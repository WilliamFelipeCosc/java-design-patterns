package ex4;

// Strategy Pattern - Interface para diferentes formatos de exportação
public interface ExportStrategy {
    String export(String content);
}