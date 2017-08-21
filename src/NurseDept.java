
import Custom.*;
import JBDC.DbConnection;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

import Panels.*;
import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.Random;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;






/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Engr. Sunday
 */
public class NurseDept extends javax.swing.JFrame {
    
    Connection conn;
PreparedStatement pst;
ResultSet rst;
 private static final int IMG_WIDTH = 120;
    private static final int IMG_HEIGHT = 120;
    JLabel label;
    ImageIcon photo;
    WritableRaster raster;
    DataBufferByte data;
    File image;
//    webcam web = new webcam();
     boolean webtab = true;
     Icon ico;
     boolean location = false;
     

//   private NewPatient testPanel1;
//   private Services testPanel2;
//   private WelcomeNurse testPanel3;
    boolean testbutton = false;


    /**
     * Creates new form NurseDept
     */
    public NurseDept() {
        initComponents();
        this.pack();
        this.setExtendedState(MAXIMIZED_BOTH);
        
        generatedNumbers();
        fillcombo();
       
       // NewpAtNo1.setText(generateSessionKey(8));
        
    
//        LayoutManager grid = new GridLayout(0,1);
//jMenuBar1.setLayout(grid);
//setJMenuBar(jMenuBar1);
//        this.setMaximumSize(null);
//        testPanel1 = new NewPatient();
//        testPanel2 = new Services();
//        testPanel3 = new WelcomeNurse();
         // buttonGroup1.add(jButton1);buttonGroup1.add(jButton2);
      
     //jSlidingPanel1.setSlidingComponents(new Component[]{testPanel3, testPanel2,testPanel1});
        
        //SlidingPanel2.setImage(new ImageIcon(this.getClass().getResource("icons/images.jpg")).getImage());
    }
    private static BufferedImage resizeImage(BufferedImage originalImage, int type) {
        BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, type);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
        g.dispose();

        return resizedImage;
    }
 public Image toImage(BufferedImage bufferedImage) {
        return Toolkit.getDefaultToolkit().createImage(bufferedImage.getSource());
 }
 private void fillcombo()
{
     try{
      
   conn=DbConnection.getconnection();
      String sql= "select distinct RoomNo from room order by RoomNo";
      pst=conn.prepareStatement(sql);
      rst=pst.executeQuery();
      while(rst.next()){
          String add=rst.getString("RoomNo");
          txtRoomNo.addItem(add);
          cmbRoomNo.addItem(add);
          
         }
        }catch(HeadlessException | SQLException ex){
           JOptionPane.showMessageDialog(this,ex); 
        }}
 private void getUserDetatils() {
  PatientIDy.getDocument().addDocumentListener(new DocumentListener() {

            private void updateId(DocumentEvent e) {
                try {
                    Document doc = e.getDocument();
                    String stringId = doc.getText(0, doc.getLength()).trim();
                    if (stringId != null && !stringId.isEmpty()) {
                        int id = Integer.parseInt(stringId);
                        String sql = "select * from patienttable where Patientid = '" + id + "'";
                        pst = conn.prepareStatement(sql);
                        rst = pst.executeQuery();
                        if (rst.next()) {
                            String add1 = rst.getString("surname");

//                            jTextField2.setText(add1);
//                            String add2 = rst.getString("middlename");
//                            jTextField3.setText(add2);
//                            String add3 = rst.getString("patient_card_no");
//                            patientid.setText(add3);


                            byte[] imagedata = rst.getBytes("image");
                            InputStream inStream = rst.getBinaryStream("image");
                            BufferedImage originalImage = ImageIO.read(inStream);
                            int type = originalImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : originalImage.getType();
                            BufferedImage resizeImageJpg = resizeImage(originalImage, type);
                            ImageIcon imageIcon = new ImageIcon(resizeImageJpg);

                            //jLabel5.setIcon(imageIcon);
       
       }
                    else {
                        restFields();
                    }
            
                    }
            
                else {
                        restFields();
            }
        }
    
    catch (Exception ex){
        ex.printStackTrace();
        System.out.printf("the error", ex.getMessage());
 }}
            private void restFields() {
                PatientIDy.setText("");
                NewPatSurname1.setText("");
                //jLabel5.setText("");
               // userTypeComboBox.setSelectedIndex(0);
            }

            public void insertUpdate(DocumentEvent e) {
                updateId(e);
            }

            public void removeUpdate(DocumentEvent e) {
                updateId(e);
            }

            public void changedUpdate(DocumentEvent e) {
                updateId(e);
            }
        });
  }

    public void generatedNumbers(){
        // String dCase = "abcdefghijklmnopqrstuvwxyz";
    String uCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
     //String sChar = "!@#$%^&*";
    String intChar = "0123456789";
     Random r = new Random();
     String pass = "";
     System.out.println ("Generating pass...");
        while (pass.length () != 8){
            int rPick = r.nextInt(4);
            //if (rPick == 0){
                //int spot = r.nextInt(25);
               // pass += dCase.charAt(spot);
            //} 
             if (rPick == 1) {
                int spot = r.nextInt (25);
                pass += uCase.charAt(spot);
            } 
            //else if (rPick == 2) {
               // int spot = r.nextInt (7);
               // pass += sChar.charAt(spot);
            //} 
            else if (rPick == 3){
                int spot = r.nextInt (9);
                pass += intChar.charAt (spot);
            }
        }
        System.out.println ("Generated Pass: " + pass);
        
    NewpAtNo1.setText(pass);

        
    }
    public static String generateSessionKey(int length){
String alphabet = 
        new String("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"); //9
int n = alphabet.length(); //10

String result = new String(); 
Random r = new Random(); //11

for (int i=0; i<length; i++) //12
    result = result + alphabet.charAt(r.nextInt(n)); //13

 return result;
 
}
    public void getData(){
       Statement stmt = null;
        try{
            String nurse = "nurses";
            conn = DbConnection.getconnection();
            stmt=conn.createStatement();
            rst=stmt.executeQuery("SELECT * FROM login where name = '" + nurse + "'" );
            rst.next();
            
          String  username=rst.getString("username");
         String   password=rst.getString("password");
           // NurseEditUsername.setText(username);
    //        pass.setText(password);
            
            rst.close();
            stmt.close();
            conn.close();
        }
        catch(Exception e){
            try{
                rst.close();
                stmt.close();
                conn.close();
            }
            catch(Exception ee){}
            
            JOptionPane.showMessageDialog(null,"Couldn't upload data n"+e, "Contact admin", JOptionPane.ERROR_MESSAGE);
        }
    }
    
   
    /*public void clearpatientRegForm(){
    NewPatRegNo.setText("");
    NewPatSurname.setText("");
    NewPatAddress.setText("");
    
    newpatnextofkin.setText("");
    newpatphone.setText("");
    NewPatFatherName.setText("");
   
    }
     public String getImageLocation()
{
   String da = String.valueOf(image.getAbsoluteFile().getPath()); 
   return da;
}
*/
     public void LoadIntoTable1(){

     String sql="select * from patientregistration"; 
    try {
        pst= conn.prepareStatement(sql);
        rst= pst.executeQuery();
     jTablePatReg.setModel(DbUtils.resultSetToTableModel(rst));
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(null, ex);
    }
};
    /* private void validatelogindetails() {
        String missing = "Missing the following : \n";
        boolean miss = false;
        if (nurseUsername.getText() == null || nurseUsername.getText().equals("")) {
            missing += "Username.\n";
            miss = true;
        }
        if (nursepass.getText() == null || nursepass.getText().equals("")) {
            missing += "Password.\n";
            miss = true;
        }
        if (miss) {
            final ImageIcon icon1 = new javax.swing.ImageIcon(getClass().getResource("editcor.jpg"));
            JOptionPane.showMessageDialog(this, missing, "Text",JOptionPane.INFORMATION_MESSAGE,   icon1);
            
        } else {
            if(validatepass(nursepass)){
                login();
            }
           
        }}
     */
     public void login(){
      try{
          
                 String login = "insert into login(username,password,status) values (?,?,?)";
                 pst=conn.prepareStatement(login);
            String statusn = "nurses";
            
            // pst.setString(1, nurseUsername.getText());
           // pst.setString(2, nursepass.getText());
           pst.setString(3, statusn );
            pst.executeUpdate();
                 
JOptionPane.showMessageDialog(null, " Login details  Uploaded successfully !", "That is good",
   JOptionPane.INFORMATION_MESSAGE  );
                

        }
          
            catch(Exception ee){
            
            JOptionPane.showMessageDialog(null, "   login details could not be inserted\t"+ee, "contact admin if the problem persist", JOptionPane.ERROR_MESSAGE);
        }
     
     
     }
    public void validateNewPatForm() throws FileNotFoundException{
    String missing = "Missing the following : \n";
        boolean miss = false;
        if (NewPatSurname1.getText() == null || NewPatSurname1.getText().equals("")) {
            missing += "Patient's Registration No.\n";
            miss = true;
        
        }
        if (NewPatAddress1.getText() == null || NewPatAddress1.getText().equals("")) {
            missing += "Middlename\n";
            miss = true;
        }
        
         if (NewPatDate1.getDate() == null || NewPatDate1.getDate().equals("")) {
            missing += "Address\n";
            miss = true;
        }
          if (NewPatStatus1.getSelectedItem() == null || NewPatStatus1.getSelectedItem().equals("")) {
            missing += "Marital Status\n";
            miss = true;
        }
          if (NewPatCity1.getText() == null || NewPatCity1.getText().equals("")) {
            missing += "City\n";
            miss = true;
        }if (NewPatPhone1.getText() == null || NewPatPhone1.getText().equals("")) {
            missing += "Mobile Number\n";
            miss = true;
        }if (NewPaatAge1.getText() == null || NewPaatAge1.getText().equals("")) {
            missing += "Age\n";
            miss = true;
        }if (NewPatDoctorAssign1.getText() == null || NewPatDoctorAssign1.getText().equals("")) {
            missing += "Assign Doctor\n";
            miss = true;
        }if (NewPaToCcupation1.getText() == null || NewPaToCcupation1.getText().equals("")) {
            missing += "Occupation\n";
            miss = true;
        }
        if (miss) {
            JOptionPane.showMessageDialog(this, missing, "Missing Fields", JOptionPane.ERROR_MESSAGE);
        }
    else{
    
    saveToDatabase();
    }
    }
