package com.leave.service;

import com.leave.mapper.PermitDao;
import com.leave.model.Permit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PermitService {
    private static final Logger log = LoggerFactory.getLogger("adminLogger");

    @Autowired
    private PermitDao permissionDao;

    public void save(Permit permission) {
        permissionDao.save(permission);

        log.debug("新增菜单{}", permission.getName());
    }

    public void update(Permit permission) {
        permissionDao.update(permission);
    }

    @Transactional
    public void delete(String id) {
        permissionDao.deleteRolePermission(id);
        permissionDao.delete(id);
        permissionDao.deleteByParentId(id);

        log.debug("删除菜单id:{}", id);
    }
}
