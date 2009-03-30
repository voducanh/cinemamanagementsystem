/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * FilmList.java
 *
 * Created on 14-Feb-2009, 09:37:27
 */

package cinemacontroller.gui;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.DefaultListModel;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JOptionPane;

import databasecontroller.MySqlController;

/**
 *
 * @author Scott
 */
public class FilmList extends javax.swing.JFrame {

	private static final long serialVersionUID = 179516414159001654L;
	private MainWindow window;
    
    /** Creates new form FilmList */
    public FilmList(MainWindow window) {
        initComponents();
        this.window = window;
        this.populateListControl();

        // Center align the login window
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }


    public void populateListControl(){
        DefaultListModel model = new DefaultListModel();

        MySqlController connection;
        ResultSet result;

		try {
			connection = MySqlController.getInstance();
			result= connection.getData("SELECT NAME FROM MOVIES ORDER BY NAME");

			while (result.next()) {
				model.addElement(result.getString(1));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

        this.jList1.setModel(model);
    }

    public int closeWindow(){
        this.window.setEnabled(true);
        return 0;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextPane_date_description = new javax.swing.JTextPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Film List - Multiplex Manager");
        setIconImage(getToolkit().getImage(getClass().getResource("/cinemacontroller/gui/icons/images.png")));

        jPanel1.setBackground(new java.awt.Color(24, 24, 24));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Films in Database");

        jScrollPane3.setBorder(null);

        jTextPane_date_description.setBackground(new java.awt.Color(24, 24, 24));
        jTextPane_date_description.setEditable(false);
        jTextPane_date_description.setForeground(new java.awt.Color(255, 255, 255));
        jTextPane_date_description.setText("This window contains a list of all the films that are stored in the database.\n\nYou can also see information, add or remove films from the database. To do this, select a film from the list and press the corresponding button.");
        jScrollPane3.setViewportView(jTextPane_date_description);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(345, Short.MAX_VALUE))
        );

        jList1.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
        
        jScrollPane1.setViewportView(jList1);

        jButton1.setText("Close");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Add Film");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Remove Film");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Film Information");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2)
                        .addGap(8, 8, 8)
                        .addComponent(jButton3))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 426, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        this.closeWindow();
        this.dispose();
    }                                        

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        new CreateNewFilm();
    } 
    
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {
        
    	Object[] filmSelected = jList1.getSelectedValues();
    	
    	if(filmSelected.length != 0){
    		
    		JOptionPane.showMessageDialog(null, "If you delete the film, it will automatically delete its timetable. If the timetable of the film was exported in the historical, it could not be imported anymore.", "Delete film", JOptionPane.WARNING_MESSAGE);
    		
    		String[] choice = {"Yes", "Cancel"};
    		int answer = JOptionPane.showOptionDialog(this,"Would you really delete the film selected?","Delete film",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,null,choice,choice[1]);
        	
    		 if (answer == JOptionPane.YES_OPTION){
    	    		try {
    	        		MySqlController connection = MySqlController.getInstance();

        	    		//delete the timetable of the film if it exists
    	        		connection.putData("DELETE FROM TIMETABLES WHERE ID_MOVIE IN (SELECT ID_MOVIE FROM MOVIES WHERE NAME='"+filmSelected[0]+"')");

    	        		//delete the film
    	        		connection.putData("DELETE FROM MOVIES WHERE NAME='"+filmSelected[0]+"'");
    	        		JOptionPane.showMessageDialog(null, "Film removed successfully.", "Delete Film", JOptionPane.INFORMATION_MESSAGE);	
    	        	
    	    		}
    	        	catch (SQLException e) {
    	        			e.printStackTrace();
    	        	} 
    	        	
    	        	//write again all films
    	        	this.populateListControl();

    		 }

    	}
    	else{
    		JOptionPane.showMessageDialog(null, "Please choose a film.", "Invalid Selection", JOptionPane.WARNING_MESSAGE);
    	}

    }    
    
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {
    	
    	Object[] filmSelected = jList1.getSelectedValues();
    	
    	if(filmSelected.length != 0){
    		new FilmInfo(filmSelected[0].toString());
    	}
    	else{
    		JOptionPane.showMessageDialog(null, "Please choose a film.", "Invalid Selection", JOptionPane.WARNING_MESSAGE);
    	}
    }


    // Variables declaration - do not modify
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JList jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextPane jTextPane_date_description;
    // End of variables declaration

}
