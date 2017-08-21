
import Custom.ReportClientInovice;

import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import javax.swing.JOptionPane;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;
import net.proteanit.sql.DbUtils;
import JBDC.DbConnection;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.JTextField;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Raj
 */
public class Bill_Room extends javax.swing.JFrame {
Connection conn=null;
ResultSet rst=null;
PreparedStatement pst=null;

private PrinterJob printerJob = null;
    private PageFormat pageFormat = null;
    private String report = null;
    private String Title = "                     HOSPITAL MANAGER                 ";
    private String Title2 = "                    BILLING DEPARTMENT                 ";
    private String Title3 = "                   Patient Billing Form  Receipt               ";
    private String titleLine = "        _________________________________________________________________________                 ";
    private String titleLine2 = "       _________________________________________________________________________                 ";
    /**
    /**
     * Creates new form Bill_Room
     */
    public Bill_Room() {
        initComponents();
       this.setExtendedState(MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        btnUpdate.setEnabled(false);
        updateBill();
        
    }
    public void printMethod(){
   // String exam= printExam.getSelectedItem().toString();
    //String date= printDate.getSelectedItem().toString();
    
        report = "\n" + Title + "\n\n" +Title2+ "\n"+titleLine + "\n\n"
                +Title3+ "\n" +titleLine2 +"\n\n "
            + "Patient's Surname: '"+ txtPatientName.getText() + "'"
                + "\n\n First Name:'" + FirstnameBil.getText() + "'"
                + "\n\n Patient Card No:'" + PatientID.getText() + "'\n\n"
                + " Gender: '" +txtGender.getText() + "'"
                + "\n\n"+ titleLine 
                + "\n\n Admit Date: "+ txtAdmitDate.getText()
                + "\n"+ titleLine2
         + "\n\n Doctor Name: "+ txtDoctorName.getText()
                + "\n"+ titleLine2
          + "\n\n Room Charges: "+ txtRoomCharges.getText()
                + "\n"+ titleLine2
                 + "\n\n Medicine Charges: "+ txtMedicneCharges.getText()
               + "\n"+ titleLine2
                        + "\n\n Billing Date: "+ txtBillingDate.getDate()
               + "\n"+ titleLine2
                 + "\n\n No of Days:  "+ txtNoOfDays.getText()
                + "\n"+ titleLine2
          
                + "\n\n Bill No: "+ BillNo.getText()
                + "\n"+ titleLine2
                + "\n\n Total Charges: "+ txtTotalCharges.getText()
                + "\n"+ titleLine2
                + "\n\n Total Paid: "+ txtTotalPaid.getText()
                + "\n"+ titleLine2
                ;
        
        printerJob = PrinterJob.getPrinterJob();
        pageFormat = printerJob.pageDialog(printerJob.defaultPage());
        try {
            printerJob.setPageable(new ReportClientInovice(report, pageFormat));
        } catch (IOException ex) {
            Logger.getLogger(Bill_Room.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (printerJob.printDialog()) {
            try {
                printerJob.print();
            } catch (PrinterException ex) {
                Logger.getLogger(Bill_Room.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    public void updateBill(){

String sql= " Select Surname,Firstname,AdmitDate,RoomNo,RoomCharges,MedicineCharges,PaymentModeDetails,TotalCharges,BillingDate,BiilNo,Totalpaid from billing ";
        try{
            conn=DbConnection.getconnection();
            pst= conn.prepareStatement(sql);
            
            rst= pst.executeQuery();
            jTable1.setModel(DbUtils.resultSetToTableModel(rst));
            

        }
        catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}
}
    
    private void restFields() {
        DefaultFormatterFactory formateUnit = new DefaultFormatterFactory(
                new NumberFormatter(new DecimalFormat("#0.00")));
        DefaultFormatterFactory formateQuantity = new DefaultFormatterFactory(new NumberFormatter(new DecimalFormat("#0")));
       // typeNameTextField.setText("");
        //quantityFormattedTextField.setText("");
        //unitPriceFormattedTextField.setText("");
       // unitPriceFormattedTextField.setFormatterFactory(formateUnit);
       // quantityFormattedTextField.setFormatterFactory(formateQuantity);
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        PatientID = new javax.swing.JTextField();
        txtPatientName = new javax.swing.JTextField();
        txtAdmitDate = new javax.swing.JFormattedTextField();
        jLabel29 = new javax.swing.JLabel();
        txtGender = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        txtDoctorName = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        FirstnameBil = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jPanel1 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtTotalPaid = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtNoOfDays = new javax.swing.JTextField();
        txtBillingDate = new com.toedter.calendar.JDateChooser();
        jLabel15 = new javax.swing.JLabel();
        BillNo = new javax.swing.JTextField();
        txtRoomCharges = new javax.swing.JTextField();
        txtMedicneCharges = new javax.swing.JTextField();
        txtPaymentModeDetails = new javax.swing.JTextField();
        txtTotalCharges = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        btnSave = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TableDisplayPatBill = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        SearchBill = new org.jdesktop.swingx.JXSearchField();
        jXLabel1 = new org.jdesktop.swingx.JXLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Billing");
        setBackground(new java.awt.Color(255, 255, 255));
        setResizable(false);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 51, 255), 2, true), "Patient Info"));

        jLabel5.setText("Patient ID");

        jLabel6.setText("Surname");

        PatientID.setEditable(false);

        txtPatientName.setEditable(false);

        txtAdmitDate.setEditable(false);
        txtAdmitDate.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat(""))));

