/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ija.project.gui;

import java.awt.Color;
import java.io.File;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;

/**
 *
 * @author simon
 */
public class PoPupFrame extends javax.swing.JFrame {

    /**
     * Creates new form PoPupFrame
     */
    public PoPupFrame() {
        initComponents();
        setLocationRelativeTo(null);
        fill();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextArea1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTextArea1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    private void fill()
    {
        File folder = new File(Paths.get("").toAbsolutePath().toString() + "/data/");
        File[] listOfFiles = folder.listFiles();
        
        for (File listOfFile : listOfFiles) 
            this.jTextArea1.append(listOfFile.getName() + "\n");
    }
    
    private void jTextArea1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextArea1MouseClicked
        try {
                int line = this.jTextArea1.getLineOfOffset( this.jTextArea1.getCaretPosition());
                
                int start = this.jTextArea1.getLineStartOffset( line );
                int end = this.jTextArea1.getLineEndOffset( line );
                
                DefaultHighlighter highlighter =  (DefaultHighlighter)this.jTextArea1.getHighlighter();
                DefaultHighlighter.DefaultHighlightPainter painter = new DefaultHighlighter.DefaultHighlightPainter( Color.CYAN );
                highlighter.setDrawsLayeredHighlights(false);

                highlighter.addHighlight(start, end, painter);
                
                name = this.jTextArea1.getDocument().getText(start, end - start);
                
            } catch (BadLocationException ex) {
                Logger.getLogger(MainJPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        wait = false;
        this.setVisible(false);
    }//GEN-LAST:event_jTextArea1MouseClicked

    private String name;
    public boolean wait = true;

    public String getName() {
        return name;
    }

    
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}
