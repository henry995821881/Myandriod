package com.henry.util;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class StreamTool {

	/**
     * 从输入流获取数据
     * @param inputStream
     * @return
     * @throws Exception
     */
    public static byte[] readInputStream(InputStream inputStream) throws Exception{
        byte[] buffer = new byte[1024];
        int len = -1;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        while((len = inputStream.read(buffer)) != -1){
            outputStream.write(buffer, 0, len);
        }
        outputStream.close();
        inputStream.close();
        return outputStream.toByteArray();
    }
}
