package andreapascarella.dao;

import andreapascarella.entities.Blog;
import andreapascarella.exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.UUID;

public class BlogsDAO {
    private final EntityManager em;

    public BlogsDAO(EntityManager em) {
        this.em = em;
    }

    public void saveBlog(Blog newBlog) {
        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        em.persist(newBlog);

        transaction.commit();

        System.out.println("Il blog " + newBlog.getBlogId() + " .é stato salvato correttamente");
    }

    public Blog findById(String blogId) {
        Blog found = em.find(Blog.class, UUID.fromString(blogId));
        if (found == null) throw new NotFoundException(blogId);
        return found;
    }
}
