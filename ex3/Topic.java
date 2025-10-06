package ex3;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe Topic - Implementa Subject (Padrão Observer)
 * Representa um tópico de notícias que pode ser observado
 */
public class Topic implements Subject {
    private final String name;
    private final List<Observer> observers;
    private final List<News> newsHistory;
    
    public Topic(String name) {
        this.name = name;
        this.observers = new ArrayList<>();
        this.newsHistory = new ArrayList<>();
    }
    
    @Override
    public void addObserver(Observer observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
            System.out.println("✓ Leitor inscrito no tópico: " + name);
        }
    }
    
    @Override
    public void removeObserver(Observer observer) {
        if (observers.remove(observer)) {
            System.out.println("✓ Leitor desinscrito do tópico: " + name);
        }
    }
    
    @Override
    public void notifyObservers(News news) {
        System.out.println("\n🔔 Notificando " + observers.size() + 
                          " leitores sobre nova notícia em " + name + "...");
        
        for (Observer observer : observers) {
            observer.update(news, name);
        }
    }
    
    @Override
    public List<Observer> getObservers() {
        return new ArrayList<>(observers);
    }
    
    /**
     * Publica uma nova notícia neste tópico
     * @param news A notícia a ser publicada
     */
    public void publishNews(News news) {
        newsHistory.add(news);
        System.out.println("\n📰 Nova notícia publicada em " + name + ": " + news.getTitle());
        notifyObservers(news);
    }
    
    // Getters
    public String getName() {
        return name;
    }
    
    public List<News> getNewsHistory() {
        return new ArrayList<>(newsHistory);
    }
    
    public int getSubscribersCount() {
        return observers.size();
    }
}