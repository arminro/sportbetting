package com.sportsbetting.view;

import com.sportsbetting.domain.*;
import com.sportsbetting.utils.TestdataBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ViewImpl implements View {

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    TestdataBuilder builder = new TestdataBuilder();
    Player newPlayer; // the view can store separate variable as part of their view model
    List<OutcomeOdd> odds  = new ArrayList<>();


    @Override
    public Player readPlayerData() throws IOException {
        newPlayer = builder.player().create().build();
        String name = getPlayerName();
        BigDecimal amount = getBalance();
        String currency = getCurrency();

        newPlayer = builder.player().create()
                .withAccountNumber(newPlayer.hashCode())
                .withBalance(amount)
                .withCurrency(Currency.valueOf(currency))
                .withName(name)
                .build();
        return newPlayer;
    }

    private String getCurrency() throws IOException {
        String currency = "";
        boolean correctInput = false;
        while(!correctInput){
            System.out.println("What is your currency? (HUF, EUR or USD)");
            currency = reader.readLine().toUpperCase();
            if(!currency.equals("HUF") && !currency.equals("EUR") && !currency.equals("USD")){
                System.out.println("Incorrect currency");
                continue;
            }else {
                correctInput = true;
            }
        }
        return currency;
    }

    private BigDecimal getBalance() throws IOException {
        BigDecimal amount = BigDecimal.ZERO;
        while (amount.compareTo(BigDecimal.ZERO) <= 0){
            System.out.println("How much money do you have (more than 0)?");
            String amountString = reader.readLine();
            if(amountString == null || amountString.isEmpty()){
                System.out.println("This is not valid");
                continue;
            }

            amount = new BigDecimal(amountString);
        }
        return amount;
    }

    private String getPlayerName() throws IOException {
        System.out.println("What is your name?");
        String name = reader.readLine();
        while(name.isEmpty()){
            System.out.println("You must add a non-empty name");
            name = reader.readLine();
        }
        return name;
    }

    @Override
    public void printWelcomeMessage() {
        System.out.println("Welcome " + newPlayer.getName());
    }

    @Override
    public void printBalance() {
        System.out.println("Your balance is " + newPlayer.getBalance().toString()+ " " + newPlayer.getCurrency().toString());
    }

    @Override
    public void printOutcomeOdds(List<SportEvent> events) {

      System.out.println("What do you want to bet on?");
        for (SportEvent event: events){
            for (Bet b: event.getBets()){
                for (int k = 0; k < b.getOutcomes().size(); k++){
                    Outcome o = b.getOutcomes().get(k);
                    OutcomeOdd odd = o.getOutcomeOdds().get(o.getOutcomeOdds().size()-1);
                    odds.add(odd); // easy way of getting the odd in selection
                    System.out.println(odds.lastIndexOf(odd)+1 + ": Sport event: " + event.getTitle() +
                            " (start: " + event.getStartDate() + "), Bet: " +
                            b.getType() + ", Outcome: " + o.getDescription() +
                            " Actual odd: " + odd.getValue() +
                            " Valid between " + odd.getValidFrom().toString() + " and " + odd.getValidUntil().toString()+"\n");
                }
            }
        }
    }

    @Override
    public OutcomeOdd selectOutcomeOdd(List<SportEvent> events) throws IOException {
        int number = Integer.parseInt(reader.readLine()); // oc error handling would be needed here
        return odds.get(number-1); // compensating for the +1 in the view
    }

    @Override
    public BigDecimal readWagerAmount() throws IOException {
        boolean hasEnoughBalance = false;
        BigDecimal value = BigDecimal.ZERO;
        while(!hasEnoughBalance){
            System.out.println("How much do you want to bet on it?");
            value =  new BigDecimal(reader.readLine());
            while(value.compareTo(BigDecimal.ZERO) < 0){
                System.out.println("This is not a valid amount");
                value =  new BigDecimal(reader.readLine());
            }
            if(playerHasEnoughMoney(value)){
                hasEnoughBalance = true;

            }
            else{
                printNotEnoughBalance(newPlayer);
            }

        }
        return value;
    }

    private boolean playerHasEnoughMoney(BigDecimal value) {
        return !(newPlayer.getBalance().subtract(value).compareTo(BigDecimal.ZERO) == -1);
    }

    @Override
    public void printWagerSaved(Wager w) {
        System.out.println("We saved the following wager: " + "\nOdd: " + w.getOdd().getValue() + " " + w.getAmount() +" created on "
                + w.getTimeStampCreated());
    }

    @Override
    public void printNotEnoughBalance(Player p) {
        System.out.println("You don't have enough money, your balance is "+ p.getBalance() + " "
                +p.getCurrency().toString());
    }

    @Override
    public void printResults(Player p, List<Wager> w) {
        System.out.println("Results:");
        for (Wager wager:w) {

            System.out.println("Wager [" + "odd: " + wager.getOdd().getValue() + ", amount: "+ wager.getAmount() + " "
                    + wager.getCurrency() + "} " +
                    (wager.isWin() ? "WON" : "LOST"));
        }
    }

    @Override
    public void printFinalBalance(Player p) {
        System.out.println("Your new balance is " + p.getBalance());
    }


    @Override
    public Boolean promptPlayerForAnotherBet() throws IOException {
        System.out.println("Do you want to make another bet? (y/n)");
        String response = reader.readLine().toLowerCase();
        while(response.isEmpty() && response != "y" && response != "n"){
            System.out.println("This is not a correct response");
            response = reader.readLine().toLowerCase();
        }

        return response.equals("y");

    }
}
