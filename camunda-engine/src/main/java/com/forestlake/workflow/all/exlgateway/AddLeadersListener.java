package com.forestlake.workflow.all.exlgateway;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("addLeaders")
public class AddLeadersListener implements ExecutionListener {
    @Override
    public void notify(DelegateExecution execution) throws Exception {
        long leaveDay =  (long)execution.getVariable("days");
        List<String> leaders = new ArrayList<>();
        if (leaveDay>3 && leaveDay<=5){
            leaders.add("wangbing");
            leaders.add("zhangsan");
            System.out.println("3-5:========");
        }else if (leaveDay>5){
            leaders.add("wangbing");
            leaders.add("zhangsan");
            leaders.add("wangwu");
            System.out.println(">5:========");
        }
        execution.setVariable("leaders",leaders);
    }
}

