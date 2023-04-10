package com.forestlake.workflow.all.servicetask;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Type:Delegate expression
 * Delegate expression:${doRepair}
 */
@Service("doRepair02")
public class DoingRepairService implements JavaDelegate {

    public void execute(DelegateExecution execution) throws Exception {
        System.out.println("进入业务任务002============================");
        String currentActivityName = execution.getCurrentActivityName();
        // 预约的具体调用
        //...
        String processDefinitionId = execution.getProcessDefinitionId();
        Map<String, Object> variables = execution.getVariables();
        variables.forEach((k, v) -> {
            System.out.println(k + "|||||" + v);
        });
        execution.setVariable("002", "002");
        System.out.println("当前活动名称：" + currentActivityName + " 流程定义id: " + processDefinitionId);
    }
}
