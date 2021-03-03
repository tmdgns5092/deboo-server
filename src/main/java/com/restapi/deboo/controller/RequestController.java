package com.restapi.deboo.controller;

import com.restapi.deboo.entity.RequestEntity;
import com.restapi.deboo.security.JwtTokenProvider;
import com.restapi.deboo.service.RequestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("request")
@RequiredArgsConstructor
@Api(description = "대출신청 컨트롤러", tags = {"request"})
public class RequestController {

    private final RequestService requestService;
    private final JwtTokenProvider jwtTokenProvider;


    @GetMapping("/{id}")
    @ApiOperation(value = "대출 조회")
    public ResponseEntity getRequestAll(@PathVariable("id") int id, ServletRequest request) throws Exception {
        String pk = jwtTokenProvider.resolveToken((HttpServletRequest) request);
        int user_id = Integer.parseInt(jwtTokenProvider.getUserPk(pk));

        return new ResponseEntity<>(requestService.getRequest(id, user_id), HttpStatus.OK);
    }

    @GetMapping("/")
    @ApiOperation(value = "대출 리스트 조회")
    public ResponseEntity getRequestAll(ServletRequest request) throws Exception {
        String pk = jwtTokenProvider.resolveToken((HttpServletRequest) request);
        int user_id = Integer.parseInt(jwtTokenProvider.getUserPk(pk));

        return new ResponseEntity<>(requestService.getRequestList(user_id), HttpStatus.OK);
    }

    @PostMapping("/")
    @ApiOperation(value = "대출신청")
    public ResponseEntity setRequest(@ModelAttribute @Valid RequestEntity requestEntity, ServletRequest request) throws Exception {
        String pk = jwtTokenProvider.resolveToken((HttpServletRequest) request);
        requestEntity.setUser_id(Integer.parseInt(jwtTokenProvider.getUserPk(pk)));

        return new ResponseEntity<>(requestService.setRequest(requestEntity), HttpStatus.OK);
    }


}
