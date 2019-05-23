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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CoordinatorActivity extends AppCompatActivity {

    private RVAdapter adapter = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coordinator_activity);

        getActionToolBar();
        getRecyclerFragment();
        pressedFab();
    }

    private void getActionToolBar() {
        // Установка Action Bar на основе Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void getRecyclerFragment() {
        ArrayList<String> arrayList = getIntent().getStringArrayListExtra(MainActivity.THIRD);
        final List<DataClass> dataSource = new ArrayList<>();

        RecyclerView recyclerView = findViewById(R.id.recyclerViewCity);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.VERTICAL, false);


        adapter = new RVAdapter(dataSource, this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        ViewCompat.setNestedScrollingEnabled(recyclerView, false);

        addDataInFragment(arrayList, dataSource);
    }

    private void addDataInFragment(ArrayList<String> arrayList, List<DataClass> dataSource) {
        if (arrayList.size() > 0) {
            for (int i = 0; i < arrayList.size(); i++) {
                String[] str = arrayList.get(i).split(getResources().getString(R.string.space));
                int drawableResID = getDrawableWeather(str);
                StringBuilder newCityName = getCityName(str);
                dataSource.add(0, new DataClass(drawableResID, newCityName, false));
                adapter.notifyDataSetChanged();
            }
        }
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_coordination, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        handleMenuItemClick(item);
        return true;
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        handleMenuItemClick(item);
        return true;
    }

    private void handleMenuItemClick(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.menu_remove: {
                adapter.removeElement();
                adapter.notifyDataSetChanged();
                break;
            }
            case R.id.menu_clear: {
                adapter.clearList();
                adapter.notifyDataSetChanged();
                break;
            }
            case R.id.searchEditText:
                Toast.makeText(getApplicationContext(), "search", Toast.LENGTH_SHORT).show();
                break;
            default: {
                Toast.makeText(getApplicationContext(), "Action not found", Toast.LENGTH_SHORT)
                        .show();
            }
        }
    }
}
