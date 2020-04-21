/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EventListener;

/**
 *
 * @author rasmu
 */
public class Event {
    private CommandType type;
    private String[] param;

    public Event(CommandType type, String[] param) {
        this.type = type;
        this.param = param;
        System.out.println(type);
    }

    public CommandType getType() {
        return type;
    }

    public void setType(CommandType type) {
        this.type = type;
    }

    public String[] getParam() {
        return param;
    }

    public void setParam(String[] param) {
        this.param = param;
    }

    
    
}
