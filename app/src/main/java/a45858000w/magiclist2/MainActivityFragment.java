package a45858000w.magiclist2;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.widget.CursorAdapter;
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
import nl.littlerobots.cupboard.tools.provider.UriHelper;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>{


    //region VARIABLES

//   private ArrayList<String> cartas;
 //   private ArrayAdapter<String> adapter;
    private ArrayList<Carta> cartas;
    //private ArrayAdapter<Carta> adapter;
    private CartasCursorAdapter adapter;
    private ProgressDialog dialog;
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


        adapter = new CartasCursorAdapter(getContext(), Carta.class);

        dialog = new ProgressDialog(getContext());
        dialog.setMessage("Cargando...");

        //TODO : commit -> Millores en el client
   //     https://github.com/lawer/RottenTomatoesClient2016/commit/2313ff67a86cff0db3febb18753bc79f8c178a08

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

        getLoaderManager().initLoader(0, null, this);

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

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return DataManager.getCursorLoader(getContext());
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        adapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        adapter.swapCursor(null);
    }



    private class RefreshDataTask extends AsyncTask<Void, Void,Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

                    dialog.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
            String color = preferences.getString("colors", "White");
            String rareza = preferences.getString("rarity", "Todas");


            ArrayList<Carta> result = null;
            if (rareza.equalsIgnoreCase("Todas")) {
                result = Api.getAllCartas();
            } else {
                result = Api.getCartasRareza(rareza, color);
            }
/*TODO acabar de implementar el metodo de colores, que se hara con : MultiSelectListPreference, mirarme como hacerlo y rellenar los archivos :
            res/xml/pref_general.xml
            mirar informacion en  : https://developer.android.com/reference/android/preference/MultiSelectListPreference.html*/


            //Log.d("DEBUG", result.toString());
            Log.d("DEBUG", result != null ? result.toString() : null);


            DataManager.deleteCartas(getContext());
            DataManager.saveCartas(result, getContext());

            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            dialog.dismiss();
        }

    }
    //endregion

}
