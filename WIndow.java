
package sibenice;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class WIndow extends javax.swing.JFrame {

    private Logic logic;
    private final String[] alphabet = new String[]{"a", "á", "b", "c", "č", "d", "ď", "e", "é", "ě", "f", "g", "h", "ch", "i", "í", "j", "k", "l", "m", "n", "ň", "o", "ó", "p", "q", "r", "ř", "s", "š", "t", "ť", "u", "ú", "ů", "v", "w", "x", "y", "ý", "z", "ž"};
    private ArrayList<JButton> alphBtns;
  
    public WIndow() {
        alphBtns = new ArrayList<>();
        logic = new Logic();
        initComponents();

        // Hide victory controls
        winBtn.setVisible(false);
        winLbl.setVisible(false);
        winLbl.setText("Výhra");

        jLabel1.setText("");
        this.setBounds(0, 0, 800, 850);
        this.setVisible(true);
        int x = 0;
        int y = 0;
        
        for (int i = 0; i < alphabet.length; i++) {
            generateButton(alphabet[i], x, y);
            x++;
            if (x >= 7) {
                x = 0;
                y++;
            }
        }
        redrawWord(false);
    }

    private void generateButton(String letter, int xGrid, int yGrid) {
        JButton btn = new JButton();
        btn.setText(letter);
        btn.setBounds(10 + (40 * xGrid), 320 + (40 * yGrid), 32, 32);
        btn.setBorder(new LineBorder(Color.black));

        btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Don't do anything if game over
                // System.out.println(logic.isGameOver());
                if(logic.isGameOver()) {
                    return;
                }
                
                // Check letter
                if (!logic.checkLetter(btn.getText())) {
                    if(!draw1.nextImage()) {
                        // We ran out of images (aka. game over)
                        redrawWord(true);
                        logic.setGameOver(true);
                        winLbl.setText("Prohra!");
                        winLbl.setVisible(true);
                        winBtn.setVisible(true);
                        return;
                    }
                }
                btn.setEnabled(false);
                redrawWord(false);

                // Check victory
                if (logic.checkVictory()) {
                    // We won
                    redrawWord(true);
                    logic.setGameOver(true);
                    winBtn.setVisible(true);
                    winLbl.setVisible(true);
                    return;
                }
            }
        });
        
        this.add(btn);
        alphBtns.add(btn);
        revalidate();
        repaint();
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        draw1 = new sibenice.Draw();
        winLbl = new javax.swing.JLabel();
        winBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(200, 200, 200));

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("W _ R D");

        winLbl.setFont(new java.awt.Font("Dialog", 1, 48)); // NOI18N
        winLbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        winLbl.setText("Výhra!");
        winLbl.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        winBtn.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        winBtn.setText("Hrát Znovu");
        winBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                winBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 382, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(draw1, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(winLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(winBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 408, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
                    .addComponent(draw1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(winLbl)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(winBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(256, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void winBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_winBtnActionPerformed
        // Reset the game
        winBtn.setVisible(false);
        winLbl.setVisible(false);
        winLbl.setText("Výhra");
        for (int i = 0; i < alphBtns.size(); i++) {
            alphBtns.get(i).setEnabled(true);
        }
        draw1.newGame();
        logic.loadNewWord();
        redrawWord(false);
        logic.setGameOver(false);
    }//GEN-LAST:event_winBtnActionPerformed

    private void redrawWord(boolean drawFullWord) {
        String[] w;
        if(drawFullWord) {
            w = logic.getSplitWord();
        } else {
            w = logic.getGuessProgress();
        }
        String s = "";
        for (int i = 0; i < w.length; i++) {
            if (w[i] == null) {
                s += "_";
            } else {
                s += w[i];
            }
            s += " ";
        }
        jLabel1.setText(s);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private sibenice.Draw draw1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton winBtn;
    private javax.swing.JLabel winLbl;
    // End of variables declaration//GEN-END:variables
}
