package ru.puchkova.homework332;

import androidx.annotation.IntDef;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private Spinner languageSpinner;
    private Spinner sizeSpinner;
    private Button ok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Utils.onActivityCreateSetTheme(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle(getResources().getString(R.string.app_name));

        init();
    }

    private void init() {
        languageSpinner = findViewById(R.id.languageSpinner);
        ok = findViewById(R.id.ok);
        sizeSpinner = findViewById(R.id.sizeSpinner);
        initSpinnerLanguage();
        initSpinnerColor();

        ok.setOnClickListener(onClickListener);
    }

    private void initSpinnerLanguage() {
        ArrayAdapter<CharSequence> adapterLanguage = ArrayAdapter.createFromResource(this, R.array.language, android.R.layout.simple_spinner_item);
        adapterLanguage.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        languageSpinner.setAdapter(adapterLanguage);
    }

    private void initSpinnerColor() {
        ArrayAdapter<CharSequence> adapterSize = ArrayAdapter.createFromResource(this, R.array.size, android.R.layout.simple_spinner_item);
        adapterSize.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sizeSpinner.setAdapter(adapterSize);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Locale localeRu = new Locale("ru");
            Locale localeEn = new Locale("en");
            Configuration config = new Configuration();
            String language = languageSpinner.getSelectedItem().toString();
            if (language.equals("English")) {
                config.setLocale(localeEn);
            } else {
                config.setLocale(localeRu);
                getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
            }
            getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
            recreate();

            int size = sizeSpinner.getSelectedItemPosition();
            setSize(size);
        }
    };

    @IntDef({size.SMALL, size.MEDIUM, size.HUGE})
    private @interface size {
        int HUGE = 0;
        int MEDIUM = 1;
        int SMALL = 2;
    }

    private void setSize(int size) {
        switch (size) {
            case 0:
                Utils.changeToTheme(this, Utils.THEME_DEFAULT);
                break;
            case 1:
                Utils.changeToTheme(this, Utils.THEME_MEDIUM);
                break;
            case 2:
                Utils.changeToTheme(this, Utils.THEME_SMALL);
                break;
        }
    }
}
