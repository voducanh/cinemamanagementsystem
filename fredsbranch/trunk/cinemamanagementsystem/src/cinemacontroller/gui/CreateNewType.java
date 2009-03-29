/*
 * CreateNewType.java
 *
 * Created on 17 mars 2009, 16:22
 */

package cinemacontroller.gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SpinnerNumberModel;

import databasecontroller.MySqlController;
import usefulmethods.TextLimiter;
import usefulmethods.Useful;

/**
 *
 * @author  Fr�d�ric
 */
public class CreateNewType extends javax.swing.JFrame {

	private static final long serialVersionUID = -7833697399792950132L;
	private Useful use;
	private Color newColor;
	/** Creates new form CreateNewType */
    public CreateNewType() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        colorChooser = new javax.swing.JColorChooser();
        jTextPane_date_description = new javax.swing.JTextPane();
        jLabel10 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jTextField2 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jSpinner1 = new javax.swing.JSpinner(new SpinnerNumberModel(-1.0,-1.0,null,0.1));
        jLabel12 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Create New Type - Multiplex Manager");
        setIconImage(getToolkit().getImage(getClass().getResource("/cinemacontroller/gui/icons/dvd_add.png")));

        jPanel2.setBackground(new java.awt.Color(24, 24, 24));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Create New Type");

        jScrollPane3.setBorder(null);

        jTextPane_date_description.setBackground(new java.awt.Color(24, 24, 24));
        jTextPane_date_description.setEditable(false);
        jTextPane_date_description.setForeground(new java.awt.Color(255, 255, 255));
        jTextPane_date_description.setText("You can create a new type by filling in the box to the right and choose a color for the timetable.\n\nThe index is use for the expected booking. It can be readjusted if the expected booking doesn't match at all with the number of seat booked.");
        jScrollPane3.setViewportView(jTextPane_date_description);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                .addContainerGap())
        );
        
        jTextField1.setDocument(new TextLimiter(40));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel10.setText("Type");

        jButton1.setText("Add Type");
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

        jButton3.setText("Add Color");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jTextField2.setEditable(false);

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel11.setText("Index");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel12.setText("Color");
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(276, 276, 276)
                        .addComponent(jButton2)
                        .addGap(10, 10, 10)
                        .addComponent(jButton1))
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(36, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(177, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton1))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {

    	
        if(!this.jTextField1.getText().isEmpty()){
        	
        	if(new Float(this.jSpinner1.getValue().toString()) > -0.1){
        		
            	if(newColor != null){
            		
                	boolean exist = false;
                	
            		try {
            			MySqlController connection = MySqlController.getInstance();

            			//test if the type doesn't already exist
            			ResultSet r = connection.getData("SELECT NAME FROM TYPES WHERE NAME='"+jTextField1.getText().toLowerCase()+"'");

            			while(r.next()){
            				if(r.getString(1).toLowerCase().equals(jTextField1.getText().toLowerCase())){
            					exist = true;
            					JOptionPane.showMessageDialog(null, "This name already exists.", "Invalid Type", JOptionPane.WARNING_MESSAGE);
            					jTextField1.setText("");
            					break;
            				}
            			}
            			
            			//if the type doesn't exist, we add it into the database
            			if(!exist){
                			r = connection.getData("SHOW TABLE STATUS LIKE 'TYPES'");
                			while(r.next()){
                    			connection.putData("INSERT INTO TYPES VALUES ('"+r.getString("Auto_increment")+"','"+jTextField1.getText()+"','"+use.cutColor(newColor)+"','"+jSpinner1.getValue().toString()+"')");
                    			JOptionPane.showMessageDialog(null, "Data added successfully.", "Type", JOptionPane.INFORMATION_MESSAGE);
                    			jTextField1.setText("");
                    			jTextField2.setBackground(null);
                    			jSpinner1.setValue(-1.0);
                			}
            			}
        	
            		} catch (SQLException e) {
            			e.printStackTrace();
            		}
            		
            	}
                else{
                	JOptionPane.showMessageDialog(null, "Please choose a color.", "Invalid Color", JOptionPane.WARNING_MESSAGE);
                }
        		
        	}
        	else{
        		JOptionPane.showMessageDialog(null, "Please choose a positive index", "Invalid Index", JOptionPane.WARNING_MESSAGE);
        	}
        	
        }
        else{
        	JOptionPane.showMessageDialog(null, "Please fill the box.", "Invalid Type", JOptionPane.WARNING_MESSAGE);
        }

    }
    
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        this.dispose();
    }
    
    
private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {

    final JLabel previewLabel = new JLabel();
    
    ActionListener okListener = new ActionListener() {
        // Called when user clicks ok
        public void actionPerformed(ActionEvent evt) {

            // Get selected color
            newColor = colorChooser.getColor();
            
    		String color = use.cutColor(newColor);

    		String red = color.substring(0, color.indexOf(","));
    		color = color.substring(color.indexOf(",")+1, color.length());
    		String green = color.substring(0, color.indexOf(","));
    		color = color.substring(color.indexOf(",")+1, color.length());
    		String blue = color;

    		jTextField2.setBackground(new java.awt.Color(new Integer(red), new Integer(green), new Integer(blue)));

        }
    };
    
    colorChooser.setPreviewPanel(previewLabel);

    JDialog d = colorChooser.createDialog(null,"Choose a color - Multiplex Manager",true,colorChooser,okListener,null);
    
    d.setIconImage(getToolkit().getImage(getClass().getResource("/cinemacontroller/gui/icons/dvd_add.png")));
    d.setVisible(true);
    
}

    // Variables declaration - do not modify
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JColorChooser colorChooser;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextPane jTextPane_date_description;
    // End of variables declaration

}
