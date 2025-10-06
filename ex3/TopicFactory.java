package ex3;

import java.util.HashMap;
import java.util.Map;

/**
 * Factory para criação de tópicos - Padrão Factory
 * Encapsula a lógica de criação de diferentes tipos de tópicos
 */
public class TopicFactory {
    private static final Map<String, String> TOPIC_DISPLAY_NAMES = new HashMap<>();
    
    static {
        TOPIC_DISPLAY_NAMES.put("politica", "Política");
        TOPIC_DISPLAY_NAMES.put("esportes", "Esportes");
        TOPIC_DISPLAY_NAMES.put("tecnologia", "Tecnologia");
        TOPIC_DISPLAY_NAMES.put("economia", "Economia");
        TOPIC_DISPLAY_NAMES.put("saude", "Saúde");
        TOPIC_DISPLAY_NAMES.put("entretenimento", "Entretenimento");
    }
    
    /**
     * Cria um novo tópico baseado no nome fornecido
     * @param topicName Nome do tópico (chave)
     * @return Nova instância de Topic
     * @throws IllegalArgumentException se o tópico não for suportado
     */
    public static Topic create(String topicName) {
        String normalizedName = topicName.toLowerCase().trim();
        
        if (!TOPIC_DISPLAY_NAMES.containsKey(normalizedName)) {
            throw new IllegalArgumentException("Tópico não suportado: " + topicName + 
                ". Tópicos disponíveis: " + TOPIC_DISPLAY_NAMES.keySet());
        }
        
        String displayName = TOPIC_DISPLAY_NAMES.get(normalizedName);
        return new Topic(displayName);
    }
    
    /**
     * Retorna todos os tópicos disponíveis
     * @return Mapa com os tópicos disponíveis
     */
    public static Map<String, String> getAvailableTopics() {
        return new HashMap<>(TOPIC_DISPLAY_NAMES);
    }
    
    /**
     * Verifica se um tópico é suportado
     * @param topicName Nome do tópico
     * @return true se o tópico é suportado
     */
    public static boolean isTopicSupported(String topicName) {
        return TOPIC_DISPLAY_NAMES.containsKey(topicName.toLowerCase().trim());
    }
}