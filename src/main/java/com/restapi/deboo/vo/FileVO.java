package com.restapi.deboo.vo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
public class FileVO {
    private List<MultipartFile> files;
    private String field1;
    private String field2;
    private String field3;
    private String field4;
}
