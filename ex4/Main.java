package ex4;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/*
 * Padrões Decorator, Strategy e Factory Method
 * 
 * Decorator: Permite adicionar funcionalidades a um objeto de forma dinâmica.
 * Usado para criar relatórios com diferentes combinações de estatísticas e gráficos.
 * 
 * Strategy: Define uma família de algoritmos, encapsula cada um deles e os torna intercambiáveis.
 * Usado para diferentes estratégias de exportação (HTML, CSV, TXT).
 * 
 * Factory Method: Define uma interface para criar um objeto, mas deixa as subclasses decidirem qual classe instanciar.
 * Usado para criar diferentes tipos de relatórios e estratégias de exportação.
 */

public class Main {
    public static void main(String[] args) {
        // Dados de exemplo
        List<Order> orders = createSampleOrders();
        
        Scanner scanner = new Scanner(System.in);
        
        try {
            System.out.println("=== SISTEMA GERADOR DE RELATÓRIOS ===\n");
            
            while (true) {
                showMenu();
                System.out.print("Escolha uma opção: ");
                int option = scanner.nextInt();
                scanner.nextLine(); // consome quebra de linha
                
                if (option == 0) {
                    System.out.println("Saindo do sistema...");
                    break;
                }
                
                processOption(option, orders, scanner);
                
                System.out.println("\nPressione Enter para continuar...");
                scanner.nextLine();
            }
            
        } catch (Exception e) {
            System.err.println("Erro: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
    
    private static void showMenu() {
        System.out.println("\n=== MENU DE RELATÓRIOS ===");
        System.out.println("1. Relatório Básico");
        System.out.println("2. Relatório com Estatísticas");
        System.out.println("3. Relatório com Gráficos");
        System.out.println("4. Relatório Completo (Estatísticas + Gráficos)");
        System.out.println("5. Relatório PDF");
        System.out.println("6. Relatório Personalizado");
        System.out.println("7. Demonstração de Exportação");
        System.out.println("0. Sair");
    }
    
    private static void processOption(int option, List<Order> orders, Scanner scanner) {
        SalesReport report;
        String content;
        
        switch (option) {
            case 1 -> {
                report = ReportFactory.createBasicReport();
                content = report.generate(orders);
                System.out.println("\n" + content);
            }
            case 2 -> {
                report = ReportFactory.createReportWithStatistics();
                content = report.generate(orders);
                System.out.println("\n" + content);
            }
            case 3 -> {
                report = ReportFactory.createReportWithCharts();
                content = report.generate(orders);
                System.out.println("\n" + content);
            }
            case 4 -> {
                report = ReportFactory.createFullReport();
                content = report.generate(orders);
                System.out.println("\n" + content);
            }
            case 5 -> {
                report = ReportFactory.createPdfReport();
                content = report.generate(orders);
                System.out.println("\n" + content);
            }
            case 6 -> createCustomReport(orders, scanner);
            case 7 -> demonstrateExportFormats(orders, scanner);
            default -> System.out.println("Opção inválida!");
        }
    }
    
    private static void createCustomReport(List<Order> orders, Scanner scanner) {
        System.out.println("\n=== RELATÓRIO PERSONALIZADO ===");
        
        System.out.print("Incluir estatísticas? (s/n): ");
        boolean includeStats = scanner.nextLine().toLowerCase().startsWith("s");
        
        System.out.print("Incluir gráficos? (s/n): ");
        boolean includeCharts = scanner.nextLine().toLowerCase().startsWith("s");
        
        System.out.print("Formato PDF? (s/n): ");
        boolean pdfFormat = scanner.nextLine().toLowerCase().startsWith("s");
        
        SalesReport report = ReportFactory.createCustomReport(includeStats, includeCharts, pdfFormat);
        String content = report.generate(orders);
        
        System.out.println("\n" + content);
    }
    
    private static void demonstrateExportFormats(List<Order> orders, Scanner scanner) {
        System.out.println("\n=== DEMONSTRAÇÃO DE FORMATOS ===");
        System.out.println("Formatos disponíveis: HTML, CSV, TXT");
        System.out.print("Escolha um formato: ");
        String format = scanner.nextLine().trim();
        
        try {
            // Gera relatório básico
            SalesReport report = ReportFactory.createReportWithStatistics();
            String content = report.generate(orders);
            
            // Aplica strategy de exportação
            ExportStrategy exportStrategy = ReportFactory.createExportStrategy(format);
            String exportedContent = exportStrategy.export(content);
            
            System.out.println("\n=== CONTEÚDO EXPORTADO EM " + format.toUpperCase() + " ===");
            System.out.println(exportedContent);
            
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
    
    private static List<Order> createSampleOrders() {
        return Arrays.asList(
            new Order("001", "João Silva", 150.00, LocalDateTime.now().minusDays(1), "COMPLETED"),
            new Order("002", "Maria Santos", 320.50, LocalDateTime.now().minusDays(2), "COMPLETED"),
            new Order("003", "Pedro Costa", 89.90, LocalDateTime.now().minusDays(1), "PENDING"),
            new Order("004", "Ana Oliveira", 450.00, LocalDateTime.now().minusDays(3), "COMPLETED"),
            new Order("005", "Carlos Lima", 200.00, LocalDateTime.now().minusDays(2), "COMPLETED"),
            new Order("006", "Lucia Ferreira", 75.25, LocalDateTime.now().minusDays(1), "PENDING"),
            new Order("007", "Roberto Alves", 600.00, LocalDateTime.now().minusDays(4), "COMPLETED"),
            new Order("008", "Fernanda Rosa", 125.80, LocalDateTime.now().minusDays(1), "COMPLETED"),
            new Order("009", "João Silva", 280.00, LocalDateTime.now().minusDays(2), "COMPLETED"),
            new Order("010", "Maria Santos", 95.50, LocalDateTime.now().minusDays(1), "PENDING")
        );
    }
}
