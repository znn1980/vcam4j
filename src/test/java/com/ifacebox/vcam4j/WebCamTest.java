package com.ifacebox.vcam4j;

import com.github.sarxos.webcam.*;
import com.github.sarxos.webcam.ds.javacv.JavaCvDriver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WebCamTest extends JFrame implements ActionListener, WebcamListener, WebcamDiscoveryListener {
    private WebcamPanel webCamPanel;
    private JPanel webCamDevicePanel;
    private JComboBox<String> webCamCombo;
    private JButton webCamOpen;
    private JButton webCamClose;
    private Webcam webcam;

    static {
        //Webcam.setDriver(new JavaCvDriver());
    }

    public WebCamTest() {
        super.setTitle("WebCamTest");
        super.setDefaultCloseOperation(EXIT_ON_CLOSE);
        super.setLayout(new BorderLayout());
        super.add(webCamPanel = new WebcamPanel(webcam = Webcam.getDefault(), WebcamResolution.VGA.getSize(), false), BorderLayout.CENTER);
        webCamPanel.setFPSDisplayed(true);
        super.add(webCamDevicePanel = new JPanel(), BorderLayout.SOUTH);
        webCamDevicePanel.setLayout(new FlowLayout());
        webCamDevicePanel.add(webCamCombo = new JComboBox());
        webCamDevicePanel.add(webCamOpen = new JButton("打开"));
        webCamOpen.addActionListener(this);
        webCamDevicePanel.add(webCamClose = new JButton("关闭"));
        webCamClose.addActionListener(this);
        super.pack();
        super.setAlwaysOnTop(true);
        super.setLocationRelativeTo(null);
        super.setVisible(true);
        Webcam.addDiscoveryListener(this);
        for (Webcam webcam : Webcam.getWebcams()) {
            webCamCombo.addItem(webcam.getName());
        }
        webcam.addWebcamListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == webCamOpen) {
            webCamPanel.stop();
            webcam.removeWebcamListener(this);
            webcam = Webcam.getWebcams().get(webCamCombo.getSelectedIndex());
            webcam.addWebcamListener(this);
            super.remove(webCamPanel);
            super.add(webCamPanel = new WebcamPanel(webcam, WebcamResolution.VGA.getSize(), false), BorderLayout.CENTER);
            super.pack();
            webCamPanel.setFPSDisplayed(true);
            webCamPanel.start();
        }
        if (e.getSource() == webCamClose) {
            webCamPanel.stop();
        }
    }

    @Override
    public void webcamOpen(WebcamEvent we) {
        System.err.println("打开摄像头（" + we.getSource().getName() + "）");
        webCamOpen.setEnabled(false);
        webCamClose.setEnabled(true);
    }

    @Override
    public void webcamClosed(WebcamEvent we) {
        System.err.println("关闭摄像头（" + we.getSource().getName() + "）");
        webCamOpen.setEnabled(true);
        webCamClose.setEnabled(false);
    }

    @Override
    public void webcamDisposed(WebcamEvent we) {
        System.err.println("Disposed摄像头（" + we.getSource().getName() + "）");
    }

    @Override
    public void webcamImageObtained(WebcamEvent we) {
    }

    @Override
    public void webcamFound(WebcamDiscoveryEvent event) {
        System.err.println("Found摄像头（" + event.getWebcam().getName() + "）");
        webCamCombo.addItem(event.getWebcam().getName());
    }

    @Override
    public void webcamGone(WebcamDiscoveryEvent event) {
        System.err.println("Gone摄像头（" + event.getWebcam().getName() + "）");
        webCamCombo.removeItem(event.getWebcam().getName());
    }

    public static void main(String[] args) throws Exception {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        SwingUtilities.invokeLater(() -> {
            new WebCamTest();
        });
    }


}
