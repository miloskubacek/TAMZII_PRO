package dnd_project_logic;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Environment;

import androidx.core.content.ContextCompat;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

public class MyCsvDatabase implements MyDatabase {


    private static Context context;

    public static void sendContext(Context applicationContext) {
        context = applicationContext;
    }

    @Override
    public void update(String table, int entity_id, String data) {
       String allData = this.selectAll(table);

        try{
            JSONObject json = new JSONObject(allData);
            Iterator<String> iter = json.keys();
            while (iter.hasNext()) {
                String key = iter.next();
                if(key.equals(String.valueOf(entity_id))) {
                    String value = (String) json.getString(key);
                }
                else {
                    //Jadidiata;
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insert(String table, String data) {


        JSONParser parser = new JSONParser();
        JSONObject json = null;
        String input = null;

        String pathToCsv = getTablePath(table);

        try{
            json = new JSONObject(data);
            Iterator<String> iter = json.keys();
            String key = iter.next();
            String value = (String)json.getString(key);
            input = "\""+value+"\"";
            while (iter.hasNext()) {
                key = iter.next();
                value = (String)json.getString(key);
                input+=",\""+value+"\"";

            }
            input +="\n";

        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
        }

        if (ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
        }

        File sdCard = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File dir = new File (sdCard.getAbsolutePath());
        dir.mkdirs();
        File file = new File(dir, pathToCsv);
        int i=0;
        try {
            BufferedWriter csvWriter = new BufferedWriter(new FileWriter(file, true));
            csvWriter.append(input);
            csvWriter.flush();
            csvWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }



    }

    @Override
    public String selectById(String table, int entity_id){

        String row;
        String output = null;
        String pathToCsv = getTablePath(table);

        if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
        }

        if (ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
        }

        File sdCard = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File dir = new File (sdCard.getAbsolutePath());
        dir.mkdirs();
        File file = new File(dir, pathToCsv);

        try {
            BufferedReader csvReader = new BufferedReader(new FileReader(file));

            row = csvReader.readLine();
            String[] firstLine = row.split(",");

            while ((row = csvReader.readLine()) != null) {
                String[] data = row.split(",");
                //if(Integer.parseInt(data[0])==entity_id){
                String entId = "\""+entity_id+"\"";
                boolean result = data[0].equals(String.valueOf(entId));
                if(result){
                    output="{";
                    output+=""+data[0]+":{";
                    output+="\""+firstLine[0]+"\": " + data[0];
                    for(int i=1;i<firstLine.length;i++){
                        output+=",\""+firstLine[i]+"\": " + data[i];
                    }
                    output+="}";
                }

            }

            csvReader.close();
            return output;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String selectAll(String table) {

        String row;
        String output;

        String pathToCsv = getTablePath(table);

        if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
        }

        if (ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
        }

        File sdCard = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File dir = new File (sdCard.getAbsolutePath());
        dir.mkdirs();
        File file = new File(dir, pathToCsv);

        try {
                BufferedReader csvReader = new BufferedReader(new FileReader(file));
                output="{";
                row = csvReader.readLine();
                String[] firstLine = row.split(",");

                row = csvReader.readLine();
                String[] data = row.split(",");
                output+=""+data[0]+":{";
                output+="\""+firstLine[0]+"\": " + data[0];
                for(int i=1;i<firstLine.length;i++){
                    output+=",\""+firstLine[i]+"\": " + data[i];
                }
                output+="}";

                while ((row = csvReader.readLine()) != null) {
                    data = row.split(",");
                    output+=","+data[0]+":{";
                    output+="\""+firstLine[0]+"\": " + data[0];
                    for(int i=1;i<firstLine.length;i++){
                        output+=",\""+firstLine[i]+"\": " + data[i];
                    }
                    output+="}";

                }
                output+="}";
                csvReader.close();
                return output;

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        return null;
    }

    private String getTablePath(String table) {
        String pathToCsv;
        switch (table){
            case "professions":
                pathToCsv = "professions.csv";
                break;
            case "characters":
                pathToCsv = "characters.csv";
                break;
            case "players":
                pathToCsv = "players.csv";
                break;
            case "races":
                pathToCsv = "races.csv";
                break;
            case "sessions":
                pathToCsv = "sessions.csv";
                break;
            case "stories":
                pathToCsv = "stories.csv";
                break;
            default:
                pathToCsv = "";
        }
        return pathToCsv;
    }
}