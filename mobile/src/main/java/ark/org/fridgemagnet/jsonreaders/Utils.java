/**
 * Created by ARK
 */
package ark.org.fridgemagnet.jsonreaders;

import android.content.Context;
import android.util.Log;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

import ark.org.fridgemagnet.ui.Item;

public class Utils {

    private static final String TAG = Utils.class.getSimpleName();
    public static final String ITEMS_JSON_FILE = "Items.json";

    /**
     * writes string to the File
     * @param file File to which data is to be written
     * @param data data to be written in the file
     */
    public static void writeToFile(File file, String data) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(file));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        }
        catch (IOException ioException) {
            Log.e("Exception", "File write failed: " + ioException.toString());
        }
    }

    /**
     * Reads data from the file and returns it as String
     * @param file File from which data is to be read
     * @return data in the file as a String
     */
    public static String readFromFile(File file) {

        String fileString = "";

        try {
            InputStream inputStream = new FileInputStream(file);

            if (inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String currentLine = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ((currentLine = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(currentLine);
                }

                inputStream.close();
                fileString = stringBuilder.toString();
            }
        }
        catch (FileNotFoundException fileNotFoundException) {
            Log.e(TAG, "File not found: " + fileNotFoundException.getMessage());
        } catch (IOException ioException) {
            Log.e(TAG, "Can not read file: " + ioException.getMessage());
        }

        return fileString;
    }

    /**
     * converts the json data in the Items.json file into ArrayList<Item>
     * @param file
     * @return
     */
    public static ArrayList<Item> getItems(File file){
        Type listType = new TypeToken<ArrayList<Item>>(){}.getType();
        ArrayList<Item> items = new GsonBuilder().create().fromJson(readFromFile(file), listType);
        return items;
    }

    public static void writeToFile(Context appContext, String itemsJsonFile, String data) {
        writeToFile(appContext.getFileStreamPath(itemsJsonFile), data);
    }
}
