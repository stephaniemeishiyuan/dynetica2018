/*
 * MassActionEditor.java
 *
 * Created on April 10, 2001, 2:07 AM
 */

package dynetica.gui.reactions;

/**
 *
 * @author Lingchong You
 * @version 0.01
 */

import dynetica.entity.*;
import javax.swing.*;

public class MassActionEditor extends javax.swing.JPanel {

    dynetica.reaction.MassAction reaction;
    DefaultListModel substanceListModel = new DefaultListModel();
    DefaultListModel parameterListModel = new DefaultListModel();

    /** Creates new customizer MassActionEditor */
    public MassActionEditor(dynetica.reaction.MassAction r) {
        reaction = r;
        initComponents();
        updateListModels();

        //6/2016. Added by LY to enable copy/cut/paste in Dynetica.
        dynetica.gui.DyneticaGUITools.installContextMenu(this);       
       
    }

    // update the substancelistModel and parameterListModel everytime the
    // reaction is modified.

    private void updateListModels() {
        if (!substanceListModel.isEmpty())
            substanceListModel.removeAllElements();
        Object[] subs = reaction.getSubstances().toArray();
        for (int i = 0; i < subs.length; i++) {
            substanceListModel.addElement(((Entity) (subs[i])).getName());
        }
        if (!substanceListModel.isEmpty())
            substanceList.setSelectedIndex(0);

        if (!parameterListModel.isEmpty())
            parameterListModel.removeAllElements();
        Object[] paras = reaction.getParameters().toArray();
        for (int i = 0; i < paras.length; i++) {
            parameterListModel.addElement(((Entity) (paras[i])).getName());
        }

        if (!parameterListModel.isEmpty())
            parameterList.setSelectedIndex(0);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the FormEditor.
     */
    // <editor-fold defaultstate="collapsed"
    // desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jSplitPane3 = new javax.swing.JSplitPane();
        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel2 = new javax.swing.JPanel();
        formulaLabel = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        stoichiometryField = new javax.swing.JTextArea();
        k1Field = new javax.swing.JTextField();
        k2Field = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        kineticsField = new javax.swing.JTextArea();
        jSplitPane2 = new javax.swing.JSplitPane();
        substancePanel = new javax.swing.JPanel();
        sListPanel = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        substanceList = new javax.swing.JList();
        jLabel5 = new javax.swing.JLabel();
        sEditor = new javax.swing.JPanel();
        parameterPanel = new javax.swing.JPanel();
        pListPanel = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        parameterList = new javax.swing.JList();
        jLabel6 = new javax.swing.JLabel();
        pEditor = new javax.swing.JPanel();

        setMaximumSize(new java.awt.Dimension(350, 400));
        setLayout(new java.awt.BorderLayout(0, 1));

        jLabel1.setText("<html> <i> Mass Action </i> " + reaction.getName());
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel1.setPreferredSize(new java.awt.Dimension(220, 30));
        jLabel1.setMinimumSize(new java.awt.Dimension(220, 30));
        jLabel1.setMaximumSize(new java.awt.Dimension(220, 30));
        add(jLabel1, java.awt.BorderLayout.NORTH);

        jSplitPane3.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        jSplitPane3.setMaximumSize(new java.awt.Dimension(350, 400));

        jSplitPane1.setDividerLocation(150);
        jSplitPane1.setDividerSize(1);

        jPanel2.setPreferredSize(new java.awt.Dimension(130, 100));
        jPanel2.setLayout(new java.awt.GridLayout(4, 1, 2, 2));

        formulaLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        formulaLabel.setText("Stoichiometry");
        formulaLabel
                .setToolTipText("The formula of the reaction following the current\nconvention in writing down chemical reactions. \nFor example, you can write:\n\n 3 A + 2 B -> 5 D");
        formulaLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        formulaLabel.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        formulaLabel.setPreferredSize(new java.awt.Dimension(130, 25));
        formulaLabel.setMinimumSize(new java.awt.Dimension(100, 25));
        formulaLabel.setMaximumSize(new java.awt.Dimension(400, 25));
        jPanel2.add(formulaLabel);

        jLabel2.setText("Forward rate constant");
        jLabel2.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jPanel2.add(jLabel2);

        jLabel4.setText("Backward rate constant");
        jLabel4.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jPanel2.add(jLabel4);

        jLabel3.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel3.setText("Kinetics");
        jLabel3.setToolTipText("The rate expression that describes the kinetics of this \nreaction. Follow the convention in writing down a \nmathematical expression.");
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel3.setPreferredSize(new java.awt.Dimension(100, 25));
        jLabel3.setMinimumSize(new java.awt.Dimension(100, 25));
        jLabel3.setMaximumSize(new java.awt.Dimension(100, 25));
        jPanel2.add(jLabel3);

        jSplitPane1.setLeftComponent(jPanel2);

        jPanel3.setLayout(new java.awt.GridLayout(4, 1, 2, 2));

        jScrollPane1.setPreferredSize(new java.awt.Dimension(200, 40));

        stoichiometryField.setWrapStyleWord(true);
        stoichiometryField.setLineWrap(true);
        stoichiometryField.setText(getStoichiometry());
        stoichiometryField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                stoichiometryFieldFocusLost(evt);
            }
        });
        jScrollPane1.setViewportView(stoichiometryField);

        jPanel3.add(jScrollPane1);

        k1Field.setText(getK1());
        k1Field.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                k1FieldActionPerformed(evt);
            }
        });
        k1Field.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                k1FieldFocusLost(evt);
            }
        });
        jPanel3.add(k1Field);

        k2Field.setText(getK2());
        k2Field.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                k2FieldActionPerformed(evt);
            }
        });
        k2Field.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                k2FieldFocusLost(evt);
            }
        });
        jPanel3.add(k2Field);

        kineticsField.setLineWrap(true);
        kineticsField.setEditable(false);
        kineticsField.setText(reaction.getKinetics());
        jScrollPane2.setViewportView(kineticsField);

        jPanel3.add(jScrollPane2);

        jSplitPane1.setRightComponent(jPanel3);

        jSplitPane3.setLeftComponent(jSplitPane1);

        jSplitPane2.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        substancePanel.setLayout(new java.awt.GridLayout(1, 2));

        sListPanel.setLayout(new java.awt.BorderLayout());

        substanceList.setModel(substanceListModel);
        substanceList.setVisibleRowCount(Math.min(8,
                substanceListModel.getSize()));
        substanceList
                .setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        substanceList
                .addListSelectionListener(new javax.swing.event.ListSelectionListener() {
                    public void valueChanged(
                            javax.swing.event.ListSelectionEvent evt) {
                        substanceListValueChanged(evt);
                    }
                });
        jScrollPane3.setViewportView(substanceList);

        sListPanel.add(jScrollPane3, java.awt.BorderLayout.CENTER);

        jLabel5.setText("Substances");
        jLabel5.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        sListPanel.add(jLabel5, java.awt.BorderLayout.NORTH);

        substancePanel.add(sListPanel);
        substancePanel.add(sEditor);

        jSplitPane2.setTopComponent(substancePanel);

        parameterPanel.setLayout(new java.awt.GridLayout(1, 2));

        pListPanel.setLayout(new java.awt.BorderLayout());

        parameterList.setModel(parameterListModel);
        parameterList.setVisibleRowCount(Math.min(8,
                parameterListModel.getSize()));
        parameterList
                .setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        parameterList
                .addListSelectionListener(new javax.swing.event.ListSelectionListener() {
                    public void valueChanged(
                            javax.swing.event.ListSelectionEvent evt) {
                        parameterListValueChanged(evt);
                    }
                });
        jScrollPane4.setViewportView(parameterList);

        pListPanel.add(jScrollPane4, java.awt.BorderLayout.CENTER);

        jLabel6.setText("Parameters");
        jLabel6.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        pListPanel.add(jLabel6, java.awt.BorderLayout.NORTH);

        parameterPanel.add(pListPanel);
        parameterPanel.add(pEditor);

        jSplitPane2.setBottomComponent(parameterPanel);

        jSplitPane3.setRightComponent(jSplitPane2);

        add(jSplitPane3, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void k2FieldFocusLost(java.awt.event.FocusEvent evt) {// GEN-FIRST:event_k2FieldFocusLost
        setK2();
    }// GEN-LAST:event_k2FieldFocusLost

    private void k1FieldFocusLost(java.awt.event.FocusEvent evt) {// GEN-FIRST:event_k1FieldFocusLost
        setK1();
    }// GEN-LAST:event_k1FieldFocusLost

    private void k2FieldActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_k2FieldActionPerformed
        setK2();
    }// GEN-LAST:event_k2FieldActionPerformed

    private void k1FieldActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_k1FieldActionPerformed
        setK1();
    }// GEN-LAST:event_k1FieldActionPerformed

    private void parameterListValueChanged(
            javax.swing.event.ListSelectionEvent evt) {// GEN-FIRST:event_parameterListValueChanged
        parameterPanel.remove(pEditor);
        // If condition added by Kanishk Asthana on 23 July 2013: To fix a
        // runtime error. This error
        // was caused whenever the method updatelistmodel() was executed.
        // This method executes everytime the parameterlist is to be updated; so
        // whenever updatelistmodels()
        // was executed all elements were removed from the parameterlist. Since
        // this removal of elements triggered
        // a parameterListvaluechanged event; the event led to this method being
        // executed. This method would then try to get
        // the current parameter. However since the parameter list was already
        // empty excecution of this method
        // would lead to the Editor misbehaving(runtime error)!
        // Same logic applies for the changes to substancelistmodel as well.
        if (getCurrentParameter() != null) {
            pEditor = getCurrentParameter().editor();
            parameterPanel.add(pEditor);
            parameterPanel.validate();
        }

    }// GEN-LAST:event_parameterListValueChanged

    private void substanceListValueChanged(
            javax.swing.event.ListSelectionEvent evt) {// GEN-FIRST:event_substanceListValueChanged

        substancePanel.remove(sEditor);
        // If condition added by Kanishk Asthana on 23 July 2013: See
        // parameterListvaluechanged for and explaination
        if (getCurrentSubstance() != null) {
            sEditor = getCurrentSubstance().editor();
            substancePanel.add(sEditor);
            substancePanel.validate();
        }

    }// GEN-LAST:event_substanceListValueChanged

    private Parameter getCurrentParameter() {
        int index = parameterList.getSelectedIndex();
        // If condition added by Kanishk Asthana on 23 July 2013: See
        // parameterListvaluechanged for and explaination
        if (index != -1) {
            String pName = (String) (parameterListModel.get(index));
            return (Parameter) (reaction.getSystem().get(pName));
        } else
            return null;
    }

    private Substance getCurrentSubstance() {
        int index = substanceList.getSelectedIndex();
        // If condition added by Kanishk Asthana on 23 July 2013: See
        // parameterListvaluechanged for and explaination
        if (index != -1) {
            String substanceName = (String) (substanceListModel.get(index));
            return (Substance) (reaction.getSystem().get(substanceName));
        } else
            return null;
    }

    private void kineticsFieldFocusLost(java.awt.event.FocusEvent evt) {// GEN-FIRST:event_kineticsFieldFocusLost
        setKinetics();
    }// GEN-LAST:event_kineticsFieldFocusLost

    private void stoichiometryFieldFocusLost(java.awt.event.FocusEvent evt) {// GEN-FIRST:event_stoichiometryFieldFocusLost
        setStoichiometry();
    }// GEN-LAST:event_stoichiometryFieldFocusLost

    private String getStoichiometry() {
        if (reaction.getStoichiometry() != null)
            return reaction.getStoichiometry();
        else
            return "";
    }

    private String getKinetics() {
        if (reaction.getKinetics() != null)
            return reaction.getKinetics();
        else
            return "";
    }

    private String getK1() {
        if (reaction.getK1() != null)
            return reaction.getK1().toString();
        else
            return "";
    }

    private String getK2() {
        if (reaction.getK2() != null)
            return reaction.getK2().toString();
        else
            return "";
    }

    private void setK1() {
        String k1String = k1Field.getText().trim();
        if (k1String != null && k1String.length() > 0) {
            try {
                reaction.setProperty("k1", k1String);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else
            reaction.setK1(null);

        kineticsField.setText(reaction.getKinetics());
        updateListModels();
    }

    private void setK2() {
        String k2String = k2Field.getText().trim();
        if (k2String != null && k2String.length() > 0) {
            try {
                reaction.setProperty("k2", k2String);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else
            reaction.setK2(null);

        kineticsField.setText(reaction.getKinetics());
        updateListModels();
    }

    private void setStoichiometry() {
        String stoichiometry = stoichiometryField.getText().trim();
        String oldStoichiometry = getStoichiometry();
        if (stoichiometry.length() > 0
                && oldStoichiometry.compareTo(stoichiometry) != 0) {
            reaction.setStoichiometry(stoichiometry);
            updateListModels();
            kineticsField.setText(reaction.getKinetics());
        }
    }

    private void setKinetics() {
        String oldKinetics = getKinetics();
        String kinetics = kineticsField.getText().trim();
        if (kinetics.length() > 0 && oldKinetics.compareTo(kinetics) != 0) {
            try {
                reaction.setKinetics(kinetics);
                updateListModels();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    private String getReactionName() {
        if (reaction.getName() != null)
            return reaction.getName();
        else
            return "";
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel formulaLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JSplitPane jSplitPane2;
    private javax.swing.JSplitPane jSplitPane3;
    private javax.swing.JTextField k1Field;
    private javax.swing.JTextField k2Field;
    private javax.swing.JTextArea kineticsField;
    private javax.swing.JPanel pEditor;
    private javax.swing.JPanel pListPanel;
    private javax.swing.JList parameterList;
    private javax.swing.JPanel parameterPanel;
    private javax.swing.JPanel sEditor;
    private javax.swing.JPanel sListPanel;
    private javax.swing.JTextArea stoichiometryField;
    private javax.swing.JList substanceList;
    private javax.swing.JPanel substancePanel;

    // End of variables declaration//GEN-END:variables
    public static class Tester {
        public static void main(String[] args) {
            dynetica.reaction.MassAction ma = new dynetica.reaction.MassAction(
                    "TestReaction", new dynetica.system.ReactiveSystem("Test"));
            try {
                ma.setProperty("stoichiometry",
                        "A + B + C + D -> E + F + K + L");
                ma.setProperty("k1", "1.20");
                ma.setProperty("k2", "3.00");
                javax.swing.JFrame frame = new javax.swing.JFrame();

                frame.addWindowListener(new java.awt.event.WindowAdapter() {
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                frame.getContentPane().add(new MassActionEditor(ma));
                frame.pack();
                frame.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
