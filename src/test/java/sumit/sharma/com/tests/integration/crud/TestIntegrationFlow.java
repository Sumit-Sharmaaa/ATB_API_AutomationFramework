package sumit.sharma.com.tests.integration.crud;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import sumit.sharma.com.base.BaseTest;
import sumit.sharma.com.endpoints.APIConstants;
import sumit.sharma.com.pojos.Booking;
import sumit.sharma.com.pojos.BookingResponse;
import static org.assertj.core.api.Assertions.*;

public class TestIntegrationFlow extends BaseTest {

    @Test(groups = "integration", priority = 1)
    @Owner("Sumit")
    @Description("TC1 - Create Booking and Verify")
    public void testCreateBooking(ITestContext iTestContext){
        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL);

        response= RestAssured
                .given(requestSpecification)
                .when().body(payloadManager.createPayloadBookingAsString()).post();

        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);

        BookingResponse bookingResponse = payloadManager.bookingResponseJava(response.asString());
        iTestContext.setAttribute("bookingid",bookingResponse.getBookingid());

    }


    @Test(groups = "integration", priority = 2)
    @Owner("Sumit")
    @Description("TC2 - Verify booking")
    public void testVerifyBookingId(ITestContext iTestContext){

        Integer bookingID = (Integer)iTestContext.getAttribute("bookingid");

        //Get Request
        String basePathGET = APIConstants.CREATE_UPDATE_BOOKING_URL + "/" + bookingID;
        System.out.println(basePathGET);

        requestSpecification.basePath(basePathGET);
        response = RestAssured.given(requestSpecification)
                    .when().get();

        validatableResponse = response.then().log().all();

        // Validatable Assertion
        validatableResponse.statusCode(200);

        Booking booking = payloadManager.getResponseFromJSON(response.asString());
        assertThat(booking.getFirstname()).isNotNull();
        assertThat(booking.getFirstname()).isEqualToIgnoringCase("Sumit");

    }

    @Test(groups = "qa", priority = 3)
    @Owner("Sumit")
    @Description("TC3 - Update Booking by Id")
    public void testUpdateBookingbyId(ITestContext iTestContext){

        String token = getToken();
        Integer bookingid = (Integer) iTestContext.getAttribute("bookingid");

        iTestContext.setAttribute("token",token);
        Integer bookingId = (Integer) iTestContext.getAttribute("bookingid");

        String basePathPUTPATCH = APIConstants.CREATE_UPDATE_BOOKING_URL+"/"+ bookingid;
        System.out.println(basePathPUTPATCH);

        requestSpecification.basePath(basePathPUTPATCH);
        response = RestAssured.given(requestSpecification).cookie("token",token)
                .when().body(payloadManager.fullUpdatePayloadAsString()).put();
        validatableResponse = response.then().log().all();

        //Validatable Assertions
        validatableResponse.statusCode(200);

        Booking booking = payloadManager.getResponseFromJSON(response.asString());

        assertThat(booking.getFirstname()).isNotNull().isNotEmpty().isNotBlank();
        assertThat(booking.getFirstname()).isEqualToIgnoringCase("Udit");
        assertThat(booking.getLastname()).isEqualToIgnoringCase("Sharma");



    }

    @Test(groups = "qa", priority = 4)
    @Owner("Sumit")
    @Description("TC4 - Delete Booking by Id")
    public void testDeleteBookingbyId(ITestContext iTestContext){

        String token = (String) iTestContext.getAttribute("token");
        Integer bookingid = (Integer) iTestContext.getAttribute("bookingid");

        String basePathDELETE = APIConstants.CREATE_UPDATE_BOOKING_URL+"/"+ bookingid;
        System.out.println(basePathDELETE);

        requestSpecification.basePath(basePathDELETE).cookie("token",token);
        validatableResponse = RestAssured.given().spec(requestSpecification)
                .when().delete().then().log().all();

        validatableResponse.statusCode(201);

    }


}
