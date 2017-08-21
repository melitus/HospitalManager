/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EHM.Customs;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;
import java.io.*;
import java.sql.*;
import javax.swing.JOptionPane;
/**
 *
 * @author ODOH
 */
public class JdbcConnection {
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
      cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/schoolmanagermain","root","");
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
