package controllers;

import java.util.ArrayList;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import models.Employee;
import models.*;

/**
 * Employee API Class is responsible for storing and managing ALL the employees entered by the user via the console.
 *
 * @author Gowriswarup Kailas
 * @version 04/20
 */
public class EmployeeAPI {
    private ArrayList<Employee> employees;

    /**
     * Constructor method for the EmployeeAPI
     */
    public EmployeeAPI() {
        employees = new ArrayList<Employee>();
    }

    /**
     * method getEmployees();
     *
     * @return the employees in the ArrayList
     */
    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    /**
     * method setEmployees(ArrayList<Employee> employees);
     *
     * @param employees used to set the employees ArrayList
     */
    public void setEmployees(ArrayList<Employee> employees) {
        this.employees = employees;
    }

    /**
     * method addEmployee(Employee employee)
     *
     * @param employee used to add the employee object to the ArrayList employees
     */
    public void addEmployee(Employee employee){
        employees.add(employee);
    }

    /**
     *method addEmployeeToDepartment(int empIndex,int deptIndex)
     *
     * @param empIndex used as an index to find the employee in the employees ArrayList
     * @param deptIndex used as an index to find the manager's index in the employees ArrayList
     * @returns true/false based on successful completion of the task
     */
    public boolean addEmployeeToDepartment(int empIndex,int deptIndex){
        if (!employees.isEmpty()){
            employees.add(empIndex, employees.get(deptIndex));
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * method getEmployee(int index)
     *
     * @param index used to find the location of the employee in the ArrayList
     * @returns the employee object found using the index. If index not valid, returns null
     */
    public Employee getEmployee(int index){
        if (index <= employees.size()){
            return employees.get(index);
        }
        else{
            return null;
        }
    }

    /**
     * method removeEmployee(String lastName)
     *
     * @param lastName used to find the employee in the employees ArrayList
     * @returns the index of the employee in the ArrayList. If none found, returns null
     */
    public Employee removeEmployee(String lastName){
        Employee matchingEmployee = null;
        int i =0;
        for(Employee emp : employees) {
            if (i == 0){
            if (emp.getEmpLastName().toUpperCase().contains(lastName.toUpperCase())) {
              matchingEmployee  = emp;
              i = 1;
            }
            }
        }
        if (matchingEmployee.equals(null)){
            return null;
        }
        else{
            employees.remove(matchingEmployee);
            return matchingEmployee;
        }
    }

    /**
     * method numberOfEmployees()
     *
     * @returns the number of employees present in the employees ArrayList
     */
    public int numberOfEmployees(){
        return employees.size();
    }

    /**
     * method listEmployees()
     *
     * @returns the list of employees (Full names) from the employees ArrayList
     */
    public String listEmployees(){
        if (employees.isEmpty()){
            return "No employees Stored";
        }
        else{
            String str = "";
            for (int i = 0; i < employees.size(); i++){
                str += "(" + (i+1) + ")" + getEmployee(i).getEmpFirstName() +" " + getEmployee(i).getEmpLastName() + "\n";
            }
            return str;
        }
    }

    /**
     * method listManagerEmployees(Manager manager)
     *
     * @param manager used to find the employees of the manager's department
     * @returns a list of employees in the managers department. If the manager does not exist,
     * return a message stating this manager does not exist. If the manager does exist,
     * but has no employees in his/her department, then returns a message stating this manager
     * has no employees in his/her department.
     */
    public String listManagerEmployees(Manager manager){
        String matchingManagerEmployee = "";
        for(Employee employee : employees) {
            if (employee.getEmpFirstName().toUpperCase().contains(manager.getEmpFirstName().toUpperCase()) && employee.getEmpLastName().toUpperCase().contains(manager.getEmpLastName().toUpperCase())) {
               if (manager.getEmployees() != null) {
                   matchingManagerEmployee += employee + "\n";
               }
               else{
                   matchingManagerEmployee = "This manager has no employees in his/her department";
               }
            }
        }

        if (matchingManagerEmployee.equals("")){
            return "This Manager does not exist";
        }
        else{
            return matchingManagerEmployee;
        }
    }

    /**
     * method listManagerEmployees()
     *
     * @returns a list of employees who are managers.If there are no managers in the System,
     * No Managers in the system should be returned.
     */
    public String listManagerEmployees(){
        String matchingManagerEmployees = "";
        for (Employee emp : employees){
            if (emp instanceof Manager){
                matchingManagerEmployees += emp + "\n";
            }
        }
        if (matchingManagerEmployees.equals("")){
            return "No Managers in the system";
        }
        else{
            return matchingManagerEmployees;
        }
    }

    /**
     * method searchEmployees(String secondName)
     *
     * @param secondName used to find the index of an employee object in the employees ArrayList.
     * @returns the index of the employee object if found, else returns -1
     */
    public int searchEmployees(String secondName){
        int index=0;
        if (!employees.isEmpty()) {
            for (int i = 0; i < employees.size();i++) {
                 if (employees.get(i).getEmpLastName().toUpperCase().contains(secondName.toUpperCase())) {
                     index = i;
                     i = employees.size();
                 }
            }
            return index;
        }
        else{
            return -1;
        }
    }

    /**
     * method totalSalariesOwed()
     *
     * @returns total salary if employees ArraryList is not empty. else, returns 0
     */
    public double totalSalariesOwed(){

        if(!employees.isEmpty()){
            double totalSalary = 0;
            for (Employee emp: employees){
                totalSalary += emp.calculateSalary();
            }
            return totalSalary;
        }
        else{
            return 0;
        }
    }

    /**
     * method avgSalariesOwed()
     * @returns the average salary of the total objects in the employees ArrayList
     */
    public double avgSalariesOwed(){
        return this.totalSalariesOwed()/employees.size();
    }

    /**
     * method employeeWithHighestPay()
     *
     * @returns the employee object with the highest pay amongst the employees ArrayLists. If no objects in ArrayList,
     * null is returned
     */
    public Employee employeeWithHighestPay(){
        Employee highest = employees.get(0);
        for (Employee emp : employees){
            if(emp.calculateSalary() > highest.calculateSalary()){
                highest = emp;
            }
        }
        return highest;
    }

    /**
     * method sortEmployeesByFirstName()
     *
     * sorts the employee objects in the Arraylist in ascending order of first name
     */
    public void sortEmployeesByFirstName(){
        for (int i = employees.size() -1; i >= 0; i--)
        {
            int highestIndex = 0;
            for (int j = 0; j <= i; j++)
            {
                if (employees.get(j).getEmpFirstName().toUpperCase().compareTo(employees.get(highestIndex).getEmpFirstName().toUpperCase() ) > 0) {
                    highestIndex = j;
                }
            }
            swapEmployees(employees, i, highestIndex);
        }
    }

    /**
     * method sortEmployeesBySecondName()
     *
     * sorts the employee objects in the Arraylist in ascending order of second name
     */
    public void sortEmployeesBySecondName(){
        for (int i = employees.size() -1; i >= 0; i--)
        {
            int highestIndex = 0;
            for (int j = 0; j <= i; j++)
            {
                if (employees.get(j).getEmpLastName().toUpperCase().compareTo(employees.get(highestIndex).getEmpLastName().toUpperCase() ) > 0) {
                    highestIndex = j;
                }
            }
            swapEmployees(employees, i, highestIndex);
        }
    }

    /**
     * method sortEmployeesByHourlyRate()
     *
     * sorts the employee objects in the Arraylist in ascending order of hourly Rates
     */
    public void sortEmployeesByHourlyRate(){
        for (int i = employees.size() -1; i >= 0; i--)
        {
            int highestIndex = 0;
            for (int j = 0; j <= i; j++)
            {
                if (employees.get(j).getHourlyRate() > employees.get(highestIndex).getHourlyRate()) {
                    highestIndex = j;
                }
            }
            swapEmployees(employees, i, highestIndex);
        }
    }

    /**
     * method swapEmployees(ArrayList<Employee> employees, int i, int j)
     *
     * used as a helper method in the sorting methods previously declared
     *
     * @param employees used to identify the arrayList
     * @param i used to determine the index of the first value to find its location in the ArrayList
     * @param j used to determine the index of the second value to find its location in the ArrayList
     */
    public void swapEmployees(ArrayList<Employee> employees, int i, int j){
            Employee lower = employees.get(i);
            Employee higher = employees.get(j);

            employees.set(i,higher);
            employees.set(j,lower);
    }

    /**
     * load() and save() methods
     *
     * @throws Exception if errors are found
     * saves the file to an employees.xml file and loads from the same
     */
    @SuppressWarnings("unchecked")
    public void load() throws Exception
    {
        XStream xstream = new XStream(new DomDriver());
        ObjectInputStream is = xstream.createObjectInputStream(new FileReader("employees.xml"));
        employees = (ArrayList<Employee>) is.readObject();
        is.close();
    }

    public void save() throws Exception
    {
        XStream xstream = new XStream(new DomDriver());
        ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("employees.xml"));
        out.writeObject(employees);
        out.close();
    }

}
