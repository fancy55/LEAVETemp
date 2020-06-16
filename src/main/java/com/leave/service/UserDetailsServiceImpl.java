package com.leave.service;

import com.leave.dto.LoginUser;
import com.leave.mapper.PermitDao;
import com.leave.model.Permit;
import com.leave.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * spring security登陆处理
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;
    @Autowired
    private PermitDao permissionDao;

    @Override
    public UserDetails loadUserByUsername(String idx) throws UsernameNotFoundException {
        User user = userService.getUser(idx);
        if (user == null) {
            throw new AuthenticationCredentialsNotFoundException("用户名不存在");
        } else if (user.getStatus() == User.Status.LOCKED) {
            throw new LockedException("用户被锁定,请联系管理员");
        } else if (user.getStatus() == User.Status.DISABLED) {
            throw new DisabledException("用户已作废");
        }

        LoginUser loginUser = new LoginUser();
        BeanUtils.copyProperties(user, loginUser);

        List<Permit> permissions = permissionDao.listByUserId(user.getId());
        loginUser.setPermissions(permissions);

        return loginUser;
    }
}