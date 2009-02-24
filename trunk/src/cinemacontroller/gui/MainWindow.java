package cinemacontroller.gui;

import cinemacontroller.gui.timetablecontrol.*;
import cinemacontroller.main.CinemaSystemController;
import cinemacontroller.screensystem.Screen;
import cinemacontroller.screensystem.Screening;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.CellEditor;
import javax.swing.JOptionPane;
import javax.swing.ScrollPaneLayout;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;


/**
 * This class is the class which creates the main window and all its components. The MainWindow
 * class is passed the 'Controller' object which allows the UI to interface with the core
 * components including rotational engine and database.
 * 
 * @author Scott
 */
public class MainWindow extends javax.swing.JFrame {

    public CinemaSystemController core_controller;
    private ArrayList<TimetableScreeningBox> list_of_box_controls;
    private TimetableTable timetable_control;


    /**
     * Constructer for the main window. This sets up all the required functionality and also
     * Renders all the components.
     */
    public MainWindow(CinemaSystemController core_controller) {
        // Set the main controller
        this.core_controller = core_controller;

        // Initialise the ArrayList that contains all the "TimeTableItems" on the timetable control.
        this.list_of_box_controls = new ArrayList<TimetableScreeningBox>();

        // Creates all the default controls.
        this.initComponents();

        // Center align the login window
        this.setLocationRelativeTo(null);
        
        // Creates a new timetable control
        this.timetable_control = createTimetableControl();

       // Update all the controls
        this.updateSummaryPanel();

        // Refresh timetable control
        this.refreshTimetable();

        // Creates a scroll pane layout control and adds the timetable to it.
        ScrollPaneLayout layout = new ScrollPaneLayout();
        this.timetable_scroll_pane.setLayout(layout);
        this.timetable_scroll_pane.getViewport().add( timetable_control );

    }

 
    public void updateSummaryPanel(){
        this.films_in_database_jlabel.setText("Total Films in Database: " + this.core_controller.film_manager.getAllFilms().size());
        this.screenings_in_database_jlabel1.setText("Screenings in Database: " + this.core_controller.screen_manager.getScreeningCount());
    }



    /**
     * Returns a timetable component
     * @return
     */
    public TimetableTable createTimetableControl(){

        Object[][] obj = new Object [this.core_controller.screen_manager.getScreens().size()][31];

        int row_counter = 0;
        for(Screen current_screen : this.core_controller.screen_manager.getScreens()){
            obj[row_counter][0] = current_screen;

            for(int column_counter = 1; column_counter < 31; column_counter++){
                obj[row_counter][column_counter] = new String();
            }
            
            row_counter++;
        }


        TableModel timetable_model = new javax.swing.table.DefaultTableModel(
            obj,
            new String [] {
                "Screen ID", "0900", "", "1000", "", "1100", "", "1200", "", "1300", "", "1400", "", "1500", "", "1600", "", "1700", "", "1800", "", "1900", "", "2000", "", "2100", "", "2200", "", "2300", ""
            }
        );




        // Create the timetable control
        TimetableTable timetable = new TimetableTable(new TimetableItemCellSizer(this.list_of_box_controls), timetable_model, 12, 9);
        // Set the default renderer for the timetable
        timetable.setDefaultRenderer(Object.class, new TimetableRenderer(this.list_of_box_controls));
        // Sets the row height
        timetable.setRowHeight(60);

        // Disables resizing and reordering
        timetable.getTableHeader().setResizingAllowed(false);
        timetable.getTableHeader().setReorderingAllowed(false);

          
                                    

        // Sets the column model for the list of screens and sets the min width to 50px
        TableColumn col = timetable.getColumnModel().getColumn(0);
        col.setMinWidth(55);

        timetable.addMouseListener(new MouseAdapter(){

            @Override
                public void mouseClicked(MouseEvent mouseEvent) {
                    int row = timetable_control.rowAtPoint(new Point(mouseEvent.getX(), mouseEvent.getY()));
                    int col = timetable_control.columnAtPoint(new Point(mouseEvent.getX(), mouseEvent.getY()));

                    Object obj = timetable_control.getValueAt(row, col);

                    System.out.println(obj.toString());

                    jPopupMenu1.show(timetable_control, mouseEvent.getX(), mouseEvent.getY());
       
                }
        });
        
        return timetable;
    }

