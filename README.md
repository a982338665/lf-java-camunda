

# 1.功能分布

    1.C端：独立
        1.创建流程
        2.发布流程到B段   
        3.开启一个流程实例进行流程测试（需要B端）
    2.B端：-> 流程引擎端
        1.web端：
            1.支持用户创建，分组及权限
            2.支持流程流转管理（包含各个流程的相关转换，可以在此处查看每个用户流程流转的详细信息）
        2.server端：
            1.支持web端一切功能，本身为web端后台
            2.支持对外api或接口，保证流程引擎的外部接入

# 2.业务侧发起请假流程 -> 使用web端进行流程流转（集成业务时，应当使用业务的web端进行流转）
    
    1.启动项目【camunda-engine】，开启【流程引擎】部署（含B端：http://localhost:8080/）
    2.启动C端，发布【请假流程】，并在B端查看发布是否成功
    3.调用项目【camunda-engine】的/start/{processKey}接口，发起一个请假流程
        http://localhost:8080/start/Process_user_task
    4.登录B端（使用账号：xiaoming），查看任务列表，找到发起的请假流程，填写信息并完成
    5.登录B端（使用账号：wangbing），查看任务列表，找到发起的审批流程，填写信息并完成
    

