package ex5;

// Gerenciador centralizado de documentos (Singleton)
public class DocumentManager {
    private static DocumentManager instance;
    private int documentCounter = 0;
    
    private DocumentManager() {
        // Construtor privado
    }
    
    public static DocumentManager getInstance() {
        if (instance == null) {
            instance = new DocumentManager();
        }
        return instance;
    }
    
    public Document createPersonalizedDocument(String type, String color, String font, String logo) {
        Document document = DocumentFactory.createDocument(type);
        document.customize(color, font, logo);
        documentCounter++;
        return document;
    }
    
    public Document createDocumentWithDecorators(String type, boolean addWatermark, boolean addHeader) {
        Document document = DocumentFactory.createDocument(type);
        documentCounter++;
        
        if (addWatermark) {
            document = new WatermarkDecorator(document, "CONFIDENCIAL");
        }
        
        if (addHeader) {
            document = new HeaderDecorator(document, "EMPRESA DE DESIGN LTDA");
        }
        
        return document;
    }
    
    public int getDocumentCount() {
        return documentCounter;
    }
    
    public void resetCounter() {
        documentCounter = 0;
    }
}