package fr.univamu.iut.micromealscommandes.menu;

import java.io.Closeable;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 * Implementation de {@link MenuRepositoryInterface} accedant a une API REST externe
 * pour récupérer les informations des menus.
 */
public class MenuRepositoryAPI implements MenuRepositoryInterface, Closeable {

    String url;

    /**
     * Cree une instance pointant vers l'URL de base de l'API des menus
     *
     * @param url URL de base de l'API
     */
    public MenuRepositoryAPI(String url) {
        this.url = url;
    }

    @Override
    public void close() {
    }

    /**
     * Recupere un menu depuis l'API externe
     *
     * @param id identifiant du menu
     * @return le menu correspondant, ou {@code null} si introuvable ou en cas d'erreur
     */
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
