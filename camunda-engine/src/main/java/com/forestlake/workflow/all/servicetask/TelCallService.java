package com.forestlake.workflow.all.servicetask;


import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Type:Expression
 * Expression:${telCall.doCall(execution)}
 * Result variable: score
 * 调用telCall.doCall，将返回的结果赋值给流程变量中的 score
 * ---------------------------------------
 * Type:Expression
 * Expression:#{telCall.getScore(execution)}
 * Result variable:
 */
@Service("telCall02")
public class TelCallService {

    public int doCall(DelegateExecution execution) {
        System.out.println("进入业务任务003============================");
        String currentActivityName = execution.getCurrentActivityName();
        // 预约的具体调用
        //...
        String processDefinitionId = execution.getProcessDefinitionId();
        Map<String, Object> variables = execution.getVariables();
        variables.forEach((k, v) -> {
            System.out.println(k + "|||||" + v);
        });
        variables.put("003", "003");
        System.out.println("当前活动名称：" + currentActivityName + " 流程定义id: " + processDefinitionId);
        return 10;
    }


    public void getScore(DelegateExecution execution){
        System.out.println("进入业务任务003============================");
        Map<String, Object> variables = execution.getVariables();
        variables.forEach((k, v) -> {
            System.out.println(k + "|||||" + v);
        });
    }

}
