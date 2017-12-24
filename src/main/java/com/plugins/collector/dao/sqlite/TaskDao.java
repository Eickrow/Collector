package com.plugins.collector.dao.sqlite;

import com.plugins.collector.bean.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskDao extends JpaRepository<Task, Long> {
}
