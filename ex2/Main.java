package ex2;

import java.util.Scanner;

/*
 * Factory Method Pattern, Polimorfismo, Encapsulamento, Open/Closed Principle (SOLID), Dependency Inversion Principle (SOLID)
 * Factory Method: A Factory encapsula a lógica de criação, mapeando strings para classes específicas, permitindo a adição de novos tipos de pagamento sem modificar o código existente.
 * Polimorfismo: Os processadores de pagamento implementam uma interface comum, permitindo que o código trate diferentes tipos de pagamento de forma uniforme.
 * Encapsulamento: A lógica de processamento de cada tipo de pagamento é encapsulada em suas respectivas classes, seguindo o princípio de responsabilidade única.
 * Open/Closed Principle: O sistema é aberto para extensão (novos tipos de pagamento), mas fechado para modificação (código existente não é alterado).
 * Dependency Inversion Principle: O código depende de abstrações (interfaces) em vez de implementações concretas, facilitando a manutenção e evolução do sistema.
 */

public class Main {
    public static void main(String[] args) {
        // Simulação de configuração de diferentes meios de pagamento
        System.out.println("=== Sistema de Pagamentos ===");
        
        Scanner scanner = new Scanner(System.in);
        
        try {
            // Coleta dados do pagamento
            System.out.print("Digite o valor do pagamento: R$ ");
            double valor = scanner.nextDouble();
            scanner.nextLine(); // consome a quebra de linha
            
            System.out.print("Digite a descrição do pagamento: ");
            String descricao = scanner.nextLine();
            
            System.out.print("Digite o nome do cliente: ");
            String cliente = scanner.nextLine();
            
            // Cria dados do pagamento
            PaymentData paymentData = new PaymentData(valor, descricao, cliente);
            
            System.out.println("\nEscolha o meio de pagamento:");
            System.out.println("1 - Cartão de Crédito (cartao)");
            System.out.println("2 - Boleto Bancário (boleto)");
            System.out.println("3 - PIX (pix)");
            System.out.print("Opção: ");
            
            int opcao = scanner.nextInt();
            
            // Mapeia a opção para o tipo de pagamento usando expressão switch (rule switch)
            String tipoBonico = switch (opcao) {
                case 1 -> "cartao";
                case 2 -> "boleto";
                case 3 -> "pix";
                default -> {
                    System.out.println("Opção inválida!");
                    yield "";
                }
            };
            
            if (tipoBonico.isEmpty()) {
                return;
            }
            
            // Usa o Factory para criar o processador correto
            System.out.println("\nProcessando pagamento...");
            PaymentProcessor processor = PaymentProcessorFactory.create(tipoBonico);
            processor.process(paymentData);
            
            System.out.println("Pagamento concluído com sucesso!");
            
            // Demonstra diferentes tipos de pagamento automaticamente
            System.out.println("\n=== Demonstração de todos os tipos ===");
            String[] tipos = {"cartao", "boleto", "pix"};
            PaymentData demoData = new PaymentData(100.00, "Pagamento de demonstração", "Cliente Demo");
            
            for (String tipo : tipos) {
                System.out.println("\nProcessando com " + tipo + ":");
                PaymentProcessor demoProcessor = PaymentProcessorFactory.create(tipo);
                demoProcessor.process(demoData);
            }
            
        } catch (Exception e) {
            System.err.println("Erro: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}

