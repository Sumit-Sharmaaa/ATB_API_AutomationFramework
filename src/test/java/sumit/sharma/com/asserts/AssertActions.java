package sumit.sharma.com.asserts;

import io.restassured.response.Response;
import org.testng.Assert;
import static org.assertj.core.api.Assertions.*;

public class AssertActions {

    // Common Assertions which can be Reused

    public void verifyResponseBody(String actual, String expected, String description){
        Assert.assertEquals(actual,expected,description);
    }

    public void verifyResponseBody(int actual, int expected, String description){
        Assert.assertEquals(actual,expected,description);
    }

    public void verifyStatusCode(Response response, Integer expected){
        Assert.assertEquals(response.getStatusCode(),expected);
    }

    // Generic Method
    public void verifyStringKey(String expectedKey, String actulaKey){
        assertThat(expectedKey).isNotNull();
        assertThat(expectedKey).isEqualToIgnoringCase(actulaKey);
        assertThat(expectedKey).isNotEmpty().isNotBlank();
    }

}
