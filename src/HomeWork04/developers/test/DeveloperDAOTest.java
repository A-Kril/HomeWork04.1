package HomeWork04.developers.test;

import HomeWork04.developers.dao.DeveloperDAO;
import HomeWork04.developers.dao.impl.JavaIODeveloperDAOImpl;
import HomeWork04.developers.model.Developer;

import java.util.List;

public class DeveloperDAOTest {
    public static void main(String[] args) {
        DeveloperDAO developerDAO = new JavaIODeveloperDAOImpl();

        List<Developer> developers = (List<Developer>) developerDAO.getAll();
        System.out.println(developers);
    }
}
