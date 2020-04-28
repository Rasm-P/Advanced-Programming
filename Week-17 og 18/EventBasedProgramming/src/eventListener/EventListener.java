/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eventListener;

import java.util.Arrays;

/**
 *
 * @author rasmu
 */
public class EventListener implements EventListenerInterface {

    private CommandType type;
    private String name;

    public EventListener(String name, CommandType type) {
        this.type = type;
        this.name = name;
    }

    @Override
    public void callListener(String[] param) {
        System.out.println(name + "; I Have been called: " + Arrays.toString(param));
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
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

}
