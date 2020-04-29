package models;

import utils.Utilities;

public abstract class Employee {
    /* this is the superclass Employee which stores the information about the employees.
     */

    //static fields
    private static double NORMAL_WORKWEEK = 39.5;
    private static double MIN_WAGE =9.8;


    private double hoursWorked;
    private double hourlyRate;
    private String empFirstName;
    private String empLastName;
    private String emailId;
    private double pps;
    /**
     * Employee Class manages an Employee.
     * This is an Abstract class
     *
     * @author Gowriswarup Kailas
     * @version 04/20
     */

    public Employee(double hoursWorked, double hourlyRate, String empFirstName, String empLastName, String emailId, double pps) {
        if(Utilities.validDoubleNonNegative(hoursWorked)) {
            this.hoursWorked = hoursWorked;
        }
        else if(!Utilities.validDoubleNonNegative(hoursWorked)) {
            this.hoursWorked = 0;
        }
        if(Utilities.validDoubleHourlyRate(hourlyRate)) {
            this.hourlyRate = hourlyRate;
        }
        else if(!Utilities.validDoubleHourlyRate(hourlyRate)) {
                this.hourlyRate = 9.8;
        }
        this.empFirstName = Utilities.maxChars(empFirstName,20);
        this.empLastName = Utilities.maxChars(empLastName,20);
        if(Utilities.validEmail(emailId)){
            this.emailId = emailId;
        };
        this.pps = pps;
    }

    public static double getNormalWorkweek() {
        return NORMAL_WORKWEEK;
    }

    public static void setNormalWorkweek(double normalWorkweek) {
        NORMAL_WORKWEEK = normalWorkweek;
    }

    public static double getMinWage() {
        return MIN_WAGE;
    }

    public static void setMinWage(double minWage) {
        MIN_WAGE = minWage;
    }

    public double getHoursWorked() {
        return hoursWorked;
    }

    public void setHoursWorked(double hoursWorked) {
        if(Utilities.validDoubleNonNegative(hoursWorked)) {
            this.hoursWorked = hoursWorked;
        }
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(double hourlyRate) {
        if(Utilities.validDoubleHourlyRate(hourlyRate)) {
           this.hourlyRate = hourlyRate;
        }
        else{
            if(hourlyRate!=0){

            }
            else{
            this.hourlyRate = 9.8;
            }
        }
    }

    public String getEmpFirstName() {
        return empFirstName;
    }

    public void setEmpFirstName(String empFirstName) {
        this.empFirstName = empFirstName;
    }

    public String getEmpLastName() {
        return empLastName;
    }

    public void setEmpLastName(String empLastName) {
        this.empLastName = empLastName;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        if(Utilities.validEmail(emailId)){
            this.emailId = emailId;
        };
    }

    public double getPps() {
        return pps;
    }

    public void setPps(double pps) {
        this.pps = pps;
    }

    public double getOverTime(){
        if (this.hoursWorked >= NORMAL_WORKWEEK) {
            double pay = (this.hoursWorked - NORMAL_WORKWEEK) * this.hourlyRate * 2;
            return pay;
        }
        else{
            return 0;
        }
    }

    public double getSalary(){
        if (this.hoursWorked >= NORMAL_WORKWEEK) {
            double pay = NORMAL_WORKWEEK * this.hourlyRate;
            double salary = pay + this.getOverTime();
            return salary;
        }
        else {
            double salary = this.hoursWorked * this.hourlyRate;
            return salary;
        }
    }


    public abstract double calculateSalary();




    @Override
    public String toString() {
        return "Employee{" +
                "hours Worked =" + hoursWorked +
                ", hourly Rate=" + hourlyRate +
                ", employee First Name='" + empFirstName + '\'' +
                ", employee Last Name='" + empLastName + '\'' +
                ", email Id='" + emailId + '\'' +
                ", pps number=" + pps +
                '}';
    }
}
