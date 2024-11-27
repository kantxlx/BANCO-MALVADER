package model;

import java.time.LocalDate;

public class Cliente extends Usuario {
    private LocalDate dataNascimento;

    public Cliente(String nome, String cpf, LocalDate dataNascimento, String telefone, String endereco, String senha) {
        super(nome, cpf, telefone, endereco, senha);
        this.dataNascimento = dataNascimento;
    }

    // Getter e Setter
    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
}
