package model;

public class Funcionario {
    private int id;
    private String nome;
    private String cpf;
    private String cargo;
    private String senha;
    private String telefone; // Novo campo telefone
    private String endereco; // Novo campo endereço

    public Funcionario(int id, String nome, String cpf, String cargo, String senha, String telefone, String endereco) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.cargo = cargo;
        this.senha = senha;
        this.telefone = telefone; // Atribuindo telefone
        this.endereco = endereco; // Atribuindo endereço
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTelefone() {
        return telefone;  // Adicionando getter para telefone
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;  // Adicionando setter para telefone
    }

    public String getEndereco() {
        return endereco;  // Adicionando getter para endereco
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;  // Adicionando setter para endereco
    }
}
