package com.leave.service;

import com.leave.mapper.UserImplMapper;
import com.leave.utils.RsaUtil;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Size;

import static com.leave.utils.RsaUtil.encryptByPublicKey;
import static com.leave.utils.RsaUtil.getPublicKey;

@Service
@Transactional
public class UserService {
    @Autowired
    UserImplMapper userMapper;

    public String register( String phone, String pswd) throws Exception {
        String str = phone.substring(3,7).concat(pswd.substring(4, 8));
        String idx = RsaUtil.enPass(str, 5, 15);
//        System.out.println("=========idx=========="+idx);
        userMapper.register(phone, idx, pswd);
        return "success";
    }

    public String loginPhone( String phone, String pswd) {
        return userMapper.login(phone,pswd);
    }
}
