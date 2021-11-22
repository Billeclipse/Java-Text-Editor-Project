package java_project;

import java.awt.*;

public class Java_Project {
    public static void main(String[] args) {    
        JTextEditor myte = new JTextEditor();
        myte.setTitle("Java Text Editor");        
        myte.setSize(new Dimension(600,400));
        myte.setMinimumSize(new Dimension(600,400));
        myte.setLocationRelativeTo(null); 
        myte.setVisible(true);
    }    
}