package fr.cypher.dasi;

import fr.cypher.dasi.dao.JpaUtil;
import fr.cypher.dasi.metier.modele.Client;
import fr.cypher.dasi.metier.service.ClientService;

import java.util.List;

public class Main {
    static void main() {
        System.out.println("Hello World!");

        JpaUtil.creerFabriquePersistance();
        ClientService service = new ClientService();

        Client bres = new Client("bres", "stephane","jaimeDiagonalise@gmail.com","LaGrange");
        Client prost = new Client("prost", "frederic","funfunfun@fun.fun","pasUtile");
        Client guerin = new Client("eric", "guerin","jaimeDiagonalise@gmail.com","EricGuedin");
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
