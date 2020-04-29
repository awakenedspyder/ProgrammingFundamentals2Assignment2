import static org.junit.Assert.*;

import models.AdminWorker;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

/**
 * Test Class for the abstract class Employee
 *
 * Note that we are testing via the AdminWorker class, but we are
 * only testing methods that reside in the Employee class
 *
 * @author Siobhan Drohan & Mairead Meagher
 * @version 03/20
 */

public class EmployeeTest {

    private AdminWorker empNormal1, empNormal2;
    private AdminWorker empTestValidation;

    /**
     * Method to set up data for testing.
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        empNormal1 = new AdminWorker(20, 10, "FirstAdmin", "McTest", "email@mail.com", 6395128, 5);
        empNormal2 = new AdminWorker( 40.5, 10, "SecondAdmin", "McTest", "email2@mail.com", 2536529, 10);
        empTestValidation = new AdminWorker(-1, 5, "ThirdAdmin", "McTest", "email3@mail.com", 5365125, -35);
    }

    /**
     * Test method for Employee constructor
     */
    @Test
    public void testConstructor() {
        //test on valid data
        assertEquals(20, empNormal1.getHoursWorked(), 0.01);
        assertEquals(10, empNormal1.getHourlyRate(), 0.01);
        assertEquals("SecondAdmin", empNormal2.getEmpFirstName(), "SecondAdmin");
        assertEquals("McTest", empNormal2.getEmpLastName(),"McTest");
        assertEquals("email2@mail.com", empNormal2.getEmailId(),"email2@mail.com");
        assertEquals(6395128, empNormal1.getPps(), 0.01);
        assertEquals(5, empNormal1.getBonus(), 0.01);

        //test on invalid data
        assertEquals(0, empTestValidation.getHoursWorked(), 0.01);
        assertEquals(9.8, empTestValidation.getHourlyRate(), 0.01);
        assertEquals(0, empTestValidation.getBonus(), 0.01);
        assertEquals("ThirdAdmin", empTestValidation.getEmpFirstName(), "ThirdAdmin");
        assertEquals("McTest", empTestValidation.getEmpLastName(),"McTest");
        assertEquals("email3@mail.com", empTestValidation.getEmailId(),"email3@mail.com");
        assertEquals(5365125, empTestValidation.getPps(), 0.01);
    }

    /**
     * Test method for getSalary(), testing for employees with and without overtime.
     */
    @Test
    public void testGetSalary(){
        //employee under NORMAL_WORKING_WEEKS hours
        assertEquals(200, empNormal1.getSalary(), 0.01 );
        //employee with overtime + fixed bonus
        assertEquals(415, empNormal2.getSalary(), 0.01 );
        empNormal1.setHourlyRate(0);
        assertEquals(196, empNormal1.getSalary(), 0.01 );
        empNormal1.setHourlyRate(20);
        assertEquals(400, empNormal1.getSalary(), 0.01 );
        empNormal1.setHourlyRate(20);
        assertEquals(400, empNormal1.getSalary(), 0.01 );
        empNormal1.setHoursWorked(40.5);
        empNormal1.setHourlyRate(20);
        assertEquals(830, empNormal1.getSalary(), 0.01);
    }

    /**
     * Test method for getters and setters.
     */
    @Test
    public void testSettersGetters() {
        //todo: add tests for the setters and getters for your Employee fields
        assertEquals(20, empNormal1.getHoursWorked(), 0.01);
        empNormal1.setHoursWorked(40);
        assertEquals(40, empNormal1.getHoursWorked(), 0.01);
        empNormal1.setHoursWorked(-1);
        assertEquals(40, empNormal1.getHoursWorked(), 0.01);
        empNormal1.setHourlyRate(40);
        assertEquals(40, empNormal1.getHourlyRate(), 0.01);
        empNormal1.setHourlyRate(9.79);
        //ensure no change when invalid data used
        assertEquals(40, empNormal1.getHourlyRate(), 0.01);
        empNormal1.setEmailId("notanemail");
        assertEquals("email@mail.com", empNormal1.getEmailId(), "email@mail.com");
    }

    /**
     * Test method for getOverTime (= hourly-rate*2 * (the number of hours overtime))
     */
    @Test
    public void getOverTime() {
        assertEquals(0, empNormal1.getOverTime(), 0.01);
        assertEquals(20, empNormal2.getOverTime(), 0.01);
        empNormal2.setHourlyRate(20);
        assertEquals(40, empNormal2.getOverTime(), 0.01);
    }

    /**
     * Test method for toString - checks that all data fields are present.
     */
    @Test
    public void testToString() {
        assertTrue(empNormal1.toString().contains("hours Worked"));
        assertTrue(empNormal1.toString().contains("20"));
        assertTrue(empNormal1.toString().contains("hourly Rate"));
        assertTrue(empNormal1.toString().contains("10"));
    }
}