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
public interface ServerFront
{
    public void foo();
    public int someOtherMethodNotRelevantToTheServerFront(int a);

    //@ExportMethod
    public String addName(String name);

    //@ExportMethod
    public String hasName(String name);
    //http:www.cphbusiness.dk/MyServer/addName?name="Anders"
}
