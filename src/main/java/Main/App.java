package Main;

import Controlador.Controlador;
import Models.Usuario;
import org.apache.log4j.BasicConfigurator;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.server.StaticHttpHandler;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;
import java.net.URI;
import java.sql.SQLException;

public class App
{


    public static final String BASE_URI = "http://localhost:8080/myapp";

    public static HttpServer startServer() {
        //Crea un recurso que escanea peticiones
        final ResourceConfig rc = new ResourceConfig().packages("Rest", "Exceptions", "Controlador", "Main", "Models");
        // create and start a new instance of grizzly http server
        // exposing the Jersey application at BASE_URI
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
    }

    public static void main(String[] args) throws IOException, SQLException {

        BasicConfigurator.configure();

        Controlador cont = new Controlador();
        Usuario us = new Usuario();
        us.setName("ivan");
        us.setPassword("password");
        us.setId(1);
        us.setObjectList(null);
        cont.addUser(us);

        final HttpServer server = startServer();

        StaticHttpHandler staticHttpHandler = new StaticHttpHandler("./public/");
        server.getServerConfiguration().addHttpHandler(staticHttpHandler, "/");

        System.out.println(String.format("Jersey app started and available at "
                +BASE_URI+ "\nHit enter to stop it..."));

        System.in.read();
        server.stop();

    }
}
