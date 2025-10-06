package ex3;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * NotificationManager - Padrão Singleton
 * Gerencia centralmente todos os tópicos e suas inscrições
 */
public class NotificationManager {
    private static NotificationManager instance;
    private final Map<String, Topic> topics;
    
    private NotificationManager() {
        this.topics = new HashMap<>();
    }
    
    /**
     * Obtém a instância única do NotificationManager
     * @return A instância singleton
     */
    public static synchronized NotificationManager getInstance() {
        if (instance == null) {
            instance = new NotificationManager();
        }
        return instance;
    }
    
    /**
     * Obtém ou cria um tópico
     * @param topicName Nome do tópico
     * @return O tópico correspondente
     */
    public Topic getTopic(String topicName) {
        String key = topicName.toLowerCase().trim();
        
        if (!topics.containsKey(key)) {
            Topic newTopic = TopicFactory.create(topicName);
            topics.put(key, newTopic);
            System.out.println("✓ Tópico criado: " + newTopic.getName());
        }
        
        return topics.get(key);
    }
    
    /**
     * Inscreve um leitor em um tópico
     * @param topicName Nome do tópico
     * @param reader O leitor a ser inscrito
     */
    public void subscribeReaderToTopic(String topicName, Reader reader) {
        Topic topic = getTopic(topicName);
        topic.addObserver(reader);
    }
    
    /**
     * Desinscreve um leitor de um tópico
     * @param topicName Nome do tópico
     * @param reader O leitor a ser desinscrito
     */
    public void unsubscribeReaderFromTopic(String topicName, Reader reader) {
        String key = topicName.toLowerCase().trim();
        if (topics.containsKey(key)) {
            topics.get(key).removeObserver(reader);
        }
    }
    
    /**
     * Publica uma notícia em um tópico
     * @param topicName Nome do tópico
     * @param news A notícia a ser publicada
     */
    public void publishNews(String topicName, News news) {
        Topic topic = getTopic(topicName);
        topic.publishNews(news);
    }
    
    /**
     * Retorna todos os tópicos gerenciados
     * @return Set com os nomes dos tópicos
     */
    public Set<String> getAllTopicNames() {
        return topics.keySet();
    }
    
    /**
     * Retorna estatísticas do sistema
     * @return String com as estatísticas
     */
    public String getSystemStats() {
        StringBuilder stats = new StringBuilder();
        stats.append("=== Estatísticas do Sistema ===\n");
        stats.append("Tópicos ativos: ").append(topics.size()).append("\n");
        
        for (Map.Entry<String, Topic> entry : topics.entrySet()) {
            Topic topic = entry.getValue();
            stats.append("- ").append(topic.getName())
                 .append(": ").append(topic.getSubscribersCount())
                 .append(" inscritos, ").append(topic.getNewsHistory().size())
                 .append(" notícias\n");
        }
        
        return stats.toString();
    }
    
    /**
     * Limpa todos os tópicos (útil para testes)
     */
    public void clearAllTopics() {
        topics.clear();
        System.out.println("✓ Todos os tópicos foram limpos");
    }
}