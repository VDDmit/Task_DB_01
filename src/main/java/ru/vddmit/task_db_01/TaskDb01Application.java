package ru.vddmit.task_db_01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class TaskDb01Application {

    public static void main(String[] args) {
        SpringApplication.run(TaskDb01Application.class, args);
    }
}
