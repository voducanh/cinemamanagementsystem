package cinemacontroller.gui;

import cinemacontroller.filmcontroller.Film;
import cinemacontroller.gui.timetablecontrol.*;
import cinemacontroller.main.CinemaSystemController;
import cinemacontroller.screencontroller.Screen;
import cinemacontroller.screencontroller.Screening;
import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ScrollPaneLayout;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import timeanddate.Date;
import timeanddate.Time;

/**
 * This class is the class which creates the main window and all its components. The MainWindow
 * class is passed the 'Controller' object which allows the UI to interface with the core
 * components including rotational engine and database.
 * 
 * @author Scott
 */
public class MainWindow extends javax.swing.JFrame {

    private CinemaSystemController core_controller;

    private final int timetable_hours = 12;
    private ArrayList<TimetableItem> list_of_film_items;
    private TimetableTable timetable_control;


    /**
     * Constructer for the main window. This sets up all the required functionality and also
     * renderes all the componenets.
     */
    public MainWindow(CinemaSystemController core_controller) {
        // Set the main controller
        this.core_controller = core_controller;

        try {
            core_controller.film_manager.addFilmToCinema("James Bond", "Steven Pielberg", "PG", new Date(12, 10, 2009), new Time(2, 30), 100, 10.50);
            core_controller.film_manager.addFilmToCinema("Bugs Life", "Steven Pielberg", "X", new Date(12, 10, 2009), new Time(2, 30), 100, 10.50);
            core_controller.film_manager.addFilmToCinema("Independance Day", "Steven Pielberg", "U", new Date(12, 10, 2009), new Time(2, 30), 100, 10.50);
        } catch (Exception ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            Screening screening1 = new Screening(core_controller.film_manager.getFilmByTitle("James Bond"), new Date(10, 10, 2009), new Time(3, 30));
            core_controller.screen_manager.getScreen(1).addScreening(screening1);

             Screening screening2 = new Screening(core_controller.film_manager.getFilmByTitle("Bugs Life"), new Date(10, 10, 2009), new Time(6, 30));
            core_controller.screen_manager.getScreen(9).addScreening(screening2);

             Screening screening3 = new Screening(core_controller.film_manager.getFilmByTitle("Independance Day"), new Date(10, 10, 2009), new Time(1, 30));
            core_controller.screen_manager.getScreen(3).addScreening(screening3);
        } catch (Exception ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }


       


        // Initialize the ArrayList that contains all the "TimeTableItems" on the timetable control.
        this.list_of_film_items = new ArrayList<TimetableItem>();

        // Creates all the default controls.
        this.initComponents();

        // Creates a new timetable control
        this.timetable_control = createTimetableControl();

        // Creates a scroll pane layout control and adds the timetable to it.
        ScrollPaneLayout layout = new ScrollPaneLayout();
        this.timetable_scroll_pane.setLayout(layout);
        this.timetable_scroll_pane.getViewport().add( timetable_control );


        // Add all internal items to timetable control
        this.automatedGetScreeningsAddToTimetable();
    }


    /**
     * Calculates where the starting column will be for a new timetable instance. Takes a time
     * and returns a int representing column number.
     * 
     * @param hours
     * @param mins
     * @return
     */
    public int calculateStartingColumn(int hours, int mins){
        int block_per_hour = Math.round(this.timetable_control.getColumnCount() / timetable_hours);
        int starting_block = block_per_hour * hours;

        // Make sure we round to the nearest block
        if(mins >= 30){ starting_block++; }

        // Return the starting block
        return starting_block;
    }


    /**
     * This function cycles through all the screens stored within the internal screen database, gets
     * the relevant screenings and adds them to the timetable controller.
     */
    public void automatedGetScreeningsAddToTimetable(){
       for(Screen current_screen : this.core_controller.screen_manager.getScreens()){
         for(Screening current_screening : current_screen.getScreenings()){

            if(current_screen.getScreenings().size() > 0){

             list_of_film_items.add(new TimetableItem(
                                                      current_screening.getFilm().getTitle(),
                                                      new Point(current_screen.getIDNumber(), calculateStartingColumn(current_screening.getStartTime().getHourOfDay(),current_screening.getStartTime().getMinute())) ,
                                                      (calculateStartingColumn(current_screening.getEndTime().getHourOfDay(),current_screening.getEndTime().getMinute()) - calculateStartingColumn(current_screening.getStartTime().getHourOfDay(),current_screening.getStartTime().getMinute())),
                                                      Color.blue,
                                                      Color.white));

            }


         }
       }
    }



    /**
     * Creates a new timetable item by taking a number of values and adding them to the timetable control
     * as well as the internal film database.
     * 
     * @param title
     * @param point
     * @param length
     * @param panelcolor
     * @param textcolor
     */
    public void addTimetableItem(String title, Point point, int length, Color panelcolor, Color textcolor){
        list_of_film_items.add(new TimetableItem(title, point, length, panelcolor, textcolor));
    }


    public TimetableTable createTimetableControl(){
        
       TableModel timetable_model = new DefaultTableModel(20,20);
        System.out.println(timetable_model.getColumnName(0));

        TimetableTable timetable = new TimetableTable( new TimetableItemCellSizer(this.list_of_film_items), timetable_model);
        timetable.setDefaultRenderer(Object.class, new TimetableRenderer(this.list_of_film_items));
       // timetable.setGridColor(Color.white);
        timetable.setRowHeight(60);
        timetable.getTableHeader().setResizingAllowed(false);
        timetable.getTableHeader().setReorderingAllowed(false);

        return timetable;
    }

