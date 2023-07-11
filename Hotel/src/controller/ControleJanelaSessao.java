package controller;

import java.sql.ResultSet;

import model.DaoSessao;
import model.Sessao;

public class ControleJanelaSessao {
    private Sessao se = new Sessao(null, null, 0);
    private DaoSessao daoSe = new DaoSessao();
   
    public boolean incluirSessao(String nome, String data, int quarto) {
        se.setNome(nome);
        se.setData(data);
        se.setQuarto(quarto);
       
        return daoSe.inserir(nome, se.formatarData(), quarto);
    }
    
    public boolean excluirSessao(String nome, String data, int quarto) {
        se.setNome(nome);
        se.setData(data);
        se.setQuarto(quarto);
       
        return daoSe.excluir(nome, se.formatarData(), quarto);
    }
    
    public ResultSet listarSessao() {
    	return daoSe.listar();
    }
}
