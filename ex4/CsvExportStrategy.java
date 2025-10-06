package ex4;

// Strategy para exportação CSV
public class CsvExportStrategy implements ExportStrategy {
    @Override
    public String export(String content) {
        StringBuilder csv = new StringBuilder();
        
        // Cabeçalho CSV
        csv.append("\"Relatório de Vendas - ").append(java.time.LocalDate.now()).append("\"\n");
        csv.append("\"ID\",\"Cliente\",\"Valor\",\"Status\",\"Data\"\n");
        
        // Extrai dados do conteúdo (simulação simples)
        String[] lines = content.split("\n");
        for (String line : lines) {
            if (line.contains("ID:") && line.contains("Cliente:")) {
                // Parse da linha: ID: 001 | Cliente: João | Valor: R$ 150.00 | Status: COMPLETED
                String[] parts = line.split("\\|");
                if (parts.length >= 4) {
                    String id = parts[0].replace("ID:", "").trim();
                    String cliente = parts[1].replace("Cliente:", "").trim();
                    String valor = parts[2].replace("Valor:", "").replace("R$", "").trim();
                    String status = parts[3].replace("Status:", "").trim();
                    
                    csv.append(String.format("\"%s\",\"%s\",\"%s\",\"%s\",\"%s\"\n", 
                        id, cliente, valor, status, java.time.LocalDate.now()));
                }
            }
        }
        
        // Adiciona estatísticas como comentário
        csv.append("\n\"--- ESTATÍSTICAS ---\"\n");
        for (String line : lines) {
            if (line.contains("Total de Pedidos:") || line.contains("Valor Total:") || 
                line.contains("Ticket Médio:")) {
                csv.append("\"").append(line.trim()).append("\"\n");
            }
        }
        
        return csv.toString();
    }
}