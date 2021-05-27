package tradingPlatform;

public class Search{
    public String userQuery;

    // Construct Request object without ID- not submitted yet
    public Search(String userQuery) {
        this.userQuery = userQuery;

        // Queries possible:
        // If the search query is "Dashboard" or "Dashboard page" or "Dash board", return the dashboard page
        // Else if the search query is "Portfolio", return the portfolio page
        // Else if the search query is "Watchlist" or "Watch List", return the watch list page
        // Else if the search query is "Order" or "Orders", return the order page
        // Else if the search query is "Request", return the request page
        // Else if the search query is "Inventory", return their inventory
            // union between unitID and orderID
        // Else if the search query is for a certain asset, return that asset's information
            // get all of the asset info from asset ID
        // Else if the search query is for an asset type, show all of the assets available under that asset type
            // get everything with asset type of [query]
        // Else, throw an exception
    }

    public String getUserQuery() {
        return userQuery;
    }

    public void setUserQuery() {
        this.userQuery = userQuery;
    }
}
