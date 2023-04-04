
const { Client, logger ,Variables} = require('camunda-external-task-client-js');
const open = require('open');

// configuration for the Client:
//  - 'baseUrl': url to the Process Engine
//  - 'logger': utility to automatically log important events
//  - 'asyncResponseTimeout': long polling timeout (then a new request will be issued)
const config = { baseUrl: 'http://localhost:8080/engine-rest', use: logger, asyncResponseTimeout: 10000 };


const client = new Client(config);

// 订阅BPMN中付款 topic: 'pay'，processDefinitionKey可以指定是哪个流程，可能其他流程也是相同topic
client.subscribe('pay', { processDefinitionKey: "Process_shopping" },
    async function ({ task, taskService }) {

        // Put your business logic here

        // Get a process variable
        const size = task.variables.get('size');
        const count = task.variables.get('count');

        console.log(`顾客下单尺寸： ${size} 数量：'${count}'...`);

        const processVariables = new Variables()
            .set("toWhere", "shanghai China");

        // Complete the task
        try {
            await taskService.complete(task,processVariables);
            console.log('I completed my task successfully!!');
        } catch (e) {
            console.error(`Failed completing my task, ${e}`);
        }
    });
