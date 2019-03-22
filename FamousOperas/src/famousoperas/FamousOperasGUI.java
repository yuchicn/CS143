/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package famousoperas;

import java.awt.Desktop;
import java.awt.Toolkit;
import java.awt.print.PrinterException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

/**
 * <pre>
 * File         FamousOperasGUI.java
 * Project      FamousOperas database
 * Description  A class representing the GUI used in a maintaining a operas
 * database.
 * Platform     Windows 10, Netbean 8.2, jdk 1.8.0 101
 * Course       CS 143, SCC
 * Hours        4 hours
 * Date         5/8/2017
 * </pre>
 *
 * @author:	yu-chi.chen
 * @see: javax.swing
 * @see: java.awt.Toolkit
 * @see java.util.ArrayList
 */
public class FamousOperasGUI extends javax.swing.JFrame {

    // class instance ArrayList of Opera objects
    private ArrayList<Opera> operas = new ArrayList<Opera>();

    // external file name of operas
    private final String fileName = "src/famousoperas/Operas.txt";
    //private final String fileNameXML = "src/USACities/Cities.xml";
    DecimalFormat number = new DecimalFormat("#,##0");

    /**
     * Method FamousOperasGUI Description Default constructor of the
     * CitiesGUI.Creates new form Operas form centered, with Add button as
     * default.The operas are read from an external text file, Opera.txt, into
     * an ArrayList of opera type.
     *
     * @see #readFromFile(java.lang.String)
     * @see #displayOperas()
     */
    public FamousOperasGUI() {
        initComponents();
        //set buttonAdd as default
        this.getRootPane().setDefaultButton(addJButton);
        //centers the form at start.
        this.setLocationRelativeTo(null);
        //set icon
        this.setIconImage(Toolkit.getDefaultToolkit().getImage("src/famousoperas/logo.jpg"));
        // Read form an external file Operas.txt and create an
        // ArrayList of Opera objects, cities        
        readFromFile(fileName);

        // Show the city names in the JList
        displayOperas();
    }

