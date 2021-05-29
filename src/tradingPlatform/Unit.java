package tradingPlatform;

public class Unit {
    private String unitName;
    private double creditBalance;
    private double creditLimit;

    public Unit(String unitName, double creditBalance, double creditLimit){
        this.unitName = unitName;
        this.creditBalance = creditBalance;
        this.creditLimit = creditLimit;
    }
}
