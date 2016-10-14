package a45858000w.magiclist2;

/**
 * Created by 45858000w on 14/10/16.
 */
import android.net.Uri;

import java.io.IOException;

public class Api {

    private final String BASE_URL = "https://docs.magicthegathering.io/";
//https://api.magicthegathering.io/v1/cards
  //  String getCartas(String pais) { //para utilizar el parametro
    String getCartas(){
        Uri builtUri = Uri.parse(BASE_URL)
                .buildUpon()
                .appendPath("vi")
                .appendPath("cards")
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
    }
}