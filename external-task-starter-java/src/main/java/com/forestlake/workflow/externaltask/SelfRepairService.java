package com.forestlake.workflow.externaltask;

import org.camunda.bpm.client.spring.annotation.ExternalTaskSubscription;
import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class SelfRepairService {
    @Bean
    @ExternalTaskSubscription(topicName = "try_self_repair", processDefinitionKeyIn = {"Process_external_task"}, lockDuration = 50000)
    public ExternalTaskHandler doSelfRepair() {

        return (externalTask, externalTaskService) -> {
            System.out.println("外部任务进入偿试自修");
            try {
                TimeUnit.SECONDS.sleep(1L);
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
            boolean isFree = externalTask.getVariable("isFree");
            if (isFree) {
                System.err.println("免费维修");
//                i:重试次数  l：重试时间间隔，单位毫秒
                externalTaskService.handleFailure(externalTask, "维修免费，不自修了", "异常打印", 1, 5000);
            } else {
                System.err.println("收费维修");
                externalTaskService.complete(externalTask);
            }
        };
    }
}
