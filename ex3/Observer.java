package ex3;

/**
 * Interface Observer - Padrão Observer
 * Define o contrato para objetos que desejam ser notificados sobre mudanças
 */
public interface Observer {
    /**
     * Método chamado quando uma nova notícia é publicada
     * @param news A notícia publicada
     * @param topicName O nome do tópico
     */
    void update(News news, String topicName);
}