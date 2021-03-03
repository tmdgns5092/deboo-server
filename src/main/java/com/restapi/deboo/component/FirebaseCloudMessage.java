package com.restapi.deboo.component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.auth.oauth2.GoogleCredentials;
import com.restapi.deboo.vo.FcmMessageVO;
import com.restapi.deboo.vo.Message;
import com.restapi.deboo.vo.StatusEnum;
import lombok.RequiredArgsConstructor;
import okhttp3.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.URL;

@Component("firebaseCloudMessage")
@RequiredArgsConstructor
public class FirebaseCloudMessage {
    private final String API_URL = "https://fcm.googleapis.com/v1/projects/mvpick-deboo/messages:send";
    private final ObjectMapper objectMapper;

    public Message sendMessageTo(FcmMessageVO.Parameter parameter) {
        Message message = new Message();
        message.setStatus(StatusEnum.OK);
        message.setMessage("성공 코드");

        try{
            String fcmMessage = makeMessage(parameter);

            OkHttpClient client = new OkHttpClient();
            RequestBody requestBody = RequestBody.create(fcmMessage , MediaType.get("application/json; charset=utf-8"));
            Request request = new Request.Builder()
                    .url(API_URL)
                    .post(requestBody)
                    .addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + getAccessToken())
                    .addHeader(HttpHeaders.CONTENT_TYPE, "application/json; UTF-8")
                    .build();

            Response response = client.newCall(request).execute();
            response.close();

            message.setData("success");
        } catch(IOException e) {
            e.printStackTrace();
            message.setData(e.getMessage());
        }

        return message;
    }

    private String makeMessage(FcmMessageVO.Parameter parameter) throws JsonProcessingException {
        FcmMessageVO fcmMessageVO = FcmMessageVO
                .builder()
                .message(
                        FcmMessageVO.Message
                                .builder()
                                .token(parameter.getToken())
                                .notification(
                                        FcmMessageVO.Notification
                                                .builder()
                                                .title(parameter.getTitle())
                                                .body(parameter.getBody())
                                                .image(null)
                                                .build())
                                .build())
                .validate_only(false)
                .build();

        return objectMapper.writeValueAsString(fcmMessageVO);
    }

    // 사용자 인증 정보를 사용하여 액세스 토큰 발급
    public String getAccessToken() throws IOException {
        String firebaseConfigPath = "mvpick-deboo-firebase-adminsdk-996nx-50038116bc.json";

        GoogleCredentials googleCredentials = GoogleCredentials
                .fromStream(new ClassPathResource(firebaseConfigPath).getInputStream())
                .createScoped("https://www.googleapis.com/auth/cloud-platform");

        googleCredentials.refreshIfExpired();
        return googleCredentials.getAccessToken().getTokenValue();
    }
}
