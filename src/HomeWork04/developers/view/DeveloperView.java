package HomeWork04.developers.view;

import HomeWork04.developers.controller.DeveloperController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DeveloperView {

    private DeveloperController controller;

    public DeveloperView() {

    }

    public void startMenu() throws Exception{
        System.out.println("========== MENU ========\n"
                + "1. Create developer;\n"
                + "2. Edit developer;\n"
                + "3. View developer;\n"
                + "4. Delete developer\n"
                + "5. View developers\n"
                + "6. Stop program");
        logicMenu();
    }

    public void logicMenu() throws Exception{
        controller = new DeveloperController();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            String com = reader.readLine();
            long id;
            switch (com) {
                case "1":
                    controller.newDeveloper();
                    startMenu();
                    break;
                case "2":
                    System.out.print("Enter developers ID:  ");
                    id = Integer.parseInt(reader.readLine());
                    controller.editDeveloper(id);
                    startMenu();
                    break;
                case "3":
                    System.out.print("Enter developers ID:  ");
                    id = Integer.parseInt(reader.readLine());
                    String res = controller.developerById(id);
                    if (res != null)
                        System.out.println("Developer found:  " + res);
                    else {
                        System.out.print("Developer was not found:  ");
                        System.err.println("ID is not exist!!");
                        Thread.sleep(100);
                    }
                    startMenu();
                    break;
                case "4":
                    System.out.print("Enter developers ID:  ");
                    id = Integer.parseInt(reader.readLine());
                    if(controller.deleteDeveloper(id))
                        System.err.println("Developer was deleted.");
                    else {
                        System.out.print("Developer was not deleted:  ");
                        System.err.println("ID is not exist!!");
                        //System.err.println("Developer was deleted.");
                    }
                    startMenu();
                    break;
                case "5":
                    System.out.println(controller.printDevs());
                    startMenu();
                    break;
                case "6":
                    System.err.println("Stop. Program terminated.");
                    break;
                default:
                    System.err.println("ENTER NUMBER FROM 1 TO 5!!!\n");
                    startMenu();
                    break;
            }
        }
        catch (IOException c) {
            System.err.println("VALUE IS NOT NUMBER!!!222");
            startMenu();
        }
        catch (InterruptedException d) {
        }
        catch (NumberFormatException a) {
            System.err.println("VALUE IS NOT NUMBER!!!111");
            startMenu();
        }
    }
}