package controllers;

import java.util.ArrayList;
import java.util.Scanner;

import models.Employee;
import models.Manager;
import models.SalesWorker;
import utils.ScannerInput;

/**
 *The responsibility of the Driver Class is to run the app and perform I/O with the user.
 *
 * @author Gowriswarup Kailas
 * @version 04/20
 */
public class Driver {

    private Scanner input = new Scanner(System.in);
    private EmployeeAPI empAPI;


    public static void main(String[] args) {
        Driver app = new Driver();
        app.run();
    }

    public Driver() {
        input = new Scanner(System.in);
        empAPI = new EmployeeAPI();
    }

    private int mainMenu(){
        System.out.println("Employee Menu");
        System.out.println("---------");
        System.out.println("  1) Add an employee (Manager)");
        System.out.println("  2) Add an employee (Sales Worker)");
        System.out.println("  3) Add an employee (Admin Worker)");
        System.out.println("  4) Add an existing employee to a department");
        System.out.println("  5) Delete an employee");
        System.out.println("  --------------------");
        System.out.println("  6) Find the total of the salaries owed to all the employees");
        System.out.println("  7) Find the average of the salaries owed to all the employees");
        System.out.println("  8) Print the employee with the highest pay");
        System.out.println("  --------------------");
        System.out.println("  9)  List all the employees in the company in ascending alphabetical order (First name)");
        System.out.println("  10) List all the employees in the company in ascending alphabetical order (Second name)");
        System.out.println("  11) List all the employees in the company in ascending alphabetical order (Hourly rate)");
        System.out.println("  --------------------");
        System.out.println("  12) Search the system for an employee by second name");
        System.out.println("  13) Search the system for an employee within a given manager's department ");
        System.out.println("  --------------------");
        System.out.println("  14) Save to XML");
        System.out.println("  15) Load from XML");
        System.out.println("  --------------------");
        System.out.println("  0) Exit");
        return ScannerInput.readNextInt("==>>");

    }


    private void run() {
        int option = mainMenu();
        while (option != 0) {

            switch (option) {
                case 1:
                    addManager();
                    break;
                case 2:
                    addSalesWorker();
                    break;
                case 3:
                    addAdminWorker();
                    break;
                case 4:
                    addEmpToManager();
                    break;
                case 5:
                    deleteEmpByName();
                    break;
                case 6:
                    empAPI.totalSalariesOwed();
                    break;
                case 7:
                    empAPI.avgSalariesOwed();
                    break;
                case 8:
                    empAPI.employeeWithHighestPay();
                    break;
                case 9:
                    empAPI.sortEmployeesByFirstName();
                    break;
                case 10:
                    empAPI.sortEmployeesBySecondName();
                    break;
                case 11:
                    empAPI.sortEmployeesByHourlyRate();
                    break;
                case 12:
                    searchBySecondName();
                    break;
                case 13:
                    searchByDept();
                    break;
                case 14:
                    try {
                        empAPI.save();
                    } catch (Exception e) {
                        System.err.println("Error writing to file: " + e);
                    }
                    break;
                case 15:
                    try {
                        empAPI.load();
                    } catch (Exception e) {
                        System.err.println("Error reading from file: " + e);
                    }
                    break;
                default:
                    System.out.println("Invalid option entered: " + option);
                    break;

            }

            //pause the program so that the user can read what we just printed to the terminal window
            ScannerInput.validNextLine("\nPress any key to continue...");

            //display the main menu again
            option = mainMenu();
        }
    }

    public ArrayList<Employee> getEmployees(){
        return empAPI.getEmployees();
    }

    private void setup(){

    }


    public void addManager(){
        String firstName = ScannerInput.validNextLine("Enter the first name of the Manager: ");
        String lastName = ScannerInput.validNextLine("Enter the last name of the Manager: ");
        double hoursWorked = ScannerInput.readNextDouble("Enter the total amount of hours worked by the manager: ");
        double hourlyRate = ScannerInput.readNextDouble("Enter the hourly rate for the manager: ");
        String emailId = ScannerInput.validNextLine("Enter the manager's email id: ");
        double pps = ScannerInput.readNextDouble("Enter the manager's pps number: ");
        empAPI.addEmployee(new Manager(hoursWorked, hourlyRate, firstName, lastName, emailId, pps));
    }

    public void addSalesWorker(){
        String firstName = ScannerInput.validNextLine("Enter the first name of the sales worker: ");
        String lastName = ScannerInput.validNextLine("Enter the last name of the sales worker: ");
        double hoursWorked = ScannerInput.readNextDouble("Enter the total amount of hours worked by the sales worker: ");
        double hourlyRate = ScannerInput.readNextDouble("Enter the hourly rate for the sales worker: ");
        String emailId = ScannerInput.validNextLine("Enter the sales worker's email id: ");
        double pps = ScannerInput.readNextDouble("Enter the sales worker's pps number: ");
        double bonus = ScannerInput.readNextDouble("Enter the sales worker's bonus percentage (between 0 and 20% inclusive): ");

        empAPI.addEmployee(new SalesWorker(hoursWorked, hourlyRate, firstName, lastName, emailId, pps, bonus));
    }

    public void addAdminWorker(){
        String firstName = ScannerInput.validNextLine("Enter the first name of the admin worker: ");
        String lastName = ScannerInput.validNextLine("Enter the last name of the admin worker: ");
        double hoursWorked = ScannerInput.readNextDouble("Enter the total amount of hours worked by the admin worker: ");
        double hourlyRate = ScannerInput.readNextDouble("Enter the hourly rate for the admin worker: ");
        String emailId = ScannerInput.validNextLine("Enter the admin worker's email id: ");
        double pps = ScannerInput.readNextDouble("Enter the admin worker's pps number: ");
        double bonus = ScannerInput.readNextDouble("Enter the admin worker's fixed bonus amount: ");

        empAPI.addEmployee(new SalesWorker(hoursWorked, hourlyRate, firstName, lastName, emailId, pps, bonus));
    }

    public void addEmpToManager(){
        if(empAPI.numberOfEmployees() != 0){
            System.out.println(empAPI.listManagerEmployees());
            int deptIndex = ScannerInput.readNextInt("Enter the index of the manager you would like to add the employee to: ");

            System.out.println(empAPI.listEmployees());
            int empIndex = ScannerInput.readNextInt("Enter the index of the employee you would like to add to the aforementioned manager: ");

            empAPI.addEmployeeToDepartment(empIndex, deptIndex);
        }
    }

    public void deleteEmpByName(){
        String secondName = ScannerInput.validNextLine("Enter the second name of the employee you wish to remove: ");
        empAPI.removeEmployee(secondName);
    }

    public void searchBySecondName(){
        String secondName = ScannerInput.validNextLine("Enter the second name of the employee you wish to remove: ");
        if (empAPI.searchEmployees(secondName) != -1) {
            empAPI.getEmployee(empAPI.searchEmployees(secondName));
        }
        else{
            System.out.println("Sorry, the employee you are searching for does not exist");
        }
    }

    public void searchByDept(){
        if(empAPI.numberOfEmployees() != 0) {
            System.out.println(empAPI.listManagerEmployees());
            int deptIndex = ScannerInput.readNextInt("Enter the index of the manager's department you would like to search for the employee in: ");

            Manager manager = (Manager) empAPI.getEmployee(deptIndex);
            System.out.println(empAPI.listManagerEmployees(manager));
        }
    }

}