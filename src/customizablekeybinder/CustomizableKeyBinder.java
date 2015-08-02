/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customizablekeybinder;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JTextArea;
import javax.swing.JWindow;
import javax.swing.KeyStroke;

/**
 * 
 * @Jonnylee719
 * 01/08/2015
 */
public class CustomizableKeyBinder {
    String focusL;
    InputMap imp;
    ActionMap amp;
    JComponent comp;
    
    public CustomizableKeyBinder(JComponent c, String focusLevel){
        comp = c;
        focusL = focusLevel;
        setInputMap();
        setActionMap();
    }
    
    
    public void setInputMap(){
        if(focusL.equals("WHEN_FOCUSED")){
            int mapName = comp.WHEN_FOCUSED;
            imp = comp.getInputMap(mapName);
        }
        else if(focusL.equals("WHEN_ANCESTOR_OF_FOCUSED_COMPONENT")){
            int mapName = comp.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT;
            imp = comp.getInputMap(mapName);
        }
        else if(focusL.equals("WHEN_IN_FOCUSED_WINDOW")){
            int mapName = comp.WHEN_IN_FOCUSED_WINDOW;
            imp = comp.getInputMap(mapName);
        }
    }
    
    public void setActionMap(){
        amp = comp.getActionMap();
    }
    
    public JComponent assignKeyBind(char inputKeyBut, char desiredOutputValue){
        KeyStroke key = KeyStroke.getKeyStroke(inputKeyBut);
        KeyStroke outKey = KeyStroke.getKeyStroke(desiredOutputValue);
        
        String actionName = outKey.toString();
        outKey = (KeyStroke)outKey;
        
        //Creating the action
        KeyAction typeOutAction = new KeyAction(actionName);
        
        imp.put(key, actionName);
        amp.put(actionName, typeOutAction);
        
        return comp;
    }
    
    public void actionResult(){
        System.out.println("Action Performed.");
    }
    
    class KeyAction extends AbstractAction{
            public KeyAction(String actionName){
                super(actionName);
            }
            public void actionPerformed(ActionEvent e){
                actionResult();
            }
        }
        
}
