package geekbrains.ru.weatherapp;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        setFragment();
    }

    private void setFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        SecondFragment secondFragment = (SecondFragment) fragmentManager.findFragmentByTag(SecondFragment.TAG);
        if (secondFragment == null) {
            Bundle bundle = new Bundle();
            bundle.putString("", "");
            secondFragment = SecondFragment.init(bundle);
        }
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.container2, secondFragment, SecondFragment.TAG);
        transaction.commitAllowingStateLoss();
    }
}
