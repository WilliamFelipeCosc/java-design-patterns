package ex5;

// Decorator para adicionar marca d'água
public class WatermarkDecorator extends DocumentDecorator {
    private final String watermarkText;
    
    public WatermarkDecorator(Document document, String watermarkText) {
        super(document);
        this.watermarkText = watermarkText;
    }
    
    @Override
    public String generate() {
        String originalContent = document.generate();
        return addWatermark(originalContent);
    }
    
    private String addWatermark(String content) {
        StringBuilder sb = new StringBuilder();
        sb.append("[MARCA D'ÁGUA: ").append(watermarkText).append("]\n");
        sb.append(content);
        sb.append("\n[MARCA D'ÁGUA: ").append(watermarkText).append("]");
        return sb.toString();
    }
}