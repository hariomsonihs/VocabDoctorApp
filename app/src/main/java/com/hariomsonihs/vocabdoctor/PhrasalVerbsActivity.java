package com.hariomsonihs.vocabdoctor;

import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.appcompat.app.AppCompatActivity;

import java.io.InputStream;

public class PhrasalVerbsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phrasal_verbs);

        WebView webView = findViewById(R.id.webView);
        WebSettings webSettings = webView.getSettings();

        // Enable JavaScript and other required settings
        webSettings.setJavaScriptEnabled(true);          // Required for JS execution
        webSettings.setDomStorageEnabled(true);         // For local storage if needed
        webSettings.setAllowFileAccess(true);           // Allow access to file:///android_asset/
        webSettings.setAllowContentAccess(true);        // Allow content access
        webSettings.setAllowFileAccessFromFileURLs(true); // Allow file access from file URLs
        webSettings.setAllowUniversalAccessFromFileURLs(true); // Allow universal access from file URLs
        webSettings.setDefaultTextEncodingName("UTF-8"); // Ensure UTF-8 encoding
        webSettings.setSupportZoom(false);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setBuiltInZoomControls(false);
        webSettings.setDisplayZoomControls(false);
        webSettings.setSupportZoom(false);

        // Configure WebViewClient for page load handling
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                // Inject CSS after page loads
                injectCSS(view);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // Ensure all file:/// URLs stay within WebView
                if (url.startsWith("file:///")) {
                    view.loadUrl(url);
                    return true;
                }
                return false;
            }
        });

        // Load local HTML file with category parameter
        String category = "phrasal-verbs"; // You can make this dynamic if needed
        webView.loadUrl("file:///android_asset/category.html?category=" + category);
    }

    // Inject CSS into WebView
    private void injectCSS(WebView webView) {
        try {
            InputStream inputStream = getAssets().open("styles.css");
            byte[] buffer = new byte[inputStream.available()];
            inputStream.read(buffer);
            inputStream.close();
            String encoded = Base64.encodeToString(buffer, Base64.NO_WRAP);
            String js = "javascript:(function() {" +
                    "var parent = document.getElementsByTagName('head').item(0);" +
                    "var style = document.createElement('style');" +
                    "style.type = 'text/css';" +
                    "style.innerHTML = window.atob('" + encoded + "');" +
                    "parent.appendChild(style)" +
                    "})()";
            webView.evaluateJavascript(js, value -> {
                if (value != null) {
                    Log.d("CSS Injection", "CSS injected successfully");
                }
            });
        } catch (Exception e) {
            Log.e("CSS Injection", "Failed to inject CSS", e);
        }
    }
}