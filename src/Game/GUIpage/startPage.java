package Game.GUIpage;


import Game.Game.Game;//bujhinai
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

public class startPage extends javax.swing.JFrame {

    
    startPage st;
    
    public startPage() {
        initComponents();//default fntn to set initial value of components
        this.setTitle("Can You See Me?");
    }

    
    @SuppressWarnings("unchecked")//mne ki
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        gameBtn = new javax.swing.JButton();
        aboutBtn = new javax.swing.JButton();
        exitBtn = new javax.swing.JButton();
        InsBtn = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        gameBtn.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        gameBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Game/GUIpage/play.png"))); // NOI18N
        gameBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gameBtnActionPerformed(evt);
            }
        });

        aboutBtn.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        aboutBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Game/GUIpage/about.png"))); // NOI18N

        exitBtn.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        exitBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Game/GUIpage/exit.png"))); // NOI18N
        exitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitBtnActionPerformed(evt);
            }
        });

        InsBtn.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        InsBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Game/GUIpage/instructions.png"))); // NOI18N
        InsBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InsBtnActionPerformed(evt);
            }
        });

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Game/GUIpage/plane.gif"))); // NOI18N

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Game/GUIpage/seeMe.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(290, 290, 290)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(gameBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jLabel3))))
            .addGroup(layout.createSequentialGroup()
                .addGap(290, 290, 290)
                .addComponent(InsBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addComponent(aboutBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(312, 312, 312)
                .addComponent(exitBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 750, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(240, 240, 240)
                        .addComponent(gameBtn))
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addComponent(InsBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(aboutBtn)
                    .addComponent(exitBtn))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void setStartPage(startPage st)
    {
        this.st = st;
    }
    
    private void gameBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gameBtnActionPerformed
        try {
            
            Game game = new Game("Can You See Me?" , 750 , 550 , st);
            game.start();
            dispose();
            
        } catch (Exception ex) {
            Logger.getLogger(startPage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_gameBtnActionPerformed

    private void exitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitBtnActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitBtnActionPerformed

    private void InsBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InsBtnActionPerformed
        Instructions in = new Instructions();
        in.setVisible(true);
        in.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        in.setLocationRelativeTo(null);
    }//GEN-LAST:event_InsBtnActionPerformed

   
    public static void main(String args[]) {
       
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new startPage().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton InsBtn;
    private javax.swing.JButton aboutBtn;
    private javax.swing.JButton exitBtn;
    private javax.swing.JButton gameBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    // End of variables declaration//GEN-END:variables
}
