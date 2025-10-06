package ex5;

// Interface base para documentos que implementa Prototype
public interface Document extends Cloneable {
    Document clone();
    void customize(String color, String font, String logo);
    String generate();
    String getType();
}