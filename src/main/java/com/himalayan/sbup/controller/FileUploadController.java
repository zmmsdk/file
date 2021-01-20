package com.himalayan.sbup.controller;

import ch.qos.logback.core.util.FileUtil;
import com.himalayan.sbup.util.AESUtil;
import com.himalayan.sbup.util.FileNameUtil;

import org.apache.commons.io.FileUtils;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

@EnableAutoConfiguration
@RestController
public class FileUploadController {

    @ModelAttribute
    public String valid(String code) {
        System.out.println("ModelAttribute: " + code);
        if (code == null) {
            return null;
        }
        try {
            return AESUtil.Encrypt(code, AESUtil.CDKEY);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping("/test")
    public String hello(String param) {
        return "Hello World !" + param;
    }

    @RequestMapping("/server/upload")
    public Map<String, Object> upload(@ModelAttribute String code, String pre, MultipartFile file, HttpServletResponse response) {
        System.out.println("code: " + code);
        System.out.println("pre: " + pre);
        if (file == null) {
            return null;
        }
        // response.setHeader("Access-Control-Allow-Origin", "http://localhost:8083"); // 解决跨域
        response.setHeader("Access-Control-Allow-Origin", "*"); // 解决跨域

        Map<String, Object> map = new HashMap<>();
        String fileName = file.getOriginalFilename();

        if (pre == null) {
            pre = "";
        }
        File parent = new File("files/" + pre);
        boolean flag = true;
        if (!parent.exists()) {
            flag = parent.mkdirs();
        }
        File saveFile = null;
        String newFileName = FileNameUtil.getFileName(fileName);
        if (flag) {
            saveFile = new File(parent.getAbsolutePath() + File.separator + newFileName);
            try {
                file.transferTo(saveFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //获取相对路径
        String path = saveFile.getPath();
        String pathName = saveFile.getName();
        System.out.println("path: " + path);

        System.out.println("pathName: " + pathName);
       // 获取绝对路径
        System.out.println(saveFile.getAbsolutePath());
        map.put("savePath", saveFile != null ? "http://localhost:8082/sbup/server/show?path=" + URLEncoder.encode(saveFile.getAbsolutePath()) : "");
        return map;
    }

    @RequestMapping("/server/show")
    public void show(@RequestParam("path") String path, HttpServletResponse response) throws IOException {
//        response.setContentType("image/jpeg");
//        设置video 响应头  response Header
        response.setContentType("video/mp4");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0L);
        ServletOutputStream outputStream = response.getOutputStream();
        File file = new File(path);
        if(file.exists()){
            outputStream.write(FileUtils.readFileToByteArray(file));
        }
        outputStream.close();
    }
}
