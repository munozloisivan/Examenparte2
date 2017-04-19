package Controlador;

import Models.Objeto;
import Models.Usuario;

/**
 * Created by ivanm on 19/04/2017.
 */
public interface Interfaz {

    void addUser(Usuario user);
    void addObject(Objeto objeto);
    void updateUser(Usuario user, String name, String pass);
    void getUser(String name);
    void getUserObjectsByOrder(Usuario user);
    void listUsersOrderByName();
    void addObjectToUser(Objeto objeto, Usuario user);

}
