/**
 * Created by ARK
 */
package ark.org.fridgemagnet;

import android.app.Application;
import android.util.Log;

import java.util.ArrayList;

import ark.org.fridgemagnet.ui.Item;
import ark.org.fridgemagnet.ui.ItemAdapter;

public class MainApplication extends Application {

    private static final String TAG = MainApplication.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate()");
        ArrayList<Item> items = new ArrayList<>();
        items.add(new Item("Add an Item to your List"));
        ItemAdapter.getInstance().setDataset(items);
    }
}
