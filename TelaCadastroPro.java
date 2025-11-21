package sistemaproj;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class TelaCadastroPro extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textqtd;
	private JTextField textprodutos;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastroPro frame = new TelaCadastroPro();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Create the frame.
	 */
	public TelaCadastroPro() {
		setTitle("Tela de Cadastro ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(64, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textqtd = new JTextField();
		textqtd.setBounds(119, 86, 96, 19);
		textqtd.setColumns(10);
		contentPane.add(textqtd);
		
		textprodutos = new JTextField();
		textprodutos.setColumns(10);
		textprodutos.setBounds(119, 49, 224, 19);
		contentPane.add(textprodutos);
		
		JLabel lblnameprod = new JLabel("Nome do Produto");
		lblnameprod.setForeground(Color.WHITE);
		lblnameprod.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblnameprod.setBounds(10, 51, 117, 13);
		contentPane.add(lblnameprod);
		
		JLabel lblquantidade = new JLabel("Quantidade:");
		lblquantidade.setForeground(Color.WHITE);
		lblquantidade.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblquantidade.setBounds(10, 88, 75, 13);
		contentPane.add(lblquantidade);
		
		JLabel lblNewLabel = new JLabel("CADASTRO DE PRODUTOS ");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(28, 10, 232, 17);
		contentPane.add(lblNewLabel);
		
		JButton btnlista = new JButton("Ver Lista de Produtos");
		btnlista.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnlista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 ConsultarProdutos telaConsulta = new ConsultarProdutos();
			        telaConsulta.setVisible(true);
			}
		});
		btnlista.setBounds(240, 173, 173, 21);
		contentPane.add(btnlista);
		
		JButton btnsalvar = new JButton("Cadastrar na Lista");
		btnsalvar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnsalvar.setBounds(28, 173, 159, 21);
		contentPane.add(btnsalvar);
		btnsalvar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String nomeProduto = textprodutos.getText();
		        String quantidade = textqtd.getText();
		        if (!nomeProduto.isEmpty() && !quantidade.isEmpty()) {
		            try {
		                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Mercado", "root", "");
		                String sql = "INSERT INTO produtos (nome_produto, qtd_prod) VALUES (?, ?)";
		                PreparedStatement stmt = conn.prepareStatement(sql);
		                stmt.setString(1, nomeProduto); 
		                stmt.setInt(2, Integer.parseInt(quantidade));
		                int linhasAfetadas = stmt.executeUpdate();
		                if (linhasAfetadas > 0) {
		                    JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso, Obrigada!", "Cadastro", JOptionPane.INFORMATION_MESSAGE);
		                    textqtd.setText(""); 
		                    textprodutos.setText("");
		                } else {
		                    JOptionPane.showMessageDialog(null, "Erro ao cadastrar produto.", "Erro", JOptionPane.WARNING_MESSAGE);
		                }
		                stmt.close();
		                conn.close();
		            } catch (SQLException ex) {
		                ex.printStackTrace();
		                JOptionPane.showMessageDialog(null, "Erro ao conectar ao banco!", "Erro", JOptionPane.ERROR_MESSAGE);
		            } catch (NumberFormatException ex) {
		                JOptionPane.showMessageDialog(null, "Quantidade deve ser um número!", "Erro", JOptionPane.WARNING_MESSAGE);
		            }
		        } else {
		            JOptionPane.showMessageDialog(null, "Todos os Campos Precisam ser Preenchidos", "Erro", JOptionPane.WARNING_MESSAGE);
		        }
		    }
		});
		JButton btnvoltar = new JButton("Voltar");
		btnvoltar.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
			        dispose(); // Fecha a janela atual (ConsultarProdutos)
			        TelaAcesso telaacesso = new TelaAcesso(); // Cria a tela anterior
			        telaacesso.frmMercado.setVisible(true); // Mostra a tela anterior
			    }
		});
		btnvoltar.setBounds(175, 221, 85, 21);
		contentPane.add(btnvoltar);
	}
}
