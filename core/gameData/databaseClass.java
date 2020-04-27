package gameData;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import com.mygdx.game.UserName;
import com.mygdx.game.VirusFighter;

public class databaseClass {

	 public static void database() throws ClassNotFoundException {
	    	Class.forName("org.sqlite.JDBC");
			Connection connection = null;
			try {
				connection = DriverManager.getConnection("jdbc:sqlite:sample.db");

		         Statement statement = connection.createStatement();
		         statement.setQueryTimeout(30);  // set timeout to 30 sec.
		           ResultSet resultSet = statement.executeQuery("SELECT * from dataofUsers");
		           while(resultSet.next())
		           {
		        	   if(resultSet.getString("name").equals(UserName.name)) {
		        		   if(Integer.parseInt(resultSet.getString("score")) < VirusFighter.played) {
		        			   int newScore = Integer.parseInt(resultSet.getString("score"))+1;
			        		   statement.executeUpdate("UPDATE dataofUsers SET score= '"+newScore+"' WHERE name='"+UserName.name+"'");
			        		   break;
		        		   }	
		        		   else if(Integer.parseInt(resultSet.getString("score")) == 1 && VirusFighter.played == 1) {
			        		   int newScore = Integer.parseInt(resultSet.getString("score"))+1;
			        		   statement.executeUpdate("UPDATE dataofUsers SET score= '"+newScore+"' WHERE name='"+UserName.name+"'");
			        		   break;
			        	   }
		        		   else if(Integer.parseInt(resultSet.getString("score")) == 2 && VirusFighter.played == 2) {
			        		   int newScore = Integer.parseInt(resultSet.getString("score"))+1;
			        		   statement.executeUpdate("UPDATE dataofUsers SET score= '"+newScore+"' WHERE name='"+UserName.name+"'");
			        		   break;
			        	   }
		        		   else if(Integer.parseInt(resultSet.getString("score")) == 3 && VirusFighter.played == 3) {
			        		   int newScore = Integer.parseInt(resultSet.getString("score"))+1;
			        		   statement.executeUpdate("UPDATE dataofUsers SET score= '"+newScore+"' WHERE name='"+UserName.name+"'");
			        		   break;
			        	   }
		        		   else if(Integer.parseInt(resultSet.getString("score")) == 4 && VirusFighter.played == 4) {
			        		   int newScore = Integer.parseInt(resultSet.getString("score"))+1;
			        		   statement.executeUpdate("UPDATE dataofUsers SET score= '"+newScore+"' WHERE name='"+UserName.name+"'");
			        		   break;
			        	   }
		        		   else if(Integer.parseInt(resultSet.getString("score")) == 5 && VirusFighter.played == 5) {
			        		   int newScore = Integer.parseInt(resultSet.getString("score"))+1;
			        		   statement.executeUpdate("UPDATE dataofUsers SET score= '"+newScore+"' WHERE name='"+UserName.name+"'");
			        		   break;
			        	   }
		        		   
		        	   }
		        	   
		        	   else
		        		   continue;
		        	   
		           }
		           statement.close();

		          }

		     catch(SQLException e){   }       
		      finally {         
		            try {
		                  if(connection != null)
		                     connection.close();
		                  }
		            catch(SQLException e) {  // Use SQLException class instead.          
		               
		             }

		      }
	    }
	 public static int update() throws ClassNotFoundException {
		 Class.forName("org.sqlite.JDBC");
		 int getScore = 0;
			Connection connection = null;
			try {
				connection = DriverManager.getConnection("jdbc:sqlite:sample.db");

		         Statement statement = connection.createStatement();
		         statement.setQueryTimeout(30);  // set timeout to 30 sec.
		           ResultSet resultSet = statement.executeQuery("SELECT * from dataofUsers");
		           while(resultSet.next())
		           {
		        	   if(resultSet.getString("name").equals(UserName.name)) {
		        		   getScore = Integer.parseInt(resultSet.getString("score"));
		        		   break;
		        	   }
		        	   else
		        		   continue;
		        	   
		           }
		           statement.close();
		          }

		     catch(SQLException e){   }       
		      finally {         
		            try {
		                  if(connection != null)
		                     connection.close();
		                  }
		            catch(SQLException e) {  // Use SQLException class instead.          
		               
		             }

		      }
			return getScore;
	 }
}
