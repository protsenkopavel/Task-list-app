package net.protsenko.tasklist.service.impl;

import lombok.RequiredArgsConstructor;
import net.protsenko.tasklist.domain.MailType;
import net.protsenko.tasklist.domain.task.Task;
import net.protsenko.tasklist.domain.user.User;
import net.protsenko.tasklist.service.MailService;
import net.protsenko.tasklist.service.Reminder;
import net.protsenko.tasklist.service.TaskService;
import net.protsenko.tasklist.service.UserService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;
import java.util.Properties;

@Service
@RequiredArgsConstructor
public class ReminderImpl implements Reminder {

    private final TaskService taskService;
    private final UserService userService;
    private final MailService mailService;
    private final Duration DURATION = Duration.ofHours(1);

    @Scheduled(cron = "0 * * * * *")
    @Override
    public void remindForTask() {
        List<Task> tasks = taskService.getAllSoonTasks(DURATION);
        tasks.forEach(task -> {
            User user = userService.getTaskAuthor(task.getId());
            Properties properties = new Properties();
            properties.setProperty("task.title", task.getTitle());
            properties.setProperty("task.description", task.getDescription());
            mailService.sendMail(user, MailType.REMINDER, properties);
        });
    }
}
