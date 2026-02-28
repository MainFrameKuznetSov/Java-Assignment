package com.ManagementSystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class StudentManagementSystem 
{
    static String URL="jdbc:mysql://localhost:3306/trialschema";
    static String USER="root";
    static String PASSWORD="AdiMondal@2003";

    public static void main(String[] args) 
    {
        Scanner sc=new Scanner(System.in);
        int choice;
        while(true)
        {
            System.out.println("\n===== Student Management System =====");
            System.out.println("1. Add Student");
            System.out.println("2. Update Student Marks");
            System.out.println("3. Delete Student");
            System.out.println("4. View All Students");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            choice=sc.nextInt();

            switch(choice) 
            {
                case 1:
                    addStudent(sc);
                    break;
                case 2:
                    updateStudent(sc);
                    break;
                case 3:
                    deleteStudent(sc);
                    break;
                case 4:
                    viewAllStudents();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    //Add Student
    public static void addStudent(Scanner sc) {

        String sql="insert Student (id,name,marks) values (?,?,?)";

        try(Connection con=DriverManager.getConnection(URL,USER,PASSWORD);
             PreparedStatement ps=con.prepareStatement(sql)) 
        {
            System.out.print("Enter ID:- ");
            int id=sc.nextInt();
            sc.nextLine();

            System.out.print("Enter Name:- ");
            String name = sc.nextLine();

            System.out.print("Enter Marks:- ");
            double marks = sc.nextDouble();

            ps.setInt(1,id);
            ps.setString(2,name);
            ps.setDouble(3,marks);

            ps.executeUpdate();
            System.out.println("Student added successfully!");

        } 
        catch (SQLException e) 
        {
            System.out.println("Error adding student: " + e.getMessage());
        }
    }

    //Update Student Marks
    public static void updateStudent(Scanner sc) 
    {
        String sql = "update Student set marks = ? where id = ?";

        try (Connection con = DriverManager.getConnection(URL,USER,PASSWORD);
             PreparedStatement ps = con.prepareStatement(sql)) {

            System.out.print("Enter Student ID:- ");
            int id = sc.nextInt();

            System.out.print("Enter New Marks:- ");
            double marks = sc.nextDouble();

            ps.setDouble(1,marks);
            ps.setInt(2,id);

            int rows = ps.executeUpdate();

            if(rows>0)
                System.out.println("Student updated successfully!");
            else
                System.out.println("Student not found!");

        } 
        catch (SQLException e) 
        {
            System.out.println("Error updating student: " + e.getMessage());
        }
    }

    //Delete Student
    public static void deleteStudent(Scanner sc) 
    {

        String sql = "delete from Student where id = ?";

        try (Connection con = DriverManager.getConnection(URL,USER,PASSWORD);
             PreparedStatement ps = con.prepareStatement(sql)) {

            System.out.print("Enter Student ID:- ");
            int id = sc.nextInt();

            ps.setInt(1,id);

            int rows = ps.executeUpdate();

            if (rows>0)
                System.out.println("Student deleted successfully!");
            else
                System.out.println("Student not found!");

        } 
        catch (SQLException e) 
        {
            System.out.println("Error deleting student: " + e.getMessage());
        }
    }

    //View All Students
    public static void viewAllStudents() 
    {

        String sql = "select * from Student";

        try (Connection con = DriverManager.getConnection(URL,USER,PASSWORD);
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            System.out.println("\n Students");

            while (rs.next()) 
            {
                System.out.println(
                        "ID: " + rs.getInt("id") +
                        ", Name: " + rs.getString("name") +
                        ", Marks: " + rs.getDouble("marks"));
            }

        } 
        catch (SQLException e) 
        {
            System.out.println("Error fetching students: " + e.getMessage());
        }
    }
}
