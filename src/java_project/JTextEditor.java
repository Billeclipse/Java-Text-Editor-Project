package java_project;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.text.DecimalFormat;
import javax.swing.border.Border;

public class JTextEditor extends JFrame implements WindowListener,ActionListener,KeyListener {
    //Declaring Objects
    JMenuBar mnBar;
    JMenu mnFile,mnEdit;
    JMenuItem mnOpen,mnSave,mnSaveAs,mnClear,mnCopy,mnExit;
    JPanel pnButtons,pnTxtField,pnTxtArea,pnStatistics;
    JTextField pathFld;
    JTextArea textAr;
    JButton openBtn;
    JButton clearBtn;
    JButton saveBtn;
    JButton copyBtn;
    JLabel curCharsLbl;
    JLabel charsLbl;    
    JLabel charsNSLbl;
    JLabel wordsLbl;
    JLabel paragraphsLbl;
    JLabel fileSizeLbl;
    //Initializing Static Strings
    private static final String file = "File";
    private static final String edit = "Edit";
    private static final String open = "Open";
    private static final String exit = "Exit";
    private static final String clear = "Clear";
    private static final String save = "Save";
    private static final String saveAs = "Save As";
    private static final String copy = "Copy";
    private static final String chars = "Characters: ";
    private static final String words = "| Words: ";
    private static final String paragraphs = "| Paragraphs: ";
    private static final String curChars = "Current Characters: ";
    private static final String charsNS = "| Char NoSpaces: ";
    private static final String fileSize = "| Size of File: ";  
    private static final String error = "Error ";    
    private static final String msg = "Message";
    private static final String erFile = "with opening File: ";
    private static final String erNameOfFile = "name of the file is empty!";  
    private static final String erOpen = "file selected is not txt!";
    
