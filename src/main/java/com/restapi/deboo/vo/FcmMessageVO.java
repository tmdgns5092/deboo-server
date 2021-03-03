package com.restapi.deboo.vo;


import io.swagger.annotations.ApiParam;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Builder
@AllArgsConstructor
@Getter
public class FcmMessageVO {
    private boolean validate_only;
    private Message message;

    @Builder
    @AllArgsConstructor
    @Getter
    public static class Message {
        private Notification notification;
        private String token;
    }

    @Builder
    @AllArgsConstructor
    @Getter
    public static class Notification {
        private String title;
        private String body;
        private String image;
    }

    @Getter
    @Setter
    @Data
    public static class Parameter {
        @NotNull(message = "token은 null을 허용하지 않습니다")
        @Size(min=1, message="token을 입력해 주세요")
        @ApiParam(value = "FireBase OAuth2 토큰", required=true, example = "")
        String token;

        @NotNull(message = "title은 null을 허용하지 않습니다")
        @Size(min=1, message="title을 입력해 주세요")
        @ApiParam(value = "메시지 타이틀", required=true, example = "")
        String title;

        @NotNull(message = "body은 null을 허용하지 않습니다")
        @Size(min=1, message="body을 입력해 주세요")
        @ApiParam(value = "메시지 BODY", required=true, example = "")
        String body;

    }


}