    /**
     * Refreshes the timetable control if modifications of the controls data have occured.
     */
    public void refreshTimetable(){
        // Create and add any new screenings to box controls
        this.generateBoxControls();
        // Refreshes the timetable's renderer which draws all the films
        this.timetable_control.setDefaultRenderer(Object.class, new TimetableRenderer(this.list_of_box_controls));

        // Refresh the control components and redraw them
        this.timetable_control.repaint();
        this.timetable_control.validate();

        // Re-pack
        this.pack();
    }


    /**
     * Generates all the box controls that represent a screening and adds it to the list of
     * controls for the renderer to draw them.
     */
    public void generateBoxControls(){
        // Cycles through each screen
        for(Screen current_screen : this.core_controller.screen_manager.getScreens()){
            // Cycles through each screening of the current screen
             for(Screening current_screening : current_screen.getScreenings()){
                 // Creates a box control and adds it to the box control list
                 this.list_of_box_controls.add(new TimetableScreeningBox(current_screening, current_screening.getColor(), new Color(255,255,255), this.timetable_control, this.getScreenRowNumber(current_screen.getIDNumber())));
             }
        }
    }


    /**
     * Returns the row number of the selected screen.
     *
     * @param screen_id
     * @return
     */
    public int getScreenRowNumber(int screen_id){
        // Cycle through all the rows of the table
        for(int counter = 0; counter < this.timetable_control.getRowCount(); counter++){
            // Get the value of the current cell
            Object o = this.timetable_control.getValueAt(counter, 0);
            // Cast the cell to a screen object
            Screen row_value = (Screen)o;

            // If the current row value is equal to what we are looking for return the row number
            if(row_value.getIDNumber() == screen_id){
                return counter;
            }
        }
        return 0;
    }



    













    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JSeparator();
        jMenuItem4 = new javax.swing.JMenuItem();
        jSplitPane_main = new javax.swing.JSplitPane();
        timetable_panel = new javax.swing.JPanel();
        timetable_scroll_pane = new javax.swing.JScrollPane();
        timetable_date_picker_combo = new javax.swing.JComboBox();
        overview_tabbed_pane = new javax.swing.JTabbedPane();
        summary_panel = new javax.swing.JPanel();
        film_summary_title_jlabel = new javax.swing.JLabel();
        films_in_database_jlabel = new javax.swing.JLabel();
        most_popular_film_jlabel = new javax.swing.JLabel();
        least_popular_jlabel = new javax.swing.JLabel();
        screenings_in_database_jlabel1 = new javax.swing.JLabel();
        actions_panel = new javax.swing.JPanel();
        toolbar = new javax.swing.JToolBar();
        create_new_screening_button = new javax.swing.JButton();
        edit_screening_jbutton = new javax.swing.JButton();
        delete_screening_jbutton = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        view_actions_jbutton = new javax.swing.JButton();
        view_statistical_data_jbutton = new javax.swing.JButton();
        view_historical_data_jbutton = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        view_films_jbutton = new javax.swing.JButton();
        view_screens_jbutton = new javax.swing.JButton();
        status_bar = new javax.swing.JLabel();
        main_menu = new javax.swing.JMenuBar();
        file_menu = new javax.swing.JMenu();
        jMenu1 = new javax.swing.JMenu();
        create_new_film_menuitem = new javax.swing.JMenuItem();
        create_new_screening_menuitem = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JSeparator();
        jMenuItem1 = new javax.swing.JMenuItem();
        edit_menu = new javax.swing.JMenu();
        help_menu = new javax.swing.JMenu();
        about_menuitem = new javax.swing.JMenuItem();

        jMenuItem2.setText("Edit this Screening");
        jPopupMenu1.add(jMenuItem2);

        jMenuItem3.setText("Delete this Screening");
        jPopupMenu1.add(jMenuItem3);
        jPopupMenu1.add(jSeparator4);

        jMenuItem4.setText("View Screening Details");
        jPopupMenu1.add(jMenuItem4);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Multiplex Manager");
        setIconImage(getToolkit().getImage(getClass().getResource("/cinemacontroller/gui/icons/images.png")));

