/**
 * Created by ARK
 */
package ark.org.fridgemagnet.jsonreaders;

import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Utils {

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
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
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
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }

        return fileString;
    }
}
