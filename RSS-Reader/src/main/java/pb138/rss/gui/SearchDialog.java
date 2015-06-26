/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pb138.rss.gui;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.xml.transform.TransformerException;
import pb138.rss.converter.ContainerToHtmlConverter;
import pb138.rss.feed.RssFeedContainer;
import pb138.rss.file.RssFileWriter;
import pb138.rss.listener.RssFeedContainerChangeListener;
import pb138.rss.search.SearchCondition;
import pb138.rss.search.SearchField;
import pb138.rss.search.SearchManagerImpl;
import pb138.rss.search.SearchQuery;
import pb138.rss.search.SearchQueryManagerImpl;
import pb138.rss.templates.XSLTProcesor;

/**
 *
 * @author Michaela
 */
public class SearchDialog extends javax.swing.JDialog {

    private SearchQueryManagerImpl qman;
    private SearchManagerImpl sman;
    private RssFeedContainer container;
    private DefaultListModel<SearchQuery> listModel;
    private DefaultComboBoxModel<SearchField> comboModelField;
    private DefaultComboBoxModel<SearchCondition> comboModelCond;
    private RssFeedContainer tmp = new RssFeedContainer();
    
    /**
     * A return status code - returned if Cancel button has been pressed
     */
    public static final int RET_CANCEL = 0;
    /**
     * A return status code - returned if OK button has been pressed
     */
    public static final int RET_OK = 1;
    
    /**
     * Creates new form SearchDialog
     * @param parent
     * @param modal
     */
    public SearchDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        listModel = new DefaultListModel<>();
        qman = new SearchQueryManagerImpl();
        sman = new SearchManagerImpl();
        