public void saveToDatabase() throws FileNotFoundException{
    
    
    try{
            conn = DbConnection.getconnection();
           

      String d1  = ((JTextField)NewPatDate1.getDateEditor().getUiComponent()).getText(); 
        
                 String filer= "Cam.jpg";
                 java.io.File fBlob = new java.io.File ( filer); 
                     java.io.FileInputStream is = new java.io.FileInputStream ( fBlob ); 
                 
            String sql= "insert into patienttable(Patientid,Surname,Firstname,Address,MaritalStatus,RegistrationDate,City,"
                    + "MobileNo,Age,Gender,AssignDoctor,Occupation,StateOfOrigin,ImagePatient,Temperature,Purse,BloodPressure,BloodGroup,Weight,Height)"
                    + "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            
            pst=conn.prepareStatement(sql);
            System.out.print(sql);
             pst.setString(1, NewpAtNo1.getText());
            pst.setString(2, NewPatSurname1.getText());
            pst.setString(3, NewPatFirstname1.getText());
             pst.setString(4, NewPatAddress1.getText());
              pst.setString(5, String.valueOf(NewPatStatus1.getSelectedItem()));
              pst.setString(6, d1);
               pst.setString(7, NewPatCity1.getText());
               pst.setString(8,  NewPatPhone1.getText());
                pst.setString(9, NewPaatAge1.getText());
                pst.setString(10,  String.valueOf(NewPaTgENDER1.getSelectedItem()));
                pst.setString(11, NewPatDoctorAssign1.getText() );
                pst.setString(12,  NewPaToCcupation1.getText());
                pst.setString(13, String.valueOf(NewPatSate1.getSelectedItem()));
                 pst.setBinaryStream (14, is, (int) fBlob.length() );
                pst.setString(15,  txtxTemperature.getText());
                pst.setString(16, txtxPurse.getText() );
                pst.setString(17,  txtxBP.getText());
                pst.setString(18,  String.valueOf(txtBgROUP.getSelectedItem()));
                pst.setString(19, txxtWeight.getText() );
                pst.setString(20,  txtHeight.getText());
                
                pst.executeUpdate();
                 
       
                JOptionPane.showMessageDialog(null,"Patient's Data Uploaded successfully !");
                
             
           
        
    }

            catch(Exception e){
                System.out.print(e);
            
            //JOptionPane.showMessageDialog(null, "Patient's Data could not be inserted\t"+e, "contact admin if the problem persist", JOptionPane.ERROR_MESSAGE);
        
    }}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        NewPatCity1 = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        NewPatSurname1 = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        NewPaatAge1 = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        NewPaToCcupation1 = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        NewPatDoctorAssign1 = new javax.swing.JTextField();
        NewpAtNo1 = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        NewPatSate1 = new javax.swing.JComboBox();
        NewPaTgENDER1 = new javax.swing.JComboBox();
        NewPatDate1 = new com.toedter.calendar.JDateChooser();
        jScrollPane3 = new javax.swing.JScrollPane();
        NewPatAddress1 = new javax.swing.JTextArea();
        NewPatStatus1 = new javax.swing.JComboBox();
        NewPatPhone1 = new org.jdesktop.swingx.JXFormattedTextField();
        NewPatFirstname1 = new javax.swing.JTextField();
        panelwebcam = new javax.swing.JPanel();
        jlabelpictureDisplay = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jPanel16 = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        txtxTemperature = new javax.swing.JTextField();
        txtxBP = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        txxtWeight = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        txtHeight = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        txtxPurse = new javax.swing.JTextField();
        txtBgROUP = new javax.swing.JComboBox();
        jPanel17 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTablePatReg = new org.jdesktop.swingx.JXTable();
        jPanel18 = new javax.swing.JPanel();
        jxtxtnewPatreg = new org.jdesktop.swingx.JXSearchField();
        jLabel35 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jPanel26 = new javax.swing.JPanel();
        jLabel57 = new javax.swing.JLabel();
        jPanel27 = new javax.swing.JPanel();
        jLabel58 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        PatientIDy = new javax.swing.JTextField();
        txtPatientName = new javax.swing.JTextField();
        jLabel62 = new javax.swing.JLabel();
        txtFirstname = new javax.swing.JTextField();
        txtGender = new javax.swing.JTextField();
        jLabel63 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        txtOccupation = new javax.swing.JTextField();
        jScrollPane6 = new javax.swing.JScrollPane();
        txtRemarks = new javax.swing.JTextArea();
        jLabel68 = new javax.swing.JLabel();
        datedischarge = new com.toedter.calendar.JDateChooser();
        NewPatAdmitDate = new javax.swing.JTextField();
        txtDoctorName = new javax.swing.JTextField();
        txtRoomNo = new javax.swing.JComboBox();
        jPanel31 = new javax.swing.JPanel();
        SearchPatDischarge = new org.jdesktop.swingx.JXSearchField();
        jLabel71 = new javax.swing.JLabel();
        jPanel32 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jtbdischrgePatient = new javax.swing.JTable();
        btnSavesd = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jPanel19 = new javax.swing.JPanel();
        jLabel36 = new javax.swing.JLabel();
        jPanel20 = new javax.swing.JPanel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        PatientID1 = new javax.swing.JTextField();
        txtSurname1 = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        txtFirstnam1 = new javax.swing.JTextField();
        txtgendert = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        txtDisease1 = new javax.swing.JTextField();
        txtDoctorName1 = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtRemarks1 = new javax.swing.JTextArea();
        cmbRoomNo = new javax.swing.JComboBox();
        Admitdate = new com.toedter.calendar.JDateChooser();
        jPanel21 = new javax.swing.JPanel();
        btnSave1 = new javax.swing.JButton();
        jPanel22 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        AdmitPatTable = new javax.swing.JTable();
        jPanel24 = new javax.swing.JPanel();
        SearchPatAdmit = new org.jdesktop.swingx.JXSearchField();
        jXLabel1 = new org.jdesktop.swingx.JXLabel();
        jPanel5 = new javax.swing.JPanel();
        jPanel23 = new javax.swing.JPanel();
        jlImage = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jLabel77 = new javax.swing.JLabel();
        jScrollPane10 = new javax.swing.JScrollPane();
        jTextArea4 = new javax.swing.JTextArea();
        jScrollPane11 = new javax.swing.JScrollPane();
        jTextArea5 = new javax.swing.JTextArea();
        jLabel78 = new javax.swing.JLabel();
        txtNameOfPatientSurname = new javax.swing.JTextField();
        jLabel79 = new javax.swing.JLabel();
        jLabel80 = new javax.swing.JLabel();
        txtCardNumber1 = new javax.swing.JTextField();
        jLabel81 = new javax.swing.JLabel();
        txtFirstname1 = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jXPanel1 = new org.jdesktop.swingx.JXPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        TableDisplayPatTreatment = new org.jdesktop.swingx.JXTable();
        jPanel44 = new javax.swing.JPanel();
        jLabel83 = new javax.swing.JLabel();
        SearchGiveTreatment = new javax.swing.JTextField();
        jPanel14 = new javax.swing.JPanel();
        jLabel45 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel25 = new javax.swing.JPanel();
        jPanel38 = new javax.swing.JPanel();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();
        txtnurseSurname = new javax.swing.JTextField();
        txtfristname = new javax.swing.JTextField();
        txtContactNo1 = new javax.swing.JTextField();
        txtEmailID1 = new javax.swing.JTextField();
        txtQualifications1 = new javax.swing.JTextField();
        btnupdatenurse = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jPanel39 = new javax.swing.JPanel();
        jScrollPane13 = new javax.swing.JScrollPane();
        nuRSEpROFILE = new org.jdesktop.swingx.JXTable();
        jPanel40 = new javax.swing.JPanel();
        jLabel75 = new javax.swing.JLabel();
        jPanel45 = new javax.swing.JPanel();
        jLabel84 = new javax.swing.JLabel();
        SearchGiveTreatment1 = new javax.swing.JTextField();
        jPanel34 = new javax.swing.JPanel();
        jPanel36 = new javax.swing.JPanel();
        jLabel47 = new javax.swing.JLabel();
        jPanel37 = new javax.swing.JPanel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        UserPasswordChange = new javax.swing.JPasswordField();
        CofirmPassword = new javax.swing.JPasswordField();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        OldPassword = new javax.swing.JPasswordField();
        NewPassword = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTabbedPane1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jTabbedPane1MouseMoved(evt);
            }
        });

        jTabbedPane2.setBackground(new java.awt.Color(0, 102, 0));
        jTabbedPane2.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        jTabbedPane2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jPanel12.setBackground(new java.awt.Color(51, 51, 255));

        jLabel15.setFont(new java.awt.Font("Tempus Sans ITC", 1, 24)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("NEW PATIENT REGISTRATION DASHBOARD");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 1107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addComponent(jLabel15)
                .addGap(0, 11, Short.MAX_VALUE))
        );

        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 51, 204), 2, true), "Patient Basic Details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial Narrow", 1, 24), new java.awt.Color(0, 102, 255))); // NOI18N

        NewPatCity1.setEnabled(false);

        jLabel16.setText("Registration Date");

        NewPatSurname1.setEnabled(false);

        jLabel17.setText("Surname");

        jLabel18.setText("City");

        NewPaatAge1.setEnabled(false);

        jLabel19.setText("Assigned Doctor");

        jLabel20.setText("Occupation");

        NewPaToCcupation1.setEnabled(false);
        NewPaToCcupation1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NewPaToCcupation1ActionPerformed(evt);
            }
        });

        jLabel21.setText("Address");

        jLabel22.setText("Age");

        jLabel23.setText("Registration Number");

        jLabel24.setText("Gender");

        jLabel25.setText("Marital Status");

        jLabel26.setText("State of Origin");

        NewPatDoctorAssign1.setEnabled(false);

        NewpAtNo1.setEnabled(false);
        NewpAtNo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NewpAtNo1ActionPerformed(evt);
            }
        });

        jLabel27.setText("FirstName");

        jLabel28.setText("Mobile No");

        NewPatSate1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Choose State...", "Abuja", "Anambra", "Abia", "Enugu", "Ekiti", " ", " " }));

        NewPaTgENDER1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Choose gender...", "Male", "Female", " " }));
        NewPaTgENDER1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NewPaTgENDER1ActionPerformed(evt);
            }
        });

        NewPatAddress1.setColumns(20);
        NewPatAddress1.setRows(5);
        NewPatAddress1.setEnabled(false);
        jScrollPane3.setViewportView(NewPatAddress1);

        NewPatStatus1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Choose Status...", "Single", "Married", "Divorced", " " }));

        NewPatPhone1.setEnabled(false);

        NewPatFirstname1.setEnabled(false);

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16)
                    .addComponent(jLabel28)
                    .addComponent(jLabel18)
                    .addComponent(jLabel23)
                    .addComponent(jLabel17)
                    .addComponent(jLabel27)
                    .addComponent(jLabel21)
                    .addComponent(jLabel25)
                    .addComponent(jLabel24)
                    .addComponent(jLabel26)
                    .addComponent(jLabel20)
                    .addComponent(jLabel19)
                    .addComponent(jLabel22))
                .addGap(28, 28, 28)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(NewPaToCcupation1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(NewPatDoctorAssign1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(NewPaTgENDER1, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(NewPaatAge1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(NewPatDate1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(NewPatStatus1, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(NewpAtNo1)
                    .addComponent(NewPatSurname1)
                    .addComponent(NewPatCity1)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE)
                    .addComponent(NewPatSate1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(NewPatPhone1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(NewPatFirstname1))
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel23)
                            .addComponent(NewpAtNo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel17))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(NewPatSurname1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel27)
                    .addComponent(NewPatFirstname1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(NewPatStatus1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel16)
                    .addComponent(NewPatDate1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(NewPatCity1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(NewPatPhone1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(NewPaatAge1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel22))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(NewPaTgENDER1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel24))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(NewPatDoctorAssign1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(NewPaToCcupation1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel20))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(NewPatSate1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel28)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        panelwebcam.setBackground(new java.awt.Color(102, 102, 102));
        panelwebcam.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 51, 255), 3, true));

        javax.swing.GroupLayout panelwebcamLayout = new javax.swing.GroupLayout(panelwebcam);
        panelwebcam.setLayout(panelwebcamLayout);
        panelwebcamLayout.setHorizontalGroup(
            panelwebcamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlabelpictureDisplay, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
        );
        panelwebcamLayout.setVerticalGroup(
            panelwebcamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlabelpictureDisplay, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
        );

        jPanel15.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 0), 2, true));

        jButton2.setText("Open Camera");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButton2)
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel16.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 255), 2, true), "Enter Basic Health Info", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial Black", 1, 18))); // NOI18N

        jLabel29.setText("Blood Pessure");

        jLabel30.setText("Blood Group");

        jLabel31.setText("Purse");

        jLabel32.setText("Temperature");

        jLabel33.setText("Weight");

        jLabel34.setText("Height");

        txtBgROUP.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "O+", "O-", "A+", "A-", "B+", "B-", "AB+", "AB-" }));

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel30)
                            .addComponent(jLabel33)
                            .addComponent(jLabel34))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txxtWeight)
                            .addComponent(txtHeight)
                            .addComponent(txtBgROUP, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(jLabel29)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtxBP, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel32)
                            .addComponent(jLabel31))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtxTemperature, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                            .addComponent(txtxPurse))))
                .addGap(37, 37, 37))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32)
                    .addComponent(txtxTemperature, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31)
                    .addComponent(txtxPurse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(txtxBP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(txtBgROUP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33)
                    .addComponent(txxtWeight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel34)
                    .addComponent(txtHeight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel17.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 51, 255), 3, true));

        jTablePatReg.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "PatientID", "Surname", "Firstname", "Gender"
            }
        ));
        jTablePatReg.setGridColor(new java.awt.Color(0, 51, 204));
        jTablePatReg.setShowGrid(true);
        jScrollPane1.setViewportView(jTablePatReg);

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 349, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 413, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel18.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));

        jxtxtnewPatreg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jxtxtnewPatregKeyPressed(evt);
            }
        });

        jLabel35.setText("Search By name or Number");

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addComponent(jLabel35)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jxtxtnewPatreg, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jxtxtnewPatreg, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                .addComponent(jLabel35))
        );

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/savecu4.jpg"))); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/print-icon.png"))); // NOI18N
        jButton1.setText("Print Patient");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton7.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jButton7.setForeground(new java.awt.Color(0, 51, 255));
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/SignOut-icon.png"))); // NOI18N
        jButton7.setLabel("Sign Out");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(81, 81, 81)
                                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(49, 49, 49)
                                .addComponent(panelwebcam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton1))
                            .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, 1070, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(panelwebcam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(69, 69, 69)
                                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                .addContainerGap(35, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Registration", jPanel4);

        jPanel26.setBackground(new java.awt.Color(102, 102, 255));

        jLabel57.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        jLabel57.setForeground(new java.awt.Color(255, 255, 255));
        jLabel57.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel57.setText("Enter the Patient Details for Discharge");

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addComponent(jLabel57, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel57, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
        );

        jPanel27.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 255), 2, true), "Patient Discharge Info", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 18))); // NOI18N

        jLabel58.setText("Patient ID");

        jLabel59.setText("Surname");

        jLabel60.setText("Gender");

        jLabel61.setText("Remarks");

        PatientIDy.setEditable(false);

        txtPatientName.setEditable(false);

        jLabel62.setText("Firstname");

        txtFirstname.setEditable(false);

        txtGender.setEditable(false);

        jLabel63.setText("Occupation");

        jLabel64.setText("Admit Date");

        jLabel65.setText("Room No.");

        jLabel67.setText("Doctor Name");

        txtOccupation.setEditable(false);

        txtRemarks.setColumns(20);
        txtRemarks.setRows(5);
        jScrollPane6.setViewportView(txtRemarks);

        jLabel68.setText("Discharge Date");

        NewPatAdmitDate.setEnabled(false);

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel58)
                    .addComponent(jLabel59)
                    .addComponent(jLabel60)
                    .addComponent(jLabel62)
                    .addComponent(jLabel64)
                    .addComponent(jLabel63)
                    .addComponent(jLabel65)
                    .addComponent(jLabel67)
                    .addComponent(jLabel61)
                    .addComponent(jLabel68))
                .addGap(53, 53, 53)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtPatientName, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtGender)
                    .addComponent(txtFirstname)
                    .addComponent(txtOccupation)
                    .addComponent(datedischarge, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                    .addComponent(jScrollPane6)
                    .addComponent(NewPatAdmitDate)
                    .addComponent(txtDoctorName)
                    .addComponent(txtRoomNo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PatientIDy, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel27Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel58))
                    .addComponent(PatientIDy, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel59)
                    .addComponent(txtPatientName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel62)
                    .addComponent(txtFirstname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel60)
                    .addComponent(txtGender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtOccupation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel63))
                .addGap(21, 21, 21)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel64)
                    .addComponent(NewPatAdmitDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel27Layout.createSequentialGroup()
                        .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel65)
                            .addComponent(txtRoomNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25)
                        .addComponent(jLabel67))
                    .addComponent(txtDoctorName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(datedischarge, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel68))
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel27Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel27Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jLabel61)))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        jPanel31.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 0, 204), 2, true));

        SearchPatDischarge.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SearchPatDischargeKeyPressed(evt);
            }
        });

        jLabel71.setText("Search By name or Number");

        javax.swing.GroupLayout jPanel31Layout = new javax.swing.GroupLayout(jPanel31);
        jPanel31.setLayout(jPanel31Layout);
        jPanel31Layout.setHorizontalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel71)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(SearchPatDischarge, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel31Layout.setVerticalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel71)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(SearchPatDischarge, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel32.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 153, 255), 2, true));

        jtbdischrgePatient.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "PatientID", "Surname", "Firstname", "Gender"
            }
        ));
        jtbdischrgePatient.setGridColor(new java.awt.Color(0, 51, 255));
        jtbdischrgePatient.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtbdischrgePatientMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(jtbdischrgePatient);

        javax.swing.GroupLayout jPanel32Layout = new javax.swing.GroupLayout(jPanel32);
        jPanel32.setLayout(jPanel32Layout);
        jPanel32Layout.setHorizontalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 445, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel32Layout.setVerticalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnSavesd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/savecu4.jpg"))); // NOI18N
        btnSavesd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSavesdActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(btnSavesd, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(68, 68, 68)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(152, Short.MAX_VALUE))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jPanel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel32, javax.swing.GroupLayout.PREFERRED_SIZE, 448, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnSavesd, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(77, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Discharge", jPanel7);

        jPanel19.setBackground(new java.awt.Color(0, 153, 255));

        jLabel36.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(255, 255, 255));
        jLabel36.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel36.setText("WELCOME TO PATIENT ADMIT INFO");

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel36, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1109, Short.MAX_VALUE)
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel36, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)
        );

        jPanel20.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 51, 255), 2, true), "Patient Admit Info", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial Rounded MT Bold", 1, 18))); // NOI18N

        jLabel37.setText("Patient ID");

        jLabel38.setText("Surname");

        jLabel39.setText("Gender");

        jLabel40.setText("Remarks");

        PatientID1.setEditable(false);

        txtSurname1.setEditable(false);

        jLabel41.setText("Firstname");

        txtFirstnam1.setEditable(false);

        txtgendert.setEditable(false);

        jLabel42.setText("Disease");

        jLabel43.setText("Admit Date");

        jLabel44.setText("Room No.");

        jLabel46.setText("Doctor Name");

        txtDoctorName1.setEditable(false);
        txtDoctorName1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDoctorName1ActionPerformed(evt);
            }
        });

        txtRemarks1.setColumns(20);
        txtRemarks1.setRows(5);
        jScrollPane4.setViewportView(txtRemarks1);

        cmbRoomNo.setEditable(true);
        cmbRoomNo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbRoomNoItemStateChanged(evt);
            }
        });
        cmbRoomNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbRoomNoActionPerformed(evt);
            }
        });

        jPanel21.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 51, 255), 2, true)));

        btnSave1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/savecu4.jpg"))); // NOI18N
        btnSave1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSave1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnSave1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSave1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
        );

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel37)
                    .addComponent(jLabel38)
                    .addComponent(jLabel39)
                    .addComponent(jLabel41)
                    .addComponent(jLabel43)
                    .addComponent(jLabel42)
                    .addComponent(jLabel44)
                    .addComponent(jLabel46)
                    .addComponent(jLabel40))
                .addGap(62, 62, 62)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(PatientID1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSurname1, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtgendert)
                    .addComponent(txtFirstnam1)
                    .addComponent(txtDisease1)
                    .addComponent(txtDoctorName1)
                    .addComponent(Admitdate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                    .addComponent(cmbRoomNo, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel37)
                    .addComponent(PatientID1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel38)
                    .addComponent(txtSurname1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel41)
                    .addComponent(txtFirstnam1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel39)
                    .addComponent(txtgendert, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDisease1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel42))
                .addGap(18, 18, 18)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel43)
                    .addComponent(Admitdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel44)
                    .addComponent(cmbRoomNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel46)
                    .addComponent(txtDoctorName1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jLabel40)))
                .addGap(18, 18, 18)
                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel22.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 51, 255), 2, true));

        AdmitPatTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "PatientID", "Surname", "Firstname", "Gender"
            }
        ));
        AdmitPatTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AdmitPatTableMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(AdmitPatTable);

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 411, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 418, Short.MAX_VALUE)
                .addContainerGap())
        );

        SearchPatAdmit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SearchPatAdmitKeyPressed(evt);
            }
        });

        jXLabel1.setText("Search for Patient");

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jXLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(SearchPatAdmit, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jXLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SearchPatAdmit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(111, 111, 111)
                        .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(507, 507, 507)
                        .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, 445, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(234, Short.MAX_VALUE))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 96, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(125, 125, 125))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(76, 76, 76))))
        );

        jTabbedPane2.addTab("Admit to Room", jPanel6);

        jPanel23.setBackground(new java.awt.Color(0, 0, 0));
        jPanel23.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 255), 2, true));

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlImage, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlImage, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel11.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 255), 2, true));

        jLabel77.setText("Remark");

        jTextArea4.setColumns(20);
        jTextArea4.setRows(5);
        jScrollPane10.setViewportView(jTextArea4);

        jTextArea5.setColumns(20);
        jTextArea5.setRows(5);
        jScrollPane11.setViewportView(jTextArea5);

        jLabel78.setText("Paient Card Number");

        jLabel79.setText("Doctors Prescription");

        jLabel80.setText("Surname");

        txtCardNumber1.setEditable(false);

        jLabel81.setText("Firstname");

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/savecu4.jpg"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton6.setForeground(new java.awt.Color(0, 0, 204));
        jButton6.setText("See Medication");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel78)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCardNumber1))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel79)
                            .addComponent(jLabel77)
                            .addComponent(jLabel80, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel81))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtNameOfPatientSurname)
                                .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 341, Short.MAX_VALUE)
                                .addComponent(txtFirstname1))
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton6)))))
                .addGap(28, 28, 28))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCardNumber1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel78))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel80)
                    .addComponent(txtNameOfPatientSurname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel81)
                    .addComponent(txtFirstname1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                            .addComponent(jLabel79)
                            .addGap(8, 8, 8))))
                .addGap(23, 23, 23)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel77)
                        .addGap(11, 11, 11)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );

        jXPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 102, 255), 2, true));

        TableDisplayPatTreatment.setBackground(new java.awt.Color(204, 204, 204));
        TableDisplayPatTreatment.setForeground(new java.awt.Color(255, 255, 255));
        TableDisplayPatTreatment.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "PatientID", "Surname", "Firstname", "Address"
            }
        ));
        TableDisplayPatTreatment.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        TableDisplayPatTreatment.setGridColor(new java.awt.Color(0, 51, 255));
        TableDisplayPatTreatment.setShowGrid(true);
        TableDisplayPatTreatment.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableDisplayPatTreatmentMouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(TableDisplayPatTreatment);

        javax.swing.GroupLayout jXPanel1Layout = new javax.swing.GroupLayout(jXPanel1);
        jXPanel1.setLayout(jXPanel1Layout);
        jXPanel1Layout.setHorizontalGroup(
            jXPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jXPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 502, Short.MAX_VALUE)
                .addContainerGap())
        );
        jXPanel1Layout.setVerticalGroup(
            jXPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 476, Short.MAX_VALUE)
        );

        jPanel44.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 51, 255), 2, true));

        jLabel83.setText("Search By Name or Number");

        SearchGiveTreatment.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SearchGiveTreatmentKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel44Layout = new javax.swing.GroupLayout(jPanel44);
        jPanel44.setLayout(jPanel44Layout);
        jPanel44Layout.setHorizontalGroup(
            jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel44Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel83)
                .addGap(29, 29, 29)
                .addComponent(SearchGiveTreatment, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel44Layout.setVerticalGroup(
            jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel44Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel83)
                    .addComponent(SearchGiveTreatment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel14.setBackground(new java.awt.Color(51, 51, 255));

        jLabel45.setFont(new java.awt.Font("Tempus Sans ITC", 1, 24)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(255, 255, 255));
        jLabel45.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel45.setText("ATTEND TO THE PATIENT");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 1107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 58, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addComponent(jLabel45)
                .addGap(0, 11, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(60, 60, 60)
                                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, 508, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel44, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jXPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jPanel44, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jXPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(53, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("View Medication From Doctor", jPanel5);

        jPanel3.setBackground(new java.awt.Color(0, 0, 255));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 640, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Patient", jPanel2);

        jTabbedPane3.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        jTabbedPane3.setFont(new java.awt.Font("Tempus Sans ITC", 1, 18)); // NOI18N

        jPanel25.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 51, 255), 4, true));

        jPanel38.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 51, 255), 2, true), "Nurse Details"));

        jLabel53.setText("Surname");

        jLabel54.setText("Firstname");

        jLabel55.setText("Address");

        jLabel56.setText("Contact No.");

        jLabel72.setText("Email ID");

        jLabel73.setText("Qualifications");

        txtContactNo1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtContactNo1KeyTyped(evt);
            }
        });

        btnupdatenurse.setText("Update");
        btnupdatenurse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnupdatenurseActionPerformed(evt);
            }
        });

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        javax.swing.GroupLayout jPanel38Layout = new javax.swing.GroupLayout(jPanel38);
        jPanel38.setLayout(jPanel38Layout);
        jPanel38Layout.setHorizontalGroup(
            jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel38Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnupdatenurse, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel38Layout.createSequentialGroup()
                        .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel53)
                            .addComponent(jLabel54)
                            .addComponent(jLabel55)
                            .addComponent(jLabel56)
                            .addComponent(jLabel72)
                            .addComponent(jLabel73))
                        .addGap(55, 55, 55)
                        .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtQualifications1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                                .addComponent(txtEmailID1, javax.swing.GroupLayout.Alignment.LEADING))
                            .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtnurseSurname, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtfristname, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
                                .addComponent(txtContactNo1, javax.swing.GroupLayout.Alignment.LEADING)))
                        .addGap(13, 13, 13)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel38Layout.setVerticalGroup(
            jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel38Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtnurseSurname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel53))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel54)
                    .addComponent(txtfristname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel38Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel38Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jLabel55)))
                .addGap(26, 26, 26)
                .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel56)
                    .addComponent(txtContactNo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel72)
                    .addComponent(txtEmailID1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel73)
                    .addComponent(txtQualifications1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addComponent(btnupdatenurse)
                .addGap(21, 21, 21))
        );

        jPanel39.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 3, true));

        nuRSEpROFILE.setForeground(new java.awt.Color(0, 51, 204));
        nuRSEpROFILE.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Surname", "Firstname", "Email Address", "Contact No"
            }
        ));
        nuRSEpROFILE.setFont(new java.awt.Font("Wide Latin", 0, 10)); // NOI18N
        nuRSEpROFILE.setGridColor(new java.awt.Color(0, 102, 255));
        nuRSEpROFILE.setSelectionBackground(new java.awt.Color(255, 255, 255));
        nuRSEpROFILE.setSelectionForeground(new java.awt.Color(51, 51, 255));
        nuRSEpROFILE.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nuRSEpROFILEMouseClicked(evt);
            }
        });
        jScrollPane13.setViewportView(nuRSEpROFILE);

        javax.swing.GroupLayout jPanel39Layout = new javax.swing.GroupLayout(jPanel39);
        jPanel39.setLayout(jPanel39Layout);
        jPanel39Layout.setHorizontalGroup(
            jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel39Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane13, javax.swing.GroupLayout.DEFAULT_SIZE, 512, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel39Layout.setVerticalGroup(
            jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel39Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane13, javax.swing.GroupLayout.DEFAULT_SIZE, 429, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel40.setBackground(new java.awt.Color(51, 102, 255));

        jLabel75.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel75.setForeground(new java.awt.Color(255, 255, 255));
        jLabel75.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel75.setText("HERE,YOUR PROFILE CAN BE UPDATED ACCURATIVELY");

        javax.swing.GroupLayout jPanel40Layout = new javax.swing.GroupLayout(jPanel40);
        jPanel40.setLayout(jPanel40Layout);
        jPanel40Layout.setHorizontalGroup(
            jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel40Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel75, javax.swing.GroupLayout.DEFAULT_SIZE, 1164, Short.MAX_VALUE))
        );
        jPanel40Layout.setVerticalGroup(
            jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel40Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel75, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel45.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 51, 255), 2, true));

        jLabel84.setText("Search By Name or Number");

        SearchGiveTreatment1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SearchGiveTreatment1KeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel45Layout = new javax.swing.GroupLayout(jPanel45);
        jPanel45.setLayout(jPanel45Layout);
        jPanel45Layout.setHorizontalGroup(
            jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel45Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel84)
                .addGap(29, 29, 29)
                .addComponent(SearchGiveTreatment1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel45Layout.setVerticalGroup(
            jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel45Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel84)
                    .addComponent(SearchGiveTreatment1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel40, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addGap(93, 93, 93)
                .addComponent(jPanel38, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addComponent(jPanel39, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel25Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel45, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(268, 268, 268))
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addComponent(jPanel40, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addComponent(jPanel38, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jPanel45, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel39, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(121, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("Manage Profile", jPanel25);

        jPanel34.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 51, 255), 4, true));

        jPanel36.setBackground(new java.awt.Color(51, 102, 255));

        jLabel47.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(255, 255, 255));
        jLabel47.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel47.setText("Enter Details Correctly to change your paswword");

        javax.swing.GroupLayout jPanel36Layout = new javax.swing.GroupLayout(jPanel36);
        jPanel36.setLayout(jPanel36Layout);
        jPanel36Layout.setHorizontalGroup(
            jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel36Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel47, javax.swing.GroupLayout.DEFAULT_SIZE, 1154, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel36Layout.setVerticalGroup(
            jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel36Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel37.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 255), 2, true));

        jLabel48.setFont(new java.awt.Font("Palatino Linotype", 1, 12)); // NOI18N
        jLabel48.setText("Confirm Password");

        jLabel49.setFont(new java.awt.Font("Palatino Linotype", 1, 12)); // NOI18N
        jLabel49.setText("Old Password");

        jButton5.setText("Change Password");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel50.setFont(new java.awt.Font("Palatino Linotype", 1, 12)); // NOI18N
        jLabel50.setText("User Name");

        jLabel51.setFont(new java.awt.Font("Palatino Linotype", 1, 12)); // NOI18N
        jLabel51.setText("New Password");

        OldPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                OldPasswordKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel37Layout = new javax.swing.GroupLayout(jPanel37);
        jPanel37.setLayout(jPanel37Layout);
        jPanel37Layout.setHorizontalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel37Layout.createSequentialGroup()
                .addGap(119, 119, 119)
                .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton5)
                    .addGroup(jPanel37Layout.createSequentialGroup()
                        .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel49)
                            .addComponent(jLabel50))
                        .addGap(78, 78, 78)
                        .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(UserPasswordChange)
                            .addComponent(OldPassword)
                            .addComponent(CofirmPassword)
                            .addComponent(NewPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE))))
                .addContainerGap(94, Short.MAX_VALUE))
        );
        jPanel37Layout.setVerticalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel37Layout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(UserPasswordChange, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(OldPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel51)
                    .addComponent(NewPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CofirmPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addComponent(jButton5)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel34Layout = new javax.swing.GroupLayout(jPanel34);
        jPanel34.setLayout(jPanel34Layout);
        jPanel34Layout.setHorizontalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel36, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel34Layout.createSequentialGroup()
                .addGap(189, 189, 189)
                .addComponent(jPanel37, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel34Layout.setVerticalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel34Layout.createSequentialGroup()
                .addComponent(jPanel36, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(143, 143, 143)
                .addComponent(jPanel37, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(201, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("Change Password", jPanel34);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane3, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane3, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        jTabbedPane1.addTab("Nurse", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1352, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void NewPaTgENDER1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NewPaTgENDER1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NewPaTgENDER1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        new CameraFrame().setVisible(true);
        
                   
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jtbdischrgePatientMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtbdischrgePatientMouseClicked
try{
            conn = DbConnection.getconnection();
            int row= jtbdischrgePatient.getSelectedRow();
            String table_click= jtbdischrgePatient.getModel().getValueAt(row, 0).toString();
            String sql= "select * from patienttable where Patientid = '" + table_click + "'";
            pst=conn.prepareStatement(sql);
            rst=  pst.executeQuery();
            if(rst.next()){
                
                String add1=rst.getString("Patientid");
                PatientIDy.setText(add1);
                String add2=rst.getString("Surname");
                txtPatientName.setText(add2);
                String add3=rst.getString("Firstname");
                txtFirstname.setText(add3);
                String add5=rst.getString("Gender");
                txtGender.setText(add5);
                String add6=rst.getString("Occupation");
                txtOccupation.setText(add6);
                String add7=rst.getString("RegistrationDate");
                NewPatAdmitDate.setText(add7);
                
                
             
            }
        }catch(Exception ex){
            JOptionPane.showMessageDialog(this,ex);
        }        
    }//GEN-LAST:event_jtbdischrgePatientMouseClicked

    private void cmbRoomNoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbRoomNoItemStateChanged

    }//GEN-LAST:event_cmbRoomNoItemStateChanged

    private void cmbRoomNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbRoomNoActionPerformed

    }//GEN-LAST:event_cmbRoomNoActionPerformed

    private void btnSave1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSave1ActionPerformed
        try{
            conn=DbConnection.getconnection();
            
            if (txtSurname1.getText().equals("")) {
                JOptionPane.showMessageDialog( this, "Please retrieve Patient Name","Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (txtFirstnam1.getText().equals("")) {
                JOptionPane.showMessageDialog( this, "Please retrieve Gender","Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (txtgendert.getText().equals("")) {
                JOptionPane.showMessageDialog( this, "Please retrieve blood group","Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (txtDisease1.getText().equals("")) {
                JOptionPane.showMessageDialog( this, "Please enter disease","Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            if (txtRemarks1.getText().equals("")) {
                JOptionPane.showMessageDialog( this, "Please select remarks.","Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

           
            
            Statement stmt1;
            stmt1= conn.createStatement();
            String sql2="Select RoomNo from room where RoomNo= '" + cmbRoomNo.getSelectedItem()+ "' and RoomStatus='Booked'";
            rst=stmt1.executeQuery(sql2);
            if(rst.next()){
                JOptionPane.showMessageDialog( this, "Room is already booked","Error", JOptionPane.ERROR_MESSAGE);
                cmbRoomNo.setSelectedItem("");
                cmbRoomNo.requestDefaultFocus();
                return;
            }
            
String d1 = ((JTextField)Admitdate.getDateEditor().getUiComponent()).getText();
            String sql= "insert into patientadmittable (PatientID,Surname,Firstname,Gender,Disease,AdmitDate,RoomNo,DoctorName,Remarks)values(?,?,?,?,?,?,?,?,?)";
       

            pst=conn.prepareStatement(sql);
            System.out.print(sql);
             pst.setString(1, PatientID1.getText());
            pst.setString(2, txtSurname1.getText());
            pst.setString(3, txtFirstnam1.getText());
             pst.setString(4, txtgendert.getText());
              
               pst.setString(5, txtDisease1.getText());
               pst.setString(6, d1);
               pst.setString(7,  String.valueOf(cmbRoomNo.getSelectedItem()));
                pst.setString(8, txtDoctorName1.getText());
                pst.setString(9, txtRemarks1.getText());
                
                
                pst.executeUpdate();
            
            String sql3= "update room set RoomStatus='Booked' where RoomNo='" + cmbRoomNo.getSelectedItem() + "'";
            pst=conn.prepareStatement(sql3);
            pst.execute();
            
                 
            JOptionPane.showMessageDialog(this,"Successfully admitted","Patient",JOptionPane.INFORMATION_MESSAGE);
            btnSavesd.setEnabled(false);

        }catch(HeadlessException | SQLException ex){
            JOptionPane.showMessageDialog(this,ex);
        }
    }//GEN-LAST:event_btnSave1ActionPerformed

    private void AdmitPatTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AdmitPatTableMouseClicked
        try{
            conn=DbConnection.getconnection();
            int row= AdmitPatTable.getSelectedRow();
            String table_click= AdmitPatTable.getModel().getValueAt(row, 0).toString();
            String sql= "select * from 	patienttable where Patientid = '" + table_click + "'";
            pst=conn.prepareStatement(sql);
            rst=  pst.executeQuery();
            if(rst.next()){
                String add1=rst.getString("Patientid");
                PatientID1.setText(add1);
                String add2=rst.getString("Surname");
                txtSurname1.setText(add2);
                String add3=rst.getString("Firstname");
                txtFirstnam1.setText(add3);
                String add4=rst.getString("Gender");
                txtgendert.setText(add2);
                String add5=rst.getString("AssignDoctor");
                txtDoctorName1.setText(add5);
            }
        }catch(Exception ex){
            JOptionPane.showMessageDialog(this,ex);
        }
    }//GEN-LAST:event_AdmitPatTableMouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        try {
            // TODO add your handling code here:

            validateNewPatForm();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(NurseDept.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void NewpAtNo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NewpAtNo1ActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_NewpAtNo1ActionPerformed

    private void OldPasswordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_OldPasswordKeyPressed
        if (evt.getKeyCode()==KeyEvent.VK_ENTER){
            try{
                String Newpass=String.valueOf(CofirmPassword.getPassword());
                String ConfPass=String.valueOf(OldPassword.getPassword());
                String OldPass=String.valueOf(UserPasswordChange.getPassword());
                String uName=NewPassword.getText();
                if (uName.equals("")) {

                    JOptionPane.showMessageDialog( this, "Please enter a username",
                        "Error", JOptionPane.ERROR_MESSAGE);
                    return;

                } else if (OldPass.equals("")) {

                    JOptionPane.showMessageDialog( this, "Please enter a old password",
                        "Error", JOptionPane.ERROR_MESSAGE);
                    return;

                } else if (Newpass.equals("")) {

                    JOptionPane.showMessageDialog( this, "Please enter a new password",
                        "Error", JOptionPane.ERROR_MESSAGE);
                    return;

                } else if (ConfPass.equals("")) {

                    JOptionPane.showMessageDialog( this, "Please enter a confirmed password",
                        "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                else if (Newpass.length()< 5) {

                    JOptionPane.showMessageDialog(this,
                        "The New Password Should be of Atleast 5 Characters",
                        "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                else if ((Newpass).equals(OldPass)) {

                    JOptionPane.showMessageDialog(this,
                        "Password is same..Re-enter new password","Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                else if (!(Newpass).equals(ConfPass)) {

                    JOptionPane.showMessageDialog(this,
                        "New Password doesn't match with Confirmed Password",
                        "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                conn=DbConnection.getconnection();
                String sql= "select Username,User_Password from users where Username='" + NewPassword.getText() + "' and User_Password= '" + UserPasswordChange.getText() + "'";
                pst=conn.prepareStatement(sql);
                rst= pst.executeQuery();
                while(rst.next())
                {
                    String usrname = rst.getString("username").trim();
                    String passwd = rst.getString("user_password").trim();
                    if(uName.equals(usrname) && OldPass.equals(passwd))
                    {
                        conn=DbConnection.getconnection();;
                        String sql1= "update users set User_password= '" + Newpass + "' where Username= '" + uName + "' and User_password = '" + OldPass + "'";
                        Statement stmt = conn.createStatement();
                        stmt.execute(sql1.toString());
                        stmt.close();
                        JOptionPane.showMessageDialog(this,"Password Successfully Changed");
                        this.dispose();
                        return;
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(this,"invalid user name or password","Error", JOptionPane.ERROR_MESSAGE);
                        NewPassword.setText("");
                        UserPasswordChange.setText("");
                        CofirmPassword.setText("");
                        OldPassword.setText("");
                        return;
                    }
                }
            }catch(SQLException | HeadlessException ex){
                JOptionPane.showMessageDialog(this,ex);
            }

        }
    }//GEN-LAST:event_OldPasswordKeyPressed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:

        try{

            String Newpass=String.valueOf(NewPassword.getPassword());
            String ConfPass=String.valueOf(CofirmPassword.getPassword());
            String OldPass=String.valueOf(OldPassword.getPassword());
            String uName=UserPasswordChange.getText();
            if (uName.equals("")) {

                JOptionPane.showMessageDialog( this, "Please enter a username",
                    "Error", JOptionPane.ERROR_MESSAGE);
                return;

            } else if (OldPass.equals("")) {

                JOptionPane.showMessageDialog( this, "Please enter a old password",
                    "Error", JOptionPane.ERROR_MESSAGE);
                return;

            } else if (Newpass.equals("")) {

                JOptionPane.showMessageDialog( this, "Please enter a new password",
                    "Error", JOptionPane.ERROR_MESSAGE);
                return;

            } else if (ConfPass.equals("")) {

                JOptionPane.showMessageDialog( this, "Please enter a confirmed password",
                    "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            else if (Newpass.length()< 9) {

                JOptionPane.showMessageDialog(this,
                    "The New Password Should be of Atleast 8 Characters",
                    "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            else if ((Newpass).equals(OldPass)) {

                JOptionPane.showMessageDialog(this,
                    "Password is same..Re-enter new password","Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            else if (!(Newpass).equals(ConfPass)) {

                JOptionPane.showMessageDialog(this,
                    "New Password doesn't match with Confirmed Password",
                    "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            conn=DbConnection.getconnection();
            String sql= "select Username,Password from loginusertable where Username='" + UserPasswordChange.getText() + "' and Password= '" + OldPassword.getText() + "'";
            pst=conn.prepareStatement(sql);
            rst= pst.executeQuery();
            while(rst.next())
            {
                String usrname = rst.getString("username").trim();
                String passwd = rst.getString("password").trim();
                if(uName.equals(usrname) && OldPass.equals(passwd))
                {
                    conn=DbConnection.getconnection();
                    String sql1= "update loginusertable set password= '" + Newpass + "' where username= '" + uName + "' and password = '" + OldPass + "'";
                    Statement stmt = conn.createStatement();
                    stmt.execute(sql1.toString());
                    stmt.close();
                    JOptionPane.showMessageDialog(this,"Password Successfully Changed");
                    this.dispose();
                    return;
                }
                else
                {
                    JOptionPane.showMessageDialog(this,"invalid user name or password","Error", JOptionPane.ERROR_MESSAGE);
                    UserPasswordChange.setText("");
                    OldPassword.setText("");
                    CofirmPassword.setText("");
                    NewPassword.setText("");
                    return;
                }
            }
        }catch(SQLException | HeadlessException ex){
            JOptionPane.showMessageDialog(this,ex);
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void btnupdatenurseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnupdatenurseActionPerformed
        try{
            conn = DbConnection.getconnection();
            if (txtnurseSurname.getText().equals("")) {
                JOptionPane.showMessageDialog( this, "Please retreive Nurse Surname","Error", JOptionPane.ERROR_MESSAGE);
                return;

            }
            if (txtfristname.getText().equals("")) {
                JOptionPane.showMessageDialog( this, "Please retrieve Nurse Firstname","Error", JOptionPane.ERROR_MESSAGE);
                return;

            }
            if (jTextArea1.getText().equals("")) {
                JOptionPane.showMessageDialog( this, "Please enter Address","Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (txtContactNo1.getText().equals("")) {
                JOptionPane.showMessageDialog( this, "Please retrieve Nurse PhoneNo","Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (txtEmailID1.getText().equals("")) {
                JOptionPane.showMessageDialog( this, "Please retrive Nurse mail.","Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            
  

String sql= " update nurse set  Surname='"+ txtnurseSurname.getText() + "',Firstname='"+ txtfristname.getText() + "',Address='"+ 
        jTextArea1.getText() + "',ContactNo='"+ txtContactNo1.getText()+ "',EmmailAddres='" + txtEmailID1.getText() + 
        "',Qualifications='"+ txtRemarks.getText() + "' where NurseID= " + null + "";
            pst=conn.prepareStatement(sql);
            pst.execute();
       
            pst=conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(this,"Successfully updated"," Record",JOptionPane.INFORMATION_MESSAGE);
            btnSavesd.setEnabled(false);

        }catch(HeadlessException | SQLException ex){
            JOptionPane.showMessageDialog(this,ex);
        }
    }//GEN-LAST:event_btnupdatenurseActionPerformed

    private void txtContactNo1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtContactNo1KeyTyped
        char c=evt.getKeyChar();
        if (!(Character.isDigit(c)|| (c== KeyEvent.VK_BACK_SPACE)||(c==KeyEvent.VK_DELETE))){
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_txtContactNo1KeyTyped

    private void SearchPatAdmitKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SearchPatAdmitKeyPressed
        // TODO add your handling code here:
         String sql= " Select Patientid,Surname,Firstname,Gender from patienttable where Surname like ?";
        try{
            conn=DbConnection.getconnection();
            pst= conn.prepareCall(sql);
            pst.setString(1, SearchPatAdmit.getText() + "%");
            rst= pst.executeQuery();
            AdmitPatTable.setModel(DbUtils.resultSetToTableModel(rst));

        }
        catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}

        
    }//GEN-LAST:event_SearchPatAdmitKeyPressed

    private void SearchPatDischargeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SearchPatDischargeKeyPressed
        // TODO add your handling code here:
        //String sql= " Select Patientid,Surname,Firstname,Gender,Occupation from patienttable";
        String sql="select PatientID as 'Patient ID', Surname as 'Patient Name',Firstname as 'Firstname',Gender as 'Gender',RegistrationDate from patienttable where Surname like ?";
        try{
            conn=DbConnection.getconnection();
            
            pst= conn.prepareCall(sql);
            pst.setString(1, SearchPatDischarge.getText() + "%");
            rst= pst.executeQuery();
            jtbdischrgePatient.setModel(DbUtils.resultSetToTableModel(rst));

        }
        catch(Exception ex)
        {JOptionPane.showMessageDialog(null, ex);}

        
                                     
        
    }//GEN-LAST:event_SearchPatDischargeKeyPressed

    private void txtDoctorName1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDoctorName1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDoctorName1ActionPerformed

    private void SearchGiveTreatmentKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SearchGiveTreatmentKeyPressed
        // TODO add your handling code here:
        String sql= " Select Patientid,Surname,	Firstname,Gender from patienttable where Patientid  like ? ";
        try{
            conn=DbConnection.getconnection();
            pst= conn.prepareCall(sql);
            pst.setString(1, SearchGiveTreatment.getText() + "%");
            rst= pst.executeQuery();
            TableDisplayPatTreatment.setModel(DbUtils.resultSetToTableModel(rst));
            
     
        }
        catch(Exception ex)
        {JOptionPane.showMessageDialog(null, ex);}

    }//GEN-LAST:event_SearchGiveTreatmentKeyPressed

    private void jxtxtnewPatregKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jxtxtnewPatregKeyPressed
        // TODO add your handling code here:
        String sql= " Select Patientid,Surname,Firstname,Address,RegistrationDate,AssignDoctor,StateOfOrigin from patienttable where Patientid like ? ";
        try{
            conn=DbConnection.getconnection();
            pst= conn.prepareCall(sql);
            pst.setString(1, jxtxtnewPatreg.getText() + "%");
            rst= pst.executeQuery();
            jTablePatReg.setModel(DbUtils.resultSetToTableModel(rst));

        }
        catch(Exception ex)
        {JOptionPane.showMessageDialog(null, ex);}
    }//GEN-LAST:event_jxtxtnewPatregKeyPressed

    private void SearchGiveTreatment1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SearchGiveTreatment1KeyPressed
        // TODO add your handling code here:
        String sql= " Select * from newuserregistration where Surname like ? ";
        try{
            conn=DbConnection.getconnection();
            pst= conn.prepareCall(sql);
            pst.setString(1, SearchGiveTreatment1.getText() + "%");
            rst= pst.executeQuery();
            nuRSEpROFILE.setModel(DbUtils.resultSetToTableModel(rst));

        }
        catch(Exception ex)
        {JOptionPane.showMessageDialog(null, ex);}
    }//GEN-LAST:event_SearchGiveTreatment1KeyPressed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        
        try{
            conn= DbConnection.getconnection();
            if (txtCardNumber1.getText().equals("")) {
                JOptionPane.showMessageDialog( this, "Please retrieve Patient Card No","Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (txtNameOfPatientSurname.getText().equals("")) {
                JOptionPane.showMessageDialog( this, "Please retreive Patient surname","Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (txtFirstname1.getText().equals("")) {
                JOptionPane.showMessageDialog( this, "Please retreive Patient Firstname","Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (jTextArea5.getText().equals("")) {
                JOptionPane.showMessageDialog( this, "Please enter charges","Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            

            String sql= "insert into patienttreatmenttable (PatientID,Surname,Firstname,Medication,Remarks)values(?,?,?,?,?)";
                    
            pst=conn.prepareStatement(sql);
            System.out.print(sql);
             pst.setString(1, txtCardNumber1.getText());
            pst.setString(2, txtNameOfPatientSurname.getText());
            pst.setString(3, txtFirstname1.getText());
             pst.setString(4, jTextArea5.getText());
              
               pst.setString(5, jTextArea4.getText());
              
                
                pst.executeUpdate();
                 

            JOptionPane.showMessageDialog(this,"Successfully saved","Treatment Record",JOptionPane.INFORMATION_MESSAGE);
           
            
        }catch(HeadlessException | SQLException ex){
            JOptionPane.showMessageDialog(this,ex);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void NewPaToCcupation1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NewPaToCcupation1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NewPaToCcupation1ActionPerformed

    private void btnSavesdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSavesdActionPerformed
        try{
            conn=DbConnection.getconnection();
            if (PatientIDy.getText().equals("")) {
                JOptionPane.showMessageDialog( this, "Please retrieve Patient ID","Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (txtPatientName.getText().equals("")) {
                JOptionPane.showMessageDialog( this, "Please retrieve Patient Name","Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (txtFirstname.getText().equals("")) {
                JOptionPane.showMessageDialog( this, "Please retrieve Gender","Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (txtGender.getText().equals("")) {
                JOptionPane.showMessageDialog( this, "Please retrieve blood group","Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (txtOccupation.getText().equals("")) {
                JOptionPane.showMessageDialog( this, "Please retrieve disease","Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (txtRoomNo.getSelectedItem().equals("")) {
                JOptionPane.showMessageDialog( this, "Please retrieve doctor id","Error", JOptionPane.ERROR_MESSAGE);
                return;

            }
            if (txtDoctorName.getText().equals("")) {
                JOptionPane.showMessageDialog( this, "Please enter retrieve doctor name","Error", JOptionPane.ERROR_MESSAGE);
                return;

            }

            Statement stmt;
            stmt= conn.createStatement();
            System.out.print("error occured........");
            String sql1="Select PatientID from patientdischargetable where PatientID= " + PatientIDy.getText() + "";
            rst=stmt.executeQuery(sql1);
            if(rst.next()){

                JOptionPane.showMessageDialog( this, "Record already exists","Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String d1  = ((JTextField)datedischarge.getDateEditor().getUiComponent()).getText();

            String sql= "insert into patientdischargetable(PatientID,Surname,Firstname,Gender,Occupation,AdmitDate,RoomNo,DoctorName,DischargeDate,Remarks) values(?,?,?,?,?,?,?,?,?,?)";
            pst=conn.prepareStatement(sql);

            pst.setString(1, PatientIDy.getText());
            pst.setString(2, txtPatientName.getText());
            pst.setString(3, txtFirstname.getText());
            pst.setString(4, txtGender.getText());
            pst.setString(5, txtOccupation.getText());
            pst.setString(6, NewPatAdmitDate.getText());
            pst.setString(7, String.valueOf(txtRoomNo.getSelectedItem()));
            pst.setString(8, txtDoctorName.getText());
            pst.setString(9, d1);
            pst.setString(10, txtRemarks.getText());

            pst.executeUpdate();

            String sql3= "update room set RoomStatus='Vacant' where RoomNo='" + txtRoomNo.getSelectedItem() + "'";
            pst=conn.prepareStatement(sql3);
            pst.execute();
            JOptionPane.showMessageDialog(this,"Successfully discharged","Patient",JOptionPane.INFORMATION_MESSAGE);
            btnSavesd.setEnabled(false);

        }catch(HeadlessException | SQLException ex){

            JOptionPane.showMessageDialog(this,ex);
        }
    }//GEN-LAST:event_btnSavesdActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        MessageFormat header = new MessageFormat("PATIENT'S RECORD ");
        MessageFormat footer = new MessageFormat("Page {0,number,integer}");
        try {
            jTablePatReg.print(JTable.PrintMode.FIT_WIDTH, header, footer, true, null, true, null);
            //table.print(JTable.PrintMode.FIT_WIDTH, header, null);
           // jTable1_pumpdata.print(JTable.PrintMode.FIT_WIDTH, header, footer);
        
        } catch (java.awt.print.PrinterException e) {
            System.err.format("Cannot print %s%n", e.getMessage());
        }  finally {
try{
  rst.close();
      pst.close();
     //conn.close();
  }
  catch(Exception e) {
                   }
      } 
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        String ddd = txtCardNumber1.getText();
              String sql = "select Medication from parienttreatmenttable where PatientID = '" +ddd+"'";
        try {
            pst = conn.prepareStatement(sql);
            rst = pst.executeQuery();
            if(rst.next()){
                String add9 = rst.getString("PatientID");

                jTextArea5.setText(add9);
            } } 
        catch (SQLException ex) {
            Logger.getLogger(NurseDept.class.getName()).log(Level.SEVERE, null, ex);
        }
            

              
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jTabbedPane1MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseMoved
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jTabbedPane1MouseMoved

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        
        new LoginManager().setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void nuRSEpROFILEMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nuRSEpROFILEMouseClicked
        // TODO add your handling code here:
            
        int row = nuRSEpROFILE.getSelectedRow();
        String Table_Click = ( nuRSEpROFILE.getModel().getValueAt(row, 0).toString());
        try{
            String sql = "select * from newuserregistration where Surname = '"+Table_Click+"'";
            conn= DbConnection.getconnection();
            pst = conn.prepareStatement(sql);
            rst = pst.executeQuery();
            if(rst.next()){
                String add1 = rst.getString("Surname");

               txtnurseSurname.setText(add1);
                String add2 = rst.getString("Firstname");
                txtfristname.setText(add2);
                String add3 = rst.getString("PhoneNo");
                txtContactNo1.setText(add3);
                String add4 = rst.getString("EmailAddress");
                txtEmailID1.setText(add4);
                

               

            }}
            catch(Exception e){
                e.printStackTrace();

            }
    }//GEN-LAST:event_nuRSEpROFILEMouseClicked

    private void TableDisplayPatTreatmentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableDisplayPatTreatmentMouseClicked
        // TODO add your handling code here:
         int row = TableDisplayPatTreatment.getSelectedRow();
        String Table_Click = ( TableDisplayPatTreatment.getModel().getValueAt(row, 0).toString());
        try{
            String sql = "select * from patienttable where Patientid = '"+Table_Click+"'";
            pst = conn.prepareStatement(sql);
            rst = pst.executeQuery();
            if(rst.next()){
                String add1 = rst.getString("Patientid");

                txtCardNumber1.setText(add1);
                String add2 = rst.getString("Surname");
                txtNameOfPatientSurname.setText(add2);
                String add3 = rst.getString("Firstname");
                txtFirstname1.setText(add3);

                byte[] imagedata = rst.getBytes("ImagePatient");
                InputStream inStream =rst.getBinaryStream("ImagePatient");
                BufferedImage originalImage = ImageIO.read(inStream);
                int type = originalImage.getType() == 0? BufferedImage.TYPE_INT_ARGB : originalImage.getType();
                BufferedImage resizeImageJpg = resizeImage(originalImage, type);
                ImageIcon imageIcon = new ImageIcon(resizeImageJpg);

                jlImage.setIcon(imageIcon);

            }}
            catch(Exception e){
                e.printStackTrace();

            }
    }//GEN-LAST:event_TableDisplayPatTreatmentMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(NurseDept.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NurseDept.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NurseDept.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NurseDept.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NurseDept().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable AdmitPatTable;
    private com.toedter.calendar.JDateChooser Admitdate;
    private javax.swing.JPasswordField CofirmPassword;
    private javax.swing.JComboBox NewPaTgENDER1;
    public javax.swing.JTextField NewPaToCcupation1;
    public javax.swing.JTextField NewPaatAge1;
    private javax.swing.JPasswordField NewPassword;
    public javax.swing.JTextArea NewPatAddress1;
    private javax.swing.JTextField NewPatAdmitDate;
    public javax.swing.JTextField NewPatCity1;
    private com.toedter.calendar.JDateChooser NewPatDate1;
    public javax.swing.JTextField NewPatDoctorAssign1;
    public javax.swing.JTextField NewPatFirstname1;
    public org.jdesktop.swingx.JXFormattedTextField NewPatPhone1;
    private javax.swing.JComboBox NewPatSate1;
    private javax.swing.JComboBox NewPatStatus1;
    public javax.swing.JTextField NewPatSurname1;
    public javax.swing.JTextField NewpAtNo1;
    private javax.swing.JPasswordField OldPassword;
    public javax.swing.JTextField PatientID1;
    public javax.swing.JTextField PatientIDy;
    private javax.swing.JTextField SearchGiveTreatment;
    private javax.swing.JTextField SearchGiveTreatment1;
    private org.jdesktop.swingx.JXSearchField SearchPatAdmit;
    private org.jdesktop.swingx.JXSearchField SearchPatDischarge;
    private org.jdesktop.swingx.JXTable TableDisplayPatTreatment;
    private javax.swing.JPasswordField UserPasswordChange;
    public javax.swing.JButton btnSave1;
    public javax.swing.JButton btnSavesd;
    public javax.swing.JButton btnupdatenurse;
    public javax.swing.JComboBox cmbRoomNo;
    private com.toedter.calendar.JDateChooser datedischarge;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel34;
    private javax.swing.JPanel jPanel36;
    private javax.swing.JPanel jPanel37;
    private javax.swing.JPanel jPanel38;
    private javax.swing.JPanel jPanel39;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel40;
    private javax.swing.JPanel jPanel44;
    private javax.swing.JPanel jPanel45;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTabbedPane jTabbedPane3;
    private org.jdesktop.swingx.JXTable jTablePatReg;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea4;
    private javax.swing.JTextArea jTextArea5;
    private org.jdesktop.swingx.JXLabel jXLabel1;
    private org.jdesktop.swingx.JXPanel jXPanel1;
    private javax.swing.JLabel jlImage;
    public javax.swing.JLabel jlabelpictureDisplay;
    private javax.swing.JTable jtbdischrgePatient;
    private org.jdesktop.swingx.JXSearchField jxtxtnewPatreg;
    private org.jdesktop.swingx.JXTable nuRSEpROFILE;
    public javax.swing.JPanel panelwebcam;
    private javax.swing.JComboBox txtBgROUP;
    private javax.swing.JTextField txtCardNumber1;
    public javax.swing.JTextField txtContactNo1;
    public javax.swing.JTextField txtDisease1;
    private javax.swing.JTextField txtDoctorName;
    public javax.swing.JTextField txtDoctorName1;
    public javax.swing.JTextField txtEmailID1;
    public javax.swing.JTextField txtFirstnam1;
    public javax.swing.JTextField txtFirstname;
    private javax.swing.JTextField txtFirstname1;
    public javax.swing.JTextField txtGender;
    private javax.swing.JTextField txtHeight;
    private javax.swing.JTextField txtNameOfPatientSurname;
    public javax.swing.JTextField txtOccupation;
    public javax.swing.JTextField txtPatientName;
    public javax.swing.JTextField txtQualifications1;
    public javax.swing.JTextArea txtRemarks;
    public javax.swing.JTextArea txtRemarks1;
    private javax.swing.JComboBox txtRoomNo;
    public javax.swing.JTextField txtSurname1;
    public javax.swing.JTextField txtfristname;
    public javax.swing.JTextField txtgendert;
    public javax.swing.JTextField txtnurseSurname;
    private javax.swing.JTextField txtxBP;
    private javax.swing.JTextField txtxPurse;
    private javax.swing.JTextField txtxTemperature;
    private javax.swing.JTextField txxtWeight;
    // End of variables declaration//GEN-END:variables
}
