package ex3;

/**
 * Classe Reader - Implementa Observer (Padrão Observer)
 * Representa um leitor que recebe notificações sobre notícias
 */
public class Reader implements Observer {
    private final String name;
    private final String email;
    private final NotificationType notificationType;
    
    public Reader(String name, String email, NotificationType notificationType) {
        this.name = name;
        this.email = email;
        this.notificationType = notificationType;
    }
    
    @Override
    public void update(News news, String topicName) {
        // Polimorfismo em ação - comportamento específico baseado no tipo
        switch (notificationType) {
            case EMAIL -> sendEmailNotification(news, topicName);
            case SMS -> sendSmsNotification(news, topicName);
            case PUSH -> sendPushNotification(news, topicName);
            case CONSOLE -> sendConsoleNotification(news, topicName);
        }
    }
    
    private void sendEmailNotification(News news, String topicName) {
        System.out.println("📧 EMAIL para " + name + " (" + email + "):");
        System.out.println("   Tópico: " + topicName);
        System.out.println("   " + news.getTitle());
        System.out.println("   " + truncateContent(news.getContent()));
    }
    
    private void sendSmsNotification(News news, String topicName) {
        System.out.println("📱 SMS para " + name + ":");
        System.out.println("   [" + topicName + "] " + news.getTitle());
    }
    
    private void sendPushNotification(News news, String topicName) {
        System.out.println("🔔 PUSH para " + name + ":");
        System.out.println("   " + topicName + ": " + news.getTitle());
    }
    
    private void sendConsoleNotification(News news, String topicName) {
        System.out.println("💻 CONSOLE para " + name + ":");
        System.out.println("   Tópico: " + topicName);
        System.out.println("   " + news.toString());
    }
    
    private String truncateContent(String content) {
        return content.length() > 50 ? content.substring(0, 50) + "..." : content;
    }
    
    // Getters
    public String getName() {
        return name;
    }
    
    public String getEmail() {
        return email;
    }
    
    public NotificationType getNotificationType() {
        return notificationType;
    }
    
    @Override
    public String toString() {
        return name + " (" + notificationType + ")";
    }
}