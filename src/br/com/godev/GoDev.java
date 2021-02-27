package br.com.godev;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import br.com.godev.connection.Conexao;
import br.com.godev.views.Principal;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.awt.event.FocusAdapter;
import java.awt.CardLayout;


public class GoDev {

	private JFrame frame;
	private JTextField txtUsuario;
	private JPasswordField txtSenha;
	private JLabel lblSenha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GoDev window = new GoDev();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GoDev() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		// Cria o frame principal da tela
		frame = new JFrame();
		frame.setTitle("GoDEV");
        frame.setVisible(true);
        frame.setBounds(10,10,250,300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.getContentPane().setLayout(null);
        // pega as dimensoes da tela e faz a tela de login abrir no centro da screen
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
        
        // Botão para entrar/logar
        JButton btnNewButton = new JButton("Entrar");
        btnNewButton.setBounds(76, 157, 96, 23);
        // click do botão
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		
        		if (txtUsuario.getText().trim().length() == 0 || txtUsuario.getText().trim().equals("Usuário")) {
        			final JPanel panel = new JPanel();
        	    	JOptionPane.showMessageDialog(panel, "Informe o usuário", "Error", JOptionPane.ERROR_MESSAGE);
        	    	txtUsuario.requestFocus();
        	    	throw new RuntimeException("Usuario inválido");
        		}
        		
        		if (txtSenha.getText().trim().length() == 0) {
        			final JPanel panel = new JPanel();
        	    	JOptionPane.showMessageDialog(panel, "Informe a senha", "Error", JOptionPane.ERROR_MESSAGE);
        	    	txtSenha.requestFocus();
        	    	throw new RuntimeException("Senha inválida");
        		}      		
        		
        		
    			Conexao conn = new Conexao(txtUsuario.getText(), txtSenha.getText());        		     		
        		
    			try {
					conn.con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
    			
        		// abre a tela principal do sistema        		
    			Principal prin = new Principal(txtUsuario.getText(), txtSenha.getText());
        		prin.main(null);
        		frame.setVisible(false);      		
        	}
        });
        
        // add o botão no frame (tela)
        frame.getContentPane().add(btnNewButton);
        
        txtUsuario = new JTextField();        
        txtUsuario.setFont(new Font("Roboto", Font.PLAIN, 11));
        txtUsuario.setBounds(46, 95, 160, 20);
        txtUsuario.setForeground(Color.GRAY);
        // crio um "placeholder" fake
        txtUsuario.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (txtUsuario.getText().equals("Usuário")) {
                	txtUsuario.setText("");
                	txtUsuario.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (txtUsuario.getText().isEmpty()) {
                	txtUsuario.setForeground(Color.GRAY);
                    txtUsuario.setText("Usuário");
                }
            }
        });
        // add o txt pro frame (tela)
        frame.getContentPane().add(txtUsuario);
        
        
        JLabel lblLogin = new JLabel("Login");
        lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
        lblLogin.setFont(new Font("Roboto", Font.BOLD | Font.ITALIC, 18));
        lblLogin.setBounds(10, 52, 226, 23);
        frame.getContentPane().add(lblLogin);
        
        lblSenha = new JLabel("Senha");
        lblSenha.setEnabled(false);
        lblSenha.setForeground(Color.GRAY);
        lblSenha.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent arg0) {
        		lblSenha.setVisible(false);
        		txtSenha.requestFocus();
        	}
        });
        lblSenha.setFont(new Font("Roboto", Font.PLAIN, 11));
        lblSenha.setBounds(46, 126, 160, 20);
        frame.getContentPane().add(lblSenha);
        
        txtSenha = new JPasswordField();
        txtSenha.addFocusListener(new FocusAdapter() {
        	@Override
        	public void focusLost(FocusEvent arg0) {        		
        		if (txtSenha.getText().length() == 0) {
        			lblSenha.setVisible(true);        			
        		}
        	}
        	@Override
        	public void focusGained(FocusEvent e) {
        		lblSenha.setVisible(false);
        	}
        });
        txtSenha.setFont(new Font("Roboto", Font.PLAIN, 11));
        txtSenha.setBounds(46, 126, 160, 20);
        txtSenha.setForeground(Color.GRAY);
        
        
        frame.getContentPane().add(txtSenha);
	}
}
