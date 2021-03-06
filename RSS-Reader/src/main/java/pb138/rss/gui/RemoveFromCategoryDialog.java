package pb138.rss.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Vector;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import org.apache.log4j.Logger;
import pb138.rss.category.Category;
import pb138.rss.category.CategoryManager;
import pb138.rss.feed.Container;
import pb138.rss.feed.RssFeed;
import pb138.rss.gui.renderer.CheckboxListCellRenderer;
import pb138.rss.gui.renderer.CheckboxSelectionModel;

/**
 *
 * @author Drimal
 */
public class RemoveFromCategoryDialog extends javax.swing.JDialog {

    private Logger log = Logger.getLogger(RemoveFromCategoryDialog.class);
    private DefaultComboBoxModel<Category> categorySelectorModel;
    private Container feedContainer;
    private List<RssFeed> selectedRssFeeds;
    private DefaultListModel<RssFeed> rssFeedListModel;
    private int returnStatus = RET_CANCEL;

    /**
     * A return status code - returned if Cancel button has been pressed
     */
    public static final int RET_CANCEL = 0;
    /**
     * A return status code - returned if OK button has been pressed
     */
    public static final int RET_OK = 1;

    /**
     * Creates new form RemoveFromCategoryDialog
     */
    public RemoveFromCategoryDialog(java.awt.Frame parent, boolean modal, CategoryManager cman, Container feedContainer) {
        super(parent, modal);
        this.feedContainer = feedContainer;
        this.categorySelectorModel = fillCategorySelectorModel(cman);
        this.rssFeedListModel = new DefaultListModel<>();
        initComponents();

        // Close the dialog when Esc is pressed
        String cancelName = "cancel";
        InputMap inputMap = getRootPane().getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), cancelName);
        ActionMap actionMap = getRootPane().getActionMap();
        actionMap.put(cancelName, new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                doClose(RET_CANCEL);
            }
        });
    }

    private DefaultComboBoxModel<Category> fillCategorySelectorModel(CategoryManager cman) {
        DefaultComboBoxModel<Category> cbModel = new DefaultComboBoxModel<>();
        for (Category cat : cman.getAllCategories()) {
            if (!cat.getName().equals("none")) {
                cbModel.addElement(cat);
            }
        }
        return cbModel;
    }

    /**
     * @return the return status of this dialog - one of RET_OK or RET_CANCEL
     */
    public int getReturnStatus() {
        return returnStatus;
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        removeButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        feedJList = new javax.swing.JList(this.rssFeedListModel);
        categoryComboBox = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();

        setMinimumSize(new java.awt.Dimension(336, 326));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });

        removeButton.setText("Remove");
        removeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeButtonActionPerformed(evt);
            }
        });

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setMinimumSize(new java.awt.Dimension(303, 250));

        jLabel1.setText("Select category:");

        jLabel2.setText("Select feed source:");

        feedJList.setCellRenderer(new CheckboxListCellRenderer());
        feedJList.setSelectionModel(new CheckboxSelectionModel());
        jScrollPane2.setViewportView(feedJList);

        categoryComboBox.setModel(this.categorySelectorModel);
        categoryComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                categoryComboBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jScrollPane2)
                    .addComponent(categoryComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(categoryComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Remove selected feeds from category");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(removeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cancelButton))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 13, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelButton)
                    .addComponent(removeButton))
                .addContainerGap())
        );

        getRootPane().setDefaultButton(removeButton);

        pack();
    }// </editor-fold>                        

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {                                             
        doClose(RET_CANCEL);
    }                                            

    /**
     * Closes the dialog
     */
    private void closeDialog(java.awt.event.WindowEvent evt) {                             
        doClose(RET_CANCEL);
    }                            
    
    private void removeButtonActionPerformed(java.awt.event.ActionEvent evt) {                                             
        List selectedValues = feedJList.getSelectedValuesList();
        boolean removed = false;
        for (Object val : selectedValues) {
            removed = true;
            RssFeed rssFeed = (RssFeed) val;
            rssFeed.setCategory(new Category("none"));
        }
        if (removed) {
            String msg = "Selected ";
            msg += selectedValues.size() > 1 ? "feeds were " : "feed was";
            JOptionPane.showMessageDialog(rootPane, msg + " removed from category");
        }
        doClose(RET_OK);
    }                                            

    private void categoryComboBoxActionPerformed(java.awt.event.ActionEvent evt) {                                                 
        // TODO add your handling code here:
        log.info("Item state in category combobox changed.");

        Vector<RssFeed> vector = new Vector<RssFeed>();
        rssFeedListModel.removeAllElements();
        
        Category selectedCategory = (Category) categoryComboBox.getSelectedItem();
        Set<String> keys = feedContainer.getKeys();
        for (Iterator<String> iterator = keys.iterator(); iterator.hasNext();) {
            String key = iterator.next();
            RssFeed rf = feedContainer.getFromFeedContainer(key);
            if (rf.getCategory().equals(selectedCategory)) {
                log.info("Listing " + rf + " feed");
                vector.add(rf);
            }
        }
        feedJList.setListData(vector);        
    }                                                

    private void doClose(int retStatus) {
        returnStatus = retStatus;
        setVisible(false);
        dispose();
    }

    /**
     * public static void main(String args[]) { try { for (javax.swing.UIManager.LookAndFeelInfo info :
     * javax.swing.UIManager.getInstalledLookAndFeels()) { if ("Nimbus".equals(info.getName())) {
     * javax.swing.UIManager.setLookAndFeel(info.getClassName()); break; } } } catch (ClassNotFoundException ex) {
     * java.util.logging.Logger.getLogger(RemoveFromCategoryDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex); } catch
     * (InstantiationException ex) { java.util.logging.Logger.getLogger(RemoveFromCategoryDialog.class.getName()).log(java.util.logging.Level.SEVERE,
     * null, ex); } catch (IllegalAccessException ex) {
     * java.util.logging.Logger.getLogger(RemoveFromCategoryDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex); } catch
     * (javax.swing.UnsupportedLookAndFeelException ex) {
     * java.util.logging.Logger.getLogger(RemoveFromCategoryDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex); }
     * java.awt.EventQueue.invokeLater(new Runnable() {
     *
     * public void run() { RemoveFromCategoryDialog dialog = new RemoveFromCategoryDialog(new javax.swing.JFrame(), true);
     * dialog.addWindowListener(new java.awt.event.WindowAdapter() {
     *
     * @Override public void windowClosing(java.awt.event.WindowEvent e) { System.exit(0); } }); dialog.setVisible(true); } } ); }
     *
     */
    // Variables declaration - do not modify                     
    private javax.swing.JButton cancelButton;
    private javax.swing.JComboBox categoryComboBox;
    private javax.swing.JList feedJList;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton removeButton;
    // End of variables declaration                   

}