        jLabel29.setText("Gender");

        txtGender.setEditable(false);
        txtGender.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGenderActionPerformed(evt);
            }
        });

        jLabel32.setText("Admit Date");

        jLabel35.setText("Doctor Name");

        txtDoctorName.setEditable(false);

        jLabel7.setText("Discharge Date");

        FirstnameBil.setEnabled(false);
        FirstnameBil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FirstnameBilActionPerformed(evt);
            }
        });

        jLabel16.setText("Firstname");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel29)
                    .addComponent(jLabel32)
                    .addComponent(jLabel35)
                    .addComponent(jLabel7)
                    .addComponent(jLabel16))
                .addGap(53, 53, 53)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtAdmitDate, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(FirstnameBil)
                    .addComponent(PatientID, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPatientName, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtGender, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                    .addComponent(txtDoctorName)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PatientID, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtPatientName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(FirstnameBil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel29)
                    .addComponent(txtGender, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtAdmitDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel32))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDoctorName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel35))
                .addGap(24, 24, 24)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(136, 136, 136))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 51, 255), 2, true), "Payment Details"));

        jLabel8.setText("Room Charges");

        jLabel1.setText("Billing Date");

        jLabel3.setText("Medicine Charges");

        jLabel4.setText("Payment Mode Details");

        jLabel9.setText("Total Charges");

        jLabel10.setText("Total Paid");

        txtTotalPaid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalPaidActionPerformed(evt);
            }
        });
        txtTotalPaid.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTotalPaidKeyTyped(evt);
            }
        });

        jLabel12.setText("No. Of Days");

        txtNoOfDays.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNoOfDaysActionPerformed(evt);
            }
        });
        txtNoOfDays.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNoOfDaysKeyTyped(evt);
            }
        });

        jLabel15.setText("Biil No");

        txtRoomCharges.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtRoomChargesKeyTyped(evt);
            }
        });

        txtMedicneCharges.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMedicneChargesKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMedicneChargesKeyTyped(evt);
            }
        });

        txtPaymentModeDetails.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPaymentModeDetailsKeyTyped(evt);
            }
        });

        txtTotalCharges.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        txtTotalCharges.setForeground(new java.awt.Color(0, 51, 204));
        txtTotalCharges.setEnabled(false);
        txtTotalCharges.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTotalChargesKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jLabel3))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(24, 24, 24)
                            .addComponent(jLabel9)
                            .addGap(20, 20, 20))))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtPaymentModeDetails, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtRoomCharges, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtMedicneCharges, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTotalCharges, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel12)
                            .addComponent(jLabel1)
                            .addComponent(jLabel10))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(txtNoOfDays, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtBillingDate, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(BillNo, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTotalPaid, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(jLabel15)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel12)
                    .addComponent(txtNoOfDays, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtRoomCharges, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(jLabel1)
                        .addComponent(txtMedicneCharges, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtBillingDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(BillNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(txtPaymentModeDetails, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtTotalPaid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(txtTotalCharges, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 51, 255), 2, true)));

        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        jButton3.setText("Print Bill");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnUpdate, javax.swing.GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
                        .addComponent(btnSave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jButton3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSave)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnUpdate)
                .addGap(36, 36, 36))
        );

        jPanel4.setBackground(new java.awt.Color(0, 102, 255));

        jLabel14.setFont(new java.awt.Font("Tempus Sans ITC", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("WELCOME TO THE BILLING CENTER DASHBOARD");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 2, Short.MAX_VALUE))
        );

        jPanel5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 255), 2, true));

        TableDisplayPatBill.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null}
            },
            new String [] {
                "Patient No", "Surname", "Firstname", "Gender"
            }
        ));
        TableDisplayPatBill.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableDisplayPatBillMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TableDisplayPatBill);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 153, 0), 2, true));

        SearchBill.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 51, 255), 2, true));
        SearchBill.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SearchBillKeyPressed(evt);
            }
        });

        jXLabel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 51, 255), 2, true));
        jXLabel1.setText("Search For Patient");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jXLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(SearchBill, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jXLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SearchBill, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel7.setBackground(new java.awt.Color(0, 102, 0));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("HOSPITAL MANAGEMENT SYSTEM");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel8.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 51, 255), 2, true));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Room Charges", "Roon No", "Total Paid", "Billing Date"
            }
        ));
        jScrollPane2.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 655, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(36, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)))
                        .addGap(26, 26, 26)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 90, Short.MAX_VALUE)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
