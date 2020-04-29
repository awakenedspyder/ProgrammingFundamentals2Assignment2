import models.AdminWorker;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
/**
 * Test Class for the Admin Worker class
 *
 * @author Gowriswarup Kailas
 * @version 04/20
 */
class AdminWorkerTest {
    private AdminWorker admin1, admin2, adminTestValidation;

    /**
     * Method to set up data for testing.
     * @throws java.lang.Exception Throws Exception
     */
    @BeforeEach
    void setUp() throws Exception {
        admin1 = new AdminWorker(20, 10, "FirstAdmin", "McTest", "email@mail.com", 6395125, 5);
        admin2 = new AdminWorker( 40.5, 10, "SecondAdmin", "McTest", "email2@mail.com", 2536528, 10);
        adminTestValidation = new AdminWorker(-1, 5, "ThirdAdmin", "McTest", "email3@mail.com", 5365129, -35);
    }

    /**
     * Method for tearing down of data after testing
     * @throws java.lang.Exception Throws Exception
     */
    @AfterEach
    void tearDown() throws Exception {
        admin1 = admin2 = adminTestValidation = null;
    }

    /**
     * Test method for AdminWorker constructor
     */
    @Test
    void TestConstructor() {
        //test on valid data
        assertEquals(20, admin1.getHoursWorked(), 0.01);
        assertEquals(10, admin1.getHourlyRate(), 0.01);
        assertEquals("FirstAdmin", admin1.getEmpFirstName(), "FirstAdmin");
        assertEquals("McTest", admin1.getEmpLastName(),"McTest");
        assertEquals("email@mail.com", admin1.getEmailId(),"email@mail.com");
        assertEquals(6395125, admin1.getPps(), 0.01);
        assertEquals(5, admin1.getBonus(), 0.01);

        //test on invalid data
        assertEquals(0, adminTestValidation.getHoursWorked(), 0.01);
        assertEquals(9.8, adminTestValidation.getHourlyRate(), 0.01);
        assertEquals("ThirdAdmin", adminTestValidation.getEmpFirstName(), "ThirdAdmin");
        assertEquals("McTest", adminTestValidation.getEmpLastName(),"McTest");
        assertEquals("email3@mail.com", adminTestValidation.getEmailId(),"email3@mail.com");
        assertEquals(5365129, adminTestValidation.getPps(), 0.01);
        assertEquals(0, adminTestValidation.getBonus(), 0.01);
    }

    /**
     * Test method for getters and setters.
     */
    @Test
    void testSettersGetters() {
        admin1.setBonus(0);
        assertEquals(0, admin1.getBonus(), 0.01);
        admin1.setBonus(1);
        assertEquals(1, admin1.getBonus(), 0.01);
        admin1.setBonus(-1);
        assertEquals(1, admin1.getBonus(), 0.01);
    }

    /**
     * Test method for calculateSalary
     */
    @Test
    void calculateSalary() {
        assertEquals(205, admin1.calculateSalary(), 0.01);
        assertEquals(425, admin2.calculateSalary(), 0.01);
    }
}