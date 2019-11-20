package ru.puchkova.homework332;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private Spinner languageSpinner;
    private Button ok;
    private TextView text;
    Locale locale = new Locale("ru");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init(){
        languageSpinner = findViewById(R.id.languageSpinner);
        ok = findViewById(R.id.ok);
        text = findViewById(R.id.text);
        initSpinnerCountries();

        ok.setOnClickListener(onClickListener);
    }

    private void initSpinnerCountries() {
        ArrayAdapter<CharSequence> adapterCountries = ArrayAdapter.createFromResource(this, R.array.language, android.R.layout.simple_spinner_item);
        adapterCountries.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        languageSpinner.setAdapter(adapterCountries);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Locale localeRu = new Locale("ru");
            Locale localeEn = new Locale("en");
            Configuration config = new Configuration();
            String language = languageSpinner.getSelectedItem().toString();
            if (language.equals("English")){
                config.setLocale(localeEn);
            } else {
                config.setLocale(localeRu);
                getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
            }
            getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
            recreate();
        }
    };
}
