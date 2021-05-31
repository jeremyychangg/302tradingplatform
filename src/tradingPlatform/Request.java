package tradingPlatform;

import tradingPlatform.enumerators.requestType;
import tradingPlatform.database.JBDCConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Request{
    public String requestID;
    public String userID;
    public Enum requestType;
    public String requestMessage;

    // Construct Request object without ID- not submitted yet
    public Request(String userID, Enum requestType, String requestMessage) {
        this.userID = userID;
        this.requestType = requestType;
        this.requestMessage = requestMessage;
    }

    // Construct Request object with all parameters
    public Request(String requestID, String userID, Enum requestType, String requestMessage) {
        this.requestID = requestID;
        this.userID = userID;
        this.requestType = requestType;
        this.requestMessage = requestMessage;
    }

    public Request() {
        Request req  = new Request();
    }

    // Get Request ID
    public String getRequestID() {
        return requestID;
    }

    // Update Request ID
    public void setRequestID() {
        this.requestID = requestID;
    }

    // Get User ID
    public String getUserID() {
        return userID;
    }

    // Update User ID
    public void setUserID() {
        this.userID = userID;
    }

    // Get Request Type
    public Enum getRequestType() {
        return requestType;
    }

    // Update Request Type
    public void setRequestType() {
        this.requestType = requestType;
    }

    // Get Request Message
    public String getRequestMessage() {
        return requestMessage;
    }

    // Update Request Message
    public void setRequestMessage() {
        this.requestMessage = requestMessage;
    }

    // FULFIL REQUESTS //
}
