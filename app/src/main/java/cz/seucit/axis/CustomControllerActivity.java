package cz.seucit.axis;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.HttpAuthHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import cz.seucit.axis.enums.Move;
import cz.seucit.axis.enums.Zoom;

import static cz.seucit.axis.MainActivity.USERNAME;
import static cz.seucit.axis.MainActivity.PASSWORD;
import static cz.seucit.axis.environment.UrlsConstants.BASE_VIDEO_URI;

public class CustomControllerActivity extends AppCompatActivity {

    WebView browser;
    Spinner spinnerMove;
    Spinner spinnerZoom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_controller);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        getSupportActionBar().hide();

        browser = findViewById(R.id.controllerView);
        browser.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedHttpAuthRequest(WebView view, HttpAuthHandler handler, String host, String realm) {
                handler.proceed(USERNAME, PASSWORD);
            }
        });

//        browser.setWebChromeClient(new WebChromeClient());
        browser.getSettings().setJavaScriptEnabled(true);
        browser.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        browser.loadUrl(BASE_VIDEO_URI.toString());

        //<editor-fold desc="Spinner Move">
        spinnerMove = findViewById(R.id.spinerMove);
        spinnerMove.setAdapter(new ArrayAdapter<Move>(this, android.R.layout.simple_spinner_dropdown_item, Move.values()));
        spinnerMove.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                Move typeOfMove = Move.values()[position];
                if (Move.Move == typeOfMove) {
                    return;
                }
                String moveUrl = typeOfMove.getUrl();
                System.out.println("typeOfMove = " + typeOfMove);
                System.out.println("moveUrl = " + moveUrl);
                browser.loadUrl(moveUrl);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                System.out.println();
                // your code here
            }

        });
        //</editor-fold>

        //<editor-fold desc="Spinner Zoom">
        spinnerZoom = findViewById(R.id.sprZoom);
        spinnerZoom.setAdapter(new ArrayAdapter<Zoom>(this, android.R.layout.simple_spinner_dropdown_item, Zoom.values()));
        spinnerZoom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                Zoom typeOfZoom = Zoom.values()[position];
                if (Zoom.Select == typeOfZoom) {
                    return;
                }
                String rotateUrl = typeOfZoom.getUrl();
                System.out.println("typeOfZoom = " + typeOfZoom);
                System.out.println("rotateUrl = " + rotateUrl);
                browser.loadUrl(rotateUrl);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });
        //</editor-fold>
    }
}

// zoom=<int>               1 - 9999
// rzoom=<int>              -9999 - 9999
// rotation=<int>           0, 90, 180, 270 1
// imagerotation=<int>      0, 90, 180, 270
