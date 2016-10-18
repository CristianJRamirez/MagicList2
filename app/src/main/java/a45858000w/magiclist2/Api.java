package a45858000w.magiclist2;

/**
 * Created by 45858000w on 14/10/16.
 */
import android.net.Uri;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class Api {
    //http://api.magicthegathering.io/v1/cards?page=311
    private final String BASE_URL = "http://api.magicthegathering.io/v1/cards";


     String getCartas(String pais) { //para utilizar el parametro

        Uri builtUri = Uri.parse(BASE_URL)
                .buildUpon()
                //.appendPath("vi")
                //.appendPath("cards")
                //.appendPath("box_office.json")
               // .appendQueryParameter("country", pais)//para buscar dentro de la api con algun paramentro en concreto
                .build();
        String url = builtUri.toString();

        try {
            String JsonResponse = HttpUtils.get(url);
            return JsonResponse;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;


    } ArrayList<Carta> getAllCartas(){
        Uri builtUri = Uri.parse(BASE_URL)
                .buildUpon()
                .build();
        String url = builtUri.toString();

        try {
            String JsonResponse = HttpUtils.get(url);
            ArrayList<Carta> cartas =new ArrayList<>();

            JSONObject data= new JSONObject(JsonResponse);
            JSONArray jsonCartas = data.getJSONArray("cards");

            for (int i = 0; i<jsonCartas.length() ; i++) {
                Carta c= new Carta();
                JSONObject object = jsonCartas.getJSONObject(i);

                c.setName(object.getString("name"));
                c.setManaCost(object.getString("manaCost"));
                c.setType(object.getString("type"));
                c.setRarity(object.getString("rarity"));
                c.setText(object.getString("text"));
                c.setPower(object.getString("power"));
                c.setImageUrl(object.getString("imageUrl"));

                cartas.add(c);
            }


            return cartas;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}