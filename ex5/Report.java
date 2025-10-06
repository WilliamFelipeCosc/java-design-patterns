package ex5;

public class Report extends BaseDocument {
    
    public Report() {
        super("Relatório");
        initializeDefaultContent();
    }
    
    private void initializeDefaultContent() {
        this.content = """
            RELATÓRIO EXECUTIVO
            
            RESUMO EXECUTIVO:
            [Resumo do relatório]
            
            OBJETIVOS:
            [Objetivos do relatório]
            
            METODOLOGIA:
            [Como foi feito]
            
            RESULTADOS:
            [Principais resultados]
            
            CONCLUSÕES:
            [Conclusões e recomendações]
            
            ANEXOS:
            [Lista de anexos]
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