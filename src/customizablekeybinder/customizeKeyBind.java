/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customizablekeybinder;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

/**
 *
 * @author Jonathan
 */
public class customizeKeyBind {
    //GUI components
    private JFrame frame;
    private JPanel background;
    private JPanel gridPanel;
    private JLabel valueLabel;
    private JLabel keyLabel;
    private JButton applyBut;
    
    //Data storage the keybind
    private Map keyBind;
    private char[] value = {'1', '2', '3', '4', '5', '6', '7', '8', '9', '0'};
    private ArrayList<JTextField> kTextArray;
    
    public static void main(String[] args){
        customizeKeyBind test = new customizeKeyBind();
        test.setUpGUI();
        
    }
    
    /*public customizeKeyBind(char[] c){
        value = c;
    }*/
    
    public void gettingTF(){
        for(int i=0; i< keyBind.size(); i++){
            char key = (char) keyBind.get(value[i]);
            System.out.println(value[i] + ":  " + key);
        }
    }
    
    public void setUpGUI(){
        try {
            setUIFont(new javax.swing.plaf.FontUIResource("Sans Serif", Font.BOLD, 30));
        }
        catch(Exception e){}
        
        frame = new JFrame("Customizable Key Bind");
        background = new JPanel();
        gridPanel = new JPanel();
        applyBut = new JButton("Apply");
        valueLabel = new JLabel("Value");
        keyLabel = new JLabel("Key");
        
        gridPanel.setLayout(new GridLayout(0,2,2,2));
        gridPanel.add(valueLabel);
        gridPanel.add(keyLabel);
        
        kTextArray = new ArrayList<JTextField>();
        for(int i = 0; i<value.length; i++){
            char keyValue = value[i];
            String keyValueString = keyValue + "";
            JLabel vLabel = new JLabel(keyValueString);
            JTextField kText = new JTextField();
            kTextArray.add(kText);
            
            gridPanel.add(vLabel);
            gridPanel.add(kText);
        }
        
        applyBut.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                applyKeyBind();
                gettingTF();
            }
        });
        
        background.setLayout(new BoxLayout(background, BoxLayout.Y_AXIS));
        background.add(gridPanel);
        background.add(applyBut);
        frame.add(background);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 1000);
        frame.setVisible(true);
    }
    
    public void applyKeyBind(){
        keyBind = new HashMap();
        for(int i = 0; i< kTextArray.size(); i++){
            JTextField curTextF = kTextArray.get(i);
            char currentChar;
            if(curTextF.getText().isEmpty())
                currentChar = value[i];
            else
                currentChar = (curTextF.getText()).charAt(0);
            keyBind.put(value[i], currentChar);
        }
    }
    
    public Map getKeyBind(){
        return keyBind;
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
