package sumit.sharma.com.tests.integration.crud;

import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import sumit.sharma.com.base.BaseTest;
import sumit.sharma.com.endpoints.APIConstants;
import sumit.sharma.com.pojos.BookingResponse;
import static org.assertj.core.api.Assertions.*;

public class Test_CreateBooking_Post extends BaseTest {

    @Owner("Sumit")
    //@Link("Linc to TC", "url" = "abcd.com")
    @Issue("JIRA ID- Sumit1234")
    @Description("Verify that POST Request is working Fine")
    @Test
    public void Test_CreateBooking_Post(){
        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL);

        response= RestAssured
                .given(requestSpecification)
                .when().body(payloadManager.createPayloadBookingAsString()).post();

        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);

        //Default Rest Assured - Validations -
        validatableResponse.body("booking.firstname", Matchers.equalTo("Sumit"));

        BookingResponse bookingResponse = payloadManager.bookingResponseJava(response.asString());

        //AssertJ

        assertThat(bookingResponse.getBookingid()).isNotNull();
        assertThat(bookingResponse.getBooking().getFirstname()).isNotNull().isNotEmpty();
        assertThat(bookingResponse.getBooking().getLastname()).isEqualToIgnoringCase("Sumit");

        //TestNG Assertions
        assertActions.verifyStatusCode(response,200);



    }

}
