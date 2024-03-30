package net.protsenko.tasklist.service;

import net.protsenko.tasklist.domain.MailType;
import net.protsenko.tasklist.domain.user.User;

import java.util.Map;
import java.util.Properties;

public interface MailService {

    void sendMail(User user, MailType type, Properties properties);

}
