package dynetica.gui.plotting;

import dynetica.algorithm.*;
import dynetica.entity.*;
import dynetica.gui.ApplicationFrame;
import javax.swing.*;
import java.io.File;
import matlabcontrol.MatlabConnectionException;
import matlabcontrol.MatlabInvocationException;

/**
 * This is implemented to allow easy visualization of results from multiple
 * rounds of stochastic simulations.
 * 
 * @author lingchong
 */

public class RepeatedStochasticSimulationsWindow extends javax.swing.JFrame {
    InteractiveFigure figure = new InteractiveFigure();
    Histogram histogram = new Histogram();

    final DefaultListModel substanceNames = new DefaultListModel();
    final dynetica.system.ReactiveSystem system;
    RepeatedStochasticSimulations rssAlgorithm;

    int numberTraces = 1;
    double startTime = 0.0;
    double endTime = 100.0;

    FigureAxisModel figureAxisModel;

    double[] xValues;
    double[][] yValues;
    String xLabel;
    String[] yLabels;

    boolean showTimeCourses = true; // by default show multiple time courses for
                                    // each variable against the parameter.

    //
    // Constructs a FigureWindow that plots data directly from
    // a ReactiveSystem
    //
    public RepeatedStochasticSimulationsWindow(
            RepeatedStochasticSimulations algorithm) {
        super("Sensitivity Analysis For ReactiveSystem "
                + algorithm.getSystem().getName());
        this.rssAlgorithm = algorithm;
        this.system = algorithm.getSystem();
        endTime = rssAlgorithm.getTime();
        startTime = endTime / 2.0;

        java.util.List substances = algorithm.getSubstances();
        int nSubs = substances.size(); // substances.length;
        System.out.println("# of substances = " + nSubs);
        Substance s;
        for (int i = 0; i < nSubs; i++) {
            s = (Substance) (substances.get(i));
            substanceNames.addElement(s.getName());
        }
        initComponents();
        timeCoursesPanel.add(figure.getPlotPanel(),
                java.awt.BorderLayout.CENTER);
        plotTimeCourses();
        plotHistograms();

        pack();
    }

    public void setUpData() {
        java.util.List substances = rssAlgorithm.getSubstances();
        int nSubs = substances.size(); // substances.length;
        System.out.println("# of substances = " + nSubs);
        Substance s;
        for (int i = 0; i < nSubs; i++) {
            s = (Substance) (substances.get(i));
            substanceNames.addElement(s.getName());
        }
        timeCoursesPanel.setSize(400, 300);
    }

