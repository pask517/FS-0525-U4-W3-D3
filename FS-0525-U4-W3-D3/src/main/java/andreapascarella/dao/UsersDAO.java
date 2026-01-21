package andreapascarella.dao;

import andreapascarella.entities.User;
import andreapascarella.exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.UUID;

public class UsersDAO {
    private final EntityManager em;

    public UsersDAO(EntityManager em) {
        this.em = em;
    }

    public void saveUser(User newUser) {
        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        em.persist(newUser);

        transaction.commit();

        System.out.println("L'utente " + newUser.getUserId() + " .é stato salvato correttamente");
    }

    public User findById(String userId) {
        User found = em.find(User.class, UUID.fromString(userId));
        if (found == null) throw new NotFoundException(userId);
        return found;
    }

}
