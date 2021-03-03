package com.restapi.deboo.service;

import com.restapi.deboo.vo.Message;
import com.restapi.deboo.component.Util;
import com.restapi.deboo.vo.StatusEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

@Service("commonService")
@RequiredArgsConstructor
public class CommonService {
    @Resource(name = "util")
    private Util util;

}
