package HomeWork04.developers.dao.impl;

import HomeWork04.developers.dao.DeveloperDAO;
import HomeWork04.developers.model.Developer;

import java.io.*;
import java.util.*;

public class JavaIODeveloperDAOImpl implements DeveloperDAO{
    private String sep = System.getProperty("file.separator");
    private static String user = System.getProperty("user.dir");
    private File file = new File(user + sep + "Developers.dat");

    @Override
    public void save(Developer developer) {
        String developerToString = "";
        if (getById(developer.getId()).getId() != null) {
            System.out.println("Developer with this ID is already in base");
            return;
        }

        developerToString += developer.getId() + ",";
        developerToString += developer.getFirstName() + ",";
        developerToString += developer.getLastName() + ",";
        developerToString += developer.getSpecialty() + ",";
        developerToString += developer.getSalary() + "\n";

        try(FileOutputStream fileOutputStream = new FileOutputStream(file, true)) {
            fileOutputStream.write(developerToString.getBytes());
            fileOutputStream.flush();
            fileOutputStream.close();

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
            //remov(developer);
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

                if ((Long.parseLong(arrayOfSplitDeveloper[0])) == id) {
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

        return developer;
    }

    @Override
    public boolean remov(Long id) throws Exception{
        Developer developer = new Developer();
        File tempFile = new File(new File(user) + ".tmp");

        if (!file.isFile()) {
            throw new Exception("Input file is missing");
        }

        try (BufferedReader bufferReader = new BufferedReader(new FileReader(file));
             BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(tempFile))) {
            String line;
            while ((line = bufferReader.readLine()) != null) {
                if (line.isEmpty()){
                    continue;
                }

                String[] arrayOfSplitDeveloper = line.split(",");

                if ((Long.parseLong(arrayOfSplitDeveloper[0])) != id){
                developer.setId(Long.parseLong(arrayOfSplitDeveloper[0]));
                developer.setFirstName(arrayOfSplitDeveloper[1]);
                developer.setLastName(arrayOfSplitDeveloper[2]);
                developer.setSpecialty(arrayOfSplitDeveloper[3]);
                developer.setSalary(Double.parseDouble(arrayOfSplitDeveloper[4]));
                    bufferedWriter.write(line);
                    bufferedWriter.newLine();
                    bufferedWriter.flush();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (!file.delete()) {
            throw new Exception("Could not delete file");
        }
        if (!tempFile.renameTo(file)) {
            throw new Exception("Could not rename file");
        }

        return false;
    }

    @Override
    public Collection<Developer> getAll() {
        List<Developer> developers = new ArrayList<>();

        try {
            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);

            String buffer;
            while ((buffer = bufferedReader.readLine()) != null) {
                Developer tempDeveloper = new Developer();
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
        }return developers;
    }

}
