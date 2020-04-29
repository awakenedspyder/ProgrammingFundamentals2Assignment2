import static org.junit.Assert.*;

import controllers.*;
import models.AdminWorker;
import models.Employee;
import models.Manager;
import models.SalesWorker;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;

/**
 * Test Class for the (concrete) Manager class
 *
 * @author Siobhan Drohan & Mairead Meagher
 * @version 03/20
 */


public class ManagerTest {

    private Manager manNormal1, manNormal2;
    private AdminWorker admin1;
    private SalesWorker sales1, sales2;

    /**
     * Method to set up data for testing.
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        manNormal1 = new Manager(30, 20, "man1First", "man1Last", "man1@mail.com", 6389128);
        admin1 = new AdminWorker(20, 10, "admin1First", "admin1Last", "admin1@mail.com", 9559128, 10);
        sales1 = new SalesWorker(20, 10, "sales1First", "sales1Last", "sales1@mail.com", 3696752, 10);
        sales2 = new SalesWorker(20, 10, "sales2First", "sales2Last", "sales2@mail.com", 8492727, 10);

        manNormal2 = new Manager(20, 100,  "man2First", "man2Last", "man2@mail.com", 9689912);
        manNormal2.addDeptEmployee(admin1);
        manNormal2.addDeptEmployee(sales1);
    }

    /**
     * Test method for Manager constructor
     */
    @Test
    public void testManager() {
        assertEquals(30, manNormal1.getHoursWorked(), 0.01);
        assertEquals(20, manNormal1.getHourlyRate(), 0.01);
        assertEquals(0, manNormal1.getTotalDeptEmployees(), 0.01);
        assertEquals("man1First", manNormal1.getEmpFirstName(), "man1First");
        assertEquals("man1Last", manNormal1.getEmpLastName(),"man1Last");
        assertEquals("man1@mail.com", manNormal1.getEmailId(),"man1@mail.com");
        assertEquals(6389128, manNormal1.getPps(), 0.01);

        assertEquals(20, manNormal2.getHoursWorked(), 0.01);
        assertEquals(100, manNormal2.getHourlyRate(), 0.01);
        assertEquals(2, manNormal2.getTotalDeptEmployees(), 0.01);
        assertEquals("man2First", manNormal2.getEmpFirstName(), "man2First");
        assertEquals("man2Last", manNormal2.getEmpLastName(),"man2Last");
        assertEquals("man2@mail.com", manNormal2.getEmailId(), "man2@mail.com");
        assertEquals(9689912, manNormal2.getPps(), 0.01);

    }


    @Test
    public void testCalculateSalary() {
        // manager with no employees
        assertEquals(600, manNormal1.calculateSalary(), 0.01);
        assertEquals(210, admin1.calculateSalary(), 0.01);
        assertEquals(220, sales1.calculateSalary(), 0.01);
        assertEquals(2004, manNormal2.calculateSalary(), 0.01);
        manNormal2.addDeptEmployee(sales2);
        assertEquals(2006, manNormal2.calculateSalary(), 0.01);
    }

    /**
     * Test method for addDeptEmployee(Employee).
     */
    @Test
    public void testAddDeptEmployee() {
        assertEquals(0, manNormal1.getTotalDeptEmployees(), 0.01);
        manNormal1.addDeptEmployee(sales1);
        assertEquals(1, manNormal1.getTotalDeptEmployees(), 0.01);
        assertEquals("sales1First", manNormal1.getDeptEmployee(0).getEmpFirstName(), "sales1First");
    }

    @Test
    public void removeEmployee() {
        assertEquals(2, manNormal2.getTotalDeptEmployees(), 0.01);
        manNormal2.removeDeptEmployee(0);
        assertEquals(1, manNormal2.getTotalDeptEmployees(), 0.01);
        assertEquals("sales1First", manNormal2.getDeptEmployee(0).getEmpFirstName(), "sales1First");

    }

    @Test
    public void setDept() {
        ArrayList<Employee> newDept = new ArrayList<Employee>();
        newDept.add(new AdminWorker(20, 10, "admin2First", "admin2Last", "admin2@mail.com", 955912,100));
        newDept.add(new SalesWorker(20, 10, "sales3First", "sales3Last", "sales3@mail.com", 955912,100));
        newDept.add(new AdminWorker(20, 10, "admin3First", "admin3Last", "admin3@mail.com", 955912,100));
        manNormal2.setEmployees(newDept);
        assertEquals(3, manNormal2.getTotalDeptEmployees(), 0.01);
        assertEquals("admin2First", manNormal2.getDeptEmployee(0).getEmpFirstName(), "admin2First");
    }
}