package andreapascarella.dao;

import andreapascarella.entities.Document;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class DocumentsDAO {

    private final EntityManager em;

    public DocumentsDAO(EntityManager em) {
        this.em = em;
    }

    public void saveDocument(Document newDocument) {
        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        em.persist(newDocument);

        transaction.commit();

        System.out.println("Il documento " + newDocument.getDocumentId() + " .é stato salvato correttamente");
    }
}
