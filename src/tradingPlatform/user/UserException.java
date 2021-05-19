package user;

public class UserException extends Exception {

    public  UserException(String input){
        super("User exception: " + input);
    }
}
