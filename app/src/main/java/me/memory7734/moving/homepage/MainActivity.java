package me.memory7734.moving.homepage;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import me.memory7734.moving.R;
import me.memory7734.moving.settings.SettingsPreferenceActivity;
import me.memory7734.moving.user.UserPreferenceActivity;

public class MainActivity extends AppCompatActivity {

    private MainFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences sp = getSharedPreferences("user_settings", Context.MODE_PRIVATE);
        if (sp.getInt("userkey", 0) == 0) {
//            startActivity(new Intent(MainActivity.this, LoginActivity.class));
//            this.finish();
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (fragment != null){
            fragmentTransaction.remove(fragment);
        }
        fragment = MainFragment.newInstance();
        fragmentTransaction.add(R.id.layout_fragment, fragment);
        fragmentTransaction.commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                startActivity(new Intent(MainActivity.this, SettingsPreferenceActivity.class));
                return true;
            case R.id.action_user:
                startActivity(new Intent(MainActivity.this, UserPreferenceActivity.class));
                return true;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }


}
