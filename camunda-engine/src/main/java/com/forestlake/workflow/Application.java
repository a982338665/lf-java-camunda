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
        identityService.setAuthenticatedUserId("xiaoming");
        VariableMap variables = Variables.createVariables();
        variables.put("isFree", false);
        runtimeService.startProcessInstanceByKey(processKey, variables);
    }


}
