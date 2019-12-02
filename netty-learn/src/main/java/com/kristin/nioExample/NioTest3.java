package com.kristin.nioExample;

import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Author: Kristin
 * @Date: 2019/12/3 00:58
 * @Comment：
 */
public class NioTest3 {
    public static void main(String[] args) throws Exception {
        FileOutputStream fileOutputStream = new FileOutputStream("/Users/lihang/Downloads/test2.txt");
        FileChannel channel = fileOutputStream.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(512);
        byte[] msg = "hello world welcome,nihao".getBytes();

        for (int i = 0; i < msg.length; i++) {
            buffer.put(msg[i]);
        }

        buffer.flip();

        channel.write(buffer);

        fileOutputStream.close();
    }
}
