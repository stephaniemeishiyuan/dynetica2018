/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dynetica.gui.algorithms;

import dynetica.algorithm.DataFit;
import dynetica.gui.ApplicationFrame;
import dynetica.gui.plotting.DataFitWindow;
import dynetica.gui.plotting.InteractiveFigure;
import dynetica.objective.AbstractMetric;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author chrismurphy
 */
public class DataFittingEditor extends javax.swing.JPanel {

    
    dynetica.system.ReactiveSystem system = null;
    dynetica.algorithm.DataFit df = null;
    
    final String[] functions = {"choose function","quadratic","exponential", "power", "hill"};
    
    private String function;
    /**
     * Creates new form DataFittingEditor
     */
    public DataFittingEditor(DataFit df, DefaultListModel xmodel, DefaultListModel ymodel) {
        this.df = df;
        system = df.getSystem();
        initComponents();
        for(int i=0;i<functions.length;i++){
            functionComboBox1.addItem(functions[i]);
        }
        xList.setModel(xmodel);
        yList.setModel(ymodel);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        functionComboBox1 = new javax.swing.JComboBox();
        runButton = new javax.swing.JButton();
        cancelButton4 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        xList = new javax.swing.JList();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        yList = new javax.swing.JList();

        jLabel3.setText("Function Type:");

        functionComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                functionComboBox1ActionPerformed(evt);
            }
        });

        runButton.setLabel("Fit Data");
        runButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                runButtonActionPerformed(evt);
            }
        });

        cancelButton4.setLabel("Cancel");
        cancelButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButton4ActionPerformed(evt);
            }
        });

        xList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                xListValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(xList);

        jLabel4.setText("Independent:");

        jLabel5.setText("Dependent:");

        jScrollPane2.setViewportView(yList);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(functionComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(runButton, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cancelButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(functionComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(runButton)
                    .addComponent(cancelButton4))
                .addGap(15, 15, 15))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void runButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_runButtonActionPerformed
        // TODO add your handling code here:
        df.setIndependent((String) xList.getSelectedValue());
        df.setDependent((String) yList.getSelectedValue());
        if(function=="power"){
            df.runPowerFit();
        }else if(function=="quadratic"){
            df.runQuadraticFit();
        }else if(function=="hill"){       
            df.runHillFit();
        }else if(function=="exponential"){
            df.runExponentialFit();
        }else{
            JOptionPane.showMessageDialog(this.getRootPane().getParent(),"Please select a function type!","Inane warning",JOptionPane.WARNING_MESSAGE);
        }

        
        DataFitWindow dfw = new DataFitWindow(df);
        dfw.pack();
        dfw.show();
        
        
    }//GEN-LAST:event_runButtonActionPerformed

    private void functionComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_functionComboBox1ActionPerformed
        // TODO add your handling code here:
        this.function = (String) functionComboBox1.getSelectedItem();
    }//GEN-LAST:event_functionComboBox1ActionPerformed

    private void xListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_xListValueChanged
        // TODO add your handling code here:
        xList.getSelectedIndex();
    }//GEN-LAST:event_xListValueChanged

    private void cancelButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButton4ActionPerformed
        // TODO add your handling code here:
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        frame.dispose();
    }//GEN-LAST:event_cancelButton4ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton4;
    private javax.swing.JComboBox functionComboBox1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton runButton;
    private javax.swing.JList xList;
    private javax.swing.JList yList;
    // End of variables declaration//GEN-END:variables
}
