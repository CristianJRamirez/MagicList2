package a45858000w.magiclist2;

/**
 * Created by 45858000w on 14/10/16.
 */
import android.net.Uri;

import java.io.IOException;

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


    } String getAllCartas(){
        Uri builtUri = Uri.parse(BASE_URL)
                .buildUpon()
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