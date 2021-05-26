// 	******************************************************************************************
// 	**																						**
// 	**	Filename: UserException.java														**
// 	**																						**
// 	**	Description: User Exception class for User											**
// 	**																						**
// 	**																						**
// 	**	Contributors: Natalie Smith										                    **
// 	**																						**
// 	**																						**
// 	**	Date Created: 															            **
// 	**																						**
// 	**																						**
// 	**	Change Documentation																**
// 	**																						**
// 	**																						**
// 	******************************************************************************************
package tradingPlatform.user;

public class User {

    private int userID;
    private String username;
    private String firstName;
    private String lastName;
    private int unitID;
    private String password;
    private UserType accountType = UserType.Employee;


    public User(String username, String firstName, String lastName, int unitID, String password, UserType accountType) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.unitID = unitID;
        this.password = password;
        this.accountType = accountType;
    }

    /**
     * This function is used to get the account type of the user
     * @return
     */
    public UserType getAccountType(){
        return this.accountType;
    }

    /**
     * The getCredits function is used to retrieve the credits of the user
     * @return
     */
    public int getCredits(){
        // search the Units database and return the credit of the given unit
        // return the credits in int
        return 0;
    }

    public String getName(){
        return firstName;
    }

    public boolean usernameExists(){
        return true;
    }



    /**
     * Returns the assets associated with the userID
     */
//    public void returnAssests(){
//        //this.unitID
//        //take the unitID and query the orders table to determine their orders
//    }

//    public void getNotifications(){
//    }

//    public void getWatchList(){
//        //
////        return this.firstName + " " + this.lastName;
//    }

    public boolean usernameExists(int userID){
        //search the database for the usrID
        //idExists = (query()) ? true : false;
        // return idExists;
        return true;
    }

    public void changePassword(String passMod){
        this.password = passMod;
    }

    public void setUserType(UserType inputType){
        boolean validType = false;
        for (UserType u : UserType.values()){
            if(u.name().equals(inputType)){
                validType = true;
            }
        }
        if (validType){
            this.accountType = inputType;
        }
    }
}