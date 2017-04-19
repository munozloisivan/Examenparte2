package Models;

import java.util.List;

/**
 * Created by ivanm on 19/04/2017.
 */
public class Usuario {
    public String name, password;
    private int id;
    public List<Objeto> objectList;

    public Usuario(String name, String password, int id, List<Objeto> objectList) {
        this.name = name;
        this.password = password;
        this.id = id;
        this.objectList = objectList;
    }

    public Usuario() { }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Objeto> getObjectList() {
        return objectList;
    }

    public void setObjectList(List<Objeto> objectList) {
        this.objectList = objectList;
    }
}
