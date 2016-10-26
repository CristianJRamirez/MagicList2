package a45858000w.magiclist2;

/**
 * Created by 45858000w on 26/10/16.
 */

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;


public class CartaAdapter extends ArrayAdapter<Carta> {

    public CartaAdapter(Context context, int resource, List<Carta> objects) {
        super(context, resource, objects);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Obtenim l'objecte en la possició corresponent
        Carta c  = getItem(position);
        Log.w("XXXX", c.toString());
        // Mirem a veure si la View s'està reusant, si no es així "inflem" la View
        // https://github.com/codepath/android_guides/wiki/Using-an-ArrayAdapter-with-ListView#row-view-recycling
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.cartas_layout, parent, false);
        }

        // Unim el codi en les Views del Layout
        TextView Carta = (TextView) convertView.findViewById(R.id.Carta);
        TextView Rareza = (TextView) convertView.findViewById(R.id.rarity);
        TextView Poder = (TextView) convertView.findViewById(R.id.power);
        ImageView Image = (ImageView) convertView.findViewById(R.id.Image);

        // Fiquem les dades dels objectes (provinents del JSON) en el layout
        Carta.setText(c.getName());
        Rareza.setText(c.getRarity());
        Poder.setText(c.getPower());
        Glide.with(getContext()).load(c.getImageUrl()).into(Image);

        // Retornem la View replena per a mostrarla
        return convertView;
    }
}
