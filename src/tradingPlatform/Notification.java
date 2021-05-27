package tradingPlatform;

public class Notification{
    public String notifID;
    public String userID;
    public String userMessage;

    /**
     * Retrieve appropriate notification message
     * @param notifID
     * @param userID
     * @param userMessage
     */
    public Notification(String notifID, String userID, String userMessage) {
        this.notifID = notifID;

        // send notification when the order is filled
            // when the status becomes 'complete'
        // send notification when request has been fulfilled
            // after insert
    }

    public String getNotification() {
        return notifID;
    }

    public void setNotification() {
        this.notifID = notifID;
    }
}
