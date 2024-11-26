package controller;

import dao.ClienteDAO;
import model.Cliente;

public class ClienteController {
    private ClienteDAO clienteDAO;

    public ClienteController() {
        this.clienteDAO = new ClienteDAO();
    }

    public Cliente autenticarCliente(String cpf, String senha) {
        return clienteDAO.autenticarCliente(cpf, senha);
    }

    public Cliente buscarClientePorCPF(String cpf) {
        return clienteDAO.buscarPorCPF(cpf);
    }
    
    public Cliente buscarPorCPF(String cpf) {
        return clienteDAO.buscarPorCPF(cpf);  // Chama a função buscarPorCPF do DAO
    }

    public boolean cadastrarCliente(Cliente cliente) {
        return clienteDAO.inserir(cliente);  // Inserir cliente no banco
    }

    public boolean atualizarCliente(Cliente cliente) {
        return clienteDAO.atualizar(cliente);  // Atualiza cliente no banco
    }

    public boolean excluirCliente(String cpf) {
        return clienteDAO.deletar(cpf);  // Exclui cliente do banco
    }
}
