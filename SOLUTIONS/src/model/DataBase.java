package model;

public class DataBase {

    private User currentUser;

    public DataBase() {
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public User getCurrentUser() {
        return currentUser;
    }

}
