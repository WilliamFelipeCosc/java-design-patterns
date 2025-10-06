package ex3;

import java.util.Map;
import java.util.Scanner;

/**
 * Padrões Observer, Factory, Singleton, Polimorfismo e SOLID
 * Observer:
 * Subject (tópico) notifica automaticamente todos os Observers (leitores) registrados, desacoplamento total entre publicador e assinantes
 * 
 * Factory:
 * TopicFactory encapsula a lógica de criação de tópicos, facilitando a adição de novos tipos sem alterar o código cliente
 * 
 * Polimorfismo:
 * Leitores implementam uma interface comum, permitindo diferentes tipos de notificação (email, SMS, push) serem tratados uniformemente
 * 
 * Singleton:
 * NotificationManager garante uma única instância que gerencia todos os tópicos e inscrições, centralizando o controle
 * 
 * SOLID:
 * Single Responsibility Principle: Cada classe tem uma única responsabilidade clara (ex: Reader, Topic, NotificationManager)
 * Open/Closed Principle: O sistema é aberto para extensão (novos tipos de notificação, novos tópicos) mas fechado para modificação (código existente não precisa mudar)
 */
public class Main {
    private static final NotificationManager manager = NotificationManager.getInstance();
    private static final Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        System.out.println("----- Sistema de Notificações de Notícias -----");
        System.out.println("Demonstrando padrões: Observer, Factory, Singleton, Polimorfismo, SOLID\n");
        
