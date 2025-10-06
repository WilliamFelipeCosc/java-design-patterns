package ex4;

// Strategy para exportação em texto simples (padrão)
public class TextExportStrategy implements ExportStrategy {
    @Override
    public String export(String content) {
        StringBuilder text = new StringBuilder();
        
        text.append("RELATÓRIO DE VENDAS\n");
        text.append("Data de Geração: ").append(java.time.LocalDateTime.now()).append("\n");
        text.append("=" .repeat(60)).append("\n\n");
        
        text.append(content);
        
        text.append("\n\n");
        text.append("=" .repeat(60)).append("\n");
        text.append("Relatório gerado pelo Sistema de Vendas Online\n");
        
        return text.toString();
    }
}