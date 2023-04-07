package com.forestlake.workflow.servicetask;


import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.springframework.stereotype.Service;

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
@Service("telCall")
public class TelCallService {

    public int doCall(DelegateExecution execution){
        System.out.println("开始电话回访");
        String repairManName = String.valueOf(execution.getVariable("repairManName"));
        System.out.println("您对"+repairManName+"的服务打几分");
        return 10;
    }

    public void getScore(DelegateExecution execution){
        System.out.println("查询评分");
        String repairManName = String.valueOf(execution.getVariable("repairManName"));
        int score =  (int)execution.getVariable("score");
        System.out.println("顾客对"+repairManName+"的评分："+score);

    }

}
