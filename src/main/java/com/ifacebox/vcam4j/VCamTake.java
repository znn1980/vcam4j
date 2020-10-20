package com.ifacebox.vcam4j;

public class VCamTake {
    private byte[] photo;

    public VCamTake() {

    }

    public VCamTake(byte[] photo) {
        this.photo = photo;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }
}
