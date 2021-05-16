package sibenice;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Draw extends JPanel {

    private BufferedImage[] imgs;
    private int currImg = 0;
    private boolean win = false;

    public Draw() {
        try {
            int fileCount = new File("src\\obrazky").list().length;
            if(fileCount > 0) {
                imgs = new BufferedImage[fileCount];
                for (int i = 0; i < fileCount; i++) {
                    imgs[i] = ImageIO.read(new File("src\\obrazky\\" + i + ".png"));
                }
                newGame();
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    
    /**
     * 
     * @return true if next image can be loaded, false if we're out of images
     */
    public boolean nextImage() {
        
        if(currImg + 2 >= imgs.length) {
            currImg++;
            repaint();
            return false;
        }
        
        if (currImg + 1 < imgs.length) {
            currImg++;
            repaint();
            return true;
        }
        return false;
    }

    public void newGame() {
        currImg = 0;
        repaint();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(imgs[currImg], 0, 0, null);
    }
}
