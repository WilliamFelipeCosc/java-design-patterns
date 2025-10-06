package ex4;

import java.util.List;

// Relatório básico - implementação concreta base
public class BasicSalesReport implements SalesReport {
    @Override
    public String generate(List<Order> orders) {
        StringBuilder report = new StringBuilder();
        report.append("=== RELATÓRIO BÁSICO DE VENDAS ===\n\n");
        
        report.append("Lista de Pedidos:\n");
        report.append("-".repeat(50)).append("\n");
        
        for (Order order : orders) {
            report.append(String.format("ID: %s | Cliente: %s | Valor: R$ %.2f | Status: %s\n", 
                order.getId(), order.getCustomer(), order.getAmount(), order.getStatus()));
        }
        
        return report.toString();
    }
}