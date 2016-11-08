package a45858000w.magiclist2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

import a45858000w.magiclist2.databinding.FragmentMainBinding;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {


    //region VARIABLES

//   private ArrayList<String> cartas;
 //   private ArrayAdapter<String> adapter;
    private ArrayList<Carta> cartas;
    //private ArrayAdapter<Carta> adapter;
    private CartaAdapter adapter;
    //endregion



    public MainActivityFragment() {
    }

    @Override//notificamos al activity quer le añadimos items al menu
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        FragmentMainBinding binding = DataBindingUtil.inflate(
                        inflater, R.layout.fragment_main, container, false);
        View view = binding.getRoot();

        cartas = new ArrayList<>();


        adapter = new CartaAdapter(
             getContext(),
             R.layout.cartas_layout,
            // R.id.Carta,
                cartas
             );


        binding.listaCartas.setAdapter(adapter);

        binding.listaCartas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Carta carta = (Carta) adapterView.getItemAtPosition(i);

            Intent intent = new Intent(getContext(), CartaDetalles.class);
            intent.putExtra("carta", carta);
            startActivity(intent);
        }
         });

        return view;
    }

    @Override//añadimos items al menu
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_cartas,menu);
    }


    //region Click en el boton Actualizar


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Intent i = new Intent(getContext(), SettingsActivity.class);
            startActivity(i);
            return true;
        }
        else if (id == R.id.actualizar) {
            refresh();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStart() {
        super.onStart();
        refresh();
    }

    private void refresh() {
           /*
           Api api = new Api();
           //String result = api.getCartas("es");
           String result = api.getAllCartas();
           Log.d("DEBUG", result);
           */
           RefreshDataTask task = new RefreshDataTask();
           task.execute();
    }



    private class RefreshDataTask extends AsyncTask<Void, Void, ArrayList<Carta>> {
        @Override
        protected ArrayList<Carta> doInBackground(Void... voids) {
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
            String color = preferences.getString("colors", "White");
            String rareza = preferences.getString("rarity", "Todas");
            Api api = new Api();



            ArrayList<Carta> result = null;
            if (rareza.equalsIgnoreCase("Todas"))
            {
                result = api.getAllCartas();
            }
            else {
                result = api.getCartasRareza(rareza, color);
            }
/*TODO acabar de implementar el metodo de colores, que se hara con : MultiSelectListPreference, mirarme como hacerlo y rellenar los archivos :
            res/xml/pref_general.xml
            mirar informacion en  : https://developer.android.com/reference/android/preference/MultiSelectListPreference.html*/


            //Log.d("DEBUG", result.toString());
            Log.d("DEBUG", result != null ? result.toString() : null);

            return result;
        }

        @Override
        protected void onPostExecute(ArrayList<Carta> cartas) {
            super.onPostExecute(cartas);
            adapter.clear();
            for (Carta c : cartas) {
                //adapter.add(c.getName());
                adapter.add(c);
            }
        }
    }
    //endregion

}
