package it.aruba.pdf_manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import it.aruba.pdf_manager.beans.UploadDocumentRequest;
import it.aruba.pdf_manager.beans.ChangeDocumentRequest;
import it.aruba.pdf_manager.beans.PresentationDocument;
import it.aruba.pdf_manager.beans.PresentationDocuments;
import it.aruba.pdf_manager.entities.Document;
import it.aruba.pdf_manager.services.DocumentService;
import it.aruba.pdf_manager.utils.Adapter;

@CrossOrigin(origins = "*")
@RequestMapping("/api")
@RestController
public class DocumentController {

	@Autowired
	DocumentService documentService;

	@GetMapping("/retrieve")
	public PresentationDocuments retrieveAll() {
		return Adapter.adapt(documentService.findAll());
	}

	@PostMapping("/update")
	public PresentationDocument update(@RequestBody ChangeDocumentRequest doc) {
		return Adapter.adapt(documentService.modify(doc));
	}

	@PostMapping("/remove")
	public PresentationDocument remove(@RequestBody ChangeDocumentRequest doc) {
		return Adapter.adapt(documentService.delete(doc));
	}

	@GetMapping(value = "/download/{filename}", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<Resource> downloadPayload(@PathVariable String filename) {
		String contentType = "application/pdf;charset=UTF-8";
		byte[] b = documentService.retrievePayload(filename).getPayload();
		Resource resource = new ByteArrayResource(b);
		return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType)).contentLength(b.length)
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=\"" + filename + ".pdf\"").body(resource);
	}

	@PostMapping("/upload")
	public ResponseEntity<PresentationDocument> uploadFile(@RequestParam("file") MultipartFile file,
			@RequestParam("date") String lastModified) {

		PresentationDocument response = new PresentationDocument();
		try {
			UploadDocumentRequest udr = Adapter.toUploadDocumentRequest(file, lastModified);
			Document result = documentService.save(udr);

			response.setFilename(result.getFilename());
			response.setLastModified(result.getLastModified());

			return ResponseEntity.status(HttpStatus.OK).body(response);
		} catch (Exception e) {
			// message = "Could not upload the file: " + file.getOriginalFilename() + "!";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(response);
		}
	}

}
