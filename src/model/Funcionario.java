package model;

public class Funcionario extends Usuario {
    private String cargo;

    public Funcionario(String nome, String cpf, String cargo, String telefone, String endereco, String senha) {
        super(nome, cpf, telefone, endereco, senha);
        this.cargo = cargo;
    }

    // Getter e Setter
    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
}
