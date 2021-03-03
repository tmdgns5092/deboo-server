package com.restapi.deboo.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.restapi.deboo.component.Util;
import com.restapi.deboo.entity.RequestEntity;
import com.restapi.deboo.repository.RequestRepository;
import com.restapi.deboo.vo.Message;
import com.restapi.deboo.vo.StatusEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("requestService")
@RequiredArgsConstructor
public class RequestService {

    @Resource(name = "util")
    private Util util;
    @Autowired
    private RequestRepository requestRep;


    // 대출 리스트 조회
    public Message getRequestList(int user_id) throws Exception {
        Message message = new Message();
        requestRep.findAllByUserId(user_id);

        message.setStatus(StatusEnum.OK);
        message.setMessage("성공 코드");
        message.setData(requestRep);

        return message;
    }

    // 대출 리스트 조회
    public Message getRequest(int id, int user_id) throws Exception {
        Message message = new Message();
        requestRep.findByUserId(id, user_id);

        message.setStatus(StatusEnum.OK);
        message.setMessage("성공 코드");
        message.setData(requestRep);

        return message;
    }

    // 대출 신청
    public Message setRequest(RequestEntity requestEntity) throws Exception {
        Message message = new Message();
        requestEntity = requestRep.save(requestEntity);

        message.setStatus(StatusEnum.OK);
        message.setMessage("성공 코드");
        message.setData(requestEntity.getId() + " Record create success");

        return message;
    }
}
