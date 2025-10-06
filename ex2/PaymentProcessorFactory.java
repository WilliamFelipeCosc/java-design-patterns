package ex2;

public class PaymentProcessorFactory {
    public static PaymentProcessor create(String type) {
        return switch (type.toLowerCase()) {
            case "cartao", "creditcard" -> new CreditCardProcessor();
            case "boleto" -> new BoletoProcessor();
            case "pix" -> new PixProcessor();
            default -> throw new IllegalArgumentException("Tipo de pagamento desconhecido: " + type);
        };
    }
}
