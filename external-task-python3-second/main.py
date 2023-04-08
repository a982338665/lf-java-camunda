from camunda.external_task.external_task import ExternalTask, TaskResult
from camunda.external_task.external_task_worker import ExternalTaskWorker

default_config = {
    "maxTasks": 1,  # 一次只拉一个任务，这样多实例处理就不会所有任务被一个实例锁住
    "lockDuration": 10000,  # 锁任务的时间
    "asyncResponseTimeout": 30000,
    "retries": 0,
    "retryTimeout": 5000,
    "sleepSeconds": 30000,  # 每次拉取的间隔时间
    "auth_basic": {"username": "admin", "password": "123456"}
}


def handle_task(task: ExternalTask) -> TaskResult:
    isFress = task.get_variable("isFree")
    if isFress:
        return task.failure(error_message="我是python客户端维修免费，我不尝试自修",
                            error_details="我是python客户端维修免费，我不尝试自修\n异常stacktrace",
                            max_retries=0, retry_timeout=5000)

    return task.complete()


if __name__ == '__main__':
    worker = ExternalTaskWorker(worker_id="python-client", base_url="http://localhost:8080/engine-rest",
                                config=default_config).subscribe("try_self_repair", handle_task)

# try_self_repair 是BPMN外部任务中的topic
