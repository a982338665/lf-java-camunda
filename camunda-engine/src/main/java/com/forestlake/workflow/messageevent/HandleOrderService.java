package com.forestlake.workflow.messageevent;


import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component("handleOrder")
public class HandleOrderService implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        System.out.println("处理订餐订单");
        String endpoint = (String)execution.getVariable("endpoint");
        RuntimeService runtimeService = execution.getProcessEngineServices().getRuntimeService();
        String businessKey = execution.getBusinessKey();
        if(endpoint.equals("alipay")){
            runtimeService.startProcessInstanceByMessage("Message_alipay_lane",businessKey);
        }else if (endpoint.equals("wechat")){
            runtimeService.startProcessInstanceByMessage("Message_wechat_lane",businessKey);
        }

    }
}
