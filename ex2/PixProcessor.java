package ex2;

public class PixProcessor implements PaymentProcessor {
    
    @Override
    public void process(PaymentData data) {
        System.out.println("⚡ Processando pagamento via PIX");
        System.out.println("   Valor: R$ " + String.format("%.2f", data.getAmount()));
        System.out.println("   Cliente: " + data.getCustomerInfo());
        System.out.println("   Descrição: " + data.getDescription());
        
        // Simula geração de chave PIX e QR Code
        String pixKey = generatePixKey();
        System.out.println("   Chave PIX: " + pixKey);
        System.out.println("   QR Code gerado para pagamento instantâneo");
        System.out.println("   Status: Pronto para recebimento");
        System.out.println("   Tempo de expiração: 30 minutos");
    }
    
    private String generatePixKey() {
        return "pix.exemplo@banco.com.br";
    }
}