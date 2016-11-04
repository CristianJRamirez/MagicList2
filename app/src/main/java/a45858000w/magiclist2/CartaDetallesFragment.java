package a45858000w.magiclist2;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A placeholder fragment containing a simple view.
 */
public class CartaDetallesFragment extends Fragment {

    public CartaDetallesFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

    View view = inflater.inflate(R.layout.fragment_carta_detalles, container, false);

    Intent i = getActivity().getIntent();

    if (i != null) {
        Carta carta = (Carta) i.getSerializableExtra("movie");

        if (carta != null) {
            updateUi(carta);
        }
    }

    return view;
}

    private void updateUi(Carta carta) {
        Log.d("CARTA", carta.toString());
    }
}
