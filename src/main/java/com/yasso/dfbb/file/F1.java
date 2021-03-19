package com.yasso.dfbb.file;

import java.util.concurrent.CountDownLatch;

/**
 * @author guochuang
 * @version 1.0
 * @date 2021/3/10 15:01
 */
public class F1 {

    public static void main(String[] args) {
        int threadSize = 4;
        String sourcePath = "D:\\taiji\\temp\\1.log";
        String targetPath = "D:\\taiji\\temp\\2.txt";
        CountDownLatch countDownLatch = new CountDownLatch(threadSize);
        MultiDownloadFileThread multiDownloadFileThread = new MultiDownloadFileThread(threadSize,
                sourcePath, targetPath, countDownLatch);
        long start = System.currentTimeMillis();
        try {
            multiDownloadFileThread.execute();
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("全部下载结束,共耗时" + (start - end) / 1000 + "s");

    }
}
