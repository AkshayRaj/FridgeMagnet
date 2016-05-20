/**
 * Created by ARK
 */
package ark.org.fridgemagnet;

import android.app.Application;
import android.util.Log;


public class MainApplication extends Application {

    private static final String TAG = MainApplication.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate()");
    }
}
