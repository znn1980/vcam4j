package com.ifacebox.vcam4j;

import com.e2esoft.vcam.ClassFactory;
import com.e2esoft.vcam.IVCamRenderer;

public class VCamDriverSDK extends VCamSDK {
    private IVCamRenderer vCamRenderer;

    public VCamDriverSDK() {
        try {
            this.vCamRenderer = ClassFactory.createVCamRenderer();
            this.vCamRenderer.setFlip(0);
        } catch (Exception e) {
            throw new VCamException("创建虚拟摄像头SDK失败！", e);
        }finally {
            this.dispose();
        }
    }


    @Override
    public boolean isOpened() {
        if (this.vCamRenderer != null) {
            return this.vCamRenderer.isOpened() == VCAM_S_OK;
        }
        return false;
    }

    @Override
    public void sendFrameEx(byte[] data) {
        if (this.vCamRenderer != null) {
            if (data == null) {
                this.vCamRenderer.sendFrameEx(new byte[0], 0, 0);
            } else {
                try {
                    this.vCamRenderer.sendFrameEx(super.getFrameEx(data), VCAM_WIDTH, VCAM_HEIGHT);
                } catch (Exception e) {
                }
            }
        }
    }

    @Override
    public void captureScreen() {
        if (this.vCamRenderer != null) {
            this.vCamRenderer.captureScreen(0, 0, 0, 0);
        }
    }

    @Override
    public void stopCaptureScreen() {
    }

    @Override
    public void dispose() {
        if (this.vCamRenderer != null) {
            this.vCamRenderer.dispose();
            this.vCamRenderer = null;
        }
    }
}
