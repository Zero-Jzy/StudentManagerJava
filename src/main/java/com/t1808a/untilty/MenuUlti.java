package com.t1808a.untilty;

import com.t1808a.controller.StudentController;

import java.util.Locale;
import java.util.Scanner;

public class MenuUlti {
    private StudentController controller = new StudentController();
    private Scanner scanner = new Scanner(System.in);

    public void generateStudentMenu() {
        while (true) {
            System.out.println(Mess.getMess("menu.student.title"));
            System.out.println(Mess.getMess("menu.student.create"));
            System.out.println(Mess.getMess("menu.student.print"));
            System.out.println(Mess.getMess("menu.student.edit"));
            System.out.println(Mess.getMess("menu.student.delete"));
            System.out.println(Mess.getMess("menu.student.search"));
            System.out.println(Mess.getMess("menu.student.exit"));
            System.out.println("---------------------------------");
            System.out.println(Mess.getMess("menu.student.choose"));
            String choice = scanner.nextLine();

            if (choice.equals("0")) {
                System.out.println(Mess.getMess("menu.student.bye:v"));
                break;
            }

            switch (choice) {
                case "1":
                    controller.create();
                    break;
                case "2":
                    controller.read();
                    break;
                case "3":
                    controller.update();
                    break;
                case "4":
                    controller.delete();
                    break;
                case "5":
                    controller.search();
                    break;
                default:
                    System.out.println(Mess.getMess("menu.student.errchoice"));
                    break;
            }

        }
    }

    public void generateLanguaeMenu(){
        System.out.println("Choose your language:");
        System.out.println("1: English.");
        System.out.println("2: Vietnamese.");
        System.out.println("3: 中文（繁體）.");

        String choose = scanner.nextLine();
        switch (choose){
            case "1":
                Mess.setLocale(Locale.ENGLISH);
                break;
            case "2":
                Mess.setLocale(new Locale("vi"));
                break;
            case "3":
                Mess.setLocale(Locale.CHINESE);
                break;
        }
    }
}