        jSplitPane_main.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 5, 1, 5));
        jSplitPane_main.setDividerLocation(250);
        jSplitPane_main.setDividerSize(0);

        timetable_scroll_pane.setBorder(null);
        timetable_scroll_pane.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout timetable_panelLayout = new javax.swing.GroupLayout(timetable_panel);
        timetable_panel.setLayout(timetable_panelLayout);
        timetable_panelLayout.setHorizontalGroup(
            timetable_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(timetable_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(timetable_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, timetable_panelLayout.createSequentialGroup()
                        .addComponent(timetable_date_picker_combo, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4))
                    .addComponent(timetable_scroll_pane, javax.swing.GroupLayout.DEFAULT_SIZE, 895, Short.MAX_VALUE)))
        );
        timetable_panelLayout.setVerticalGroup(
            timetable_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(timetable_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(timetable_date_picker_combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(timetable_scroll_pane, javax.swing.GroupLayout.DEFAULT_SIZE, 558, Short.MAX_VALUE))
        );

        jSplitPane_main.setRightComponent(timetable_panel);

        film_summary_title_jlabel.setFont(new java.awt.Font("Tahoma", 1, 11));
        film_summary_title_jlabel.setText("Film Summary");

        films_in_database_jlabel.setText("Films in Database: ");

        most_popular_film_jlabel.setText("Most Popular Film: ");

        least_popular_jlabel.setText("Least Popular Film: ");

        screenings_in_database_jlabel1.setText("Screenings in Database:");

        javax.swing.GroupLayout summary_panelLayout = new javax.swing.GroupLayout(summary_panel);
        summary_panel.setLayout(summary_panelLayout);
        summary_panelLayout.setHorizontalGroup(
            summary_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(summary_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(summary_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(films_in_database_jlabel, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                    .addComponent(film_summary_title_jlabel, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                    .addComponent(most_popular_film_jlabel, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                    .addComponent(least_popular_jlabel, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                    .addComponent(screenings_in_database_jlabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE))
                .addContainerGap())
        );
        summary_panelLayout.setVerticalGroup(
            summary_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(summary_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(film_summary_title_jlabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(films_in_database_jlabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(most_popular_film_jlabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(least_popular_jlabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(screenings_in_database_jlabel1)
                .addContainerGap(462, Short.MAX_VALUE))
        );

        overview_tabbed_pane.addTab("Summary", summary_panel);

        javax.swing.GroupLayout actions_panelLayout = new javax.swing.GroupLayout(actions_panel);
        actions_panel.setLayout(actions_panelLayout);
        actions_panelLayout.setHorizontalGroup(
            actions_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 240, Short.MAX_VALUE)
        );
        actions_panelLayout.setVerticalGroup(
            actions_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 567, Short.MAX_VALUE)
        );

        overview_tabbed_pane.addTab("Actions", actions_panel);

        jSplitPane_main.setLeftComponent(overview_tabbed_pane);

        toolbar.setFloatable(false);
        toolbar.setRollover(true);
        toolbar.setMinimumSize(new java.awt.Dimension(117, 28));
        toolbar.setPreferredSize(new java.awt.Dimension(168, 36));

        create_new_screening_button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cinemacontroller/gui/icons/application_add.png"))); // NOI18N
        create_new_screening_button.setToolTipText("Add a screening into the database.");
        create_new_screening_button.setFocusable(false);
        create_new_screening_button.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        create_new_screening_button.setMaximumSize(new java.awt.Dimension(26, 28));
        create_new_screening_button.setMinimumSize(new java.awt.Dimension(28, 28));
        create_new_screening_button.setPreferredSize(new java.awt.Dimension(32, 32));
        create_new_screening_button.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        create_new_screening_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                create_new_screening_buttonActionPerformed(evt);
            }
        });
        toolbar.add(create_new_screening_button);

        edit_screening_jbutton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cinemacontroller/gui/icons/application_edit.png"))); // NOI18N
        edit_screening_jbutton.setToolTipText("Edit a screening from in database.");
        edit_screening_jbutton.setFocusable(false);
        edit_screening_jbutton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        edit_screening_jbutton.setMaximumSize(new java.awt.Dimension(26, 28));
        edit_screening_jbutton.setMinimumSize(new java.awt.Dimension(28, 28));
        edit_screening_jbutton.setPreferredSize(new java.awt.Dimension(32, 32));
        edit_screening_jbutton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        edit_screening_jbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edit_screening_jbuttonActionPerformed(evt);
            }
        });
        toolbar.add(edit_screening_jbutton);

        delete_screening_jbutton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cinemacontroller/gui/icons/application_delete.png"))); // NOI18N
        delete_screening_jbutton.setToolTipText("Remove a screening from the database.");
        delete_screening_jbutton.setFocusable(false);
        delete_screening_jbutton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        delete_screening_jbutton.setMaximumSize(new java.awt.Dimension(26, 28));
        delete_screening_jbutton.setMinimumSize(new java.awt.Dimension(28, 28));
        delete_screening_jbutton.setPreferredSize(new java.awt.Dimension(32, 32));
        delete_screening_jbutton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        delete_screening_jbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delete_screening_jbuttonActionPerformed(evt);
            }
        });
        toolbar.add(delete_screening_jbutton);
        toolbar.add(jSeparator2);

        view_actions_jbutton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cinemacontroller/gui/icons/script_gear.png"))); // NOI18N
        view_actions_jbutton.setToolTipText("View Actions from the recommendation engine.");
        view_actions_jbutton.setFocusable(false);
        view_actions_jbutton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        view_actions_jbutton.setMaximumSize(new java.awt.Dimension(26, 28));
        view_actions_jbutton.setMinimumSize(new java.awt.Dimension(28, 28));
        view_actions_jbutton.setPreferredSize(new java.awt.Dimension(32, 32));
        view_actions_jbutton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        view_actions_jbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                view_actions_jbuttonActionPerformed(evt);
            }
        });
        toolbar.add(view_actions_jbutton);

        view_statistical_data_jbutton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cinemacontroller/gui/icons/chart_pie.png"))); // NOI18N
        view_statistical_data_jbutton.setToolTipText("View statistical data.");
        view_statistical_data_jbutton.setFocusable(false);
        view_statistical_data_jbutton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        view_statistical_data_jbutton.setMaximumSize(new java.awt.Dimension(26, 28));
        view_statistical_data_jbutton.setMinimumSize(new java.awt.Dimension(28, 28));
        view_statistical_data_jbutton.setPreferredSize(new java.awt.Dimension(32, 32));
        view_statistical_data_jbutton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        view_statistical_data_jbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                view_statistical_data_jbuttonActionPerformed(evt);
            }
        });
        toolbar.add(view_statistical_data_jbutton);

        view_historical_data_jbutton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cinemacontroller/gui/icons/hourglass.png"))); // NOI18N
        view_historical_data_jbutton.setToolTipText("View statistical data.");
        view_historical_data_jbutton.setFocusable(false);
        view_historical_data_jbutton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        view_historical_data_jbutton.setMaximumSize(new java.awt.Dimension(26, 28));
        view_historical_data_jbutton.setMinimumSize(new java.awt.Dimension(28, 28));
        view_historical_data_jbutton.setPreferredSize(new java.awt.Dimension(32, 32));
        view_historical_data_jbutton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        view_historical_data_jbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                view_historical_data_jbuttonActionPerformed(evt);
            }
        });
        toolbar.add(view_historical_data_jbutton);
        toolbar.add(jSeparator3);

        view_films_jbutton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cinemacontroller/gui/icons/images.png"))); // NOI18N
        view_films_jbutton.setToolTipText("View all the films stored and their attributes.");
        view_films_jbutton.setFocusable(false);
        view_films_jbutton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        view_films_jbutton.setMaximumSize(new java.awt.Dimension(26, 28));
        view_films_jbutton.setMinimumSize(new java.awt.Dimension(28, 28));
        view_films_jbutton.setPreferredSize(new java.awt.Dimension(32, 32));
        view_films_jbutton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        view_films_jbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                view_films_jbuttonActionPerformed(evt);
            }
        });
        toolbar.add(view_films_jbutton);

        view_screens_jbutton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cinemacontroller/gui/icons/house.png"))); // NOI18N
        view_screens_jbutton.setToolTipText("View all the screens and their attributes.");
        view_screens_jbutton.setFocusable(false);
        view_screens_jbutton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        view_screens_jbutton.setMaximumSize(new java.awt.Dimension(26, 28));
        view_screens_jbutton.setMinimumSize(new java.awt.Dimension(28, 28));
        view_screens_jbutton.setPreferredSize(new java.awt.Dimension(32, 32));
        view_screens_jbutton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        view_screens_jbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                view_screens_jbuttonActionPerformed(evt);
            }
        });
        toolbar.add(view_screens_jbutton);

        status_bar.setText("  Idle.");

        file_menu.setText("File");

        jMenu1.setText("New");

        create_new_film_menuitem.setText("Film");
        jMenu1.add(create_new_film_menuitem);

        create_new_screening_menuitem.setText("Screening");
        jMenu1.add(create_new_screening_menuitem);

        file_menu.add(jMenu1);
        file_menu.add(jSeparator1);

        jMenuItem1.setText("Exit");
        file_menu.add(jMenuItem1);

        main_menu.add(file_menu);

        edit_menu.setText("Edit");
        main_menu.add(edit_menu);

        help_menu.setText("Help");

        about_menuitem.setText("About");
        about_menuitem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                about_menuitemActionPerformed(evt);
            }
        });
        help_menu.add(about_menuitem);

        main_menu.add(help_menu);

        setJMenuBar(main_menu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(status_bar, javax.swing.GroupLayout.DEFAULT_SIZE, 1160, Short.MAX_VALUE)
            .addComponent(toolbar, javax.swing.GroupLayout.DEFAULT_SIZE, 1160, Short.MAX_VALUE)
            .addComponent(jSplitPane_main, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1160, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(toolbar, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSplitPane_main, javax.swing.GroupLayout.DEFAULT_SIZE, 597, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(status_bar, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void create_new_screening_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_create_new_screening_buttonActionPerformed
        CreateNewScreening create_screening_window = new CreateNewScreening(this);
        create_screening_window.setVisible(true);
        
}//GEN-LAST:event_create_new_screening_buttonActionPerformed

    private void about_menuitemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_about_menuitemActionPerformed
       
        this.setEnabled(false);
        new AboutProgram(this).setVisible(true);
    }//GEN-LAST:event_about_menuitemActionPerformed

    private void edit_screening_jbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edit_screening_jbuttonActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_edit_screening_jbuttonActionPerformed

    private void delete_screening_jbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delete_screening_jbuttonActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_delete_screening_jbuttonActionPerformed

    private void view_actions_jbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_view_actions_jbuttonActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_view_actions_jbuttonActionPerformed

    private void view_statistical_data_jbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_view_statistical_data_jbuttonActionPerformed
        this.generateBoxControls();
        this.refreshTimetable();
}//GEN-LAST:event_view_statistical_data_jbuttonActionPerformed

    private void view_films_jbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_view_films_jbuttonActionPerformed
        this.setEnabled(false);
        new FilmList(this).setVisible(true);
}//GEN-LAST:event_view_films_jbuttonActionPerformed

    private void view_screens_jbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_view_screens_jbuttonActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_view_screens_jbuttonActionPerformed

    private void view_historical_data_jbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_view_historical_data_jbuttonActionPerformed
        new HistoricalDatabase().setVisible(true);
}//GEN-LAST:event_view_historical_data_jbuttonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem about_menuitem;
    private javax.swing.JPanel actions_panel;
    private javax.swing.JMenuItem create_new_film_menuitem;
    private javax.swing.JButton create_new_screening_button;
    private javax.swing.JMenuItem create_new_screening_menuitem;
    private javax.swing.JButton delete_screening_jbutton;
    private javax.swing.JMenu edit_menu;
    private javax.swing.JButton edit_screening_jbutton;
    private javax.swing.JMenu file_menu;
    private javax.swing.JLabel film_summary_title_jlabel;
    private javax.swing.JLabel films_in_database_jlabel;
    private javax.swing.JMenu help_menu;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSplitPane jSplitPane_main;
    private javax.swing.JLabel least_popular_jlabel;
    private javax.swing.JMenuBar main_menu;
    private javax.swing.JLabel most_popular_film_jlabel;
    private javax.swing.JTabbedPane overview_tabbed_pane;
    private javax.swing.JLabel screenings_in_database_jlabel1;
    private javax.swing.JLabel status_bar;
    private javax.swing.JPanel summary_panel;
    private javax.swing.JComboBox timetable_date_picker_combo;
    private javax.swing.JPanel timetable_panel;
    private javax.swing.JScrollPane timetable_scroll_pane;
    private javax.swing.JToolBar toolbar;
    private javax.swing.JButton view_actions_jbutton;
    private javax.swing.JButton view_films_jbutton;
    private javax.swing.JButton view_historical_data_jbutton;
    private javax.swing.JButton view_screens_jbutton;
    private javax.swing.JButton view_statistical_data_jbutton;
    // End of variables declaration//GEN-END:variables

}
