package com.kristin.nioExample;

import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Author: Kristin
 * @Date: 2019/12/3 00:48
 * @Comment：
 */
public class NioTest2 {
    public static void main(String[] args) throws Exception {
        FileInputStream fileInputStream = new FileInputStream("/Users/lihang/Downloads/test.txt");
        FileChannel fileChannel = fileInputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(512);
        fileChannel.read(byteBuffer);

        byteBuffer.flip();

        while (byteBuffer.remaining() > 0) {
            byte b = byteBuffer.get();
            System.out.println("Character:" + (char) b);
        }
        fileInputStream.close();
    }
}
