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
public class EventListener {

    private CommandType type;
    private String name;

    public EventListener(String name, CommandType type) {
        this.type = type;
        this.name = name;
    }
    
    public void callListener() {
        System.out.println(name + "; I Have been called");
    }

    public CommandType getType() {
        return type;
    }

    public void setType(CommandType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
