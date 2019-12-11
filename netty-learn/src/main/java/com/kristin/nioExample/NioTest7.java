package com.kristin.nioExample;

import java.nio.ByteBuffer;

/**
 * @Author: Kristin
 * @Date: 2019/12/10 00:15
 * @Comment： 只读Buffer
 */
public class NioTest7 {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(10);

        for (int i = 0; i < buffer.capacity(); i++) {
            buffer.put((byte) i);
        }

        ByteBuffer readonlyBuffer = buffer.asReadOnlyBuffer();
        System.out.println(buffer.getClass());
        System.out.println(readonlyBuffer.getClass());
    }
}
