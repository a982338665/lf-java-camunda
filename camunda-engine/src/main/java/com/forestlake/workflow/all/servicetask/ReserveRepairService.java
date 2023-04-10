package com.forestlake.workflow.all.servicetask;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.util.Map;

/**
 * Type:Java class
 * Java class:com.forestlake.workflow.servicetask.ReserveRepairService
 */
public class ReserveRepairService implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        System.out.println("进入业务任务001============================");
        String currentActivityName = execution.getCurrentActivityName();
        // 预约的具体调用
        //...
        String processDefinitionId = execution.getProcessDefinitionId();
        Map<String, Object> variables = execution.getVariables();
        variables.forEach((k, v) -> {
            System.out.println(k + "|||||" + v);
        });
        variables.put("001", "001");
        System.out.println("当前活动名称：" + currentActivityName + " 流程定义id: " + processDefinitionId);
    }
}
