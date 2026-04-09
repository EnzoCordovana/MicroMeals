package fr.univamu.iut.micromealscommandes.menu;

import java.io.Closeable;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

public class MenuRepositoryAPI implements MenuRepositoryInterface, Closeable {

    String url;

    public MenuRepositoryAPI(String url) {
        this.url = url;
    }

    @Override
    public void close() {
    }

    @Override
    public Menu getMenu(int id) {
        Menu myMenu = null;

        Client client = ClientBuilder.newClient();
        WebTarget menuResource = client.target(url);
        WebTarget menuEndpoint = menuResource.path("menus/" + id);
        Response response = menuEndpoint.request(MediaType.APPLICATION_JSON).get();

        if (response.getStatus() == 200)
            myMenu = response.readEntity(Menu.class);

        client.close();
        return myMenu;
    }
}
