/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customizablekeybinder;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JWindow;
import javax.swing.KeyStroke;
import javax.swing.UIManager;

/**
 *
 * @author Jonathan
 */
public class Main {
    private CustomizableKeyBinder binder;
    private JFrame frame;
    private JButton iFor1;
    private JPanel background;
    private JTextArea display;
    
    public static void main(String[] args){
        Main testing = new Main();
        testing.setGUI();
        
    }
    public void setGUI(){
        try{
            setUIFont(new javax.swing.plaf.FontUIResource("Sans Serif", Font.BOLD, 30));
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
        frame = new JFrame("Testing");
        background = new JPanel();
        iFor1 = new JButton("iFor1");
        display = new JTextArea("0");
        String focusLevel = "WHEN_IN_FOCUSED_WINDOW";
        display.setEditable(false);
        
        setUpKeyBinder(iFor1, focusLevel);
        iFor1 = (JButton) binder.assignKeyBind('i', '1');
               
        
        background.add(iFor1);
        
        frame.addWindowFocusListener(new WindowAdapter() {
         public void windowGainedFocus(WindowEvent e) {
        iFor1.requestFocusInWindow();
        }
        });
        
        
        frame.getContentPane().add(display, BorderLayout.NORTH);
        frame.getContentPane().add(background, BorderLayout.SOUTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 1000);
        frame.setVisible(true);
    }
    
    public void setUpKeyBinder(JButton but, String fl){
        binder = new CustomizableKeyBinder(but, fl);
    }
    
    public static void setUIFont(javax.swing.plaf.FontUIResource f){   
        java.util.Enumeration keys = UIManager.getDefaults().keys();
        while(keys.hasMoreElements()){
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if(value instanceof javax.swing.plaf.FontUIResource) 
                UIManager.put(key, f);
        }
    }
}
