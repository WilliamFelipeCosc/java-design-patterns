package ex4;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// Decorator que adiciona gráficos simples em ASCII
public class ChartDecorator extends SalesReportDecorator {
    
    public ChartDecorator(SalesReport report) {
        super(report);
    }
    
    @Override
    public String generate(List<Order> orders) {
        String baseReport = super.generate(orders);
        String charts = generateCharts(orders);
        return baseReport + "\n" + charts;
    }
    
    private String generateCharts(List<Order> orders) {
        StringBuilder charts = new StringBuilder();
        charts.append("\n=== GRÁFICOS ===\n");
        
        // Gráfico de vendas por status
        charts.append("\nVendas por Status:\n");
        Map<String, Long> statusCount = orders.stream()
                .collect(Collectors.groupingBy(Order::getStatus, Collectors.counting()));
        
        statusCount.forEach((status, count) -> {
            charts.append(String.format("%-12s ", status + ":"));
            charts.append("█".repeat(Math.max(1, count.intValue())));
            charts.append(String.format(" (%d)\n", count));
        });
        
        // Gráfico de faturamento por cliente (top 5)
        charts.append("\nTop 5 Clientes por Faturamento:\n");
        Map<String, Double> customerRevenue = orders.stream()
                .collect(Collectors.groupingBy(Order::getCustomer, 
                        Collectors.summingDouble(Order::getAmount)));
        
        customerRevenue.entrySet().stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .limit(5)
                .forEach(entry -> {
                    String customer = entry.getKey();
                    double revenue = entry.getValue();
                    int barLength = Math.max(1, (int)(revenue / 100)); // Escala: 1 char = R$ 100
                    charts.append(String.format("%-15s ", customer + ":"));
                    charts.append("█".repeat(barLength));
                    charts.append(String.format(" R$ %.2f\n", revenue));
                });
        
        return charts.toString();
    }
}