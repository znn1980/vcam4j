package com.ifacebox.vcam4j;

import com.e2esoft.vcam.ds.ClassFactory;
import com.e2esoft.vcam.ds.IVCamRenderer;
import com.e2esoft.vcam.ds.IVCamSDK;
import com.e2esoft.vcam.ds.IVCamSource;

public class VCamDShowSDK extends VCamSDK {
    private IVCamSDK vCamSDK;
    private IVCamSource vCamSource;
    private IVCamRenderer vCamRenderer;

    public VCamDShowSDK() {
        try {
            this.vCamSDK = ClassFactory.createVCamSDK();
            this.vCamSource = ClassFactory.createVCamSource();
            this.vCamRenderer = ClassFactory.createVCamRenderer();
        } catch (Exception e) {
            this.dispose();
            throw new VCamException("创建虚拟摄像头SDK失败！", e);
        }
    }

    @Override
    public boolean isOpened() {
        if (this.vCamSource != null) {
            return this.vCamSource.isOpened() == VCAM_S_OK;
        }
        return false;
    }

    @Override
    public void sendFrameEx(byte[] data) {
        if (this.vCamRenderer != null) {
            if (data == null) {
                this.vCamRenderer.playBuffer24(new byte[0], 0, 0);
            } else {
                try {
                    this.vCamRenderer.playBuffer24(this.getFrameEx(data), VCAM_WIDTH, VCAM_HEIGHT);
                } catch (Exception e) {
                }
            }
        }
    }

    @Override
    public void captureScreen() {
        if (this.vCamSource != null) {
            this.vCamSource.captureScreen();
        }
    }

    @Override
    public void captureScreen(int x, int y, int width, int height) {
        if (this.vCamSource != null) {
            this.vCamSource.captureScreenFixed(x, y, width, height);
        }
    }

    @Override
    public void stopCaptureScreen() {
        if (this.vCamSource != null) {
            this.vCamSource.stopCaptureScreen();
        }
    }

    @Override
    public void dispose() {
        if (this.vCamSDK != null) {
            this.vCamSDK.dispose();
            this.vCamSDK = null;
        }
        if (this.vCamSource != null) {
            this.vCamSource.dispose();
            this.vCamSource = null;
        }
        if (this.vCamRenderer != null) {
            this.vCamRenderer.dispose();
            this.vCamRenderer = null;
        }
    }
}
