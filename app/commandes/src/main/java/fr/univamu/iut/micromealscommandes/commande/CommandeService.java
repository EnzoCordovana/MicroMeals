package fr.univamu.iut.micromealscommandes.commande;

import fr.univamu.iut.micromealscommandes.menu.Menu;
import fr.univamu.iut.micromealscommandes.menu.MenuRepositoryInterface;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class CommandeService {

    protected CommandeRepositoryInterface commandeRepo;
    protected MenuRepositoryInterface menuRepo;

    public CommandeService(CommandeRepositoryInterface commandeRepo, MenuRepositoryInterface menuRepo) {
        this.commandeRepo = commandeRepo;
        this.menuRepo = menuRepo;
    }

    public String getAllCommandesJSON() {
        ArrayList<Commande> allCommandes = commandeRepo.getAllCommandes();

        String result = null;
        try (Jsonb jsonb = JsonbBuilder.create()) {
            result = jsonb.toJson(allCommandes);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return result;
    }

    public String getCommandesByAbonneIdJSON(int abonneId) {
        ArrayList<Commande> commandes = commandeRepo.getCommandesByAbonneId(abonneId);

        String result = null;
        try (Jsonb jsonb = JsonbBuilder.create()) {
            result = jsonb.toJson(commandes);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return result;
    }

    public String getCommandeJSON(int id) {
        String result = null;
        Commande myCommande = commandeRepo.getCommande(id);

        if (myCommande != null) {
            try (Jsonb jsonb = JsonbBuilder.create()) {
                result = jsonb.toJson(myCommande);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        return result;
    }

    public String createCommande(CommandeInput input) {

        ArrayList<LigneCommande> lignes = new ArrayList<>();

        for (LigneCommandeInput ligneInput : input.getLignes()) {
            Menu menu = menuRepo.getMenu(ligneInput.getMenuId());

            if (menu == null)
                return null;

            LigneCommande ligne = new LigneCommande(
                    menu.getId(),
                    menu.getNom(),
                    ligneInput.getQuantite(),
                    menu.getPrixTotal()
            );
            lignes.add(ligne);
        }

        double prixTotal = 0;
        for (LigneCommande ligne : lignes) {
            prixTotal += ligne.getPrixLigne();
        }

        Commande commande = new Commande(input.getAbonneId(), input.getAdresseLivraison(), input.getDateLivraison());
        commande.setDateCommande(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")));
        commande.setLignes(lignes);
        commande.setPrixTotal(prixTotal);

        int generatedId = commandeRepo.createCommande(commande);

        if (generatedId == -1)
            return null;

        commande.setId(generatedId);

        String result = null;
        try (Jsonb jsonb = JsonbBuilder.create()) {
            result = jsonb.toJson(commande);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return result;
    }

    public String updateCommande(int id, CommandeUpdateInput input) {
        boolean updated = commandeRepo.updateCommande(id, input.getAdresseLivraison(), input.getDateLivraison());

        if (!updated)
            return null;

        return getCommandeJSON(id);
    }

    public boolean deleteCommande(int id) {
        return commandeRepo.deleteCommande(id);
    }
}
