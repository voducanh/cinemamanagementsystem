/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * HistoricalDatabase.java
 *
 * Created on 16-Feb-2009, 23:36:39
 */

package cinemacontroller.gui;

import cinemacontroller.filmcontroller.Film;
import cinemacontroller.historicalsystem.HistoricalController;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Scott
 */
public class HistoricalDatabase extends javax.swing.JFrame {

    private HistoricalController historical_controller;

    /** Creates new form HistoricalDatabase */
    public HistoricalDatabase() {
        // Setup a new historical controller to access historical records
        this.historical_controller = new HistoricalController();
        // Load the current window's controls
        this.initComponents();
        // Load the historical data into the current window
        this.LoadHistoricalData();
    }

    public void LoadHistoricalData(){

        // Set the historical table's model to that of a default table model
        DefaultTableModel model = (DefaultTableModel) this.jTable1.getModel();

        // Create a new arraylist to store all the films
        ArrayList<Film> list_of_films = this.historical_controller.getAllFilms();


        // Alert the user if there are no films to show
        if(list_of_films.size() < 1){
            JOptionPane.showMessageDialog(null, "There are current no historical records to show.", "No Records Available", JOptionPane.WARNING_MESSAGE);
            this.jButton1.setEnabled(false);
        }else{
            this.jButton1.setEnabled(true);
        }

       // Cycle through all the films, create a new row on table and add details for film
       for(Film current_film : list_of_films){
            model.addRow(new Object []{" " + current_film.getTitle(), " " + current_film.getDirector(), " " + current_film.getBBFCRating(), " " + current_film.getAvaliabilityDate().toString()});
       }

    }
    
























    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextPane_date_description = new javax.swing.JTextPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Historical Data Archives");
        setIconImage(getToolkit().getImage(getClass().getResource("/cinemacontroller/gui/icons/hourglass.png")));

        jPanel2.setBackground(new java.awt.Color(24, 24, 24));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Historical Database");

        jScrollPane3.setBorder(null);

        jTextPane_date_description.setBackground(new java.awt.Color(24, 24, 24));
        jTextPane_date_description.setEditable(false);
        jTextPane_date_description.setForeground(new java.awt.Color(255, 255, 255));
        jTextPane_date_description.setText("This window contains all the previous films that have been stored in the cinema management system's database. \n\n\nTip: You can view additional data by selecting a film from the list.");
        jScrollPane3.setViewportView(jTextPane_date_description);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(496, Short.MAX_VALUE))
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title", "Director", "BBFC Rating", "Availability Date"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jButton1.setText("Remove All");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Close");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 562, Short.MAX_VALUE)
                        .addComponent(jButton2))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 706, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 650, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed


        int response = JOptionPane.showConfirmDialog(null, "Really remove all historical archives?");

        try {
            if(response == 1){
                this.historical_controller.removeAllRecords();
            }
        } catch (SQLException ex) {
            Logger.getLogger(HistoricalDatabase.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(HistoricalDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextPane jTextPane_date_description;
    // End of variables declaration//GEN-END:variables

}
