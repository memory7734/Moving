package me.memory7734.moving.insert;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;

import me.memory7734.moving.R;

public class InsertPreferenceActivity extends AppCompatActivity {

    private Fragment fragment;
    private InsertPresenter insertPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        initViews();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            this.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            this.getWindow().setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));
        }

        fragment = InsertPreferenceFragment.newInstance();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.settings_container, fragment)
                .commit();
        insertPresenter = new InsertPresenter(InsertPreferenceActivity.this, (InsertContract.View) fragment);

    }

    private void initViews() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setElevation(0);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_insert, menu);
        return true;
    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        if (item.getItemId() == R.id.action_save) {
            if (insertPresenter.save()) {
//                startActivity(new Intent(InsertPreferenceActivity.this, MainActivity.class));
                finish();
            }
        } else {
//            startActivity(new Intent(InsertPreferenceActivity.this, MainActivity.class));
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

}
