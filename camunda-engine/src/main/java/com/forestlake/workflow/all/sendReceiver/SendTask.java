package com.forestlake.workflow.all.sendReceiver;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component("sendTask")
public class SendTask implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        System.out.println("sendTask发送消息============================================");
        RuntimeService runtimeService = execution.getProcessEngineServices().getRuntimeService();
        runtimeService
                //接收任务的name
                .createMessageCorrelation("Message_receive_task_test")
                //业务唯一标识，此处为了防止所有实例都收到此消息
                .processInstanceBusinessKey("message_bussinessKey")
                .correlate();
    }
}

