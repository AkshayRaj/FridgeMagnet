/**
 * Created by ARk
 */
package ark.org.fridgemagnet.jsonreaders;

import android.content.Context;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.google.gson.stream.JsonWriter;

import android.util.Log;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

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
        String jsonData = Utils.readFromFile(mContext.getFileStreamPath(ITEMS_JSON_FILE));
        JsonElement jsonElement = new JsonParser().parse(jsonData);
        JsonArray jsonArray;
        if(jsonElement.isJsonArray()) {
            jsonArray = jsonElement.getAsJsonArray();
        } else {
            jsonArray = new JsonArray();
        }
        jsonArray.add(new JsonPrimitive(itemName));
        Utils.writeToFile(mContext.getFileStreamPath(ITEMS_JSON_FILE), jsonArray.toString());

    }

    public String getItem(int index){
        return null;
    }


    private ItemReader(Context context){
        mContext = context.getApplicationContext();
        //createItemsFile(mContext);
    }

    private void createItemsFile(Context context){
        File itemsFile = new File(mContext.getFilesDir(), ITEMS_JSON_FILE);
        try {
            JsonWriter jsonWriter = new JsonWriter(new FileWriter(itemsFile));
            jsonWriter.beginArray();
            jsonWriter.endArray();
            jsonWriter.close();
        } catch (IOException ioException) {

        }
    }
}
