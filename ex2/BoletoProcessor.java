package ex2;

public class BoletoProcessor implements PaymentProcessor {
    
    @Override
    public void process(PaymentData data) {
        if (data == null) {
            System.out.println("Dados de pagamento não suportados: null");
            return;
        }

        System.out.println("🧾 Processando pagamento via BOLETO BANCÁRIO");
        System.out.println("   Valor: R$ " + String.format("%.2f", data.getAmount()));
        System.out.println("   Cliente: " + data.getCustomerInfo());
        System.out.println("   Descrição: " + data.getDescription());
        
        // Simula geração de código de barras
        String codigoBarras = generateBoletoCode();
        System.out.println("   Código de barras: " + codigoBarras);
        System.out.println("   Vencimento: 3 dias úteis");
        System.out.println("   Status: Boleto gerado com sucesso");
    }
    
    private String generateBoletoCode() {
        return "03399.12345 67890.123456 78901.234567 1 12340001000000";
    }

}
