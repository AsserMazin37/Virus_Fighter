package com.mygdx.game;

import com.badlogic.gdx.ScreenAdapter;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;

public class UserName extends ScreenAdapter implements ActionListener{
	
	JFrame frame;
	
	JLabel userName, score, level;
	
	JTextField userField, scoreField, levelField;
	
	JButton signUp, login, cont;
	
	JPanel panel;
	
	public static String name;
	
	final VirusFighter game;
    boolean found = false;

	boolean check = false;
	private void database2(String user) throws ClassNotFoundException{
		Class.forName("org.sqlite.JDBC");
		Connection connection = null;
		try {
			connection = DriverManager.getConnection("jdbc:sqlite:sample.db");

	         Statement statement = connection.createStatement();
	         statement.setQueryTimeout(30);  // set timeout to 30 sec.
	         //statement.executeUpdate("DROP TABLE IF EXISTS userNames");
	         //statement.executeUpdate("CREATE TABLE userNames (name STRING)");   
             //statement.executeUpdate("INSERT INTO userNames values('Seif')");   
	         //statement.executeUpdate("UPDATE person SET name='Peter' WHERE id='1'");
	         //statement.executeUpdate("DELETE FROM person WHERE id='1'");
	         boolean found = false;
	           ResultSet resultSet = statement.executeQuery("SELECT * from userNames");
	           while(resultSet.next())
	           {
	        	   if(resultSet.getString("name").equals(user)) {
		 	              System.out.println("name = " + resultSet.getString("name"));
		 	              found = true;
	        	   }
	        	   else
	        		   continue;
	        	   if(found == true) {
		        	   JOptionPane.showMessageDialog(frame, "Username already found","Invalid",JOptionPane.WARNING_MESSAGE);
	        		   MainMenu.check = true;
	        		   break;
	        	   }
	              
	           }
	           if(found == false) {
	        	   //MainMenu.show = true;	   
	        	   JOptionPane.showMessageDialog(frame, "Welcome"+name,"Success",JOptionPane.INFORMATION_MESSAGE);

	        	   statement.executeUpdate("INSERT INTO userNames values(name)");  
        		   MainMenu.check = true;
	           }
	           statement.close();
	          }
	     catch(SQLException e){  System.err.println(e.getMessage()); }       
	      finally {         
	            try {
	                  if(connection != null)
	                     connection.close();
	                  }
	            catch(SQLException e) {  // Use SQLException class instead.          
	               System.err.println(e); 
	             }

	      }
	}
	 private void database(String user) throws ClassNotFoundException {
    	Class.forName("org.sqlite.JDBC");
		Connection connection = null;
		try {
			connection = DriverManager.getConnection("jdbc:sqlite:sample.db");

	         Statement statement = connection.createStatement();
	         statement.setQueryTimeout(30);  // set timeout to 30 sec.
	         //statement.executeUpdate("DROP TABLE IF EXISTS userNames");
	        // statement.executeUpdate("CREATE TABLE userNames (name STRING)");   
             //statement.executeUpdate("INSERT INTO userNames values('Seif')");   
	         //statement.executeUpdate("UPDATE person SET name='Peter' WHERE id='1'");
	         //statement.executeUpdate("DELETE FROM person WHERE id='1'");
	           ResultSet resultSet = statement.executeQuery("SELECT * from userNames");
	           while(resultSet.next())
	           {
	        	   if(resultSet.getString("name").equals(user)) {
		 	              System.out.println("name = " + resultSet.getString("name"));
		 	              found = true;
	        	   }
	        	   else
	        		   continue;
	        	   
	              
	           }
	           if(found == false) {
	        	   //MainMenu.show = true;	   
	        	   JOptionPane.showMessageDialog(frame, "Welcome "+"Seif","Success",JOptionPane.INFORMATION_MESSAGE);
	        	   /*statement.executeUpdate("INSERT INTO userNames values('user')");  
        		   MainMenu.check = true;*/
	           }
	           else if(found == true) {
	        		   MainMenu.check = true;
		        	   JOptionPane.showMessageDialog(frame, "Welcome"+name,"Success",JOptionPane.INFORMATION_MESSAGE);

	        	   }
	           statement.close();

	          }

	     catch(SQLException e){  System.err.println(e.getMessage()); }       
	      finally {         
	            try {
	                  if(connection != null)
	                     connection.close();
	                  }
	            catch(SQLException e) {  // Use SQLException class instead.          
	               System.err.println(e); 
	             }

	      }
    }
    public UserName(final VirusFighter game) {
    	
    	this.game = game;
    	
    	frame = new JFrame("Login");
		frame.setLayout(new FlowLayout());
		frame.setSize(250, 200);
		frame.setLocationRelativeTo(null);
		
		userName = new JLabel("Username");
		frame.add(userName);
		
		userField = new JTextField(12);
		frame.add(userField);
		
		signUp = new JButton("Sign Up");
		login = new JButton("Login");
		
		panel = new JPanel(new GridLayout(1,2));    // 1 row, 2 columns
		panel.add(signUp);
		panel.add(login);
		frame.add(panel);
		
		score = new JLabel("Score");
		frame.add(score);
		score.setVisible(false);
		
		scoreField = new JTextField(13);
		scoreField.setEditable(false);
		frame.add(scoreField);
		scoreField.setVisible(false);
		
		level = new JLabel("Last Level");
		frame.add(level);
		level.setVisible(false);
		
		levelField = new JTextField(10);
		levelField.setEditable(false);
		frame.add(levelField);
		levelField.setVisible(false);
		
		signUp.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				name = userField.getText();
				try {
			database2(name);
		} catch (ClassNotFoundException x) {
			// TODO Auto-generated catch block
			x.printStackTrace();
		}
			}
		});
		
		login.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					database(name);
				} catch (ClassNotFoundException x) {
					// TODO Auto-generated catch block
					x.printStackTrace();
				}
				/*userField.setEditable(false);
				score.setVisible(true);
				scoreField.setVisible(true);
				level.setVisible(true);
				levelField.setVisible(true);*/
				//panel.setVisible(false);
				//cont = new JButton("Continue");
				//frame.add(cont);
				check = true;
				cont.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						name = userField.getText();
						
					}
				});	
			}
		});
		
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
    
	@Override
	public void render(float delta) {
		game.batch.begin();
		
		if(check) {
			frame.dispose();
			game.setScreen(new CharacterMenu(game));
		}
		
		game.batch.end();
		
	}
}