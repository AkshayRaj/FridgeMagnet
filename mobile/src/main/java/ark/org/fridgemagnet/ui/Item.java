/**
 * Created by ARK
 */
package ark.org.fridgemagnet.ui;


import android.util.Log;

import com.google.gson.annotations.SerializedName;

import org.json.JSONException;
import org.json.JSONObject;

public class Item {

    private static final String TAG = Item.class.getSimpleName();
    public static final String MNAME_JSON_KEY = "name";

    @SerializedName(MNAME_JSON_KEY)
    private String mName;

    public Item(String name){
        mName = name;
    }

    public Item(JSONObject jsonObject){
        try {
            mName = jsonObject.getString(MNAME_JSON_KEY);
        } catch (JSONException jsonException) {
            Log.wtf(TAG, "Error while constructing new Item from JSONObject " + jsonException.getMessage());
        }
    }

    public String getName() {
            return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put(MNAME_JSON_KEY, mName);
        } catch (JSONException jsonException) {
            Log.wtf(TAG, "Error while converting to JSON " + jsonException.getMessage());
        }
        return jsonObject;
    }

}
