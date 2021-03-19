package com.yasso.dfbb.file;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.concurrent.CountDownLatch;

/**
 * @author guochuang
 * @version 1.0
 * @date 2021/3/10 15:01
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MultiDownloadFileThread {

    private static final Logger LOGGER = LoggerFactory.getLogger(MultiDownloadFileThread.class);

    private int threadCount;
    private String sourcePath;
    private String targetPath;
    private CountDownLatch latch;

    public void execute() {
        File file = new File(sourcePath);
        int fileLength = (int) file.length();
        int blockSize = fileLength / threadCount;

        for (int i = 1; i < threadCount; i++) {
            // 第一个线程开始的位置
            int startIndex = (i - 1) * blockSize;
            int endIndex = startIndex + blockSize - 1;
            if (i == threadCount) {
                endIndex = fileLength;
            }
            LOGGER.info("线程{}下载{}字节~{}字节", i, startIndex, endIndex);
            DownLoadThread downLoadThread = new DownLoadThread(i, startIndex, endIndex);
            downLoadThread.start();
        }

    }

    class DownLoadThread extends Thread {
        private int i;
        private int startIndex;
        private int endIndex;

        public DownLoadThread(int i, int startIndex, int endIndex) {
            this.i = i;
            this.startIndex = startIndex;
            this.endIndex = endIndex;
        }

        @Override
        public void run() {
            File file = new File(sourcePath);
            FileInputStream fileInputStream = null;
            RandomAccessFile randomAccessFile = null;
            FileChannel fileChannel = null;
            FileLock fileLock = null;
            try {
                fileInputStream = new FileInputStream(file);
                fileInputStream.skip(startIndex);
                randomAccessFile = new RandomAccessFile(targetPath, "rwd");
                fileChannel = randomAccessFile.getChannel();
                while (true) {
                    try {
                        fileLock = fileChannel.tryLock();
                        break;
                    } catch (Exception ex) {
                        LOGGER.info("有其他线程正在操作该文件，当前线程休眠1000毫秒,当前进入的线程为: {}", i);
                        sleep(1000);
                    }
                }
                //随机写文件的其实位置
                randomAccessFile.seek(startIndex);
                int len = 0;
                byte[] arr = new byte[1024];
                // 获取文件片段长度
                int segLength = endIndex - startIndex + 1;
                while ((len = fileInputStream.read(arr)) != -1) {
                    if (segLength > len) {
                        segLength = segLength - len;
                        randomAccessFile.write(arr, 0, len);
                    } else {
                        randomAccessFile.write(arr, 0, segLength);
                        break;
                    }
                }
                LOGGER.info("线程{} 下载完毕", i);
                latch.countDown();
            } catch (Exception ex) {
                ex.printStackTrace();
                latch.countDown();
            } finally {
                try {
                    if (fileInputStream != null) {
                        fileInputStream.close();
                    }
                    if (randomAccessFile != null) {
                        randomAccessFile.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            super.run();
        }
    }



}
