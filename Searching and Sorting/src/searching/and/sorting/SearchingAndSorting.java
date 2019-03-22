/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package searching.and.sorting;

import java.awt.Toolkit;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 * <pre>
 * File         SearchingAndSorting.java
 * Project      Searching And Sorting
 * Description  A class representing the GUI used sorting numbers
 * Platform     jdk 1.8.2 ; NetBeans IDE 8.2; Windows 10
 * Course       CS 143, SCC
 * Created on   April 14 2017
 * </pre>
 *
 * @author:	Yu-Chi.Chen
 * @see java.util.ArrayList
 */
public class SearchingAndSorting extends javax.swing.JFrame {

    private int[] rand = new int[50];
    private int[] sorted = new int[50];
    private Random random = new Random();
    private int[] array;
    private int[] tempMergArr;
    private int length;
    private int N;
    private long startTime;
    private long stopTime;
    private long elapsedTime;

    /**
     * Method  SearchingAndSorting
     * Description  Default constructor of the SearchingAndSorting.Creates new sorting form
     * form centered, with create button as default.
     * @see CreateData
     */
    public SearchingAndSorting() {
        initComponents();
        this.getRootPane().setDefaultButton(CreateData);
        this.setIconImage(Toolkit.getDefaultToolkit().getImage("src/searching/and/sorting/S.png"));
        this.setTitle("SearchingAndSorting");
        //centers ther form at start.
        setLocationRelativeTo(null);
    }
    /**
     * Method       doBubbleSort
     * Description:  Bubble sort, also referred to as sinking sort, 
     * is a simple sorting algorithm that works by repeatedly stepping 
     * through the list to be sorted, comparing each pair of adjacent 
     * items and swapping them if they are in the wrong order. 
     * The pass through the list is repeated until no swaps are needed, 
     * which indicates that the list is sorted. 
     * @param array ArrayList of numbers
     * @see #swapNumbers(int, int, int[])
     * @return array
     */
    public int[] doBubbleSort(int array[]) {
        int n = array.length;
        int k;
        for (int m = n; m >= 0; m--) {
            for (int i = 0; i < m - 1; i++) {
                k = i + 1;
                if (array[i] > array[k]) {
                    swapNumbers(i, k, array);
                }
            }
        }
        return array;
    }
    /**
     * Method       swapNumbers
     * Description  switching numbers and using this method in doBubbleSort
     * @param i contain value
     * @param j contain new sorted value
     * @param array ArrayList of numbers
     * @see #doBubbleSort(int[]) 
     */
    private void swapNumbers(int i, int j, int[] array) {
        int temp;
        temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
    /**
     * Method       doSelectionSort
     * Description:  The selection sort is a combination of searching 
     * and sorting. During each pass, the unsorted element with the smallest 
     * (or largest) value is moved to its proper position in the array. 
     * The number of times the sort passes through the array is one less than 
     * the number of items in the array. 
     * @param arr ArrayList of numbers
     * @return arr
     */
    public int[] doSelectionSort(int arr[]) {
        for (int i = 0; i < arr.length - 1; i++) {
            int index = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[index]) {
                    index = j;
                }
            }
            int smallerNumber = arr[index];
            arr[index] = arr[i];
            arr[i] = smallerNumber;
        }
        return arr;
    }
    /**
     * Method       doInsertionSort
     * Description: Insertion sort iterates through the list by 
     * consuming one input element at each repetition, and growing 
     * a sorted output list. On a repetition, insertion sort removes one 
     * element from the input data, finds the location it belongs within the 
     * sorted list, and inserts it there. It repeats until no input elements remain.
     * @param input ArrayList of numbers
     * @return input
     */
    public int[] doInsertionSort(int input[]) {
        int temp;
        for (int i = 1; i < input.length; i++) {
            for (int j = i; j > 0; j--) {
                if (input[j] < input[j - 1]) {
                    temp = input[j];
                    input[j] = input[j - 1];
                    input[j - 1] = temp;
                }
            }
        }
        return input;
    }
    /**
     * Method       doQuickSort
     * Description:  Choose an element, called pivot, from the list.
     * Generally pivot can be the middle index element.Reorder the list so that 
     * all elements with values less than the pivot come before the pivot, while 
     * all elements with values greater than the pivot come after it 
     * (equal values can go either way). After this partitioning, the pivot 
     * is in its final position. This is called the partition operation.Recursively 
     * apply the above steps to the sub-list of elements with smaller values and separately 
     * the sub-list of elements with greater values. 
     * @param inputArr ArrayList of numbers
     * @return inputArr
     * @see #quickSort(int, int) 
     * @see #exchangeNumbers(int, int) 
     */
    public int[] doQuickSort(int inputArr[]) {
        if (inputArr == null || inputArr.length == 0) {
            return inputArr;
        }
        this.array = inputArr;
        length = inputArr.length;
        quickSort(0, length - 1);
        return inputArr;
    }
    /**
     * Method       quickSort
     * Description  using for doQuickSort method to sort
     * @param lowerIndex :the lowest index of the ArrayList
     * @param higherIndex :the highest index of the ArrayList
     */
    private void quickSort(int lowerIndex, int higherIndex) {
        int i = lowerIndex;
        int j = higherIndex;
        // calculate pivot number, I am taking pivot as middle index number
        int pivot = array[lowerIndex + (higherIndex - lowerIndex) / 2];
        // Divide into two arrays
        while (i <= j) {
            /**
             * In each iteration, I will identify a number from left side which
             * is greater then the pivot value, and also I will identify a
             * number from right side which is less then the pivot value. Once
             * the search is done, then I exchange both numbers.
             */
            while (array[i] < pivot) {
                i++;
            }
            while (array[j] > pivot) {
                j--;
            }
            if (i <= j) {
                exchangeNumbers(i, j);
                //move index to next position on both sides
                i++;
                j--;
            }
        }
        // call quickSort() method recursively
        if (lowerIndex < j) {
            quickSort(lowerIndex, j);
        }
        if (i < higherIndex) {
            quickSort(i, higherIndex);
        }
    }
    /**
     * Method       exchangeNumbers
     * Description  I exchange both numbers.
     * @param i numbers 
     * @param j numbers
     */
    private void exchangeNumbers(int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
    /**
     * Method       doMergeSort
     * Description: Divide the unsorted array into n partitions,
     * each partition contains 1 element. Here the one element is considered 
     * as sorted.Repeatedly merge partitioned units to produce new sublists until 
     * there is only 1 sublist remaining. This will be the sorted list at the end.
     * @param inputArr ArrayList of numbers
     * @return inputArr
     */
    public int[] doMergeSort(int inputArr[]) {
        this.array = inputArr;
        this.length = inputArr.length;
        this.tempMergArr = new int[length];
        MergeSort(0, length - 1);
        return inputArr;
    }
    /**
     * Method       MergeSort
     * Description  using for doMergeSort method to sort
     * @param lowerIndex :the lowest index of the ArrayList
     * @param higherIndex :the highest index of the ArrayList
     */
    private void MergeSort(int lowerIndex, int higherIndex) {

        if (lowerIndex < higherIndex) {
            int middle = lowerIndex + (higherIndex - lowerIndex) / 2;
            // Below step sorts the left side of the array
            MergeSort(lowerIndex, middle);
            // Below step sorts the right side of the array
            MergeSort(middle + 1, higherIndex);
            // Now merge both sides
            mergeParts(lowerIndex, middle, higherIndex);
        }
    }
    /**
     * Method       mergeParts
     * Description  using in MergeSort
     * @param lowerIndex the lowest index of the ArrayList
     * @param middle the middle index of the ArrayList
     * @param higherIndex the highest index of the ArrayList
     */
    private void mergeParts(int lowerIndex, int middle, int higherIndex) {

        for (int i = lowerIndex; i <= higherIndex; i++) {
            tempMergArr[i] = array[i];
        }
        int i = lowerIndex;
        int j = middle + 1;
        int k = lowerIndex;
        while (i <= middle && j <= higherIndex) {
            if (tempMergArr[i] <= tempMergArr[j]) {
                array[k] = tempMergArr[i];
                i++;
            } else {
                array[k] = tempMergArr[j];
                j++;
            }
            k++;
        }
        while (i <= middle) {
            array[k] = tempMergArr[i];
            k++;
            i++;
        }

    }
    /**
     * Method       doHeapSort
     * Description: This is heap sorting Algorithm
     * @param arr ArrayList of numbers
     * @return arr
     */
    public int[] doHeapSort(int arr[]) {
        heapify(arr);
        for (int i = N; i > 0; i--) {
            swap(arr, 0, i);
            N = N - 1;
            maxheap(arr, 0);
        }
        return arr;
    }
    /**
     * Method       heapify
     * Description: Function to build a heap 
     * @param arr ArrayList of numbers
     */    
    public void heapify(int arr[]) {
        N = arr.length - 1;
        for (int i = N / 2; i >= 0; i--) {
            maxheap(arr, i);
        }
    }
    /**
     * Method       maxheap
     * Description: Function to swap largest element in heap 
     * @param arr ArrayList of numbers
     * @param i number value
     */
    public void maxheap(int arr[], int i) {
        int left = 2 * i;
        int right = 2 * i + 1;
        int max = i;
        if (left <= N && arr[left] > arr[i]) {
            max = left;
        }
        if (right <= N && arr[right] > arr[max]) {
            max = right;
        }

        if (max != i) {
            swap(arr, i, max);
            maxheap(arr, max);
        }
    }
    /**
     * Method       maxheap
     * Description: Function to swap two numbers in an array 
     * @param arr ArrayList of numbers
     * @param i number value
     * @param j number value
     */
    public void swap(int arr[], int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
    /**
     * Method       linearSearch
     * Description: Searches the array A for the integer N. If N is not in the array, then -1
     * is returned. If N is in the array, then the return value is the first
     * integer i that satisfies A[i] == N.
     * @param A array
     * @param N integer
     */
    private int linearSearch(int[] A, int N) {

        for (int index = 0; index < A.length; index++) {
            if (A[index] == N) {
                return index;  // N has been found at this index!
            }
        }

        // If we get this far, then N has not been found
        // anywhere in the array.  Return a value of -1.
        return -1;

    }

    /**
     * Searches the array A for the integer N. Precondition: A must be sorted
     * into increasing order. Postcondition: If N is in the array, then the
     * return value, i, satisfies A[i] == N. If N is not in the array, then the
     * return value is -1.
     */
     /**
     * Method       binarySearch
     * Description: Searches the array A for the integer N. Precondition: A must be sorted
     * into increasing order. Postcondition: If N is in the array, then the
     * return value, i, satisfies A[i] == N. If N is not in the array, then the
     * return value is -1.
     * @param A array
     * @param N integer
     */
    private int binarySearch(int[] A, int N) {

        int lowestPossibleLoc = 0;
        int highestPossibleLoc = A.length - 1;

        while (highestPossibleLoc >= lowestPossibleLoc) {
            int middle = (lowestPossibleLoc + highestPossibleLoc) / 2;
            if (A[middle] == N) {
                // N has been found at this index!
                return middle;
            } else if (A[middle] > N) {
                // eliminate locations >= middle
                highestPossibleLoc = middle - 1;
            } else {
                // eliminate locations <= middle
                lowestPossibleLoc = middle + 1;
            }
        }

        // At this point, highestPossibleLoc < LowestPossibleLoc,
        // which means that N is known to be not in the array.  Return
        // a -1 to indicate that N could not be found in the array.
        return -1;

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        Bubble = new javax.swing.JRadioButton();
        Selection = new javax.swing.JRadioButton();
        Insertion = new javax.swing.JRadioButton();
        Quick = new javax.swing.JRadioButton();
        Merge = new javax.swing.JRadioButton();
        Heap = new javax.swing.JRadioButton();
        jLabel4 = new javax.swing.JLabel();
        LinearSearch = new javax.swing.JRadioButton();
        BinarySearch = new javax.swing.JRadioButton();
        CreateData = new javax.swing.JButton();
        Sort = new javax.swing.JButton();
        Exit = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        linearSearchText = new javax.swing.JTextField();
        binarySearchText = new javax.swing.JTextField();
        TimeText = new javax.swing.JTextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        ClearMenu = new javax.swing.JMenuItem();
        PrintMenu = new javax.swing.JMenuItem();
        ExitMenu = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        BubbleMenu = new javax.swing.JMenuItem();
        SelectionMenu = new javax.swing.JMenuItem();
        InsertionMenu = new javax.swing.JMenuItem();
        QuickMenu = new javax.swing.JMenuItem();
        MergeMenu = new javax.swing.JMenuItem();
        HeapMenu = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        LinearMenu = new javax.swing.JMenuItem();
        BinaryMenu = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        AboutMenu = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane3.setViewportView(jList1);

        jLabel1.setText("Original Values:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(16, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jList2.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane4.setViewportView(jList2);

        jLabel2.setText("Sorted Values:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addContainerGap(20, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Sorting Algorthm :");

        buttonGroup1.add(Bubble);
        Bubble.setText("Bubble");
        Bubble.setEnabled(false);
        Bubble.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BubbleActionPerformed(evt);
            }
        });

        buttonGroup1.add(Selection);
        Selection.setText("Selection");
        Selection.setEnabled(false);
        Selection.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SelectionActionPerformed(evt);
            }
        });

        buttonGroup1.add(Insertion);
        Insertion.setText("Insertion");
        Insertion.setEnabled(false);
        Insertion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InsertionActionPerformed(evt);
            }
        });

        buttonGroup1.add(Quick);
        Quick.setText("Quick");
        Quick.setEnabled(false);
        Quick.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                QuickActionPerformed(evt);
            }
        });

        buttonGroup1.add(Merge);
        Merge.setText("Merge");
        Merge.setEnabled(false);
        Merge.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MergeActionPerformed(evt);
            }
        });

        buttonGroup1.add(Heap);
        Heap.setText("Heap");
        Heap.setEnabled(false);
        Heap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HeapActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Searching Algorthm:");

        buttonGroup2.add(LinearSearch);
        LinearSearch.setText("Linear Search :");
        LinearSearch.setToolTipText("Input a number and press Enter button");
        LinearSearch.setEnabled(false);
        LinearSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LinearSearchActionPerformed(evt);
            }
        });

        buttonGroup2.add(BinarySearch);
        BinarySearch.setText("Binary Search :");
        BinarySearch.setToolTipText("Input a number and press Enter button");
        BinarySearch.setEnabled(false);
        BinarySearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BinarySearchActionPerformed(evt);
            }
        });

        CreateData.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        CreateData.setText("Create Data");
        CreateData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CreateDataActionPerformed(evt);
            }
        });

        Sort.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        Sort.setText("Sort");
        Sort.setEnabled(false);
        Sort.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SortActionPerformed(evt);
            }
        });

        Exit.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        Exit.setText("Exit");
        Exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExitActionPerformed(evt);
            }
        });

        jLabel5.setText("Time (milliseconds):");

        linearSearchText.setEnabled(false);
        linearSearchText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                linearSearchTextActionPerformed(evt);
            }
        });

        binarySearchText.setEnabled(false);
        binarySearchText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                binarySearchTextActionPerformed(evt);
            }
        });

        TimeText.setEditable(false);
        TimeText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TimeTextActionPerformed(evt);
            }
        });

        jMenu1.setText("File");

        ClearMenu.setText("Clear");
        ClearMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ClearMenuActionPerformed(evt);
            }
        });
        jMenu1.add(ClearMenu);

        PrintMenu.setText("Print");
        PrintMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PrintMenuActionPerformed(evt);
            }
        });
        jMenu1.add(PrintMenu);

        ExitMenu.setText("Exit");
        ExitMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExitMenuActionPerformed(evt);
            }
        });
        jMenu1.add(ExitMenu);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Sort");
        jMenu2.setToolTipText("Select sorting Algorithm");

        BubbleMenu.setText("Bubble");
        BubbleMenu.setEnabled(false);
        BubbleMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BubbleMenuActionPerformed(evt);
            }
        });
        jMenu2.add(BubbleMenu);

        SelectionMenu.setText("Selection");
        SelectionMenu.setEnabled(false);
        SelectionMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SelectionMenuActionPerformed(evt);
            }
        });
        jMenu2.add(SelectionMenu);

        InsertionMenu.setText("Insertion");
        InsertionMenu.setEnabled(false);
        InsertionMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InsertionMenuActionPerformed(evt);
            }
        });
        jMenu2.add(InsertionMenu);

        QuickMenu.setText("Quick");
        QuickMenu.setEnabled(false);
        QuickMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                QuickMenuActionPerformed(evt);
            }
        });
        jMenu2.add(QuickMenu);

        MergeMenu.setText("Merge");
        MergeMenu.setEnabled(false);
        MergeMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MergeMenuActionPerformed(evt);
            }
        });
        jMenu2.add(MergeMenu);

        HeapMenu.setText("Heap");
        HeapMenu.setEnabled(false);
        HeapMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HeapMenuActionPerformed(evt);
            }
        });
        jMenu2.add(HeapMenu);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Search");
        jMenu3.setToolTipText("Select searching Algorithm");

        LinearMenu.setText("Linear");
        LinearMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LinearMenuActionPerformed(evt);
            }
        });
        jMenu3.add(LinearMenu);

        BinaryMenu.setText("Binary");
        BinaryMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BinaryMenuActionPerformed(evt);
            }
        });
        jMenu3.add(BinaryMenu);

        jMenuBar1.add(jMenu3);

        jMenu4.setText("Help");

        AboutMenu.setText("About");
        AboutMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AboutMenuActionPerformed(evt);
            }
        });
        jMenu4.add(AboutMenu);

        jMenuBar1.add(jMenu4);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(Bubble)
                    .addComponent(Selection)
                    .addComponent(Insertion)
                    .addComponent(Quick)
                    .addComponent(Merge)
                    .addComponent(Heap)
                    .addComponent(LinearSearch)
                    .addComponent(BinarySearch)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel5)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(TimeText, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(binarySearchText, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(linearSearchText, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(CreateData, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Sort, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(Exit, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Bubble)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Selection)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Insertion)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Quick)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Merge)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Heap)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(LinearSearch)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(linearSearchText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BinarySearch)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(binarySearchText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(TimeText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CreateData, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Sort, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Exit, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    /** 
     * Method  QuickActionPerformed
     * Description  Event handler for the Quick button.
     * @param evt action event of the exit button being clicked
     */
    private void QuickActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_QuickActionPerformed
        startTime = System.currentTimeMillis(); //sorting start
        sorted = doQuickSort(sorted);
        stopTime = System.currentTimeMillis(); //sorting end
        elapsedTime = stopTime - startTime; // print out the time
    }//GEN-LAST:event_QuickActionPerformed
    /** 
     * Method  BubbleActionPerformed
     * Description  Event handler for the Bubble button.
     * @param evt action event of the exit button being clicked
     */
    private void BubbleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BubbleActionPerformed
        startTime = System.currentTimeMillis(); //sorting start
        sorted = doBubbleSort(sorted);
        stopTime = System.currentTimeMillis(); //sorting end
        elapsedTime = stopTime - startTime; // print out the time
    }//GEN-LAST:event_BubbleActionPerformed
    /** 
     * Method  SelectionActionPerformed
     * Description  Event handler for the Select button.
     * @param evt action event of the exit button being clicked
     */
    private void SelectionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SelectionActionPerformed
        startTime = System.currentTimeMillis(); //sorting start
        sorted = doSelectionSort(sorted);
        stopTime = System.currentTimeMillis(); //sorting end
        elapsedTime = stopTime - startTime; // print out the time
    }//GEN-LAST:event_SelectionActionPerformed
    /** 
     * Method  InsertionActionPerformed
     * Description  Event handler for the Insertion button.
     * @param evt action event of the exit button being clicked
     */
    private void InsertionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InsertionActionPerformed
        startTime = System.currentTimeMillis(); //sorting start
        sorted = doInsertionSort(sorted);
        stopTime = System.currentTimeMillis(); //sorting end
        elapsedTime = stopTime - startTime; // print out the time
    }//GEN-LAST:event_InsertionActionPerformed
    /** 
     * Method  MergeActionPerformed
     * Description  Event handler for the Merge button.
     * @param evt action event of the exit button being clicked
     */
    private void MergeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MergeActionPerformed
        startTime = System.currentTimeMillis(); //sorting start
        sorted = doMergeSort(sorted);
        stopTime = System.currentTimeMillis(); //sorting end
        elapsedTime = stopTime - startTime; // print out the time
    }//GEN-LAST:event_MergeActionPerformed
    /** 
     * Method  HeapActionPerformed
     * Description  Event handler for the Heap button.
     * @param evt action event of the exit button being clicked
     */
    private void HeapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HeapActionPerformed
        startTime = System.currentTimeMillis(); //sorting start
        sorted = doHeapSort(sorted);
        stopTime = System.currentTimeMillis(); //sorting end
        elapsedTime = stopTime - startTime; // print out the time
    }//GEN-LAST:event_HeapActionPerformed
    /** 
     * Method  CreateDataActionPerformed
     * Description  Event handler for the Create button. It's a default button
     * and making now random number
     * @param evt action event of the exit button being clicked
     */
    private void CreateDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CreateDataActionPerformed
        for (int i = 0; i < 50; i++) {
            int n = random.nextInt(100) + 1;
            rand[i] = n;
            sorted[i] = n;
        }
        String[] randInt = Arrays.toString(rand).replaceAll("[\\[\\]]", "").split("\\s*,\\s*");
        jList1.setListData(randInt);
        Sort.setEnabled(true);
        Bubble.setEnabled(true);
        Selection.setEnabled(true);
        Insertion.setEnabled(true);
        Quick.setEnabled(true);
        Merge.setEnabled(true);
        Heap.setEnabled(true);
        LinearSearch.setEnabled(true);
        BubbleMenu.setEnabled(true);
        SelectionMenu.setEnabled(true);
        InsertionMenu.setEnabled(true);
        QuickMenu.setEnabled(true);
        MergeMenu.setEnabled(true);
        HeapMenu.setEnabled(true);
    }//GEN-LAST:event_CreateDataActionPerformed
    /** 
     * Method  SortActionPerformed
     * Description  Event handler for the Sort button, and sorted numbers
     * will appear at jList2
     * @param evt action event of the exit button being clicked
     * @see #jList2
     */
    private void SortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SortActionPerformed
        String[] sortedInt = Arrays.toString(sorted).replaceAll("[\\[\\]]", "").split("\\s*,\\s*");
        jList2.setListData(sortedInt);
        BinarySearch.setEnabled(true);
        binarySearchText.setEnabled(true);
        TimeText.setText(String.valueOf(elapsedTime));
    }//GEN-LAST:event_SortActionPerformed
    /** 
     * Method  ExitActionPerformed
     * Description  Event handler for the exit button. Exit the program.
     * @param evt action event of the exit button being clicked
     */
    private void ExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_ExitActionPerformed
    /** 
     * Method  BubbleMenuActionPerformed
     * Description  Event handler for the Bubble Menu, same function as
     * BubbleSort button
     * @param evt action event of the exit button being clicked
     * @see #Bubble
     */
    private void BubbleMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BubbleMenuActionPerformed
        Bubble.doClick();
    }//GEN-LAST:event_BubbleMenuActionPerformed
    /** 
     * Method  SelectionMenuActionPerformed
     * Description  Event handler for the Selection Menu, same function as
     * SelectionSort button
     * @param evt action event of the exit button being clicked
     * @see #Selection
     */
    private void SelectionMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SelectionMenuActionPerformed
        Selection.doClick();
    }//GEN-LAST:event_SelectionMenuActionPerformed
    /** 
     * Method  InsertionMenuActionPerformed
     * Description  Event handler for the Insertion Menu, same function as
     * InsertionSort button
     * @param evt action event of the exit button being clicked
     * @see #Insertion
     */
    private void InsertionMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InsertionMenuActionPerformed
        Insertion.doClick();
    }//GEN-LAST:event_InsertionMenuActionPerformed
    /** 
     * Method  QuickMenuActionPerformed
     * Description  Event handler for the Quick Menu, same function as
     * QuickSort button
     * @param evt action event of the exit button being clicked
     * @see #Quick
     */
    private void QuickMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_QuickMenuActionPerformed
        Quick.doClick();
    }//GEN-LAST:event_QuickMenuActionPerformed
    /** 
     * Method  MergeMenuActionPerformed
     * Description  Event handler for the Merge Menu, same function as
     * MergeSort button
     * @param evt action event of the exit button being clicked
     * @see #Merge
     */
    private void MergeMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MergeMenuActionPerformed
        Merge.doClick();
    }//GEN-LAST:event_MergeMenuActionPerformed
    /** 
     * Method  HeapMenuActionPerformed
     * Description  Event handler for the Heap Menu, same function as
     * HeapSort button
     * @param evt action event of the exit button being clicked
     * @see #Heap
     */
    private void HeapMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HeapMenuActionPerformed
        Heap.doClick();
    }//GEN-LAST:event_HeapMenuActionPerformed
    /** 
     * Method  LinearSearchActionPerformed
     * Description  Event handler for the LinearSearc button
     * @param evt action event of the exit button being clicked
     */
    private void LinearSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LinearSearchActionPerformed
        linearSearchText.setEnabled(true);
        linearSearchText.requestFocus();
        binarySearchText.setEnabled(false);
        binarySearchText.setText("");
    }//GEN-LAST:event_LinearSearchActionPerformed
    /** 
     * Method  BinarySearchActionPerformed
     * Description  Event handler for the BinarySearc button
     * @param evt action event of the exit button being clicked
     */
    private void BinarySearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BinarySearchActionPerformed
        binarySearchText.setEnabled(true);
        binarySearchText.requestFocus();
        linearSearchText.setEnabled(false);
        linearSearchText.setText("");
    }//GEN-LAST:event_BinarySearchActionPerformed
    /** 
     * Method  linearSearchTextActionPerformed
     * Description  Event handler for the LinearSearch Text, and get number
     * from jList1 or jList2
     * @param evt action event of the exit button being clicked
     * @see #jList1
     * @see #jList2
     */
    private void linearSearchTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_linearSearchTextActionPerformed
        int index = 0, key = 0;
        int[] dataArray = new int[rand.length];
        //copy the integer object into int primitive type
        for (int i = 0; i < dataArray.length; i++) {
            dataArray[i] = rand[i];
        }
        key = Integer.parseInt(linearSearchText.getText());
        index = linearSearch(dataArray, key);

        if (index < 0) {
            linearSearchText.setText(key + " is not here!");
        } else {
            linearSearchText.setText(key + " found at " + index);
            jList1.setSelectedIndex(index);
        }
    }//GEN-LAST:event_linearSearchTextActionPerformed
    /** 
     * Method  binarySearchTextActionPerformed
     * Description  Event handler for the BinarySearch Text, and get number
     * from jList2
     * @param evt action event of the exit button being clicked
     * @see #jList2
     */
    private void binarySearchTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_binarySearchTextActionPerformed
        int index = 0, key = 0;
        int[] dataArray = new int[sorted.length];
        //copy the integer object into int primitive type
        for (int i = 0; i < dataArray.length; i++) {
            dataArray[i] = sorted[i];
        }
        key = Integer.parseInt(binarySearchText.getText());
        index = binarySearch(dataArray, key);

        if (index < 0) {
            binarySearchText.setText(key + " is not here!");
        } else {
            binarySearchText.setText(key + " found at " + index);
            jList2.setSelectedIndex(index);
        }
    }//GEN-LAST:event_binarySearchTextActionPerformed
    /** 
     * Method  TimeTextActionPerformed
     * Description  method to record spending time on every sort method
     * @param evt action event of the exit button being clicked
     * @see #SortActionPerformed(java.awt.event.ActionEvent) 
     */
    private void TimeTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TimeTextActionPerformed

    }//GEN-LAST:event_TimeTextActionPerformed
    /** 
     * Method  LinearMenuActionPerformed
     * Description  Event handler for the LinearSearch Menu. Same function
     * as LinearSearch button
     * @param evt action event of the exit button being clicked
     */
    private void LinearMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LinearMenuActionPerformed
        LinearSearch.doClick();
    }//GEN-LAST:event_LinearMenuActionPerformed
    /** 
     * Method  BinaryMenuActionPerformed
     * Description  Event handler for the BinarySearch Menu. Same function
     * as BinarySearch button
     * @param evt action event of the exit button being clicked
     */
    private void BinaryMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BinaryMenuActionPerformed
        BinarySearch.doClick();
    }//GEN-LAST:event_BinaryMenuActionPerformed
    /**
     * Method AboutMenuActionPerformed 
     * Description event handler for the about menu item. 
     * create a AboutDialog object to display the about form
     * @param evt action event of the about menu item being clicked
     * @see #AboutDialog
     */
    private void AboutMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AboutMenuActionPerformed
        // diplay the about form
        AboutDialog About = new AboutDialog(this, true);
        About.setVisible(true);
    }//GEN-LAST:event_AboutMenuActionPerformed
    /** 
     * Method  ClearMenuActionPerformed
     * Description  Event handler for the Clear Menu. Clear data
     * @param evt action event of the exit button being clicked
     */
    private void ClearMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ClearMenuActionPerformed
        int result = JOptionPane.showConfirmDialog(null, "Are you sure to clear the whole database?", "Clear Database", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);        
    }//GEN-LAST:event_ClearMenuActionPerformed
    /** 
     * Method  PrintMenuActionPerformed
     * Description  Event handler for the Print Menu. 
     * @param evt action event of the exit button being clicked
     */
    private void PrintMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PrintMenuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PrintMenuActionPerformed
    /** 
     * Method  ExitMenuActionPerformed
     * Description  Event handler for the exit Menu. Exit the program.
     * @param evt action event of the exit button being clicked
     */
    private void ExitMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExitMenuActionPerformed
        System.exit(0);
    }//GEN-LAST:event_ExitMenuActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        splash Splash = new splash();
        Splash.setVisible(true);
        try {
            for (int i = 0; i <= 100; i++) {
                Thread.sleep(10);
                Splash.LoadingPercent.setText(Integer.toString(i) + "%");
                Splash.LoadingBar.setValue(i);
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
            java.util.logging.Logger.getLogger(SearchingAndSorting.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SearchingAndSorting.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SearchingAndSorting.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SearchingAndSorting.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SearchingAndSorting().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem AboutMenu;
    private javax.swing.JMenuItem BinaryMenu;
    private javax.swing.JRadioButton BinarySearch;
    private javax.swing.JRadioButton Bubble;
    private javax.swing.JMenuItem BubbleMenu;
    private javax.swing.JMenuItem ClearMenu;
    private javax.swing.JButton CreateData;
    private javax.swing.JButton Exit;
    private javax.swing.JMenuItem ExitMenu;
    private javax.swing.JRadioButton Heap;
    private javax.swing.JMenuItem HeapMenu;
    private javax.swing.JRadioButton Insertion;
    private javax.swing.JMenuItem InsertionMenu;
    private javax.swing.JMenuItem LinearMenu;
    private javax.swing.JRadioButton LinearSearch;
    private javax.swing.JRadioButton Merge;
    private javax.swing.JMenuItem MergeMenu;
    private javax.swing.JMenuItem PrintMenu;
    private javax.swing.JRadioButton Quick;
    private javax.swing.JMenuItem QuickMenu;
    private javax.swing.JRadioButton Selection;
    private javax.swing.JMenuItem SelectionMenu;
    private javax.swing.JButton Sort;
    private javax.swing.JTextField TimeText;
    private javax.swing.JTextField binarySearchText;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JList<String> jList1;
    private javax.swing.JList<String> jList2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextField linearSearchText;
    // End of variables declaration//GEN-END:variables
}
