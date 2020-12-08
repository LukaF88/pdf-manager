package it.aruba.pdf_manager;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Date;

import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import it.aruba.pdf_manager.beans.UploadDocumentRequest;
import it.aruba.pdf_manager.beans.ChangeDocumentRequest;
import it.aruba.pdf_manager.beans.PresentationDocuments;
import it.aruba.pdf_manager.services.DocumentService;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class PdfManagerApplicationTests {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private DocumentService documentService;

	private static HttpHeaders headers = new HttpHeaders();

	static {
		headers.setBasicAuth("user", "pass");
	}

	void init() {

		// creo un botto di record, pdf in una cartella
		// e verifico la dimensione, facendo una get

		String fileName = "pdf/armadillo.pdf";
		ClassLoader classLoader = getClass().getClassLoader();

		File file = new File(classLoader.getResource(fileName).getFile());
		String path = file.getAbsolutePath();
		boolean result = Files.exists(Path.of(path));
		assert result;

		File f = new File(classLoader.getResource("./pdf/").getFile());

		// Populates the array with names of files and directories
		String[] pathnames = f.list();
		for (String pathname : pathnames) {
			createRecord(Path.of(f.getPath(), pathname));
		}
	}

	private void createRecord(Path pathname) {
		UploadDocumentRequest doc = new UploadDocumentRequest();
		doc.setFilename(pathname.getFileName().toString());
		byte[] bytes;
		try {
			bytes = Files.readAllBytes(pathname.toAbsolutePath());
			doc.setPayload(bytes);
			doc.setLastModified(new Date());
			documentService.save(doc);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	void retrieve() {

		init();

		PresentationDocuments files = doRetrieve();
		assertThat(files.getDocuments().size() > 0).isTrue();
	}

	private PresentationDocuments doRetrieve() {
		String findAllUrl = "http://localhost:" + port + "/api/retrieve";
		return this.restTemplate
				.exchange(findAllUrl, HttpMethod.GET, new HttpEntity(headers), PresentationDocuments.class).getBody();
	}

	@Test
	void update() {

		init();

		String modifyUrl = "http://localhost:" + port + "/api/update";
		ChangeDocumentRequest ch = new ChangeDocumentRequest();
		ch.setOldName("armadillo.pdf");
		ch.setNewName("zerocalcare.pdf");

		this.restTemplate.exchange(modifyUrl, HttpMethod.POST, new HttpEntity<ChangeDocumentRequest>(ch, headers),
				PresentationDocuments.class).getBody();

		PresentationDocuments res = doRetrieve();
		assertThat(res.getDocuments().stream().filter((pred) -> {
			return pred.getFilename().equals("zerocalcare.pdf");
		}).count() == 1).isTrue();
	}

	@Test
	void delete() {

		init();

		PresentationDocuments firstRetr = doRetrieve();
		String modifyUrl = "http://localhost:" + port + "/api/remove";
		ChangeDocumentRequest ch = new ChangeDocumentRequest();
		ch.setOldName("prova.pdf");
		ch.setNewName(null);

		this.restTemplate.exchange(modifyUrl, HttpMethod.POST, new HttpEntity<ChangeDocumentRequest>(ch, headers),
				PresentationDocuments.class).getBody();

		PresentationDocuments secRetr = doRetrieve();
		assertThat(firstRetr.getDocuments().size() == secRetr.getDocuments().size() + 1).isTrue();
	}

}
