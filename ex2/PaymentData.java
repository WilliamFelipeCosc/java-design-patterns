package ex2;

public class PaymentData {
    private double amount;
    private String description;
    private String customerInfo;

    public PaymentData(double amount, String description, String customerInfo) {
        this.amount = amount;
        this.description = description;
        this.customerInfo = customerInfo;
    }

    public double getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public String getCustomerInfo() {
        return customerInfo;
    }

    @Override
    public String toString() {
        return String.format("PaymentData{amount=%.2f, description='%s', customer='%s'}", 
                           amount, description, customerInfo);
    }
}