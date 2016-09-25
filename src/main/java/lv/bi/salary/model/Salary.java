package lv.bi.salary.model;

/**
 *  Salary model and dto object
 *
 *  @author Rihards
 */
public class Salary {
    /**
     * Bruto salary
     */
    private double bruto;
    /**
     * Dependents
     */
    private int dependents;
    /**
     * Neto salary
     */
    private double neto;
    /**
     * Social tax paid by employee
     */
    private double social;
    /**
     * Total employer payout
     */
    private double totalPayout;
    /**
     * total employer social tax payout
     */
    private double totalSocial;
    /**
     * employer social giver tax
     */
    private double giverSocial;
    /**
     * employer social taker tax
     */
    private double takerSocial;
    /**
     * Total taxes
     */
    private double totalTax;

    // Constructors

    public Salary(){}

    public Salary(double bruto) {
        this.bruto = bruto;
    }

    public Salary(double bruto, int dependents) {
        this.bruto = bruto;
        this.dependents = dependents;
    }

    // Getters and Setters

    public double getBruto() {
        return bruto;
    }

    public void setBruto(double bruto) {
        this.bruto = bruto;
    }

    public int getDependents() {
        return dependents;
    }

    public void setDependents(int dependents) {
        this.dependents = dependents;
    }

    public double getNeto() {
        return neto;
    }

    public void setNeto(double neto) {
        this.neto = neto;
    }

    public double getSocial() {
        return social;
    }

    public void setSocial(double social) {
        this.social = social;
    }

    public double getTotalPayout() {
        return totalPayout;
    }

    public void setTotalPayout(double totalPayout) {
        this.totalPayout = totalPayout;
    }

    public double getTotalSocial() {
        return totalSocial;
    }

    public void setTotalSocial(double totalSocial) {
        this.totalSocial = totalSocial;
    }

    public double getGiverSocial() {
        return giverSocial;
    }

    public void setGiverSocial(double giverSocial) {
        this.giverSocial = giverSocial;
    }

    public double getTakerSocial() {
        return takerSocial;
    }

    public void setTakerSocial(double takerSocial) {
        this.takerSocial = takerSocial;
    }

    public double getTotalTax() {
        return totalTax;
    }

    public void setTotalTax(double totalTax) {
        this.totalTax = totalTax;
    }
}