    public JTextEditor() {
        //Initializing Objects
        BorderLayout bl2 = new BorderLayout(0,20);
        Border blackLine,raisedBevel;
        blackLine = BorderFactory.createLineBorder(Color.black);        
        raisedBevel = BorderFactory.createRaisedBevelBorder();
        KeyStroke keyStrokeToOpen = KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK);
        KeyStroke keyStrokeToSave = KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK);
        //Dimensions Objects
        Dimension txtFldDim = new Dimension(400,25);
        Dimension btnDim = new Dimension(80,30);
        //Menu Objects
        mnBar = new JMenuBar();
        mnFile= new JMenu(file);
        mnFile.setMnemonic(KeyEvent.VK_F);
        mnBar.add(mnFile);
        mnEdit= new JMenu(edit);
        mnEdit.setMnemonic(KeyEvent.VK_E);
        mnBar.add(mnEdit);
        mnOpen = new JMenuItem(open,new ImageIcon("src/images/open.png"));  
        mnOpen.setAccelerator(keyStrokeToOpen);
        mnOpen.addActionListener(this);
        mnFile.add(mnOpen);
        mnSave = new JMenuItem(save,new ImageIcon("src/images/save.png"));
        mnSave.setAccelerator(keyStrokeToSave);
        mnSave.addActionListener(this);
        mnFile.add(mnSave);
        mnSaveAs = new JMenuItem(saveAs,new ImageIcon("src/images/save_as.png"));
        mnSaveAs.addActionListener(this);
        mnFile.add(mnSaveAs);
        mnFile.addSeparator();
        mnExit = new JMenuItem(exit,new ImageIcon("src/images/exit.png"));
        mnExit.addActionListener(this);
        mnFile.add(mnExit);
        mnClear = new JMenuItem(clear,new ImageIcon("src/images/clear.png"));
        mnClear.addActionListener(this);
        mnEdit.add(mnClear);
        mnCopy = new JMenuItem(copy,new ImageIcon("src/images/copy.png"));
        mnCopy.addActionListener(this);
        mnEdit.add(mnCopy);
        //Panel Objects         
        pnButtons = new JPanel();        
        pnTxtField = new JPanel();
        pnTxtField.setLayout(bl2); 
        pnStatistics = new JPanel();
        pnStatistics.setBorder(raisedBevel);
        //Buttons Objects
        openBtn = new JButton(open);
        openBtn.addActionListener(this);
        openBtn.setPreferredSize(btnDim);
        openBtn.setMaximumSize(btnDim);
        pnButtons.add(openBtn);
        clearBtn = new JButton(clear);
        clearBtn.addActionListener(this);
        clearBtn.setPreferredSize(btnDim);
        clearBtn.setMaximumSize(btnDim);
        pnButtons.add(clearBtn);
        saveBtn = new JButton(save);
        saveBtn.addActionListener(this);
        saveBtn.setPreferredSize(btnDim);
        saveBtn.setMaximumSize(btnDim);
        pnButtons.add(saveBtn);
        copyBtn = new JButton(copy);        
        copyBtn.addActionListener(this);
        copyBtn.setPreferredSize(btnDim);
        copyBtn.setMaximumSize(btnDim);
        pnButtons.add(copyBtn);
        //TextField Objects
        pathFld = new JTextField();
        pathFld.setPreferredSize(txtFldDim);
        pathFld.setMinimumSize(txtFldDim);        
        pathFld.setBorder(blackLine);
        pnTxtField.add(pathFld,BorderLayout.PAGE_START);
        //TextArea Objects
        textAr = new JTextArea();        
        textAr.addKeyListener(this);
        textAr.setBorder(blackLine);
        JScrollPane scroll = new JScrollPane(textAr);
        pnTxtField.add(scroll,BorderLayout.CENTER);     
        //Labels Objects
        curCharsLbl = new JLabel(curChars+"0");
        pnTxtField.add(curCharsLbl,BorderLayout.PAGE_END);
        charsLbl = new JLabel(chars+"0");
        pnStatistics.add(charsLbl);
        charsNSLbl = new JLabel(charsNS+"0");
        pnStatistics.add(charsNSLbl);
        wordsLbl = new JLabel(words+"0");
        pnStatistics.add(wordsLbl);
        paragraphsLbl = new JLabel(paragraphs+"0");
        pnStatistics.add(paragraphsLbl);        
        fileSizeLbl = new JLabel(fileSize+"0"+" Bytes");
        pnStatistics.add(fileSizeLbl);
        // Adds and Sets on JFrame
        BorderLayout bl = new BorderLayout(0,5);
        this.setLayout(bl);
        this.addWindowListener(this);
        this.setJMenuBar(mnBar);
        this.add(pnButtons,BorderLayout.PAGE_START);
        this.add(pnTxtField,BorderLayout.CENTER);
        this.add(pnStatistics,BorderLayout.PAGE_END);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();        
        switch (cmd){
            case open: open(); break;
            case clear: clear(); break;
            case save: save(); break;
            case saveAs: saveAs(); break;
            case copy: copy(); break;
            case exit: System.exit(0); break;
        }
    }

    private void copy(){
        String choice3 = JOptionPane.showInputDialog("Give the name of the file: ");
        if(choice3!=null && choice3.trim().length()>0){
            String dir2 = "./"+choice3+".txt";
            openFileForWrite(dir2);
        }else{
            JOptionPane.showMessageDialog(this, error+erNameOfFile, error+msg, JOptionPane.ERROR_MESSAGE);
        }
    }

    private void saveAs(){
        JFileChooser choice2 = new JFileChooser();
        //choice2.setCurrentDirectory(new File("C://"));
        int option2 = choice2.showSaveDialog(this);
        if (option2 == JFileChooser.APPROVE_OPTION) {
            String dir = choice2.getSelectedFile()+".txt";
            openFileForWrite(dir);
        }
    }

    private void save(){
        if (pathFld.getText()!=null && pathFld.getText().trim().length()>0){
            String dir;
            if(pathFld.getText().contains(".txt"))
                dir = pathFld.getText();
            else
                dir = pathFld.getText()+".txt";
            openFileForWrite(dir);
            
        }else{
            JFileChooser choice2 = new JFileChooser();
            //choice2.setCurrentDirectory(new File("C://"));
            int option2 = choice2.showSaveDialog(this);
            if (option2 == JFileChooser.APPROVE_OPTION) {
                String dir = choice2.getSelectedFile()+".txt";
                openFileForWrite(dir);
            }
        }
    }

    private void clear(){
        if(!pathFld.getText().isEmpty())
            pathFld.setText("");
        if(!textAr.getText().isEmpty())
            textAr.setText("");
        curCharsLbl.setText(curChars+"0");
        charsLbl.setText(chars+"0");
        wordsLbl.setText(words+"0");
        paragraphsLbl.setText(paragraphs+"0");
        charsNSLbl.setText(charsNS+"0");
        fileSizeLbl.setText(fileSize+"0"+" Bytes");
    }

    private void open(){
        JFileChooser choice = new JFileChooser();
        //choice.setCurrentDirectory(new File("C://"));
        int option = choice.showOpenDialog(this);
        if (option == JFileChooser.APPROVE_OPTION){
            if(choice.getSelectedFile().toString().contains(".txt")){
                try (FileReader fr = new FileReader(choice.getSelectedFile().toString())) {            
                    int cntChars = 0,cntSpaces = 0,cntNewLines = 0;
                    int cntWords,cntParagraphs;
                    char input;
                    File selected = choice.getSelectedFile();
                    long FileSize=selected.length(); //Size of the file
                    pathFld.setText(choice.getSelectedFile().toString());
                    textAr.setText("");            
                    while (fr.ready()) {
                        input = (char) fr.read();
                        textAr.setText(textAr.getText()+input);
                        //Checking for spaces,enters and tabs
                        if(input == ' ' || input == '\t')
                            cntSpaces++;
                        if(input == '\n' || input == '\r')
                            cntNewLines++;
                        cntChars++;
                    }
                    fr.close();   
                    curCharsLbl.setText(curChars+cntChars); //Print Current Characters Label
                    charsLbl.setText(chars+cntChars); //Print Characters Label                    
                    charsNSLbl.setText(charsNS+(cntChars-cntSpaces-cntNewLines)); //Print Characters with no spaces Label
                    cntParagraphs=cntNewLines+1;
                    paragraphsLbl.setText(paragraphs+cntParagraphs); //Print Paragraphs Label
                    cntWords=cntSpaces+cntNewLines+1;
                    wordsLbl.setText(words+cntWords); //Print Words Label
                    //Print FileSize Label
                    if(FileSize>=1000){
                        double newFileSize=FileSize/1000.00;
                        DecimalFormat FileSizeFormat = new DecimalFormat("#.00");
                        fileSizeLbl.setText(fileSize+FileSizeFormat.format(newFileSize)+" KBytes");                            
                    }else
                        fileSizeLbl.setText(fileSize+FileSize+" Bytes");
                } catch(IOException ex) {
                    JOptionPane.showMessageDialog(this, error+erFile+ex.toString(), error+msg, JOptionPane.ERROR_MESSAGE);
                }
            }else
                JOptionPane.showMessageDialog(this, error+erOpen, error+msg, JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void openFileForWrite(String dir){
        String txt = textAr.getText();
        try(FileWriter fw = new FileWriter(dir)) {            
            fw.write(txt);
            fw.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, error+erFile+ex.toString(), error+msg, JOptionPane.ERROR_MESSAGE);
        }
    }
    
    @Override
    public void keyTyped(KeyEvent e) {}
    
    @Override
    public void keyPressed(KeyEvent ke) {
        curCharsLbl.setText(curChars+textAr.getText().length());
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        curCharsLbl.setText(curChars+textAr.getText().length()); 
    } 
    
    @Override
    public void windowOpened(WindowEvent e) {}

    @Override
    public void windowClosing(WindowEvent e) {
        System.exit(0);
    }

    @Override
    public void windowClosed(WindowEvent e) {}

    @Override
    public void windowIconified(WindowEvent e) {}

    @Override
    public void windowDeiconified(WindowEvent e) {}

    @Override
    public void windowActivated(WindowEvent e) {}

    @Override
    public void windowDeactivated(WindowEvent e) {}
}