    /**
     * Refreshes the timetable control if modifications of the controls data have occured.
     */
    public void refreshTimetable(){
        // Refreshes the timetable control
        this.timetable_control.setDefaultRenderer(Object.class, new TimetableRenderer(this.list_of_film_items));

        // Refresh the control components and redraw them
        this.timetable_control.validate();
        this.timetable_control.repaint();
    }























    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSplitPane_main = new javax.swing.JSplitPane();
        timetable_panel = new javax.swing.JPanel();
        timetable_scroll_pane = new javax.swing.JScrollPane();
        timetable_date_picker_combo = new javax.swing.JComboBox();
        overview_tabbed_pane = new javax.swing.JTabbedPane();
        summary_panel = new javax.swing.JPanel();
        actions_panel = new javax.swing.JPanel();
        toolbar = new javax.swing.JToolBar();
        create_new_screening_button = new javax.swing.JButton();
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Multiplex Manager");

        jSplitPane_main.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 5, 1, 5));
        jSplitPane_main.setDividerLocation(250);
        jSplitPane_main.setDividerSize(0);

        timetable_scroll_pane.setBorder(null);

        timetable_date_picker_combo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Monday 12th of Decement 2008" }));

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
                    .addComponent(timetable_scroll_pane, javax.swing.GroupLayout.DEFAULT_SIZE, 818, Short.MAX_VALUE)))
        );
        timetable_panelLayout.setVerticalGroup(
            timetable_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(timetable_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(timetable_date_picker_combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(timetable_scroll_pane, javax.swing.GroupLayout.DEFAULT_SIZE, 611, Short.MAX_VALUE))
        );

        jSplitPane_main.setRightComponent(timetable_panel);

        javax.swing.GroupLayout summary_panelLayout = new javax.swing.GroupLayout(summary_panel);
        summary_panel.setLayout(summary_panelLayout);
        summary_panelLayout.setHorizontalGroup(
            summary_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 245, Short.MAX_VALUE)
        );
        summary_panelLayout.setVerticalGroup(
            summary_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 620, Short.MAX_VALUE)
        );

        overview_tabbed_pane.addTab("Summary", summary_panel);

        javax.swing.GroupLayout actions_panelLayout = new javax.swing.GroupLayout(actions_panel);
        actions_panel.setLayout(actions_panelLayout);
        actions_panelLayout.setHorizontalGroup(
            actions_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 245, Short.MAX_VALUE)
        );
        actions_panelLayout.setVerticalGroup(
            actions_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 620, Short.MAX_VALUE)
        );

        overview_tabbed_pane.addTab("Actions", actions_panel);

        jSplitPane_main.setLeftComponent(overview_tabbed_pane);

        toolbar.setFloatable(false);
        toolbar.setRollover(true);

        create_new_screening_button.setIcon(new javax.swing.ImageIcon("F:\\Documents\\Coursework\\Software Engineering\\Third Deliverable\\Icons\\import.png")); // NOI18N
        create_new_screening_button.setFocusable(false);
        create_new_screening_button.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        create_new_screening_button.setMaximumSize(new java.awt.Dimension(24, 24));
        create_new_screening_button.setPreferredSize(new java.awt.Dimension(32, 32));
        create_new_screening_button.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        create_new_screening_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                create_new_screening_buttonActionPerformed(evt);
            }
        });
        toolbar.add(create_new_screening_button);

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
        help_menu.add(about_menuitem);

        main_menu.add(help_menu);

        setJMenuBar(main_menu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(toolbar, javax.swing.GroupLayout.DEFAULT_SIZE, 1088, Short.MAX_VALUE)
            .addComponent(status_bar, javax.swing.GroupLayout.DEFAULT_SIZE, 1088, Short.MAX_VALUE)
            .addComponent(jSplitPane_main, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1088, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(toolbar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSplitPane_main, javax.swing.GroupLayout.DEFAULT_SIZE, 650, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(status_bar, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void create_new_screening_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_create_new_screening_buttonActionPerformed
        CreateScreening create_screening_window = new CreateScreening(this);
        create_screening_window.setVisible(true);
        this.setEnabled(false);
}//GEN-LAST:event_create_new_screening_buttonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem about_menuitem;
    private javax.swing.JPanel actions_panel;
    private javax.swing.JMenuItem create_new_film_menuitem;
    private javax.swing.JButton create_new_screening_button;
    private javax.swing.JMenuItem create_new_screening_menuitem;
    private javax.swing.JMenu edit_menu;
    private javax.swing.JMenu file_menu;
    private javax.swing.JMenu help_menu;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSplitPane jSplitPane_main;
    private javax.swing.JMenuBar main_menu;
    private javax.swing.JTabbedPane overview_tabbed_pane;
    private javax.swing.JLabel status_bar;
    private javax.swing.JPanel summary_panel;
    private javax.swing.JComboBox timetable_date_picker_combo;
    private javax.swing.JPanel timetable_panel;
    private javax.swing.JScrollPane timetable_scroll_pane;
    private javax.swing.JToolBar toolbar;
    // End of variables declaration//GEN-END:variables

}
