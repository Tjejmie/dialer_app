package se.miun.jasv2000.dt031g.dialer;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import android.util.Log;
import android.view.MenuItem;
import android.webkit.CookieManager;
import android.webkit.DownloadListener;
import android.webkit.URLUtil;

import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

public class DownloadActivity extends AppCompatActivity {


    private ProgressDialog pDialog;
    private WebView webView;
    String path;
    String urlPage;
    String fileName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);

        Bundle extras = getIntent().getExtras();
        urlPage = extras.getString(getResources().getString(R.string.URL_KEY));
        path = extras.getString(getResources().getString(R.string.FILEPATH_KEY));

        webView = findViewById(R.id.webview);
        webView.loadUrl(urlPage);

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                if (url.startsWith(urlPage)) {
                    webView.loadUrl(url);
                    return super.shouldOverrideUrlLoading(view, url);
                }
                return false;
            }

        });

        webView.setDownloadListener(new DownloadListener()
        {
            @Override
            public void onDownloadStart(String url, String userAgent,
                                        String contentDisposition, String mimeType,
                                        long contentLength) {
                DownloadManager.Request request = new DownloadManager.Request(
                        Uri.parse(url));
                request.setMimeType(mimeType);
                fileName = url.substring(url.lastIndexOf('/') + 1);
                String cookies = CookieManager.getInstance().getCookie(url);
                request.addRequestHeader("cookie", cookies);
                request.addRequestHeader("User-Agent", userAgent);
                request.setDescription("Downloading File...");
                request.setTitle(URLUtil.guessFileName(url, contentDisposition, mimeType));
                request.allowScanningByMediaScanner();
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);

                new DownloadVoices().execute(urlPage + fileName);



            }});

    }

    class DownloadVoices  extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            pDialog = new ProgressDialog(DownloadActivity.this);
            pDialog.setTitle(R.string.downloading_title);
            pDialog.setMessage(fileName);
            pDialog.setButton(ProgressDialog.BUTTON_NEGATIVE, "cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            pDialog.setMax(100);
            pDialog.setIndeterminate(true);
            //pDialog.setCancelable(false);

            pDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            pDialog.show();
        }

        @Override
        protected void onProgressUpdate(String... progress) {
            super.onProgressUpdate(progress);
            // if we get here, length is known, now set indeterminate to false
            pDialog.setIndeterminate(false);
            pDialog.setMax(100);
            pDialog.setProgress(Integer.parseInt(progress[0]));
        }

        /**
         * Downloading file in background thread
         * */
        @Override
        protected String doInBackground(String... f_url) {

            try {
                URL url = new URL(f_url[0]);
                URLConnection connection = url.openConnection();
                connection.connect();
                // getting file length
                int fileLength = connection.getContentLength();
                // input stream to read file - with 8k buffer
                InputStream input = new BufferedInputStream(url.openStream(), 8192);
                // Output stream to write file
                OutputStream output = new FileOutputStream(path + R.string.downloadFileName);

                // make download slower
                byte data[] = new byte[5];

                long total = 0;
                int count;
                while ((count = input.read(data)) != -1) {
                    // allow canceling with back button
                    if (isCancelled()) {
                        input.close();
                        return null;
                    }
                    total += count;
                    // publishing the progress....
                    if (fileLength > 0) // only if total length is known
                        publishProgress(String.valueOf((int) (total * 100 / fileLength)));
                    output.write(data, 0, count);
                }


                output.flush();
                output.close();
                input.close();

            } catch (Exception e) {
                Log.e("Error: ", e.getMessage());
            }
            Util.unzip(new File(path + R.string.downloadFileName), new File(path));


            File file = new File(path, String.valueOf(R.string.downloadFileName));
            boolean deleted = file.delete();

            return null;
        }

        /**
         * After completing background task
         * **/
        @Override
        protected void onPostExecute(String result) {

            pDialog.dismiss();

            if (result != null)
                Toast.makeText(getApplicationContext(),R.string.download_error+result, Toast.LENGTH_LONG).show();
            else
                Toast.makeText(getApplicationContext(),R.string.file_downloaded, Toast.LENGTH_SHORT).show();
        }

    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);

    }



}