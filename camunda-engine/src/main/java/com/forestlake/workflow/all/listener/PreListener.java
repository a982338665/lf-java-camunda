package com.forestlake.workflow.all.listener;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component("preListener")
public class PreListener implements ExecutionListener {
    @Override
    public void notify(DelegateExecution delegateExecution) throws Exception {
        System.out.println("前置监听开始=============================");
        Map<String, Object> variables = delegateExecution.getVariables();
        variables.forEach((k, v) -> {
            System.out.println(k + "|||||" + v);
        });
        delegateExecution.setVariable("pre", "pre");
        System.out.println("前置监听结束=============================");

    }
}

