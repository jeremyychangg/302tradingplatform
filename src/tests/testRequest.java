// 	******************************************************************************************
// 	**																						**
// 	**	Filename: testRequest.java															**
// 	**																						**
// 	**	Description: Testing Code for Request Class											**
// 	**																						**
// 	**																						**
// 	**	Contributors: Nicole Truong											                **
// 	**																						**
// 	**																						**
// 	**	Date Created: 16/05/2021															**
// 	**																						**
// 	**																						**
// 	**	Change Documentation																**
// 	**		> Initial Version - 16/05/2021                                                  **
// 	**      > Revised by Jeremy Chang - 20/05/2021                                          **
// 	**		> Revised by Nicole Truong - 23/05/2021											**
// 	**																						**
// 	**																						**
// 	******************************************************************************************
package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.NullString;
import tradingPlatform.Request;
import tradingPlatform.enumerators.requestType;
// import user.User
import javax.lang.model.type.NullType;
import static com.sun.xml.internal.ws.api.message.Packet.Status.Request;

public class testRequest {
    // Declare objects for testing
    Request request1;
    Request request2;

    // - Test 1: Construct requests for testing
    @BeforeEach
    @Test
    void setupRequests() {
        request1 = new Request ("S0001", requestType.newUnitRequest, "Assign me to my new unit");
        request2 = new Request ("R0018", "A0002", requestType.newAssetTypeRequest, "Add Devices as new asset type");
    }

    // - Test 2: Check that request ID is not null
    @Test
    public void requestIDNotNull() {
        assertNotNull(request2.getRequestID());
        assertTrue(request2.getRequestID() != null);
    }

    // - Test 3: Check if request ID provided is a string
    @Test
    public void requestIDValid() {
        assertTrue(request2.getRequestID() == "R0018");
    }

    // - Test 4: check that the request ID provided starts with an R
    @Test
    public void requestIDStartsWithR() {
        assertTrue(request2.getRequestID().startsWith("R"));
    }

    // - Test 5: check that the userID provided is not null
    @Test
    public void userIDNotNull() {
        assertNotNull(request1.getUserID());
        assertTrue(request1.getUserID() != null);
        assertNotNull(request2.getUserID());
        assertTrue(request2.getUserID() != null);
    }

    // - Test 6: check that the userID provided is a string
    @Test
    public void userIDValid() {
        assertTrue(request1.getUserID() == "S0001");
        assertTrue(request2.getUserID() == "A0002");
    }

    // - Test 7: check that the userID provided starts with S, L or A
    @Test
    public void userIDPrefix() {
        assertTrue(request1.getUserID().startsWith("S") || request1.getUserID().startsWith("A") || request1.getUserID().startsWith("L"));
        assertTrue(request2.getUserID().startsWith("S") || request2.getUserID().startsWith("A") || request2.getUserID().startsWith("L"));
    }

    // - Test 8: check that request type provided is not null
    @Test
    public void requestTypeNotNull() {
        assertNotNull(request1.getRequestType());
        assertTrue(request1.getRequestType() != null);
        assertNotNull(request2.getRequestType());
        assertTrue(request2.getRequestType() != null);
    }

    // - Test 9: check that the request type provided is valid within the enum fields
    @Test
    public void requestTypeValid() {
        assertTrue(request1.getRequestType() == requestType.newUnitRequest);
        assertTrue(request2.getRequestType() == requestType.newAssetTypeRequest);
    }

    // - Test 10: check that the request message is not null
    @Test
    public void requestMessageNotNull() {
        assertNotNull(request1.getRequestMessage());
        assertTrue(request1.getRequestMessage() != null);
        assertNotNull(request2.getRequestMessage());
        assertTrue(request2.getRequestMessage() != null);
    }

    // - Test 11: check that the request message a string
    @Test
    public void requestMessageValid() {
        assertTrue(request1.getRequestMessage() == "Assign me to my new unit");
        assertTrue(request2.getRequestMessage() == "Add Devices as new asset type");
    }
}
