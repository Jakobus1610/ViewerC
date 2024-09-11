package com.example.viewerc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.pdf.PdfRenderer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
//import com.github.*;
//import com.github.barteksc.pdfviewer.
// com.github.mhiew:android-pdf-viewer:3.2.0-beta.1


import com.downloader.Error;
import com.downloader.OnDownloadListener;
import com.downloader.PRDownloader;
import com.example.viewerc.databinding.ActivityMainBinding;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnPageErrorListener;
import com.google.android.material.tabs.TabLayout;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'viewerc' library on application startup.
    static {
        System.loadLibrary("viewerc");
    }

    private ActivityMainBinding binding;
    private WebView webView;
    private static final String TAG = "MyActivity";
    CharSequence text = "";
    int duration = Toast.LENGTH_SHORT;
    final String pdfUrl = "https://www.osz1-technik-potsdam.de/wp-content/uploads/";

    //WebView pdfView;

    PDFView pdfView;
    ProgressBar progressBar;

    Context appCon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        pdfView = binding.pdfView;
        progressBar = binding.progressBar;

        appCon = getApplicationContext();

        PRDownloader.initialize(appCon);

        /*progressBar.setVisibility(View.VISIBLE);
        String fileName = "myFile.pdf";
        downloadPdfFromInternet(
                pdfUrl+"Montag.pdf",
                getRootDirPath(this),
                fileName);*/

        /*webView = binding.webView;
        setupWebViewWithUrl(pdfUrl + "Montag.pdf");*/


        //pdfView = ;

        binding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //do stuff here
                //webView.stopLoading();
               // webView.clearCache(false);

                // Setting up the WebView with the PDF URL


                String day = "Montag";

                switch (/*binding.tabLayout.getSelectedTabPosition()*/tab.getPosition())
                {
                    case 0:
                        day = "Montag";
                        break;
                    case 1:
                        day = "Dienstag";
                        break;
                    case 2:
                        day = "Mittwoch";
                        break;
                    case 3:
                        day = "Donnerstag";
                        break;
                    case 4:
                        day = "Freitag";
                        break;

                    default:
                        text = "ERROR. Tab pos is out of bounds";
                        Toast.makeText(binding.getRoot().getContext() /* MyActivity */, text, duration).show();
                        //toast.show();
                }

                /*text = day;
                Toast.makeText(binding.getRoot().getContext() *//* MyActivity *//*, text, duration).show();*/

                String url = pdfUrl + day + ".pdf";
                Log.v(TAG, url);

                progressBar.setVisibility(View.VISIBLE);
                String fileName = day + ".pdf";
                downloadPdfFromInternet(
                        url,
                        getRootDirPath(appCon),
                        fileName);


                /*webView.loadUrl("https://docs.google.com/gview?embedded=true&url=" + url);
                webView.requestFocus();*/
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        /*WebView webview = (WebView) findViewById(R.id.webview);
        webview.getSettings().setJavaScriptEnabled(true);
        String pdf = "https://www.osz1-technik-potsdam.de/wp-content/uploads/Montag.pdf";
        webview.loadUrl("http://docs.google.com/gview?embedded=true&url=" + pdf);*/



        // Example of a call to a native method
        //TextView tv = binding.sampleText;
        //tv.setText(stringFromJNI());
        // create a new renderer
  /*      PdfRenderer renderer = new PdfRenderer(getSeekableFileDescriptor());

        Bitmap mBitmap = Bitmap.createBitmap(
                screenWidth, (screenWidth.toFloat() / page.width * page.height).toInt(), Bitmap.Config.ARGB_8888
        );

        // let us just render all pages
        final int pageCount = renderer.getPageCount();
        for (int i = 0; i < pageCount; i++) {
            PdfRenderer.Page page = renderer.openPage(i);

            // say we render for showing on the screen
            page.render(mBitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY);

            // do stuff with the bitmap

            // close the page
            page.close();
        }

        // close the renderer
        renderer.close();

//        String value="https://www.osz1-technik-potsdam.de/wp-content/uploads/Montag.pdf";
//        Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse(value));
//
//        // start activity
//        startActivity(intent);
*/
       /* DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int screenWidth = displayMetrics.widthPixels;

        ParcelFileDescriptor input = ParcelFileDescriptor.open(File("montag.pdf"), ParcelFileDescriptor.MODE_READ_ONLY);
        PdfRenderer renderer;
        try {
            renderer = new PdfRenderer(input);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        PdfRenderer.Page page = renderer.openPage(0);
        Bitmap bitmap = Bitmap.createBitmap(
                screenWidth, (int)((float)screenWidth / page.getWidth() * page.getHeight()), Bitmap.Config.ARGB_8888
        );
        page.render(bitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY);

// do something with the bitmap, like putting it on an ImageView

        page.close();
        renderer.close();*/
    }

    private void downloadPdfFromInternet(String url, String dirPath, String fileName) {
        PRDownloader.download(url, dirPath, fileName)
                .build()
                .start(new OnDownloadListener() {
                    @Override
                    public void onDownloadComplete() {
//                        Toast.makeText(MainActivity.this, "downloadComplete", Toast.LENGTH_LONG).show();
                        File downloadedFile = new File(dirPath, fileName);
                        progressBar.setVisibility(View.GONE);
                        showPdfFromFile(downloadedFile);
                    }

                    @Override
                    public void onError(Error error) {
                        Toast.makeText(MainActivity.this, "Error in downloading file : " + error, Toast.LENGTH_LONG).show();
                    }
                });
    }

    private void showPdfFromFile(File file) {
        pdfView.fromFile(file)
                .password(null)
                .defaultPage(0)
                .enableSwipe(true)
                .swipeHorizontal(false)
                .enableDoubletap(true)
                .onPageError(new OnPageErrorListener() {
                    @Override
                    public void onPageError(int page, Throwable t) {
                        Toast.makeText(MainActivity.this, "Error at page: " + page, Toast.LENGTH_LONG).show();
                    }
                })
                .load();
    }

    public String getRootDirPath(Context context) {
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            File file = ContextCompat.getExternalFilesDirs(context.getApplicationContext(), null)[0];
            return file.getAbsolutePath();
        } else {
            return context.getApplicationContext().getFilesDir().getAbsolutePath();
        }
    }

    // This function configures the WebView to display the PDF.
    private void setupWebViewWithUrl(/*WebView webView,*/ String url) {
        if (webView != null) {
            WebSettings webSettings = webView.getSettings();
            webSettings.setJavaScriptEnabled(true);
            webSettings.setLoadWithOverviewMode(true);
            webSettings.setUseWideViewPort(true);
            webSettings.setSupportMultipleWindows(true);
            webSettings.setDomStorageEnabled(true);
            webSettings.setDatabaseEnabled(true);
            webSettings.setAllowFileAccess(true);
            webSettings.setAllowContentAccess(true);
            //webSettings.setAllowFileAccess(true);
            //webSettings.setUseWideViewPort(true);
            webSettings.setSupportZoom(true);
            //webSettings.setLoadWithOverviewMode(true);
            //webSettings.setJavaScriptEnabled(true);
            webSettings.setJavaScriptCanOpenWindowsAutomatically(false);
            //webSettings.setUseWideViewPort(true);


            // Configure a WebViewClient to handle navigation events
            webView.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                    // Return false to allow the WebView to handle the URL
                    return false;
                }
            });

            // Configure a WebChromeClient (optional)
            webView.setWebChromeClient(new WebChromeClient() {});

            // Generate HTML content to embed the PDF
            //String htmlContent = getPDFHtml(url);

            // Load the HTML content into the WebView
            //webView.loadData(htmlContent, "text/html", "utf-8");

            webView.loadUrl("https://docs.google.com/gview?embedded=true&url=" + url);
            webView.requestFocus();

            //Log.v(TAG, htmlContent);
        }
    }

    // This function generates the HTML content to embed the PDF.
    /*private String getPDFHtml(String url) {
        return "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, user-scalable=no\">\n" +
                "    <style>\n" +
                "        body, html {\n" +
                "            margin: 0;\n" +
                "            height: 100%;\n" +
                "            overflow: hidden;\n" +
                "        }\n" +
                "        iframe {\n" +
                "            position: absolute;\n" +
                "            top: 0;\n" +
                "            left: 0;\n" +
                "            width: 100%;\n" +
                "            height: 100%;\n" +
                "            border: none;\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                //"    <iframe src=\"" + url + "\" allow=\"autoplay\"></iframe>\n" +
                //"<iframe src=\"https://drive.google.com/file/d/1gsX_X_sPhWp2fs6GSZb-ZWJzSnJNFAUD/view\" allow=\"autoplay\"></iframe>\n" +
                "<embed src=\"https://drive.google.com/viewerng/viewer?embedded=true&url=https://www.osz1-technik-potsdam.de/wp-content/uploads/Montag.pdf&embedded=true\" width=\"500\" height=\"375\" type=\"application/pdf\">\n" +
                "</body>\n" +
                "</html>";
    }*/

    /**
     * A native method that is implemented by the 'viewerc' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();
}