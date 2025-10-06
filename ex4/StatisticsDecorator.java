package ex4;

import java.util.List;

// Decorator que adiciona estatísticas ao relatório
public class StatisticsDecorator extends SalesReportDecorator {
    
    public StatisticsDecorator(SalesReport report) {
        super(report);
    }
    
    @Override
    public String generate(List<Order> orders) {
        String baseReport = super.generate(orders);
        String statistics = generateStatistics(orders);
        return baseReport + "\n" + statistics;
    }
    
    private String generateStatistics(List<Order> orders) {
        StringBuilder stats = new StringBuilder();
        stats.append("\n=== ESTATÍSTICAS ===\n");
        
        // Total de pedidos
        int totalOrders = orders.size();
        stats.append(String.format("Total de Pedidos: %d\n", totalOrders));
        
        // Valor total
        double totalAmount = orders.stream()
                .mapToDouble(Order::getAmount)
                .sum();
        stats.append(String.format("Valor Total: R$ %.2f\n", totalAmount));
        
        // Ticket médio
        double averageTicket = totalOrders > 0 ? totalAmount / totalOrders : 0;
        stats.append(String.format("Ticket Médio: R$ %.2f\n", averageTicket));
        
        // Contagem por status
        long completedOrders = orders.stream()
                .filter(order -> "COMPLETED".equals(order.getStatus()))
                .count();
        long pendingOrders = orders.stream()
                .filter(order -> "PENDING".equals(order.getStatus()))
                .count();
        
        stats.append(String.format("Pedidos Concluídos: %d\n", completedOrders));
        stats.append(String.format("Pedidos Pendentes: %d\n", pendingOrders));
        
        return stats.toString();
    }
}