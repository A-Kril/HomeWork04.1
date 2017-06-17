package HomeWork04.developers.dao.impl;

import HomeWork04.developers.dao.DeveloperDAO;
import HomeWork04.developers.model.Developer;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class JavaIODeveloperDAOImpl implements DeveloperDAO{
    private String sep = System.getProperty("file.separator");
    private String user = System.getProperty("user.dir");
    private File file = new File(user + sep + "Developers.dat");

    @Override
    public void save(Developer developer) {
        String developerToString = "";

        developerToString += developer.getId() + ",";
        developerToString += developer.getFirstName() + ",";
        developerToString += developer.getLastName() + ",";
        developerToString += developer.getSpecialty() + ",";
        developerToString += developer.getSalary() + "\n";

        try(FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            fileOutputStream.write(developerToString.getBytes());

            System.out.println("Developer: " + developer + " successfully saved.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Developer developer) {

        if (developer.getId() == null) {
            System.out.println("Developer not found...");
            return;
        }else {
            remov(developer);
            save(developer);
        }
    }

    @Override
    public Developer getById(Long id) {
        Developer developer = new Developer();

        try {
            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);

            String buffer;
            while ((buffer = bufferedReader.readLine()) != null) {
                if (buffer.isEmpty()) {
                    continue;
                }

                String[] arrayOfSplitDeveloper = buffer.split(",");

                if ((Integer.parseInt(arrayOfSplitDeveloper[0])) == id) {
                    developer.setId(Long.parseLong(arrayOfSplitDeveloper[0]));
                    developer.setFirstName(arrayOfSplitDeveloper[1]);
                    developer.setLastName(arrayOfSplitDeveloper[2]);
                    developer.setSpecialty(arrayOfSplitDeveloper[3]);
                    developer.setSalary(Double.parseDouble(arrayOfSplitDeveloper[4]));

                    return developer;
                }
            }

            bufferedReader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

//        System.out.println("Developer: " + developer + " successfully loaded.");
        return developer;
    }

    @Override
    public void remov(Developer developer) {
        try{

            FileReader reader = new FileReader(file);
            //реализовать метод удаления девелопера
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Collection<Developer> getAll() {
        List<Developer> developers = new ArrayList<>();
        Developer tempDeveloper = new Developer();

        try {
            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);

            String buffer;
            while ((buffer = bufferedReader.readLine()) != null) {
                if (buffer.isEmpty()) {
                    continue;
                }

                String[] arrayOfSplitDeveloper = buffer.split(",");

                tempDeveloper.setId(Long.parseLong(arrayOfSplitDeveloper[0]));
                tempDeveloper.setFirstName(arrayOfSplitDeveloper[1]);
                tempDeveloper.setLastName(arrayOfSplitDeveloper[2]);
                tempDeveloper.setSpecialty(arrayOfSplitDeveloper[3]);
                tempDeveloper.setSalary(Double.parseDouble(arrayOfSplitDeveloper[4]));

                developers.add(tempDeveloper);
            }

            bufferedReader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return developers;
    }
}
