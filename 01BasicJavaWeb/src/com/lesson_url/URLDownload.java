package com.lesson_url;

import javax.net.ssl.HttpsURLConnection;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class URLDownload {
    public static void main(String[] args) throws Exception{
        // 1. 下载地址
        URL url = new URL("http://localhost:8080/doesntexist/SecurityFile.txt");

        // 2. 连接到这个资源 HTTP
        HttpURLConnection urlConnection = (HttpsURLConnection) url.openConnection();

        InputStream inputStream = urlConnection.getInputStream();

        FileOutputStream fos = new FileOutputStream("SecurityFiole.txt");

        byte[] buffer = new byte[1024];
        int len;
        while ((len=inputStream.read(buffer)) != -1){
            fos.write(buffer,0, len); // 写出这个数据
        }

        fos.close();
        inputStream.close();
        urlConnection.disconnect(); //断开连接

    }
}
