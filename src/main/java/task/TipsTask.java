package task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class TipsTask implements Runnable {

    @Autowired
    TaskScheduler poolTaskScheduler;

    @Override
    public void run() {

        System.out.println("系统提醒，你已经工作了15分钟！");
    }

    @PostConstruct
    public void init() {

        System.out.println("submit task");
        poolTaskScheduler.schedule(this, new CronTrigger("0 0/15 * * * ? "));
    }
}
