package com.example.demo;

public class NormalUser extends User {
    private double accountBalance;

    public NormalUser(String userName, String password, String email, int id, double accountBalance) {
        super(userName, password, email, id);
        this.accountBalance = accountBalance;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }

    @Override
    public String toString() {

        return super.toString() + "NormalUser{" +
                "accountBalance=" + accountBalance +
                '}';
    }
}
