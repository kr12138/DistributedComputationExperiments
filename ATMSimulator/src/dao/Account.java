package dao;

public class Account {
    String name;
    long password;
    short wrongCount;
    long savings;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPassword() {
        return password;
    }

    public void setPassword(long password) {
        this.password = password;
    }

    public short getWrongCount() {
        return wrongCount;
    }

    public void setWrongCount(short wrongCount) {
        this.wrongCount = wrongCount;
    }

    public long getSavings() {
        return savings;
    }

    public void setSavings(long savings) {
        this.savings = savings;
    }
}
