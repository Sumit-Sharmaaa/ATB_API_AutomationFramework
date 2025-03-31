package sumit.sharma.com.base;

import com.beust.ah.A;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.Validatable;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import sumit.sharma.com.asserts.AssertActions;
import sumit.sharma.com.endpoints.APIConstants;
import sumit.sharma.com.modules.PayloadManager;

public class BaseTest {

    public RequestSpecification requestSpecification;
    public AssertActions assertActions;
    public PayloadManager payloadManager;
    public JsonPath jsonPath;
    public Response response;
    public ValidatableResponse validatableResponse;

    @BeforeTest
    public void setup() {
        // Base URL & Content Type

        payloadManager = new PayloadManager();
        assertActions = new AssertActions();

        requestSpecification = RestAssured.given()
                .baseUri(APIConstants.BASE_URL)
                .contentType(ContentType.JSON).log().all();


        // OR

//        requestSpecification = new RequestSpecBuilder()
//                .setBaseUri(APIConstants.BASE_URL)
//                .addHeader("Content-Type","application/json")
//                .build().log().all();

    }
        public String getToken()
    {
            requestSpecification = RestAssured
                    .given().baseUri(APIConstants.BASE_URL)
                    .basePath(APIConstants.AUTH_URL);

            // Setting the payload
        String payload = payloadManager.setAuthPayload();

        //Get the Token
        response = requestSpecification.contentType(ContentType.JSON).body(payload).when().post();

        // String Extraction
        String token = payloadManager.getTokenFromJson(response.asString());
        return token;

        }



}
