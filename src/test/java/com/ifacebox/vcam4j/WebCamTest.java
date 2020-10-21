package com.ifacebox.vcam4j;

import com.github.sarxos.webcam.*;
import com.github.sarxos.webcam.ds.javacv.JavaCvDriver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class WebCamTest extends JFrame implements ActionListener, WebcamListener, WebcamDiscoveryListener {
    private WebcamPanel webCamPanel;
    private JPanel webCamsPanel;
    private WebcamPicker webCamPicker;
    private JButton webCamOpen;
    private JButton webCamClose;
    private Webcam webCam;

    static {
        //Webcam.setDriver(new JavaCvDriver());
    }

    public WebCamTest(List<Webcam> webCams) {
        super.setTitle("WebCamTest");
        super.setDefaultCloseOperation(EXIT_ON_CLOSE);
        super.setLayout(new BorderLayout());
        super.setPreferredSize(WebcamResolution.VGA.getSize());
        super.add(webCamPanel = new WebcamPanel(webCam = webCams.get(0), WebcamResolution.VGA.getSize(), false), BorderLayout.CENTER);
        super.add(webCamsPanel = new JPanel(), BorderLayout.SOUTH);
        webCamsPanel.setLayout(new FlowLayout());
        webCamsPanel.add(webCamPicker = new WebcamPicker(webCams));
        webCamsPanel.add(webCamOpen = new JButton("打开"));
        webCamOpen.addActionListener(this);
        webCamOpen.setEnabled(true);
        webCamsPanel.add(webCamClose = new JButton("关闭"));
        webCamClose.addActionListener(this);
        webCamClose.setEnabled(false);
        super.pack();
        super.setAlwaysOnTop(true);
        super.setLocationRelativeTo(null);
        super.setVisible(true);
        Webcam.addDiscoveryListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == webCamOpen) {
            webCamPanel.stop();
            webCam.removeWebcamListener(this);
            webCam = webCamPicker.getSelectedWebcam();
            webCam.addWebcamListener(this);
            super.remove(webCamPanel);
            super.add(webCamPanel = new WebcamPanel(webCam, WebcamResolution.VGA.getSize(), false), BorderLayout.CENTER);
            super.pack();
            webCamPanel.setFPSDisplayed(true);
            webCamPanel.setDisplayDebugInfo(true);
            webCamPanel.setImageSizeDisplayed(true);
            webCamPanel.start();
        }
        if (e.getSource() == webCamClose) {
            webCamPanel.stop();
        }
    }

    @Override
    public void webcamOpen(WebcamEvent we) {
        System.err.println("打开摄像头（" + we.getSource().getName() + "）");
        webCamPicker.setEnabled(false);
        webCamOpen.setEnabled(false);
        webCamClose.setEnabled(true);
    }

    @Override
    public void webcamClosed(WebcamEvent we) {
        System.err.println("关闭摄像头（" + we.getSource().getName() + "）");
        webCamPicker.setEnabled(true);
        webCamOpen.setEnabled(true);
        webCamClose.setEnabled(false);
    }

    @Override
    public void webcamDisposed(WebcamEvent we) {
        System.err.println("销毁摄像头（" + we.getSource().getName() + "）");
    }

    @Override
    public void webcamImageObtained(WebcamEvent we) {
    }

    @Override
    public void webcamFound(WebcamDiscoveryEvent event) {
        System.err.println("发现摄像头（" + event.getWebcam().getName() + "）");
        webCamPicker.addItem(event.getWebcam());
    }

    @Override
    public void webcamGone(WebcamDiscoveryEvent event) {
        System.err.println("丢失摄像头（" + event.getWebcam().getName() + "）");
        webCamPicker.removeItem(event.getWebcam());
    }

    public static void main(String[] args) throws Exception {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        SwingUtilities.invokeLater(() -> {
            try {
                new WebCamTest(Webcam.getWebcams());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
                System.exit(0);
            }
        });
    }

}
