/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Custom;

/**
 *
 * @author Melitus
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 *

/**
 *
 * @author Melitus
 */
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;


import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;


@SuppressWarnings("serial")
public class webcam extends javax.swing.JPanel {

   // dotmonsapi api = new dotmonsapi();
    	private Executor executor = Executors.newSingleThreadExecutor();

	private Dimension captureSize = WebcamResolution.VGA.getSize();
	private Dimension displaySize = WebcamResolution.QQVGA.getSize();

	private Webcam webcam = Webcam.getDefault();
	public WebcamPanel panel = new WebcamPanel(webcam, displaySize, false);

	private JButton btSnapMe = new JButton(new SnapMeAction());
       
        
	private class SnapMeAction extends AbstractAction {

		public SnapMeAction() {
                    
			super("Snap");
                        
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				File file = new File("ehm.jpg");
				ImageIO.write(webcam.getImage(), "JPG", file);
				System.out.println("Image saved in " + file.getAbsolutePath());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
        public void stop()
        {
            panel.stop();
        }
        public void isFile()
        {
            File ff = new File("c:\\nysc\\");
            if (ff.exists()==false)
            {
                try
                {
                ff.createNewFile();
                }
                catch(Exception rr)
                {
                    System.out.println("Error webcam.isFile" + rr);
                }
            }
        }


	public webcam() {
            webcam.setViewSize(captureSize);
                
		panel.setFPSDisplayed(true);
		panel.setFillArea(true);
                
                btSnapMe.setEnabled(true);
setLayout(new FlowLayout());
		add(panel);
		add(btSnapMe);
	}

	public static void main(String[] args) {
            JFrame frame = new JFrame();
            
		webcam web = new webcam();
                frame.add(web);
                
                frame.pack();
                //frame.setLocation(api.centerx(frame), api.centery(frame));
		frame.setVisible(true);
                frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                Webcam webcam = Webcam.getDefault();
		webcam.open();
	}
}
