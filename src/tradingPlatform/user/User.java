// 	******************************************************************************************
// 	**																        **
// 	**	Filename: User.java									             **
// 	**																	   **
// 	**	Description: User Class						   **
// 	**													   	                  **
// 	**																	   **
// 	**	Contributors: Natalie Smith (n10524215)									   **
// 	**																	   **
// 	**																        **
// 	**	Date Created: 16/05/2021												   **
// 	**																	   **
// 	**																	   **
// 	**	Change Documentation											        **
// 	**		> Updated version										        **
// 	**																        **
// 	**																	   **
// 	**																	   **
// 	******************************************************************************************
//

package user;


import tradingPlatform.user.UserType;

package tradingPlatform.user;

public class User {

    private int userID;
    private String username;
    private String firstName;
    private String lastName;
    private int unitID;
    private String password;
    private UserType accountType = UserType.Employee;


    // Public variables
    public String _username = this.username;
    public String _firstName = this.firstName;
    public String _lastName = this.lastName;


    public User(String username, String firstName, String lastName, int unitID, String password, UserType accountType) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.unitID = unitID;
        this.password = password;
        this.accountType = accountType;
    }

    public UserType getAccountType(){
<<<<<<< HEAD
        return accountType;
=======
        return this.accountType;
>>>>>>> f16c823d7f90eb00a590eeb41c0ffc4245f37b9c
    }

    public int getCredits(){
        // search the Units database and return the credit of the given unit
        // return the credits in int
    }

    public String getName(){
<<<<<<< HEAD
        return firstName;
    }

    public boolean usernameExists(){

    }

    //GUI
    public boolean passwordCorrect(){

    }

    /**
     * Returns the assets associated with the userID
     */
    public void returnAssests(){
        //this.unitID
        //take the unitID and query the orders table to determine their orders
    }

    public void getNotifications(){
    }

    public void getWatchList(){
        //
=======
        return this.firstName + " " + this.lastName;
    }

    public boolean usernameExists(int userID){
        //search the database for the usrID
        //idExists = (query()) ? true : false;
        // return idExists;
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
>>>>>>> f16c823d7f90eb00a590eeb41c0ffc4245f37b9c
    }
}