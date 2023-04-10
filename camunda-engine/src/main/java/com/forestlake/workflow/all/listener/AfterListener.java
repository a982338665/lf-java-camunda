package com.forestlake.workflow.all.listener;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 并行网关：
 * 并行执行后，取到的execution 是全局变量，包含整个流程结束前的所有内容，应该会有线程安全问题
 */
@Component("afterListener")
public class AfterListener implements ExecutionListener {
    @Override
    public void notify(DelegateExecution delegateExecution) throws Exception {
        System.out.println("after监听开始=============================");
        Map<String, Object> variables = delegateExecution.getVariables();
        variables.forEach((k, v) -> {
            System.out.println(k + "|||||" + v);
        });
        delegateExecution.setVariable("after", "after");
        System.out.println("after监听结束=============================");
    }
}

