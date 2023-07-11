package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.ControleJanelaSessao;

public class JanelaSessao extends JFrame {

	private JPanel contentPane;
	private JTextField textNome;
	private JTextField textData;
	private JTextField textQuarto;

	private ControleJanelaSessao cjs = new ControleJanelaSessao();
	private JTable tblUsuarios;

	public JanelaSessao() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 599, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setBackground(new Color(10, 10, 10));
		contentPane.setLayout(null);

		JLabel lblCadastroEstd = new JLabel("CADASTRO DE ESTADIAS");
		lblCadastroEstd.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCadastroEstd.setForeground(new Color(255,255,255));
		lblCadastroEstd.setBounds(140, 11, 198, 24);
		contentPane.add(lblCadastroEstd);

		JLabel lblNome = new JLabel("Nome do hospede:");
		lblNome.setBackground(new Color(255, 255, 255));
		lblNome.setBounds(10, 49, 113, 14);
		lblNome.setForeground(new Color(255,255,255));
		contentPane.add(lblNome);

		JLabel lblQuarto = new JLabel("Quarto:");
		lblQuarto.setBounds(21, 102, 46, 14);
		lblQuarto.setForeground(new Color(255,255,255));
		contentPane.add(lblQuarto);

		JLabel lblData = new JLabel("Data:");
		lblData.setBounds(21, 74, 46, 14);
		lblData.setForeground(new Color(255,255,255));
		contentPane.add(lblData);

		textNome = new JTextField();
		textNome.setBounds(119, 46, 120, 20);
		contentPane.add(textNome);
		textNome.setColumns(10);

		textData = new JTextField();
		textData.setBounds(55, 71, 86, 20);
		contentPane.add(textData);
		textData.setColumns(10);

		textQuarto = new JTextField();
		textQuarto.setBounds(65, 99, 45, 20);
		contentPane.add(textQuarto);
		textQuarto.setColumns(10);

		JButton btnCadastrar = new JButton("CADASTRAR");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nome = textNome.getText();
				String data = textData.getText();
				int quarto = Integer.parseInt(textQuarto.getText());

				if (cjs.incluirSessao(nome, data, quarto) == true) {

					JOptionPane.showMessageDialog(null, "Estadia cadastrada com sucesso!");
					
					textNome.setText("");
					textData.setText("");
					textQuarto.setText("");
				} else {
					JOptionPane.showMessageDialog(null, "Erro ocorreu, revise as informações!");

					textNome.setText("");
					textData.setText("");
					textQuarto.setText("");
				}
			}
		});
		
		JButton btnExcluir = new JButton("CANCELAR");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent f) {
				String nome = textNome.getText();
				String data = textData.getText();
				int quarto = Integer.parseInt(textQuarto.getText());

				if (cjs.excluirSessao(nome, data, quarto) == true) {

					JOptionPane.showMessageDialog(null, "Estadia cancelada com sucesso!");
					
					textNome.setText("");
					textData.setText("");
					textQuarto.setText("");
				} else {
					JOptionPane.showMessageDialog(null, "Erro ocorreu, revise as informações!");

					textNome.setText("");
					textData.setText("");
					textQuarto.setText("");
				}
			}
		});
		btnCadastrar.setBounds(65, 177, 119, 23);
		btnExcluir.setBounds(194, 177, 119, 23);
		contentPane.add(btnCadastrar);
		contentPane.add(btnExcluir);
		
		JButton btnListar = new JButton("LISTAR");
		btnListar.setBounds(323, 177, 119, 23);
		btnListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent g) {
				ResultSet rs = cjs.listarSessao();
				if(rs != null) {
					try {
						ResultSetMetaData rsmd = rs.getMetaData();
						DefaultTableModel model = (DefaultTableModel) tblUsuarios.getModel();
						int cols = rsmd.getColumnCount();
						String[] colName = new String[cols];
						for(int i=0;i<cols;i++) {
							colName[i]=rsmd.getColumnName(i+1);
							model.setColumnIdentifiers(colName);
						}
						String id, nome, data, quarto;
						while(rs.next()) {
							id = rs.getString(1);
							nome = rs.getString(2);
							data = rs.getString(3);
							quarto = rs.getString(4);
							String[] linha = {id, nome, data, quarto};
							model.addRow(linha);
						}
						
					} catch (SQLException e) {
						
						e.printStackTrace();
					}
				}
			}
		});
		
		contentPane.add(btnListar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(249, 46, 324, 120);
		contentPane.add(scrollPane);
		
		tblUsuarios = new JTable();
		scrollPane.setViewportView(tblUsuarios);
		
		
	}
}
