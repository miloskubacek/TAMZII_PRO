package dnd_project_logic;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;

import androidx.core.content.ContextCompat;

import com.example.onlinedndproject.R;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MyCsvDatabase implements MyDatabase {


    private static Context context;

    public static void sendContext(Context applicationContext) {
        context = applicationContext;
    }

    @Override
    public void update(String table, int entity_id, String data) {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
        }

        /*if (ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
        }


        File sdCard = Environment.getExternalStorageDirectory();
        File dir = new File (sdCard.getAbsolutePath() + "dnd_data");
        dir.mkdirs();
        File file = new File(dir, "test.csv");
        int i=0;
        try {
            BufferedWriter csvWriter = new BufferedWriter(new FileWriter(file));
                csvWriter.append("Name");
                csvWriter.append(",");
                csvWriter.append("Role");
                csvWriter.append(",");
                csvWriter.append("Topic");
                csvWriter.append("\n");

                csvWriter.flush();
                csvWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
*/



    }

    @Override
    public void insert(String table, String data) {


    }

    @Override
    public String selectById(String table, int entity_id){

        String pathToCsv;
        String row;
        String output = null;

        InputStream is = null;

        switch (table){
            case "professions":
               is =context.getResources().openRawResource(R.raw.professions);
                break;
            case "characters":
                is =context.getResources().openRawResource(R.raw.characters);
                break;
            case "players":
                is =context.getResources().openRawResource(R.raw.players);
                break;
            case "races":
                is =context.getResources().openRawResource(R.raw.races);
                break;
            case "sessions":
                is =context.getResources().openRawResource(R.raw.sessions);
                break;
            case "stories":
                is =context.getResources().openRawResource(R.raw.stories);
                break;
            default:
                pathToCsv = "";
        }

        BufferedReader csvReader = null;
        try {


            csvReader = new BufferedReader(new InputStreamReader(is));

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

        String pathToCsv;
        String row;
        String output;

        InputStream is = null;

        switch (table){
            case "professions":
                is =context.getResources().openRawResource(R.raw.professions);
                break;
            case "characters":
                is =context.getResources().openRawResource(R.raw.characters);
                break;
            case "players":
                is =context.getResources().openRawResource(R.raw.players);
                break;
            case "races":
                is =context.getResources().openRawResource(R.raw.races);
                break;
            case "sessions":
                is =context.getResources().openRawResource(R.raw.sessions);
                break;
            case "stories":
                is =context.getResources().openRawResource(R.raw.stories);
                break;
            default:
                pathToCsv = "";
        }

        BufferedReader csvReader = null;
        try {
                csvReader = new BufferedReader(new InputStreamReader(is));
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
}