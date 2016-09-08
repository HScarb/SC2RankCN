package com.scarb.plugins.timetask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Scarb on 8/31/2016.
 */
public class Crawler {
    public String craw(String url)
    {
        String result = "";
        BufferedReader in = null;

        try {
            URL realUrl = new URL(url);
            URLConnection connection = realUrl.openConnection();
            connection.connect();
            in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
            String line;
            while ((line = in.readLine()) != null)
            {
                result+=line;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try{
                if(in != null)
                {
                    in.close();
                }
            }catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return result;
    }
}
