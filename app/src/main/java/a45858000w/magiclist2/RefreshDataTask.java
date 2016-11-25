package a45858000w.magiclist2;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.Log;

import com.alexvasilkov.events.Events;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by 45858000w on 25/11/16.
 */

class RefreshDataTask extends AsyncTask<Void, Void,Void> {

    private Context context;

    RefreshDataTask(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        Events.post("start-downloading-data");
    }

    @Override
    protected Void doInBackground(Void... voids) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
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


        DataManager.deleteCartas(context);
        DataManager.saveCartas(result, context);

        return null;
    }
    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        Events.post("finish-downloading-data");
    }

}
