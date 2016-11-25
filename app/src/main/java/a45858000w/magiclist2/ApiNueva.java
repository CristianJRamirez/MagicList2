package a45858000w.magiclist2;

/**
 * Created by 45858000w on 25/11/16.
 */
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.ArrayList;


public class ApiNueva {

    private static final String BASE_URL = "http://api.magicthegathering.io/v1/cards/";
    private static final String BASE_URL2 = "http://api.magicthegathering.io";
    private static String API_KEY = "9htuhtcb4ymusd73d4z6jxcj";
    private static final Integer LIMIT = 50;
    private static final int PAGES = 5;

    static ArrayList<Carta> getCartasRareza(String rareza, String color) {
        ArrayList<Carta> result = new ArrayList<>();

        return doCall(rareza,color);
    }

    static ArrayList<Carta> getAllCartas() throws IOException {
        return doCall();
    }

    @Nullable
    private static ArrayList<Carta> doCall() {
        ArrayList<Carta> cartas = new ArrayList<>();

        for (int i = 0; i < PAGES; i++) {
            try {
                String url = getUrlPage(i);
                String JsonResponse = HttpUtils.get(url);
                ArrayList<Carta> list = setDatosCartas(JsonResponse);
                Log.d("CARTAS --------------------------->",list.toString());
                cartas.addAll(list);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return cartas;
    }

    @Nullable
    private static ArrayList<Carta> doCall(String rareza, String color) {
        ArrayList<Carta> cartas = new ArrayList<>();

        for (int i = 0; i < PAGES; i++) {
            try {
                String url = getUrlPage(rareza,color,  i);
                String JsonResponse = HttpUtils.get(url);
                ArrayList<Carta> list = setDatosCartas(JsonResponse);
                Log.d("CARTAS ------**********************--->",list.toString());
                cartas.addAll(list);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return cartas;
    }

    private static String getUrlPage(int page) throws IOException {
        Uri builtUri = Uri.parse(BASE_URL)
                .buildUpon()
                .appendQueryParameter("page", String.valueOf(page))
                .build();
        String url = builtUri.toString();
        Log.d("URL -------------->", url);
        return url;
    }

    private static String getUrlPage(String rareza, String color, int page) {
        Uri builtUri = Uri.parse(BASE_URL2)
                .buildUpon()
                .appendPath("v1")
                .appendPath("cards")
                .appendQueryParameter("rarity", rareza)//para buscar dentro de la api con algun paramentro en concreto,
                .appendQueryParameter("colors", color)//para buscar dentro de la api con algun paramentro en concreto

                .appendQueryParameter("page", String.valueOf(page))
                .build();
        Log.d("URL --********************----->", builtUri.toString());
        return builtUri.toString();
    }


    private static ArrayList<Carta> setDatosCartas(String url) throws IOException {
        String JsonResponse = HttpUtils.get(url);
        ArrayList<Carta> cartas = new ArrayList<>();
        try {
            JSONObject data = new JSONObject(JsonResponse);
            JSONArray jsonCartas = data.getJSONArray("cards");
            for (int i = 0; i<jsonCartas.length() ; i++) {
                Carta c= new Carta();
                JSONObject object = jsonCartas.getJSONObject(i);

                if (object.has("name")) {
                    c.setName(object.getString("name"));
                }
                if (object.has("manaCost")) {
                    c.setManaCost(object.getString("manaCost"));
                }
                if (object.has("type")) {
                    c.setType(object.getString("type"));
                }
                if (object.has("rarity")) {
                    c.setRarity(object.getString("rarity"));
                }
                if (object.has("text")) {
                    c.setText(object.getString("text"));
                }
                if (object.has("power"))
                {
                    c.setPower(object.getString("power"));
                }
                if (object.has("imageUrl")) {
                    c.setImageUrl(object.getString("imageUrl"));
                }
                if (object.has("colors"))
                {
                    c.setColor(object.getString("colors"));
                }

                cartas.add(c);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return cartas;
    }
}