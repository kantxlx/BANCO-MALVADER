package model;

import java.time.LocalDateTime;

public class Transacao {
    private int id;
    private int contaId;
    private String tipo;
    private double valor;
    private LocalDateTime dataTransacao;

    public Transacao(int id, int contaId, String tipo, double valor, LocalDateTime dataTransacao) {
        this.id = id;
        this.contaId = contaId;
        this.tipo = tipo;
        this.valor = valor;
        this.dataTransacao = dataTransacao;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getContaId() {
        return contaId;
    }

    public void setContaId(int contaId) {
        this.contaId = contaId;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public LocalDateTime getDataTransacao() {
        return dataTransacao;
    }

    public void setDataTransacao(LocalDateTime dataTransacao) {
        this.dataTransacao = dataTransacao;
    }
}
