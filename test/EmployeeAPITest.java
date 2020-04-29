import static java.lang.Double.NaN;
import static org.junit.Assert.*;

import controllers.EmployeeAPI;
import models.AdminWorker;
import models.Employee;
import models.Manager;
import models.SalesWorker;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;

import java.util.ArrayList;

/**
 * Test Class for the Employee API class
 *
 * @author Gowriswarup Kailas
 * @version 04/20
 */

public class EmployeeAPITest {

    private EmployeeAPI empAPI, populatedEmpAPI;
    private Employee manNormal1, admin1, sales1, manNormal2, admin2, sales2;
    private ArrayList<Employee> emptyEmployees, populatedEmployees;

    /**
     * Method to set up data for testing.
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        //A empAPI object that will be empty at the beginning of each test
        empAPI = new EmployeeAPI();

        //An empty ArrayList of Employees created independently of the empAPI class.
        //This will be used to compare with the ArrayList created in empAPI.
        emptyEmployees = new ArrayList<Employee>();

        //A populated ArrayList of Employees created independently of the empAPI class.
        //This will be used to compare with the ArrayList created in empAPI.
        populatedEmployees = new ArrayList<Employee>();
        manNormal1 = new Manager(20, 10, "FirstMan", "FirstManLast", "man1@mail.com", 6395128);
        admin1 = new AdminWorker( 40.5, 10, "FirstAdmin", "FirstAdminLast", "admin1@mail.com", 2536529, 10);
        sales1 = new SalesWorker(38, 9, "FirstSales", "FirstSalesLast", "sales1@mail.com", 5365125, 30);
        manNormal2 = new Manager(40, 15, "SecondMan", "SecondManLast", "man2@mail.com", 5126398);
        admin2 = new AdminWorker( 20.5, 20, "SecondAdmin", "SecondAdminLast", "admin2@mail.com", 2525369, 15);
        sales2 = new SalesWorker(38, 9, "SecondSales", "SecondSalesLast", "sales2@mail.com", 5512365, 80);
        populatedEmployees.add(manNormal1);
        populatedEmployees.add(admin1);
        populatedEmployees.add(sales1);
        populatedEmployees.add(manNormal2);
        populatedEmployees.add(admin2);
        populatedEmployees.add(sales2);


        //A empAPI object that will be populated with three Employees at the beginning of each test
        populatedEmpAPI = new EmployeeAPI();
        populatedEmpAPI.setEmployees(populatedEmployees);
    }

    @AfterEach
    void tearDown() {
        empAPI = populatedEmpAPI = null;
        manNormal1 = admin1 = sales1 = null;
        populatedEmployees = emptyEmployees = null;
    }


    @Test
    public void addEmployee() {
        populatedEmployees.add(sales2);
        assertEquals(7, populatedEmpAPI.numberOfEmployees());
    }

    @Test
    public void addEmployeeToDepartment() {
        if (populatedEmpAPI.addEmployeeToDepartment(2,0)){
            assertEquals(admin1, populatedEmpAPI.getEmployee(1));
        }
        assertFalse(empAPI.addEmployeeToDepartment(1,1));
    }

    @Test
    public void getEmployee() {
        assertEquals(manNormal1, populatedEmpAPI.getEmployee(0));
    }

    @Test
    public void removeEmployee() {
        if(populatedEmployees.size()>0){
            populatedEmpAPI.removeEmployee("SecondAdminLast");
        }
        assertEquals(5, populatedEmpAPI.numberOfEmployees());
    }

    @Test
    public void numberOfEmployees() {
        assertEquals(6, populatedEmpAPI.numberOfEmployees());
        assertEquals(0, empAPI.numberOfEmployees());
    }

    @Test
    public void listEmployees() {
        assertEquals("(1)FirstMan FirstManLast\n"+
                "(2)FirstAdmin FirstAdminLast\n"+
                "(3)FirstSales FirstSalesLast\n"+
                "(4)SecondMan SecondManLast\n"+
                "(5)SecondAdmin SecondAdminLast\n"+
                "(6)SecondSales SecondSalesLast\n" , populatedEmpAPI.listEmployees());
        assertFalse(empAPI.listEmployees().contains("FirstAdmin McTest2"));
    }

    @Test
    public void ListManagerEmployees() {
        assertEquals(manNormal1 +"\n" , populatedEmpAPI.listManagerEmployees((Manager) manNormal1));
        assertEquals(manNormal2 +"\n", populatedEmpAPI.listManagerEmployees((Manager) manNormal2));
    }

    @Test
    public void testListManagerEmployees() {
        assertTrue(populatedEmpAPI.listManagerEmployees().contains(manNormal1.toString()));
        assertFalse(empAPI.listManagerEmployees().contains(manNormal1.toString()));
    }

    @Test
    public void searchEmployees() {
        assertEquals(1, populatedEmpAPI.searchEmployees("FirstAdminLast"));
        assertEquals(0, populatedEmpAPI.searchEmployees("secondName"));
    }

    @Test
    public void totalSalariesOwed() {
        assertEquals(2402.3, populatedEmpAPI.totalSalariesOwed(), 0.01);
        assertEquals(0, empAPI.totalSalariesOwed(), 0.01);
    }
    @Test
    public void avgSalariesOwed() {
        assertEquals(400.3833333333334, populatedEmpAPI.avgSalariesOwed(), 0.01);
        assertEquals(NaN, empAPI.avgSalariesOwed(), NaN);
    }

    @Test
    public void employeeWithHighestPay() {
        assertEquals("SecondMan SecondManLast", populatedEmpAPI.employeeWithHighestPay().getEmpFirstName()+" "+populatedEmpAPI.employeeWithHighestPay().getEmpLastName(), "SecondMan SecondManLast");
    }

    @Test
    public void sortEmployeesByFirstName() {
        populatedEmpAPI.sortEmployeesByFirstName();
        assertEquals("(1)FirstAdmin FirstAdminLast\n"+
                "(2)FirstMan FirstManLast\n"+
                "(3)FirstSales FirstSalesLast\n"+
                "(4)SecondAdmin SecondAdminLast\n"+
                "(5)SecondMan SecondManLast\n"+
               "(6)SecondSales SecondSalesLast\n" , populatedEmpAPI.listEmployees());
    }

    @Test
    public void sortEmployeesBySecondName() {
        populatedEmpAPI.sortEmployeesBySecondName();
        assertEquals("(1)FirstAdmin FirstAdminLast\n"+
                "(2)FirstMan FirstManLast\n"+
                "(3)FirstSales FirstSalesLast\n"+
                "(4)SecondAdmin SecondAdminLast\n"+
                "(5)SecondMan SecondManLast\n"+
                "(6)SecondSales SecondSalesLast\n" , populatedEmpAPI.listEmployees());
    }

    @Test
    public void sortEmployeesByHourlyRate() {
        populatedEmpAPI.sortEmployeesByHourlyRate();
        assertEquals("(1)FirstSales FirstSalesLast\n"+
                "(2)SecondSales SecondSalesLast\n"+
                "(3)FirstAdmin FirstAdminLast\n"+
                "(4)FirstMan FirstManLast\n"+
                "(5)SecondMan SecondManLast\n"+
                "(6)SecondAdmin SecondAdminLast\n" , populatedEmpAPI.listEmployees());
    }

    @Test
    public void swapEmployees() {
        populatedEmpAPI.swapEmployees(populatedEmployees, 1,3);
        assertEquals("(1)FirstMan FirstManLast\n"+
                "(2)SecondMan SecondManLast\n"+
                "(3)FirstSales FirstSalesLast\n"+
                "(4)FirstAdmin FirstAdminLast\n"+
                "(5)SecondAdmin SecondAdminLast\n"+
                "(6)SecondSales SecondSalesLast\n" , populatedEmpAPI.listEmployees());
    }

    @Test
    public void testSaveAndLoad() throws Exception {
        //TESTING AN EMPTY ARRAYLIST
        //--------------------------
        //Saving a new empAPI object with an empty ArrayList of Employee
        assertEquals(0, empAPI.getEmployees().size());
        assertEquals(emptyEmployees, empAPI.getEmployees());
        empAPI.save();
        //Load the file into another empAPI object and compare it to emptyEmployees
        EmployeeAPI empAPI2 = new EmployeeAPI();
        empAPI2.load();
        assertEquals(empAPI2.getEmployees().size(), empAPI.getEmployees().size());

        //TESTING A POPULATED ARRAYLIST
        //-----------------------------
        //Saving a empAPI object with a populated ArrayList of Employee
        assertEquals(6, populatedEmpAPI.getEmployees().size());
        assertEquals(populatedEmployees, populatedEmpAPI.getEmployees());
        populatedEmpAPI.save();
        //Load the file into another empAPI object and compare it to populatedEmpAPI
        EmployeeAPI empAPI3 = new EmployeeAPI();
        empAPI3.load();
        assertEquals(empAPI3.getEmployees().size(), populatedEmpAPI.getEmployees().size());
        assertEquals(empAPI3.getEmployees().get(1).getEmpFirstName(), populatedEmpAPI.getEmployees().get(1).getEmpFirstName());
        assertEquals(empAPI3.getEmployees().get(2).getEmpFirstName(), populatedEmpAPI.getEmployees().get(2).getEmpFirstName());
    }
}