package com.kristin.nioExample;

import java.nio.IntBuffer;
import java.security.SecureRandom;

/**
 * @Author: Kristin
 * @Date: 2019/12/2 23:53
 * @Comment：
 */
public class NioTest1 {
    public static void main(String[] args) {
        IntBuffer buffer = IntBuffer.allocate(10);

        for (int i = 0; i < buffer.capacity(); ++i) {
            int randomnumber = new SecureRandom().nextInt(20);
            buffer.put(randomnumber);
        }

        buffer.flip();

        while(buffer.hasRemaining()){
            System.out.println(buffer.get());
        }
    }
}
