package ex4;

import java.util.List;

// Interface base para relatórios
public interface SalesReport {
    String generate(List<Order> orders);
}