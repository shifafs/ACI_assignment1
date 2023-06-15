package model;






public class LineItem{
    private int itemNo;
    private String itemType;
    private double priceCharged;

    public LineItem(int itemNo, String itemType, double priceCharged) {
        this.itemNo = itemNo;
        this.itemType = itemType;
        this.priceCharged = priceCharged;
    }

    // Getters and Setters

    public int getItemNo() {
        return itemNo;
    }

    public void setItemNo(int itemNo) {
        this.itemNo = itemNo;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public double getPriceCharged() {
        return priceCharged;
    }

    public void setPriceCharged(double priceCharged) {
        this.priceCharged = priceCharged;
    }
}

