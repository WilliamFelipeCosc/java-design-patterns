package ex5;

public class Resume extends BaseDocument {
    
    public Resume() {
        super("Currículo");
        initializeDefaultContent();
    }
    
    private void initializeDefaultContent() {
        this.content = """
            DADOS PESSOAIS:
            Nome: [Nome do Candidato]
            Email: [email@exemplo.com]
            Telefone: [telefone]
            
            EXPERIÊNCIA PROFISSIONAL:
            [Adicionar experiências]
            
            FORMAÇÃO ACADÊMICA:
            [Adicionar formação]
            
            HABILIDADES:
            [Listar habilidades]
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