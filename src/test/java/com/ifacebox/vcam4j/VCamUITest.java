package com.ifacebox.vcam4j;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VCamUITest extends JFrame implements ActionListener {
    private volatile boolean vCamRunning = false;
    private volatile boolean vCamOpening = false;
    private volatile boolean vCamOpened = false;
    private JButton vCamStart;
    private JButton vCamStop;
    private JButton vCamStartFrame;
    private JButton vCamStopFrame;
    private JButton vCamStartScreen;
    private JButton vCamStopScreen;
    private Thread vCamFrameThread;
    private Thread vCamScreenThread;
    private Rectangle vCamScreen;
    private VCamSDK vCamSDK;

    public VCamUITest() {
        vCamScreen = GraphicsEnvironment.getLocalGraphicsEnvironment()
                .getDefaultScreenDevice().getDefaultConfiguration().getBounds();
        super.setTitle("VCamUITest");
        super.setDefaultCloseOperation(EXIT_ON_CLOSE);
        super.setLayout(new GridLayout(3, 2));
        super.add(vCamStart = new JButton("创建SDK"));
        super.add(vCamStop = new JButton("销毁SDK"));
        vCamStart.addActionListener(this);
        vCamStop.addActionListener(this);
        super.add(vCamStartFrame = new JButton("开始发送视频帧"));
        super.add(vCamStopFrame = new JButton("停止发送视频帧"));
        vCamStartFrame.addActionListener(this);
        vCamStopFrame.addActionListener(this);
        super.add(vCamStartScreen = new JButton("开始屏幕捕获"));
        super.add(vCamStopScreen = new JButton("停止屏幕捕获"));
        vCamStartScreen.addActionListener(this);
        vCamStopScreen.addActionListener(this);
        super.pack();
        super.setAlwaysOnTop(true);
        super.setLocationRelativeTo(null);
        super.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vCamStart && vCamSDK == null) {
            System.err.println(vCamStart.getText());
            vCamOpening = false;
            vCamOpened = false;
            try {
                vCamSDK = VCamSDK.createVCamSDK();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, e.toString());
            }
        }
        if (e.getSource() == vCamStop && vCamSDK != null) {
            System.err.println(vCamStop.getText());
            vCamRunning = false;
            if (vCamFrameThread != null) {
                vCamFrameThread.interrupt();
                vCamFrameThread = null;
            }
            if (vCamScreenThread != null) {
                vCamScreenThread.interrupt();
                vCamScreenThread = null;
            }
            vCamSDK.sendFrameEx(null);
            vCamSDK.dispose();
            vCamSDK = null;
        }
        if (e.getSource() == vCamStartFrame && vCamFrameThread == null && vCamSDK != null) {
            System.err.println(vCamStartFrame.getText());
            vCamRunning = true;
            vCamFrameThread = new Thread(() -> {
                while (vCamRunning) {
                    if (isClosed()) {
                        continue;
                    }
                    System.err.println(vCamStartFrame.getText());
                    vCamSDK.sendFrameEx(VCamServerTest.getVCamFrame());
                }
            });
            vCamFrameThread.start();
        }
        if (e.getSource() == vCamStopFrame && vCamFrameThread != null) {
            System.err.println(vCamStopFrame.getText());
            vCamRunning = false;
            vCamFrameThread.interrupt();
            vCamFrameThread = null;
        }
        if (e.getSource() == vCamStartScreen && vCamScreenThread == null && vCamSDK != null) {
            System.err.println(vCamStartScreen.getText());
            vCamRunning = true;
            vCamScreenThread = new Thread(() -> {
                while (vCamRunning) {
                    if (isClosed()) {
                        continue;
                    }
                    System.err.println(vCamStartScreen.getText());
                    vCamSDK.captureScreen(vCamScreen.x, vCamScreen.x, vCamScreen.width, vCamScreen.height);
                }
            });
            vCamScreenThread.start();
        }
        if (e.getSource() == vCamStopScreen && vCamScreenThread != null) {
            System.err.println(vCamStopScreen.getText());
            vCamSDK.stopCaptureScreen();
            vCamRunning = false;
            vCamScreenThread.interrupt();
            vCamScreenThread = null;
        }
    }

    public boolean isClosed() {
        vCamSDK.sleepFps();
        vCamOpening = vCamSDK.isOpened();
        if (this.vCamOpening) {
            if (vCamOpened != vCamOpening) {
                System.err.println("打开虚拟摄像头...");
                this.vCamOpened = this.vCamOpening;
            }
        } else {
            if (vCamOpened != vCamOpening) {
                System.err.println("关闭虚拟摄像头...");
                vCamSDK.sendFrameEx(null);
                vCamOpened = vCamOpening;
            }
            return true;
        }
        return false;
    }

    public static void main(String[] args) throws Exception {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        new VCamUITest();
    }

}
