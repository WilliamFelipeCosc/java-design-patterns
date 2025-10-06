package ex5;

// Decorator para adicionar cabeçalho personalizado
public class HeaderDecorator extends DocumentDecorator {
    private final String headerText;
    
    public HeaderDecorator(Document document, String headerText) {
        super(document);
        this.headerText = headerText;
    }
    
    @Override
    public String generate() {
        String originalContent = document.generate();
        return addCustomHeader(originalContent);
    }
    
    private String addCustomHeader(String content) {
        StringBuilder sb = new StringBuilder();
        sb.append("╔══════════════════════════════════════╗\n");
        sb.append("║ ").append(centerText(headerText, 34)).append(" ║\n");
        sb.append("╚══════════════════════════════════════╝\n\n");
        sb.append(content);
        return sb.toString();
    }
    
    private String centerText(String text, int width) {
        if (text.length() >= width) {
            return text.substring(0, width);
        }
        int padding = (width - text.length()) / 2;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < padding; i++) {
            sb.append(" ");
        }
        sb.append(text);
        while (sb.length() < width) {
            sb.append(" ");
        }
        return sb.toString();
    }
}