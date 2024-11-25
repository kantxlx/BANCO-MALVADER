package controller;

import java.util.List;

import dao.TransacaoDAO;
import model.Transacao;

public class TransacaoController {
    private TransacaoDAO transacaoDAO;

    public TransacaoController() {
        this.transacaoDAO = new TransacaoDAO();
    }

    public boolean registrarTransacao(Transacao transacao) {
        return transacaoDAO.inserir(transacao);
    }

    public List<Transacao> listarTransacoes() {
        return transacaoDAO.listarTodas();
    }

    public List<Transacao> listarTransacoesPorConta(String numeroConta) {
        return transacaoDAO.listarPorConta(numeroConta);
    }
}
