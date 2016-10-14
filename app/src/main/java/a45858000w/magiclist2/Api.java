package a45858000w.magiclist2;

/**
 * Created by 45858000w on 14/10/16.
 */
import android.net.Uri;

import java.io.IOException;

public class Api {

    private final String BASE_URL = "http://api.rottentomatoes.com/api/public/v1.0/";

    String getPeliculesMesVistes(String pais) {
        Uri builtUri = Uri.parse(BASE_URL)
                .buildUpon()
                .appendPath("lists")
                .appendPath("movies")
                .appendPath("box_office.json")
                .appendQueryParameter("country", pais)
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