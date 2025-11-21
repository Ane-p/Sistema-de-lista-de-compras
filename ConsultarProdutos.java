package sistemaproj;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ConsultarProdutos extends JFrame {
    private JTable tabelaProdutos;
    private DefaultTableModel modelo;

    public ConsultarProdutos() {
        setTitle("Lista de Produtos");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        modelo = new DefaultTableModel();
        modelo.addColumn("ID");
        modelo.addColumn("Nome");
        modelo.addColumn("Quantidade");

        tabelaProdutos = new JTable(modelo);
        JScrollPane scrollPane = new JScrollPane(tabelaProdutos);

        JPanel panelBotoes = new JPanel();
        JButton btnVoltar = new JButton("Voltar");
        JButton btnExcluir = new JButton("Excluir Produto");

        // Botão para voltar para tela de cadastro
        btnVoltar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Fecha a janela atual
            }
        });
        // Botão para excluir um produto da lista
        btnExcluir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int linhaSelecionada = tabelaProdutos.getSelectedRow();
                if (linhaSelecionada != -1) {
                    int idProduto = (int) modelo.getValueAt(linhaSelecionada, 0); 
                    int confirmacao = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir?", "Confirmação", JOptionPane.YES_NO_OPTION);
                    if (confirmacao == JOptionPane.YES_OPTION) {
                        try {
                            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Mercado", "root", "");
                            String sql = "DELETE FROM produtos WHERE id_produto = ?";
                            PreparedStatement stmt = conn.prepareStatement(sql);
                            stmt.setInt(1, idProduto);

                            stmt.executeUpdate();
                            stmt.close();
                            conn.close();

                            JOptionPane.showMessageDialog(null, "Produto excluído com sucesso!");
                            modelo.removeRow(linhaSelecionada); // Remove visualmente
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(null, "Erro ao excluir produto!", "Erro", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Selecione um produto para excluir!", "Aviso", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        panelBotoes.add(btnVoltar);
        panelBotoes.add(btnExcluir);

        add(scrollPane, BorderLayout.CENTER);
        add(panelBotoes, BorderLayout.SOUTH);
        carregarProdutos();
    }
    private void carregarProdutos() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Mercado", "root", "");
            String sql = "SELECT id_produto, nome_produto, qtd_prod FROM produtos";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id_produto");
                String nome = rs.getString("nome_produto");
                int quantidade = rs.getInt("qtd_prod");
                modelo.addRow(new Object[]{id, nome, quantidade});
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao carregar os produtos!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ConsultarProdutos().setVisible(true);
        });
    }
}
