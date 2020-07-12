package br.com.henrique.block07;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.NumberPicker;

public class MainActivity extends AppCompatActivity {

    NumberPicker possibilities;
    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = findViewById(R.id.webview);
        possibilities = findViewById(R.id.numberPicker);
        String[] possibilitiesString = {
                "Google",
                "FaceBook",
                "Instagran"
        };
        possibilities.setDisplayedValues(possibilitiesString);
        possibilities.setMinValue(0);
        possibilities.setMaxValue(possibilitiesString.length - 1);
    }

    public void navigate(View v) {
        int choice = possibilities.getValue();
        webView.setWebViewClient(new WebViewClient());
        switch (choice){
        case 0:
            webView.loadUrl("https://www.google.com/");
            break;
            case 1:
                webView.loadUrl("https://www.facebook.com/");
                break;
            case 2:
                webView.loadUrl("https://www.instagram.com/?hl=pt-br");
                break;
        }
    }

}
