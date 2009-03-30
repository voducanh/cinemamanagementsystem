/*
 * MainW.java
 *
 * Created on 28 mars 2009, 23:51
 */

package cinemacontroller.gui;

import javax.swing.event.TableColumnModelEvent;
import javax.swing.table.DefaultTableColumnModel;

import cinemacontroller.rotationalcontroller.AutomaticRotation;
import cinemacontroller.rotationalcontroller.AutomaticRotationCurrentPeriod;

/**
 *
 * @author  Fr�d�ric
 */
public class MainWindow extends javax.swing.JFrame {

	private static final long serialVersionUID = -3688656990364226359L;
    private String login ;
    
    /** Creates new form MainW */
    public MainWindow(String login) {

        // Curent login
        this.login = login;

        // Creates all the default controls.
        this.initComponents();

        // Center align the login window
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

        jToolBar1 = new javax.swing.JToolBar();
        timetable = new javax.swing.JButton();
        timetable2 = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        statistics = new javax.swing.JButton();
        historical = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        viewFilms = new javax.swing.JButton();
        options = new javax.swing.JButton();
        user = new javax.swing.JButton();
        comboDay = new javax.swing.JComboBox();
        comboWeek = new javax.swing.JComboBox();
        displayWeek = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenuItem11 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JSeparator();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem12 = new javax.swing.JMenuItem();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu7 = new javax.swing.JMenu();
        jMenuItem23 = new javax.swing.JMenuItem();
        jMenu11 = new javax.swing.JMenu();
        jMenuItem13 = new javax.swing.JMenuItem();
        jMenuItem14 = new javax.swing.JMenuItem();
        jMenuItem15 = new javax.swing.JMenuItem();
        jMenuItem16 = new javax.swing.JMenuItem();
        jMenu13 = new javax.swing.JMenu();
        jMenuItem17 = new javax.swing.JMenuItem();
        jMenuItem18 = new javax.swing.JMenuItem();
        jMenuItem19 = new javax.swing.JMenuItem();
        jMenuItem20 = new javax.swing.JMenuItem();
        jMenu14 = new javax.swing.JMenu();
        jMenuItem21 = new javax.swing.JMenuItem();
        jMenuItem22 = new javax.swing.JMenuItem();
        jMenu12 = new javax.swing.JMenu();
        jMenuItem24 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle(login+" - Multiplex Manager");
        setIconImage(getToolkit().getImage(getClass().getResource("/cinemacontroller/gui/icons/images.png")));


        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        timetable.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cinemacontroller/gui/icons/timetable.png"))); // NOI18N
        timetable.setFocusable(false);
        timetable.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        timetable.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        timetable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                timetableActionPerformed(evt);
            }
        });
        jToolBar1.add(timetable);

        timetable2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cinemacontroller/gui/icons/timetable.png"))); // NOI18N
        timetable2.setFocusable(false);
        timetable2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        timetable2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        timetable2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                timetable2ActionPerformed(evt);
            }
        });
        jToolBar1.add(timetable2);
        jToolBar1.add(jSeparator2);

        statistics.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cinemacontroller/gui/icons/chart_pie.png"))); // NOI18N
        statistics.setFocusable(false);
        statistics.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        statistics.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        statistics.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                statisticsActionPerformed(evt);
            }
        });
        jToolBar1.add(statistics);

        historical.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cinemacontroller/gui/icons/hourglass.png"))); // NOI18N
        historical.setFocusable(false);
        historical.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        historical.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        historical.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                historicalActionPerformed(evt);
            }
        });
        jToolBar1.add(historical);
        jToolBar1.add(jSeparator3);

        viewFilms.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cinemacontroller/gui/icons/images.png"))); // NOI18N
        viewFilms.setFocusable(false);
        viewFilms.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        viewFilms.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        viewFilms.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewFilmsActionPerformed(evt);
            }
        });
        jToolBar1.add(viewFilms);

        options.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cinemacontroller/gui/icons/options.png"))); // NOI18N
        options.setFocusable(false);
        options.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        options.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        options.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optionsActionPerformed(evt);
            }
        });
        jToolBar1.add(options);

        user.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cinemacontroller/gui/icons/user.png"))); // NOI18N
        user.setFocusable(false);
        user.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        user.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        user.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userActionPerformed(evt);
            }
        });
        jToolBar1.add(user);

        comboDay.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday" }));
        comboDay.setBorder(null);

        comboWeek.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));
        comboWeek.setBorder(null);

        displayWeek.setText("Display");
        displayWeek.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                displayWeekActionPerformed(evt);
            }
        });

        
        jLabel1.setText("Day");

        jLabel2.setText("Week");

        jTabbedPane1.addTab("Summary", jTabbedPane2);
        jTabbedPane1.addTab("Actions", jTabbedPane3);

        jLabel3.setText("Idle.");

        jTable1.setAutoResizeMode(jTable1.AUTO_RESIZE_OFF);

        jTable1.setColumnModel(new DefaultTableColumnModel() {
            protected void fireColumnAdded(TableColumnModelEvent pEvent) {
                for (int index = pEvent.getFromIndex(); index <= pEvent
                        .getToIndex(); index++) {
                    getColumn(index).setPreferredWidth(200);
                }
                super.fireColumnAdded(pEvent);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7", "Title 8", "Title 9", "Title 10", "Title 11", "Title 12"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jLabel4.setText("14:00");

        jLabel5.setText("14:30");

        jLabel6.setText("15:00");

        jLabel7.setText("15:30");

        jMenu7.setText("File");

        jMenuItem23.setText("Exit");
        jMenuItem23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem23ActionPerformed(evt);
            }
        });
        jMenu7.add(jMenuItem23);

        jMenuBar2.add(jMenu7);

        jMenu11.setText("New");

        jMenuItem13.setText("Film");
        jMenuItem13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem13ActionPerformed(evt);
            }
        });
        jMenu11.add(jMenuItem13);

        jMenuItem14.setText("Type");
        jMenuItem14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem14ActionPerformed(evt);
            }
        });
        jMenu11.add(jMenuItem14);

        jMenuItem15.setText("Distributor");
        jMenuItem15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem15ActionPerformed(evt);
            }
        });
        jMenu11.add(jMenuItem15);

        jMenuItem16.setText("Expected Audience");
        jMenuItem16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem16ActionPerformed(evt);
            }
        });
        jMenu11.add(jMenuItem16);

        jMenuBar2.add(jMenu11);

        jMenu13.setText("Alter");

        jMenuItem17.setText("Film");
        jMenuItem17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem17ActionPerformed(evt);
            }
        });
        jMenu13.add(jMenuItem17);

        jMenuItem18.setText("Type");
        jMenuItem18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem18ActionPerformed(evt);
            }
        });
        jMenu13.add(jMenuItem18);

        jMenuItem19.setText("Distributor");
        jMenuItem19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem19ActionPerformed(evt);
            }
        });
        jMenu13.add(jMenuItem19);

        jMenuItem20.setText("Expected Audience");
        jMenuItem20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem20ActionPerformed(evt);
            }
        });
        jMenu13.add(jMenuItem20);

        jMenuBar2.add(jMenu13);

        jMenu14.setText("Historical");

        jMenuItem21.setText("Import a day");
        jMenuItem21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem21ActionPerformed(evt);
            }
        });
        jMenu14.add(jMenuItem21);

        jMenuItem22.setText("Import a period");
        jMenuItem22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem22ActionPerformed(evt);
            }
        });
        jMenu14.add(jMenuItem22);

        jMenuBar2.add(jMenu14);

        jMenu12.setText("Help");

        jMenuItem24.setText("About");
        jMenuItem24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem24ActionPerformed(evt);
            }
        });
        jMenu12.add(jMenuItem24);

        jMenuBar2.add(jMenu12);

        setJMenuBar(jMenuBar2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 846, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(comboDay, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(comboWeek, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(displayWeek))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel5)
                                        .addComponent(jLabel4)
                                        .addComponent(jLabel6))
                                    .addComponent(jLabel7))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 992, Short.MAX_VALUE))))
                    .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 437, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(comboDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(displayWeek)
                            .addComponent(jLabel2)
                            .addComponent(comboWeek, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel7))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 533, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );

        pack();
    }// </editor-fold>

    private void jMenuItem23ActionPerformed(java.awt.event.ActionEvent evt) {
    	this.dispose();
    	}

    	private void jMenuItem13ActionPerformed(java.awt.event.ActionEvent evt) {
    	new CreateNewFilm();
    	}

    	private void jMenuItem14ActionPerformed(java.awt.event.ActionEvent evt) {
    	new CreateNewType();
    	}

    	private void jMenuItem15ActionPerformed(java.awt.event.ActionEvent evt) {
    	new CreateNewDistributor();
    	}

    	private void jMenuItem16ActionPerformed(java.awt.event.ActionEvent evt) {
    	new CreateNewExpectedAudience();
    	}

    	private void jMenuItem17ActionPerformed(java.awt.event.ActionEvent evt) {

    	}

    	private void jMenuItem18ActionPerformed(java.awt.event.ActionEvent evt) {
    	new AlterType();
    	}

    	private void jMenuItem19ActionPerformed(java.awt.event.ActionEvent evt) {
    	new AlterDistributor();
    	}

    	private void jMenuItem20ActionPerformed(java.awt.event.ActionEvent evt) {
    	new AlterExpectedAudience();
    	}

    	private void jMenuItem21ActionPerformed(java.awt.event.ActionEvent evt) {
//    	 TODO add your handling code here:
    	}

    	private void jMenuItem22ActionPerformed(java.awt.event.ActionEvent evt) {
//    	 TODO add your handling code here:
    	}

    	private void jMenuItem24ActionPerformed(java.awt.event.ActionEvent evt) {
    	new AboutProgram(this);
    	}

    	private void displayDayActionPerformed(java.awt.event.ActionEvent evt) {
//    	 TODO add your handling code here:
    	}

    	private void displayWeekActionPerformed(java.awt.event.ActionEvent evt) {
//    	 TODO add your handling code here:
    	}

    	private void timetableActionPerformed(java.awt.event.ActionEvent evt) {
    		new AutomaticRotation(0);
    	}

    	private void timetable2ActionPerformed(java.awt.event.ActionEvent evt) {
//    	 TODO add your handling code here:
    	}

    	private void statisticsActionPerformed(java.awt.event.ActionEvent evt) {
//    	 TODO add your handling code here:
    	}

    	private void historicalActionPerformed(java.awt.event.ActionEvent evt) {
//    	 TODO add your handling code here:
    	}

    	private void viewFilmsActionPerformed(java.awt.event.ActionEvent evt) {
    	new FilmList(this);
    	}

    	private void viewScreensActionPerformed(java.awt.event.ActionEvent evt) {
//    	 TODO add your handling code here:
    	}

    	private void userActionPerformed(java.awt.event.ActionEvent evt) {
    	new UserList(this);
    	}
    	
    	private void optionsActionPerformed(java.awt.event.ActionEvent evt) {

        	}

    // Variables declaration - do not modify
    private javax.swing.JComboBox comboDay;
    private javax.swing.JComboBox comboWeek;
    private javax.swing.JButton displayWeek;
    private javax.swing.JButton historical;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu11;
    private javax.swing.JMenu jMenu12;
    private javax.swing.JMenu jMenu13;
    private javax.swing.JMenu jMenu14;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem14;
    private javax.swing.JMenuItem jMenuItem15;
    private javax.swing.JMenuItem jMenuItem16;
    private javax.swing.JMenuItem jMenuItem17;
    private javax.swing.JMenuItem jMenuItem18;
    private javax.swing.JMenuItem jMenuItem19;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem20;
    private javax.swing.JMenuItem jMenuItem21;
    private javax.swing.JMenuItem jMenuItem22;
    private javax.swing.JMenuItem jMenuItem23;
    private javax.swing.JMenuItem jMenuItem24;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JButton options;
    private javax.swing.JButton statistics;
    private javax.swing.JButton timetable;
    private javax.swing.JButton timetable2;
    private javax.swing.JButton user;
    private javax.swing.JButton viewFilms;
    // End of variables declaration
    
	public String getLogin() {
		return login;
	}

}
