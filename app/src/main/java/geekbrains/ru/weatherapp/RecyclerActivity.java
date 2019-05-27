package geekbrains.ru.weatherapp;

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
        setContentView(R.layout.activity_recycler);

        getRecyclerFragment();
    }

    private void getRecyclerFragment() {
        ArrayList<String> arrayList = getIntent().getStringArrayListExtra(MainActivity.THIRD);
        final List<DataClass> dataSource = new ArrayList<>();

        RecyclerView recyclerView = findViewById(R.id.recyclerViewCity);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.VERTICAL, false);

        RVAdapter adapter = new RVAdapter(dataSource, this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        if (arrayList.size() > 0) {
            for (int i = 0; i < arrayList.size(); i++) {
                String[] str = arrayList.get(i).split(getResources().getString(R.string.sSpace));
                int drawableResID = getDrawableWeather(str);
                StringBuilder newCityName = getCityName(str);
                dataSource.add(0, new DataClass(drawableResID, newCityName, false));
                adapter.notifyDataSetChanged();
            }
        }
    }

    private int getDrawableWeather(String[] str) {
        String drawableResID = str[str.length - 1];
        return getResources().getIdentifier(drawableResID, null, null);
    }

    private StringBuilder getCityName(String[] str) {
        String[] newStr = new String[4];
        System.arraycopy(str, 0, newStr, 0, 4);
        StringBuilder newString = new StringBuilder();
        for (String s : newStr) newString.append(s);
        return newString;
    }
}