        try {
            // Demonstração automática
            demonstrateSystem();
            
            // Menu interativo
            if (askUserChoice("Deseja testar o sistema interativamente?")) {
                interactiveMenu();
            }
            
        } catch (Exception e) {
            System.err.println("Erro: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
    
    /**
     * Demonstração automática do sistema
     */
    private static void demonstrateSystem() {
        System.out.println("📋 === Demonstração Automática ===\n");
        
        // Criando leitores com diferentes tipos de notificação (Polimorfismo)
        Reader alice = new Reader("Alice Silva", "alice@email.com", NotificationType.EMAIL);
        Reader bob = new Reader("Bob Santos", "bob@email.com", NotificationType.PUSH);
        Reader carol = new Reader("Carol Lima", "carol@email.com", NotificationType.SMS);
        Reader david = new Reader("David Costa", "david@email.com", NotificationType.CONSOLE);
        
        // Inscrevendo leitores em tópicos (Observer Pattern + Singleton)
        System.out.println("👥 Inscrevendo leitores em tópicos...");
        manager.subscribeReaderToTopic("politica", alice);
        manager.subscribeReaderToTopic("politica", bob);
        
        manager.subscribeReaderToTopic("tecnologia", alice);
        manager.subscribeReaderToTopic("tecnologia", carol);
        manager.subscribeReaderToTopic("tecnologia", david);
        
        manager.subscribeReaderToTopic("esportes", bob);
        manager.subscribeReaderToTopic("esportes", carol);
        
        // Publicando notícias (Observer Pattern notifica automaticamente)
        System.out.println("\n📰 Publicando notícias...");
        
        News politicNews = new News(
            "Nova Lei de Transparência Aprovada",
            "O Congresso aprovou hoje uma nova lei que aumenta a transparência nos gastos públicos, exigindo relatórios detalhados mensais.",
            "Repórter João"
        );
        manager.publishNews("politica", politicNews);
        
        News techNews = new News(
            "IA Revoluciona Diagnósticos Médicos",
            "Novo sistema de inteligência artificial consegue diagnosticar doenças com 95% de precisão, superando métodos tradicionais.",
            "Repórter Ana"
        );
        manager.publishNews("tecnologia", techNews);
        
        News sportsNews = new News(
            "Brasil Classifica para Final da Copa",
            "Seleção brasileira vence por 3x1 e garante vaga na final do campeonato mundial, em jogo emocionante.",
            "Repórter Carlos"
        );
        manager.publishNews("esportes", sportsNews);
        
        // Mostrando estatísticas
        System.out.println("\n" + manager.getSystemStats());
    }
    
    /**
     * Menu interativo para testar o sistema
     */
    private static void interactiveMenu() {
        System.out.println("\n🎮 === Menu Interativo ===");
        
        while (true) {
            System.out.println("\nEscolha uma opção:");
            System.out.println("1 - Criar leitor e inscrever em tópico");
            System.out.println("2 - Publicar notícia");
            System.out.println("3 - Mostrar tópicos disponíveis");
            System.out.println("4 - Mostrar estatísticas");
            System.out.println("0 - Sair");
            System.out.print("Opção: ");
            
            try {
                int option = scanner.nextInt();
                scanner.nextLine(); // consume newline
                
                switch (option) {
                    case 1 -> createReaderAndSubscribe();
                    case 2 -> publishNewsInteractively();
                    case 3 -> showAvailableTopics();
                    case 4 -> System.out.println("\n" + manager.getSystemStats());
                    case 0 -> {
                        System.out.println("👋 Obrigado por usar o sistema!");
                        return;
                    }
                    default -> System.out.println("❌ Opção inválida!");
                }
            } catch (Exception e) {
                System.out.println("❌ Entrada inválida: " + e.getMessage());
                scanner.nextLine(); // clear invalid input
            }
        }
    }
    
    /**
     * Cria um leitor e o inscreve em um tópico
     */
    private static void createReaderAndSubscribe() {
        System.out.print("Nome do leitor: ");
        String name = scanner.nextLine();
        
        System.out.print("Email do leitor: ");
        String email = scanner.nextLine();
        
        System.out.println("Tipos de notificação disponíveis:");
        NotificationType[] types = NotificationType.values();
        for (int i = 0; i < types.length; i++) {
            System.out.println((i + 1) + " - " + types[i].getDisplayName());
        }
        
        System.out.print("Escolha o tipo (1-" + types.length + "): ");
        int typeChoice = scanner.nextInt();
        scanner.nextLine();
        
        if (typeChoice < 1 || typeChoice > types.length) {
            System.out.println("❌ Tipo inválido!");
            return;
        }
        
        NotificationType selectedType = types[typeChoice - 1];
        Reader reader = new Reader(name, email, selectedType);
        
        showAvailableTopics();
        System.out.print("Digite o nome do tópico para inscrição: ");
        String topicName = scanner.nextLine();
        
        try {
            manager.subscribeReaderToTopic(topicName, reader);
            System.out.println("✅ Leitor " + name + " inscrito com sucesso!");
        } catch (Exception e) {
            System.out.println("❌ Erro ao inscrever: " + e.getMessage());
        }
    }
    
    /**
     * Publica uma notícia interativamente
     */
    private static void publishNewsInteractively() {
        showAvailableTopics();
        System.out.print("Digite o nome do tópico: ");
        String topicName = scanner.nextLine();
        
        System.out.print("Título da notícia: ");
        String title = scanner.nextLine();
        
        System.out.print("Conteúdo da notícia: ");
        String content = scanner.nextLine();
        
        System.out.print("Nome do autor: ");
        String author = scanner.nextLine();
        
        News news = new News(title, content, author);
        
        try {
            manager.publishNews(topicName, news);
            System.out.println("✅ Notícia publicada com sucesso!");
        } catch (Exception e) {
            System.out.println("❌ Erro ao publicar: " + e.getMessage());
        }
    }
    
    /**
     * Mostra os tópicos disponíveis
     */
    private static void showAvailableTopics() {
        System.out.println("\n📂 Tópicos disponíveis:");
        Map<String, String> topics = TopicFactory.getAvailableTopics();
        for (Map.Entry<String, String> entry : topics.entrySet()) {
            System.out.println("- " + entry.getKey() + " (" + entry.getValue() + ")");
        }
    }
    
    /**
     * Pergunta uma escolha sim/não ao usuário
     */
    private static boolean askUserChoice(String question) {
        System.out.print(question + " (s/n): ");
        String response = scanner.nextLine().trim().toLowerCase();
        return response.equals("s") || response.equals("sim") || response.equals("y") || response.equals("yes");
    }
}
