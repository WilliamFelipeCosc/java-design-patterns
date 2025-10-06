package ex4;

import java.util.List;

// Decorator que adiciona formatação PDF simulada
public class PdfExportDecorator extends SalesReportDecorator {
    
    public PdfExportDecorator(SalesReport report) {
        super(report);
    }
    
    @Override
    public String generate(List<Order> orders) {
        String baseReport = super.generate(orders);
        String pdfFormatting = addPdfFormatting(baseReport);
        return pdfFormatting;
    }
    
    private String addPdfFormatting(String content) {
        StringBuilder pdf = new StringBuilder();
        
        // Cabeçalho PDF simulado
        pdf.append("████████████████████████████████████████████████████\n");
        pdf.append("█                 RELATÓRIO PDF                   █\n");
        pdf.append("█              Sistema de Vendas Online           █\n");
        pdf.append("█         Data: ").append(java.time.LocalDate.now()).append("                      █\n");
        pdf.append("████████████████████████████████████████████████████\n\n");
        
        // Conteúdo formatado
        String[] lines = content.split("\n");
        for (String line : lines) {
            if (line.startsWith("===")) {
                pdf.append("┌").append("─".repeat(50)).append("┐\n");
                pdf.append("│").append(centerText(line.replace("=", "").trim(), 50)).append("│\n");
                pdf.append("└").append("─".repeat(50)).append("┘\n");
            } else if (line.trim().isEmpty()) {
                pdf.append("\n");
            } else {
                pdf.append("  ").append(line).append("\n");
            }
        }
        
        // Rodapé PDF simulado
        pdf.append("\n");
        pdf.append("─".repeat(54)).append("\n");
        pdf.append("Documento gerado automaticamente - Sistema de Vendas\n");
        pdf.append("Página 1 de 1\n");
        
        return pdf.toString();
    }
    
    private String centerText(String text, int width) {
        if (text.length() >= width) {
            return text.substring(0, width);
        }
        int padding = (width - text.length()) / 2;
        return " ".repeat(padding) + text + " ".repeat(width - text.length() - padding);
    }
}