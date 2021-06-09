package com.example.file.controller;

import com.example.file.ceph.FileHandler;
import com.example.util.RespResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: chenq
 * @Description:
 * @Date: Created in 2021-06-09 10:15
 */
@RestController
@RequestMapping(value = "/file")
public class UploadController {

    @Autowired
    private FileHandler fileHandler;

    /**
     * 文件上传
     */
    @PostMapping("/upload")
    public RespResult upload(MultipartFile file) throws IOException {
        fileHandler.upload(file.getOriginalFilename(), file.getBytes());
        return RespResult.ok();
    }

    /**
     * 文件下载
     */
    @PostMapping("/download/{fileName}")
    public RespResult download(@PathVariable String fileName, HttpServletResponse response) throws IOException {
        byte[] buffer = fileHandler.download(fileName);
        ServletOutputStream os = response.getOutputStream();
        os.write(buffer);
        os.flush();
        os.close();
        return RespResult.ok();

    }
}
