/*
 * ProgressiveReactionEditor.java
 *
 * Created on April 10, 2001, 2:07 AM
 */

package dynetica.gui.reactions;

/**
 *
 * @author Lingchong You
 * @version 0.5
 */

import javax.swing.*;
import dynetica.entity.*;
import dynetica.reaction.*;

public class MichaelisMentenReactionEditor extends javax.swing.JPanel {

    dynetica.reaction.MichaelisMentenReaction reaction;
    DefaultListModel substanceListModel = new DefaultListModel();
    DefaultListModel parameterListModel = new DefaultListModel();

    /** Creates new customizer ProgressiveReactionEditor */
    public MichaelisMentenReactionEditor(
            dynetica.reaction.MichaelisMentenReaction r) {
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
            sList.setSelectedIndex(0);

        if (!parameterListModel.isEmpty())
            parameterListModel.removeAllElements();
        Object[] paras = reaction.getParameters().toArray();
        for (int i = 0; i < paras.length; i++) {
            parameterListModel.addElement(((Entity) (paras[i])).getName());
        }

        if (!parameterListModel.isEmpty())
            pList.setSelectedIndex(0);
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
        jSplitPane2 = new javax.swing.JSplitPane();
        jSplitPane1 = new javax.swing.JSplitPane();
        labelPane = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        fieldPane = new javax.swing.JPanel();
        substrateField = new javax.swing.JTextField();
        productField = new javax.swing.JTextField();
        enzymeField = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        vmaxField = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        kmField = new javax.swing.JTextArea();
        jScrollPane5 = new javax.swing.JScrollPane();
        kineticsField = new javax.swing.JTextArea();
        jSplitPane3 = new javax.swing.JSplitPane();
        substancePanel = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        sList = new javax.swing.JList();
        jLabel7 = new javax.swing.JLabel();
        sEditor = new javax.swing.JPanel();
        parameterPanel = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        pList = new javax.swing.JList();
        jLabel8 = new javax.swing.JLabel();
        pEditor = new javax.swing.JPanel();

        setLayout(new java.awt.BorderLayout(0, 1));

        jLabel1.setText("<html> <i> Michaelis-Menten Reaction </i>"
                + reaction.getName());
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        add(jLabel1, java.awt.BorderLayout.NORTH);

        jSplitPane2.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        jSplitPane1.setDividerLocation(100);
        jSplitPane1.setDividerSize(1);

        labelPane.setPreferredSize(new java.awt.Dimension(120, 100));
        labelPane.setLayout(new java.awt.GridLayout(6, 1, 2, 2));

        jLabel2.setText("Substrate");
        jLabel2.setToolTipText("The name of the substrate for this reaction");
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        labelPane.add(jLabel2);

        jLabel3.setText("Product");
        jLabel3.setToolTipText("The name of the product.");
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        labelPane.add(jLabel3);

        jLabel4.setText("Enzyme");
        jLabel4.setToolTipText("The name of the enzyme");
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel4.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        labelPane.add(jLabel4);

        jLabel5.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel5.setText("Vmax");
        jLabel5.setToolTipText("The expression for the Vmax");
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel5.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        labelPane.add(jLabel5);

        jLabel6.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel6.setText("Km");
        jLabel6.setToolTipText("The expression for Km");
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel6.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        labelPane.add(jLabel6);

        jLabel9.setText("Kinetics");
        jLabel9.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        labelPane.add(jLabel9);

        jSplitPane1.setLeftComponent(labelPane);

        fieldPane.setLayout(new java.awt.GridLayout(6, 1, 2, 2));

        substrateField.setText(getSubstrateName());
        substrateField.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        substrateField.setPreferredSize(new java.awt.Dimension(100, 25));
        substrateField.setMaximumSize(new java.awt.Dimension(400, 25));
        substrateField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                substrateFieldActionPerformed(evt);
            }
        });
        substrateField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                substrateFieldFocusLost(evt);
            }
        });
        fieldPane.add(substrateField);

        productField.setText(getProductName());
        productField.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        productField.setPreferredSize(new java.awt.Dimension(100, 25));
        productField.setMaximumSize(new java.awt.Dimension(400, 25));
        productField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                productFieldActionPerformed(evt);
            }
        });
        productField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                productFieldFocusLost(evt);
            }
        });
        fieldPane.add(productField);

        enzymeField.setText(getEnzymeName());
        enzymeField.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        enzymeField.setPreferredSize(new java.awt.Dimension(100, 25));
        enzymeField.setMaximumSize(new java.awt.Dimension(400, 25));
        enzymeField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enzymeFieldActionPerformed(evt);
            }
        });
        enzymeField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                enzymeFieldFocusLost(evt);
            }
        });
        fieldPane.add(enzymeField);

        vmaxField.setWrapStyleWord(true);
        vmaxField.setLineWrap(true);
        vmaxField.setText(getVmax());
        vmaxField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                vmaxFieldFocusLost(evt);
            }
        });
        jScrollPane2.setViewportView(vmaxField);

        fieldPane.add(jScrollPane2);

        kmField.setWrapStyleWord(true);
        kmField.setLineWrap(true);
        kmField.setText(getKm());
        kmField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                kmFieldFocusLost(evt);
            }
        });
        jScrollPane3.setViewportView(kmField);

        fieldPane.add(jScrollPane3);

        kineticsField.setLineWrap(true);
        kineticsField.setEditable(false);
        kineticsField.setText(reaction.getKinetics());
        jScrollPane5.setViewportView(kineticsField);

        fieldPane.add(jScrollPane5);

        jSplitPane1.setRightComponent(fieldPane);

        jSplitPane2.setLeftComponent(jSplitPane1);

        jSplitPane3.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        substancePanel.setLayout(new java.awt.GridLayout(1, 2, 10, 0));

        jPanel2.setLayout(new java.awt.BorderLayout());

        sList.setModel(substanceListModel);
        sList.setVisibleRowCount(Math.min(8, substanceListModel.getSize()));
        sList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        sList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                sListValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(sList);

        jPanel2.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jLabel7.setText("Substances");
        jPanel2.add(jLabel7, java.awt.BorderLayout.NORTH);

        substancePanel.add(jPanel2);
        substancePanel.add(sEditor);

        jSplitPane3.setTopComponent(substancePanel);

        parameterPanel.setLayout(new java.awt.GridLayout(1, 2, 10, 0));

        jPanel5.setLayout(new java.awt.BorderLayout());

        pList.setModel(parameterListModel);
        pList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        pList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                pListValueChanged(evt);
            }
        });
        jScrollPane4.setViewportView(pList);

        jPanel5.add(jScrollPane4, java.awt.BorderLayout.CENTER);

        jLabel8.setText("Parameters");
        jPanel5.add(jLabel8, java.awt.BorderLayout.NORTH);

        parameterPanel.add(jPanel5);
        parameterPanel.add(pEditor);

        jSplitPane3.setBottomComponent(parameterPanel);

        jSplitPane2.setRightComponent(jSplitPane3);

        add(jSplitPane2, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private Parameter getCurrentParameter() {
        int index = pList.getSelectedIndex();
        // If condition added by Kanishk Asthana on 23 July 2013: See
        // pListvaluechanged for and explaination
        if (index != -1) {
            String pName = (String) (parameterListModel.get(index));
            return (Parameter) (reaction.getSystem().get(pName));
        } else
            return null;
    }

    private Substance getCurrentSubstance() {
        int index = sList.getSelectedIndex();
        // If condition added by Kanishk Asthana on 23 July 2013: See
        // pListvaluechanged for and explaination
        if (index != -1) {
            String substanceName = (String) (substanceListModel.get(index));
            return (Substance) (reaction.getSystem().get(substanceName));
        } else
            return null;
    }

    private void pListValueChanged(javax.swing.event.ListSelectionEvent evt) {// GEN-FIRST:event_pListValueChanged
        parameterPanel.remove(pEditor);
        // If condition added by Kanishk Asthana on 23 July 2013: To fix a
        // runtime error. This error
        // was caused whenever the method updatelistmodel() was executed.
        // This method executes everytime the parameterlist is to be updated; so
        // whenever updatelistmodels()
        // was executed all elements were removed from the plist. Since this
        // removal of elements triggered
        // a pListvaluechanged event; the event led to this method being
        // executed. This method would then try to get
        // the current parameter. However since the parameter list was already
        // empty excecution of this method
        // would lead to the Editor misbehaving(runtime error)!
        // Same logic applies for the changes to slistmodel as well.
        if (getCurrentParameter() != null) {
            pEditor = getCurrentParameter().editor();
            parameterPanel.add(pEditor);
            parameterPanel.validate();
        }

    }// GEN-LAST:event_pListValueChanged

    private void sListValueChanged(javax.swing.event.ListSelectionEvent evt) {// GEN-FIRST:event_sListValueChanged

        substancePanel.remove(sEditor);
        // If condition added by Kanishk Asthana on 23 July 2013: See
        // pListvaluechanged for and explaination
        if (getCurrentSubstance() != null) {
            sEditor = getCurrentSubstance().editor();
            substancePanel.add(sEditor);
            substancePanel.validate();
        }
    }// GEN-LAST:event_sListValueChanged

    private void vmaxFieldFocusLost(java.awt.event.FocusEvent evt) {// GEN-FIRST:event_vmaxFieldFocusLost
        setVmax();
    }// GEN-LAST:event_vmaxFieldFocusLost

    private void kmFieldFocusLost(java.awt.event.FocusEvent evt) {// GEN-FIRST:event_kmFieldFocusLost
        setKm();
    }// GEN-LAST:event_kmFieldFocusLost

    private void enzymeFieldFocusLost(java.awt.event.FocusEvent evt) {// GEN-FIRST:event_enzymeFieldFocusLost
        setEnzyme();
    }// GEN-LAST:event_enzymeFieldFocusLost

    private void productFieldFocusLost(java.awt.event.FocusEvent evt) {// GEN-FIRST:event_productFieldFocusLost
        setProduct();
    }// GEN-LAST:event_productFieldFocusLost

    private void substrateFieldFocusLost(java.awt.event.FocusEvent evt) {// GEN-FIRST:event_substrateFieldFocusLost
        setSubstrate();
    }// GEN-LAST:event_substrateFieldFocusLost

    private void enzymeFieldActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_enzymeFieldActionPerformed
        setEnzyme();
    }// GEN-LAST:event_enzymeFieldActionPerformed

    private void productFieldActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_productFieldActionPerformed
        setProduct();
    }// GEN-LAST:event_productFieldActionPerformed

    private void substrateFieldActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_substrateFieldActionPerformed
        setSubstrate();
    }// GEN-LAST:event_substrateFieldActionPerformed

    private String getReactionName() {
        return reaction.getName();
    }

    private String getSubstrateName() {
        if (reaction.getSubstrate() != null)
            return reaction.getSubstrate().getName();
        else
            return "";
    }

    private String getProductName() {
        if (reaction.getProduct() != null)
            return reaction.getProduct().getName();
        else
            return "";
    }

    private String getEnzymeName() {
        if (reaction.getEnzyme() != null)
            return reaction.getEnzyme().getName();
        else
            return "";
    }

    private String getKm() {
        if (reaction.getKm() != null)
            return reaction.getKm().toString();
        else
            return "";
    }

    private String getVmax() {
        if (reaction.getVmax() != null)
            return reaction.getVmax().toString();
        else
            return "";
    }

    private void setKm() {
        String kmString = kmField.getText().trim();
        String oldKmString = getKm();
        if (kmString.length() > 0 && kmString.compareTo(oldKmString) != 0) {
            try {
                reaction.setKm(kmString);
                updateListModels();
                kineticsField.setText(reaction.getKinetics());
            }

            catch (Exception e) {
                System.out.println(e);
            }

        }
    }

    private void setVmax() {
        String vmaxString = vmaxField.getText().trim();
        String oldVmaxString = getVmax();
        if (vmaxString.length() > 0 && vmaxString.compareTo(oldVmaxString) != 0) {
            try {
                reaction.setVmax(vmaxString);
                updateListModels();
                kineticsField.setText(reaction.getKinetics());
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    private void setSubstrate() {
        String substrateName = substrateField.getText().trim();
        String oldSubstrateName = getSubstrateName();
        if (substrateName.length() > 0
                && substrateName.compareTo(oldSubstrateName) != 0) {
            if (reaction.getSystem().contains(substrateName))
                reaction.setSubstrate(reaction.getSystem().getSubstance(
                        substrateName));
            else {
                dynetica.entity.Substance s = new dynetica.entity.Substance(
                        substrateName, reaction.getSystem());
                reaction.setSubstrate(s);
            }
            updateListModels();
            kineticsField.setText(reaction.getKinetics());
        }

    }

    private void setProduct() {
        String productName = productField.getText().trim();
        String oldProductName = getProductName();
        if (productName.length() > 0
                && productName.compareTo(oldProductName) != 0) {
            if (reaction.getSystem().contains(productName))
                reaction.setProduct(reaction.getSystem().getSubstance(
                        productName));
            else {
                dynetica.entity.Substance s = new dynetica.entity.Substance(
                        productName, reaction.getSystem());
                reaction.setProduct(s);
            }
            updateListModels();
            kineticsField.setText(reaction.getKinetics());
        }

    }

    private void setEnzyme() {
        String enzymeName = enzymeField.getText().trim();
        String oldEnzymeName = getEnzymeName();
        if (enzymeName.length() > 0 && enzymeName.compareTo(oldEnzymeName) != 0) {
            if (reaction.getSystem().contains(enzymeName))
                reaction.setEnzyme(reaction.getSystem()
                        .getSubstance(enzymeName));
            else {
                dynetica.entity.Substance s = new dynetica.entity.Substance(
                        enzymeName, reaction.getSystem());
                reaction.setEnzyme(s);
            }
            updateListModels();
            kineticsField.setText(reaction.getKinetics());
        }

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField enzymeField;
    private javax.swing.JPanel fieldPane;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JSplitPane jSplitPane2;
    private javax.swing.JSplitPane jSplitPane3;
    private javax.swing.JTextArea kineticsField;
    private javax.swing.JTextArea kmField;
    private javax.swing.JPanel labelPane;
    private javax.swing.JPanel pEditor;
    private javax.swing.JList pList;
    private javax.swing.JPanel parameterPanel;
    private javax.swing.JTextField productField;
    private javax.swing.JPanel sEditor;
    private javax.swing.JList sList;
    private javax.swing.JPanel substancePanel;
    private javax.swing.JTextField substrateField;
    private javax.swing.JTextArea vmaxField;
    // End of variables declaration//GEN-END:variables

}
