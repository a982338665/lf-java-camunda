package com.forestlake.workflow.all.exlgateway;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Type:Delegate expression
 * Delegate expression:${doRepair}
 */
@Service("test005")
public class Test005Service implements JavaDelegate {

    public void execute(DelegateExecution execution) throws Exception {
        System.out.println("test005============================");
        Map<String, Object> variables = execution.getVariables();
        variables.forEach((k, v) -> {
            System.out.println(k + "|||||" + v);
        });
        System.out.println("test005============================");
    }
}
