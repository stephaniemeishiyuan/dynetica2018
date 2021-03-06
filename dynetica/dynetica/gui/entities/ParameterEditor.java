/*
 * ParameterEditor.java
 *
 * Created on April 10, 2001, 1:22 AM
 */

package dynetica.gui.entities;

/**
 * 
 * @author Lingchong You
 * @version
 */
public class ParameterEditor extends javax.swing.JPanel {

    dynetica.entity.Parameter parameter;

    /** Creates new customizer ParameterCustomizer */
    public ParameterEditor(dynetica.entity.Parameter p) {
        parameter = p;
        initComponents();
       //6/2016. Added by LY to enable copy/cut/paste in Dynetica.
        dynetica.gui.DyneticaGUITools.installContextMenu(this);               
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the FormEditor.
     */
    // <editor-fold defaultstate="collapsed"
    // desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jSplitPane2 = new javax.swing.JSplitPane();
        jSplitPane1 = new javax.swing.JSplitPane();
        labelPanel = new javax.swing.JPanel();
        valueLabel = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        fieldPanel = new javax.swing.JPanel();
        valueField = new javax.swing.JTextField();
        minField = new javax.swing.JTextField();
        maxField = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        annotationArea = new javax.swing.JTextArea();

        setMaximumSize(new java.awt.Dimension(100000, 1000000));
        setMinimumSize(new java.awt.Dimension(106, 100));
        setPreferredSize(new java.awt.Dimension(250, 270));
        setLayout(new java.awt.BorderLayout(0, 1));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("<html> <i> Parameter </i>" + parameter.getName());
        jLabel2.setMaximumSize(new java.awt.Dimension(176, 30));
        jLabel2.setMinimumSize(new java.awt.Dimension(176, 30));
        jLabel2.setPreferredSize(new java.awt.Dimension(176, 30));
        add(jLabel2, java.awt.BorderLayout.NORTH);

        jSplitPane2.setDividerLocation(100);
        jSplitPane2.setDividerSize(3);
        jSplitPane2.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        jSplitPane2.setPreferredSize(new java.awt.Dimension(245, 250));

        jSplitPane1.setDividerLocation(100);
        jSplitPane1.setDividerSize(1);
        jSplitPane1.setPreferredSize(new java.awt.Dimension(243, 100));

        labelPanel.setLayout(new java.awt.GridLayout(3, 1, 1, 1));

        valueLabel.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        valueLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        valueLabel.setText("Value");
        valueLabel.setMaximumSize(new java.awt.Dimension(100, 25));
        valueLabel.setMinimumSize(new java.awt.Dimension(100, 25));
        valueLabel.setPreferredSize(new java.awt.Dimension(100, 25));
        labelPanel.add(valueLabel);

        jLabel3.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setText("Minimum value");
        jLabel3.setToolTipText("The minimum value of a parameter");
        jLabel3.setMaximumSize(new java.awt.Dimension(100, 25));
        jLabel3.setMinimumSize(new java.awt.Dimension(100, 25));
        jLabel3.setPreferredSize(new java.awt.Dimension(100, 25));
        labelPanel.add(jLabel3);

        jLabel4.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel4.setText("Maximum value");
        jLabel4.setToolTipText("The maximum value of the parameter.");
        jLabel4.setMaximumSize(new java.awt.Dimension(100, 25));
        jLabel4.setMinimumSize(new java.awt.Dimension(100, 25));
        jLabel4.setPreferredSize(new java.awt.Dimension(100, 25));
        labelPanel.add(jLabel4);

        jSplitPane1.setLeftComponent(labelPanel);

        fieldPanel.setMaximumSize(new java.awt.Dimension(150, 100));
        fieldPanel.setLayout(new java.awt.GridLayout(3, 1, 1, 1));

        valueField.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        valueField.setText(String.valueOf(parameter.getValue()));
        valueField.setMaximumSize(new java.awt.Dimension(150, 25));
        valueField.setMinimumSize(new java.awt.Dimension(106, 25));
        valueField.setPreferredSize(new java.awt.Dimension(106, 25));
        valueField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                valueFieldActionPerformed(evt);
            }
        });
        valueField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                valueFieldFocusLost(evt);
            }
        });
        fieldPanel.add(valueField);

        minField.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        minField.setText(String.valueOf(parameter.getMin()));
        minField.setMaximumSize(new java.awt.Dimension(150, 25));
        minField.setMinimumSize(new java.awt.Dimension(106, 25));
        minField.setPreferredSize(new java.awt.Dimension(106, 25));
        minField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                minFieldActionPerformed(evt);
            }
        });
        minField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                minFieldFocusLost(evt);
            }
        });
        fieldPanel.add(minField);

        maxField.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        maxField.setText(String.valueOf(parameter.getMax()));
        maxField.setMaximumSize(new java.awt.Dimension(150, 25));
        maxField.setMinimumSize(new java.awt.Dimension(106, 25));
        maxField.setPreferredSize(new java.awt.Dimension(106, 25));
        maxField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                maxFieldActionPerformed(evt);
            }
        });
        maxField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                maxFieldFocusLost(evt);
            }
        });
        fieldPanel.add(maxField);

        jSplitPane1.setRightComponent(fieldPanel);

        jSplitPane2.setLeftComponent(jSplitPane1);

        jScrollPane1.setViewportBorder(javax.swing.BorderFactory
                .createTitledBorder("Annotation"));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(103, 80));

        annotationArea.setLineWrap(true);
        annotationArea.setText(parameter.getAnnotation());
        annotationArea.setWrapStyleWord(true);
        annotationArea
                .setBorder(javax.swing.BorderFactory.createEtchedBorder());
        annotationArea.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                annotationAreaFocusLost(evt);
            }
        });
        jScrollPane1.setViewportView(annotationArea);

        jSplitPane2.setRightComponent(jScrollPane1);

        add(jSplitPane2, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void annotationAreaFocusLost(java.awt.event.FocusEvent evt) {// GEN-FIRST:event_annotationAreaFocusLost
        setAnnotation();
    }// GEN-LAST:event_annotationAreaFocusLost

    private void valueFieldFocusLost(java.awt.event.FocusEvent evt) {// GEN-FIRST:event_valueFieldFocusLost
        setValue();
    }// GEN-LAST:event_valueFieldFocusLost

    private void maxFieldFocusLost(java.awt.event.FocusEvent evt) {// GEN-FIRST:event_maxFieldFocusLost
        setMax();
    }// GEN-LAST:event_maxFieldFocusLost

    private void minFieldFocusLost(java.awt.event.FocusEvent evt) {// GEN-FIRST:event_minFieldFocusLost
        setMin();
    }// GEN-LAST:event_minFieldFocusLost

    private void valueFieldHierarchyChanged(java.awt.event.HierarchyEvent evt) {// GEN-FIRST:event_valueFieldHierarchyChanged
        setValue();
    }// GEN-LAST:event_valueFieldHierarchyChanged

    private void maxFieldActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_maxFieldActionPerformed
        setMax();
    }// GEN-LAST:event_maxFieldActionPerformed

    private void minFieldActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_minFieldActionPerformed
        setMin();
    }// GEN-LAST:event_minFieldActionPerformed

    private void valueFieldActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_valueFieldActionPerformed
        setValue();
    }// GEN-LAST:event_valueFieldActionPerformed

    private void setAnnotation() {
        String newAnnotation = annotationArea.getText();
        String oldOne = parameter.getAnnotation();
        if (oldOne.compareTo(newAnnotation) != 0) {
            parameter.getSystem().fireSystemStateChange();
            parameter.setAnnotation(newAnnotation);
        }
    }

    private void setMax() {
        double oldValue = parameter.getMax();
        double newValue = Double.parseDouble(maxField.getText());
        if (oldValue != newValue) {
            parameter.setMax(newValue);
        }
    }

    private void setMin() {
        double oldValue = parameter.getMin();
        double newValue = Double.parseDouble(minField.getText());
        if (oldValue != newValue) {
            parameter.setMin(newValue);
        }
    }

    private void setValue() {
        double oldValue = parameter.getValue();
        double newValue = Double.parseDouble(valueField.getText());
        if (oldValue != newValue) {
            parameter.setValue(newValue);
            parameter.setDefaultValue(newValue);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea annotationArea;
    private javax.swing.JPanel fieldPanel;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JSplitPane jSplitPane2;
    private javax.swing.JPanel labelPanel;
    private javax.swing.JTextField maxField;
    private javax.swing.JTextField minField;
    private javax.swing.JTextField valueField;
    private javax.swing.JLabel valueLabel;
    // End of variables declaration//GEN-END:variables

}
