/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package JBDC;


/**
 *
 * @author Melitus
 */



import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class DbConnection {
  public  static Connection getconnection()
  {
    // for oracle  "jdbc:oracle:thin:@localhost:1521:XE";            
   // String ip = fileinputstream("ipaddress.txt");              
   // String driver = "jdbc:mysql://localhost:3307/electionmanager";
   // String user = "sunday";
   // String password = "melitus";

    Connection cn = null;
    try
    {
      Class.forName("com.mysql.jdbc.Driver");
      cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospitalmanager","root","");
     // JOptionPane.showMessageDialog(null, "Connection Established");
        return cn;
    }
    catch (Exception e)
    {
      System.out.println("Connections Error connecting to database at connect.class = " + e);
      return null;
    }
    
  }

 

  public boolean closeconnection(Connection cn)
  {
    try
    {
      cn.close();
      return true;
    }
    catch (Exception e)
    {
      System.out.println("yaya Connect error close = " + e);
    }return false;
  }  
}
