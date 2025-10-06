package ex3;

import java.util.List;

/**
 * Interface Subject - Padrão Observer
 * Define o contrato para objetos que podem ser observados
 */
public interface Subject {
    /**
     * Adiciona um observer à lista de observadores
     * @param observer O observer a ser adicionado
     */
    void addObserver(Observer observer);
    
    /**
     * Remove um observer da lista de observadores
     * @param observer O observer a ser removido
     */
    void removeObserver(Observer observer);
    
    /**
     * Notifica todos os observadores sobre uma mudança
     * @param news A notícia que foi publicada
     */
    void notifyObservers(News news);
    
    /**
     * Retorna a lista de observadores (para demonstração)
     * @return Lista de observadores
     */
    List<Observer> getObservers();
}