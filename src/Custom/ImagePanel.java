/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Custom;

/**
 *
 * @author user
 */
import java.awt.*;
public class ImagePanel  extends javax.swing.JPanel {
  private Image backgroundImage;
  public ImagePanel(){
  
  }
  public Image getImage(){
  
      return backgroundImage;
  }
  public void setImage(Image image){
  this.backgroundImage = image;
  Dimension size = new Dimension(this.getWidth(), this.getHeight());
  this.setPreferredSize(size);
  this.setMaximumSize(size);
  this.setMinimumSize(size);
  this.setSize(size);
  this.setOpaque(false);
  }
  public static void main(String[] args){
  }
  public void paintComponent(Graphics g){
  if(this.backgroundImage !=null){
  g.drawImage(backgroundImage, 0, 0, this.getWidth(),this.getHeight(),null);
  
  }
  
  }
}
