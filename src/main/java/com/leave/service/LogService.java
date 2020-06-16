package com.leave.service;

import com.leave.mapper.LogsDao;
import com.leave.model.Logs;
import com.leave.model.User;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class LogService {

    private static final Logger log = LoggerFactory.getLogger("adminLogger");

    @Autowired
    private LogsDao logsDao;

    /**
     * 用户由调用者设置
     */
    @Async
    public void save(Logs sysLogs) {
//		SysUser user = UserUtil.getLoginUser();
        if (sysLogs == null || sysLogs.getUser() == null || sysLogs.getUser().getId() == null) {
            return;
        }

//		sysLogs.setUser(user);
        logsDao.save(sysLogs);
    }

    @Async
    public void save(String userId, String module, Boolean flag, String remark) {
        Logs sysLogs = new Logs();
        sysLogs.setFlag(flag);
        sysLogs.setModule(module);
        sysLogs.setRemark(remark);

        User user = new User();
        user.setId(userId);
        sysLogs.setUser(user);

        logsDao.save(sysLogs);

    }

    public void deleteLogs() {
        Date date = DateUtils.addMonths(new Date(), -3);
        String time = DateFormatUtils.format(date, DateFormatUtils.ISO_8601_EXTENDED_DATE_FORMAT.getPattern());

        int n = logsDao.deleteLogs(time);
        log.info("删除{}之前日志{}条", time, n);
    }
}
