package com.forestlake.workflow.externaltask;

import org.camunda.bpm.client.spring.annotation.ExternalTaskSubscription;
import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageEventExternalTaskService {

    @Bean
    @ExternalTaskSubscription(topicName = "wechatPay", processDefinitionKeyIn = {"Process_message_event"},lockDuration=50000)
    public ExternalTaskHandler wechatPay() {

        return (externalTask, externalTaskService) -> {
            System.out.println("微信支付");
            externalTaskService.complete(externalTask);

        };
    }


    @Bean
    @ExternalTaskSubscription(topicName = "alipay", processDefinitionKeyIn = {"Process_message_event"},lockDuration=50000)
    public ExternalTaskHandler alipay() {

        return (externalTask, externalTaskService) -> {
            System.out.println("阿里支付");
            externalTaskService.complete(externalTask);

        };
    }

    @Bean
    @ExternalTaskSubscription(topicName = "success_pay", processDefinitionKeyIn = {"Process_message_event"},lockDuration=50000)
    public ExternalTaskHandler successPay() {

        return (externalTask, externalTaskService) -> {
            System.out.println("支付成功");
            externalTaskService.complete(externalTask);

        };
    }
}

