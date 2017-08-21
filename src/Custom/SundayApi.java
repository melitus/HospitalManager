/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Custom;

/**
 *
 * @author Melitus
 */
import javax.swing.*;
import java.awt.*;
import java.util.Calendar;
import java.sql.*;
import javax.swing.table.*;
import java.io.*;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.awt.Graphics2D;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.filechooser.FileNameExtensionFilter;


public class SundayApi {
    
     private static final int IMG_WIDTH = 120;
    private static final int IMG_HEIGHT = 120;
    JLabel label;
    ImageIcon photo;
    WritableRaster raster;
    DataBufferByte data;
    File image;
    BufferedImage resizeImageJpg;
    private Clip clip;
   
    public void browseImage(JLabel jl,JLabel jlb){
    JFileChooser chooser;
        FileNameExtensionFilter filter;
        chooser = new JFileChooser();
        chooser.setCurrentDirectory(image);
        filter = new FileNameExtensionFilter("jpeg, gif and png files", "jpg", "gif", "png");
        chooser.addChoosableFileFilter(filter);

        int i = chooser.showOpenDialog(null);
        if (i == JFileChooser.APPROVE_OPTION) {
            image = chooser.getSelectedFile();
            jlb.setText(image.getAbsolutePath());
            
            try {
                BufferedImage originalImage = ImageIO.read(image);
                int type = originalImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : originalImage.getType();

                BufferedImage resizeImageJpg = resizeImage(originalImage, type);
                photo = new ImageIcon(toImage(resizeImageJpg));

                //converting buffered image to byte array
                raster = resizeImageJpg.getRaster();
                data = (DataBufferByte) raster.getDataBuffer();

            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
/*ps=connection.prepareStatement("insert into image values(?,?)");
FileInputStream fileInputStream=new FileInputStream(filePath);
byte b[]=new byte[fileInputStream.available()];
fileInputStream.read(b);
fileInputStream.close();
ps.setObject(1, jTextField1.getText());
ps.setBytes(2, b);*/
    jl.setIcon(photo);
    jl.repaint();jl.revalidate();
    }
    


    } 
    public Icon webImage(String file)
{
    Icon webico;
    try
    {
    	BufferedImage originalImage = ImageIO.read(new File(file));
	int type = originalImage.getType() == 0? BufferedImage.TYPE_INT_ARGB : originalImage.getType();
 resizeImageJpg = resizeImage(originalImage, type); 

	}
catch(Exception e)
        {
		System.out.println(e.getMessage());
	}
    
     webico = new ImageIcon(resizeImageJpg);	
               
    
       return webico;
    
    
     }

     private BufferedImage resizeImage(BufferedImage originalImage, int type) {
        BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, type);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
        g.dispose();

        return resizedImage;
    }
    public Image toImage(BufferedImage bufferedImage) {
        return Toolkit.getDefaultToolkit().createImage(bufferedImage.getSource());
    }
    public void controlSound() {
URL url= getClass().getResource("/audio/camera_click.mp3");
soundStream(url);//This method will load the sound

if (clip.isRunning())
clip.stop(); //// Stop the Player if it is still Running
clip.setFramePosition(0); /////////// Rewind Clip to Beginning 
clip.start();  //Start Playing
}

public void soundStream (URL url){

    try {
        AudioInputStream ais= AudioSystem.getAudioInputStream(url);
       clip= AudioSystem.getClip();
        clip.open(ais);
    } catch (UnsupportedAudioFileException ex) {
        ex.printStackTrace();
    } catch (IOException ex) {
        ex.printStackTrace();
    }
     catch (LineUnavailableException ex) {
        ex.printStackTrace();
    }}
}
