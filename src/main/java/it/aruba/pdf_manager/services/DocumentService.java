package it.aruba.pdf_manager.services;

import it.aruba.pdf_manager.beans.UploadDocumentRequest;
import it.aruba.pdf_manager.beans.ChangeDocumentRequest;
import it.aruba.pdf_manager.entities.Document;
import it.aruba.pdf_manager.entities.DocumentPayload;

public interface DocumentService {
    Iterable<Document> findAll();

    Document save(UploadDocumentRequest request);

    Document delete(ChangeDocumentRequest request);

    Document modify(ChangeDocumentRequest request);

    DocumentPayload retrievePayload(String id);

    Document findById(String filename);
}
