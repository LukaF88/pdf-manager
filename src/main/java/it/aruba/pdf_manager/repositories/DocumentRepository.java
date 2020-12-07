package it.aruba.pdf_manager.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.aruba.pdf_manager.entities.Document;

@Repository("documentRepository")
public interface DocumentRepository extends CrudRepository<Document, String> {

}