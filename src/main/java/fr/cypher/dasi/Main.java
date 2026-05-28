package fr.cypher.dasi;

import fr.cypher.dasi.dao.JpaUtil;
import fr.cypher.dasi.metier.modele.Client;
import fr.cypher.dasi.metier.service.ClientService;

import java.time.LocalDate;
import java.util.List;

public class Main {
    static void main() {
        System.out.println("Hello World!");

        JpaUtil.creerFabriquePersistance();
        ClientService service = new ClientService();

        // mail, prenom, motDePasse, telephone, nom, dateDeNaissance, adresse
        Client bres = new Client("jaimeDiagonalise@gmail.com", "stephane", "LaGrange", null, "bres", null, null);
        Client prost = new Client("funfunfun@fun.fun", "frederic", "pasUtile", null, "prost", null, null);
        Client guerin = new Client("eguerin@67.fr", "eric", "EricGuedin", null, "guerin", null, null);
        System.out.println(bres);

        boolean inscription = service.inscrireClient(bres);
        System.out.println("Inscription: " + inscription);
        inscription = service.inscrireClient(prost);
        System.out.println("Inscription: " + inscription);
        inscription = service.inscrireClient(guerin);
        System.out.println("Inscription: " + inscription);
        System.out.println(bres);

        List<Client> clients = service.listerClients();
        if (clients != null) for (Client c : clients) System.out.println(c);
        else System.out.println("Rien");
    }
}
