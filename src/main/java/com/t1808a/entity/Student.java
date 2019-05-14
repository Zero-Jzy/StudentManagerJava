package com.t1808a.entity;

import com.t1808a.model.StudentModel;

import java.util.ArrayList;

public class Student extends Person {
    private String rollNumber;

    private ArrayList<String> errors;

    public Student(){

    }

    public Student(String rollNumber , String name, String dob, String address, String phone, String email) {
        super(name, dob, address, phone, email);
        this.rollNumber = rollNumber;
    }

    public ArrayList<String> getErrors() {
        return errors;
    }

    public void setErrors(ArrayList<String> errors) {
        this.errors = errors;
    }

    public String getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(String rollNumber) {
        this.rollNumber = rollNumber;

    }

    public boolean isValid() {
        if (this.getName().length() == 0 || this.getName() == null) {
            errors.add("Vui lòng nhập tên!");
        }

        if (errors.size() > 0) { return false; }
        return true;
    }

    public boolean isValidRollNumber() {
        StudentModel model = new StudentModel();
        this.errors = new ArrayList<String>();
        if (this.rollNumber == null || this.rollNumber.length() == 0 ) {
            errors.add("Vui lòng nhập mã sinh viên!");
        }else {
            if (model.FindById(this.rollNumber) != null){
                errors.add("Mã sinh viên đã tồn tại !!");
            }
        }
        if (errors.size() > 0) { return false; }
        return true;
    }

    public void printErrors() {
        for (String err: errors) {
            System.err.println("-"+err);
        }
    }
}
