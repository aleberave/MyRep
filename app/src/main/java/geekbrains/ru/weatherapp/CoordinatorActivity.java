package geekbrains.ru.weatherapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CoordinatorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coordinator_activity);

        // Установка Action Bar на основе Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ArrayList<String> arrayList = getIntent().getStringArrayListExtra(MainActivity.THIRD);
        final List<DataClass> dataSource = new ArrayList<>();

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.VERTICAL, false);


        RVAdapter adapter = new RVAdapter(dataSource);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        ViewCompat.setNestedScrollingEnabled(recyclerView, false);

        if (arrayList.size() > 0) {
            for (int i = 0; i < arrayList.size(); i++) {
                String[] str = arrayList.get(i).split(getResources().getString(R.string.space));
                int drawableResID = getDrawableWeather(str);
                String newCityName = getCityName(str);
                dataSource.add(0, new DataClass(drawableResID, newCityName, false));
                adapter.notifyDataSetChanged();
            }
        }
        pressedFab();
    }

    private void pressedFab() {
        // обработка кликов по FAB кнопке
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "This is button-SnackBar-FAB", Snackbar.LENGTH_LONG)
                        .setAction("Button", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(getApplicationContext(),
                                        "Pressed button-SnackBar-FAB.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }).show();
            }
        });
    }

    private int getDrawableWeather(String[] str) {
        int draw = 0;
        String drawableResID = str[str.length - 1];
        draw = getResources().getIdentifier(drawableResID, null, null);
        return draw;
    }

    private String getCityName(String[] str) {
        String[] newStr = new String[4];
        System.arraycopy(str, 0, newStr, 0, 4);
        String newString = "";
        for (int i = 0; i < newStr.length; i++) {
            newString = newString + newStr[i];
        }
        return newString;
    }
}
