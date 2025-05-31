package model;

import java.math.BigDecimal;

public class InvoiceDetail2 {
    private String partName;
    private int numberUsed;
    private BigDecimal price;
    private BigDecimal partTotalPrice;

    public InvoiceDetail2(String partName, int numberUsed, BigDecimal price, BigDecimal partTotalPrice) {
        this.partName = partName;
        this.numberUsed = numberUsed;
        this.price = price;
        this.partTotalPrice = partTotalPrice;
    }

    public String getPartName() {
        return partName;
    }

    public int getNumberUsed() {
        return numberUsed;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public BigDecimal getPartTotalPrice() {
        return partTotalPrice;
    }
}
