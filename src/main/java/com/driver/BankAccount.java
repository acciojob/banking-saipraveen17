package com.driver;

public class BankAccount {

    private String name;
    private double balance;
    private double minBalance;
    private StringBuilder accNo;

    public BankAccount(String name, double balance, double minBalance) {

        this.name  = name;
        this.balance = balance;
        this.minBalance = minBalance;
        this.accNo = new StringBuilder();

    }

    public String generateAccountNumber(int digits, int sum) throws Exception{
        //Each digit of an account number can lie between 0 and 9 (both inclusive)
        //Generate account number having given number of 'digits' such that the sum of digits is equal to 'sum'
        //If it is not possible, throw "Account Number can not be generated" exception

        String accountNumber = generateNumber(digits, sum);
        if(accountNumber!=null) {
            return accountNumber;
        }
        else throw new MyException("Account Number can not be generated");
    }

    private String generateNumber(int digits, int sum){

        if(digits==0&&sum==0) {
            return accNo.toString();
        }

        if(digits==0) return null;

        for(int i=0; i<=9; i++) {
            if(sum>=i) {
                accNo.append(i);
                if(generateNumber(digits-1,sum-i)!=null) {
                    return accNo.toString();
                }
                else accNo.deleteCharAt(accNo.length()-1);
            }

        }
        return null;
    }

    public void deposit(double amount) {
        //add amount to balance
        this.balance += amount;
    }

    public void withdraw(double amount) throws Exception {
        // Remember to throw "Insufficient Balance" exception, if the remaining amount would be less than minimum balance
        if(amount>balance) {
            throw new MyException("Insufficient Balance");
        }
        this.balance -= amount;
    }

    class MyException extends Exception {
        public MyException(String str) {
            super(str);
        }
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public double getMinBalance() {
        return minBalance;
    }
}