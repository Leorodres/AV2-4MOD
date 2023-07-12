package model;

import javax.swing.JOptionPane;

public class Sessao {
	private String nome;
	private String data;
	private int quarto;
	
	public Sessao(String nome, String data, int quarto) {
		super();
		this.nome = nome;
		this.data = data;
		this.quarto = quarto;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public int getQuarto() {
		return quarto;
	}
	public void setQuarto(int quarto) {
		this.quarto = quarto;
	}
	public String formatarData() {
		try {
        return data.substring(6, 10) + "-" +
               data.substring(3, 5) + "-" +
               data.substring(0, 2);
		}
		catch(StringIndexOutOfBoundsException e) {
			JOptionPane.showMessageDialog(null, "Formatação da data incorreta! DD/MM/AAAA");
			return null;
		}
    }
	
}
