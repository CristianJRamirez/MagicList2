package a45858000w.magiclist2;

import android.content.Intent;
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

    public CartaDetallesFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_carta_detalles, container, false);

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

        ivPosterImage = (ImageView) view.findViewById(R.id.ivPosterImage);
        cartaTitulo = (TextView) view.findViewById(R.id.cartaTitulo);
        cartaPower = (TextView) view.findViewById(R.id.cartaPower);
        cartaMana = (TextView) view.findViewById(R.id.cartaMana);
        cartaRarity = (TextView) view.findViewById(R.id.cartaRarity);
        cartaType = (TextView) view.findViewById(R.id.cartaType);
        cartaText = (TextView) view.findViewById(R.id.cartaText);


        cartaTitulo.setText(carta.getName() );
        cartaPower.setText(
               Html.fromHtml("<b>Poder :</b> " + carta.getPower() + "%"));
        cartaText.setText(Html.fromHtml("<b>Texto :</b> " + carta.getText()));
        Glide.with(getContext()).load(carta.getImageUrl()).into(ivPosterImage);
    }
}
