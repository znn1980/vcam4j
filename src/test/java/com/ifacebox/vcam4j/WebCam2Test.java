package com.ifacebox.vcam4j;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.videoInputLib.videoInput;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

public class WebCam2Test extends JFrame implements Runnable, ActionListener {
    private static final int WEBCAM_WIDTH = 640;
    private static final int WEBCAM_HEIGHT = 480;
    private boolean isOpened = false;
    private JLabel webCam;
    private JPanel webCamPanel;
    private JComboBox<WebCamDevice> webCamCombo;
    private JButton webCamOpen;
    private JButton webCamClose;
    private Thread webCamThread;
    private WebCamDevice webCamDevice;
    private BytePointer webCamBuffer;
    private videoInput vi;

    public WebCam2Test() {
        super.setTitle("WebCamTest");
        super.setDefaultCloseOperation(EXIT_ON_CLOSE);
        super.setLayout(new BorderLayout());
        super.add(webCam = new JLabel(), BorderLayout.CENTER);
        webCam.setPreferredSize(new Dimension(WEBCAM_WIDTH, WEBCAM_HEIGHT));
        webCam.setBackground(Color.BLACK);
        webCam.setOpaque(true);
        super.add(webCamPanel = new JPanel(), BorderLayout.SOUTH);
        webCamPanel.setLayout(new FlowLayout());
        webCamPanel.add(webCamCombo = new JComboBox());
        webCamPanel.add(webCamOpen = new JButton("打开"));
        webCamOpen.addActionListener(this);
        webCamPanel.add(webCamClose = new JButton("关闭"));
        webCamClose.addActionListener(this);
        super.pack();
        super.setAlwaysOnTop(true);
        super.setLocationRelativeTo(null);
        super.setVisible(true);
        videoInput.setVerbose(false);
        for (int i = 0; i < videoInput.listDevices(); i++) {
            webCamCombo.addItem(new WebCamDevice(i, videoInput.getDeviceName(i).getString()));
        }
    }


    @Override
    public void run() {
        vi = new videoInput();
        isOpened = vi.setupDevice(webCamDevice.getId(), WEBCAM_WIDTH, WEBCAM_HEIGHT);
        webCamBuffer = new BytePointer(vi.getSize(webCamDevice.getId()));
        if (!isOpened) {
            JOptionPane.showMessageDialog(this, "打开摄像头（" + webCamDevice.getName() + "）失败！");
        } else {
            System.err.println("打开摄像头（" + webCamDevice.getName() + "）");
        }
        while (isOpened) {
            if (!vi.isFrameNew(webCamDevice.getId()) || !vi.getPixels(webCamDevice.getId(), webCamBuffer)) {
                continue;
            }
            BufferedImage bgr = new BufferedImage(WEBCAM_WIDTH, WEBCAM_HEIGHT, BufferedImage.TYPE_3BYTE_BGR);
            byte[] data = ((DataBufferByte) bgr.getRaster().getDataBuffer()).getData();
            System.arraycopy(webCamBuffer.getStringBytes(), 0, data, 0, data.length);
            BufferedImage rgb = new BufferedImage(WEBCAM_WIDTH, WEBCAM_HEIGHT, BufferedImage.TYPE_3BYTE_BGR);
            Graphics g = rgb.getGraphics();
            g.drawImage(bgr, 0, 0, WEBCAM_WIDTH, WEBCAM_HEIGHT, 0, WEBCAM_HEIGHT, WEBCAM_WIDTH, 0, null);
            g.dispose();
            webCam.setIcon(new ImageIcon(rgb));
        }
        System.err.println("关闭摄像头（" + webCamDevice.getName() + "）");
        vi.stopDevice(webCamDevice.getId());
        vi = null;
        webCamThread = null;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == webCamOpen) {
            if (webCamThread == null) {
                webCamDevice = (WebCamDevice) webCamCombo.getSelectedItem();
                webCamThread = new Thread(this);
                webCamThread.setDaemon(true);
                webCamThread.start();
            }
        }
        if (e.getSource() == webCamClose) {
            if (webCamThread != null) {
                isOpened = false;
            }
        }
    }

    static class WebCamDevice {
        private int id;
        private String name;

        public WebCamDevice(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return id + "." + name;
        }
    }

    public static void main(String[] args) throws Exception {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        new WebCam2Test();
    }

}
