package ex5;

public class CommercialProposal extends BaseDocument {
    
    public CommercialProposal() {
        super("Proposta Comercial");
        initializeDefaultContent();
    }
    
    private void initializeDefaultContent() {
        this.content = """
            PROPOSTA COMERCIAL
            
            CLIENTE: [Nome do Cliente]
            DATA: [Data da Proposta]
            VALIDADE: [Validade da Proposta]
            
            SERVIÇOS OFERECIDOS:
            [Descrição dos serviços]
            
            VALORES:
            [Tabela de preços]
            
            CONDIÇÕES DE PAGAMENTO:
            [Condições]
            
            PRAZO DE ENTREGA:
            [Cronograma]
            """;
    }
    
    @Override
    public String generate() {
        StringBuilder sb = new StringBuilder();
        sb.append(getHeader());
        sb.append(content);
        sb.append(getFooter());
        return sb.toString();
    }
}