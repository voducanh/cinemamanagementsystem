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
import cinemacontroller.screencontroller.Screening;
import java.sql.SQLException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Scott
 */
public class HistoricalDatabase extends javax.swing.JFrame {

    private MainWindow main_window;

    /** Creates new form HistoricalDatabase */
    public HistoricalDatabase(MainWindow main_window) {
        this.main_window = main_window;

        // Load the current window's controls
        this.initComponents();

        // Center align the login window
        this.setLocationRelativeTo(null);

        // Load the historical data into the current window
        this.populateFilmTable();

        this.populateScreeningTable();
    }

    public void LoadHistoricalData(){
        

    }
    


    public void populateFilmTable(){
        try {
            // Set the historical table's model to that of a default table model
            DefaultTableModel model = (DefaultTableModel) this.jTable1.getModel();
            // Create a new arraylist to store all the films
            ArrayList<Film> list_of_films = this.main_window.core_controller.historical_controller.getAllFilms();
            // Alert the user if there are no films to show
            if (list_of_films.size() < 1) {
                JOptionPane.showMessageDialog(null, "There are current no historical records to show.", "No Records Available", JOptionPane.WARNING_MESSAGE);
                this.jButton1.setEnabled(false);
            } else {
                this.jButton1.setEnabled(true);
            }

            Format time_formatter = new SimpleDateFormat("kk:mm");
            Format date_formatter = new SimpleDateFormat("d/m/y");




            // Cycle through all the films, create a new row on table and add details for film
            for (Film current_film : list_of_films) {
                Date time = current_film.getLength().getTime();
                Date start_dater = current_film.getDateAdded().getTime();
                Date end_dater = current_film.getExpireDate().getTime();


                model.addRow(new Object[]{" " + current_film.getTitle(), " " + current_film.getDirector(), " " + current_film.getBBFCRating(),  time_formatter.format(time), date_formatter.format(start_dater), date_formatter.format(end_dater) });
            }

            this.jTable1.setModel(model);

        } catch (Exception ex) {
            System.out.println(ex);
        }

    }

    public void populateScreeningTable(){
        try{

             // Set the historical table's model to that of a default table model
            DefaultTableModel model = (DefaultTableModel) this.jTable2.getModel();
            // Create a new arraylist to store all the films
            ArrayList<Screening> list_of_screenings = this.main_window.core_controller.historical_controller.getAllScreenings(this.main_window.core_controller.screen_controller.getScreens());

            Format time_formatter = new SimpleDateFormat("kk:mm");
            Format date_formatter = new SimpleDateFormat("d/m/y");

            // Cycle through all the films, create a new row on table and add details for film
            for (Screening current_screening : list_of_screenings) {
                Date time = current_screening.getStartTime().getTime();
                Date date = current_screening.getDate().getTime();


                model.addRow(new Object[]{" " + current_screening.getFilm().getTitle(), " " + time_formatter.format(time), " " + date_formatter.format(date), current_screening.getScreen().getID(), current_screening.getViewings()});
            }

            this.jTable2.setModel(model);

        }catch(Exception e){

        }
    }
















    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextPane_date_description = new javax.swing.JTextPane();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Historical Data Archives - Multiplex Manager");
        setIconImage(getToolkit().getImage(getClass().getResource("/cinemacontroller/gui/icons/time.png")));

        jPanel2.setBackground(new java.awt.Color(24, 24, 24));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11));
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
                .addContainerGap(469, Short.MAX_VALUE))
        );

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

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title", "Director", "BBFC Rating", "Length", "Availability Date", "Expire Date", "Total Views"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 801, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 573, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Film Log", jPanel1);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Film Title", "Date", "Time", "Screen ID", "Viewings"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 801, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 573, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Screening Log", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 682, Short.MAX_VALUE)
                        .addComponent(jButton2))
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 826, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 623, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                this.main_window.core_controller.historical_controller.removeAllRecords();
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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextPane jTextPane_date_description;
    // End of variables declaration//GEN-END:variables

}
