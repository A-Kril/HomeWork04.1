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
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Developer developer = new Developer();
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

        //developerDAO.update(developer);
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
                //+ "4. Experience \n"
                + "5. Salary \n"
                + "6. Stop editing & save");
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
//                case "4":
//                    System.out.println("Enter new experience");
//                    //developer.setExperience(Integer.parseInt(reader.readLine()));
//                    developerDAO.save(developer);
//                    editDeveloper(id);
//                    break;
                case "5":
                    System.out.println("Enter new salary");
                    developer.setSalary(Double.parseDouble(reader.readLine()));
                    developerDAO.update(developer);
                    editDeveloper(id);
                    break;
                case "6":
                    break;
                default:
                    System.err.println("ENTER NUMBER FROM 1 TO 5!!!\n");
                    editDeveloper(id);
                    break;
            }
        }
        catch (NumberFormatException e) {
            System.err.println("VALUE IS NOT NUMBER!!!");
            editDeveloper(id);
        }
    }

    public boolean deleteDeveloper(Long id) throws Exception{
        DeveloperDAO developerDAO = new JavaIODeveloperDAOImpl();
        //Developer developer = new Developer();
        return developerDAO.remov(id);
        //return false;
    }

    public Collection<Developer> printDevs() {
        DeveloperDAO developerDAO = new JavaIODeveloperDAOImpl();
        List<Developer> developers = (List<Developer>) developerDAO.getAll();
        return developers;
        //return developerDAO.getAll();
        //return developerDAO.toString();
    }
}

