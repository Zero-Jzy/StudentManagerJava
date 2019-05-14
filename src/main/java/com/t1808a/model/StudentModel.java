package com.t1808a.model;

import com.t1808a.entity.Student;

import java.sql.*;
import java.util.ArrayList;

public class StudentModel {

    private static Connection connection;

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager
                    .getConnection("jdbc:mysql://localhost/myschool?user=root&password=");
        } catch (SQLException e) {
            System.out.println("SQLException " + e.getMessage());

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public boolean insert(Student student) {

        try {
            PreparedStatement pstmt = connection.prepareStatement(
                    "insert into students (rollNumber, name, dob, address, phone, email) values (?,?,?,?,?,?)");
            pstmt.setString(1, student.getRollNumber());
            pstmt.setString(2, student.getName());
            pstmt.setString(3, student.getDob());
            pstmt.setString(4, student.getAddress());
            pstmt.setString(5, student.getPhone());
            pstmt.setString(6, student.getEmail());
            pstmt.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return false;
    }


    public ArrayList<Student> FindAll() {
        ArrayList<Student> students = new ArrayList<Student>();

        try {
            String SQL = "SELECT * FROM students WHERE status != -1";
            PreparedStatement pstmt = connection.prepareStatement(SQL);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Student newStudent = new Student();
                newStudent.setRollNumber(rs.getString("rollNumber"));
                newStudent.setName(rs.getString("name"));
                newStudent.setDob(rs.getString("dob"));
                newStudent.setAddress(rs.getString("address"));
                newStudent.setPhone(rs.getString("phone"));
                newStudent.setEmail(rs.getString("email"));
                students.add(newStudent);
            }
            rs.close();

            return students;
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return students;
    }

    public ArrayList<Student> FindLikeName(String name) {

        ArrayList<Student> students = new ArrayList<Student>();
        try {
            String SQL = "SELECT * FROM students WHERE name like ?";
            PreparedStatement pstmt = connection.prepareStatement(SQL);
            pstmt.setString(1, "%" + name + "%");

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Student student = new Student();
                student.setRollNumber(rs.getString("rollNumber"));
                student.setName(rs.getString("name"));
                student.setDob(rs.getString("dob"));
                student.setAddress(rs.getString("address"));
                student.setPhone(rs.getString("phone"));
                student.setEmail(rs.getString("email"));
                students.add(student);
            }
            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    public Student FindById(String id) {
        Student student = new Student();
        try {
            String SQL = "SELECT * FROM students WHERE rollNumber = ?";
            PreparedStatement pstmt = connection.prepareStatement(SQL);
            pstmt.setString(1, id);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                student.setRollNumber(rs.getString("rollNumber"));
                student.setName(rs.getString("name"));
                student.setDob(rs.getString("dob"));
                student.setAddress(rs.getString("address"));
                student.setPhone(rs.getString("phone"));
                student.setEmail(rs.getString("email"));
            }else {
                return null;
            }
            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }

    public boolean update(String rollNumber, Student obj) {
        try {

            PreparedStatement ps = connection.prepareStatement(
                    "UPDATE students SET name = ?,dob = ?, address = ?, phone = ?, email = ? WHERE rollNumber = ?");
            ps.setString(1, obj.getName());
            ps.setString(2, obj.getDob());
            ps.setString(3, obj.getAddress());
            ps.setString(4, obj.getPhone());
            ps.setString(5, obj.getEmail());
            ps.setString(6, rollNumber);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(String rollNumber) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE students SET status = -1 WHERE rollNumber = ?");
            preparedStatement.setString(1, rollNumber);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
