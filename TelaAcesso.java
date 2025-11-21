package sistemaproj;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaAcesso {
    public JFrame frmMercado;
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TelaAcesso window = new TelaAcesso();
                    window.frmMercado.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public TelaAcesso() {
        initialize();
    }

    private void initialize() {
        frmMercado = new JFrame();
        frmMercado.setTitle("Mercado HiperMais");
        frmMercado.getContentPane().setBackground(new Color(64, 0, 0));
        frmMercado.setBounds(100, 100, 452, 300);
        frmMercado.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmMercado.getContentPane().setLayout(null);

        JLabel lblmensagem = new JLabel("BEM VINDOS A LISTA DE COMPRAS DO MERCADO HIPERMAIS ");
        lblmensagem.setForeground(Color.WHITE);
        lblmensagem.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblmensagem.setBounds(10, 10, 416, 31);
        frmMercado.getContentPane().add(lblmensagem);

        JLabel lblNewLabel_1 = new JLabel("Vamos Fazer Compras?");
        lblNewLabel_1.setForeground(Color.WHITE);
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
        lblNewLabel_1.setBounds(113, 77, 205, 21);
        frmMercado.getContentPane().add(lblNewLabel_1);

        JButton btnproduto = new JButton("Montar Lista de compras");
        btnproduto.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnproduto.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TelaCadastroPro telaCadastro = new TelaCadastroPro();
                telaCadastro.setVisible(true);
            }
        });
        btnproduto.setBackground(Color.WHITE);
        btnproduto.setBounds(10, 149, 175, 31);
        frmMercado.getContentPane().add(btnproduto);

        JButton btnconsultar = new JButton("Consultar Lista");
        btnconsultar.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnconsultar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ConsultarProdutos telaConsulta = new ConsultarProdutos();
                telaConsulta.setVisible(true);
            }
        });
        btnconsultar.setBounds(217, 149, 188, 31);
        frmMercado.getContentPane().add(btnconsultar);
        
        JButton btnsair = new JButton("Sair");
        btnsair.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		System.exit(0);
        	}
        });
        btnsair.setFont(new Font("Tahoma", Font.PLAIN, 13));
        btnsair.setBounds(163, 216, 85, 21);
        frmMercado.getContentPane().add(btnsair);
    }
}