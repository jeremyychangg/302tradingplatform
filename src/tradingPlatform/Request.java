package tradingPlatform;

public class Request{
    public int requestID;
    public String userID;
    public Enum requestType;
    public String requestMessage;

    public Request(int requestID, String userID, Enum requestType, String requestMessage) {
        this.requestID = requestID;
        this.userID = userID;
        this.requestType = requestType;
        this.requestMessage = requestMessage;
    }

    public Request() {
        Request req  = new Request();
    }

    // CLASSES: not written properly- just an idea of what they will do.

    // Gets the user ID to validate member of organisation / admin
    public String getUserID() {

        return userID;
    }

    // Sets the user ID
    public void setUserID (String userID) {

        this.userID = userID;
    }

    // Gets the request type for admin use
    public Enum getRequestType() {

        return requestType;
    }

    // Sets the request type
    public void setRequestType (Enum requestType) {

        this.requestType = requestType;
    }

    // Gets the request message from the user
    public String getRequestMessage() {

        return requestMessage;
    }

    public void setRequestMessage() {

        this.requestMessage = requestMessage;
    }

    // Provides request ID for customer
    public void setRequestID() {

        this.requestID = requestID;
    }

    // Request for user permissions
    public void requestUser(int userID, String requestMessage) {

    }

    // Request about asset info
    public void requestAsset(int userID, String requestMessage) {

    }
}
