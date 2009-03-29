/*
 * AlterType.java
 *
 * Created on 25 mars 2009, 16:10
 */

package cinemacontroller.gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
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
public class AlterType extends javax.swing.JFrame {

	private static final long serialVersionUID = 0L;
	private Useful use;
	private Color newColor;
	private ArrayList<String> colorRGB = new ArrayList<String>();
	private ArrayList<String> index = new ArrayList<String>();
    /** Creates new form AlterType */
    public AlterType() {
    	use = new Useful();
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
        jTextPane_date_description = new javax.swing.JTextPane();
        jLabel10 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        modelComboBox1 = new javax.swing.DefaultComboBoxModel();
        jSpinner1 = new javax.swing.JSpinner(new SpinnerNumberModel(-1.0,-1.0,null,0.1));
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        colorChooser= new javax.swing.JColorChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Alter Type - Multiplex Manager");
        setIconImage(getToolkit().getImage(getClass().getResource("/cinemacontroller/gui/icons/dvd_add.png")));

        jPanel2.setBackground(new java.awt.Color(24, 24, 24));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Alter Type");

        jScrollPane3.setBorder(null);

        jTextPane_date_description.setBackground(new java.awt.Color(24, 24, 24));
        jTextPane_date_description.setEditable(false);
        jTextPane_date_description.setForeground(new java.awt.Color(255, 255, 255));
        jTextPane_date_description.setText("You can alter a type that already exists by choosing it in the list. Then, fill in the box to the right with the new type and change the color if you want to.\n\nThe index is use for the expected booking. It can be readjusted if the expected booking doesn't match at all with the number of seat booked.");
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
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTextField1.setDocument(new TextLimiter(40));
        
        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel10.setText("Type");

        jButton2.setText("Close");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton1.setText("OK");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel11.setText("Current Type");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel12.setText("Current Index");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel13.setText("Color");

        jButton3.setText("Add Color");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        MySqlController connection;
        ResultSet result;
        
		modelComboBox1 = new DefaultComboBoxModel();
		
        jComboBox1 = new javax.swing.JComboBox(modelComboBox1);
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	jComboBox1ActionPerformed(evt);
            }
        });

		try {
			
			connection = MySqlController.getInstance();
			result= connection.getData("SELECT * FROM TYPES ORDER BY NAME");
			jComboBox1.addItem("");
			colorRGB.add("");
			index.add("");
			while (result.next()) {
				jComboBox1.addItem(result.getString(2));
				
				//store all colors in an arrayList
				colorRGB.add(result.getString(3));
				
				index.add(result.getString(4));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
        jTextField2.setEditable(false);

        jTextField3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField3.setEditable(false);
        
        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel14.setText("Index");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jComboBox1, 0, 294, Short.MAX_VALUE)
                                .addComponent(jTextField1)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jTextField3, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(140, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
    	
    	String sets= "";
    	
    	if(jComboBox1.getSelectedItem() != jComboBox1.getItemAt(0)){
    		
    		//list all posibilities
    		
    		if(!jTextField1.getText().trim().isEmpty()){
        		sets += "NAME='"+jTextField1.getText().trim()+"',";
        	}
    		
    		if(newColor != null){
        		sets += "COLOR_RGB='"+use.cutColor(newColor)+"',";
        	}
    		
    		if(new Float(this.jSpinner1.getValue().toString()) > -0.1){
        		sets += "`INDEX`='"+jSpinner1.getValue().toString()+"'";
        	}
    		
    		if(!sets.isEmpty()){
    			
    			String lastChar = "";
    			lastChar += sets.charAt(sets.length()-1);
    			if(lastChar.equals(",")){
    				sets = sets.substring(0, sets.length()-1);
    			}
    			
    			boolean exist = false;

    			//check if the type written doesn't exist
    			try {
        			MySqlController connection = MySqlController.getInstance();

        			ResultSet r = connection.getData("SELECT NAME FROM TYPES WHERE NAME='"+jTextField1.getText().toLowerCase().trim()+"'");

        			while(r.next()){
        				if(r.getString(1).toLowerCase().equals(jTextField1.getText().toLowerCase().trim())){
        					exist = true;
        					JOptionPane.showMessageDialog(null, "This name already exists.", "Invalid Type", JOptionPane.WARNING_MESSAGE);
        					jTextField1.setText("");
        					break;
        				}
        				
        			}

        			//if the type doesn't exist, we update it into the database (with the new color)
        			if(!exist){
        				
        					connection.putData("UPDATE TYPES SET "+sets+" WHERE NAME='"+jComboBox1.getSelectedItem()+"'");
            				
                			JOptionPane.showMessageDialog(null, "Type updated successfully.", "Type", JOptionPane.INFORMATION_MESSAGE);
                			jTextField1.setText("");
                			jTextField2.setBackground(null);
                			jComboBox1.setSelectedIndex(0);
                			jSpinner1.setValue(-1);
            		
        			}
    	
        		} catch (SQLException e) {
        			e.printStackTrace();
        		}
    			
    			
    			
    			
    		}
    		else{
    			JOptionPane.showMessageDialog(null, "Please choose at least a data to update", "Invalid Data", JOptionPane.WARNING_MESSAGE);
            }
    	}
    	else{
        	JOptionPane.showMessageDialog(null, "Please choose a current type", "Invalid Type", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
    	
    	this.dispose();
    }
    
    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {
    	
    	if(jComboBox1.getSelectedItem() != jComboBox1.getItemAt(0)){

    		String color = colorRGB.get(jComboBox1.getSelectedIndex());

    		String red = color.substring(0, color.indexOf(","));
    		color = color.substring(color.indexOf(",")+1, color.length());
    		String green = color.substring(0, color.indexOf(","));
    		color = color.substring(color.indexOf(",")+1, color.length());
    		String blue = color;

    		jTextField2.setBackground(new java.awt.Color(new Integer(red), new Integer(green), new Integer(blue)));
    		jTextField3.setText(index.get(jComboBox1.getSelectedIndex()));
    	}
    	else{
    		jTextField2.setBackground(null);
    		jTextField3.setText("");
    	}
    	
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
    private javax.swing.DefaultComboBoxModel modelComboBox1;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JColorChooser colorChooser;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextPane jTextPane_date_description;
    // End of variables declaration

}