private void Reset()
{
    PatientID.setText("");
    txtPatientName.setText("");
    FirstnameBil.setText("");
    txtAdmitDate.setText("");
    txtGender.setText("");
    txtMedicneCharges.setText("");
    txtRoomCharges.setText("");
    txtPaymentModeDetails.setText("");
    txtTotalCharges.setText("");
    txtTotalPaid.setText("");
    txtBillingDate.getDate();
   
    txtNoOfDays.setText("");
    btnSave.setEnabled(true);
    btnUpdate.setEnabled(false);
   
    }
    private void TableDisplayPatBillMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableDisplayPatBillMouseClicked
          
           int row = TableDisplayPatBill.getSelectedRow();
       String Table_Click = ( TableDisplayPatBill.getModel().getValueAt(row, 0).toString());
       btnUpdate.setEnabled(true);
       try{
       String sql = "select * from patienttable where 	Patientid = '"+Table_Click+"'";
       pst = conn.prepareStatement(sql);
       rst = pst.executeQuery();
       if(rst.next()){
            String add1 = rst.getString("Patientid");
                
                PatientID.setText(add1);
                String add2 = rst.getString("Surname");
                txtPatientName.setText(add2);
          String add3 = rst.getString("Firstname");
               FirstnameBil.setText(add3);
                String add4 = rst.getString("RegistrationDate");
                txtAdmitDate.setText(add4);
          String add5 = rst.getString("AssignDoctor");
               txtDoctorName.setText(add5);
                String add6 = rst.getString("Gender");
               txtGender.setText(add6);
//                String add7 = rst.getString("dischargedate");
//               txtDoctorName.setText(add7);
//                
               
       }
       }
