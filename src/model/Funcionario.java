package model;

public class Funcionario extends Usuario {
    private String cargo;

    // Construtor
    public Funcionario(int id, String nome, String cpf, String cargo, String telefone, String endereco, String senha) {
        super(nome, cpf, telefone, endereco, senha);
        this.cargo = cargo;
        setId(id);
    }

    // Getter e Setter
    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
}