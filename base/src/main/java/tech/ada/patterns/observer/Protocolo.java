package tech.ada.patterns.observer;

public interface Protocolo {

    public Protocolo registrar(Observer observer);
    public Protocolo esquecer(Observer observer);

    public void notificar();

    public String getMensagem(Observer observer);
    public void publicar(String mensagem);

}