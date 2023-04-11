package com.forestlake.workflow;

import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.Variables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@SpringBootApplication
@RestController
public class Application {


    @Autowired
    private IdentityService identityService;
    @Autowired
    private RuntimeService runtimeService;

    public static void main(String... args) {
        SpringApplication.run(Application.class, args);
    }


    @GetMapping("/start/{processKey}")
    public void start(@PathVariable(value = "processKey") String processKey) {
//        identityService.setAuthenticatedUserId("xiaoming");
//        runtimeService.startProcessInstanceByKey(processKey);
        //==========================================================
//        identityService.setAuthenticatedUserId("xiaoming");
//        VariableMap variables = Variables.createVariables();
//        variables.put("isFree", false);
//        runtimeService.startProcessInstanceByKey(processKey, variables);
        //==========================================================
//        VariableMap variables = Variables.createVariables();
//        identityService.setAuthenticatedUserId("xiaoming");
//        List<String> leaders = new LinkedList<>();
//        leaders.add("wangbing");
//        leaders.add("zhangsan");
//        leaders.add("wangwu");
//        variables.put("leaders", leaders);
//        variables.put("originDays", 10);
//        runtimeService.startProcessInstanceByKey(processKey, variables);
        //==========================================================
        VariableMap variables = Variables.createVariables();
        identityService.setAuthenticatedUserId("xiaoming");
        variables.put("originDays", 10);
        runtimeService.startProcessInstanceByKey(processKey, variables);

    }

    @GetMapping("/1/{processKey}")
    public void start01(@PathVariable(value = "processKey") String processKey) {
        VariableMap variables = Variables.createVariables();
        identityService.setAuthenticatedUserId("xiaoming");
        variables.put("originDays", 10);
        variables.put("isFree", false);
        List<String> leaders = new LinkedList<>();
        leaders.add("wangbing");
        leaders.add("zhangsan");
        leaders.add("wangwu");
        variables.put("leaders", leaders);
        variables.put("a", 10);
        variables.put("b", 1);
        List<String> videoNames = new ArrayList<>();
        videoNames.add("电影");
        videoNames.add("电视剧");
        videoNames.add("综艺节目");
        variables.put("videoNames",videoNames);
        runtimeService.startProcessInstanceByKey(processKey, variables);

    }


}
