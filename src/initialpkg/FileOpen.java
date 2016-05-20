package initialpkg;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;


public class FileOpen extends JPanel implements ActionListener{
	
	static private final String newline = "\n";
    JButton openButton, saveButton;
    JTextArea log;
    JFileChooser fc;
    static String filePath;
 
    public FileOpen() {
        super(new BorderLayout());
 

        log = new JTextArea(5,20);
        log.setMargin(new Insets(5,5,5,5));
        log.setEditable(false);
        JScrollPane logScrollPane = new JScrollPane(log);
 
        fc = new JFileChooser();
 
       
        openButton = new JButton("Open a File...");
        openButton.addActionListener(this);

        saveButton = new JButton("Save a File...");
        saveButton.addActionListener(this);
 

        JPanel buttonPanel = new JPanel(); 
        buttonPanel.add(openButton);
        buttonPanel.add(saveButton);
 

        add(buttonPanel, BorderLayout.PAGE_START);
        add(logScrollPane, BorderLayout.CENTER);
    }
 
    public void actionPerformed(ActionEvent e) {
 

        if (e.getSource() == openButton) {
            int returnVal = fc.showOpenDialog(FileOpen.this);
 
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                filePath = file.getAbsolutePath();
                log.append("Opening: " + file.getName() + "." + newline);
                if (FileOpen.getPath() != null){
                	log.append("Successfully retreived path: \n" + FileOpen.getPath());
                }
            } else {
                log.append("Open command cancelled by user." + newline);
            }
            log.setCaretPosition(log.getDocument().getLength());
 

        } else if (e.getSource() == saveButton) {
            int returnVal = fc.showSaveDialog(FileOpen.this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                
                log.append("Saving: " + file.getName() + "." + newline);
            } else {
                log.append("Save command cancelled by user." + newline);
            }
            log.setCaretPosition(log.getDocument().getLength());
        }
    }
 

 
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event dispatch thread.
     */
    static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("File Chooser");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        //Add content to the window.
        frame.add(new FileOpen());
 
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
	
    static String getPath(){
    	return filePath;
    }
    
}