    private void plotTimeCourses() {
        // System.out.println("Plotting data");
        int[] indices = substanceList.getSelectedIndices();
        xLabel = "Time";
        xValues = rssAlgorithm.getTimePoints();
        yLabels = new String[indices.length * numberTraces];
        yValues = new double[indices.length * numberTraces][xValues.length];

        for (int j = 0; j < indices.length; j++) {
            int selectedIndex = indices[j];
            double[][] yValuesForOneSubstance = rssAlgorithm
                    .getTimeCourses(selectedIndex);
            for (int i = 0; i < numberTraces; i++) {
                int valueIndex = i + j * numberTraces;
                yValues[valueIndex] = yValuesForOneSubstance[i];

                if (indices.length > 1)
                    yLabels[valueIndex] = (String) (substanceNames
                            .get(selectedIndex));
            }

        }

        if (showTimeCourses)
            xLabel = "Time";
        if (indices.length == 1)
            yLabels = null; // suppress labeling if the variable is not changed.

        figure.plotData(xLabel, yLabels, xValues, yValues);
        figure.setLogX(logXItem.getState());
        figure.setLogY(logYItem.getState());
        timeCoursesPanel.repaint();
        if (figureAxisModel == null) {
            figureAxisModel = new FigureAxisModel();
            rangeTable.setModel(figureAxisModel);
        } else
            figureAxisModel.setFigure();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the FormEditor.
     */
    // <editor-fold defaultstate="collapsed"
    // desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator1 = new javax.swing.JSeparator();
        mainPane = new javax.swing.JSplitPane();
        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        substanceList = new javax.swing.JList();
        substanceListLabel = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        rangeTable = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        logXbox = new javax.swing.JCheckBox();
        logYbox = new javax.swing.JCheckBox();
        jSplitPane2 = new javax.swing.JSplitPane();
        histogramPanel = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        startTimeLabel = new javax.swing.JLabel();
        startTimeBar = new javax.swing.JSlider();
        endTimeLabel = new javax.swing.JLabel();
        endTimeBar = new javax.swing.JSlider();
        timeCoursesPanel = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        tracesLabel = new javax.swing.JLabel();
        numTracesBar = new javax.swing.JSlider();
        jMenuBar1 = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        saveSelectedDataItem = new javax.swing.JMenuItem();
        closeItem = new javax.swing.JMenuItem();
        exitItem = new javax.swing.JMenuItem();
        viewMenu = new javax.swing.JMenu();
        logXItem = new javax.swing.JCheckBoxMenuItem();
        logYItem = new javax.swing.JCheckBoxMenuItem();
        selectAllItem = new javax.swing.JMenuItem();
        matlabplotItem = new javax.swing.JMenuItem();
        histogramItem = new javax.swing.JMenuItem();

        setSize(new java.awt.Dimension(700, 350));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }
        });

        mainPane.setDividerLocation(110);
        mainPane.setLastDividerLocation(10);
        mainPane.setPreferredSize(new java.awt.Dimension(600, 300));

        jSplitPane1.setDividerLocation(200);
        jSplitPane1.setDividerSize(5);
        jSplitPane1.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        jSplitPane1.setLastDividerLocation(80);

        jPanel2.setLayout(new java.awt.BorderLayout());

        jScrollPane1.setMinimumSize(new java.awt.Dimension(60, 100));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(60, 200));

        substanceList.setModel(substanceNames);
        substanceList.setSelectedIndex(0);
        substanceList
                .addListSelectionListener(new javax.swing.event.ListSelectionListener() {
                    public void valueChanged(
                            javax.swing.event.ListSelectionEvent evt) {
                        substanceListValueChanged(evt);
                    }
                });
        jScrollPane1.setViewportView(substanceList);

        jPanel2.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        substanceListLabel.setText("Substances");
        jPanel2.add(substanceListLabel, java.awt.BorderLayout.NORTH);

        jSplitPane1.setTopComponent(jPanel2);

        jPanel3.setLayout(new java.awt.BorderLayout(0, 3));

        jLabel6.setText("Figure Configuration");
        jLabel6.setBorder(javax.swing.BorderFactory
                .createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabel6.setMaximumSize(new java.awt.Dimension(200, 18));
        jLabel6.setPreferredSize(new java.awt.Dimension(110, 18));
        jPanel3.add(jLabel6, java.awt.BorderLayout.NORTH);

        rangeTable.setBorder(javax.swing.BorderFactory.createLineBorder(null));
        rangeTable.setMaximumSize(new java.awt.Dimension(70, 100));
        rangeTable.setMinimumSize(new java.awt.Dimension(70, 64));
        rangeTable.setPreferredSize(new java.awt.Dimension(70, 64));
        rangeTable.setRowMargin(5);
        rangeTable.setRowSelectionAllowed(false);
        jPanel3.add(rangeTable, java.awt.BorderLayout.CENTER);

        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1,
                javax.swing.BoxLayout.Y_AXIS));

        logXbox.setText("Log Scale in X");
        logXbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logXboxActionPerformed(evt);
            }
        });
        jPanel1.add(logXbox);

        logYbox.setText("Log Scale in Y");
        logYbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logYboxActionPerformed(evt);
            }
        });
        jPanel1.add(logYbox);

        jPanel3.add(jPanel1, java.awt.BorderLayout.SOUTH);

        jSplitPane1.setBottomComponent(jPanel3);

        mainPane.setLeftComponent(jSplitPane1);

        jSplitPane2.setDividerLocation(300);
        jSplitPane2.setDividerSize(10);

        histogramPanel.setLayout(new java.awt.BorderLayout());

        startTimeLabel.setFont(new java.awt.Font("Lucida Grande", 0, 10)); // NOI18N
        startTimeLabel.setText("Start: "
                + dynetica.util.Numerics.displayFormattedValue(startTime));
        jPanel5.add(startTimeLabel);

        startTimeBar.setMaximum(rssAlgorithm.getIterations());
        startTimeBar.setPaintLabels(true);
        startTimeBar
                .setToolTipText("Adjust number of time points to be shown. This is done by truncating the time series at various start time and/or end time");
        startTimeBar.setValue((int) (Math.round(startTime
                * rssAlgorithm.getIterations() / rssAlgorithm.getTime())));
        startTimeBar.setMinimumSize(new java.awt.Dimension(36, 20));
        startTimeBar.setPreferredSize(new java.awt.Dimension(80, 20));
        startTimeBar.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                startTimeBarStateChanged(evt);
            }
        });
        jPanel5.add(startTimeBar);

        endTimeLabel.setFont(new java.awt.Font("Lucida Grande", 0, 10)); // NOI18N
        endTimeLabel.setText("End: "
                + dynetica.util.Numerics.displayFormattedValue(endTime));
        jPanel5.add(endTimeLabel);

        endTimeBar.setMaximum(rssAlgorithm.getIterations());
        endTimeBar.setMinimum((int) (Math.round(startTime
                * rssAlgorithm.getIterations() / rssAlgorithm.getTime())));
        endTimeBar
                .setToolTipText("Adjust number of time points to be shown. This is done by truncating the time series at various start time and/or end time");
        endTimeBar.setValue(rssAlgorithm.getIterations());
        endTimeBar.setMinimumSize(new java.awt.Dimension(36, 20));
        endTimeBar.setPreferredSize(new java.awt.Dimension(80, 20));
        endTimeBar.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                endTimeBarStateChanged(evt);
            }
        });
        jPanel5.add(endTimeBar);

        histogramPanel.add(jPanel5, java.awt.BorderLayout.NORTH);

        jSplitPane2.setRightComponent(histogramPanel);

        timeCoursesPanel.setLayout(new java.awt.BorderLayout());

        tracesLabel.setFont(new java.awt.Font("Lucida Grande", 0, 10)); // NOI18N
        tracesLabel.setText("Traces: " + numberTraces);
        jPanel4.add(tracesLabel);

        numTracesBar.setMaximum(rssAlgorithm.getRounds());
        numTracesBar.setToolTipText("Adjust number of traces to be shown.");
        numTracesBar.setValue(1);
        numTracesBar.setMinimumSize(new java.awt.Dimension(36, 20));
        numTracesBar.setPreferredSize(new java.awt.Dimension(80, 20));
        numTracesBar.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                numTracesBarStateChanged(evt);
            }
        });
        jPanel4.add(numTracesBar);

        timeCoursesPanel.add(jPanel4, java.awt.BorderLayout.NORTH);

        jSplitPane2.setLeftComponent(timeCoursesPanel);

        mainPane.setRightComponent(jSplitPane2);

        getContentPane().add(mainPane, java.awt.BorderLayout.CENTER);

        fileMenu.setText("File");

        saveSelectedDataItem.setText("Save Selected Data");
        saveSelectedDataItem
                .addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        saveSelectedDataItemActionPerformed(evt);
                    }
                });
        fileMenu.add(saveSelectedDataItem);

        closeItem.setText("Close");
        closeItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeItemActionPerformed(evt);
            }
        });
        fileMenu.add(closeItem);

        exitItem.setText("Exit");
        exitItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitItemActionPerformed(evt);
            }
        });
        fileMenu.add(exitItem);

        jMenuBar1.add(fileMenu);

        viewMenu.setText("View");

        logXItem.setText("Log Scale in X axis");
        logXItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logXItemActionPerformed(evt);
            }
        });
        viewMenu.add(logXItem);

        logYItem.setText("Log Scale in Y axis");
        logYItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logYItemActionPerformed(evt);
            }
        });
        viewMenu.add(logYItem);

        selectAllItem.setText("Select All");
        selectAllItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectAllItemActionPerformed(evt);
            }
        });
        viewMenu.add(selectAllItem);

        matlabplotItem.setText("Plot in Matlab");
        matlabplotItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                matlabplotItemActionPerformed(evt);
            }
        });
        viewMenu.add(matlabplotItem);

        histogramItem.setText("Show Histogram");
        histogramItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                histogramItemActionPerformed(evt);
            }
        });
        viewMenu.add(histogramItem);

        jMenuBar1.add(viewMenu);

        setJMenuBar(jMenuBar1);
    }// </editor-fold>//GEN-END:initComponents

    private void maxPointsSliderStateChanged(javax.swing.event.ChangeEvent evt) {// GEN-FIRST:event_maxPointsSliderStateChanged
    }// GEN-LAST:event_maxPointsSliderStateChanged

    private void logYboxActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_logYboxActionPerformed
        boolean state = logYbox.isSelected();
        logYItem.setSelected(state);
        figure.setLogY(state);
        timeCoursesPanel.repaint();
    }// GEN-LAST:event_logYboxActionPerformed

    private void logXboxActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_logXboxActionPerformed
        boolean state = logXbox.isSelected();
        logXItem.setSelected(state);
        figure.setLogX(state);
        timeCoursesPanel.repaint();
    }// GEN-LAST:event_logXboxActionPerformed

    private void selectAllItemActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_selectAllItemActionPerformed
        int[] allIndices = new int[substanceNames.size()];
        for (int i = 0; i < allIndices.length; i++)
            allIndices[i] = i;
        substanceList.setSelectedIndices(allIndices);
    }// GEN-LAST:event_selectAllItemActionPerformed

    private void saveSelectedDataItemActionPerformed(
            java.awt.event.ActionEvent evt) {// GEN-FIRST:event_saveSelectedDataItemActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        int returnVal = fileChooser.showSaveDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File output = fileChooser.getSelectedFile();
            figure.saveData(output);
        }

    }// GEN-LAST:event_saveSelectedDataItemActionPerformed

    private void exitItemActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_exitItemActionPerformed
        System.exit(0);
    }// GEN-LAST:event_exitItemActionPerformed

    private void closeItemActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_closeItemActionPerformed
        this.dispose();
    }// GEN-LAST:event_closeItemActionPerformed

    private void logXItemActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_logXItemActionPerformed
        figure.setLogX(logXItem.getState());
        logXbox.setSelected(logXItem.getState());
    }// GEN-LAST:event_logXItemActionPerformed

    private void logYItemActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_logYItemActionPerformed
        figure.setLogY(logYItem.getState());
    }// GEN-LAST:event_logYItemActionPerformed

    private void substanceListValueChanged(
            javax.swing.event.ListSelectionEvent evt) {// GEN-FIRST:event_substanceListValueChanged
        plotTimeCourses();
        plotHistograms();
    }// GEN-LAST:event_substanceListValueChanged

    /** Exit the Application */
    private void exitForm(java.awt.event.WindowEvent evt) {// GEN-FIRST:event_exitForm
        // autoUpdateThread = null;
        this.dispose();
    }// GEN-LAST:event_exitForm

    private void matlabplotItemActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_matlabplotItemActionPerformed
        try {
            figure.matlabPlot(xLabel, yLabels, xValues, yValues,
                    logXItem.isSelected(), logYItem.isSelected());
        }

        catch (MatlabConnectionException MCE) {
        }

        catch (MatlabInvocationException MIE) {
        }
        // TODO add your handling code here:
    }// GEN-LAST:event_matlabplotItemActionPerformed

    private void histogramItemActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_histogramItemActionPerformed
        //
        // experimental code
        //
        endTime = rssAlgorithm.getTime();
        startTime = endTime / 2.0;
        int index = (substanceList.getSelectedIndices())[0];
        String name = (String) (substanceNames.get(index));
        double[] aggregatedData = rssAlgorithm.getAggregatedDataPoints(name,
                startTime, endTime);
        ApplicationFrame frame = new ApplicationFrame("Histogram");
        frame.getContentPane().add(
                (new Histogram(aggregatedData, 20, name)).getPlotPanel());
        frame.pack();
        frame.show();

    }// GEN-LAST:event_histogramItemActionPerformed

    private void startTimeBarStateChanged(javax.swing.event.ChangeEvent evt) {// GEN-FIRST:event_startTimeBarStateChanged
        startTime = startTimeBar.getValue() * rssAlgorithm.getTime()
                / rssAlgorithm.getIterations();
        startTimeLabel.setText("Start:"
                + dynetica.util.Numerics.displayFormattedValue(startTime));
        plotHistograms();
    }// GEN-LAST:event_startTimeBarStateChanged

    private void endTimeBarStateChanged(javax.swing.event.ChangeEvent evt) {// GEN-FIRST:event_endTimeBarStateChanged
        endTime = endTimeBar.getValue() * rssAlgorithm.getTime()
                / rssAlgorithm.getIterations();
        endTimeLabel.setText("End:"
                + dynetica.util.Numerics.displayFormattedValue(endTime));
        plotHistograms();
    }// GEN-LAST:event_endTimeBarStateChanged

    private void numTracesBarStateChanged(javax.swing.event.ChangeEvent evt) {// GEN-FIRST:event_numTracesBarStateChanged
        numberTraces = numTracesBar.getValue();
        tracesLabel.setText("Traces:" + numberTraces);
        plotTimeCourses();
    }// GEN-LAST:event_numTracesBarStateChanged

    private void plotHistograms() {
        int index = (substanceList.getSelectedIndices())[0];
        String name = (String) (substanceNames.get(index));
        double[] aggregatedData = rssAlgorithm.getAggregatedDataPoints(name,
                startTime, endTime);

        if (histogram.getPlotPanel() == null) {
            histogram.setData(aggregatedData, 20, name);
            histogramPanel.add(histogram.getPlotPanel(),
                    java.awt.BorderLayout.CENTER);
        }

        //
        // if histogram has already been initiated before, just update data.
        else {
            histogram.setData(aggregatedData, 20, name);
        }
        //
        // update the graphics
        histogramPanel.repaint();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem closeItem;
    private javax.swing.JSlider endTimeBar;
    private javax.swing.JLabel endTimeLabel;
    private javax.swing.JMenuItem exitItem;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenuItem histogramItem;
    private javax.swing.JPanel histogramPanel;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JSplitPane jSplitPane2;
    private javax.swing.JCheckBoxMenuItem logXItem;
    private javax.swing.JCheckBox logXbox;
    private javax.swing.JCheckBoxMenuItem logYItem;
    private javax.swing.JCheckBox logYbox;
    private javax.swing.JSplitPane mainPane;
    private javax.swing.JMenuItem matlabplotItem;
    private javax.swing.JSlider numTracesBar;
    private javax.swing.JTable rangeTable;
    private javax.swing.JMenuItem saveSelectedDataItem;
    private javax.swing.JMenuItem selectAllItem;
    private javax.swing.JSlider startTimeBar;
    private javax.swing.JLabel startTimeLabel;
    private javax.swing.JList substanceList;
    private javax.swing.JLabel substanceListLabel;
    private javax.swing.JPanel timeCoursesPanel;
    private javax.swing.JLabel tracesLabel;
    private javax.swing.JMenu viewMenu;

    // End of variables declaration//GEN-END:variables

    class FigureAxisModel extends javax.swing.table.AbstractTableModel {
        final Object[][] data = { { "xmin", new Double(figure.getXmin()) },
                { "xmax", new Double(figure.getXmax()) },
                { "ymin", new Double(figure.getYmin()) },
                { "ymax", new Double(figure.getYmax()) } };

        public FigureAxisModel() {
        }

        public void setFigure() {
            data[0][1] = new Double(figure.getXmin());
            data[1][1] = new Double(figure.getXmax());
            data[2][1] = new Double(figure.getYmin());
            data[3][1] = new Double(figure.getYmax());

            for (int i = 0; i < 4; i++)
                fireTableCellUpdated(i, 1);
        }

        public int getColumnCount() {
            return data[0].length;
        }

        public String getColumnName(int c) {
            return null;
        }

        public int getRowCount() {
            return data.length;
        }

        public Object getValueAt(int row, int col) {
            return data[row][col];
        }

        public Class getColumnClass(int c) {
            return getValueAt(0, c).getClass();
        }

        public boolean isCellEditable(int row, int col) {
            return (col == 1);
        }

        public void setValueAt(Object value, int row, int col) {
            data[row][col] = value;
            fireTableCellUpdated(row, col);

            double newValue = ((Double) value).doubleValue();
            if (row == 0) {
                figure.setXmin(newValue);
            }

            else if (row == 1) {
                figure.setXmax(newValue);
            }

            else if (row == 2) {
                figure.setYmin(newValue);
            }

            else if (row == 3) {
                figure.setYmax(newValue);
            }

        }
    }

}