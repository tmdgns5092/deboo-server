package com.restapi.deboo.controller;

import com.google.firebase.messaging.FirebaseMessagingException;
import com.restapi.deboo.component.FirebaseCloudMessage;
import com.restapi.deboo.component.Util;
import com.restapi.deboo.entity.UserEntity;
import com.restapi.deboo.repository.UserRepository;
import com.restapi.deboo.security.JwtTokenProvider;
import com.restapi.deboo.service.CommonService;
import com.restapi.deboo.service.UserService;
import com.restapi.deboo.vo.FcmMessageVO;
import com.restapi.deboo.vo.FileVO;
import com.restapi.deboo.vo.Message;
import com.restapi.deboo.vo.StatusEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("")
@RequiredArgsConstructor
@Api(description = "메인 컨트롤러", tags = {"common"})
public class CommonController {
    @Value("${spring.profiles.active}")
    private String activeProfile;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /* 파일업로드 테스트 용 */
    @Resource(name="util")
    private Util util;

    @Resource(name="firebaseCloudMessage")
    private FirebaseCloudMessage firebaseCloudMessage;
    @Autowired
    ResourceLoader resourceLoader;
    private final CommonService commonService;


    @GetMapping("profile")
    @ApiOperation(value="프로퍼티 식별자")
    public Object profile() {  return activeProfile;  }

    @GetMapping("health")
    @ApiOperation(value="서버 상태값 확인")
    public Object health() {
        JSONObject jobj = new JSONObject();
        jobj.put("status", "UP");
        return jobj;
    }


    @GetMapping("file/{fileId}")
    @ApiOperation(value="파일 URL 반환")
    public ResponseEntity serveFile(@PathVariable Long fileId) {

        System.out.println("file id : " + fileId);
        Message message = new Message();
        message.setStatus(StatusEnum.OK);
        message.setMessage("성공 코드");
        message.setData("localhost:3000/img/1fbc6794-dbd9-4115-9879-10aa8142d84f8_1.png");

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PostMapping("file")
    @ApiOperation(value="파일 업로드")
    public ResponseEntity file(FileVO form) throws Exception {
        Message message = new Message();
        message.setStatus(StatusEnum.OK);
        message.setMessage("성공 코드");
        message.setData(util.store(form));

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("/encrtypt")
    @ApiOperation(value="jasypt 암호화 테스트")
    public ResponseEntity encrtypt(@RequestParam(value="str") String str) {

        Message message = new Message();
        message.setStatus(StatusEnum.OK);
        message.setMessage("성공 코드");
        message.setData(util.standardEncrtypt(str));

        return new ResponseEntity(message, HttpStatus.OK);
    }

    @GetMapping("/decrtypt")
    @ApiOperation(value="jasypt 복호화 테스트")
    public ResponseEntity decrtypt(@RequestParam(value="str") String str) {

        Message message = new Message();
        message.setStatus(StatusEnum.OK);
        message.setMessage("성공 코드");
        message.setData(util.standardDecrypt(str));

        return new ResponseEntity(message, HttpStatus.OK);
    }


    @GetMapping("/get_fcm")
    @ApiOperation(value="fcm 토큰 발급")
    public ResponseEntity getFcmToken() {
        String response = "";

        try{
            response = firebaseCloudMessage.getAccessToken();
        }
        catch (IOException e) {
            e.printStackTrace();
            response = e.getMessage();
        }

        return new ResponseEntity(response, HttpStatus.OK);
    }

    @PostMapping("/send_fcm")
    @ApiOperation(value="fcm 발송")
    public ResponseEntity sendFCM(@ModelAttribute @Valid FcmMessageVO.Parameter parameter) {

        return new ResponseEntity(firebaseCloudMessage.sendMessageTo(parameter), HttpStatus.OK);
    }
}
