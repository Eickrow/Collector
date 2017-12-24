package com.plugins.collector.schedule;

import com.plugins.collector.bean.ProcesserStatus;
import com.plugins.collector.bean.Task;
import com.plugins.collector.dao.sqlite.TaskDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class TaskSchedule {

//    @Autowired
//    private TaskDao taskDao;

    @Scheduled(fixedDelay = 1000 * 60)
    public void addTask() {
//        List<String> list = Arrays.asList("https://www.baidu.com");
//        list.stream().map(url -> {
//            Task task = new Task();
//            task.setTarget(url);
//            task.setProcesserStatus(ProcesserStatus.waiting);
//            task.setCreatedTime(new Date());
//            return task;
//        }).forEach(taskDao::save);
    }
}
