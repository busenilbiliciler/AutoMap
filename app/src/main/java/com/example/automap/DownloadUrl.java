package com.example.automap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class DownloadUrl {


    public String ReadTheUrl(String placeURL) throws IOException

    {

        String Data = "";
        InputStream inputSteam= null;
        HttpURLConnection httpURLConnection = null;



        try
        {
            URL url = new URL(placeURL);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.connect();

            inputSteam = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputSteam));
            StringBuffer stringBuffer = new StringBuffer();

            String line = "";

            while ((line = bufferedReader.readLine()) != null)
            {

                stringBuffer.append(line);

            }

            Data = stringBuffer.toString();
            bufferedReader.close();



        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }


        finally

        {

            inputSteam.close();
            httpURLConnection.disconnect();
        }

        return Data;
    }












}
