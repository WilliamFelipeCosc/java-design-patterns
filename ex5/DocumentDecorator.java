package ex5;

// Decorator base para adicionar funcionalidades aos documentos
public abstract class DocumentDecorator implements Document {
    protected Document document;
    
    public DocumentDecorator(Document document) {
        this.document = document;
    }
    
    @Override
    public Document clone() {
        // Clona o documento decorado
        Document clonedDocument = document.clone();
        try {
            DocumentDecorator cloned = (DocumentDecorator) super.clone();
            cloned.document = clonedDocument;
            return cloned;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Erro ao clonar decorator", e);
        }
    }
    
    @Override
    public void customize(String color, String font, String logo) {
        document.customize(color, font, logo);
    }
    
    @Override
    public String getType() {
        return document.getType();
    }
    
    @Override
    public String generate() {
        return document.generate();
    }
}