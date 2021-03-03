package com.restapi.deboo.controller;

import com.restapi.deboo.entity.UserEntity;
import com.restapi.deboo.repository.UserRepository;
import com.restapi.deboo.security.JwtTokenProvider;
import com.restapi.deboo.service.UserService;
import com.restapi.deboo.component.Util;
import com.restapi.deboo.vo.Message;
import com.restapi.deboo.vo.StatusEnum;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import lombok.RequiredArgsConstructor;

import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("user")
@RequiredArgsConstructor
@Api(description = "유저 컨트롤러", tags = {"user"})
public class UserController {

    private final UserService       userService;
    private final JwtTokenProvider  jwtTokenProvider;


    @GetMapping("/{id}")
    @ApiOperation(value = "유저 조회")
    public ResponseEntity findById(@PathVariable("id") int id) {
        return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
    }

    @GetMapping("")
    @ApiOperation(value = "유저 리스트 조회")
    public ResponseEntity findAll() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/")
    @ApiOperation(value="유저 추가")
    public ResponseEntity create(@ModelAttribute @Valid UserEntity.create create) {
        return new ResponseEntity<>(userService.create(create), HttpStatus.OK);
    }

    @GetMapping("/localLogin")
    @ApiOperation(value="로컬 로그인")
    public ResponseEntity localLoginCheck(@ModelAttribute UserEntity.localLoginEntity param) {
        List<String> role = new ArrayList<>();
        Message message = userService.localLoginCheck(param);

        if("성공 코드".equals(message.getMessage())) {
            role.add("USER");
            message.setData(jwtTokenProvider.createToken(message.getData().toString(), role));
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else
            return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("/socialLogin")
    @ApiOperation(value="소셜 로그인")
    public ResponseEntity socialLoginCheck(@ModelAttribute UserEntity.socialLoginEntity param) {
        List<String> role = new ArrayList<>();
        Message message = userService.socialLoginCheck(param);

        if("성공 코드".equals(message.getMessage())) {
            role.add("USER");
            return new ResponseEntity<>(jwtTokenProvider.createToken(message.getData().toString(), role), HttpStatus.OK);
        } else
            return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PutMapping("/profile")
    @ApiOperation(value="프로필 수정")
    public ResponseEntity profile(@ModelAttribute @Valid UserEntity.profile param, ServletRequest request) {
        String pk = jwtTokenProvider.resolveToken((HttpServletRequest) request);
        int id = Integer.parseInt(jwtTokenProvider.getUserPk(pk));

        return new ResponseEntity(userService.updateProfile(param, id), HttpStatus.OK);
    }

}
