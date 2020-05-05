/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package annotations;

/**
 *
 * @author rasmu
 */
public interface Server
{
    public void setUpServer(Object serverInterface) throws IllegalArgumentException;

    public void runServer();
}
