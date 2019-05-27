package geekbrains.ru.weatherapp;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

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
            SecondFragment details = new SecondFragment();
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
