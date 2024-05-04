import java.sql.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.*;

public class Books_Available extends javax.swing.JFrame {

    /**
     * Creates new form Books_Available
     */
    public Books_Available() {
        initComponents();
        setDefaultCloseOperation(Books_Available.DISPOSE_ON_CLOSE);
    }

    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        fetch = new javax.swing.JButton();
        back = new javax.swing.JButton();
        filterByNameField = new javax.swing.JTextField();
        filterByAuthorField = new javax.swing.JTextField();
        filterByCategoryField = new javax.swing.JTextField();
        filterByNameLabel = new javax.swing.JLabel();
        filterByAuthorLabel = new javax.swing.JLabel();
        filterByCategoryLabel = new javax.swing.JLabel();
        applyFilterButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "isbn", "name", "author", "category", "copies"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        fetch.setFont(new java.awt.Font("Segoe UI", 1, 14)); 
        fetch.setText("FETCH");
        fetch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fetchActionPerformed(evt);
            }
        });

        back.setFont(new java.awt.Font("Segoe UI", 1, 14)); 
        back.setText("BACK");
        back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backActionPerformed(evt);
            }
        });

        filterByNameLabel.setText("Filter by Name:");
        filterByAuthorLabel.setText("Filter by Author:");
        filterByCategoryLabel.setText("Filter by Category:");

        applyFilterButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); 
        applyFilterButton.setText("Apply Filter");
        applyFilterButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                applyFilterButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 580, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(fetch, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(back, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(filterByNameLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(filterByNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(filterByAuthorLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(filterByAuthorField, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(filterByCategoryLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(filterByCategoryField, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(applyFilterButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fetch, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(back, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(filterByNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(filterByAuthorField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(filterByCategoryField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(filterByNameLabel)
                    .addComponent(filterByAuthorLabel)
                    .addComponent(filterByCategoryLabel)
                    .addComponent(applyFilterButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }                      

    private void fetchActionPerformed(java.awt.event.ActionEvent evt) {                                      
        // Display all books initially when fetch button is clicked
        displayAllBooks();
    }                                     

    private void backActionPerformed(java.awt.event.ActionEvent evt) {                                     
        // Close the current frame and go back
        this.dispose();
    }                                    

    private void applyFilterButtonActionPerformed(java.awt.event.ActionEvent evt) {                                                   
        // Apply filter based on entered values in filter fields
        applyFilters();
    }                                                  

    private void displayAllBooks() {
        // Retrieve all books from the database and display in the table
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0); // Clear existing rows
        
        String url = "jdbc:mysql://localhost:3306/library?useSSL=false";
        String user = "root";
        String pwd = "Saiteja@18";
        String query = "SELECT * FROM books;";
        
        try (Connection conn = DriverManager.getConnection(url, user, pwd);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery(query)) {
            
            while (rs.next()) {
                String category = rs.getString("category");
                String name = rs.getString("name"); 
                String author = rs.getString("author");
                String isbn = rs.getString("isbn");
                int copies = rs.getInt("copies");
                
                model.addRow(new Object[] {isbn, name, author, category, copies}); 
            }
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage()); 
        }
    }
    
    private void applyFilters() {
        // Apply filters based on entered filter criteria
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0); // Clear existing rows
        
        String url = "jdbc:mysql://localhost:3306/library?useSSL=false";
        String user = "root";
        String pwd = "Saiteja@18";
        
        // Construct the SQL query with filter criteria
        String query = "SELECT * FROM books WHERE 1=1";
        if (!filterByNameField.getText().isEmpty()) {
            query += " AND name LIKE '%" + filterByNameField.getText() + "%'";
        }
        if (!filterByAuthorField.getText().isEmpty()) {
            query += " AND author LIKE '%" + filterByAuthorField.getText() + "%'";
        }
        if (!filterByCategoryField.getText().isEmpty()) {
            query += " AND category LIKE '%" + filterByCategoryField.getText() + "%'";
        }
        
        try (Connection conn = DriverManager.getConnection(url, user, pwd);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery(query)) {
            
            while (rs.next()) {
                String category = rs.getString("category");
                String name = rs.getString("name"); 
                String author = rs.getString("author");
                String isbn = rs.getString("isbn");
                int copies = rs.getInt("copies");
                
                model.addRow(new Object[] {isbn, name, author, category, copies}); 
            }
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage()); 
        }
    }

    public static void main(String args[]) {
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException Books_Available) {
            java.util.logging.Logger.getLogger(Books_Available.class.getName()).log(java.util.logging.Level.SEVERE, null, Books_Available);
        } catch (InstantiationException Books_Available) {
            java.util.logging.Logger.getLogger(Books_Available.class.getName()).log(java.util.logging.Level.SEVERE, null, Books_Available);
        } catch (IllegalAccessException Books_Available) {
            java.util.logging.Logger.getLogger(Books_Available.class.getName()).log(java.util.logging.Level.SEVERE, null, Books_Available);
        } catch (javax.swing.UnsupportedLookAndFeelException Books_Available) {
            java.util.logging.Logger.getLogger(Books_Available.class.getName()).log(java.util.logging.Level.SEVERE, null, Books_Available);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Books_Available().setVisible(true);
            }
        });
    }

    // Variables declaration                     
    private javax.swing.JButton applyFilterButton;
    private javax.swing.JButton back;
    private javax.swing.JButton fetch;
    private javax.swing.JTextField filterByCategoryField;
    private javax.swing.JLabel filterByCategoryLabel;
    private javax.swing.JTextField filterByNameField;
    private javax.swing.JLabel filterByNameLabel;
    private javax.swing.JTextField filterByAuthorField;
    private javax.swing.JLabel filterByAuthorLabel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration                   
}
