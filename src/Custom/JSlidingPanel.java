/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Custom;

/**
 *
 * @author melitus
 */
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.Timer;

public class JSlidingPanel extends JPanel {

    public static int MOVE_RIGHT = 1;
    public static int MOVE_LEFT = 0;
    /////////////////////////////////
    private int speedPanel = 10;
    private int directionMove = MOVE_LEFT;
    private int timeSpeed = 20;
    private int currentStep;
    private Component currentPanel;
    private Component[] slidingComponents;

    public JSlidingPanel() {
        this(10, MOVE_LEFT, 40, null);
    }

    public JSlidingPanel(int speedPanel, int directionMove, int timeSpeed, Component[] slidingComponents) {
        super(new CardLayout());
        setBorder(BorderFactory.createEtchedBorder());
        this.speedPanel = speedPanel;
        this.directionMove = directionMove;
        this.timeSpeed = timeSpeed;
        setSlidingComponents(slidingComponents);
    }

    public void next() {
        if (slidingComponents != null) {
            ++currentStep;
            if (currentStep > slidingComponents.length - 1) {
                currentStep = 0;
            }
            nextSlidPanel(slidingComponents[currentStep]);
        } else {
            throw new NullPointerException("You didn't set any"
                    + " components to the slideing panel");
        }
    }

    public void previous() {
        if (slidingComponents != null) {
            --currentStep;
            if (currentStep < 0) {
                currentStep = slidingComponents.length - 1;
            }
            nextSlidPanel(slidingComponents[currentStep]);
        } else {
            throw new NullPointerException("You didn't set any"
                    + " components to the slideing panel");
        }
    }

    private void nextSlidPanel(Component showPanel) {
        showPanel.setVisible(true);
        JPanelSlidingListener sl = new JPanelSlidingListener(speedPanel, currentPanel, showPanel, directionMove);
        Timer t = new Timer(timeSpeed, sl);
        sl.timer = t;
        t.start();
        refresh();
    }
    //<editor-fold defaultstate="collapsed" desc="hide this now">

    /**
     * 
     * @param speedPanel
     * @param directionMove
     * @param timeSpeed 
     */
    public void setSlideingProperties(int speedPanel, int directionMove, int timeSpeed) {
        this.speedPanel = speedPanel;
        this.directionMove = directionMove;
        this.timeSpeed = timeSpeed;
    }

    /**
     * @return the speedPanel
     */
    public int getSpeedPanel() {
        return speedPanel;
    }

    /**
     * @param speedPanel the speedPanel to set
     */
    public void setSpeedPanel(int speedPanel) {
        this.speedPanel = speedPanel;
    }

    /**
     * @return the directionMove
     */
    public int getDirectionMove() {
        return directionMove;
    }

    /**
     * @param directionMove the directionMove to set
     */
    public void setDirectionMove(int directionMove) {
        this.directionMove = directionMove;
    }

    /**
     * @return the timeSpeed
     */
    public int getTimeSpeed() {
        return timeSpeed;
    }

    /**
     * @param timeSpeed the timeSpeed to set
     */
    public void setTimeSpeed(int timeSpeed) {
        this.timeSpeed = timeSpeed;
    }

    /**
     * @return the slidingComponents
     */
    public Component[] getSlidingComponents() {
        return slidingComponents;
    }

    //</editor-fold>
    /**
     * @param slidingComponents the slidingComponents to set
     */
    public void setSlidingComponents(Component[] slidingComponents) {
        if (slidingComponents != null) {
            setLayout(new CardLayout());
            this.slidingComponents = slidingComponents;
            currentPanel = slidingComponents[0];
            for (Component c : slidingComponents) {
                add(c);
            }
        }
    }

    private class JPanelSlidingListener implements ActionListener {

        private Component hidePanel;
        private Component showPanel;
        private int steps;
        private int step = 0;
        private Timer timer;
        private int isNext;

        public JPanelSlidingListener(int steps, Component HidePanel, Component ShowPanel, int isNext) {
            this.steps = steps;
            this.hidePanel = HidePanel;
            this.showPanel = ShowPanel;
            this.isNext = isNext;
            currentPanel = ShowPanel;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Rectangle bounds = hidePanel.getBounds();
            int shift = bounds.width / steps;
            if (isNext == JSlidingPanel.MOVE_LEFT) {
                hidePanel.setLocation(bounds.x - shift, bounds.y);
                showPanel.setLocation(bounds.x - shift + bounds.width, bounds.y);
            } else {
                hidePanel.setLocation(bounds.x + shift, bounds.y);
                showPanel.setLocation(bounds.x + shift - bounds.width, bounds.y);
            }
            repaint();
            step++;
            if (step == steps) {
                timer.stop();
                hidePanel.setVisible(false);
            }
        }
    }

    public void refresh() {
        revalidate();
        repaint();
    }
}
