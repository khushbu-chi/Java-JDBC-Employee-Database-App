import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
public class employee_manager {
    public static void main(String[] args) {
        System.out.println("Welcome to Employee Management System");
        int choice;
        do{
            System.out.println("Choose your Option");
            System.out.println("1. Add Employee");
            System.out.println("2. Update Employee");
            System.out.println("3. View Employee");
            System.out.println("4. Delete Employee");
            System.out.println("5. EXIT");
            Scanner sc = new Scanner(System.in);
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter Employee Name: ");
                    Scanner scanner = new Scanner(System.in);
                    String employeeName = scanner.nextLine();

                    System.out.print("Enter Department Name: ");
                    String employeedepartment = scanner.nextLine();

                    System.out.print("Enter Employee Salary: ");
                    Scanner in = new Scanner(System.in);
                    Double salary = in.nextDouble();
                    addEmployee(employeeName, employeedepartment, salary);
                    break;
                case 2:
                    System.out.println("Enter Employee ID: to update");
                    Scanner input = new Scanner(System.in);
                    int employeeID = input.nextInt();

                   // System.out.print("Enter Employee Name: to update");
                   // Scanner sc1 = new Scanner(System.in);
                   // String employeeName1 = sc1.nextLine();

                   // System.out.print("Enter Department Name: to update");
                   // Scanner sc2 = new Scanner(System.in);
                  //  String department1 = sc2.nextLine();

                    System.out.print("Enter Employee Salary: to update");
                    Scanner sc3 = new Scanner(System.in);
                    Double salary1 = sc3.nextDouble();

                    updateEmployee(employeeID, salary1);
                    break;
                case 3:
                    System.out.println("Employee List:");
                    viewEmployees();
                    break;
                case 4:
                    System.out.println("Enter Employee ID: to delete");
                    Scanner input2 = new Scanner(System.in);
                    int id = input2.nextInt();
                    deleteEmployee(id);
                    break;
                case 5:
                    System.out.println("Exiting Employee Management System");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid Option");
                    break;
            }
        }while(choice != 5);
    }

    public static void addEmployee(String employeeName, String employeedepartment, double salary) {
        String query = "INSERT INTO employees (name, department, salary) VALUES (?, ?, ?)";
        try (Connection connection = dbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, employeeName);
            preparedStatement.setString(2, employeedepartment);
            preparedStatement.setDouble(3, salary);
            // Execute the query
            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Employee added successfully!");
            } else {
                System.out.println("Employee adding failed!");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void viewEmployees() {
        String selectQuery = "SELECT * FROM employees";
        try (Connection connection = dbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
             ResultSet resultSet = preparedStatement.executeQuery(selectQuery)) {
            System.out.println("Employee List:");
            while (resultSet.next()) {
                System.out.println("ID: " + resultSet.getInt("id") +
                        ", Name: " + resultSet.getString("name") +
                        ", Department: " + resultSet.getString("department") +
                        ", Salary: " + resultSet.getDouble("salary"));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateEmployee(int employeeID, double salary) {
        // Example: Update an employee's salary
        String updateQuery = "UPDATE employees SET salary = ? WHERE id = ?";

        try (Connection connection = dbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {

            // Set parameters for the query
            preparedStatement.setDouble(1,salary); // Set salary
            preparedStatement.setInt(2, employeeID);        // Set employee ID

            // Execute the update
            int rowsUpdated = preparedStatement.executeUpdate();

            // Check the result
            if (rowsUpdated > 0) {
                System.out.println("Record updated successfully!");
            } else {
                System.out.println("No record found with the given ID.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void deleteEmployee(int employeeID) {
        String deleteQuery = "DELETE FROM employees WHERE id = ?";
        try (Connection connection = dbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
            preparedStatement.setInt(1, employeeID); // Delete employee with ID 1
            //     preparedStatement.executeUpdate();
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Employee deleted successfully!");
            } else {
                System.out.println("Employee not found!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

