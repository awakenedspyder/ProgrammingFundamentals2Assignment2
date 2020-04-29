package models;

import utils.Utilities;

/**
 * Sales Worker Class is a concrete class which extends Employee and implement the class for a Sales Worker.
 *
 * @author Gowriswarup Kailas
 * @version 04/20
 */
public class SalesWorker extends Employee {

    private double percentageBonus;

    public SalesWorker(double hoursWorked, double hourlyRate, String empFirstName, String empLastName, String emailId, double pps, double percentageBonus) {
        super(hoursWorked, hourlyRate, empFirstName, empLastName, emailId, pps);
        if (Utilities.validDoubleRange(0,20, percentageBonus)) {
            this.percentageBonus = percentageBonus;
        }
    }
    public double getPercentageBonus() {
        return percentageBonus;
    }

    public void setPercentageBonus(double percentageBonus) {
        if (Utilities.validDoubleRange(0,20, percentageBonus)) {
            this.percentageBonus = percentageBonus;
        }
    }

    @Override
    public double calculateSalary(){
        double finalSalary = getSalary() + (getSalary()*(percentageBonus/100));
        return finalSalary;
    }

}
