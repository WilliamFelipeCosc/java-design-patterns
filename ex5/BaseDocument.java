package ex5;

// Classe abstrata base para documentos
public abstract class BaseDocument implements Document {
    protected String color;
    protected String font;
    protected String logo;
    protected String content;
    protected String type;
    
    public BaseDocument(String type) {
        this.type = type;
        this.color = "preto";
        this.font = "Arial";
        this.logo = "logo-padrao.png";
        this.content = "";
    }
    
    @Override
    public Document clone() {
        try {
            BaseDocument cloned = (BaseDocument) super.clone();
            // Deep copy se necessário para objetos complexos
            return cloned;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Erro ao clonar documento", e);
        }
    }
    
    @Override
    public void customize(String color, String font, String logo) {
        if (color != null) this.color = color;
        if (font != null) this.font = font;
        if (logo != null) this.logo = logo;
    }
    
    @Override
    public String getType() {
        return type;
    }
    
    protected String getHeader() {
        return String.format("=== %s ===\n", type.toUpperCase());
    }
    
    protected String getFooter() {
        return String.format("\n[Cor: %s | Fonte: %s | Logo: %s]\n", color, font, logo);
    }
}