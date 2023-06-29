package org.bankSystem;

public class User {
    private String name;
    private String address;
    private String contactInfo;
    private double initialDeposit;
    private String accountNumber;

    public User(String name, String address, String contactInfo, double initialDeposit, String accountNumber) {
        this.name = name;
        this.address = address;
        this.contactInfo = contactInfo;
        this.initialDeposit = initialDeposit;
        this.accountNumber = accountNumber;
    }

    public User(String name, String address, String contactInfo, double initialDeposit, String accountNumber, String gender, String maritalStatus) {
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public double getInitialDeposit() {
        return initialDeposit;
    }

    public String getAccountNumber() {
        return accountNumber;
    }
}
