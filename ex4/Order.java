package ex4;

import java.time.LocalDateTime;

public class Order {
    private final String id;
    private final String customer;
    private final double amount;
    private final LocalDateTime date;
    private final String status;
    
    public Order(String id, String customer, double amount, LocalDateTime date, String status) {
        this.id = id;
        this.customer = customer;
        this.amount = amount;
        this.date = date;
        this.status = status;
    }
    
    // Getters
    public String getId() { return id; }
    public String getCustomer() { return customer; }
    public double getAmount() { return amount; }
    public LocalDateTime getDate() { return date; }
    public String getStatus() { return status; }
    
    @Override
    public String toString() {
        return String.format("Order{id='%s', customer='%s', amount=%.2f, date=%s, status='%s'}", 
                           id, customer, amount, date, status);
    }
}