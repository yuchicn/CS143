/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package famousoperas;

import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.JOptionPane;

/**
 * <pre>
 * File         EditOpera.java
 * Project      FamousOperas database
 * Description  A class for a add opera dialog to edit an existing opera in 
 * the operas database
 * Platform     Windows 10, Netbean 8.2, jdk 1.8.0 101
 * Date         5/8/2017
 * Hours        1 hour
 * Class     CS143, SCC
 </pre>
 *
 * @author      yu-chi.chen
 */
public class EditOpera extends javax.swing.JDialog {
    //instance variable
    Opera newOpera;

    /**
     * Method  Editopera
     * Description  Default constructor of EditOpera.Creates new form 
     * EditOpera form centered, with save button as default. Populate all the 
     * fields from the operaToEdit object to the textfields
     * @param parent
     * @param modal
     * @param operaToEdit the Opera object to be edited
     */
    public EditOpera(java.awt.Frame parent, boolean modal, Opera operaToEdit) {
        super(parent, modal);
        initComponents();
        //set buttonAdd as default
        this.getRootPane().setDefaultButton(saveJButton);
        //centers the form at start.
        this.setLocationRelativeTo(null);
        //set icon
        this.setIconImage(Toolkit.getDefaultToolkit().getImage("src/famuosoperas/logo.jpg"));
        nameJTextField.requestFocus();
        newOpera = null;
        this.setModal(true);
        
        nameJTextField.setText(operaToEdit.getName());
        composerJTextField.setText(operaToEdit.getComposer());
        yearJTextField.setText(String.valueOf(operaToEdit.getYear()));
        cityJTextField.setText(operaToEdit.getCity());
        synopsisJTextField.setText(operaToEdit.getSynopsis());
        linkJTextField.setText(operaToEdit.getLink());
    }

    /**
     * Method  getNewOpera
     * Description  Getter method for the instance vaiable newOpera.
     * Called by the main GUI to get the newOpera object.
     * @return Opera newOpera
     */
    public Opera getNewOpera() {
        return newOpera;
    }
    
