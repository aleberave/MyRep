package geekbrains.ru.weatherapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setFragment();
    }

    private void setFragment() {

        FragmentManager fragmentManager = getSupportFragmentManager();
        MainFragment mainFragment = (MainFragment) fragmentManager.findFragmentByTag(MainFragment.TAG);
        Bundle bundle = new Bundle();
        if (mainFragment == null) {
            bundle.putString("", "");
            mainFragment = MainFragment.init(bundle);
        }
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.container1, mainFragment, MainFragment.TAG);
        transaction.commitAllowingStateLoss();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        MainFragment mainFragment = (MainFragment) getSupportFragmentManager().findFragmentById(R.id.container1);
        mainFragment.onActivityResult(requestCode, resultCode, data);
    }

}
