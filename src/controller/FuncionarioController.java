package controller;

import java.util.List;

import dao.FuncionarioDAO;
import model.Funcionario;

public class FuncionarioController {
    private FuncionarioDAO funcionarioDAO;

    public FuncionarioController() {
        this.funcionarioDAO = new FuncionarioDAO();
    }

    public boolean cadastrarFuncionario(Funcionario funcionario) {
        return funcionarioDAO.inserir(funcionario);
    }

    public List<Funcionario> listarFuncionarios() {
        return funcionarioDAO.listarTodos();
    }

    public Funcionario buscarFuncionarioPorCPF(String cpf) {
        return funcionarioDAO.buscarPorCPF(cpf);
    }

    public boolean atualizarFuncionario(Funcionario funcionario) {
        return funcionarioDAO.atualizar(funcionario);
    }

    public boolean excluirFuncionario(String cpf) {
        return funcionarioDAO.deletar(cpf);
    }

    // Método de autenticação de funcionário
    public boolean autenticarFuncionario(String cpf, String senha) {
        return funcionarioDAO.autenticar(cpf, senha);  // Verifica CPF e senha no banco
    }
}
