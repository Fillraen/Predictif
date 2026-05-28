package fr.cypher.dasi.util.API;

import fr.cypher.dasi.metier.modele.embedded.Adresse;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class DataGouvApi {

    private static final String URL = "https://data.geopf.fr/geocodage/search";

    private static final HttpClient HTTP = HttpClient.newHttpClient();

    // Retourne [longitude, latitude] ou null si introuvable
    public static double[] obtenirCoordonnees(Adresse adresse) {
        try {
            String libelle = adresse.getNumeroDeVoie() + " "
                    + adresse.getNomDeVoie() + ", "
                    + adresse.getVille();

            String url = URL
                    + "?autocomplete=0"
                    + "&index=address"
                    + "&limit=1"
                    + "&returntruegeometry=false"
                    + "&q=" + encode(libelle);

            JSONObject json     = new JSONObject(get(url));
            JSONArray  features = json.getJSONArray("features");

            if (features.isEmpty()) return null;

            JSONArray coords = features.getJSONObject(0)
                    .getJSONObject("geometry")
                    .getJSONArray("coordinates");

            return new double[]{ coords.getDouble(0), coords.getDouble(1) };

        } catch (Exception e) {
            System.err.println("Erreur DataGouv - géocodage");
            e.printStackTrace();
            return null;
        }
    }

    private static String get(String url) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        return HTTP.send(request, HttpResponse.BodyHandlers.ofString()).body();
    }

    private static String encode(String valeur) {
        return URLEncoder.encode(valeur, StandardCharsets.UTF_8);
    }
}
