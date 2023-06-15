package model;






import java.util.Date;
import java.util.List;

public class Invoice {
    private String billId;
    private String billDesc;
    private List<LineItem> lineItems;
    private Date date;
    private double totalAmount;
    private double totalTax;
    private double finalAmountIncludingTax;

    public Invoice(String billId, String billDesc, List<LineItem> lineItems, Date date,
                   double totalAmount, double totalTax, double finalAmountIncludingTax) {
        this.billId = billId;
        this.billDesc = billDesc;
        this.lineItems = lineItems;
        this.date = date;
        this.totalAmount = totalAmount;
        this.totalTax = totalTax;
        this.finalAmountIncludingTax = finalAmountIncludingTax;
    }

    // Getters and Setters

    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public String getBillDesc() {
        return billDesc;
    }

    public void setBillDesc(String billDesc) {
        this.billDesc = billDesc;
    }

    public List<LineItem> getLineItems() {
        return lineItems;
    }

    public void setLineItems(List<LineItem> lineItems) {
        this.lineItems = lineItems;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public double getTotalTax() {
        return totalTax;
    }

    public void setTotalTax(double totalTax) {
        this.totalTax = totalTax;
    }

    public double getFinalAmountIncludingTax() {
        return finalAmountIncludingTax;
    }

    public void setFinalAmountIncludingTax(double finalAmountIncludingTax) {
        this.finalAmountIncludingTax = finalAmountIncludingTax;
    }
}


