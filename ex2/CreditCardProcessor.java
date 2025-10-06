package ex2;

public class CreditCardProcessor implements PaymentProcessor {
    @Override
    public void process(PaymentData data) {
        System.out.println("💳 Processando pagamento via CARTÃO DE CRÉDITO");
        System.out.println("   Valor: R$ " + String.format("%.2f", data.getAmount()));
        System.out.println("   Cliente: " + data.getCustomerInfo());
        System.out.println("   Descrição: " + data.getDescription());
        
        // Simula autorização do cartão
        System.out.println("   Conectando com a operadora...");
        String transactionId = generateTransactionId();
        System.out.println("   ID da transação: " + transactionId);
        System.out.println("   Status: Aprovado pela operadora");
        System.out.println("   Forma de pagamento: Crédito à vista");
    }
    
    private String generateTransactionId() {
        return "CC" + System.currentTimeMillis();
    }
}