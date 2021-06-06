package tradingPlatform;

import tradingPlatform.enumerators.requestType;
import tradingPlatform.database.JBDCConnection;
import tradingPlatform.exceptions.RequestException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static tradingPlatform.Main.connection;


public class Request{
    public String requestID;
    public String userID;
    public Enum requestType;
    public String requestMessage;

    // Construct Request object with all parameters
    public Request(String requestID, String userID, Enum requestType, String requestMessage) {
        this.requestID = requestID;
        this.userID = userID;
        this.requestType = requestType;
        this.requestMessage = requestMessage;
    }

    // Construct Request object without ID- not submitted yet
    public Request(String userID, Enum requestType, String requestMessage) throws RequestException, SQLException {
        this.userID = userID;
        this.requestType = requestType;
        this.requestMessage = requestMessage;

        String reqIDSubstring;

        if (requestType == tradingPlatform.enumerators.requestType.newUserRequest) {
            reqIDSubstring = "US";
        } else if (requestType == tradingPlatform.enumerators.requestType.newAccountTypeRequest) {
            reqIDSubstring = "AC";
        } else if (requestType == tradingPlatform.enumerators.requestType.newUnitRequest) {
            reqIDSubstring = "NU";
        } else if (requestType == tradingPlatform.enumerators.requestType.changeUnitRequest) {
            reqIDSubstring = "CU";
        } else if (requestType == tradingPlatform.enumerators.requestType.editBalanceRequest) {
            reqIDSubstring = "EB";
        } else if (requestType == tradingPlatform.enumerators.requestType.editLimitRequest) {
            reqIDSubstring = "EL";
        } else if (requestType == tradingPlatform.enumerators.requestType.editAssetsOwnedRequest) {
            reqIDSubstring = "EA";
        } else if (requestType == tradingPlatform.enumerators.requestType.newAssetRequest) {
            reqIDSubstring = "AR";
        } else if (requestType == tradingPlatform.enumerators.requestType.newAssetTypeRequest) {
            reqIDSubstring = "AS";
        } else if (requestType == tradingPlatform.enumerators.requestType.permissionRequest) {
            reqIDSubstring = "PR";
        } else {
            throw new RequestException("Invalid Request");
        }

        int maxID = 0;
        Statement statement = connection.createStatement();
        String sqlMaxID
                = "SELECT max(substring(assetID, 3, 8)) as maxID FROM request WHERE substring(assetID, 1, 2) = '"
                + reqIDSubstring + "';";
        ResultSet getMaxID = statement.executeQuery(sqlMaxID);

        // Extract string result and parse as integer
        if (getMaxID.next() && getMaxID.getString("maxID") != null) {
            maxID = Integer.parseInt(getMaxID.getString("maxID"));
        }

        // Add 1 to current max ID to get new ID number for this asset and append to asset type code
        String newID = reqIDSubstring + String.format("%08d", maxID + 1);
        this.requestID = newID;
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
