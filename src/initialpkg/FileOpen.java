package initialpkg;


import java.awt.BorderLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * This FileOpen will be used to allow the user to maneuver to a desired file through a window explorer and then select the file to continue the program's intended functionality
 * 
 * @author Marc Pelve
 * @since JDK 8
 * @since June 2, 2016
 * @version 1.0
 *
 */
public class FileOpen extends JPanel implements ActionListener{
	
	static private final String newline = "\n";
    JButton openButton;
    JTextArea log;
    JFileChooser fileChooser;
    static String filePath;
 
    /**
     * This is the default constructor for calling this file opener
     */
    public FileOpen() {
        super(new BorderLayout());
        
        fileChooser = new JFileChooser();
        openButton = new JButton("Open a StormData File...");
        
        log = new JTextArea(5,20);
        log.setMargin(new Insets(5,5,5,5));
        log.setEditable(false);
        
        JScrollPane logScrollPane = new JScrollPane(log);
 
        openButton.addActionListener(this);

        JPanel buttonPanel = new JPanel(); 
        buttonPanel.add(openButton);
 
        add(buttonPanel, BorderLayout.PAGE_START);
        add(logScrollPane, BorderLayout.CENTER);
    }
 
    /**
     * This method retrieves the event sent by the action listener on the button and stores the path of the file in order to open it for future use
     * 
     * @param e is an event sent by the action listener on the button
     */
    public void actionPerformed(ActionEvent e) {
 
        if (e.getSource() == openButton) {
            int returnVal = fileChooser.showOpenDialog(FileOpen.this);
 
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                filePath = file.getAbsolutePath().replace("\\", "/");;
                log.append("Opening: " + file.getName() + "." + newline);
                
                if (FileOpen.getPath() != null){
                	log.append("Successfully retreived path: \n" + FileOpen.getPath());
                } else {
                	log.append("File opening did not work, try again");
                }
            } else {
                log.append("Open command cancelled by user." + newline);
            }
            log.setCaretPosition(log.getDocument().getLength());
        } 
    }
 

 
    /**
     * This calls for the class to show the frame and allow the execution of other related methods
     */
    static synchronized void createAndShowGUI() {
    	JFrame frame = new JFrame("StormData by Marc");
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 200);
        frame.add(new FileOpen());
        frame.setVisible(true);
    }
	

    
    /**
     * This retrieves the path that was selected and assigned from the JFileChooser
     * 
     * @return filePath is the full absolute path (string)
     */
    static synchronized String getPath(){
    	return filePath;
    }
    
}