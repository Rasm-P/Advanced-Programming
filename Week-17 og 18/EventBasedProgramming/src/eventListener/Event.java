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
public class Event implements EventInterface{
    private CommandType type;
    private String[] param;

    public Event(CommandType type, String[] param) {
        this.type = type;
        this.param = param;
        System.out.println(type);
    }

    @Override
    public CommandType getType() {
        return type;
    }

    @Override
    public void setType(CommandType type) {
        this.type = type;
    }
    
    @Override
    public String[] getParam() {
        return param;
    }

    @Override
    public void setParam(String[] param) {
        this.param = param;
    }
    
}
