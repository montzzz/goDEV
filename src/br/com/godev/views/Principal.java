package br.com.godev.views;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.MenuKeyListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import br.com.godev.dbutils.DAOAluno;
import br.com.godev.domain.Aluno;

import javax.swing.event.MenuKeyEvent;
import javax.swing.JTable;
import java.awt.List;
import javax.swing.JSpinner;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JDesktopPane;

public class Principal extends JFrame {

	private JPanel contentPane;
	private String user;
	private String pass;
	private JTextField txtNome;
	private JTextField txtSobrenome;
	private DAOAluno dao = new DAOAluno();
	String[] colunas = {"id", "nome", "sobrenome"};
	ArrayList<Object[]> dados = new ArrayList<>();
	DefaultTableModel model = new DefaultTableModel(colunas,0);
	private JTable tblDados;
	private JScrollPane scroll_table;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public void findAllAlunos(){
		model.setRowCount(0);
		dados = dao.consultar();
		for (Object[] obj : dados) {
			model.addRow(obj);
		}
		
		this.tblDados.setModel(model);
	}	
	
	
	/**
	 * Create the frame.
	 */
	public Principal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550,500);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setForeground(Color.BLACK);
		menuBar.setBackground(Color.LIGHT_GRAY);
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Cadastros");
		menuBar.add(mnNewMenu);
		
		CardLayout cl = new CardLayout();
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(cl);		
		
		
		JPanel pnlPrincipal = new JPanel();
		contentPane.add(pnlPrincipal, "name_4895195611700");
		pnlPrincipal.setLayout(null);
		pnlPrincipal.setVisible(true);
		
		JLabel lblNewLabel = new JLabel("GoDEV");
		lblNewLabel.setFont(new Font("Roboto", Font.BOLD | Font.ITALIC, 32));
		lblNewLabel.setBounds(228, 25, 103, 37);
		pnlPrincipal.add(lblNewLabel);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 11, 504, 2);
		pnlPrincipal.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 73, 504, 2);
		pnlPrincipal.add(separator_1);
		
		JLabel lblNewLabel_1 = new JLabel("Seja bem vindo ao sistema GoDEV,");
		lblNewLabel_1.setFont(new Font("Roboto", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(117, 101, 319, 37);
		pnlPrincipal.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("acima no menu contém as opções");
		lblNewLabel_2.setFont(new Font("Roboto", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(117, 149, 319, 24);
		pnlPrincipal.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("disponíveis.");
		lblNewLabel_3.setFont(new Font("Roboto", Font.BOLD, 20));
		lblNewLabel_3.setBounds(223, 184, 113, 24);
		pnlPrincipal.add(lblNewLabel_3);
		
		JPanel pnlAluno = new JPanel();
		contentPane.add(pnlAluno, "name_4899845715400");
		pnlAluno.setLayout(null);
		
		tblDados = new JTable();
		tblDados.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtNome.setText(String.valueOf(tblDados.getValueAt(tblDados.getSelectedRow(), 1)));
				txtSobrenome.setText(String.valueOf(tblDados.getValueAt(tblDados.getSelectedRow(), 2)));
			}
		});
		tblDados.setBounds(10, 163, 314, -49);
		pnlAluno.add(tblDados);
		scroll_table = new JScrollPane(tblDados);            // add table to scroll panel
	    scroll_table.setBounds(10, 181, 441, 238);
	    scroll_table.setVisible(true);
	    pnlAluno.add(scroll_table);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(10, 26, 37, 14);
		pnlAluno.add(lblNome);
		
		txtNome = new JTextField();
		txtNome.setBounds(10, 43, 152, 20);
		pnlAluno.add(txtNome);
		txtNome.setColumns(10);
		
		JLabel lblSobrenome = new JLabel("Sobrenome:");
		lblSobrenome.setBounds(172, 26, 81, 14);
		pnlAluno.add(lblSobrenome);
		
		txtSobrenome = new JTextField();
		txtSobrenome.setColumns(10);
		txtSobrenome.setBounds(172, 43, 152, 20);
		pnlAluno.add(txtSobrenome);
		
		JButton btnIncluir = new JButton("Incluir");
		btnIncluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Inserir(txtNome.getText(), txtSobrenome.getText());
				
				findAllAlunos();
			}
		});
		btnIncluir.setBounds(10, 86, 89, 23);
		pnlAluno.add(btnIncluir);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Aluno alu = new Aluno();
				alu.setId(Integer.valueOf(String.valueOf(tblDados.getValueAt(tblDados.getSelectedRow(), 0))));
				alu.setNome(txtNome.getText());
				alu.setSobrenome(txtSobrenome.getText());
				
				dao.alterar(alu);
				
				findAllAlunos();
			}
		});
		btnEditar.setBounds(109, 86, 89, 23);
		pnlAluno.add(btnEditar);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int input = JOptionPane.showConfirmDialog(null, "Deseja realmente excluir o aluno?");
				// sim
				if (input == 0) {
					dao.excluir(Integer.valueOf(String.valueOf(tblDados.getValueAt(tblDados.getSelectedRow(), 0))));
				}
				findAllAlunos();
			}
		});
		btnExcluir.setBounds(209, 86, 89, 23);
		pnlAluno.add(btnExcluir);
		
		JLabel lblNewLabel_4 = new JLabel("Alunos cadastrados:");
		lblNewLabel_4.setFont(new Font("Roboto", Font.BOLD, 13));
		lblNewLabel_4.setBounds(10, 159, 126, 14);
		pnlAluno.add(lblNewLabel_4);
		pnlAluno.setVisible(false);
		
		JMenuItem menuItemAlunos = new JMenuItem("Alunos");
		menuItemAlunos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				contentPane.removeAll();
				contentPane.add(pnlAluno);
				contentPane.repaint();
				contentPane.revalidate();
			}
		});
		mnNewMenu.add(menuItemAlunos);
		
		
		cl.show(contentPane, "1");
		findAllAlunos();
	}
	
	public Principal (String user, String pass) {
		this.user = user;
		this.pass = pass;
	}

	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
	
	private void Inserir(String nome, String sobrenome) {
		if (nome.trim().length() == 0) {
			JOptionPane.showMessageDialog(contentPane, "Informe o nome", "Error", JOptionPane.ERROR_MESSAGE);
	    	throw new RuntimeException("Nome obrigatório.");
		}
		
		if (sobrenome.trim().length() == 0) {
			JOptionPane.showMessageDialog(contentPane, "Informe o sobrenome", "Error", JOptionPane.ERROR_MESSAGE);
	    	throw new RuntimeException("Sobrenome obrigatório.");
		}
		
		Aluno alu = new Aluno();
		alu.setNome(nome);
		alu.setSobrenome(sobrenome);
		
		dao.inserir(alu);		
	}
}
