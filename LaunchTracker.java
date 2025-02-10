package com.example.PageCraft;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import android.os.AsyncTask;

public class LaunchTracker {

    // Public method to send data, calling AsyncTask in the background
    public static void sendData(String name, String appName) {
        new SendDataTask().execute(name, appName);
    }

    // AsyncTask to handle network operation in the background thread
    private static class SendDataTask extends AsyncTask<String, Void, Integer> {
        @Override
        protected Integer doInBackground(String... params) {
            try {
                // Get the current date and format it
                String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

                // Set up the connection with the server
                URL url = new URL("http://192.168.0.228:5800/api/post");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                // Set request method to POST
                connection.setRequestMethod("POST");

                // Enable input/output streams
                connection.setDoOutput(true);

                // Set content type to application/x-www-form-urlencoded
                connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

                // Form the data to send in the POST request
                String data = "name=" + params[0] + "&app_name=" + params[1] + "&date=" + date;

                // Write the data to the output stream
                try (OutputStream os = connection.getOutputStream()) {
                    byte[] input = data.getBytes("utf-8");
                    os.write(input, 0, input.length);
                }

                // Check the response code
                int responseCode = connection.getResponseCode();
                return responseCode;

            } catch (Exception e) {
                e.printStackTrace();
                return -1; // Return failure code
            }
        }

        @Override
        protected void onPostExecute(Integer result) {
            // Handle result after the background task completes
            if (result == HttpURLConnection.HTTP_OK) {
                // Handle success (logging, showing a message, etc.)
                System.out.println("Data sent successfully!");
            } else {
                // Handle failure
                System.out.println("Failed to send data.");
            }
        }
    }
}

