package a45858000w.magiclist2;

/**
 * Created by 45858000w on 26/10/16.
 */

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.List;


public class CartaAdapter extends ArrayAdapter<Carta> {

    public CartaAdapter(Context context, int resource, List<Carta> objects) {
        super(context, resource, objects);
    }
}
