package cinemacontroller.gui;

import cinemacontroller.filmcontroller.Film;
import cinemacontroller.gui.timetablecontrol.*;
import cinemacontroller.main.CinemaSystemController;
import cinemacontroller.screencontroller.Screen;
import cinemacontroller.screencontroller.Screening;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.ScrollPaneLayout;
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

    private TimetableTable timetable_control;
    private final int timetable_number_of_columns = 31;

    private GregorianCalendar showing_timetable_date;
    private Screening selected_screening;

    /**
     * Constructer for the main window. This sets up all the required functionality and also
     * Renders all the components.
     */
    public MainWindow(CinemaSystemController core_controller) {
        // Set the main controller
        this.core_controller = core_controller;
        this.showing_timetable_date = new GregorianCalendar();
        
        // Creates all the default controls.
        this.initComponents();
        this.setLocationRelativeTo(null);
        
        // Creates a new timetable control
        this.timetable_control = createTimetableControl();

        // Creates a scroll pane layout control and adds the timetable to it.
        ScrollPaneLayout layout = new ScrollPaneLayout();
        this.timetable_scroll_pane.setLayout(layout);
        this.timetable_scroll_pane.getViewport().add(timetable_control);

        // Update all the controls
        this.updateControls();
    }


    /**
     * Performs all the various updatings of the MainWindow that are associate
     * with most actions. Actions that are not simplier should call their own
     * update methods.
     */
    public void updateControls(){
        // Update the summary panel
        this.updateSummaryPanel();
        this.updateActionPanel();
        this.updateTimetableControl();
        this.updateShowingDateLabel();
    }


    /**
     * Updates the summary panel with summary information.
     */
    private void updateSummaryPanel(){
        this.films_in_database_jlabel.setText("Total Films in Database: " + this.core_controller.film_controller.getFilms().size());
        this.total_screens_in_database_jlabel.setText("Total Screen Count: " + this.core_controller.screen_controller.getScreens().size());
        this.total_screenings_in_database_jlabel.setText("Total Screening Count: " + this.core_controller.screen_controller.getScreeningCount());
    }

    /**
     * Updates the mini actions panel with any actions requiring atttention.
     */
    private void updateActionPanel(){
    }

    /**
     * Updates the main timetable control.
     */
    private void updateTimetableControl(){
        this.clearModel(this.timetable_control.getModel());
        this.refreshTableModel();
    }

    /**
     * Updates the current date control.
     */
    private void updateShowingDateLabel(){
        Format date_format = new SimpleDateFormat("d MMMM yyyy");
        this.timetable_showing_week_jlabel.setText("Schedule for: " + date_format.format(this.showing_timetable_date.getTime()));
    }

     /**
     * Updates the status bar.
     * @param text
     */
    public void updateStatus(String status){
        this.status_bar.setText("  " + status);
    }


    /**
     * Returns a timetable component
     * @return
     */
    public TimetableTable createTimetableControl(){
        TableModel timetable_model = this.createTableColumnsAndHeadersModel();

        // Create the timetable control
        TimetableTable timetable = new TimetableTable(new TimetableItemCellSizer(timetable_model), timetable_model, 12, 9);
        timetable.setDefaultRenderer(Object.class, new TimetableRenderer());
        timetable.setRowHeight(60);

        // Disables resizing and reordering
        timetable.getTableHeader().setResizingAllowed(false);
        timetable.getTableHeader().setReorderingAllowed(false);

        // Sets the column model for the list of screens and sets the min width to 60px
        TableColumn col = timetable.getColumnModel().getColumn(0);
        col.setMinWidth(60);

        // Add a mouse listener
        timetable.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent mouseEvent) { showRightClickMenuTimetable(mouseEvent); }
        });
        
        return timetable;
    }

    /**
     * Generates all the columns and headers for the timetable control.
     * @return
     */
    public TableModel createTableColumnsAndHeadersModel(){
        int screen_counter = 0;

        // The array containing all the row and column cell data
        Object[][] cell_values = new Object [this.core_controller.screen_controller.getScreens().size()][timetable_number_of_columns];

        // Populate the array with the cell data for the screens
        for(Screen current_screen : this.core_controller.screen_controller.getScreens()){
            cell_values[screen_counter][0] = current_screen;
            screen_counter++;
        }

        // Update the timetable control with the new model
        TableModel timetable_model = new javax.swing.table.DefaultTableModel(
                cell_values, new String [] { "Screen", "0900", "", "1000", "", "1100", "", "1200", "", "1300", "", "1400", "", "1500", "", "1600", "", "1700", "", "1800", "", "1900", "", "2000", "", "2100", "", "2200", "", "2300", ""}
        );

        return timetable_model;
    }
    
    public void showRightClickMenuTimetable(MouseEvent mouseEvent){
        int row = timetable_control.rowAtPoint(new Point(mouseEvent.getX(), mouseEvent.getY()));
        int col = timetable_control.columnAtPoint(new Point(mouseEvent.getX(), mouseEvent.getY()));

        for(int row_counter = 0; row_counter < timetable_control.getModel().getRowCount(); row_counter++){
                for(int col_counter = 0; col_counter < timetable_control.getModel().getColumnCount(); col_counter++){
                    Object obj = timetable_control.getModel().getValueAt(row_counter, col_counter);

                    if(obj instanceof TimetableScreeningBox){
                        TimetableScreeningBox box = (TimetableScreeningBox)obj;

                        if((col >= box.getStartColumn() && col < (box.getStartColumn() + box.getBoxSize())) && (box.getTableRow() == row)){

                            selected_screening = box.getScreening();
                        }

                    }

                }
         }

        if(mouseEvent.getButton() == MouseEvent.BUTTON1 && mouseEvent.getClickCount() == 2){
            new EditScreening(this, this.selected_screening).setVisible(true);
        }


         if(mouseEvent.getButton() == MouseEvent.BUTTON3){
            jPopupMenu1.show(timetable_control, mouseEvent.getX(), mouseEvent.getY());
         }

    }



    /**
     * Refreshes the timetable control's table model by adding any new screenings
     * that need to be shown to the new model.
     */
    public void refreshTableModel(){
       TableModel sd = this.timetable_control.getModel();
       this.clearModel(sd);
       
        // Cycles through each screen
        for(Screen current_screen : this.core_controller.screen_controller.getScreens()){
            // Cycles through each screening of the current screen
             for(Screening current_screening : current_screen.getScreenings()){
                 if((this.showing_timetable_date.get(Calendar.DAY_OF_MONTH) == (current_screening.getDate().get(Calendar.DAY_OF_MONTH))) && (this.showing_timetable_date.get(Calendar.MONTH) == current_screening.getDate().get(Calendar.MONTH)) && (this.showing_timetable_date.get(Calendar.YEAR) == current_screening.getDate().get(Calendar.YEAR))){
                     // Creates a box control and adds it to the timetable model
                     TimetableScreeningBox box = new TimetableScreeningBox(current_screening, TimetableColor.getColor(current_screening.getFilm().getColor()), this.timetable_control, this.getScreenRowNumber(current_screen.getID()));
                     sd.setValueAt(box, box.getTableRow(), box.getStartColumn());
                 }
             }
        }
         // Update the timetable control with the new model
         this.timetable_control.setModel(sd);
    }


    /**
     * Clears all the cells in the table model.
     * @param model
     */
    public void clearModel(TableModel model){
        for(int row_counter = 0; row_counter < model.getRowCount(); row_counter++){
            for(int col_counter = 1; col_counter < model.getColumnCount(); col_counter++){
                model.setValueAt(null, row_counter, col_counter);
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
            if(row_value.getID() == screen_id){
                return counter;
            }
        }
        return 0;
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JSeparator();
        jMenuItem6 = new javax.swing.JMenuItem();
        jSplitPane_main = new javax.swing.JSplitPane();
        timetable_panel = new javax.swing.JPanel();
        timetable_showing_week_jlabel = new javax.swing.JLabel();
        timetable_scroll_pane = new javax.swing.JScrollPane();
        next_date_button = new javax.swing.JButton();
        previous_date_button = new javax.swing.JButton();
        overview_tabbed_pane = new javax.swing.JTabbedPane();
        summary_panel = new javax.swing.JPanel();
        film_summary_title_jlabel = new javax.swing.JLabel();
        films_in_database_jlabel = new javax.swing.JLabel();
        most_popular_film_jlabel = new javax.swing.JLabel();
        least_popular_jlabel = new javax.swing.JLabel();
        total_screens_in_database_jlabel = new javax.swing.JLabel();
        film_summary_title_jlabel1 = new javax.swing.JLabel();
        total_screenings_in_database_jlabel = new javax.swing.JLabel();
        film_summary_title_jlabel2 = new javax.swing.JLabel();
        total_screens_in_database_jlabel1 = new javax.swing.JLabel();
        total_screens_in_database_jlabel2 = new javax.swing.JLabel();
        total_screens_in_database_jlabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        actions_panel = new javax.swing.JPanel();
        run_action_manager_jbutton = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        toolbar = new javax.swing.JToolBar();
        create_new_film_button = new javax.swing.JButton();
        jSeparator5 = new javax.swing.JToolBar.Separator();
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
        edit_users_jbutton = new javax.swing.JButton();
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

        jMenuItem5.setText("Edit Film");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItem5);

        jMenuItem2.setText("Edit this Screening");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItem2);

        jMenuItem3.setText("Delete this Screening");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItem3);
        jPopupMenu1.add(jSeparator4);

        jMenuItem6.setText("Set Viewings");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItem6);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Schedule Overview - Multiplex Manager");
        setIconImage(getToolkit().getImage(getClass().getResource("/cinemacontroller/gui/icons/images.png")));

        jSplitPane_main.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 5, 1, 5));
        jSplitPane_main.setDividerLocation(250);
        jSplitPane_main.setDividerSize(0);

        timetable_showing_week_jlabel.setFont(new java.awt.Font("Tahoma", 1, 14));
        timetable_showing_week_jlabel.setText("Schedule for: 9th - 15th March 2009");

        next_date_button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cinemacontroller/gui/icons/control.png"))); // NOI18N
        next_date_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                next_date_buttonActionPerformed(evt);
            }
        });

        previous_date_button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cinemacontroller/gui/icons/control_180.png"))); // NOI18N
        previous_date_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                previous_date_buttonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout timetable_panelLayout = new javax.swing.GroupLayout(timetable_panel);
        timetable_panel.setLayout(timetable_panelLayout);
        timetable_panelLayout.setHorizontalGroup(
            timetable_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(timetable_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(timetable_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(timetable_scroll_pane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 893, Short.MAX_VALUE)
                    .addGroup(timetable_panelLayout.createSequentialGroup()
                        .addComponent(timetable_showing_week_jlabel, javax.swing.GroupLayout.DEFAULT_SIZE, 843, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(previous_date_button, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(next_date_button, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        timetable_panelLayout.setVerticalGroup(
            timetable_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(timetable_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(timetable_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(timetable_showing_week_jlabel, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(timetable_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(previous_date_button)
                        .addComponent(next_date_button)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(timetable_scroll_pane, javax.swing.GroupLayout.DEFAULT_SIZE, 553, Short.MAX_VALUE))
        );

        jSplitPane_main.setRightComponent(timetable_panel);

        summary_panel.setBackground(new java.awt.Color(255, 255, 255));

        film_summary_title_jlabel.setFont(new java.awt.Font("Tahoma", 1, 11));
        film_summary_title_jlabel.setText("Film Summary");

        films_in_database_jlabel.setText("Films in Database: ");

        most_popular_film_jlabel.setText("Most Popular Film: ");

        least_popular_jlabel.setText("Least Popular Film: ");

        total_screens_in_database_jlabel.setText("Total Screen Count:");

        film_summary_title_jlabel1.setFont(new java.awt.Font("Tahoma", 1, 11));
        film_summary_title_jlabel1.setText("Screen Summary");

        total_screenings_in_database_jlabel.setText("Total Screening Count:");

        film_summary_title_jlabel2.setFont(new java.awt.Font("Tahoma", 1, 11));
        film_summary_title_jlabel2.setText("Screening Summary");

        total_screens_in_database_jlabel1.setText("Total Viewings:");

        total_screens_in_database_jlabel2.setText("Average Viewings:");

        total_screens_in_database_jlabel3.setText("Minumum Viewings:");

        jButton1.setText("View Additional Statistics");

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
                    .addComponent(film_summary_title_jlabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                    .addComponent(total_screens_in_database_jlabel, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                    .addComponent(total_screenings_in_database_jlabel, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                    .addComponent(film_summary_title_jlabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                    .addComponent(total_screens_in_database_jlabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                    .addComponent(total_screens_in_database_jlabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                    .addComponent(total_screens_in_database_jlabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING))
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
                .addGap(18, 18, 18)
                .addComponent(film_summary_title_jlabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(total_screens_in_database_jlabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(total_screenings_in_database_jlabel)
                .addGap(18, 18, 18)
                .addComponent(film_summary_title_jlabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(total_screens_in_database_jlabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(total_screens_in_database_jlabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(total_screens_in_database_jlabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 284, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );

        overview_tabbed_pane.addTab("Summary", summary_panel);

        actions_panel.setBackground(new java.awt.Color(255, 255, 255));

        run_action_manager_jbutton.setText("View Additional Actions");

        jButton2.setText("Run");

        jList1.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Move 300 to screen 4", "Move 300 to screen 5", "Move 300 to screen 6", "Remove 300", "More screenings for 007" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jList1);

        javax.swing.GroupLayout actions_panelLayout = new javax.swing.GroupLayout(actions_panel);
        actions_panel.setLayout(actions_panelLayout);
        actions_panelLayout.setHorizontalGroup(
            actions_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, actions_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(actions_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                    .addGroup(actions_panelLayout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(run_action_manager_jbutton)))
                .addContainerGap())
        );
        actions_panelLayout.setVerticalGroup(
            actions_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, actions_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 511, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(actions_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(run_action_manager_jbutton)
                    .addComponent(jButton2))
                .addContainerGap())
        );

        overview_tabbed_pane.addTab("Actions", actions_panel);

        jSplitPane_main.setLeftComponent(overview_tabbed_pane);

        toolbar.setFloatable(false);
        toolbar.setRollover(true);
        toolbar.setMinimumSize(new java.awt.Dimension(117, 28));
        toolbar.setPreferredSize(new java.awt.Dimension(168, 36));

        create_new_film_button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cinemacontroller/gui/icons/dvd_add.png"))); // NOI18N
        create_new_film_button.setToolTipText("Add a screening into the database.");
        create_new_film_button.setFocusable(false);
        create_new_film_button.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        create_new_film_button.setMaximumSize(new java.awt.Dimension(26, 28));
        create_new_film_button.setMinimumSize(new java.awt.Dimension(28, 28));
        create_new_film_button.setPreferredSize(new java.awt.Dimension(32, 32));
        create_new_film_button.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        create_new_film_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                create_new_film_buttonActionPerformed(evt);
            }
        });
        toolbar.add(create_new_film_button);
        toolbar.add(jSeparator5);

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
        view_actions_jbutton.setToolTipText("View actions that are required to keep everything running smoothly.");
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
        view_statistical_data_jbutton.setToolTipText("View film and screen statistics.");
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
        view_historical_data_jbutton.setToolTipText("View archived historical data.");
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
        view_films_jbutton.setToolTipText("Open the film manager to browse, add and edit films stored in the database.");
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

        edit_users_jbutton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cinemacontroller/gui/icons/user.png"))); // NOI18N
        edit_users_jbutton.setToolTipText("View users and add and delete users.");
        edit_users_jbutton.setFocusable(false);
        edit_users_jbutton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        edit_users_jbutton.setMaximumSize(new java.awt.Dimension(26, 28));
        edit_users_jbutton.setMinimumSize(new java.awt.Dimension(28, 28));
        edit_users_jbutton.setPreferredSize(new java.awt.Dimension(32, 32));
        edit_users_jbutton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        edit_users_jbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edit_users_jbuttonActionPerformed(evt);
            }
        });
        toolbar.add(edit_users_jbutton);
        edit_users_jbutton.getAccessibleContext().setAccessibleDescription("View users and add and delete users.");

        status_bar.setText("  Idle.");

        file_menu.setText("File");

        jMenu1.setText("New");

        create_new_film_menuitem.setText("Film");
        create_new_film_menuitem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                create_new_film_menuitemActionPerformed(evt);
            }
        });
        jMenu1.add(create_new_film_menuitem);

        create_new_screening_menuitem.setText("Screening");
        create_new_screening_menuitem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                create_new_screening_menuitemActionPerformed(evt);
            }
        });
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
            .addComponent(status_bar, javax.swing.GroupLayout.DEFAULT_SIZE, 1168, Short.MAX_VALUE)
            .addComponent(jSplitPane_main, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1168, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(toolbar, javax.swing.GroupLayout.PREFERRED_SIZE, 905, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(263, Short.MAX_VALUE))
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
        new StatisticsWindow().setVisible(true);
}//GEN-LAST:event_view_statistical_data_jbuttonActionPerformed

    private void view_films_jbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_view_films_jbuttonActionPerformed
        new FilmList(this).setVisible(true);
}//GEN-LAST:event_view_films_jbuttonActionPerformed

    private void view_screens_jbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_view_screens_jbuttonActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_view_screens_jbuttonActionPerformed

    private void view_historical_data_jbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_view_historical_data_jbuttonActionPerformed
        new HistoricalDatabase(this).setVisible(true);
}//GEN-LAST:event_view_historical_data_jbuttonActionPerformed


    private void create_new_film_menuitemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_create_new_film_menuitemActionPerformed
        new CreateNewFilm(this.core_controller, this).setVisible(true);
    }//GEN-LAST:event_create_new_film_menuitemActionPerformed

    private void create_new_screening_menuitemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_create_new_screening_menuitemActionPerformed
        CreateNewScreening create_screening_window = new CreateNewScreening(this);
        create_screening_window.setVisible(true);
    }//GEN-LAST:event_create_new_screening_menuitemActionPerformed

    private void create_new_film_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_create_new_film_buttonActionPerformed
        new CreateNewFilm(this.core_controller, this).setVisible(true);
}//GEN-LAST:event_create_new_film_buttonActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        try{
            this.core_controller.screen_controller.removeScreening(selected_screening);
            this.core_controller.screen_controller.database_controller.removeScreening(selected_screening);
            this.updateStatus("Screening deleted!");
            this.updateControls();
        }catch(Exception e){
            System.out.println(e);
        }
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        new EditFilm(this.core_controller, this, selected_screening.getFilm()).setVisible(true);
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void next_date_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_next_date_buttonActionPerformed
        this.showing_timetable_date.set(Calendar.DAY_OF_MONTH, this.showing_timetable_date.get(Calendar.DAY_OF_MONTH) + 1);
        this.updateControls();
    }//GEN-LAST:event_next_date_buttonActionPerformed

    private void previous_date_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_previous_date_buttonActionPerformed
        this.showing_timetable_date.set(Calendar.DAY_OF_MONTH, this.showing_timetable_date.get(Calendar.DAY_OF_MONTH) - 1);
        this.updateControls();
    }//GEN-LAST:event_previous_date_buttonActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        new EditScreening(this, this.selected_screening).setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        GregorianCalendar current_time = new GregorianCalendar();
        GregorianCalendar end_time_and_date = new GregorianCalendar();

        end_time_and_date.set(Calendar.HOUR_OF_DAY, this.selected_screening.getEndTime().get(Calendar.HOUR_OF_DAY));
        end_time_and_date.set(Calendar.MINUTE, this.selected_screening.getEndTime().get(Calendar.MINUTE));
        
        end_time_and_date.set(Calendar.DAY_OF_MONTH, this.selected_screening.getDate().get(Calendar.DAY_OF_MONTH));
        end_time_and_date.set(Calendar.MONTH, this.selected_screening.getDate().get(Calendar.MONTH));
        end_time_and_date.set(Calendar.YEAR, this.selected_screening.getDate().get(Calendar.YEAR));

        if(current_time.after(end_time_and_date)){
            new UpdateViewingFigures(this, this.selected_screening).setVisible(true);
        }else{
            JOptionPane.showMessageDialog(null, "You cannot set the viewing figures for a screening that is yet to happen.", "Invalid", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void edit_users_jbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edit_users_jbuttonActionPerformed
        new UserList(this).setVisible(true);
}//GEN-LAST:event_edit_users_jbuttonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem about_menuitem;
    private javax.swing.JPanel actions_panel;
    private javax.swing.JButton create_new_film_button;
    private javax.swing.JMenuItem create_new_film_menuitem;
    private javax.swing.JButton create_new_screening_button;
    private javax.swing.JMenuItem create_new_screening_menuitem;
    private javax.swing.JButton delete_screening_jbutton;
    private javax.swing.JMenu edit_menu;
    private javax.swing.JButton edit_screening_jbutton;
    private javax.swing.JButton edit_users_jbutton;
    private javax.swing.JMenu file_menu;
    private javax.swing.JLabel film_summary_title_jlabel;
    private javax.swing.JLabel film_summary_title_jlabel1;
    private javax.swing.JLabel film_summary_title_jlabel2;
    private javax.swing.JLabel films_in_database_jlabel;
    private javax.swing.JMenu help_menu;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JList jList1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JToolBar.Separator jSeparator5;
    private javax.swing.JSplitPane jSplitPane_main;
    private javax.swing.JLabel least_popular_jlabel;
    private javax.swing.JMenuBar main_menu;
    private javax.swing.JLabel most_popular_film_jlabel;
    private javax.swing.JButton next_date_button;
    private javax.swing.JTabbedPane overview_tabbed_pane;
    private javax.swing.JButton previous_date_button;
    private javax.swing.JButton run_action_manager_jbutton;
    private javax.swing.JLabel status_bar;
    private javax.swing.JPanel summary_panel;
    private javax.swing.JPanel timetable_panel;
    private javax.swing.JScrollPane timetable_scroll_pane;
    private javax.swing.JLabel timetable_showing_week_jlabel;
    private javax.swing.JToolBar toolbar;
    private javax.swing.JLabel total_screenings_in_database_jlabel;
    private javax.swing.JLabel total_screens_in_database_jlabel;
    private javax.swing.JLabel total_screens_in_database_jlabel1;
    private javax.swing.JLabel total_screens_in_database_jlabel2;
    private javax.swing.JLabel total_screens_in_database_jlabel3;
    private javax.swing.JButton view_actions_jbutton;
    private javax.swing.JButton view_films_jbutton;
    private javax.swing.JButton view_historical_data_jbutton;
    private javax.swing.JButton view_screens_jbutton;
    private javax.swing.JButton view_statistical_data_jbutton;
    // End of variables declaration//GEN-END:variables

}
