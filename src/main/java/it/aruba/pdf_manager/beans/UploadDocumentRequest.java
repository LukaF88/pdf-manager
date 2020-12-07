package it.aruba.pdf_manager.beans;

import java.util.Date;

public class UploadDocumentRequest {
    String filename;
    Date lastModified;
    byte[] payload;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public byte[] getPayload() {
        if (this.payload == null)
            this.payload = new byte[0];
        return payload;
    }

    public void setPayload(byte[] payload) {
        this.payload = payload;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

}