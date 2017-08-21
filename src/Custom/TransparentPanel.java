/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EHM.Customs;

/**
 *
 * @author user
 */
import java.awt.*;
public class TransparentPanel extends javax.swing.JPanel{
    private int red;
     private int green;
      private int blue;
       private int alpha;
       
       public TransparentPanel(){
           this(233,238,236,100);
       
       }
        public TransparentPanel(int red,int green,int blue,int alpha){
       super();
       this.setOpaque(false);
       this.setLayout(null);
       }
    
   public int getAlpha(){
   return alpha;
   } 
   public void setAlpha(int alpha){
   this.alpha = alpha;
   }
   public int getBlue(){
   return blue;
   } 
   public void setBlue(int blue){
   this.blue = blue;
   }
   public int getGreen(){
   return green;
   } 
   public void setGreen(int green){
   this.green = green;
   }
   public int getRed(){
   return red;
   } 
   public void setRed(int red){
   this.red = red;
   }
   public void paintComponent(Graphics g){}

    @Override
    public void paintComponents(Graphics g) {
        super.paintComponents(g); //To change body of generated methods, choose Tools | Templates.
        Color c = new Color(red,green,blue,alpha);
        g.setColor(c);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
    }
   
}
