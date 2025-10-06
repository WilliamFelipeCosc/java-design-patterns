package ex4;

// Strategy para exportação HTML
public class HtmlExportStrategy implements ExportStrategy {
    @Override
    public String export(String content) {
        StringBuilder html = new StringBuilder();
        
        html.append("<!DOCTYPE html>\n");
        html.append("<html>\n<head>\n");
        html.append("    <title>Relatório de Vendas</title>\n");
        html.append("    <meta charset=\"UTF-8\">\n");
        html.append("    <style>\n");
        html.append("        body { font-family: Arial, sans-serif; margin: 40px; }\n");
        html.append("        .header { color: #2c3e50; border-bottom: 2px solid #3498db; }\n");
        html.append("        .content { white-space: pre-line; }\n");
        html.append("        .chart { color: #27ae60; }\n");
        html.append("    </style>\n");
        html.append("</head>\n<body>\n");
        
        html.append("    <div class=\"header\">\n");
        html.append("        <h1>Relatório de Vendas</h1>\n");
        html.append("        <p>Data: ").append(java.time.LocalDate.now()).append("</p>\n");
        html.append("    </div>\n");
        
        html.append("    <div class=\"content\">\n");
        html.append("        <pre>").append(escapeHtml(content)).append("</pre>\n");
        html.append("    </div>\n");
        
        html.append("</body>\n</html>");
        
        return html.toString();
    }
    
    private String escapeHtml(String content) {
        return content.replace("&", "&amp;")
                     .replace("<", "&lt;")
                     .replace(">", "&gt;")
                     .replace("\"", "&quot;");
    }
}