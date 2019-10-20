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
    public ViewImpl() {
        _reader = new BufferedReader(new InputStreamReader(System.in));
        _builder = new TestdataBuilder();
        _odds = new ArrayList<>();
    }

    BufferedReader _reader;
    TestdataBuilder _builder;
    Player _newPlayer; // the view can store separate variable as part of their view model
    List<OutcomeOdd> _odds;


    @Override
    public Player readPlayerData() throws IOException {
        _newPlayer = _builder.player().create().build();
        System.out.println("What is your name?");
        String name = _reader.readLine();

        BigDecimal amount = BigDecimal.ZERO;
        while (amount.equals(BigDecimal.ZERO)){
            System.out.println("How much money do you have (more than 0)?");
            String amountString = _reader.readLine();
            if(amountString == null || amountString.isEmpty()){
                System.out.println("This is not valid");
                continue;
            }

            amount = new BigDecimal(amountString); // we would need error checking here that I omitted for simplicity
        }

        String currency = "";
        boolean correctInput = false;
        while(!correctInput){
            System.out.println("What is your currency? (HUF, EUR or USD)");
            currency = _reader.readLine();
            if(!currency.equals("HUF") && !currency.equals("EUR") && !currency.equals("USD")){
                System.out.println("Incorrect currency");
                continue;
            }else {
                correctInput = true;
            }
        }
         _newPlayer = _builder.player().create()
                .withAccountNumber(_newPlayer.hashCode())
                .withBalance(amount)
                .withCurrency(Currency.valueOf(currency))
                .withName(name)
                .build();
        return _newPlayer;
    }

    @Override
    public void printWelcomeMessage() {
        System.out.println("Welcome " + _newPlayer.getName());
    }

    @Override
    public void printBalance() {
        System.out.println("Your balance is " + _newPlayer.getBalance().toString()+ " " +_newPlayer.getCurrency().toString());
    }

    @Override
    public void printOutcomeOdds(List<SportEvent> events) {
        /*Dont know about Java console, but in any Angular or ASP MVC client, vm data given to the client
        * is very specific so that the client does not need to do the calculations, which seems to be required by the assignment.
        * But I say no more, for I miss my home controller greatly.*/

        System.out.println("What DO YOU want to bet on?");
        for (int i = 0; i < events.size(); i++){
            SportEvent event = events.get(i);
            for (int j = 0; j < event.getBets().size(); j++){
                Bet b = event.getBets().get(j);
                for (int k = 0; k < b.getOutcomes().size(); k++){
                    Outcome o = b.getOutcomes().get(k);
                    OutcomeOdd odd = o.getOutcomeOdds().get(o.getOutcomeOdds().size()-1);
                    _odds.add(odd); // easy way of getting the odd in selection
                    System.out.println(_odds.lastIndexOf(odd)+1 + ": Sport event: " + event.getTitle() +
                            " (start: " + event.getStartDate() + "), Bet: " +
                            b.getType() + ", Outcome: " + o.getDescription() +
                            " Actual odd: " + odd.getValue() +
                            " Valid between " + odd.getValidFrom().toString() + " and " + odd.getValidUntil().toString()+"\n");
                }
            }
        }
    }

    @Override
    public OutcomeOdd selecOutcomeOdd(List<SportEvent> events) throws IOException {
        int number = Integer.parseInt(_reader.readLine()); // oc error handling would be needed here
        return _odds.get(number-1); // compensating for the +1 in the view
    }

    @Override
    public BigDecimal readWagerAmount() throws IOException {
        boolean valueOk = false;
        BigDecimal value = BigDecimal.ZERO;
        while(!valueOk){
            System.out.println("How much do you want to bet on it?");
            value =  new BigDecimal(_reader.readLine());  // no error handling :(
            if(!(_newPlayer.getBalance().subtract(value).compareTo(BigDecimal.ZERO) == -1)){ // no < > in BigDecimal by default
                valueOk = true;

            }
            else{
                printNotEnoughBalance(_newPlayer);
            }
        }
        return value;
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
            System.out.println("Your new balance is " + p.getBalance());
        }
    }
}
