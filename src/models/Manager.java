package models;

import java.util.ArrayList;

/**
 * Manager Class is a concrete class which extends Employee and implement the class for a manager.
 *
 * @author Gowriswarup Kailas
 * @version 04/20
 */

public class Manager extends Employee{

    private ArrayList<Employee> deptEmployees;

    public Manager(double hoursWorked, double hourlyRate, String empFirstName, String empLastName, String emailId, double pps) {
        super(hoursWorked, hourlyRate, empFirstName, empLastName, emailId, pps);
        deptEmployees = new ArrayList<Employee>();
    }

    public ArrayList<Employee> getEmployees() {
        return deptEmployees;
    }

    public void setEmployees(ArrayList<Employee> employees) {
        this.deptEmployees = employees;
    }

    public void addDeptEmployee(Employee employee){
        deptEmployees.add(employee);
    }

    public int getTotalDeptEmployees(){
        return deptEmployees.size();
    }

    public Employee getDeptEmployee(int index){
        return deptEmployees.get(index);
    }

    public void removeDeptEmployee(int index){
        deptEmployees.remove(index);
    }


    @Override
    public double calculateSalary(){
        double managerSalary = getSalary();
        for (Employee employee : deptEmployees) {
            managerSalary += (employee.getSalary() * 0.01);
        }
        return managerSalary;
    }
}
