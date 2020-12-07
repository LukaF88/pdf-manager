package it.aruba.pdf_manager.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.aruba.pdf_manager.beans.UploadDocumentRequest;
import it.aruba.pdf_manager.beans.ChangeDocumentRequest;
import it.aruba.pdf_manager.entities.Document;
import it.aruba.pdf_manager.entities.DocumentPayload;
import it.aruba.pdf_manager.repositories.DocumentPayloadRepository;
import it.aruba.pdf_manager.repositories.DocumentRepository;
import it.aruba.pdf_manager.utils.ThumbGenerator;

@Service("documentService")
public class DocumentServiceImpl implements DocumentService {

    private static Logger logger = LoggerFactory.getLogger(DocumentServiceImpl.class);

    @Autowired
    DocumentRepository documentRepository;

    @Autowired
    DocumentPayloadRepository payloadRepository;

    @Override
    public Iterable<Document> findAll() {
        logger.info("finding all");
        return documentRepository.findAll();
    }

    @Override
    public synchronized Document save(UploadDocumentRequest d) {

        DocumentPayload payload = new DocumentPayload();
        payload.setFilename(d.getFilename());
        payload.setPayload(d.getPayload());
        payloadRepository.save(payload);

        Document document = new Document();
        document.setFilename(d.getFilename());
        document.setThumbBase64(ThumbGenerator.generateThumbnailAsBase64(d.getPayload()));
        document.setLastModified(new java.sql.Date(d.getLastModified().getTime()));
        document.setSize(payload.getPayload().length);

        logger.info("saving object");
        return documentRepository.save(document);
    }

    @Override
    public Document delete(ChangeDocumentRequest d) {
        Document document = new Document();
        document.setFilename(d.getOldName());
        payloadRepository.deleteById(d.getOldName());
        logger.info("deleting object");
        documentRepository.delete(document);
        return document;
    }

    @Override
    public DocumentPayload retrievePayload(String id) {
        logger.info("retriving payload");
        return payloadRepository.findById(id).get();
    }

    @Override
    public Document findById(String filename) {
        return documentRepository.findById(filename).get();
    }

    @Override
    public Document modify(ChangeDocumentRequest request) {
        if (request.getOldName() == null || request.getNewName() == null)
            return null;
        if (request.getOldName().equals(request.getNewName()))
            return null;

        DocumentPayload payload = payloadRepository.findById(request.getOldName()).get();
        payloadRepository.delete(payload);
        payload.setFilename(request.getNewName());
        payloadRepository.save(payload);

        Document document = documentRepository.findById(request.getOldName()).get();
        documentRepository.delete(document);
        document.setFilename(request.getNewName());
        logger.info("modifying object");

        return documentRepository.save(document);
    }
}
