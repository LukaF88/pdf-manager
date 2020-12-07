package it.aruba.pdf_manager;

import javax.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import it.aruba.pdf_manager.services.DocumentService;

@SpringBootApplication
public class PdfManagerApplication extends SpringBootServletInitializer {

	@Autowired
	DocumentService documentService;

	@Autowired
	ServletContext context;

	public static void main(String[] args) {
		SpringApplication.run(PdfManagerApplication.class, args);
	}

}
