package a45858000w.magiclist2;

import nl.littlerobots.cupboard.tools.provider.CupboardContentProvider;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

/**
 * Created by 45858000w on 11/11/16.
 */

public class CartaContentProvider extends CupboardContentProvider {


    // The content provider authority is used for building Uri's for the provider
    public static final String AUTHORITY = BuildConfig.APPLICATION_ID + ".provider";

    static {
        cupboard().register(Carta.class);
    }

    public CartaContentProvider() {
        super(AUTHORITY, 1);
    }
}
