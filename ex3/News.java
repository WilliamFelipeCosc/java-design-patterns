package ex3;

import java.time.LocalDateTime;

/**
 * Classe que representa uma notícia
 * Encapsula os dados de uma notícia
 */
public class News {
    private final String title;
    private final String content;
    private final String author;
    private final LocalDateTime publishDate;
    
    public News(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.publishDate = LocalDateTime.now();
    }
    
    // Getters
    public String getTitle() {
        return title;
    }
    
    public String getContent() {
        return content;
    }
    
    public String getAuthor() {
        return author;
    }
    
    public LocalDateTime getPublishDate() {
        return publishDate;
    }
    
    @Override
    public String toString() {
        return String.format("[%s] %s\nPor: %s\n%s", 
            publishDate.toLocalTime(), title, author, content);
    }
}