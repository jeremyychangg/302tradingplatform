package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import tradingPlatform.Request;
import tradingPlatform.enumerators.requestType;
// import user.User

//import static com.sun.xml.internal.ws.api.message.Packet.Status.Request;

public class testRequest {
    Request request;

    // - Test 1: check if request exists
    @BeforeEach
    @Test
    void setup() {
        request = new Request();
    }

    // - Test 2: check if Request has userID
    @Test
    public void noUserID() throws Exception {
        assertNull(request.userID);
    }

    // - Test 3: check if Request has request ID
    @Test
    public void noRequestID() throws Exception {
        assertNull(request.requestID);
    }

    // - Test 4: check if Request has requestType
    @Test
    public void noRequestType() throws Exception {
        assertNull(request.requestType);
    }

    // - Test 5: check if Request has a request message
    @Test
    public void noRequest() throws Exception {
        assertNull(request.requestMessage);
    }

    // - Test 6: check if user (userID) exists
    @Test
    // need to input User Class to check if the user exists
    public void noUserFound() throws Exception {
        // user = new User();
    }

    // - Test 7: check if user ID is an integer
    @Test
    public void invalidID() throws Exception {
        Request testRequest = new Request(100, "", requestType.userRequest, "Password Change required");
    }
    // - Test 8: check if request type is an enum value (request for user / request for asset info)
    @Test
    public void invalidRequestType() throws Exception {
        Request testRequest = new Request(100, "3", requestType.designRequest, "Password Change required");
    }
}
