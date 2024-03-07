package com.example.project.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Network_utils {
    /**
     * connects to the online database
     * @param url
     * @return string (which contains all the information json string)
     * @throws IOException
     */
    public static String makeHttpsrequest(URL url) throws IOException {
        HttpURLConnection connection=(HttpURLConnection) url.openConnection();
        InputStream inputStream=connection.getInputStream();
        try {

        Scanner scanner=new Scanner(inputStream);
        scanner.useDelimiter("\\A");
        boolean hasinput=scanner.hasNext();
        if(hasinput){
            return scanner.next();
        }
        else return null;}
        finally {
            connection.disconnect();
        }

    }

}
