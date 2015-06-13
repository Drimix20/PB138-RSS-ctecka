package pb138.rss.gui;

import java.io.File;
import java.net.URLDecoder;
import java.security.CodeSource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import pb138.rss.configuration.ConfigurationLoader;
import pb138.rss.configuration.ConfigurationSaver;
import pb138.rss.feed.RssFeedContainer;
import pb138.rss.reader.downloader.RssFeedDownloader;
import pb138.rss.reader.downloader.RssFeedReaderTask;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.xml.transform.TransformerException;
import org.apache.log4j.Logger;
import pb138.rss.templates.XSLTProcesor;

/**
 *
 * @author Richard Zember
 * @UCO 422178
 */
public class ReaderUI extends javax.swing.JFrame {

    private static final Logger logger = Logger.getLogger(ReaderUI.class);
    private RssFeedContainer feedContainer;
    private RssFeedDownloader downloader;
    private List<RssFeedReaderTask> tasks;

    /**
     * Creates new form ReaderUI
     */
    public ReaderUI() {
        initComponents();

        tasks = new ArrayList<>();
        feedContainer = new RssFeedContainer();
        downloader = new RssFeedDownloader(feedContainer, 3);

        try {
            File inputFile = new File(getJarContainingFolder(ReaderUI.class) + File.separator + "configuration.xml");
            ConfigurationLoader loader = new ConfigurationLoader(inputFile, feedContainer);
            tasks = loader.loadConfiguration();
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
        downloader.schedule(tasks);
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        addFeedButton = new javax.swing.JButton();
        exportButton = new javax.swing.JButton();
        removeFeedButton = new javax.swing.JButton();
        selectCatButton = new javax.swing.JButton();
        showAllButton = new javax.swing.JButton();
        addToCatButton = new javax.swing.JButton();
        feedSelector = new javax.swing.JComboBox();
        removeFromCatButton = new javax.swing.JButton();
        createCatButton = new javax.swing.JButton();
        deleteCatButton = new javax.swing.JButton();
        closeButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        categorySelector = new javax.swing.JComboBox();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 242, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 110, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        addFeedButton.setText("Add Feed");
        addFeedButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addFeedButtonActionPerformed(evt);
            }
        });

        removeFeedButton.setText("Remove Feed");
        removeFeedButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeFeedButtonActionPerformed(evt);
            }
        });

        selectCatButton.setText("Select Category");
        selectCatButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectCatButtonActionPerformed(evt);
            }
        });

        showAllButton.setText("Show All");
        showAllButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showAllButtonActionPerformed(evt);
            }
        });

        addToCatButton.setText("Add to Category");
        addToCatButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addToCatButtonActionPerformed(evt);
            }
        });

        feedSelector.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"Item 1", "Item 2", "Item 3", "Item 4"}));
        feedSelector.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                feedSelectorActionPerformed(evt);
            }
        });

        removeFromCatButton.setText("Remove from Category");
        removeFromCatButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeFromCatButtonActionPerformed(evt);
            }
        });

        createCatButton.setText("Create Category");
        createCatButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createCatButtonActionPerformed(evt);
            }
        });

        deleteCatButton.setText("Delete Category");
        deleteCatButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteCatButtonActionPerformed(evt);
            }
        });

        exportButton.setText("Export to HTML");
        exportButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportButtonActionPerformed(evt);
            }
        });

        closeButton.setText("Close");
        closeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeButtonActionPerformed(evt);
            }
        });

        jTextPane1.setEditable(false);
        jTextPane1.setContentType("text/html");
        String initialText = "<html>\n"
                + "<h2>The feed contents will be displayed here.\n</h2>"
                + "<p>This text is not editable by the user, but can be selected and copied.</p><br/>"
                + "<strong><em>HTML</em></strong> can be displayed.";

        jTextPane1.setText(initialText);
        jScrollPane2.setViewportView(jTextPane1);

        categorySelector.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"Item 1", "Item 2", "Item 3", "Item 4"}));
        categorySelector.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                categorySelectorActionPerformed(evt);
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
                                        .addComponent(addFeedButton, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(removeFeedButton, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(51, 51, 51)
                                        .addComponent(showAllButton, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(selectCatButton, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 146, Short.MAX_VALUE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jScrollPane2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(addToCatButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(createCatButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(removeFromCatButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(deleteCatButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(exportButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(feedSelector, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(closeButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(categorySelector, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(addFeedButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(removeFeedButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(showAllButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(selectCatButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(feedSelector, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(categorySelector, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(addToCatButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(removeFromCatButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(55, 55, 55)
                                        .addComponent(createCatButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(deleteCatButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(55, 55, 55)
                                        .addComponent(exportButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 121, Short.MAX_VALUE)
                                        .addComponent(closeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jScrollPane2))
                        .addContainerGap())
        );

        pack();
    }// </editor-fold>

    private void addFeedButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        AddFeedDialog dialog = new AddFeedDialog(this, rootPaneCheckingEnabled);
        dialog.setRssFeedContainer(feedContainer);
        dialog.setRssFeedTasks(tasks);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);

        waitForStatus(dialog.getReturnStatus());

        downloader.schedule(tasks);
    }

    private void removeFeedButtonActionPerformed(java.awt.event.ActionEvent evt) {
        logger.info("Count of reader task: " + tasks.size());
        List<String> labels = new ArrayList<>();
        for (RssFeedReaderTask label : this.tasks) {
            labels.add(label.getLabel());
        }
        RemoveFeedDialog dialog = new RemoveFeedDialog(this, rootPaneCheckingEnabled,
                "Select configuration for remove:", "Remove", "Cancel", labels);
        dialog.setFeedReaderTask(tasks);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);

        waitForStatus(dialog.getReturnStatus());

        downloader.schedule(tasks);
    }

    private void waitForStatus(int status) {
        while (status == -1) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException ex) {
                logger.warn(ex.getMessage());
            }
        }
    }

    private void selectCatButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void showAllButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void addToCatButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void removeFromCatButtonActionPerformed(java.awt.event.ActionEvent evt) {
        //TODO add your handling code here:
    }

    private void feedSelectorActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void categorySelectorActionPerformed(java.awt.event.ActionEvent evt) {
        SelectCategoryDialog dialog = new SelectCategoryDialog(this, rootPaneCheckingEnabled, Arrays.asList("Categorie1", "Categorie2", "Categorie3"));
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    private void createCatButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        NewCategoryDialog dialog = new NewCategoryDialog(this, rootPaneCheckingEnabled);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    private void deleteCatButtonActionPerformed(java.awt.event.ActionEvent evt) {
        //TODO empty list ma byt list kategorii
        DeleteCategoryDialog dialog = new DeleteCategoryDialog(this, rootPaneCheckingEnabled, Arrays.asList("Categorie1", "Categorie2", "Categorie3"));
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    private void exportButtonActionPerformed(java.awt.event.ActionEvent evt) {
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File("."));
        chooser.setDialogTitle("File");
        if (chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            String savePath = chooser.getSelectedFile().toString();
            String rssPath = "src/main/java/pb138/rss/";
            try {
                XSLTProcesor procesor = new XSLTProcesor(rssPath + "templates/sources.xsl");
                procesor.transform(rssPath + "file/feeds.xml", savePath);
                JOptionPane.showMessageDialog(null, "Successfully exported");
            } catch (TransformerException ex) {
                logger.warn(ex.getMessage());
                JOptionPane.showMessageDialog(null, "Error while exporting");
            }
        }
    }

    private void closeButtonActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            File outputFile = new File(getJarContainingFolder(ReaderUI.class) + File.separator + "configuration.xml");
            ConfigurationSaver saver = new ConfigurationSaver(outputFile);
            saver.saveConfiguration(tasks);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
        System.exit(0);
    }

    private String getJarContainingFolder(Class aclass) throws Exception {
        CodeSource codeSource = aclass.getProtectionDomain().getCodeSource();

        File jarFile;

        if (codeSource.getLocation() != null) {
            jarFile = new File(codeSource.getLocation().toURI());
        } else {
            String path = aclass.getResource(aclass.getSimpleName() + ".class").getPath();
            String jarFilePath = path.substring(path.indexOf(":") + 1, path.indexOf("!"));
            jarFilePath = URLDecoder.decode(jarFilePath, "UTF-8");
            jarFile = new File(jarFilePath);
        }
        return jarFile.getParentFile().getAbsolutePath();
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
            java.util.logging.Logger.getLogger(AddFeedDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddFeedDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddFeedDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddFeedDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ReaderUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify
    private javax.swing.JButton addFeedButton;
    private javax.swing.JButton exportButton;
    private javax.swing.JButton addToCatButton;
    private javax.swing.JComboBox categorySelector;
    private javax.swing.JButton closeButton;
    private javax.swing.JButton createCatButton;
    private javax.swing.JButton deleteCatButton;
    private javax.swing.JComboBox feedSelector;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JButton removeFeedButton;
    private javax.swing.JButton removeFromCatButton;
    private javax.swing.JButton selectCatButton;
    private javax.swing.JButton showAllButton;
    // End of variables declaration
}
