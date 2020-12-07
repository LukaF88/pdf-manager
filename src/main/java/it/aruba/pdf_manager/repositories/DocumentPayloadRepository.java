package it.aruba.pdf_manager.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.aruba.pdf_manager.entities.DocumentPayload;

@Repository("documentPayloadRepository")
public interface DocumentPayloadRepository extends CrudRepository<DocumentPayload, String> {

}