    /**
     * Method readFromFile Description Reads operas from a text file that is
     * pipe (|) delimited and creates an instance of the Opera class with the
     * data read. Then the newly created opera is added to the operas database.
     * Uses an object from the ReadFile class to read record.
     *
     * @param file the text file of ball ets
     * @see Opera
     */
    public void readFromFile(String file) {
        operas.clear();
        try {
            FileReader inputFile = new FileReader(file);
            BufferedReader input = new BufferedReader(inputFile);
            //read the first line
            String line = input.readLine();
            while (line != null) {
                Opera opera = new Opera();
                StringTokenizer token = new StringTokenizer(line, "|");
                while (token.hasMoreTokens()) {
                    opera.setName(token.nextToken());
                    opera.setComposer(token.nextToken());
                    opera.setYear(Integer.parseInt(token.nextToken()));
                    opera.setCity(token.nextToken());
                    opera.setSynopsis(token.nextToken());
                    opera.setLink(token.nextToken());
                }
                //add Opera to the arraylist
                operas.add(opera);
                line = input.readLine();
            }
            input.close();
        } catch (FileNotFoundException exp) {
            JOptionPane.showMessageDialog(null, file + " doesnot exist", "File Input Error", JOptionPane.WARNING_MESSAGE);
            //add JFileChooser to find the file
        } catch (IOException exp) {
            JOptionPane.showMessageDialog(null, file + " is corrupt", "File Input Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    /**
     * Method displayOperas() Description Displays operas in JList sorted by
     * year first performed using quick sort algorithm or name using the
     * insertion sort algorithm.
     *
     * @parem void pre-condition Uses the ArrayList operas. post-condition
     * operas ArrayList is sorted and displayed either by name or year first
     * performed
     * @see #selectionSort
     * @see #quickSort(java.util.ArrayList)
     */
    private void displayOperas() {
        int location = operasJList.getSelectedIndex();//index of the opera selected
        String[] operasList = new String[operas.size()];
        if (yearJRadioButtonMenuItem.isSelected()) {
            //sort the operas using quick sort on year
            //and display the names and the years first performed of the operas in ascending order 
            quickSort(operas);
            for (int i = 0; i < operasList.length; i++) {
                operasList[i] = operas.get(i).getName() + ", " + operas.get(i).getYear();
            }
        } else {
            //sort operas using insertion sort by names only and dispaly in JList
            insertionSort(operas);
            for (int i = 0; i < operasList.length; i++) {
                operasList[i] = operas.get(i).getName();
            }
        }
        operasJList.setListData(operasList);
        if (location < 0) {
            operasJList.setSelectedIndex(0);
        } else {
            operasJList.setSelectedIndex(location);
        }
    }

    /**
     * Method showOperaData Description This method is called from within the
     * constructor to display the data for the selected Opera.
     *
     * @param index position of opera in ArrayList
     * @retunr void
     */
    private void showOperaData(int index) {
        nameJTextField.setText(operas.get(index).getName());
        composerJTextField.setText(operas.get(index).getComposer());
        cityJTextField.setText(operas.get(index).getCity());
        yearJTextField.setText(number.format(operas.get(index).getYear()));
        synopsisJTextField.setText(operas.get(index).getSynopsis());
        linkJTextField.setText(operas.get(index).getLink());
    }

    /**
     * Method saveOperas Description This method is called when a opera is added
     * or deleted to save the new operas list in the text file. pre-condition
     * Uses the textfile and te ArrayList operas post-condition opera ArrayList
     * is saved in the text file
     *
     * @parem void
     * @return void
     */
    private void saveOperas() throws IOException {
        try {
            FileWriter fileW = new FileWriter(fileName, false);
            PrintWriter output = new PrintWriter(fileW);
            //loop thorugh and write all the operas to file
            for (int i = 0; i < operas.size(); i++) {
                String line = operas.get(i).getName() + "|"
                        + operas.get(i).getComposer() + "|"
                        + operas.get(i).getYear() + "|"
                        + operas.get(i).getCity() + "|"
                        + operas.get(i).getSynopsis() + "|"
                        + operas.get(i).getLink() + "\n";
                output.write(line);
            }
            output.close();
        } catch (IOException exp) {
            JOptionPane.showMessageDialog(null, fileName + " does not exist", "File input Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    /**
     * Method insertionSort Description Sorts ArrayList operas in ascending
     * order by year. Uses the quick sort algorithm which inserts opera at
     * correct position and shuffles everyone else below that position.
     *
     * @param operas: ArrayList of operas
     */
    public void insertionSort(ArrayList<Opera> operas) {
        int i, j;
        for (i = 0; i < operas.size(); i++) {
            Opera temp = operas.get(i);
            j = i - 1;
            while (j >= 0 && operas.get(j).getName().compareToIgnoreCase(temp.getName()) > 0) {
                operas.set(j + 1, operas.get(j));
                j--;
            }
            operas.set(j + 1, temp);
        }
    }

    /**
     * Method linearSearch() Description Search for a specific opera by its name
     * in the arrayList operas by going through the list linearily
     *
     * @param array the string array of names of the operas
     * @param key the name of the opera being searched
     * @return the index of the opera. return -1 if the opera is not on the list
     */
    public int linearSearch(String[] array, String key) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].toLowerCase().contains(key.toLowerCase())) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Method linearSearchForInt() Description Search for a specific opera by
     * its year in the arrayList operas by going through the list linearily
     *
     * @param array the string array of years first performed of the operas
     * @param integer the key
     * @return the index of the opera. return -1 if the opera is not on the list
     */
    public int linearSearchForInt(int[] array, int integer) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == integer) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Method quickSort() Description Sorts ArrayList operas in ascending order
     * by year. Uses the quick sort algorithm which invoke the recursivequick
     * method
     *
     * @param array:the ArrayList of operas
     * @see #quick
     */
    public void quickSort(ArrayList<Opera> array) {
        if (array == null || array.size() <= 1) {
            return;
        }
        quick(array, 0, array.size() - 1);
    }

    /**
     * Method quick() Description Sorts the ArrayList operas recursively by the
     * quick sort algorithm which uses a pivot point to divede the list in two
     * part and keeps calling itself utiil the list is sorted
     *
     * @param array:the ArrayList of operas
     * @param low :the lowest index of the ArrayList
     * @param high :the highest index f the ArrayList
     */
    public void quick(ArrayList<Opera> array, int low, int high) {
        int i = low, j = high;
        int pivot = array.get((low + high) / 2).getYear();
        //divide into 2 lists
        while (i <= j) {
            while (array.get(j).getYear() > pivot) {
                j--;
            }
            while (array.get(i).getYear() < pivot) {
                i++;
            }
            if (i <= j) //swap and increment/decrement indices
            {
                Opera temp;
                temp = operas.get(i);
                operas.set(i, operas.get(j));
                operas.set(j, temp);
                i++;
                j--;
            }
        }
        //recursion
        if (low < j) {
            quick(array, low, i);
        }
        if (i < high) {
            quick(array, i, high);
        }
    }

    /**
     * Method searchOpera Description Search for a opera by name and highlight
     * if found. pre-condition Uses the ArrayList operas a String of the name of
     * opera being searched. post-condition The opera is selected if found.
     *
     * @param operaName
     * @retunr void
     */
    private void searchOpera(String operaName) {
        for (int i = 0; i < operas.size(); i++) {
            if (operas.get(i).getName().equalsIgnoreCase(operaName)) {
                operasJList.setSelectedIndex(i);
            }
        }
    }

    /**
     * Method operaExists Description To find out if a opera is already exist in
     * the database. Return true if the opera exists, return false if not.
     *
     * @param opera Opera object
     * @return boolean
     */
    private boolean operaExists(Opera opera) {
        //go throgh the list to find the city
        for (int i = 0; i < operas.size(); i++) {
            if (opera.equals(operas.get(i))) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sortButtonGroup = new javax.swing.ButtonGroup();
        listPanel = new javax.swing.JPanel();
        operasListScrollPane = new javax.swing.JScrollPane();
        operasJList = new javax.swing.JList<>();
        displayPanel = new javax.swing.JPanel();
        nameJLabel = new javax.swing.JLabel();
        nameJTextField = new javax.swing.JTextField();
        composerJLabel = new javax.swing.JLabel();
        composerJTextField = new javax.swing.JTextField();
        yearJLabel = new javax.swing.JLabel();
        yearJTextField = new javax.swing.JTextField();
        cityJLabel = new javax.swing.JLabel();
        cityJTextField = new javax.swing.JTextField();
        synopsisJLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        synopsisJTextField = new javax.swing.JTextArea();
        linkJLabel = new javax.swing.JLabel();
        linkJTextField = new javax.swing.JTextField();
        controlPanel = new javax.swing.JPanel();
        addJButton = new javax.swing.JButton();
        editJButton = new javax.swing.JButton();
        deleteJButton = new javax.swing.JButton();
        exitJButton = new javax.swing.JButton();
        titlePanel = new javax.swing.JPanel();
        titleJLabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        operasJMenuBar = new javax.swing.JMenuBar();
        fileJMenu = new javax.swing.JMenu();
        clearJMenu = new javax.swing.JMenuItem();
        printOperaJMenu = new javax.swing.JMenuItem();
        printFormJMenu = new javax.swing.JMenuItem();
        exitJMenu = new javax.swing.JMenuItem();
        sortJMenu = new javax.swing.JMenu();
        nameJRadioButtonMenuItem = new javax.swing.JRadioButtonMenuItem();
        yearJRadioButtonMenuItem = new javax.swing.JRadioButtonMenuItem();
        databaseJMenu = new javax.swing.JMenu();
        addJMenu = new javax.swing.JMenuItem();
        deleteJMenu = new javax.swing.JMenuItem();
        editJMenu = new javax.swing.JMenuItem();
        searchJMenu = new javax.swing.JMenuItem();
        searchByYearJMenu = new javax.swing.JMenuItem();
        searchByCityJMenu = new javax.swing.JMenuItem();
        helpJMenu = new javax.swing.JMenu();
        aboutJMenu = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("FamousOperas");
        setResizable(false);
        getContentPane().setLayout(new java.awt.BorderLayout(10, 10));

        operasJList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                operasJListValueChanged(evt);
            }
        });
        operasListScrollPane.setViewportView(operasJList);

        displayPanel.setLayout(new java.awt.GridLayout(6, 2, 3, 3));

        nameJLabel.setFont(new java.awt.Font("Seravek", 0, 14)); // NOI18N
        nameJLabel.setForeground(new java.awt.Color(102, 102, 102));
        nameJLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        nameJLabel.setText("Name of opera:");
        displayPanel.add(nameJLabel);

        nameJTextField.setEditable(false);
        nameJTextField.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        displayPanel.add(nameJTextField);

        composerJLabel.setFont(new java.awt.Font("Seravek", 0, 14)); // NOI18N
        composerJLabel.setForeground(new java.awt.Color(102, 102, 102));
        composerJLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        composerJLabel.setText("Composer:");
        displayPanel.add(composerJLabel);

        composerJTextField.setEditable(false);
        composerJTextField.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        composerJTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                composerJTextFieldActionPerformed(evt);
            }
        });
        displayPanel.add(composerJTextField);

        yearJLabel.setFont(new java.awt.Font("Seravek", 0, 14)); // NOI18N
        yearJLabel.setForeground(new java.awt.Color(102, 102, 102));
        yearJLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        yearJLabel.setText("Year first performed:");
        displayPanel.add(yearJLabel);

        yearJTextField.setEditable(false);
        yearJTextField.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        displayPanel.add(yearJTextField);

        cityJLabel.setFont(new java.awt.Font("Seravek", 0, 14)); // NOI18N
        cityJLabel.setForeground(new java.awt.Color(102, 102, 102));
        cityJLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        cityJLabel.setText("City where first performed:");
        displayPanel.add(cityJLabel);

        cityJTextField.setEditable(false);
        cityJTextField.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        displayPanel.add(cityJTextField);

        synopsisJLabel.setFont(new java.awt.Font("Seravek", 0, 14)); // NOI18N
        synopsisJLabel.setForeground(new java.awt.Color(102, 102, 102));
        synopsisJLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        synopsisJLabel.setText("Synopsis:");
        displayPanel.add(synopsisJLabel);

        synopsisJTextField.setEditable(false);
        synopsisJTextField.setColumns(20);
        synopsisJTextField.setLineWrap(true);
        synopsisJTextField.setRows(5);
        synopsisJTextField.setToolTipText("");
        synopsisJTextField.setWrapStyleWord(true);
        synopsisJTextField.setAlignmentX(0.0F);
        synopsisJTextField.setAlignmentY(0.0F);
        jScrollPane1.setViewportView(synopsisJTextField);

        displayPanel.add(jScrollPane1);

        linkJLabel.setFont(new java.awt.Font("Seravek", 0, 14)); // NOI18N
        linkJLabel.setForeground(new java.awt.Color(102, 102, 102));
        linkJLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        linkJLabel.setText("Link to Youtube:");
        displayPanel.add(linkJLabel);

        linkJTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                linkJTextFieldActionPerformed(evt);
            }
        });
        displayPanel.add(linkJTextField);

        controlPanel.setMinimumSize(new java.awt.Dimension(403, 50));
        controlPanel.setPreferredSize(new java.awt.Dimension(729, 50));
        controlPanel.setLayout(new java.awt.GridLayout(1, 5, 5, 20));

        addJButton.setForeground(new java.awt.Color(51, 51, 51));
        addJButton.setMnemonic('A');
        addJButton.setText("Add");
        addJButton.setToolTipText("Add a new opera");
        addJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addJButtonActionPerformed(evt);
            }
        });
        controlPanel.add(addJButton);

        editJButton.setForeground(new java.awt.Color(51, 51, 51));
        editJButton.setMnemonic('E');
        editJButton.setText("Edit");
        editJButton.setToolTipText("Edit selected opera");
        editJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editJButtonActionPerformed(evt);
            }
        });
        controlPanel.add(editJButton);

        deleteJButton.setForeground(new java.awt.Color(51, 51, 51));
        deleteJButton.setMnemonic('D');
        deleteJButton.setText("Delete");
        deleteJButton.setToolTipText("Delete selected opera");
        deleteJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteJButtonActionPerformed(evt);
            }
        });
        controlPanel.add(deleteJButton);

        exitJButton.setForeground(new java.awt.Color(51, 51, 51));
        exitJButton.setMnemonic('X');
        exitJButton.setText("Exit");
        exitJButton.setToolTipText("Exit the program");
        exitJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitJButtonActionPerformed(evt);
            }
        });
        controlPanel.add(exitJButton);

        titlePanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51), 2));

        titleJLabel.setFont(new java.awt.Font("Open Sans", 1, 36)); // NOI18N
        titleJLabel.setForeground(new java.awt.Color(51, 51, 51));
        titleJLabel.setText("Famous Operas");

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/famousoperas/L.png"))); // NOI18N

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/famousoperas/R.png"))); // NOI18N

        javax.swing.GroupLayout titlePanelLayout = new javax.swing.GroupLayout(titlePanel);
        titlePanel.setLayout(titlePanelLayout);
        titlePanelLayout.setHorizontalGroup(
            titlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(titlePanelLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addComponent(titleJLabel)
                .addGap(41, 41, 41)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38))
        );
        titlePanelLayout.setVerticalGroup(
            titlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(titlePanelLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(titlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(35, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, titlePanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(titleJLabel)
                .addGap(47, 47, 47))
        );

        javax.swing.GroupLayout listPanelLayout = new javax.swing.GroupLayout(listPanel);
        listPanel.setLayout(listPanelLayout);
        listPanelLayout.setHorizontalGroup(
            listPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, listPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(listPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(listPanelLayout.createSequentialGroup()
                        .addComponent(controlPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8))
                    .addGroup(listPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, listPanelLayout.createSequentialGroup()
                            .addComponent(operasListScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addGap(18, 18, 18)
                            .addComponent(displayPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 444, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(titlePanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(16, 16, 16))
        );
        listPanelLayout.setVerticalGroup(
            listPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(listPanelLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(titlePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(listPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(operasListScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 295, Short.MAX_VALUE)
                    .addComponent(displayPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(controlPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
        );

        getContentPane().add(listPanel, java.awt.BorderLayout.CENTER);

        fileJMenu.setMnemonic('F');
        fileJMenu.setText("File");

        clearJMenu.setMnemonic('C');
        clearJMenu.setText("Clear");
        clearJMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearJMenuActionPerformed(evt);
            }
        });
        fileJMenu.add(clearJMenu);

        printOperaJMenu.setMnemonic('b');
        printOperaJMenu.setText("Print opera");
        printOperaJMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printOperaJMenuActionPerformed(evt);
            }
        });
        fileJMenu.add(printOperaJMenu);

        printFormJMenu.setMnemonic('f');
        printFormJMenu.setText("Print form");
        printFormJMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printFormJMenuActionPerformed(evt);
            }
        });
        fileJMenu.add(printFormJMenu);

        exitJMenu.setMnemonic('t');
        exitJMenu.setText("Exit");
        exitJMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitJMenuActionPerformed(evt);
            }
        });
        fileJMenu.add(exitJMenu);

        operasJMenuBar.add(fileJMenu);

        sortJMenu.setMnemonic('S');
        sortJMenu.setText("Sort");

        sortButtonGroup.add(nameJRadioButtonMenuItem);
        nameJRadioButtonMenuItem.setMnemonic('n');
        nameJRadioButtonMenuItem.setText("By Name");
        nameJRadioButtonMenuItem.setToolTipText("Sort by name and display only name");
        nameJRadioButtonMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameJRadioButtonMenuItemActionPerformed(evt);
            }
        });
        sortJMenu.add(nameJRadioButtonMenuItem);

        sortButtonGroup.add(yearJRadioButtonMenuItem);
        yearJRadioButtonMenuItem.setMnemonic('Y');
        yearJRadioButtonMenuItem.setText("By Year First Performed");
        yearJRadioButtonMenuItem.setToolTipText("Sort by populatoin a nd display name and population");
        yearJRadioButtonMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                yearJRadioButtonMenuItemActionPerformed(evt);
            }
        });
        sortJMenu.add(yearJRadioButtonMenuItem);

        operasJMenuBar.add(sortJMenu);

        databaseJMenu.setMnemonic('t');
        databaseJMenu.setText("Database Management");

        addJMenu.setMnemonic('n');
        addJMenu.setText("Add new opera");
        addJMenu.setToolTipText("Add a new opera");
        addJMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addJMenuActionPerformed(evt);
            }
        });
        databaseJMenu.add(addJMenu);

        deleteJMenu.setMnemonic('e');
        deleteJMenu.setText("Delete opera");
        deleteJMenu.setToolTipText("Delete a opera");
        deleteJMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteJMenuActionPerformed(evt);
            }
        });
        databaseJMenu.add(deleteJMenu);

        editJMenu.setMnemonic('d');
        editJMenu.setText("Edit opera");
        editJMenu.setToolTipText("Edit a opera");
        editJMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editJMenuActionPerformed(evt);
            }
        });
        databaseJMenu.add(editJMenu);

        searchJMenu.setMnemonic('h');
        searchJMenu.setText("Search opera by name");
        searchJMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchJMenuActionPerformed(evt);
            }
        });
        databaseJMenu.add(searchJMenu);

        searchByYearJMenu.setMnemonic('y');
        searchByYearJMenu.setText("Search opera by year");
        searchByYearJMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchByYearJMenuActionPerformed(evt);
            }
        });
        databaseJMenu.add(searchByYearJMenu);

        searchByCityJMenu.setText("Search opera by city");
        searchByCityJMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchByCityJMenuActionPerformed(evt);
            }
        });
        databaseJMenu.add(searchByCityJMenu);

        operasJMenuBar.add(databaseJMenu);

        helpJMenu.setMnemonic('H');
        helpJMenu.setText("Help");

        aboutJMenu.setMnemonic('o');
        aboutJMenu.setText("About");
        aboutJMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutJMenuActionPerformed(evt);
            }
        });
        helpJMenu.add(aboutJMenu);

        operasJMenuBar.add(helpJMenu);

        setJMenuBar(operasJMenuBar);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void composerJTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_composerJTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_composerJTextFieldActionPerformed

    /**
     * Method addJButtonActionPerformed Description Event handler for the add
     * button.Add a new city to the cities ArrayList and save it to the text
     * file.
     *
     * @param evt action event of the add button being clicked
     * @return void
     */
    private void addJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addJButtonActionPerformed
        AddOpera opera = new AddOpera(this, true);
        opera.setVisible(true);
        Opera newOpera = opera.getNewOpera();
        if (newOpera != null) {
            //check if opera already exist
            if (operaExists(newOpera)) {
                JOptionPane.showMessageDialog(null, newOpera.getName() + " already exists.", "Opera exists", JOptionPane.WARNING_MESSAGE);
            } else {
                try {
                    //proceed to add new opera
                    operas.add(newOpera);
                    displayOperas();
                    //highlight the newly add opera
                    searchOpera(newOpera.getName());
                    saveOperas();
                } catch (IOException ex) {
                    Logger.getLogger(FamousOperasGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_addJButtonActionPerformed

    /**
     * Method exitJButtonActionPerformed Description Event handler for the exit
     * button. Exit the program.
     *
     * @param evt action event of the exit button being clicked
     */
    private void exitJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitJButtonActionPerformed
        /// End  program
        System.exit(0);
    }//GEN-LAST:event_exitJButtonActionPerformed

    /**
     * Method clearJMenuActionPerformed Description Event handler for the clear
     * menu item. Clear the operas ArrayLsit
     *
     * @param evt action event of the clear menu item being clicked
     */
    private void clearJMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearJMenuActionPerformed
        try {
            int result = JOptionPane.showConfirmDialog(null, "Are you sure to clear the whole database?", "Clear Database", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
            if (result == JOptionPane.YES_OPTION) {
                operas.clear();
                displayOperas();
                saveOperas();
            }
        } catch (IOException ex) {
            Logger.getLogger(FamousOperasGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_clearJMenuActionPerformed

    /**
     * Method printOperaJMenuActionPerformed Description Event handler for print
     * opera menu item.
     *
     * @param evt action event of the print opera menu item being clicked
     */
    private void printOperaJMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printOperaJMenuActionPerformed
        try {
            // get the cities that is seleted and display all fields in a dynamically
            // created JTextArea & print the area
            int index = operasJList.getSelectedIndex();
            if (index < 0) {
                index = 0;
            }
            Opera operaToPrint = new Opera();
            operaToPrint = operas.get(index);
            JTextArea operaView = new JTextArea();
            String output = "Name of city: " + operaToPrint.getName() + "\n";
            output += "Composer: " + operaToPrint.getComposer() + "\n";
            output += "Year first performed: " + operaToPrint.getYear() + "\n";
            output += "City where first performed: " + operaToPrint.getCity() + "\n";
            output += "Synopsis: " + operaToPrint.getSynopsis() + "\n";
            output += "Link to Youtube: " + operaToPrint.getLink() + "\n";
            operaView.setText(output);
            operaView.print();
        } catch (PrinterException ex) {
            Logger.getLogger(FamousOperasGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_printOperaJMenuActionPerformed

    /**
     * Method nameJRadioButtonMenuItemActionPerformed Description Event handler
     * for sort by name menu item. Call the displayOperas method to sort the
     * operas by name
     *
     * @param evt action event of the sort by name menu item being clicked
     * @see #displayOperas()
     */
    private void nameJRadioButtonMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameJRadioButtonMenuItemActionPerformed
        // display cities sorted by name
        displayOperas();
    }//GEN-LAST:event_nameJRadioButtonMenuItemActionPerformed

    /**
     * Method yearJRadioButtonMenuItemActionPerformed Description Event handler
     * for sort by year menu item. Call the displayOperas method to sort the
     * operas by year first performed
     *
     * @param evt action event of the sort by year menu item being clicked
     * @see #displayOperas()
     */
    private void yearJRadioButtonMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_yearJRadioButtonMenuItemActionPerformed
        // display cities sorted by population
        displayOperas();
    }//GEN-LAST:event_yearJRadioButtonMenuItemActionPerformed

    /**
     * Method operasJListValueChanged, Description an event handler to populate
     * the text fields onto the form with the selected opera from the JList.
     *
     * @return void
     * @parem evt: ListSelectionEvent evt
     * @see #showOperaData(int)
     */
    private void operasJListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_operasJListValueChanged
        //to display the data of the selected opera
        int index = operasJList.getSelectedIndex();
        if (index < 0) {
            index = 0;
        }
        showOperaData(index);
    }//GEN-LAST:event_operasJListValueChanged

    /**
     * Method deleteJButtonActionPerformed Description event handler for
     * deleting te selected opera.
     *
     * @return void
     * @parem evt event of the datele button being clicked
     */
    private void deleteJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteJButtonActionPerformed
        // Delete selected operas
        int index = operasJList.getSelectedIndex();
        int result = JOptionPane.showConfirmDialog(null, "Are you sure in deleting the opera", "Delete opera", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
        if (result == JOptionPane.YES_OPTION) {
            operas.remove(index);
            displayOperas();
            try {
                saveOperas();
            } catch (IOException ex) {
                Logger.getLogger(FamousOperasGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_deleteJButtonActionPerformed

    /**
     * Method searchJMenuItemActionPerformed Description event handler for the
     * search opera menu item. It will search for the opera name input by the
     * user in the operas arrayList and set it as the selected opera. A warning
     * window will pop out if the opera input is not found. pre-condition: Uses
     * the operas ArrayList post-condition: The opera with the name entered is
     * selected
     *
     * @param evt action event of the search opera menu item being selected
     */
    private void searchJMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchJMenuActionPerformed
        //find specified opera input by user
        String operaName = JOptionPane.showInputDialog("Enter name of opera");
        if ((operaName != null) && (operaName.length() > 0)) {
            //sort the JList by name
            nameJRadioButtonMenuItem.setSelected(true);
            displayOperas();
            String[] operaArray = new String[operas.size()];
            for (int i = 0; i < operaArray.length; i++) {
                operaArray[i] = operas.get(i).getName();
            }
            int index = linearSearch(operaArray, operaName);
            if (index == -1) {
                JOptionPane.showMessageDialog(null, operaName + " not found!", "Search result", JOptionPane.WARNING_MESSAGE);
            } else {
                operasJList.setSelectedIndex(index);
            }
        }
    }//GEN-LAST:event_searchJMenuActionPerformed

    /**
     * Method aboutJMenuActionPerformed Description event handler for the about
     * menu item. create a AboutJDialog object to display the about form
     *
     * @param evt action event of the about menu item being clicked
     * @see #AboutJDialog
     */
    private void aboutJMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutJMenuActionPerformed
        // diplay the about form
        AboutJDialog about = new AboutJDialog(this, true);
        about.setVisible(true);
    }//GEN-LAST:event_aboutJMenuActionPerformed

    /**
     * Method addJMenuActionPerformed Description event handler for the add menu
     * item. Call the add button to add a new opera to the operas ArrayList
     *
     * @param evt action event of the add menu item being clicked
     * @see #addJButton
     */
    private void addJMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addJMenuActionPerformed
        // add a new opera
        addJButton.doClick();
    }//GEN-LAST:event_addJMenuActionPerformed

    /**
     * Method deleteJMenuActionPerformed Description event handler for the
     * delete menu item. Call the delete button to delete to the operas
     * ArrayList
     *
     * @param evt action event of the delete menu item being clicked
     * @see #deleteJButton
     */
    private void deleteJMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteJMenuActionPerformed
        // delete a opera
        deleteJButton.doClick();
    }//GEN-LAST:event_deleteJMenuActionPerformed

    /**
     * Method exitJMenuActionPerformed Description Event handler for the exit
     * menu item. Exit from the program.
     *
     * @param evt action event of the exit button being clicked
     * @return void
     */
    private void exitJMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitJMenuActionPerformed
        // end the program
        System.exit(0);
    }//GEN-LAST:event_exitJMenuActionPerformed

    /**
     * Method editJButtonActionPerformed Description Event handler for the edit
     * button. Editing existing opera. Create a EditOpera JDialog.
     *
     * @param evt action event of the edit button being clicked
     * @return void
     * @see EditCity
     */
    private void editJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editJButtonActionPerformed
        //Edit existing opera
        int index = operasJList.getSelectedIndex();
        Opera operaToEdit = new Opera(operas.get(index));
        EditOpera editJDialog = new EditOpera(this, true, operaToEdit);
        editJDialog.setVisible(true);
        if (editJDialog.getNewOpera() != null) {
            try {
                //remove the old opera
                operas.remove(index);
                //add the new opera
                operas.add(editJDialog.getNewOpera());
                //display operas
                displayOperas();
                //save operas
                saveOperas();
            } catch (IOException ex) {
                Logger.getLogger(FamousOperasGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }//GEN-LAST:event_editJButtonActionPerformed

    /**
     * Method editJMenuActionPerformed Description Event handler for the edit
     * menu item. Call the editJButton to edit existing opera
     *
     * @param evt action event of the edit menu item being clicked
     * @return void
     * @see #editJButton
     */
    private void editJMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editJMenuActionPerformed
        //edit the opera
        editJButton.doClick();
    }//GEN-LAST:event_editJMenuActionPerformed

    private void searchByYearJMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchByYearJMenuActionPerformed
        //find specified opera input by user
        String operaYear = JOptionPane.showInputDialog("Enter the year the opera is first performed: ");
        if (!Validation.isInteger(operaYear) || !Validation.isValidYear(operaYear)) {
            JOptionPane.showMessageDialog(this, "Please enter a valid year", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int year = Integer.parseInt(operaYear);
        //sort the JList by year
        yearJRadioButtonMenuItem.setSelected(true);
        displayOperas();
        int[] yearArray = new int[operas.size()];
        for (int i = 0; i < yearArray.length; i++) {
            yearArray[i] = operas.get(i).getYear();
        }
        int index = linearSearchForInt(yearArray, year);
        if (index == -1) {
            JOptionPane.showMessageDialog(null, year + " not found!", "Search result", JOptionPane.WARNING_MESSAGE);
        } else {
            operasJList.setSelectedIndex(index);
        }
    }//GEN-LAST:event_searchByYearJMenuActionPerformed

    private void searchByCityJMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchByCityJMenuActionPerformed
        //find specified opera input by user
        String operaCity = JOptionPane.showInputDialog("Enter name of city where the opera first performed: ");
        if ((operaCity != null) && (operaCity.length() > 0)) {
            //sort the JList by name
            nameJRadioButtonMenuItem.setSelected(true);
            displayOperas();
            String[] cityArray = new String[operas.size()];
            for (int i = 0; i < cityArray.length; i++) {
                cityArray[i] = operas.get(i).getCity();
            }
            int index = linearSearch(cityArray, operaCity);
            if (index == -1) {
                JOptionPane.showMessageDialog(null, operaCity + " not found!", "Search result", JOptionPane.WARNING_MESSAGE);
            } else {
                operasJList.setSelectedIndex(index);
            }
        }
    }//GEN-LAST:event_searchByCityJMenuActionPerformed

    /**
     * Method printFormJMenuActionPerformed Description Event handler for print
     * form menu item. Print the entire form as GUI
     *
     * @param evt action event of the print form menu item being clicked
     * @see PrintUtilities
     */
    private void printFormJMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printFormJMenuActionPerformed
        //print the entire form as GUI -- thank you Marty Hall! :)
        PrintUtilities.printComponent(this);
    }//GEN-LAST:event_printFormJMenuActionPerformed

    private void linkJTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_linkJTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_linkJTextFieldActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        splash Splash = new splash();
        Splash.setVisible(true);
        try {
            for (int i = 0; i <= 100; i++) {
                Thread.sleep(15);
                if (i == 100) {
                    Splash.setVisible(false);
                }
            }
        } catch (Exception e) {
        }
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
            java.util.logging.Logger.getLogger(FamousOperasGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FamousOperasGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FamousOperasGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FamousOperasGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FamousOperasGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem aboutJMenu;
    private javax.swing.JButton addJButton;
    private javax.swing.JMenuItem addJMenu;
    private javax.swing.JLabel cityJLabel;
    private javax.swing.JTextField cityJTextField;
    private javax.swing.JMenuItem clearJMenu;
    private javax.swing.JLabel composerJLabel;
    private javax.swing.JTextField composerJTextField;
    private javax.swing.JPanel controlPanel;
    private javax.swing.JMenu databaseJMenu;
    private javax.swing.JButton deleteJButton;
    private javax.swing.JMenuItem deleteJMenu;
    private javax.swing.JPanel displayPanel;
    private javax.swing.JButton editJButton;
    private javax.swing.JMenuItem editJMenu;
    private javax.swing.JButton exitJButton;
    private javax.swing.JMenuItem exitJMenu;
    private javax.swing.JMenu fileJMenu;
    private javax.swing.JMenu helpJMenu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel linkJLabel;
    private javax.swing.JTextField linkJTextField;
    private javax.swing.JPanel listPanel;
    private javax.swing.JLabel nameJLabel;
    private javax.swing.JRadioButtonMenuItem nameJRadioButtonMenuItem;
    private javax.swing.JTextField nameJTextField;
    private javax.swing.JList<String> operasJList;
    private javax.swing.JMenuBar operasJMenuBar;
    private javax.swing.JScrollPane operasListScrollPane;
    private javax.swing.JMenuItem printFormJMenu;
    private javax.swing.JMenuItem printOperaJMenu;
    private javax.swing.JMenuItem searchByCityJMenu;
    private javax.swing.JMenuItem searchByYearJMenu;
    private javax.swing.JMenuItem searchJMenu;
    private javax.swing.ButtonGroup sortButtonGroup;
    private javax.swing.JMenu sortJMenu;
    private javax.swing.JLabel synopsisJLabel;
    private javax.swing.JTextArea synopsisJTextField;
    private javax.swing.JLabel titleJLabel;
    private javax.swing.JPanel titlePanel;
    private javax.swing.JLabel yearJLabel;
    private javax.swing.JRadioButtonMenuItem yearJRadioButtonMenuItem;
    private javax.swing.JTextField yearJTextField;
    // End of variables declaration//GEN-END:variables
}
