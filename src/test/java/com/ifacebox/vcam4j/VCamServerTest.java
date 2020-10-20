package com.ifacebox.vcam4j;

import com.alibaba.fastjson.JSON;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpServer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.util.Random;
import java.util.concurrent.Executors;

public class VCamServerTest {
    private static final Random COLOR_RANDOM = new Random();

    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(9526), 0);
        server.createContext("/ifacebox/GetOneFrameCameraVideo", (httpExchange) -> {
            Headers headers = httpExchange.getResponseHeaders();
            headers.set("Accept", "application/json");
            headers.set("Content-Type", "application/json;charset=UTF-8");
            VCamTake vCamTake = new VCamTake();
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
                vCamTake.setPhoto(baos.toByteArray());
            } finally {
                g2d.dispose();
            }
            httpExchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, JSON.toJSONBytes(vCamTake).length);
            JSON.writeJSONString(httpExchange.getResponseBody(), vCamTake);
            httpExchange.close();
        });
        server.setExecutor(Executors.newCachedThreadPool());
        server.start();
    }
}
