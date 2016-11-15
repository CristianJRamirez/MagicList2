package a45858000w.magiclist2;

/**
 * Created by 45858000w on 11/11/16.
 */

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;



import a45858000w.magiclist2.databinding.CartasLayoutBinding;

public class CartasCursorAdapter extends CupboardCursorAdapter<Carta> {

    public CartasCursorAdapter(Context context, Class<Carta> entityClass) {
        super(context, entityClass);
    }

    @Override
    public View newView(Context context, Carta model, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        CartasLayoutBinding binding = DataBindingUtil.inflate(
                inflater, R.layout.cartas_layout, parent, false);

        return binding.getRoot();
    }


    @Override
    public void bindView(View view, Context context, Carta model) {
        CartasLayoutBinding binding = DataBindingUtil.getBinding(view);

        binding.Carta.setText(model.getName());
        binding.power.setText("Poder:" + model.getPower());
        binding.rarity.setText(model.getRarity());
        Glide.with(context).load(model.getImageUrl()).into(binding.Image);
    }
}