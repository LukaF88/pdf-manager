package it.aruba.pdf_manager.beans;

import java.util.Date;

public class PresentationDocument {

    private String filename;
    private long size;
    private String thumbBase64;

    private Date lastModified;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getThumbBase64() {
        return thumbBase64;
    }

    public void setThumbBase64(String thumbBase64) {
        this.thumbBase64 = thumbBase64;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

}
