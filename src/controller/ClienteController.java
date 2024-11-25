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

    public boolean cadastrarCliente(Cliente cliente) {
        return clienteDAO.inserir(cliente);
    }

    public boolean atualizarCliente(Cliente cliente) {
        return clienteDAO.atualizar(cliente);
    }

    public boolean excluirCliente(String cpf) {
        return clienteDAO.deletar(cpf);
    }
}
