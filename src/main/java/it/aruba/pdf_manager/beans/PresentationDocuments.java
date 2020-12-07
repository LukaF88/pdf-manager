package it.aruba.pdf_manager.beans;

import java.util.ArrayList;
import java.util.List;

public class PresentationDocuments {

    private List<PresentationDocument> documents;

    public List<PresentationDocument> getDocuments() {
        if (documents == null)
            this.documents = new ArrayList<>();
        return documents;
    }

    public void setDocuments(List<PresentationDocument> documents) {
        this.documents = documents;
    }

}
