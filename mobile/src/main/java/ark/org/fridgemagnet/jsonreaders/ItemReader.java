/**
 * Created by ARk
 */
package ark.org.fridgemagnet.jsonreaders;

import android.content.Context;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import android.util.Log;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class ItemReader {
    private static final String TAG = ItemReader.class.getSimpleName();
    public static final String ITEMS_JSON_FILE = "Items.json";
    private static ItemReader sInstance;
    private final Context mContext;


    public static ItemReader init(Context context){
        if(sInstance == null){
            sInstance = new ItemReader(context);
        }
        return sInstance;
    }

    public void insertItem(int index, String itemName) {
        new JsonParser().parse("").getAsJsonArray();
    }

    public String getItem(int index){
        return null;
    }

    private JsonReader openJsonFile(){
        JsonReader jsonReader = null;
        try {
            InputStream inputStream = mContext.getAssets().open(ITEMS_JSON_FILE);
            jsonReader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));
        } catch (IOException ioException) {
            Log.wtf(TAG, "Error opening Items.json file from Assets " + ioException.getMessage());
        }
        return jsonReader;
    }

    private ItemReader(Context context){
        mContext = context.getApplicationContext();
        createItemsFile(mContext);
    }

    private void createItemsFile(Context context){
        Log.wtf(TAG, "createItemsFile()");
        File itemsFile = new File(mContext.getFilesDir(), ITEMS_JSON_FILE);
        try {
            JsonWriter jsonWriter = new JsonWriter(new FileWriter(itemsFile));
            jsonWriter.beginArray();
            jsonWriter.endArray();
            jsonWriter.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        if(itemsFile.exists()){
            Log.wtf(TAG, "File created successfully " + itemsFile.getAbsolutePath());
        }
    }
}
