package Rest;

import Models.Objeto;
import Models.Usuario;
import Controlador.Controlador;
import javax.inject.Singleton;
import javax.naming.ldap.Control;
import javax.print.attribute.standard.Media;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;



@Path("/json")
public class JSONController {

    Controlador control = new Controlador();

    public HashMap<String, Usuario> usuarioHashMap;
    public HashMap<Integer, Objeto> objetoHashMap;

    @Singleton
    public JSONController()  {
        usuarioHashMap = new HashMap<String, Usuario>();
        objetoHashMap = new HashMap<Integer, Objeto>();
    }


    //consultar info de un usuario
    @GET
    @Path("/usuario/got/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Usuario getUsuariobyId(@PathParam("name") String name){

        Usuario finded = new Usuario();

        for (int i = 0; i<usuarioHashMap.size(); i++){
            if (usuarioHashMap.get(i).getName() == name){
                finded = usuarioHashMap.get(i);
            }
        }
        return finded;
    }

    //lista de usuarios
    @GET
    @Path("/usuario/get_all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Usuario> getListaUsuarios(){return (List<Usuario>) usuarioHashMap.values();}

    //consultar objetos de un usuario
    @GET
    @Path("/usuario/objetos/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Objeto> getObjetosUsuario(@PathParam("name") String name){
        return usuarioHashMap.get(name).getObjectList();
    }

    //añadir un usuario
    @POST
    @Path("/usuario/new")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createUsuarioInJSON(Usuario usuario){
        control.addUser(usuario);
        String yesResult = "Usuario añadido";
        return Response.status(201).entity(yesResult).build();
    }

    //modificar un usuario
    @POST
    @Path("/usuario/update/{name}/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response modifyUserInJSON(@PathParam("name") String name){
        control.updateUser(usuarioHashMap.get(name),"name","pass");

        String yesResult = "Usuario modificado";
        return Response.status(201).entity(yesResult).build();
    }

    //añadir objeto sobre un usuario




}
