package ex5;

import java.util.Scanner;

/*
 * Padrões: Prototype, Factory, Decorator, Polimorfismo e Singleton
 * 
 * Prototype: Permite clonar objetos existentes para criar novos, economizando tempo e recursos.
 * Usado para criar rapidamente novos documentos baseados em protótipos existentes.
 * 
 * Factory: Define uma interface para criar um objeto, mas deixa as subclasses decidirem qual classe instanciar.
 * Usado para encapsular a lógica de criação de diferentes tipos de documentos.
 * 
 * Decorator: Permite adicionar funcionalidades a um objeto de forma dinâmica.
 * Usado para adicionar elementos como marcas d'água e cabeçalhos personalizados aos documentos
 * 
 * Polimorfismo: Permite que diferentes classes sejam tratadas de forma uniforme através de uma interface comum.
 * Usado para manipular diferentes tipos de documentos e decorators de maneira consistente.
 * 
 * Singleton: Garante que uma classe tenha apenas uma instância e fornece um ponto global de acesso a ela.
 * Usado para gerenciar a criação e contagem de documentos em um único ponto centralizado
 */

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Sistema de Documentos - Empresa de Design ===");
        
        DocumentManager manager = DocumentManager.getInstance();
        Scanner scanner = new Scanner(System.in);
        
        try {
            demonstratePrototypePattern();
            System.out.println("\n" + "=".repeat(50) + "\n");
            
            demonstrateDecoratorPattern();
            System.out.println("\n" + "=".repeat(50) + "\n");
            
            interactiveDocumentCreation(scanner, manager);
            
        } catch (Exception e) {
            System.err.println("Erro: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
    
    private static void demonstratePrototypePattern() {
        System.out.println("=== DEMONSTRAÇÃO PROTOTYPE PATTERN ===");
        
        // Cria documento original
        Document originalResume = DocumentFactory.createDocument("curriculo");
        originalResume.customize("azul", "Times New Roman", "logo-empresa.png");
        
        System.out.println("1. Documento Original:");
        System.out.println(originalResume.generate());
        
        // Clona e personaliza rapidamente
        Document clonedResume = originalResume.clone();
        clonedResume.customize("verde", "Calibri", "logo-cliente.png");
        
        System.out.println("\n2. Documento Clonado e Personalizado:");
        System.out.println(clonedResume.generate());
        
        System.out.println("✅ BENEFÍCIO: Clone rápido sem recriar estrutura!");
    }
    
    private static void demonstrateDecoratorPattern() {
        System.out.println("=== DEMONSTRAÇÃO DECORATOR PATTERN ===");
        
        // Documento base
        Document proposal = DocumentFactory.createDocument("proposta");
        proposal.customize("vermelho", "Arial", "logo-premium.png");
        
        System.out.println("1. Documento Base:");
        System.out.println(proposal.generate());
        
        // Adiciona marca d'água
        Document proposalWithWatermark = new WatermarkDecorator(proposal, "CONFIDENCIAL");
        
        System.out.println("\n2. Com Marca d'Água:");
        System.out.println(proposalWithWatermark.generate());
        
        // Adiciona cabeçalho personalizado
        Document fullyDecorated = new HeaderDecorator(proposalWithWatermark, "PROPOSTA PREMIUM");
        
        System.out.println("\n3. Totalmente Decorado:");
        System.out.println(fullyDecorated.generate());
        
        System.out.println("✅ BENEFÍCIO: Funcionalidades combinadas dinamicamente!");
    }
    
    private static void interactiveDocumentCreation(Scanner scanner, DocumentManager manager) {
        System.out.println("=== CRIAÇÃO INTERATIVA DE DOCUMENTO ===");
        
        System.out.println("Tipos disponíveis: curriculo, proposta, relatorio");
        System.out.print("Escolha o tipo de documento: ");
        String tipo = scanner.nextLine();
        
        System.out.print("Cor personalizada (ou Enter para padrão): ");
        String cor = scanner.nextLine();
        if (cor.trim().isEmpty()) cor = null;
        
        System.out.print("Fonte personalizada (ou Enter para padrão): ");
        String fonte = scanner.nextLine();
        if (fonte.trim().isEmpty()) fonte = null;
        
        System.out.print("Logo personalizado (ou Enter para padrão): ");
        String logo = scanner.nextLine();
        if (logo.trim().isEmpty()) logo = null;
        
        System.out.print("Adicionar marca d'água? (s/n): ");
        boolean watermark = scanner.nextLine().toLowerCase().startsWith("s");
        
        System.out.print("Adicionar cabeçalho personalizado? (s/n): ");
        boolean header = scanner.nextLine().toLowerCase().startsWith("s");
        
        try {
            // Cria documento personalizado
            Document documento = manager.createPersonalizedDocument(tipo, cor, fonte, logo);
            
            // Aplica decorators se solicitado
            if (watermark) {
                documento = new WatermarkDecorator(documento, "CONFIDENCIAL");
            }
            
            if (header) {
                documento = new HeaderDecorator(documento, "DOCUMENTO PERSONALIZADO");
            }
            
            System.out.println("\n=== DOCUMENTO GERADO ===");
            System.out.println(documento.generate());
            
            System.out.println("\nTotal de documentos criados: " + manager.getDocumentCount());
            
            // Demonstra clonagem rápida
            System.out.println("\n=== CLONE RÁPIDO ===");
            Document clone = documento.clone();
            clone.customize("roxo", "Comic Sans", "logo-divertido.png");
            System.out.println("Clone com nova personalização:");
            System.out.println(clone.generate());
            
        } catch (IllegalArgumentException e) {
            System.err.println("Tipo de documento inválido: " + tipo);
            System.err.println("Tipos válidos: curriculo, proposta, relatorio");
        }
    }
}
