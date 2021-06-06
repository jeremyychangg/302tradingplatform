package tradingPlatform;

import tradingPlatform.enumerators.requestType;
import tradingPlatform.database.JBDCConnection;
import tradingPlatform.exceptions.RequestException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static tradingPlatform.Main.connection;

/**
 * Created for users to make requests, and for leads to retrieve requests
 */
public class Request{
    public String requestID;
    public String userID;
    public Enum requestType;
    public String requestMessage;

    /**
     * Creates an instance of request in database
     * @param requestID
     * @param userID
     * @param requestType
     * @param requestMessage
     */
    public Request(String requestID, String userID, Enum requestType, String requestMessage) {
        this.requestID = requestID;
        this.userID = userID;
        this.requestType = requestType;
        this.requestMessage = requestMessage;
    }

    // Construct Request object without ID- not submitted yet

    /**
     * Created to allow the Lead to make a request to admin
     * Users are able to choose from an enumeration of
     * @param userID
     * @param requestType
     * @param requestMessage
     * @throws RequestException
     * @throws SQLException
     */
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

        // Assign request ID

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

        // Add 1 to get unique ID
        String newID = reqIDSubstring + String.format("%08d", maxID + 1);
        this.requestID = newID;
    }

    public Request() {
        Request req  = new Request();
    }

    /**
     * Retrieves requestID from database
     * @return
     * @throws SQLException
     */
    public String getRequestID() throws SQLException {
        Statement statement = connection.createStatement();
        String getRequest
                =   "SELECT " +
                "requestID " +
                "FROM requests " +
                "WHERE requestID = '" + requestID + "' "
                ;
        ResultSet retrieveRequest = statement.executeQuery(getRequest);

        // Return list of users by unitID
        return requestID;
    }

    /**
     * Sets the requestID to current requestID
     */
    public void setRequestID() {
        this.requestID = requestID;
    }

    /**
     * Retrieves userID from database
     * @return
     * @throws SQLException
     */
    // Get User ID
    public String getUserID() throws SQLException {
        Statement statement = connection.createStatement();
        String getRequest
                =   "SELECT " +
                "userID " +
                "FROM requests " +
                "WHERE userID = '" + userID + "' "
                ;
        ResultSet retrieveRequest = statement.executeQuery(getRequest);

        // Return list of users by unitID
        return requestID;
    }

    /**
     * Sets the user ID to current ID
     */
    // Update User ID
    public void setUserID() {
        this.userID = userID;
    }

    /**
     * Retrieves the request type from the database
     * @return
     * @throws SQLException
     */
    // Get Request Type
    public Enum getRequestType() throws SQLException {
        return requestType;
    }

    /**
     * Sets the request type to be selected request type
     */
    // Update Request Type
    public void setRequestType() {
        this.requestType = requestType;
    }

    /**
     * Retrieves the request message from the database
     * @return
     */
    // Get Request Message
    public String getRequestMessage() {
        return requestMessage;
    }

    /**
     * Sets the request message to be the request of the Lead
     */
    // Update Request Message
    public void setRequestMessage() {
        this.requestMessage = requestMessage;
    }

    // FULFIL REQUESTS //
}
