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
public interface EventListenerInterface {
    
    public CommandType getType();

    public void setType(CommandType type);

    public String getName();

    public void setName(String name);

    public void callListener(String[] param);
    
}
