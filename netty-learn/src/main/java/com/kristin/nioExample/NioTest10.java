package com.kristin.nioExample;

import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

/**
 * @Author: Kristin
 * @Date: 2019/12/11 22:29
 * @Comment： 文件锁：共享锁、排他锁
 */
public class NioTest10 {
    public static void main(String[] args) throws Exception {
        RandomAccessFile randomAccessFile = new RandomAccessFile("/Users/lihang/Downloads/test.txt", "rw");
        FileChannel fileChannel = randomAccessFile.getChannel();
        FileLock fileLock = fileChannel.lock(3, 6, true);  // 从文件第3个字节开始，锁6个字节长度，true表示共享锁，false表示排他锁
        System.out.println("valid: " + fileLock.isValid());
        System.out.println("lock type: " + fileLock.isShared());
        fileLock.release();
        randomAccessFile.close();
    }
}
