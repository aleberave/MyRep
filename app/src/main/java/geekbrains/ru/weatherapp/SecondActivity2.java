package geekbrains.ru.weatherapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

public class SecondActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        if (isConfiguarationOrientation()) return;

        getFragment(savedInstanceState);
    }

    private void getFragment(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            // Если Activity запускается первый раз (с каждыми новыми данными о погоде первый раз)
            // то перенаправим параметр фрагменту
            SecondFragment2 details = new SecondFragment2();
            details.setArguments(getIntent().getExtras());

            // Добавим фрагмент в Activity
            getSupportFragmentManager().beginTransaction()
                    .add(android.R.id.content, details).commitAllowingStateLoss();
        }
    }

    private boolean isConfiguarationOrientation() {
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // Если устройство перевернули в альбомную ориентацию, то надо эту activity закрыть
            finish();
            return true;
        }
        return false;
    }

}