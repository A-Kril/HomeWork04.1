package HomeWork04.developers.controller;

import HomeWork04.developers.dao.DeveloperDAO;
import HomeWork04.developers.dao.impl.JavaIODeveloperDAOImpl;
import HomeWork04.developers.model.Developer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.List;

public class DeveloperController {


    public DeveloperController() {

    }

    public String developerById(long id) throws IOException {
        DeveloperDAO developerDAO = new JavaIODeveloperDAOImpl();
        if (developerDAO.getById(id) == null)
            return null;
        return developerDAO.getById(id).toString();
    }

    public void newDeveloper() throws IOException {
        DeveloperDAO developerDAO = new JavaIODeveloperDAOImpl();
        Developer developer = new Developer();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter id:  ");
        developer.setId(Long.parseLong(reader.readLine()));
        System.out.print("Enter firstName:  ");
        developer.setFirstName(reader.readLine());
        System.out.print("Enter lastName:  ");
        developer.setLastName(reader.readLine());
        System.out.print("Enter specialty:  ");
        developer.setSpecialty(reader.readLine());
        System.out.print("Enter salary:  ");
        developer.setSalary(Double.parseDouble(reader.readLine()));
        System.out.println();

        developerDAO.save(developer);
    }

    public void newDeveloper(long id, String firstName, String lastName, String specialty, double salary) throws IOException {
        DeveloperDAO developerDAO = new JavaIODeveloperDAOImpl();
        Developer developer = new Developer (id, firstName, lastName, specialty, salary);
        developerDAO.save(developer);
    }

    public void editDeveloper(long id) throws IOException {
        DeveloperDAO developerDAO = new JavaIODeveloperDAOImpl();
        Developer developer = developerDAO.getById(id);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("What do you want to edit?");
        System.out.println("1. First name \n"
                + "2. Last name \n"
                + "3. Specialty \n"
                + "4. Salary \n"
                + "5. Stop editing & save");
        String com = reader.readLine();
        try {
            switch (com) {
                case "1":
                    System.out.println("Enter new first name");
                    developer.setFirstName(reader.readLine());
                    developerDAO.update(developer);
                    editDeveloper(id);
                    break;
                case "2":
                    System.out.println("Enter new last name");
                    developer.setLastName(reader.readLine());
                    developerDAO.update(developer);
                    editDeveloper(id);
                    break;
                case "3":
                    System.out.println("Enter new specialty");
                    developer.setSpecialty(reader.readLine());
                    developerDAO.update(developer);
                    editDeveloper(id);
                    break;
                case "4":
                    System.out.println("Enter new salary");
                    developer.setSalary(Double.parseDouble(reader.readLine()));
                    developerDAO.update(developer);
                    editDeveloper(id);
                    break;
                case "5":
                    break;
                default:
                    System.err.println("Enter number from 1 to 4!!!\n");
                    editDeveloper(id);
                    break;
            }
        }
        catch (NumberFormatException e) {
            System.err.println("Value is not number!");
            editDeveloper(id);
        }
    }

    public boolean deleteDeveloper(Long id) throws Exception{
        DeveloperDAO developerDAO = new JavaIODeveloperDAOImpl();
        return developerDAO.remov(id);
    }

    public Collection<Developer> printDevs() {
        DeveloperDAO developerDAO = new JavaIODeveloperDAOImpl();
        List<Developer> developers = (List<Developer>) developerDAO.getAll();
        return developers;
    }
}

