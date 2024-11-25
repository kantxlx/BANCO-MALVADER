package controller;

import java.util.List;

import dao.ContaDAO;
import model.Conta;

public class ContaController {
    private ContaDAO contaDAO;

    public ContaController() {
        this.contaDAO = new ContaDAO();
    }

    // Abrir uma nova conta
    public boolean abrirConta(Conta conta) {
        return contaDAO.inserir(conta);
    }

    // Listar todas as contas
    public List<Conta> listarContas() {
        return contaDAO.listarTodas();
    }

    // Buscar uma conta pelo número da conta
    public Conta buscarContaPorNumero(String numeroConta) {
        return contaDAO.buscarPorNumero(numeroConta);
    }

    public Conta buscarContaPorClienteId(int clienteId) {
        return contaDAO.buscarPorClienteId(clienteId);
    }
    
    public Conta buscarContaPorClienteCPF(String cpf) {
        return contaDAO.buscarPorClienteCPF(cpf);  // Verifica a conta associada ao CPF
    }
    
    // Atualizar os dados de uma conta
    public boolean atualizarConta(Conta conta) {
        return contaDAO.atualizar(conta);
    }

    // Encerrar uma conta
    public boolean encerrarConta(String numeroConta) {
        return contaDAO.deletar(numeroConta);
    }

    // Verificar se o saldo da conta é suficiente para realizar uma transação (depósito/saque)
    public boolean verificarSaldoSuficiente(String numeroConta, double valor) {
        return contaDAO.verificarSaldoSuficiente(numeroConta, valor);
    }

    // Atualizar o saldo da conta após uma transação
    public boolean atualizarSaldo(String numeroConta, double novoSaldo) {
        return contaDAO.atualizarSaldo(numeroConta, novoSaldo);
    }

}
