package com.ifacebox.vcam4j;

import javax.swing.*;

public class VCamUITest extends JFrame {

    public VCamUITest(){

    }
    public static void main(String[] args) throws Exception {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        new VCamUITest();
    }
}
