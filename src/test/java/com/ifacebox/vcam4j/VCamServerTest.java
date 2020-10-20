package com.ifacebox.vcam4j;

import com.alibaba.fastjson.JSON;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.util.Random;
import java.util.concurrent.Executors;

public class VCamServerTest implements HttpHandler {
    public static final String VCAM_SERVER_URL = "/ifacebox/GetOneFrameCameraVideo";
    public static final int VCAM_SERVER_PORT = 9526;
    private static final Random COLOR_RANDOM = new Random();
    private HttpServer server;

    public synchronized void start() {
        try {
            if (server == null) {
                System.err.println("开始模拟摄像头视频流！");
                server = HttpServer.create(new InetSocketAddress(VCAM_SERVER_PORT), 0);
                server.createContext(VCAM_SERVER_URL, this);
                server.setExecutor(Executors.newCachedThreadPool());
                server.start();
            }
        } catch (Exception e) {
            System.err.println(String.format("模拟摄像头视频流异常！%s", e.toString()));
        }
    }

    public synchronized void stop() {
        if (server != null) {
            System.err.println("停止模拟摄像头视频流！");
            server.stop(0);
            server = null;
        }
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        Headers headers = httpExchange.getResponseHeaders();
        headers.set("Accept", "application/json");
        headers.set("Content-Type", "application/json;charset=UTF-8");
        VCamTake vCamTake = new VCamTake(getVCamFrame());
        httpExchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, JSON.toJSONBytes(vCamTake).length);
        JSON.writeJSONString(httpExchange.getResponseBody(), vCamTake);
        httpExchange.close();
    }

    public static byte[] getVCamFrame() {
        BufferedImage bi = new BufferedImage(640, 480, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = (Graphics2D) bi.createGraphics();
        g2d.setColor(new Color(COLOR_RANDOM.nextInt(255), COLOR_RANDOM.nextInt(255), COLOR_RANDOM.nextInt(255)));
        g2d.setBackground(new Color(COLOR_RANDOM.nextInt(255), COLOR_RANDOM.nextInt(255), COLOR_RANDOM.nextInt(255)));
        g2d.fillRect(0, 0, 640, 480);
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font(null, Font.BOLD, 38));
        g2d.drawString("虚拟摄像头测试", 100, 100);
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            ImageIO.write(bi, "jpg", baos);
            return baos.toByteArray();
        } catch (Exception e) {
            return null;
        } finally {
            g2d.dispose();
        }
    }

    public static void main(String[] args) {
        new VCamServerTest().start();
    }

}
