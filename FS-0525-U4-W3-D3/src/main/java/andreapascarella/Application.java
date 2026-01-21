package andreapascarella;

import andreapascarella.dao.BlogsDAO;
import andreapascarella.dao.CategoriesDAO;
import andreapascarella.dao.DocumentsDAO;
import andreapascarella.dao.UsersDAO;
import andreapascarella.entities.Blog;
import andreapascarella.entities.Category;
import andreapascarella.entities.Document;
import andreapascarella.entities.User;
import andreapascarella.exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.ArrayList;

public class Application {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("FS-0525-U4-W3-D3pu");

    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();

        UsersDAO ud = new UsersDAO(em);
        DocumentsDAO dd = new DocumentsDAO(em);
        BlogsDAO bd = new BlogsDAO(em);
        CategoriesDAO cd = new CategoriesDAO(em);

        User aldo = new User("Aldo", "Baglio");
        User giovanni = new User("Giovanni", "Storti");
        User giacomo = new User("Giacomo", "Poretti");

        //ud.saveUser(aldo);
        //ud.saveUser(giovanni);
        //ud.saveUser(giacomo);
        try {
            User aldoFromDB = ud.findById("caf42608-a1c1-42cd-89f6-bee415c398d2");

            Document aldoDoc = new Document("1234", "Italy", aldoFromDB);

            //dd.saveDocument(aldoDoc);
            System.out.println(aldoFromDB.getDocument());

        } catch (NotFoundException ex) {
            System.out.println(ex.getMessage());
        }

        try {
            User giovanniFromDB = ud.findById("ab3d9d4d-c749-477f-9b03-a72e99fbd081");

            Blog react = new Blog("React", "Bello ma non quanto Java", giovanniFromDB);

            //bd.saveBlog(react);

            Blog java = new Blog("Java", "Bellissimo", giovanniFromDB);

            //bd.saveBlog(java);

            System.out.println(giovanniFromDB.getBlogs());

        } catch (NotFoundException ex) {
            System.out.println(ex.getMessage());
        }

        Category category = new Category("Development");
        Category category1 = new Category("OOP");
        Category category2 = new Category("Frontend");
        Category category3 = new Category("Backend");
        Category category4 = new Category("Java");

        //cd.saveCategory(category);
        //cd.saveCategory(category1);
        //cd.saveCategory(category2);
        //cd.saveCategory(category3);
        //cd.saveCategory(category4);

        try {
            Blog javaFromDb = bd.findById("2a1a3d75-2cee-4f10-b790-2632f95e4c91");

            Category developmentFromDB = cd.findById("7fa9c172-cb75-4593-a888-ac8b9870c7e9");
            Category oopFromDB = cd.findById("3fba995d-026e-4097-aa52-f0f8104fd6b6");
            Category javaCatFromDB = cd.findById("a25c8427-5c10-45ec-a028-a80aec14bdbe");
            Category backendFromDB = cd.findById("2989c40f-25ba-4641-a8b7-8abbf5d85944");

            ArrayList<Category> javaCategories = new ArrayList<>();
            javaCategories.add(developmentFromDB);
            javaCategories.add(oopFromDB);
            javaCategories.add(javaCatFromDB);
            javaCategories.add(backendFromDB);

            javaFromDb.setCategories(javaCategories);

            bd.saveBlog(javaFromDb);

            System.out.println("Tutte le categorie del blog java");
            javaFromDb.getCategories().forEach(System.out::println);

            System.out.println("Tutti i blog associati alla categoria Development sono: ");
            developmentFromDB.getBlogs().forEach(System.out::println);

        } catch (NotFoundException ex) {
            System.out.println(ex.getMessage());
        }

        em.close();
        emf.close();
    }
}
