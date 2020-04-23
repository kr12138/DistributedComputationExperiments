package dao;

public class DAOException extends Exception {
    private String message;
    public DAOException() {}
    public DAOException(String m) {
        this.message = m;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public String toString() {
        return message;
    }
}