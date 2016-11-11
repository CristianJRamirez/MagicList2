package a45858000w.magiclist2;

/**
 * Created by 45858000w on 11/11/16.
 */

import android.content.Context;
import android.net.Uri;

import java.util.ArrayList;

import nl.littlerobots.cupboard.tools.provider.UriHelper;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

/**
 * Created by carlesgm on 22/8/16.
 */

public class DataManager {

    private static UriHelper URI_HELPER = UriHelper.with(CartaContentProvider.AUTHORITY);
    private static Uri CARTA_URI = URI_HELPER.getUri(Carta.class);

    static void saveCartas(ArrayList<Carta> cartas, Context context) {
        cupboard().withContext(context).put(CARTA_URI, Carta.class, cartas);
    }

    static void deleteCartas(Context context) {
            cupboard().withContext(context).delete(CARTA_URI, "_id > ?", "1");
        }
}