        // Close the dialog when Esc is pressed
        String cancelName = "cancel";
        InputMap inputMap = getRootPane().getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), cancelName);
        ActionMap actionMap = getRootPane().getActionMap();
        actionMap.put(cancelName, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                doClose(RET_CANCEL);
            }
        });
        
        comboModelField = fillComboModelField();
        searchFieldComboBox.setModel(comboModelField);
        comboModelCond = fillComboModelCond();
        searchConditionComboBox.setModel(comboModelCond);
        
    }
    
    private DefaultComboBoxModel<SearchField> fillComboModelField() {
        DefaultComboBoxModel<SearchField> comboModelField = new DefaultComboBoxModel<>();
        for (SearchField field : SearchField.values()) {
            comboModelField.addElement(field);
        }
        return comboModelField;
    }
    
    private DefaultComboBoxModel<SearchCondition> fillComboModelCond() {
        DefaultComboBoxModel<SearchCondition> comboModelCond = new DefaultComboBoxModel<>();
        for (SearchCondition cond : SearchCondition.values()) {
            comboModelCond.addElement(cond);
        }
        return comboModelCond;
    }
    
    public void setContainer(RssFeedContainer container) {
        this.container = container;
    }
    
    public RssFeedContainer getFilteredContainer() {
        return container;
    }
    
    /**
     * @return the return status of this dialog - one of RET_OK or RET_CANCEL
     */
    public int getReturnStatus() {
        return returnStatus;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        addQueryButton = new javax.swing.JButton();
        searchButton = new javax.swing.JButton();
        searchFieldComboBox = new javax.swing.JComboBox();
        searchConditionComboBox = new javax.swing.JComboBox();
        searchStringField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        allQueriesCheckBox = new javax.swing.JCheckBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        deleteButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        feedsCheckBox = new javax.swing.JCheckBox();
        itemsCheckBox = new javax.swing.JCheckBox();
        returnButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        addQueryButton.setText("Add query");
        addQueryButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addQueryButtonActionPerformed(evt);
            }
        });

        searchButton.setText("Search");
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });

        jLabel3.setText("String");

        jLabel2.setText("Condition");

        jLabel1.setText("Field");

        allQueriesCheckBox.setSelected(true);
        allQueriesCheckBox.setText("All queries?");

        jScrollPane1.setViewportView(jList1);

        deleteButton.setText("Delete");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        feedsCheckBox.setSelected(true);
        feedsCheckBox.setText("Feeds?");

        itemsCheckBox.setSelected(true);
        itemsCheckBox.setText("Items?");

        returnButton.setText("Return last search");
        returnButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                returnButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(searchFieldComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(searchConditionComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(searchStringField, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(addQueryButton)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(feedsCheckBox)
                            .addComponent(itemsCheckBox)
                            .addComponent(allQueriesCheckBox))
                        .addGap(0, 6, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(deleteButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(searchButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(returnButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cancelButton)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchStringField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchFieldComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchConditionComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addQueryButton))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(feedsCheckBox)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(itemsCheckBox)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(allQueriesCheckBox)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(deleteButton)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(searchButton)
                        .addComponent(cancelButton)
                        .addComponent(returnButton)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>                        

    /** 
     * Vytvorenie nového dotazu.
     * @param evt 
     */
    private void addQueryButtonActionPerformed(java.awt.event.ActionEvent evt) {                                               
        SearchQuery query = new SearchQuery((SearchField)searchFieldComboBox.getSelectedItem(), 
                (SearchCondition)searchConditionComboBox.getSelectedItem(), searchStringField.getText());
        qman.addSearchQuery(query);

        listModel.addElement(query);
        jList1.setModel(listModel);
    }                                              

    /** 
     * Spustenie vyhľadávania.
     * @param evt 
     */
    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {                                             
        Set<SearchQuery> queries = qman.getAllQueries();
        
        if (queries.isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "No queries");
            return;
        }
        tmp = container;  
        container = sman.runSearchForContainer(container, queries, 
                allQueriesCheckBox.isSelected(), feedsCheckBox.isSelected(), itemsCheckBox.isSelected());
            
        String filePath = "src/main/java/pb138/rss/file/temporary-feeds.xml";
        String xslPath = "src/main/java/pb138/rss/templates/app-sources.xsl";
        ContainerToHtmlConverter converter = createConverter(container, filePath,xslPath);
        try {
            this.jTextPane1.setText(converter.getString());
            this.listener.setChangeJTextPane(false);
        } catch (TransformerException ex) {
            java.util.logging.Logger.getLogger(ReaderUI.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }  
    
    /** 
     * Vrátenie posledného vyhľadávania.
     * @param evt 
     */
    private void returnButtonActionPerformed(java.awt.event.ActionEvent evt) {
        if (tmp != null)
            container = tmp;
        
        String filePath = "src/main/java/pb138/rss/file/temporary-feeds.xml";
        String xslPath = "src/main/java/pb138/rss/templates/app-sources.xsl";
        ContainerToHtmlConverter converter = createConverter(container, filePath,xslPath);
        try {
            this.jTextPane1.setText(converter.getString());
            this.listener.setChangeJTextPane(false);
        } catch (TransformerException ex) {
            java.util.logging.Logger.getLogger(ReaderUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private javax.swing.JTextPane jTextPane1;
    public void setTextPane(javax.swing.JTextPane textPane) {
        jTextPane1 = textPane;
    }
    
    private RssFeedContainerChangeListener listener;
    public void setListener(RssFeedContainerChangeListener listener) {
        this.listener = listener;
    }
    
    private ContainerToHtmlConverter createConverter(RssFeedContainer container, String filePath, String xslPath) {
        return new ContainerToHtmlConverter(
                        new RssFileWriter(container, filePath),
                        new XSLTProcesor(xslPath),
                        filePath);
    }
    
    private void formWindowClosing(java.awt.event.WindowEvent evt) {                                   
        doClose(RET_CANCEL);
    }                                  

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {                                             
        List<SearchQuery> selected = jList1.getSelectedValuesList();
        for (int i = 0; i < selected.size(); i++) {  
            qman.removeSearchQuery(selected.get(i));
            listModel.removeElement(selected.get(i));
        }        
    }                                            

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {                                             
        doClose(RET_CANCEL);
    }                                            

    private void doClose(int retStatus) {
        returnStatus = retStatus;
        setVisible(false);
        dispose();        
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SearchDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SearchDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SearchDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SearchDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                SearchDialog dialog = new SearchDialog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton addQueryButton;
    private javax.swing.JCheckBox allQueriesCheckBox;
    private javax.swing.JButton cancelButton;
    private javax.swing.JButton deleteButton;
    private javax.swing.JCheckBox feedsCheckBox;
    private javax.swing.JCheckBox itemsCheckBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JList jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton returnButton;
    private javax.swing.JButton searchButton;
    private javax.swing.JComboBox searchConditionComboBox;
    private javax.swing.JComboBox searchFieldComboBox;
    private javax.swing.JTextField searchStringField;
    // End of variables declaration                     

    private int returnStatus = RET_CANCEL;
}
