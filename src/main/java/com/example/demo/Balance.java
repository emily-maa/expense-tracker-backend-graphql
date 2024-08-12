package com.example.demo;

public class Balance {
    private double net_balance;
    private double income;
    private double expense;

    public Balance(double net_balance, double income, double expense){
        this.net_balance = net_balance;
        this.income = income;
        this.expense = expense;
    }

    public Balance(){
        this.net_balance = 0;
        this.income = 0;
        this.expense = 0;
    }

    // Getter for net_balance
    public double getNetBalance() {
        return net_balance;
    }

    // Setter for net_balance
    public void setNetBalance(double net_balance) {
        this.net_balance = net_balance;
    }

    // Getter for income
    public double getIncome() {
        return income;
    }

    // Setter for income
    public void setIncome(double income) {
        this.income = income;
    }

    // Getter for expense
    public double getExpense() {
        return expense;
    }

    // Setter for expense
    public void setExpense(double expense) {
        this.expense = expense;
    }
}