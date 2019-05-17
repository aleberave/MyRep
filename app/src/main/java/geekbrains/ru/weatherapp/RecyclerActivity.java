package geekbrains.ru.weatherapp;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler2);

        ArrayList<String> arrayList = getIntent().getStringArrayListExtra(MainActivity.THIRD);
        final List<DataClass> dataSource = new ArrayList<>();

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.VERTICAL, false);


        RVAdapter adapter = new RVAdapter(dataSource);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        if (arrayList.size() > 0) {
            for (int i = 0; i < arrayList.size(); i++) {
                String[] str = arrayList.get(i).split(getResources().getString(R.string.space));
                getDrawableWeather(str);
                dataSource.add(0, new DataClass(getDrawableWeather(str), arrayList.get(i), true));
                adapter.notifyDataSetChanged();
            }
        }
    }

    private Drawable getDrawableWeather(String[] str) {
        Drawable drawable = null;
        String precipitation = str[4];

        if (precipitation.startsWith("without")) {
            drawable = getResources().getDrawable(R.drawable.sun_cloud);
        } else if (precipitation.startsWith("wait")) {
            drawable = getResources().getDrawable(R.drawable.night_snow);
        }

        return drawable;
    }
}
