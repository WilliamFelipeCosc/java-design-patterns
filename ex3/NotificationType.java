package ex3;

/**
 * Enum que define os tipos de notificação disponíveis
 * Demonstra polimorfismo através de diferentes comportamentos
 */
public enum NotificationType {
    EMAIL("Email"),
    SMS("SMS"),
    PUSH("Push Notification"),
    CONSOLE("Console");
    
    private final String displayName;
    
    NotificationType(String displayName) {
        this.displayName = displayName;
    }
    
    public String getDisplayName() {
        return displayName;
    }
    
    @Override
    public String toString() {
        return displayName;
    }
}