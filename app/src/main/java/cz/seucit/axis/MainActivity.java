package cz.seucit.axis;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.HttpAuthHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import static cz.seucit.axis.environment.UrlsConstants.*;

public class MainActivity extends AppCompatActivity {

    WebView browser;
    EditText urlInput;
    Spinner spinner;

    public final static String USERNAME = "root";
    public final static String PASSWORD = "tricameras";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        getSupportActionBar().hide();

//        Intent intent = new Intent(this, LoginActivity.class);
//        startActivity(intent);

        spinner = findViewById(R.id.spinner);
        spinner.setSelection(0,false);

        final ArrayList<String> activities = new ArrayList<>();
        activities.add("Axis server");
        activities.add("Video only");
        activities.add("Custom controller");

        ArrayAdapter<String> adapter = new ArrayAdapter(getApplicationContext(),  android.R.layout.simple_spinner_dropdown_item, activities);
        adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                Intent intent;
                String selected = activities.get(position);
                switch (selected) {
                    case "Axis server":
                        break;
                    case "Video only":
                       intent = new Intent(MainActivity.this, VideoActivity.class);
                       startActivity(intent);
                       break;
                    case "Custom controller":
                        intent = new Intent(MainActivity.this, CustomControllerActivity.class);
                        startActivity(intent);
                        break;
                    default:
                        Toast.makeText(MainActivity.this, "Neoekávaný vstup.", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });

        urlInput = findViewById(R.id.urlInput);

        browser = findViewById(R.id.browser);
        browser.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedHttpAuthRequest(WebView view, HttpAuthHandler handler, String host, String realm) {
                handler.proceed(USERNAME, PASSWORD);
            }
        });

//        browser.setWebChromeClient(new WebChromeClient());
        browser.getSettings().setJavaScriptEnabled(true);
        browser.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);

        if (urlInput.getText().length() < 1) {
            urlInput.setText(BASE_URI.toString());
        }
    }

    @Override
    protected void onPostResume() {
        spinner.setSelection(0,false);
        super.onPostResume();
    }

    public void onClick_send(View view) {
        browser.loadUrl(String.valueOf(urlInput.getText()));
    }
}
