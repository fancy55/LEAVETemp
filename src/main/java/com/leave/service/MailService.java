package com.leave.service;

import com.leave.mapper.MailDao;
import com.leave.model.Mail;
import com.leave.utils.UserUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MailService {

    private static final Logger log = LoggerFactory.getLogger("adminLogger");

    @Autowired
    private SendMailService sendMailService;
    @Autowired
    private MailDao mailDao;

    @Transactional
    public void save(Mail mail, List<String> toUser) {
        mail.setUserId(UserUtil.getLoginUser().getId());
        mailDao.save(mail);

        toUser.forEach(u -> {
            int status = 1;
            try {
                sendMailService.sendMail(u, mail.getSubject(), mail.getContent());
            } catch (Exception e) {
                log.error("发送邮件失败", e);
                status = 0;
            }

            mailDao.saveToUser(mail.getId(), u, status);
        });

    }
}
