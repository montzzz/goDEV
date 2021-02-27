package br.com.godev.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

/** 
 *
 * Classe responsável pela conexão com o banco de dados Firebird 2.5
 *
 * Database: godev
 * User: sysdba
 * Password: masterkey
 *
 */

public class Conexao {

   public Connection con = null;
   public Statement stm = null;
   String url;
   String driver;
   

   String user;
   String pass;

   public Conexao(String user, String pass) {
	   this.url = "jdbc:firebirdsql:localhost/3050:C:/db/godev.FDB";
	   this.driver = "org.firebirdsql.jdbc.FBDriver"; 
	   this.user = user;
	   this.pass = pass;
	   
      try {
         Class.forName(this.driver);
         con =
            DriverManager.getConnection(
               this.url,
               this.user,
               this.pass);
         stm = con.createStatement();         
         
         System.out.println("Conexão estabelecida com sucesso!");
      } catch (Exception e) {
    	  final JPanel panel = new JPanel();
    	  JOptionPane.showMessageDialog(panel, "Erro ao se conectar com o banco de dados, erro: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    	  throw new RuntimeException("Erro ao se conectar com o banco de dados.");
      }
   }
   
   public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}
     
}
