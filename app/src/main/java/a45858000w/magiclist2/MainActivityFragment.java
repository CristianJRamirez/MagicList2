package a45858000w.magiclist2;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {


    //region VARIABLES
    private ArrayList<String> cartas;
    private ArrayAdapter<String> adapter;
    //endregion



    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        ListView listaCartas = (ListView) view.findViewById(R.id.listaCartas);

        cartas = new ArrayList<>();
        adapter = new ArrayAdapter<>(
             getContext(),
             R.layout.cartas_layout,
             R.id.Carta,
                cartas
             );
        listaCartas.setAdapter(adapter);


        return view;
    }
}
