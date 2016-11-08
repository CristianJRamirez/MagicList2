package a45858000w.magiclist2;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import a45858000w.magiclist2.databinding.FragmentCartaDetallesBinding;

/**
 * A placeholder fragment containing a simple view.
 */
public class CartaDetallesFragment extends Fragment {

    private View view;
    private ImageView ivPosterImage;
    private TextView cartaTitulo;
    private TextView cartaPower;
    private TextView cartaMana;
    private TextView cartaRarity;
    private TextView cartaType;
    private TextView cartaText;
    private FragmentCartaDetallesBinding binding;

    public CartaDetallesFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        FragmentCartaDetallesBinding
        binding = DataBindingUtil.inflate(
                        inflater, R.layout.fragment_carta_detalles, container, false);
        View view = binding.getRoot();

    Intent i = getActivity().getIntent();

    if (i != null) {
        Carta carta = (Carta) i.getSerializableExtra("carta");

        if (carta != null) {
            updateUi(carta);
        }
    }

    return view;
}

    private void updateUi(Carta carta) {
        Log.d("CARTA", carta.toString());




        binding.cartaTitulo.setText(carta.getName());
        binding.cartaPower.setText(Html.fromHtml("<b>Poder :</b> " + carta.getPower() + "%"));
        cartaMana.setText(Html.fromHtml("<b>Coste de Mana :</b> " + carta.getManaCost()));
        binding.cartaRarity.setText(Html.fromHtml("<b>Rareza :</b> " + carta.getRarity()));
        binding.cartaType.setText(Html.fromHtml("<b>Tipo :</b> " + carta.getType()));
        binding.cartaText.setText(Html.fromHtml("<b>Texto :</b> " + carta.getText()));
        Glide.with(getContext()).load(carta.getImageUrl()).into(binding.ivPosterImage);
    }
}
