package geekbrains.ru.weatherapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    public static final String THIRD = "THIRD";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        int countOfFragmentInManager = getSupportFragmentManager().getBackStackEntryCount();
        if (countOfFragmentInManager > 0) {
            getSupportFragmentManager().popBackStack("Some_Key", FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id) {
            case R.id.toWeatherActivity:
                Intent intent = new Intent(MainActivity.this, RecyclerActivity.class);
                intent.putExtra(THIRD, SecondFragment.list);
                startActivity(intent);
                return true;
            case R.id.toCoordinatorActivity:
                Intent intent1 = new Intent(MainActivity.this, CoordinatorActivity.class);
                intent1.putExtra(THIRD, SecondFragment.list);
                startActivity(intent1);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
