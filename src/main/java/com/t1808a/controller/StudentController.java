package com.t1808a.controller;

import com.t1808a.entity.Student;
import com.t1808a.model.StudentModel;
import com.t1808a.untilty.Mess;

import java.util.ArrayList;
import java.util.Scanner;

public class StudentController {

    private StudentModel model = new StudentModel();
    private Scanner scanner = new Scanner(System.in);

    public Student enterStudent() {
        Student student = new Student();
        while (true){
            System.out.println(Mess.getMess("student.enterRollnumber"));
            student.setRollNumber(scanner.nextLine());
            if (!student.isValidRollNumber()){
                student.printErrors();
            }else break;
        }

        System.out.println(Mess.getMess("student.enterName"));
        student.setName(scanner.nextLine());
        System.out.println(Mess.getMess("student.enterDob"));
        student.setDob(scanner.nextLine());
        System.out.println(Mess.getMess("student.enterAddress"));
        student.setAddress(scanner.nextLine());
        System.out.println(Mess.getMess("student.enterPhoneNumber"));
        student.setPhone(scanner.nextLine());
        System.out.println(Mess.getMess("student.enterEmail"));
        student.setEmail(scanner.nextLine());
        return student;
    }


    public void create() {
        model.insert(enterStudent());
    }

    public boolean update() {
        System.out.println(Mess.getMess("student.enterWannaUpdate"));
        String rollNumber = scanner.nextLine();
        return model.update(rollNumber, enterStudent());
    }

    public void read(){
        printList(model.FindAll());
    }

    public void printList(ArrayList<Student> studentArrayList) {
        if (!studentArrayList.isEmpty()) {
            System.out.printf("%-7s|%-8s|%-10s|%-10s|%-10s\n", "STT", "Mã Sv", "Tên", "Địa Chỉ", "Phone");
            for (int i = 0; i < studentArrayList.size(); i++) {
                System.out.printf("%-7d|%-8s|%-10s|%-10s|%-10s\n",
                        (i + 1),
                        studentArrayList.get(i).getRollNumber(),
                        studentArrayList.get(i).getName(),
                        studentArrayList.get(i).getAddress(),
                        studentArrayList.get(i).getPhone());
            }
        } else {
            System.out.println("Không có nhân viên nào!");
        }
    }

    public void delete() {
        System.out.println(Mess.getMess("student.enterWannaDelete"));
        String rollNumber;
        while (true) {
            rollNumber = scanner.nextLine();
            Student student = model.FindById(rollNumber);
            if (student == null) {
                System.out.println("Không tìm được nhân viên bạn muốn xóa !");
                System.out.println("Chọn lại mã nhân viên muốn xóa!");
            } else {
                System.out.println("Đã tìm được nhận viên:" + student.getName() + "\nBạn có muốn xóa không? (y/n)");
                while (true) {
                    String ch = scanner.nextLine();
                    if (ch.equalsIgnoreCase("y")) {
                        if (model.delete(rollNumber)) {
                            System.out.println("Xóa thành công!");
                        } else {
                            System.err.println("Có lỗi khi xóa dữ liệu !");
                        }
                        break;
                    } else if (ch.equalsIgnoreCase("n")) {
                        System.out.println("bye");
                        break;
                    } else {
                        System.out.println("Vui lòng nhập lại lựa của bạn!");
                    }
                }
                break;

            }
        }


    }

    public void search(){
        System.out.println(Mess.getMess("student.enterWannaSearch"));
        String nameLike = scanner.nextLine();
        printList(model.FindLikeName(nameLike));

    }

}
