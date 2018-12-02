package com.dev.nicolashoareau.tutoasynchttp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class AsyncHTTP extends AsyncTask<String, Integer, String> {

    private AppCompatActivity myActivity;

    public AsyncHTTP(MainActivity mainActivity) {
        myActivity = mainActivity;
    }

    @Override
    protected void onPreExecute() { // ce qui est fait avant

    }

    @Override
    protected String doInBackground(String... strings) { // arrière plan
        publishProgress(1);
        try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }

        URL url = null;
        HttpURLConnection urlConnection = null;
        String result = null;
        try {
            url = new URL(strings[0]);
            urlConnection = (HttpURLConnection) url.openConnection(); // Ouverture de l'url qui renvoie un objet de type HttpConnection
            InputStream in = new BufferedInputStream(urlConnection.getInputStream()); // Lecture de l'objet
            publishProgress(2);
            result = readStream(in); //consomme les données du flux in  afin de construire le résultat
        }
        catch (MalformedURLException e) { e.printStackTrace(); }
        catch (IOException e) { e.printStackTrace(); }
        finally { if (urlConnection != null)
            urlConnection.disconnect();  }

        publishProgress(4);
        return result; // returns the result
    }

    @Override
    protected void onProgressUpdate(Integer... values) { //mise à jour de la progress bar
        ProgressBar pb = (ProgressBar) myActivity.findViewById(R.id.progressBar);
        pb.setProgress(values[0]);
    }

    @Override
    protected void onPostExecute(String s) { //ce qui est fait après
        TextView text = (TextView) myActivity.findViewById(R.id.text);
        text.setText(s); // Mise à jour de la textView
        ProgressBar pb = (ProgressBar) myActivity.findViewById(R.id.progressBar);
        pb.setProgress(5);
    }

    private String readStream(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder(); //la chaîne qui correspond au contenu de la réponse de la requête HTTP
        BufferedReader r = new BufferedReader(new InputStreamReader(is),1000);
        for (String line = r.readLine(); line != null; line =r.readLine()){
            sb.append(line);
        }
        is.close();
        return sb.toString();
    }


}