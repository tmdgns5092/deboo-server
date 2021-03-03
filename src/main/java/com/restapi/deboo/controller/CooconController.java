package com.restapi.deboo.controller;

import com.google.gson.Gson;
import com.restapi.deboo.entity.UserEntity;
import com.restapi.deboo.service.CooconService;
import com.restapi.deboo.vo.CooconVO;
import com.restapi.deboo.vo.Message;
import com.restapi.deboo.vo.StatusEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import net.sf.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("coocon")
@RequiredArgsConstructor
@Api(description = "쿠콘 API 컨트롤러", tags = {"coocon"})
public class CooconController {

    private final CooconService cooconService;

    // KB 아파트 시세조회 상용
    @GetMapping("/apartment")
    @ApiOperation(value="KB아파트 시세조회 상용")
    public ResponseEntity apartment(@ModelAttribute @Valid CooconVO.Apartment apartment) throws Exception {
        return new ResponseEntity(cooconService.apartment(apartment), HttpStatus.OK);
    }

    // KB 아파트 시세조회 테스트
    @GetMapping("/testApartment")
    @ApiOperation(value="KB아파트 시세조회 개발")
    public ResponseEntity testApartment(@ModelAttribute @Valid CooconVO.Apartment apartment) throws Exception {
        System.out.println("test");
        return new ResponseEntity(cooconService.testApartment(apartment), HttpStatus.OK);
    }

    // 주택실거래가 조회 테스트
    @GetMapping("/house")
    @ApiOperation(value="주택실거래가 조회 개발")
    public ResponseEntity house(@ModelAttribute @Valid CooconVO.House house) throws Exception {
        return new ResponseEntity(cooconService.testHouse(house), HttpStatus.OK);
    }

    // 등기부등본 조회 테스트
    @GetMapping("/testAttested")
    @ApiOperation(value="등기부등본 조회 개발")
    public ResponseEntity testAttested(@ModelAttribute @Valid CooconVO.Attested attested) throws Exception {
        return new ResponseEntity(cooconService.testAttested(attested), HttpStatus.OK);
    }
}
