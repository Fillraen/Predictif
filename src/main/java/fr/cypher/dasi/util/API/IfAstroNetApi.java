package fr.cypher.dasi.util.API;

import fr.cypher.dasi.metier.modele.Prediction;
import fr.cypher.dasi.metier.modele.embedded.ProfilAstral;
import org.json.JSONObject;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;

public class IfAstroNetApi {

    private static final String URL = "https://servif.insa-lyon.fr/WebDataGenerator/Astro";
    private static final String KEY = "ASTRO-01-M0lGLURBU0ktQVNUUk8tQjAx";

    private static final HttpClient HTTP = HttpClient.newHttpClient();

    private static final SimpleDateFormat ISO = new SimpleDateFormat("yyyy-MM-dd");

    public static ProfilAstral obtenirProfilAstral(String prenom, Date dateNaissance) {
        try {
            String url = URL
                    + "?service=profil"
                    + "&key=" + KEY
                    + "&prenom=" + encode(prenom)
                    + "&date-naissance=" + ISO.format(dateNaissance);

            JSONObject profil = new JSONObject(get(url)).getJSONObject("profil");

            return new ProfilAstral(
                    profil.getString("animal"),
                    profil.getString("signe-zodiaque"),
                    profil.getString("couleur"),
                    profil.getString("signe-chinois")
            );
        } catch (Exception e) {
            System.err.println("Erreur IfAstroNet - profil astral");
            e.printStackTrace();
            return null;
        }
    }

    public static Prediction obtenirPredictions(String couleur, String animal,
                                                int niveauAmour, int niveauSante, int niveauTravail) {
        try {
            String url = URL
                    + "?service=predictions"
                    + "&key=" + KEY
                    + "&couleur=" + encode(couleur)
                    + "&animal="  + encode(animal)
                    + "&niveau-amour="   + niveauAmour
                    + "&niveau-sante="   + niveauSante
                    + "&niveau-travail=" + niveauTravail;

            JSONObject json = new JSONObject(get(url));

            return new Prediction(
                    json.getString("prediction-amour"),
                    json.getString("prediction-sante"),
                    json.getString("prediction-travail")
            );
        } catch (Exception e) {
            System.err.println("Erreur IfAstroNet - prédictions");
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
