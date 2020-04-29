package models;

import utils.Utilities;

public class AdminWorker extends Employee {

    private double bonus;

    /**
     * Admin Worker Class is a concrete class which extends Employee and implement the class for an Admin Worker.
     *
     * @author Gowriswarup Kailas
     * @version 04/20
     */

    public AdminWorker(double hoursWorked, double hourlyRate, String empFirstName, String empLastName, String emailId, double pps, double bonus) {
        super(hoursWorked, hourlyRate, empFirstName, empLastName, emailId, pps);
        if(Utilities.validDoubleNonNegative(bonus)) {
            this.bonus = bonus;
        }
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        if(Utilities.validDoubleNonNegative(bonus)) {
            this.bonus = bonus;
        }
    }

    @Override
    public double calculateSalary(){
        double finalSalary = getSalary() + bonus;
        return finalSalary;
    }


}
