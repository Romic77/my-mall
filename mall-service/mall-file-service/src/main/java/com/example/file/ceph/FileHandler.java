package com.example.file.ceph;

import org.javaswift.joss.model.Container;
import org.javaswift.joss.model.StoredObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: chenq
 * @Description:
 * @Date: Created in 2021-06-09 10:11
 */
@Component
public class FileHandler {
    @Autowired
    private Container container;

    /**
     * 文件上传
     */
    public void upload(String fileName, byte[] buffer) {
        //获取容器
        StoredObject object = container.getObject(fileName);
        //文件上传
        object.uploadObject(buffer);
    }

    /**
     * 文件下载
     */
    public byte[] download(String fileName){
        //获取容器
        StoredObject object = container.getObject(fileName);
        //下载
        return object.downloadObject();
    }
}
