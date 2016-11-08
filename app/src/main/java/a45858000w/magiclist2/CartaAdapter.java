package a45858000w.magiclist2;

/**
 * Created by 45858000w on 26/10/16.
 */

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;
import java.util.Set;

import a45858000w.magiclist2.databinding.CartasLayoutBinding;


public class CartaAdapter extends ArrayAdapter<Carta> {

    public CartaAdapter(Context context, int resource, List<Carta> objects) {
        super(context, resource, objects);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Obtenim l'objecte en la possició corresponent
        Carta c  = getItem(position);
        Log.w("XXXX", c.toString());

        CartasLayoutBinding binding = null;

        // Mirem a veure si la View s'està reusant, si no es així "inflem" la View
        // https://github.com/codepath/android_guides/wiki/Using-an-ArrayAdapter-with-ListView#row-view-recycling
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            binding = DataBindingUtil.inflate(inflater, R.layout.cartas_layout, parent, false);
            }
        else {
                binding = DataBindingUtil.getBinding(convertView);
        }



        // Fiquem les dades dels objectes (provinents del JSON) en el layout
        binding.Carta.setText(c.getName());
        binding.rarity.setText(c.getRarity());
        binding.power.setText("Poder : " +c.getPower() + "%");
        Glide.with(getContext()).load(c.getImageUrl()).into(binding.Image);



        // Retornem la View replena per a mostrarla
        return binding.getRoot();
    }
}
