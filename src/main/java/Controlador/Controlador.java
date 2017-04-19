package Controlador;

import Models.Objeto;
import Models.Usuario;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import java.util.*;


/**
 * Created by ivanm on 19/04/2017.
 */
public class Controlador implements Interfaz {



    static final Logger logger = Logger.getLogger(Controlador.class);
    private static Controlador instance;

    public HashMap<String, Usuario> usuarioHashMap;
    public HashMap<Integer, Objeto> objetoHashMap;
    public Controlador(){
        BasicConfigurator.configure();
        usuarioHashMap = new HashMap<String, Usuario>();
        objetoHashMap = new HashMap<Integer, Objeto>();
    }

    public static Controlador getInstance(){
        if (instance == null) instance = new Controlador();
        return instance;
    }

    int idObjetoGenerated = 0;
    int idObjetoAdded = 0;
    int idUsuarioAdded = 0;

    //añadir usuario
    public void addUser(Usuario user) {
        user.setId(idUsuarioAdded);
        usuarioHashMap.put(user.getName(), user);
        idUsuarioAdded++;
        logger.info("Usuario "+user.getName()+"con contraseña"+user.getPassword());
    }

    public void addObject(Objeto objeto){
        objeto.setId(idObjetoGenerated);
        objetoHashMap.put(idObjetoGenerated,objeto);
        idObjetoGenerated++;
    }

    //modificar usuario
    public void updateUser(Usuario user, String name, String pass) {
        if (user.getName().equals(name)){
            user.setPassword(pass);
            Usuario newUsuario = new Usuario();
            newUsuario.setName(user.getName());
            newUsuario.setPassword(user.getPassword());
            usuarioHashMap.remove(user.getName());
            usuarioHashMap.put(newUsuario.getName(),newUsuario);
            logger.info("Usuario actualizado con contraseña nueva"+newUsuario.getPassword());
        }
        else{
            logger.error("El usuario indicado no existe");
        }
    }

    //consultar informacion de usuario
    public void getUser(String name) {
        String nombre = usuarioHashMap.get(name).getName();
        String password = usuarioHashMap.get(name).getPassword();
        List<Objeto> listObjetos = usuarioHashMap.get(name).getObjectList();
        if (listObjetos.size() == 0){
            logger.error("Lista vacia");
        }
        else{
            StringBuffer mostrar = new StringBuffer("nombre: "+nombre+" password: "+password+" id:"+usuarioHashMap.get(name).getId()+", objetos: [");
            logger.info(mostrar);
            for (int i=0; i<listObjetos.size(); i++){
                if (i != listObjetos.size()){
                    mostrar.append("nombre "+listObjetos.get(i).getNombre()+" descripcion: "+listObjetos.get(i).getDescripcion()+" id: "+listObjetos.get(i).getId()+"]");
                }
            }
            logger.info(mostrar);
        }
    }

    //consultar los objetos de un usuario (orden de insercion)
    public void getUserObjectsByOrder(Usuario user){

        List<Objeto> listObjetos = usuarioHashMap.get(user.getName()).getObjectList();
        logger.info("Lista de objetos del usuario "+user.getName());
        for (int i=0; i<listObjetos.size();i++){
            logger.info("nombre "+listObjetos.get(i).getNombre()+ "descripcion: "+listObjetos.get(i).getDescripcion()+" id: "+listObjetos.get(i).getId());
        }
    }

    //Listado usuarios ordenado alfabéticamente
    public void listUsersOrderByName() {
        List<String> list = new ArrayList<String>();
        Iterator iterator = usuarioHashMap.keySet().iterator();
        while(iterator.hasNext()){
            String name = iterator.next().toString();
            list.add(name);
        }
        logger.info("Listamos usuarios de forma alfabetica: \n");
        Collections.sort(list);
        for (int i=0; i< usuarioHashMap.size(); i++){
            logger.info("nombre: "+usuarioHashMap.get(list.get(i)).getName()+" | password:"+usuarioHashMap.get(list.get(i)).getPassword()+" | id: "+usuarioHashMap.get(list.get(i)).getId());
        }
    }

    //añadir un objeto sobre un usuario
    public void addObjectToUser(Objeto objeto, Usuario user) {

        Usuario userAdding = usuarioHashMap.get(user.getName());
        objeto.setId(idObjetoAdded);
        List<Objeto> obj = new ArrayList<Objeto>();
        obj.add(objeto);
        userAdding.setObjectList(obj);
        idObjetoAdded++;
    }

    public Enumeration<Usuario> mostrarLista(){
        Enumeration<Usuario> en = (Enumeration<Usuario>) usuarioHashMap.values();
        return en;

    }


}
