package com.ifacebox.vcam4j;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.ByteArrayInputStream;
import java.io.IOException;

public abstract class VCamSDK {
    public static final int VCAM_S_OK = 0;
    public static final int VCAM_FPS = 100;
    public static final int VCAM_WIDTH = 640;
    public static final int VCAM_HEIGHT = 480;
    private static VCamSDK VCAM_SDK;

    public static synchronized VCamSDK createVCamSDK() {
        if (VCAM_SDK == null) {
            try {
                VCAM_SDK = new VCamDriverSDK();
            } catch (VCamException e) {
                try {
                    VCAM_SDK = new VCamDShowSDK();
                } catch (VCamException ex) {
                    VCAM_SDK = null;
                }
            }
        }
        return VCAM_SDK;
    }

    /**
     * 检查设备是否正被其他应用程序使用。
     *
     * @return
     */
    public abstract boolean isOpened();

    /**
     * 向驱动程序发送 RGB24 格式的视频缓冲区。
     *
     * @param data
     */
    public abstract void sendFrameEx(byte[] data);

    /**
     * 开始屏幕捕获，鼠标跟踪捕获。
     */
    public abstract void captureScreen();

    /**
     * 屏幕捕获。
     *
     * @param x
     * @param y
     * @param width
     * @param height
     */
    public abstract void captureScreen(int x, int y, int width, int height);

    /**
     * 停止屏幕捕获。
     */
    public abstract void stopCaptureScreen();

    /**
     * 销毁。
     */
    public abstract void dispose();

    public void sleepFps() {
        try {
            Thread.sleep(VCAM_FPS);
        } catch (Exception e) {
        }
    }

    public byte[] getFrameEx(byte[] data) throws IOException {
        BufferedImage image = new BufferedImage(VCAM_WIDTH, VCAM_HEIGHT, BufferedImage.TYPE_3BYTE_BGR);
        Graphics g = image.getGraphics();
        try (ByteArrayInputStream bais = new ByteArrayInputStream(data)) {
            Image img = ImageIO.read(bais);
            if (img.getWidth(null) != VCAM_WIDTH || img.getHeight(null) != VCAM_HEIGHT) {
                img = img.getScaledInstance(VCAM_WIDTH, VCAM_HEIGHT, Image.SCALE_FAST);
            }
            g.drawImage(img, 0, 0, VCAM_WIDTH, VCAM_HEIGHT, 0, VCAM_HEIGHT, VCAM_WIDTH, 0, null);
        } finally {
            g.dispose();
        }
        return ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
    }
}
