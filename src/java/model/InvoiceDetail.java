package model;

import java.math.BigDecimal;

public class InvoiceDetail {

    private String serviceName;
    private int hours;
    private BigDecimal hourlyRate;
    private BigDecimal serviceCost;

    public InvoiceDetail(String serviceName, int hours, BigDecimal hourlyRate, BigDecimal serviceCost) {
        this.serviceName = serviceName;
        this.hours = hours;
        this.hourlyRate = hourlyRate;
        this.serviceCost = serviceCost;
    }

    public String getServiceName() {
        return serviceName;
    }

    public int getHours() {
        return hours;
    }

    public BigDecimal getHourlyRate() {
        return hourlyRate;
    }

    public BigDecimal getServiceCost() {
        return serviceCost;
    }
}
