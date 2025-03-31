package sumit.sharma.com.tests.integration.sample;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestIntegrationScenario {
    // This is the first File
    // After this file we will create the TestNG File.

    // Create a Booking
    // Get Booking
    // Update the Booking
    // Delete the Booking

    @Test(groups = "qa", priority = 1)
    @Owner("Sumit")
    @Description("TC1 - Create Booking and Verify")
    public void testCreateBooking(){
        Assert.assertTrue(true);
    }

    @Test(groups = "qa", priority = 2)
    @Owner("Sumit")
    @Description("TC2 - Create Booking")
    public void testVerifyBookingId(){
        Assert.assertTrue(true);
    }

    @Test(groups = "qa", priority = 3)
    @Owner("Sumit")
    @Description("TC3 - Update Booking by Id")
    public void testUpdateBookingbyId(){
        Assert.assertTrue(true);
    }

    @Test(groups = "qa", priority = 4)
    @Owner("Sumit")
    @Description("TC4 - Delete Booking by Id")
    public void testDeleteBookingbyId(){
        Assert.assertTrue(true);
    }

    // Now we will create the TestNG File.

}
