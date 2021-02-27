package br.com.godev.dbutils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import br.com.godev.connection.Conexao;
import br.com.godev.domain.Aluno;

public class DAOAluno {
	
	Conexao db = new Conexao("SYSDBA", "masterkey");
	Aluno alu = new Aluno();
	
	public boolean inserir(Object obj) {
		alu = (Aluno) obj;
		String sql = "INSERT INTO ALUNOS (NOME, SOBRENOME) VALUES (?, ?);";
		Connection con;
		try {
			Class.forName(db.getDriver());
			con = DriverManager.getConnection(db.getUrl(), db.getUser(), db.getPass());
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, alu.getNome());
			pst.setString(2, alu.getSobrenome());
			int count = pst.executeUpdate();
			
			if (count > 0) {
				con.close();
				return true;
			} else {
				con.close();
				return false;
			}
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao inserir aluno, erro: " + e.getMessage());		
			return false;
		}
		
	}
	
	public boolean alterar(Object obj) {
		alu = (Aluno) obj;
		String sql = "UPDATE ALUNOS SET NOME = ?, SOBRENOME = ? WHERE ID = ?";
		Connection con;
		try {
			Class.forName(db.getDriver());
			con = DriverManager.getConnection(db.getUrl(), db.getUser(), db.getPass());
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, alu.getNome());
			pst.setString(2, alu.getSobrenome());
			pst.setInt(3, alu.getId());
			int count = pst.executeUpdate();
			
			if (count > 0) {
				con.close();
				return true;
			} else {
				con.close();
				return false;
			}
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao alterar aluno, erro: " + e.getMessage());		
			return false;
		}
		
	}
	
	public ArrayList<Object[]> consultar(){
		String sql = "SELECT * FROM ALUNOS";
		Connection con;
		PreparedStatement pst;
		ResultSet rs;
		ResultSetMetaData meta;
		ArrayList<Object[]> dados = new ArrayList<>();
		
		try {
			Class.forName(db.getDriver());
			con = DriverManager.getConnection(db.getUrl(), db.getUser(), db.getPass());
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			meta = rs.getMetaData();
			while (rs.next()) {
				Object [] dadosRet = new Object[meta.getColumnCount()];
				
				for (int i = 0; i < dadosRet.length; i++) {
					dadosRet[i] = rs.getObject(i + 1);
				}
				
				dados.add(dadosRet);
			}			
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao consultar aluno, erro: " + e.getMessage());		
		}
		
		return dados;
	}
	
	public boolean excluir(Integer id) {
		String sql = "DELETE FROM ALUNOS WHERE ID = ?";
		Connection con;
		PreparedStatement pst;
		try {
			Class.forName(db.getDriver());
			con = DriverManager.getConnection(db.getUrl(), db.getUser(), db.getPass());
			pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			int count = pst.executeUpdate();
			
			if (count > 0) {
				con.close();
				return true;
			} else {
				con.close();
				return false;
			}
			
		} catch (SQLException  | ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Erro ao deletar aluno, erro: " + e.getMessage());
			return false;
		}	
				
	}

}
