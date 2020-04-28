/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eventListener;

/**
 *
 * @author rasmu
 */
public interface EventInterface {
    
    public CommandType getType();

    public void setType(CommandType type);
    
    public String[] getParam();

    public void setParam(String[] param);
    
    
}
