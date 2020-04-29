import models.SalesWorker;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
/**
 * Test Class for the Sales Worker class
 *
 * @author Gowriswarup Kailas
 * @version 04/20
 */
class salesWorkerTest {
    private SalesWorker sales1, sales2, salesTestValidation;

    /**
     * Method to set up data for testing.
     * @throws java.lang.Exception Throws Exception
     */
    @BeforeEach
    void setUp() throws Exception {
        sales1 = new SalesWorker(20, 10, "Firstsales", "McTest", "email@mail.com", 6395125, 5);
        sales2 = new SalesWorker( 40.5, 10, "Secondsales", "McTest", "email2@mail.com", 2536528, 10);
        salesTestValidation = new SalesWorker(-1, 5, "Thirdsales", "McTest", "email3@mail.com", 5365129, -35);
    }

    /**
     * Method for tearing down of data after testing
     * @throws java.lang.Exception Throws Exception
     */
    @AfterEach
    void tearDown() throws Exception {
        sales1 = sales2 = salesTestValidation = null;
    }

    /**
     * Test method for salesWorker constructor
     */
    @Test
    void TestConstructor() {
        //test on valid data
        assertEquals(20, sales1.getHoursWorked(), 0.01);
        assertEquals(10, sales1.getHourlyRate(), 0.01);
        assertEquals("Firstsales", sales1.getEmpFirstName(), "Firstsales");
        assertEquals("McTest", sales1.getEmpLastName(),"McTest");
        assertEquals("email@mail.com", sales1.getEmailId(),"email@mail.com");
        assertEquals(6395125, sales1.getPps(), 0.01);
        assertEquals(5, sales1.getPercentageBonus(), 0.01);

        //test on invalid data
        assertEquals(0, salesTestValidation.getHoursWorked(), 0.01);
        assertEquals(9.8, salesTestValidation.getHourlyRate(), 0.01);
        assertEquals("Thirdsales", salesTestValidation.getEmpFirstName(), "Thirdsales");
        assertEquals("McTest", salesTestValidation.getEmpLastName(),"McTest");
        assertEquals("email3@mail.com", salesTestValidation.getEmailId(),"email3@mail.com");
        assertEquals(5365129, salesTestValidation.getPps(), 0.01);
        assertEquals(0, salesTestValidation.getPercentageBonus(), 0.01);
    }

    /**
     * Test method for getters and setters.
     */
    @Test
    void testSettersGetters() {
        sales1.setPercentageBonus(0);
        assertEquals(0, sales1.getPercentageBonus(), 0.01);
        sales1.setPercentageBonus(1);
        assertEquals(1, sales1.getPercentageBonus(), 0.01);
        sales1.setPercentageBonus(-1);
        assertEquals(1, sales1.getPercentageBonus(), 0.01);
    }

    /**
     * Test method for calculateSalary
     */
    @Test
    void calculateSalary() {
        assertEquals(210, sales1.calculateSalary(), 0.01);
        assertEquals(456.5, sales2.calculateSalary(), 0.01);
    }
}