    /**
     * Method  checkInput
     * Description  Called by the save button to check if the user's input is 
     * valid
     * @return boolean
     */
    private boolean checkInput()
    {
        if(Validation.isEmpty(nameJTextField.getText()) || 
           Validation.isEmpty(composerJTextField.getText()) ||
           Validation.isEmpty(yearJTextField.getText()) ||
           Validation.isEmpty(cityJTextField.getText()) ||
           Validation.isEmpty(synopsisJTextField.getText()) ||
           Validation.isEmpty(linkJTextField.getText()))
        {
            if(Validation.isEmpty(nameJTextField.getText())) 
                nameJTextField.setBackground(Color.getHSBColor(100, 255, 100));
            if(Validation.isEmpty(composerJTextField.getText())) 
                composerJTextField.setBackground(Color.getHSBColor(100, 255, 100));
            if(Validation.isEmpty(cityJTextField.getText())) 
                cityJTextField.setBackground(Color.getHSBColor(100, 255, 100));
            if(Validation.isEmpty(yearJTextField.getText())) 
                yearJTextField.setBackground(Color.getHSBColor(100, 255, 100));
            if(Validation.isEmpty(synopsisJTextField.getText())) 
                synopsisJTextField.setBackground(Color.getHSBColor(100, 255, 100));
            if(Validation.isEmpty(linkJTextField.getText())) 
                linkJTextField.setBackground(Color.getHSBColor(100, 255, 100));
          
            JOptionPane.showMessageDialog(this, "Input can't be empty!", "Input Error", JOptionPane.ERROR_MESSAGE);
            return false;
 
        }
        if(!Validation.isInteger(yearJTextField.getText()) || !Validation.isValidYear(yearJTextField.getText())) 
        {
            yearJTextField.setBackground(Color.getHSBColor(100, 255, 100));
            JOptionPane.showMessageDialog(this, "Please enter a valid year!", "Input Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if(!Validation.isValidName(composerJTextField.getText())) 
        {
            composerJTextField.setBackground(Color.getHSBColor(100, 255, 100));
            JOptionPane.showMessageDialog(this, "Please enter a valid name!", "Input Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if(!Validation.isValidLink(linkJTextField.getText()))
        {
            linkJTextField.setBackground(Color.getHSBColor(100, 255, 100));
            JOptionPane.showMessageDialog(this, "Please enter a valid youtube link!", "Input Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        titlePanel = new javax.swing.JPanel();
        titleJLabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        displayPanel = new javax.swing.JPanel();
        nameJLabel = new javax.swing.JLabel();
        nameJTextField = new javax.swing.JTextField();
        composerJLabel = new javax.swing.JLabel();
        composerJTextField = new javax.swing.JTextField();
        yearJLabel = new javax.swing.JLabel();
        yearJTextField = new javax.swing.JTextField();
        cityJLabel = new javax.swing.JLabel();
        cityJTextField = new javax.swing.JTextField();
        synopsisJLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        synopsisJTextField = new javax.swing.JTextArea();
        linkJLabel = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        linkJTextField = new javax.swing.JTextArea();
        controlPanel = new javax.swing.JPanel();
        saveJButton = new javax.swing.JButton();
        clearJButton = new javax.swing.JButton();
        cancelJButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Edit Opera");
        setResizable(false);

        titlePanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51), 2));

        titleJLabel.setFont(new java.awt.Font("Open Sans", 1, 36)); // NOI18N
        titleJLabel.setForeground(new java.awt.Color(51, 51, 51));
        titleJLabel.setText("Edit Opera");

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/famousoperas/L.png"))); // NOI18N

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/famousoperas/R.png"))); // NOI18N

        javax.swing.GroupLayout titlePanelLayout = new javax.swing.GroupLayout(titlePanel);
        titlePanel.setLayout(titlePanelLayout);
        titlePanelLayout.setHorizontalGroup(
            titlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(titlePanelLayout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addComponent(titleJLabel)
                .addGap(49, 49, 49)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52))
        );
        titlePanelLayout.setVerticalGroup(
            titlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(titlePanelLayout.createSequentialGroup()
                .addGap(0, 58, Short.MAX_VALUE)
                .addComponent(titleJLabel)
                .addGap(0, 58, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, titlePanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(titlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(43, 43, 43))
        );

        displayPanel.setLayout(new java.awt.GridLayout(6, 2, 3, 3));

        nameJLabel.setFont(new java.awt.Font("Seravek", 0, 14)); // NOI18N
        nameJLabel.setForeground(new java.awt.Color(102, 102, 102));
        nameJLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        nameJLabel.setText("Name of opera:");
        displayPanel.add(nameJLabel);

        nameJTextField.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        displayPanel.add(nameJTextField);

        composerJLabel.setFont(new java.awt.Font("Seravek", 0, 14)); // NOI18N
        composerJLabel.setForeground(new java.awt.Color(102, 102, 102));
        composerJLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        composerJLabel.setText("Composer:");
        displayPanel.add(composerJLabel);

        composerJTextField.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        composerJTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                composerJTextFieldActionPerformed(evt);
            }
        });
        displayPanel.add(composerJTextField);

        yearJLabel.setFont(new java.awt.Font("Seravek", 0, 14)); // NOI18N
        yearJLabel.setForeground(new java.awt.Color(102, 102, 102));
        yearJLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        yearJLabel.setText("Year first performed:");
        displayPanel.add(yearJLabel);

        yearJTextField.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        displayPanel.add(yearJTextField);

        cityJLabel.setFont(new java.awt.Font("Seravek", 0, 14)); // NOI18N
        cityJLabel.setForeground(new java.awt.Color(102, 102, 102));
        cityJLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        cityJLabel.setText("City where first performed:");
        displayPanel.add(cityJLabel);

        cityJTextField.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        displayPanel.add(cityJTextField);

        synopsisJLabel.setFont(new java.awt.Font("Seravek", 0, 14)); // NOI18N
        synopsisJLabel.setForeground(new java.awt.Color(102, 102, 102));
        synopsisJLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        synopsisJLabel.setText("Synopsis:");
        displayPanel.add(synopsisJLabel);

        synopsisJTextField.setColumns(20);
        synopsisJTextField.setLineWrap(true);
        synopsisJTextField.setRows(5);
        synopsisJTextField.setWrapStyleWord(true);
        jScrollPane1.setViewportView(synopsisJTextField);

        displayPanel.add(jScrollPane1);

        linkJLabel.setFont(new java.awt.Font("Seravek", 0, 14)); // NOI18N
        linkJLabel.setForeground(new java.awt.Color(102, 102, 102));
        linkJLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        linkJLabel.setText("Link to Youtube:");
        displayPanel.add(linkJLabel);

        linkJTextField.setColumns(20);
        linkJTextField.setLineWrap(true);
        linkJTextField.setRows(5);
        linkJTextField.setWrapStyleWord(true);
        jScrollPane2.setViewportView(linkJTextField);

        displayPanel.add(jScrollPane2);

        controlPanel.setLayout(new java.awt.GridLayout(1, 3, 10, 3));

        saveJButton.setForeground(new java.awt.Color(51, 51, 51));
        saveJButton.setText("Save");
        saveJButton.setToolTipText("Save all the fields");
        saveJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveJButtonActionPerformed(evt);
            }
        });
        controlPanel.add(saveJButton);

        clearJButton.setText("Clear");
        clearJButton.setToolTipText("Clear all the fields");
        clearJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearJButtonActionPerformed(evt);
            }
        });
        controlPanel.add(clearJButton);

        cancelJButton.setForeground(new java.awt.Color(51, 51, 51));
        cancelJButton.setText("Cancel");
        cancelJButton.setToolTipText("Cancel editing");
        cancelJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelJButtonActionPerformed(evt);
            }
        });
        controlPanel.add(cancelJButton);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titlePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(controlPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 576, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(19, 19, 19)
                    .addComponent(displayPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 613, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(63, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titlePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 306, Short.MAX_VALUE)
                .addComponent(controlPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(181, Short.MAX_VALUE)
                    .addComponent(displayPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(72, 72, 72)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void composerJTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_composerJTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_composerJTextFieldActionPerformed
    
    /** 
     * Method  cancelJButtonActionPerformed
     * Description  Event handler for the cancel button. Close the JDialog.
     * @param evt action event of the cancel button being clicked
     * @return void
     */
    private void cancelJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelJButtonActionPerformed
        this.dispose();
    }//GEN-LAST:event_cancelJButtonActionPerformed

    /** 
     * Method  saveJButtonActionPerformed
     * Description  Event handler for the save button. Create a new Opera 
     * object newOperas with all the fields. 
     * @param evt action event of the save button being clicked
     * @return void
     */
    private void saveJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveJButtonActionPerformed

            //set the background color of all the text fields white
            nameJTextField.setBackground(Color.WHITE);
            composerJTextField.setBackground(Color.WHITE);
            cityJTextField.setBackground(Color.WHITE);
            yearJTextField.setBackground(Color.WHITE);
            synopsisJTextField.setBackground(Color.WHITE);
            linkJTextField.setBackground(Color.WHITE);
            
            if(checkInput())
            {
                String name = nameJTextField.getText();
                String composer = composerJTextField.getText();
                int year = Integer.parseInt(yearJTextField.getText());
                String city = cityJTextField.getText();
                String synopsis = synopsisJTextField.getText();
                String link = linkJTextField.getText();
                newOpera = new Opera(name, composer, year, city, synopsis, link);
                this.dispose();
            }
    }//GEN-LAST:event_saveJButtonActionPerformed

    /** 
     * Method  clearJButtonActionPerformed
     * Description  Event handler for the clear button. Clear all the text 
     * fields in the form.
     * @param evt action event of the clear button being clicked
     * @return void
     */
    private void clearJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearJButtonActionPerformed
        //clear all the text fields
        nameJTextField.setText("");
        composerJTextField.setText("");
        yearJTextField.setText("");
        cityJTextField.setText("");
        synopsisJTextField.setText("");
        linkJTextField.setText("");
    }//GEN-LAST:event_clearJButtonActionPerformed

    /**
     * @param args the command line arguments
     */
   /* public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
       /* try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(EditBallet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EditBallet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EditBallet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EditBallet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
       /* java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                EditOpera dialog = new EditOpera (new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }*/

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelJButton;
    private javax.swing.JLabel cityJLabel;
    private javax.swing.JTextField cityJTextField;
    private javax.swing.JButton clearJButton;
    private javax.swing.JLabel composerJLabel;
    private javax.swing.JTextField composerJTextField;
    private javax.swing.JPanel controlPanel;
    private javax.swing.JPanel displayPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel linkJLabel;
    private javax.swing.JTextArea linkJTextField;
    private javax.swing.JLabel nameJLabel;
    private javax.swing.JTextField nameJTextField;
    private javax.swing.JButton saveJButton;
    private javax.swing.JLabel synopsisJLabel;
    private javax.swing.JTextArea synopsisJTextField;
    private javax.swing.JLabel titleJLabel;
    private javax.swing.JPanel titlePanel;
    private javax.swing.JLabel yearJLabel;
    private javax.swing.JTextField yearJTextField;
    // End of variables declaration//GEN-END:variables
}
