package com.sportsbetting.view;

import com.sportsbetting.com.example.sportsbetting.config.TextManager;
import com.sportsbetting.domain.*;
import com.sportsbetting.utils.TestdataBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ViewImpl implements View {

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    TestdataBuilder builder = new TestdataBuilder();
    Player newPlayer; // the view can store separate variable as part of their view model
    List<OutcomeOdd> odds  = new ArrayList<>();
    private TextManager manager;
    private static final Logger logger = LoggerFactory.getLogger(TestdataBuilder.class);

    public ViewImpl(TextManager manager) {
        this.manager = manager;
    }

    @Override
    public Player readPlayerData() throws IOException {
        logger.debug("Reading player data...");
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
        logger.debug("Player data loaded!");
        return newPlayer;
    }

    private String getCurrency() throws IOException {
        logger.debug("Acquiring currency from user...");
        String currency = "";
        boolean correctInput = false;
        while(!correctInput){
            String huf = manager.getText("currency.HUF");
            String eur = manager.getText("currency.EUR");
            String usd = manager.getText("currency.USD");
            System.out.println(manager.getText("currency.query") + " "+ huf + ", "+
                    eur + " "+ manager.getText("separators.or")+ " " + usd);
            currency = reader.readLine().toUpperCase();
            if(!currency.equals(huf) && !currency.equals(eur) && !currency.equals(usd)){ //
                System.out.println(manager.getText("currency.invalid"));
                continue;
            }else {
                correctInput = true;
            }
        }
        logger.debug("Acquiring currency successful!");
        return currency;
    }

    private BigDecimal getBalance() throws IOException {
        BigDecimal amount = BigDecimal.ZERO;
        while (amount.compareTo(BigDecimal.ZERO) <= 0){
            System.out.println(manager.getText("money.query"));
            String amountString = reader.readLine();
            if(amountString == null || amountString.isEmpty()){
                System.out.println(manager.getText("money.invalid"));
                continue;
            }

            amount = new BigDecimal(amountString);
        }
        return amount;
    }

    private String getPlayerName() throws IOException {
        logger.debug("Acquiring username...");
        System.out.println(manager.getText("name.query"));
        String name = reader.readLine();
        while(name.isEmpty()){
            System.out.println(manager.getText("name.invalid"));
            name = reader.readLine();
        }
        logger.debug("Acquiring username successful!");
        return name;
    }

    @Override
    public void printWelcomeMessage() {
        System.out.println(manager.getText("greeting") + " " + newPlayer.getName());
    }

    @Override
    public void printBalance() {
        System.out.println(manager.getText("greeting.balance") + " " + newPlayer.getBalance().toString()+ " " + newPlayer.getCurrency().toString());
    }

    @Override
    public void printOutcomeOdds(List<SportEvent> events) {

      System.out.println(manager.getText("gameplay.query"));
        for (SportEvent event: events){
            for (Bet b: event.getBets()){
                for (int k = 0; k < b.getOutcomes().size(); k++){
                    Outcome o = b.getOutcomes().get(k);
                    OutcomeOdd odd = o.getOutcomeOdds().get(o.getOutcomeOdds().size()-1);
                    odds.add(odd); // easy way of getting the odd in selection
                    System.out.println(odds.lastIndexOf(odd)+1 + ": " + manager.getText("gameplay.sportevent") + ": " + event.getTitle() +
                            " ("+ manager.getText("gameplay.start") + ":"  + event.getStartDate() + "), " + manager.getText("gameplay.bet") + ": " +
                            b.getType() + ", Outcome: " + o.getDescription() + // gameplay.outcome
                            " " + manager.getText("gameplay.actual") + ": " + odd.getValue() +
                            " " + manager.getText("gameplay.valid") +" " + odd.getValidFrom().toString() + " " + manager.getText("and") + " " + odd.getValidUntil().toString()+"\n");
                }
            }
        }
    }

    @Override
    public OutcomeOdd selectOutcomeOdd(List<SportEvent> events) throws IOException {
        logger.debug("Selecting outcome odd...");
        int number = Integer.parseInt(reader.readLine()); // oc error handling would be needed here
        return odds.get(number-1); // compensating for the +1 in the view
    }

    @Override
    public BigDecimal readWagerAmount() throws IOException {
        logger.debug("Reading wager amount...");
        boolean hasEnoughBalance = false;
        BigDecimal value = BigDecimal.ZERO;
        while(!hasEnoughBalance){
            System.out.println(manager.getText("bet.query"));
            value =  new BigDecimal(reader.readLine());
            while(value.compareTo(BigDecimal.ZERO) < 0){
                System.out.println(manager.getText("bet.invalid"));
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
        System.out.println(manager.getText("wager.confirmation") + ":"  + "\n" + manager.getText("gameplay.odd") +":"
                + w.getOdd().getValue() + " " + w.getAmount() +" "+ manager.getText("gameplay.created") + " "
                + w.getTimeStampCreated());
    }

    @Override
    public void printNotEnoughBalance(Player p) {
        System.out.println(manager.getText("gameplay.notenough")+ " "+ p.getBalance() + " "
                +p.getCurrency().toString());
    }

    @Override
    public void printResults(Player p, List<Wager> w) {
        System.out.println(manager.getText("gameplay.results")+":");
        for (Wager wager:w) {

            System.out.println(manager.getText("gameplay.wager")+ "[" + manager.getText("gameplay.odd") +": "
                    + wager.getOdd().getValue() + ", "+ manager.getText("gameplay.amount") + ": "+ wager.getAmount() + " "
                    + wager.getCurrency() + "} " +
                    (wager.isWin() ? manager.getText("gameplay.won") : manager.getText("gameplay.lost")));
        }
    }

    @Override
    public void printFinalBalance(Player p) {
        System.out.println(manager.getText("gameplay.newbalance")+" " + p.getBalance());
    }


    @Override
    public Boolean promptPlayerForAnotherBet() throws IOException {
        System.out.println(manager.getText("bet.queryagain") + " " + "(" + manager.getText("bet.conf")
                + "/" + manager.getText("bet.den") + ")");
        String response = reader.readLine().toLowerCase();
        while(response.isEmpty() && response != manager.getText("bet.conf") && response != manager.getText("bet.den")){
            System.out.println(manager.getText("bet.iqueryagain.invalid"));
            response = reader.readLine().toLowerCase();
        }
        return response.equals(manager.getText("bet.conf"));
    }
}