catch( Exception e){
        
        
        }
    }//GEN-LAST:event_TableDisplayPatBillMouseClicked

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
      try{
            conn= DbConnection.getconnection();
              if (PatientID.getText().equals("")) {
                JOptionPane.showMessageDialog( this, "Please retrieve Patient ID","Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (txtNoOfDays.getText().equals("")) {
                JOptionPane.showMessageDialog( this, "Please enter no. of days","Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
           if (txtMedicneCharges.getText().equals("")) {
                JOptionPane.showMessageDialog( this, "Please retrieve service charges","Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
         
            if (txtBillingDate.getDate().equals("")) {
                JOptionPane.showMessageDialog( this, "Please enter billing date","Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (txtTotalPaid.getText().equals("")) {
                JOptionPane.showMessageDialog( this, "Please enter total paid","Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
                double add1 = Double.parseDouble(txtTotalCharges.getText());
                double add2 = Double.parseDouble(txtTotalPaid.getText());
             if (add2 > add1) {
                JOptionPane.showMessageDialog( this, "Total Paid is more than total Charges","Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        Statement stmt;
       stmt= conn.createStatement();
       String sql1="Select BiilNo from billing where BiilNo= " + BillNo.getText() + "";
      rst=stmt.executeQuery(sql1);
      if(rst.next()){
        JOptionPane.showMessageDialog( this, "Record already exists","Error", JOptionPane.ERROR_MESSAGE);
        return;
      }
            
            String d2  = ((JTextField)txtBillingDate.getDateEditor().getUiComponent()).getText(); 
            String d3  = ((JTextField)txtBillingDate.getDateEditor().getUiComponent()).getText(); 
            
            String sql=  "insert into billing (PatientID,Surname,Firstname,Gender,DoctorName,AdmitDate,RoomNo,DischargeDate,"
                    + "RoomCharges,MedicineCharges,PaymentModeDetails,TotalCharges,NoOfDays,BillingDate,BiilNo,Totalpaid) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            
            pst=conn.prepareStatement(sql);
            
             pst.setString(1, PatientID.getText());
            pst.setString(2, txtPatientName.getText());
            pst.setString(3, FirstnameBil.getText());
             pst.setString(4, txtGender.getText());
              pst.setString(5, txtAdmitDate.getText());
              pst.setString(6, txtDoctorName.getText());
               pst.setString(7, d3);
               pst.setString(8, txtRoomCharges.getText());
               pst.setString(9, txtMedicneCharges.getText());
               pst.setString(10, txtPaymentModeDetails.getText());
               pst.setString(11, txtTotalCharges.getText());
               pst.setString(12, txtNoOfDays.getText());
               pst.setString(13, d2);
               pst.setString(14, BillNo.getText());
              pst.setString(15, txtTotalPaid.getText());
                pst.setString(16, d2);
                 
                     
             pst.executeUpdate();
            JOptionPane.showMessageDialog(this,"Successfully saved","Record",JOptionPane.INFORMATION_MESSAGE);
            

        }catch(HeadlessException | SQLException ex){
            JOptionPane.showMessageDialog(this,ex);
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        try{
            conn = DbConnection.getconnection();
              String d2  = ((JTextField)jDateChooser1.getDateEditor().getUiComponent()).getText(); 
            String d3  = ((JTextField)txtBillingDate.getDateEditor().getUiComponent()).getText(); 
            String sql1 = "update billing set TotalCharges='" + txtRoomCharges.getText() + "',NoOfDays='" + txtNoOfDays.getText() + "',BiilNo"
                    + "='" + BillNo.getText()+ "',Totalpaid='" + txtTotalPaid.getText() + "' where Surname='" + txtPatientName.getText() + "' ";
   
            
            pst=conn.prepareStatement(sql1);
            pst.execute();
            JOptionPane.showMessageDialog(this,"Successfully Updated","Record",JOptionPane.INFORMATION_MESSAGE);
            btnUpdate.setEnabled(false);

        }catch(HeadlessException | SQLException ex){
            System.out.print(ex);
            JOptionPane.showMessageDialog(this,ex);
            System.out.print(ex);
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void txtNoOfDaysActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNoOfDaysActionPerformed
             
                double add1 = Double.parseDouble(txtRoomCharges.getText());
                double add = Double.parseDouble(txtNoOfDays.getText());
                double add2= add * add1;
                String add3= Double.toString(add2);
                double add5 = Double.parseDouble(txtMedicneCharges.getText());
                //double add6= add4 + add5;
                // String add7= Double.toString(add6);
                //txtTotalCharges.setText(add7);
    }//GEN-LAST:event_txtNoOfDaysActionPerformed

    private void txtTotalPaidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalPaidActionPerformed
                double add1 = Double.parseDouble(txtTotalCharges.getText());
                double add = Double.parseDouble(txtTotalPaid.getText());
                double add2= add1 - add;
                String add3= Double.toString(add2);
              //  txtDueCharges.setText(add3);  
    }//GEN-LAST:event_txtTotalPaidActionPerformed

    private void txtNoOfDaysKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNoOfDaysKeyTyped
      char c=evt.getKeyChar();
      if (!(Character.isDigit(c)|| (c== KeyEvent.VK_BACK_SPACE)||(c==KeyEvent.VK_DELETE))){
          getToolkit().beep();
          evt.consume();
    }//GEN-LAST:event_txtNoOfDaysKeyTyped
    }
    private void txtTotalPaidKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTotalPaidKeyTyped
     char c=evt.getKeyChar();
      if (!(Character.isDigit(c)|| (c== KeyEvent.VK_BACK_SPACE)||(c==KeyEvent.VK_DELETE))){
          getToolkit().beep();
          evt.consume();
      }
                       
    }//GEN-LAST:event_txtTotalPaidKeyTyped

    private void SearchBillKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SearchBillKeyPressed
        // TODO add your handling code here:
        String sql= " Select Patientid,Surname,Firstname,Address,Gender from patienttable where Surname like ?";
        try{
            conn=DbConnection.getconnection();
            pst= conn.prepareCall(sql);
            pst.setString(1, SearchBill.getText() + "%");
            rst= pst.executeQuery();
            TableDisplayPatBill.setModel(DbUtils.resultSetToTableModel(rst));

        }
        catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}

        
                            
    }//GEN-LAST:event_SearchBillKeyPressed

    private void txtGenderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGenderActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGenderActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
         MessageFormat header = new MessageFormat("Billing RECORD ");
        MessageFormat footer = new MessageFormat("Page {0,number,integer}");
        try {
            jTable1.print(JTable.PrintMode.FIT_WIDTH, header, footer, true, null, true, null);
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
    }//GEN-LAST:event_jButton3ActionPerformed

    private void FirstnameBilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FirstnameBilActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FirstnameBilActionPerformed

    private void txtRoomChargesKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRoomChargesKeyTyped
        // TODO add your handling code here:
        char c=evt.getKeyChar();
        if (!(Character.isDigit(c)|| (c== KeyEvent.VK_BACK_SPACE)||(c==KeyEvent.VK_DELETE))){
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_txtRoomChargesKeyTyped

    private void txtMedicneChargesKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMedicneChargesKeyTyped
        // TODO add your handling code here:
        char c=evt.getKeyChar();
        if (!(Character.isDigit(c)|| (c== KeyEvent.VK_BACK_SPACE)||(c==KeyEvent.VK_DELETE))){
            getToolkit().beep();
            evt.consume();
        }
        
        
    }//GEN-LAST:event_txtMedicneChargesKeyTyped

    private void txtPaymentModeDetailsKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPaymentModeDetailsKeyTyped
        // TODO add your handling code here:
        char c=evt.getKeyChar();
        if (!(Character.isDigit(c)|| (c== KeyEvent.VK_BACK_SPACE)||(c==KeyEvent.VK_DELETE))){
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_txtPaymentModeDetailsKeyTyped

    private void txtTotalChargesKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTotalChargesKeyTyped
        // TODO add your handling code here:
        char c=evt.getKeyChar();
        if (!(Character.isDigit(c)|| (c== KeyEvent.VK_BACK_SPACE)||(c==KeyEvent.VK_DELETE))){
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_txtTotalChargesKeyTyped

    private void txtMedicneChargesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMedicneChargesKeyReleased
        // TODO add your handling code here:
        double d1 = Double.parseDouble(txtRoomCharges.getText());
        double d2 = Double.parseDouble(txtMedicneCharges.getText());
        double sum = d1+d2;
        
        txtTotalCharges.setText(String.valueOf(sum));
        
    }//GEN-LAST:event_txtMedicneChargesKeyReleased

    private void Get_Data1(){
      try{
        conn=DbConnection.getconnection();
       String sql="select PatientRegistration.PatientID as 'Patient ID', PatientName as 'Patient Name',sum(serviceCharges) as 'Service Charges' from Services,PatientRegistration where Services.PatientID=PatientRegistration.PatientID group by PatientRegistration.PatientID,PatientName order by PatientName";
         pst=conn.prepareStatement(sql);
         rst= pst.executeQuery();
         TableDisplayPatBill.setModel(DbUtils.resultSetToTableModel(rst));
         }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
          

    }}
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
                if ("Metal".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Bill_Room.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Bill_Room.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Bill_Room.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Bill_Room.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Bill_Room().setVisible(true);
            }
        });
}
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField BillNo;
    private javax.swing.JTextField FirstnameBil;
    public javax.swing.JTextField PatientID;
    private org.jdesktop.swingx.JXSearchField SearchBill;
    private javax.swing.JTable TableDisplayPatBill;
    public javax.swing.JButton btnSave;
    public javax.swing.JButton btnUpdate;
    private javax.swing.JButton jButton3;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private org.jdesktop.swingx.JXLabel jXLabel1;
    public javax.swing.JFormattedTextField txtAdmitDate;
    private com.toedter.calendar.JDateChooser txtBillingDate;
    public javax.swing.JTextField txtDoctorName;
    public javax.swing.JTextField txtGender;
    private javax.swing.JTextField txtMedicneCharges;
    public javax.swing.JTextField txtNoOfDays;
    public javax.swing.JTextField txtPatientName;
    private javax.swing.JTextField txtPaymentModeDetails;
    private javax.swing.JTextField txtRoomCharges;
    private javax.swing.JTextField txtTotalCharges;
    public javax.swing.JTextField txtTotalPaid;
    // End of variables declaration//GEN-END:variables

}