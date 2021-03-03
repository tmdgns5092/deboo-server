package com.restapi.deboo.component;


import com.restapi.deboo.security.JwtTokenProvider;
import com.restapi.deboo.vo.FileVO;
import com.restapi.deboo.vo.Message;
import com.restapi.deboo.vo.StatusEnum;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.UUID;

@Component("util")
@Service
public class Util {
    private JwtTokenProvider jwtTokenProvider;

    public static String standardEncrtypt(String str) {
        StandardPBEStringEncryptor standardPBEStringEncryptor = new StandardPBEStringEncryptor();
        standardPBEStringEncryptor.setAlgorithm("PBEWithMD5AndDES");
        standardPBEStringEncryptor.setPassword("deboo");

        return standardPBEStringEncryptor.encrypt(str);
    }

    public static String standardDecrypt(String str) {
        StandardPBEStringEncryptor standardPBEStringEncryptor = new StandardPBEStringEncryptor();
        standardPBEStringEncryptor.setAlgorithm("PBEWithMD5AndDES");
        standardPBEStringEncryptor.setPassword("deboo");

        return standardPBEStringEncryptor.decrypt(str);
    }

    public String listMapToString(Map<String, Object> map) {
        String result = "{";

        for (String key : map.keySet()) {
            result += "\"" + key + "\": \"" + map.get(key).toString() + "\",";
        }

        return result + "}";
    }


    // 나중에 서비스로 이동해야 함
    public Boolean store(FileVO fileVO) throws Exception {
        Path rootLocation = Paths.get("/Users/gwonseunghun/Documents/dev/springBoot/deboo/src/main/resources/static/img/");
        try {
            for (MultipartFile file : fileVO.getFiles()) {
                if (file.isEmpty()) {
                    throw new Exception("Filed to store empty file " + file.getOriginalFilename());
                }
                System.out.println("original file name : " + file.getOriginalFilename());
                String saveFileName = fileSave(rootLocation.toString(), file);
                System.out.println("file name : " + saveFileName);
                // DB insert
//                UploadFile saveFile = new UploadFile();
//                saveFile.setFileName(file.getOriginalFilename());
//                saveFile.setSaveFileName(saveFileName);
//                saveFile.setContentType(file.getContentType());
//                saveFile.setSize(file.getResource().contentLength());
//                saveFile.setRegisterDate(LocalDateTime.now());
//                saveFile.setFilePath(rootLocation.toString().replace(File.separatorChar, '/') +'/' + saveFileName);
//                uploadFileRepository.save(saveFile);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public String fileSave(String rootLocation, MultipartFile file) throws IOException {
        System.out.println("root location : " + rootLocation);
        File uploadDir = new File(rootLocation);

        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        UUID uuid = UUID.randomUUID().randomUUID();
        String saveFileName = uuid.toString() + file.getOriginalFilename();
        File saveFile = new File(rootLocation, saveFileName);
        FileCopyUtils.copy(file.getBytes(), saveFile);

        return saveFileName;
    }

    public Message httpsRequest(String url, String param) {
        HttpsURLConnection conn;
        byte[] resMessage = null;
        String temp = "";
        Message message = new Message();
        message.setStatus(StatusEnum.OK);
        message.setMessage("성공 코드");
        message.setData("");

        try {
            conn = (HttpsURLConnection) new URL(url).openConnection();

            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setUseCaches(false);
            OutputStreamWriter os = new OutputStreamWriter(conn.getOutputStream());

            String JSONDataVal = URLEncoder.encode(URLEncoder.encode(param, "UTF-8"), "UTF-8");
            String postString = "JSONData=" + JSONDataVal;
            os.write(postString);
            os.flush();
            os.close();

            DataInputStream in = new DataInputStream(conn.getInputStream());
            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            int bcount = 0;
            byte[] buf = new byte[2048];
            while (true) {
                int n = in.read(buf);
                if (n == -1) break;
                bout.write(buf, 0, n);
            }
            bout.flush();
            resMessage = bout.toByteArray();
            conn.disconnect();

            try {
                temp = new String(resMessage, "UTF-8");
            } catch (IOException e) {
                e.printStackTrace();
            }
            temp = temp.replaceAll("\r\n", "");
            temp = temp.replaceAll("\r", "");
            temp = temp.replaceAll("\n", "");

            try {
                JSONParser parser = new JSONParser();
                Object obj = parser.parse(temp);
                JSONObject jobj = (JSONObject) obj;
                message.setStatus(StatusEnum.OK);
                message.setMessage("성공 코드");
                message.setData(jobj);
                return message;
            } catch (Exception e) {
                e.printStackTrace();
                message.setStatus(StatusEnum.OK);
                message.setMessage("성공 코드");
                message.setData(e.getMessage());
                return message;
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
            message.setStatus(StatusEnum.OK);
            message.setMessage("성공 코드");
            message.setData(e.getMessage());
            return message;
        } catch (IOException e) {
            e.printStackTrace();
            message.setStatus(StatusEnum.OK);
            message.setMessage("성공 코드");
            message.setData(e.getMessage());
            return message;
        }
    }
}
