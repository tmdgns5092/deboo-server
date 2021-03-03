package com.restapi.deboo.service;

import com.restapi.deboo.vo.Message;
import com.restapi.deboo.vo.UserVO;
import com.restapi.deboo.entity.UserEntity;
import com.restapi.deboo.repository.UserRepository;
import com.restapi.deboo.component.Util;
import com.restapi.deboo.vo.StatusEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service("userService")
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    @Resource(name = "util")
    private Util util;
    @Autowired
    private UserRepository userRep;

    @Override
    public UserVO loadUserByUsername(String username) throws UsernameNotFoundException {
        List<UserEntity> user = userRep.loadUserByUsername(username);
        if(user.size() == 0)
            throw new UsernameNotFoundException(username);

        return new UserVO(user.get(0));
    }

    public Message findById(int id) {
        Message message = new Message();
        message.setStatus(StatusEnum.OK);
        message.setMessage("성공 코드");
        message.setData(userRep.findById(id));

        return message;
    }

    public Message findAll() {
        Message message = new Message();
        message.setStatus(StatusEnum.OK);
        message.setMessage("성공 코드");
        message.setData(userRep.findAll());

        return message;
    }

    public Message create(UserEntity.create create) {
        Message message = new Message();

        // user phone select
        List<UserEntity> areadyCheck = userRep.findAllByPhone(create.getPhone());

        if(areadyCheck.size() > 0) {
            message.setStatus(StatusEnum.ALREADY_REPORTED);
            message.setMessage("성공 코드");
            message.setData("Already Phone number");

            return message;
        } else {
            int mg = userRep.create(create.getName(), create.getGender(), create.getEmail(), create.getPhone(), create.getType(), create.getAuth(), create.isActive(), create.getAccess_token());

            System.out.println(mg);

            message.setStatus(StatusEnum.OK);
            message.setMessage("성공 코드");
            message.setData("User Create Success");

            return message;
        }
    }

    public Message localLoginCheck(UserEntity.localLoginEntity param) {
        UserEntity result   = userRep.findOneByUserPhone(param.getPhone());
        Message message = new Message();

        if(result != null) {
            if(param.getName().equals(result.getName())){
                message.setStatus(StatusEnum.OK);
                message.setMessage("성공 코드");
                message.setData(result.getId());
            } else {
                message.setStatus(StatusEnum.BAD_REQUEST);
                message.setMessage("실패 코드");
                message.setData("Name do not match");
            }
        } else {
            message.setStatus(StatusEnum.NOT_FOUND);
            message.setMessage("실패 코드");
            message.setData("Not Found User Record");
        }

        return message;
    }

    public Message socialLoginCheck(UserEntity.socialLoginEntity param) {
        UserEntity result = userRep.findOneByUserAccessToken(param.getAccess_token());
        Message message = new Message();

        if(result != null) {
            if(param.getName().equals(result.getName()) && param.getPhone().equals(result.getPhone())){
                message.setStatus(StatusEnum.OK);
                message.setMessage("성공 코드");
                message.setData(result.getId());
            } else {
                message.setStatus(StatusEnum.BAD_REQUEST);
                message.setMessage("실패 코드");
                message.setData("Name or Phone do not match");
            }
        } else {
            message.setStatus(StatusEnum.NOT_FOUND);
            message.setMessage("실패 코드");
            message.setData("Not Found User Record");
        }

        return message;
    }

    public Message updateProfile(UserEntity.profile param, int id) {
        Message message = new Message();
        Optional<UserEntity> user = userRep.findById(id);

        if(user != null) {
            user.get().setName(param.getName());
            user.get().setEmail(param.getEmail());
            user.get().setPhone(param.getPhone());
            userRep.save(user.get());

            message.setStatus(StatusEnum.OK);
            message.setMessage("성공 코드");
            message.setData(user.get());
        } else {
            message.setStatus(StatusEnum.NOT_FOUND);
            message.setMessage("실패 코드");
            message.setData("Not Found User Data. Check token");
        }

        return message;
    }


}
