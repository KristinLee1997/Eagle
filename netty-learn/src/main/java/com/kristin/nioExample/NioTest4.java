package com.kristin.nioExample;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Author: Kristin
 * @Date: 2019/12/9 23:11
 * @Comment：
 */
public class NioTest4 {
    public static void main(String[] args) throws IOException {
        FileInputStream fileInputStream = new FileInputStream("/Users/lihang/Downloads/test2.txt");
        FileOutputStream fileOutputStream = new FileOutputStream("/Users/lihang/Downloads/test_write.txt");

        FileChannel inputChannel = fileInputStream.getChannel();
        FileChannel outChannel = fileOutputStream.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(1024);

        while (true) {
            buffer.clear();
            int read = inputChannel.read(buffer);

            if (read == -1) {
                break;
            }
            buffer.flip();
            outChannel.write(buffer);
        }
        inputChannel.close();
        outChannel.close();
    }
}
