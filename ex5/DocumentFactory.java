package ex5;

import java.util.HashMap;
import java.util.Map;

// Factory para criar protótipos de documentos
public class DocumentFactory {
    private static final Map<String, Document> prototypes = new HashMap<>();
    
    static {
        // Inicializa protótipos padrão
        prototypes.put("curriculo", new Resume());
        prototypes.put("proposta", new CommercialProposal());
        prototypes.put("relatorio", new Report());
    }
    
    public static Document createDocument(String type) {
        Document prototype = prototypes.get(type.toLowerCase());
        if (prototype == null) {
            throw new IllegalArgumentException("Tipo de documento desconhecido: " + type);
        }
        
        // Retorna um clone do protótipo
        return prototype.clone();
    }
    
    public static void registerPrototype(String type, Document prototype) {
        prototypes.put(type.toLowerCase(), prototype);
    }
    
    public static String[] getAvailableTypes() {
        return prototypes.keySet().toArray(new String[0]);
    }
}