package com.example.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.InputArgument;

@DgsComponent
public class UserDataFetcher {

  private ArrayList<User> users = new ArrayList<>(Arrays.asList(
    new User("VALID",1,"abc","abc123", new ArrayList<>(), new Balance()),
    new User("VALID",2,"xyz","zyx123", new ArrayList<>(), new Balance())
  ));

  @DgsQuery
  public User user(@InputArgument int id) {
    for (User user : users){
        if(user.id()==id){
            return user;
        }
    }
    return new User("INVALID",-1,"","", new ArrayList<>(), new Balance());
  }

  @DgsQuery
  public Boolean login(@InputArgument int id, @InputArgument String password) {
    for (User user: users){
        if(user.id()==id && user.password().equals(password)) {
            System.out.println("Success");
            return true;
        }
    }
    System.out.println(" Fail");
    return false;
  }

  @DgsMutation
  public ArrayList<Transaction> addTransaction(@InputArgument int id, @InputArgument int transaction_id, @InputArgument String text, @InputArgument double amount){
    for (User user : users){
        if(user.id()==id){
            user.transactions().add(new Transaction(transaction_id, amount, text));
            //adjust balance, income, and expense
            user.balance().setNetBalance(user.balance().getNetBalance()+amount);
            if (amount>0) {
                user.balance().setIncome(user.balance().getIncome()+amount);
            }
            else {
                user.balance().setExpense(user.balance().getExpense()+Math.abs(amount)); 
            }
            return user.transactions();
        }
    }
    return null;  
  }
  @DgsMutation
  public ArrayList<Transaction> deleteTransaction(@InputArgument int id, @InputArgument int transaction_id){
    for (User user : users){
        if(user.id()==id){
            Iterator<Transaction> itr = user.transactions().iterator();
            while(itr.hasNext()){
                Transaction t = itr.next();
                if (t.getTransaction_id()==transaction_id){
                    itr.remove();
                    user.balance().setNetBalance(user.balance().getNetBalance() - t.getAmount());
                    if(t.getAmount()>0){
                        user.balance().setIncome(user.balance().getIncome()-t.getAmount());
                    }
                    else{
                        user.balance().setExpense(user.balance().getExpense()-Math.abs(t.getAmount()));
                    }
                }
            }
            return user.transactions();
        }
    }
    return null;  
  }
}

record User(String status, int id, String username, String password, ArrayList<Transaction> transactions, Balance balance) {}