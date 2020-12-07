package it.aruba.pdf_manager.utils;

import java.io.IOException;
import java.util.Calendar;

import org.springframework.web.multipart.MultipartFile;

import it.aruba.pdf_manager.beans.PresentationDocument;
import it.aruba.pdf_manager.beans.PresentationDocuments;
import it.aruba.pdf_manager.beans.UploadDocumentRequest;
import it.aruba.pdf_manager.entities.Document;

public class Adapter {

    public static PresentationDocuments adapt(Iterable<Document> docs) {
        PresentationDocuments result = new PresentationDocuments();
        docs.forEach(doc -> {
            result.getDocuments().add(adapt(doc));
        });
        return result;
    }

    public static PresentationDocument adapt(Document doc) {
        PresentationDocument d = new PresentationDocument();
        d.setFilename(doc.getFilename());
        d.setLastModified(doc.getLastModified());
        d.setThumbBase64(doc.getThumbBase64());
        d.setSize(doc.getSize());

        return d;
    }

    public static UploadDocumentRequest toUploadDocumentRequest(MultipartFile file, String lastModified) {
        byte[] byteArr;
        UploadDocumentRequest udr = new UploadDocumentRequest();
        try {
            byteArr = file.getBytes();
        } catch (IOException e) {
            return null;
        }

        udr.setFilename(file.getOriginalFilename());
        udr.setPayload(byteArr);
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(Long.valueOf(lastModified));
        udr.setLastModified(c.getTime());
        return udr;
    }

}