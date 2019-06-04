package geekbrains.ru.weatherapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public static final String THIRD = "THIRD";
    private EditText searchEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m_activity_main);

        Toolbar toolbar = findViewById(R.id.toolbarM);
        searchEditText = findViewById(R.id.searchEditText);

        setSupportActionBar(toolbar);
        initFloatingActionBtn();
        initSideMenu(toolbar);
    }

    private void initSideMenu(Toolbar toolbar) {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void initFloatingActionBtn() {
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(MainActivity.this, view);
                getMenuInflater().inflate(R.menu.menu_main, popupMenu.getMenu());
                popupMenu.getMenu().add(0, 12345, 15, "Popup menu test");
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        handleMenuItemClick(menuItem);
                        return true;
                    }
                });
                popupMenu.show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        int countOfFragmentInManager = getSupportFragmentManager().getBackStackEntryCount();
        if (countOfFragmentInManager > 0) {
            getSupportFragmentManager().popBackStack("Some_Key", FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (searchEditText.getVisibility() == View.VISIBLE) {
            searchEditText.setVisibility(View.GONE);
            Objects.requireNonNull(getSupportActionBar()).setTitle(getString(R.string.app_name));
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu_main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id) {
            case R.id.toRecyclerActivity:
                if (SecondFragment.list.size() > 0) {
                    Intent intent = new Intent(MainActivity.this, RecyclerActivity.class);
                    intent.putExtra(THIRD, SecondFragment.list);
                    startActivity(intent);
                    return true;
                } else {
                    Toast.makeText(getApplicationContext(), "No data about weather", Toast.LENGTH_SHORT).show();
                    return true;
                }
            case R.id.toCoordinatorActivity:
                if (SecondFragment.list.size() > 0) {
                    Intent intent1 = new Intent(MainActivity.this, CoordinatorActivity.class);
                    intent1.putExtra(THIRD, SecondFragment.list);
                    startActivity(intent1);
                    return true;
                } else {
                    Toast.makeText(getApplicationContext(), "No data about weather", Toast.LENGTH_SHORT).show();
                    return true;
                }
            case R.id.toSecondActivity2:
                return true;
        }

        handleMenuItemClick(item);
        return super.onOptionsItemSelected(item);
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
            case R.id.menu_search: {
                Objects.requireNonNull(getSupportActionBar()).setTitle("");
                searchEditText.setVisibility(View.VISIBLE);
                searchEditText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        Toast.makeText(getApplicationContext(), s.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
            default: {
                Toast.makeText(getApplicationContext(), "Action not found", Toast.LENGTH_SHORT)
                        .show();
            }
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        int optionId = R.layout.m_content_main;

        if (id == R.id.nav_about_developer) {

            optionId = R.layout.fragment_about_developer;
            findViewById(R.id.m_container_port_2).setVisibility(View.GONE);

            ViewGroup parent = findViewById(R.id.m_container1);
            parent.removeAllViews();
            View newContent = getLayoutInflater().inflate(optionId, parent, false);
            parent.addView(newContent);

            item.setChecked(true);


        } else if (id == R.id.nav_callback) {

            Fragment fragment = null;
            Class fragmentClass;

            fragmentClass = Feedback.class;

            try {
                fragment = (Fragment) fragmentClass.newInstance();
            } catch (Exception e) {
                e.printStackTrace();

            }

            ViewGroup parent = findViewById(R.id.m_container1);
            parent.removeAllViews();

            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.m_container1,
                    Objects.requireNonNull(fragment))
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();

            item.setChecked(true);
            Toast.makeText(getApplicationContext(), "callback", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_share) {
            Toast.makeText(getApplicationContext(), "share", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_send) {
            Toast.makeText(getApplicationContext(), "send", Toast.LENGTH_SHORT).show();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
