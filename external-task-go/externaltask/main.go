package main

import (
	"fmt"
	camundaclientgo "github.com/citilinkru/camunda-client-go/v3"
	"github.com/citilinkru/camunda-client-go/v3/processor"
	"time"
)

func main() {
	client := camundaclientgo.NewClient(camundaclientgo.ClientOptions{
		EndpointUrl: "http://localhost:8080/engine-rest",
		ApiUser:     "admin",
		ApiPassword: "123456",
		Timeout: time.Second * 100000,
	})

	logger := func(err error) {
		fmt.Println(err.Error())
	}

	proc := processor.NewProcessor(client, &processor.Options{
		WorkerId:     "go-client",
		LockDuration: time.Second * 50,
		MaxTasks:     10,
		//MaxParallelTaskPerHandler: 100,
		LongPollingTimeout: 500 * time.Second,
	}, logger)

	defKey := "Process_external_task"
	proc.AddHandler(
		[]*camundaclientgo.QueryFetchAndLockTopic{
			{TopicName: "try_self_repair"},
			//{LockDuration: 1000},
			{ProcessDefinitionKeyIn:  &defKey},
		},

		func(ctx *processor.Context) error {
			fmt.Printf("进入到go外部任务")
			time.Sleep(time.Second * 1)
			isFree := ctx.Task.Variables["isFree"].Value.(bool)
			if isFree {
				fmt.Println("维修是免费的，我不尝试自修")
				erroMsg := "我是go服务，我不要尝试自修"
				errDetail := "这里是stackTrace"
				retrys := 0
				rtryTimeout := 5000

				ctx.HandleFailure(processor.QueryHandleFailure{
					ErrorMessage: &erroMsg,
					ErrorDetails: &errDetail,
					Retries:      &retrys,
					RetryTimeout: &rtryTimeout,
				})
			} else {
				fmt.Println("维修是不是免费的，我要尝试自修")
				ctx.Complete(processor.QueryComplete{})
			}

			return nil
		},

	)
	// 让主进程不退出
	select {}
}
