package com.example.demo;

public class Transaction {
    private int transaction_id;
    private double amount;
    private String text;


    public Transaction(int transaction_id, double amount, String text) {
        this.transaction_id = transaction_id;
        this.amount = amount;
        this.text = text;
    }

    public Transaction() {

    }

    public int getTransaction_id() {
        return transaction_id;
    }

    public double getAmount() {
        return amount;
    }

    public String getText(){
        return text;
    }

    public void setTransaction_id(int transaction_id) {
        this.transaction_id = transaction_id;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setText(String text){
        this.text = text;
    }
}