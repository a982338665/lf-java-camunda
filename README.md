

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
     
# 3.service-task 业务任务
    
# 4.流程引擎自动部署 流程任务
    
    1.resources目录下添加META-INF目录，并在目录下添加processes.xml
    2.resources目录下添加bpmn目录，并将bpmn文件放于此目录，然后重启

# 5.service-task 业务任务示例
    
    1.预约维修
        使用java class 模式实现业务任务。
        com.forestlake.camunda.servicetask.ReserveRepair
        在这里插入图片描述
    2.师傅上门维修
        使用Delegate expression实现业务任务，使用EL表达式接收实现任务的Bean，不用带方法名，默认调用execute方法。 实现任务的Bean需要implements JavaDeletegate接口。Java
        在这里插入图片描述
    3.公司电话回访
        使用Expression实现业务任务，使用EL表达式接收bean.callMethod(参数)的方式执行，也可以是一行普通的表达式，比如${a==b?a:b}，并且将方法执行结果存入Result Variable;
        相比Delegate expression ，此种方式实现任务的Bean无须实现implements JavaDeletegate接口，可以是任意方法，任意参数，需要用到流程执行的参数，可以直接传入execution.
        在这里插入图片描述
    4.查看评分
        使用上一步相同的Expression方式 ，相同的bean不同的方法，获取上一步调用方法存入的结果，变量名score.

# 6.外部任务（external-task）- 常用 微服务订阅
    
    1.流程引擎：创建外部任务实例
    2.外部程序：根据topic订阅拉取
    3.外部程序&流程引擎：完成外部任务实例
        业务处理失败时，会上报引擎，继续重试，待成功后流程继续向下
    4.测试流程：
        1.启动camunda-engine
        2.启动external-task-starter-java
        3.访问http://localhost:8080/start/Process_external_task 发起流程
    
# 7.restapi鉴权 & listener监听器
    
    1.鉴权：客户端接入引擎时需要配置账号密码
    2.监听器：执行完成任务前后触发的事件

# 8.go和python3.7实现

# 9.
    
