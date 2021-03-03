package com.restapi.deboo.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.restapi.deboo.component.Util;
import com.restapi.deboo.vo.CooconVO;
import com.restapi.deboo.vo.Message;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("cooconService")
@RequiredArgsConstructor
public class CooconService {

    @Resource(name = "util")
    private Util util;

    // KB 아파트 시세조회 상용
    public Message apartment(CooconVO.Apartment apartment) throws Exception {
        // Object to String
        ObjectMapper mapper = new ObjectMapper()
                .configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true)
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        String url = "https://sgw.coocon.co.kr/sol/gateway/rm_price_wapi.jsp";    // 테스트


        return util.httpsRequest(url, mapper.writeValueAsString(apartment));
    }
    // KB 아파트 시세조회 테스트
    public Message testApartment(CooconVO.Apartment apartment) throws Exception {
        // Object to String
        ObjectMapper mapper = new ObjectMapper()
                .configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true)
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        String url = "https://dev2.coocon.co.kr:8443/sol/gateway/rm_price_wapi.jsp";    // 테스트

        System.out.println("url : " + url);
        System.out.println("parameter : " + mapper.writeValueAsString(apartment));

        return util.httpsRequest(url, mapper.writeValueAsString(apartment));
    }

    // 주택실거래가 조회 테스트
    public Message house(CooconVO.House house) throws Exception {
        // Object to String
        ObjectMapper mapper = new ObjectMapper()
                .configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true)
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        String url = "https://sgw.coocon.co.kr/sol/gateway/rt_price_wapi.jsp";
        return util.httpsRequest(url, mapper.writeValueAsString(house));
    }
    // 주택실거래가 조회 테스트
    public Message testHouse(CooconVO.House house) throws Exception {
        // Object to String
        ObjectMapper mapper = new ObjectMapper()
                .configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true)
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        String url = "https://dev2.coocon.co.kr:8443/sol/gateway/rt_price_wapi.jsp";
        return util.httpsRequest(url, mapper.writeValueAsString(house));
    }


    // 등기부등본 조회 사용
    public Message attested(CooconVO.Attested attested) throws Exception {
        ObjectMapper mapper = new ObjectMapper()
                .configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true)
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        String url = "https://sgw.coocon.co.kr/sol/gateway/scrap_wapi_1400.jsp";    // 테스트

        return util.httpsRequest(url, mapper.writeValueAsString(attested));
    }
    // 등기부등본 조회 테스트
    public Message testAttested(CooconVO.Attested attested) throws Exception {
        ObjectMapper mapper = new ObjectMapper()
                .configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true)
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        String url = "https://dev2.coocon.co.kr:8443/sol/gateway/scrap_wapi_1400.jsp";    // 테스트

        return util.httpsRequest(url, mapper.writeValueAsString(attested));
    }
}
