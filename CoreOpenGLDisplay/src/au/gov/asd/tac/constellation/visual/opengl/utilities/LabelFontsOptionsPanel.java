/*
 * Copyright 2010-2019 Australian Signals Directorate
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package au.gov.asd.tac.constellation.visual.opengl.utilities;

import au.gov.asd.tac.constellation.utilities.gui.JNumberedTextArea;
import au.gov.asd.tac.constellation.visual.opengl.utilities.glyphs.ConstellationLabelFonts;
import au.gov.asd.tac.constellation.visual.opengl.utilities.glyphs.FontInfo;
import au.gov.asd.tac.constellation.visual.opengl.utilities.glyphs.FontStyle;
import au.gov.asd.tac.constellation.visual.opengl.utilities.glyphs.GlyphManagerBI;
import java.awt.Color;
import java.awt.Component;
import java.util.Arrays;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import org.openide.util.HelpCtx;

/**
 * An options panel allowing customisation of label fonts.
 *
 * @author algol
 * @author cygnus_x-1
 */
final class LabelFontsOptionsPanel extends javax.swing.JPanel {

    private final LabelFontsOptionsPanelController controller;

    LabelFontsOptionsPanel(LabelFontsOptionsPanelController controller) {
        this.controller = controller;
        initComponents();

        final JNumberedTextArea numberedTextArea = new JNumberedTextArea(fontListTextArea);
        fontListScrollPane.setRowHeaderView(numberedTextArea);

        final String[] styles = Arrays.stream(FontStyle.class.getEnumConstants())
                .map(s -> s.name())
                .toArray(String[]::new);
        addStyleComboBox.setModel(new DefaultComboBoxModel<>(styles));

        final String[] scripts = Arrays.stream(Character.UnicodeScript.class.getEnumConstants())
                .map(s -> s.name())
                .sorted()
                .toArray(String[]::new);
        Arrays.sort(scripts);
        addScriptComboBox.setModel(new DefaultComboBoxModel<>(scripts));

        fontListTextArea.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(final DocumentEvent e) {
                numberedTextArea.updateLineNumbers();
            }

            @Override
            public void removeUpdate(final DocumentEvent e) {
                numberedTextArea.updateLineNumbers();
            }

            @Override
            public void changedUpdate(final DocumentEvent e) {
                numberedTextArea.updateLineNumbers();
            }
        });
        fontListTextArea.setCaretPosition(0);
    }

    private void setLabelFontsPanelEnabled(final boolean useDefaultSettings) {
        for (final Component c : labelFontsPanel.getComponents()) {
            if (c instanceof JScrollPane) {
                ((JScrollPane) c).getViewport().getView().setEnabled(useDefaultSettings);
            } else {
                c.setEnabled(useDefaultSettings);
            }
        }
    }

    boolean getUseDefaultSettings() {
        return useDefaultCheckBox.isSelected();
    }

    void setUseDefaultSettings(final boolean useDefaultSettings) {
        useDefaultCheckBox.setSelected(useDefaultSettings);
        if (useDefaultSettings) {
            final ConstellationLabelFonts defaultFonts = ConstellationLabelFonts.getDefault();
            setUseMultiFonts(defaultFonts.getUseMultiFonts());
            setFontList(defaultFonts.getFontListString());
        }
        setLabelFontsPanelEnabled(!useDefaultSettings);
    }

    public boolean getUseMultiFonts() {
        return useMultiFontCheckBox.isSelected();
    }

    public void setUseMultiFonts(final boolean useMultiFonts) {
        useMultiFontCheckBox.setSelected(useMultiFonts);
    }

    public String getFontList() {
        return fontListTextArea.getText().trim();
    }

    public void setFontList(final String fontList) {
        fontListTextArea.setText(fontList);
    }

    public void setAvailableFonts(final String[] availableFonts) {
        addFontComboBox.setModel(new DefaultComboBoxModel<>(availableFonts));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        useDefaultCheckBox = new javax.swing.JCheckBox();
        labelFontsPanel = new javax.swing.JPanel();
        fontListScrollPane = new javax.swing.JScrollPane();
        fontListTextArea = new javax.swing.JTextArea();
        addFontLabel = new javax.swing.JLabel();
        addFontComboBox = new javax.swing.JComboBox<>();
        addFontButton = new javax.swing.JButton();
        addScriptLabel = new javax.swing.JLabel();
        addScriptComboBox = new javax.swing.JComboBox<>();
        validateButton = new javax.swing.JButton();
        useMultiFontCheckBox = new javax.swing.JCheckBox();
        validateLabel = new javax.swing.JLabel();
        addStyleComboBox = new javax.swing.JComboBox<>();
        addStyleLabel = new javax.swing.JLabel();
        addStyleButton = new javax.swing.JButton();
        addScriptButton = new javax.swing.JButton();
        warningLabel = new javax.swing.JLabel();

        useDefaultCheckBox.setSelected(true);
        org.openide.awt.Mnemonics.setLocalizedText(useDefaultCheckBox, org.openide.util.NbBundle.getMessage(LabelFontsOptionsPanel.class, "LabelFontsOptionsPanel.useDefaultCheckBox.text")); // NOI18N
        useDefaultCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                useDefaultCheckBoxActionPerformed(evt);
            }
        });

        labelFontsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(LabelFontsOptionsPanel.class, "LabelFontsOptionsPanel.labelFontsPanel.border.title"))); // NOI18N

        fontListTextArea.setColumns(20);
        fontListTextArea.setRows(5);
        fontListTextArea.setText(org.openide.util.NbBundle.getMessage(LabelFontsOptionsPanel.class, "LabelFontsOptionsPanel.fontListTextArea.text")); // NOI18N
        fontListScrollPane.setViewportView(fontListTextArea);

        addFontLabel.setLabelFor(addFontButton);
        org.openide.awt.Mnemonics.setLocalizedText(addFontLabel, org.openide.util.NbBundle.getMessage(LabelFontsOptionsPanel.class, "LabelFontsOptionsPanel.addFontLabel.text")); // NOI18N

        addFontComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        org.openide.awt.Mnemonics.setLocalizedText(addFontButton, org.openide.util.NbBundle.getMessage(LabelFontsOptionsPanel.class, "LabelFontsOptionsPanel.addFontButton.text")); // NOI18N
        addFontButton.setActionCommand(org.openide.util.NbBundle.getMessage(LabelFontsOptionsPanel.class, "LabelFontsOptionsPanel.addFontButton.actionCommand")); // NOI18N
        addFontButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addFontButtonActionPerformed(evt);
            }
        });

        addScriptLabel.setLabelFor(addScriptButton);
        org.openide.awt.Mnemonics.setLocalizedText(addScriptLabel, org.openide.util.NbBundle.getMessage(LabelFontsOptionsPanel.class, "LabelFontsOptionsPanel.addScriptLabel.text")); // NOI18N

        addScriptComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        org.openide.awt.Mnemonics.setLocalizedText(validateButton, org.openide.util.NbBundle.getMessage(LabelFontsOptionsPanel.class, "LabelFontsOptionsPanel.validateButton.text")); // NOI18N
        validateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                validateButtonActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(useMultiFontCheckBox, org.openide.util.NbBundle.getMessage(LabelFontsOptionsPanel.class, "LabelFontsOptionsPanel.useMultiFontCheckBox.text")); // NOI18N

        validateLabel.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        validateLabel.setForeground(new java.awt.Color(153, 153, 153));
        validateLabel.setLabelFor(validateButton);
        org.openide.awt.Mnemonics.setLocalizedText(validateLabel, org.openide.util.NbBundle.getMessage(LabelFontsOptionsPanel.class, "LabelFontsOptionsPanel.validateLabel.text")); // NOI18N

        addStyleComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        addStyleLabel.setLabelFor(addScriptButton);
        org.openide.awt.Mnemonics.setLocalizedText(addStyleLabel, org.openide.util.NbBundle.getMessage(LabelFontsOptionsPanel.class, "LabelFontsOptionsPanel.addStyleLabel.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(addStyleButton, org.openide.util.NbBundle.getMessage(LabelFontsOptionsPanel.class, "LabelFontsOptionsPanel.addStyleButton.text")); // NOI18N
        addStyleButton.setActionCommand(org.openide.util.NbBundle.getMessage(LabelFontsOptionsPanel.class, "LabelFontsOptionsPanel.addStyleButton.actionCommand")); // NOI18N
        addStyleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addStyleButtonActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(addScriptButton, org.openide.util.NbBundle.getMessage(LabelFontsOptionsPanel.class, "LabelFontsOptionsPanel.addScriptButton.text")); // NOI18N
        addScriptButton.setActionCommand(org.openide.util.NbBundle.getMessage(LabelFontsOptionsPanel.class, "LabelFontsOptionsPanel.addScriptButton.actionCommand")); // NOI18N
        addScriptButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addScriptButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout labelFontsPanelLayout = new javax.swing.GroupLayout(labelFontsPanel);
        labelFontsPanel.setLayout(labelFontsPanelLayout);
        labelFontsPanelLayout.setHorizontalGroup(
            labelFontsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(labelFontsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(labelFontsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fontListScrollPane)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, labelFontsPanelLayout.createSequentialGroup()
                        .addGroup(labelFontsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(labelFontsPanelLayout.createSequentialGroup()
                                .addComponent(useMultiFontCheckBox)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(validateButton))
                            .addGroup(labelFontsPanelLayout.createSequentialGroup()
                                .addComponent(addFontLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(addFontComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(addFontButton, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(addStyleLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(addStyleComboBox, 0, 90, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(addStyleButton)
                                .addGap(18, 18, 18)
                                .addComponent(addScriptLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(labelFontsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(labelFontsPanelLayout.createSequentialGroup()
                                        .addComponent(addScriptComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(addScriptButton, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(validateLabel, javax.swing.GroupLayout.Alignment.TRAILING))))
                        .addGap(4, 4, 4)))
                .addContainerGap())
        );
        labelFontsPanelLayout.setVerticalGroup(
            labelFontsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(labelFontsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(fontListScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(labelFontsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(validateButton)
                    .addComponent(useMultiFontCheckBox))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(validateLabel)
                .addGap(18, 18, 18)
                .addGroup(labelFontsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addScriptButton)
                    .addComponent(addFontLabel)
                    .addComponent(addFontComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addStyleLabel)
                    .addComponent(addStyleComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addScriptLabel)
                    .addComponent(addScriptComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addFontButton)
                    .addComponent(addStyleButton))
                .addContainerGap())
        );

        warningLabel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        warningLabel.setForeground(new java.awt.Color(255, 0, 0));
        warningLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/au/gov/asd/tac/constellation/visual/opengl/utilities/resources/warning.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(warningLabel, org.openide.util.NbBundle.getMessage(LabelFontsOptionsPanel.class, "LabelFontsOptionsPanel.warningLabel.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelFontsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(useDefaultCheckBox)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 151, Short.MAX_VALUE)
                        .addComponent(warningLabel)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(useDefaultCheckBox)
                    .addComponent(warningLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelFontsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void useDefaultCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_useDefaultCheckBoxActionPerformed
        setUseDefaultSettings(useDefaultCheckBox.isSelected());
    }//GEN-LAST:event_useDefaultCheckBoxActionPerformed

    private void validateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_validateButtonActionPerformed
        final String text = fontListTextArea.getText();
        final FontInfo.ParsedFontInfo pfi = FontInfo.parseFontInfo(text.split("\n"), GlyphManagerBI.DEFAULT_FONT_SIZE);
        if (pfi.messages.isEmpty()) {
            validateLabel.setText("OK");
            validateLabel.setForeground(Color.GREEN.darker());
        } else {
            validateLabel.setText(pfi.messages.get(0));
            validateLabel.setForeground(Color.RED.darker());
        }
    }//GEN-LAST:event_validateButtonActionPerformed

    private void addFontButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addFontButtonActionPerformed
        final String fontName = (String) addFontComboBox.getSelectedItem();
        fontListTextArea.insert(String.format("%s", fontName), fontListTextArea.getCaretPosition());
    }//GEN-LAST:event_addFontButtonActionPerformed

    private void addStyleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addStyleButtonActionPerformed
        final String styleName = (String) addStyleComboBox.getSelectedItem();
        fontListTextArea.insert(String.format(",%s", styleName), fontListTextArea.getCaretPosition());
    }//GEN-LAST:event_addStyleButtonActionPerformed

    private void addScriptButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addScriptButtonActionPerformed
        final String scriptName = (String) addScriptComboBox.getSelectedItem();
        fontListTextArea.insert(String.format(",%s", scriptName), fontListTextArea.getCaretPosition());
    }//GEN-LAST:event_addScriptButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addFontButton;
    private javax.swing.JComboBox<String> addFontComboBox;
    private javax.swing.JLabel addFontLabel;
    private javax.swing.JButton addScriptButton;
    private javax.swing.JComboBox<String> addScriptComboBox;
    private javax.swing.JLabel addScriptLabel;
    private javax.swing.JButton addStyleButton;
    private javax.swing.JComboBox<String> addStyleComboBox;
    private javax.swing.JLabel addStyleLabel;
    private javax.swing.JScrollPane fontListScrollPane;
    private javax.swing.JTextArea fontListTextArea;
    private javax.swing.JPanel labelFontsPanel;
    private javax.swing.JCheckBox useDefaultCheckBox;
    private javax.swing.JCheckBox useMultiFontCheckBox;
    private javax.swing.JButton validateButton;
    private javax.swing.JLabel validateLabel;
    private javax.swing.JLabel warningLabel;
    // End of variables declaration//GEN-END:variables
}
