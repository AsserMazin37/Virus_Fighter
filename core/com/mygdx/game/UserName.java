package com.mygdx.game;

import com.badlogic.gdx.Gdx;
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
	
	JLabel userName, level;
	
	JTextField userField, scoreField, levelField;
	
	JButton signUp, login, cont;
	
	JPanel panel;
	
	public static String name;
	
	final VirusFighter game;
    boolean found = false;
    public static boolean unlockSpaceShip = false;

	boolean check = false;
	boolean check2 = false;
	boolean inorup = false;
	static int userScore;
	static String send;
	
	 private void database(String user) throws ClassNotFoundException {
    	Class.forName("org.sqlite.JDBC");
		Connection connection = null;
		try {
			connection = DriverManager.getConnection("jdbc:sqlite:data.db");

	         Statement statement = connection.createStatement();
	         statement.setQueryTimeout(30);  // set timeout to 30 sec.
	         //statement.executeUpdate("DROP TABLE IF EXISTS dataofUsers");
	         statement.executeUpdate("CREATE TABLE IF NOT EXISTS dataofUsers  (name STRING, score int)");
	         //statement.executeUpdate("INSERT INTO dataofUsers values('newtry','4')");  
	         //statement.executeUpdate("UPDATE person SET name='Peter' WHERE id='1'");
	         //statement.executeUpdate("DELETE FROM person WHERE id='1'");
	          
	           ResultSet resultSet = statement.executeQuery("SELECT * from dataofUsers");
	           
	           while(resultSet.next())
	           {
	 	              System.out.println(resultSet.getString("name"));

	        	   if(resultSet.getString("name").equals(user)) {
		 	              found = true;
			        	   userScore = Integer.parseInt(resultSet.getString("score"));
		 	              break;
	        	   }
	        	   else
	        		   continue;
	           }
	           if(inorup == true) {
	        	   if(found == true) {
		        	   //MainMenu.show = true;	   
		        	   //JOptionPane.showMessageDialog(frame, "Welcome "+user+,"Success",JOptionPane.INFORMATION_MESSAGE);
		        	   //statement.executeUpdate("INSERT INTO userNames values('user')");  
	        		  // MainMenu.check = true;
	        		   if(userScore == 5) {
	        			   unlockSpaceShip = true;
	        		   }
		        	   check2 = true;
		        	   send = user;
		           }
		           else if(found == false) {
		        		   //MainMenu.check = true;
			        	   JOptionPane.showMessageDialog(frame, "Username not found!","Invalid",JOptionPane.ERROR_MESSAGE);
		        	   }
	           }
	           else {
	        	   if(found == true) {
		        	   //MainMenu.show = true;	   
		        	   //JOptionPane.showMessageDialog(frame, "Welcome "+user+,"Success",JOptionPane.INFORMATION_MESSAGE);
		        	   //statement.executeUpdate("INSERT INTO userNames values('user')");  
	        		  // MainMenu.check = true;
		        	   JOptionPane.showMessageDialog(frame, "Username already exists!","Invalid",JOptionPane.ERROR_MESSAGE);
		           }
		           else if(found == false) {
		        		   //MainMenu.check = true;
		        	   		statement.executeUpdate("INSERT INTO dataofUsers values('"+user+"' , '1')");  
			        	   JOptionPane.showMessageDialog(frame, "Successfully registered!","Info",JOptionPane.INFORMATION_MESSAGE);
			        	   send = user;
			        	   check = true;
		           }
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
    	frame = new JFrame("SignUp/Login");
		frame.setLayout(new FlowLayout());
		frame.setSize(300, 200);
		frame.setLocationRelativeTo(null);
		
		userName = new JLabel("Username:");
		frame.add(userName);
		
		userField = new JTextField(12);
		frame.add(userField);
		
		signUp = new JButton("Sign Up");
		login = new JButton("Login");
		
		panel = new JPanel(new GridLayout(1,2));    // 1 row, 2 columns
		panel.add(signUp);
		panel.add(login);
		frame.add(panel);
		
		level = new JLabel("Last Level Reached");
		frame.add(level);
		level.setVisible(false);
		
		scoreField = new JTextField(13);
		scoreField.setEditable(false);
		frame.add(scoreField);
		scoreField.setVisible(false);
		
		levelField = new JTextField(10);
		levelField.setEditable(false);
		frame.add(levelField);
		levelField.setVisible(false);
		
		signUp.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				inorup = false;
				name = userField.getText();
				try {
					database(userField.getText());
				} catch (ClassNotFoundException x) {
					x.printStackTrace();
				}
			}
		});
		
		login.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				inorup = true;
				try {
					database(userField.getText());
				} catch (ClassNotFoundException x) {
					x.printStackTrace();
				}
				if(check2 == true) {
					userField.setEditable(false);
					level.setVisible(true);
					scoreField.setVisible(true);
					scoreField.setText(Integer.toString(userScore));
					panel.setVisible(false);
					cont = new JButton("Continue");
					frame.add(cont);
				}
				
				cont.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						name = userField.getText();
						check = true;
					}
				});	
			}
		});

		frame.setVisible(true);
		frame.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		        if (JOptionPane.showConfirmDialog(frame, 
		            "Are you sure you want to close this window?", "Close Window?", 
		            JOptionPane.YES_NO_OPTION,
		            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
		        	Gdx.app.exit();
		        }
		    }
		});

		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
	}
    
	@Override
	public void render(float delta) {
		if(check == true) {
			frame.dispose();
			game.setScreen(new CharactersMenu(game));
		}		
	}
}
