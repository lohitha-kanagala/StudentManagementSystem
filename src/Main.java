import java.sql.*;
import java.util.Scanner;

public class Main {

    static final String URL =
            "jdbc:mysql://localhost:3306/studentdb";

    static final String USER = "root";

    static final String PASSWORD = "root123";


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println("\n===== STUDENT MANAGEMENT SYSTEM =====");

            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Exit");

            System.out.print("Enter Choice: ");

            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    addStudent(sc);
                    break;

                case 2:
                    viewStudents();
                    break;

                case 3:
                    updateStudent(sc);
                    break;

                case 4:
                    deleteStudent(sc);
                    break;

                case 5:
                    System.out.println("Thank You!");
                    System.exit(0);

                default:
                    System.out.println("Invalid Choice");
            }
        }
    }


    // ADD STUDENT

    public static void addStudent(Scanner sc) {

        try {

            Connection con = DriverManager.getConnection(
                    URL, USER, PASSWORD);

            System.out.print("Enter Name: ");
            sc.nextLine();
            String name = sc.nextLine();

            System.out.print("Enter Department: ");
            String dept = sc.nextLine();

            System.out.print("Enter Marks: ");
            int marks = sc.nextInt();

            String query =
                    "INSERT INTO students(name, department, marks) VALUES(?,?,?)";

            PreparedStatement pst =
                    con.prepareStatement(query);

            pst.setString(1, name);
            pst.setString(2, dept);
            pst.setInt(3, marks);

            pst.executeUpdate();

            System.out.println("Student Added Successfully!");

            con.close();

        } catch (Exception e) {

            System.out.println(e);
        }
    }


    // VIEW STUDENTS

    public static void viewStudents() {

        try {

            Connection con = DriverManager.getConnection(
                    URL, USER, PASSWORD);

            String query = "SELECT * FROM students";

            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(query);

            System.out.println("\nID\tNAME\tDEPARTMENT\tMARKS");

            while (rs.next()) {

                System.out.println(
                        rs.getInt("id") + "\t"
                                + rs.getString("name") + "\t"
                                + rs.getString("department") + "\t\t\t"
                                + rs.getInt("marks")
                );
            }

            con.close();

        } catch (Exception e) {

            System.out.println(e);
        }
    }


    // UPDATE STUDENT

    public static void updateStudent(Scanner sc) {

        try {

            Connection con = DriverManager.getConnection(
                    URL, USER, PASSWORD);

            System.out.print("Enter Student ID: ");
            int id = sc.nextInt();

            System.out.print("Enter New Marks: ");
            int marks = sc.nextInt();

            String query =
                    "UPDATE students SET marks=? WHERE id=?";

            PreparedStatement pst =
                    con.prepareStatement(query);

            pst.setInt(1, marks);
            pst.setInt(2, id);

            pst.executeUpdate();

            System.out.println("Student Updated Successfully!");

            con.close();

        } catch (Exception e) {

            System.out.println(e);
        }
    }


    // DELETE STUDENT

    public static void deleteStudent(Scanner sc) {

        try {

            Connection con = DriverManager.getConnection(
                    URL, USER, PASSWORD);

            System.out.print("Enter Student ID: ");
            int id = sc.nextInt();

            String query =
                    "DELETE FROM students WHERE id=?";

            PreparedStatement pst =
                    con.prepareStatement(query);

            pst.setInt(1, id);

            pst.executeUpdate();

            System.out.println("Student Deleted Successfully!");

            con.close();

        } catch (Exception e) {

            System.out.println(e);
        }
    }
}