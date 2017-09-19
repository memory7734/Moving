package me.memory7734.moving.user;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.ScrollView;

import com.soundcloud.android.crop.Crop;

import java.io.File;

import me.memory7734.moving.R;
import me.memory7734.moving.service.CacheService;

public class UserPreferenceActivity extends AppCompatActivity implements View.OnClickListener {

    private final int REQUEST_CAMERA = 0;
    private final int REQUEST_GELLERY = 1;
    private String mPhotoCachePath;
    private IconView mHeadIcon;

    PopupWindow mPopupWindow;
    View rootView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        initViews();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            this.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            this.getWindow().setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));

        }


        ScrollView scrollView = (ScrollView) findViewById(R.id.scroll_view);
        scrollView.smoothScrollTo(0, 0);

        Fragment fragment = UserPreferenceFragment.newInstance();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.user_container, fragment)
                .commit();

        new UserPresenter(UserPreferenceActivity.this, (UserContract.View) fragment);
    }

    private void initViews() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setElevation(0);
        }
        mHeadIcon = (IconView) findViewById(R.id.head_icon);
        mHeadIcon.setOnClickListener(this);

        //拍照的临时文件保存在sd卡根目录，但暂时没办法删除（可能是crop中有inputstream没关闭）
        mPhotoCachePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/photoCache.jpg";

        //初始化换头像弹出菜单
        initPopupWindow();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        mHeadIcon.refresh();
    }


    @Override
    public void onClick(View view) {  //这里所有都是点击头像或菜单
        switch (view.getId()) {
            case R.id.head_icon:
                showPopupWindow();
                break;
            case R.id.take_photo:
                Intent intentFromCapture = new Intent(
                        MediaStore.ACTION_IMAGE_CAPTURE);
                File photoCache = new File(mPhotoCachePath);
                intentFromCapture.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoCache));
                startActivityForResult(intentFromCapture,
                        REQUEST_CAMERA);
                mPopupWindow.dismiss();
                break;
            case R.id.select_from_photograph:
                Intent intentFromGallery = new Intent();
                intentFromGallery.setType("image/*");
                intentFromGallery
                        .setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intentFromGallery,
                        REQUEST_GELLERY);
                mPopupWindow.dismiss();
                break;
            case R.id.pop_background:
            case R.id.pop_cancel:
                mPopupWindow.dismiss();
                break;
        }
    }

    private void initPopupWindow() {
        View contentView = LayoutInflater.from(UserPreferenceActivity.this)
                .inflate(R.layout.bar_icon_edit, null);

        mPopupWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT, true);
        mPopupWindow.setContentView(contentView);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));

        rootView = LayoutInflater.from(UserPreferenceActivity.this)  //把弹出菜单加到当前activity的layout中
                .inflate(R.layout.activity_user, null);

        View popBackground = contentView.findViewById(R.id.pop_background);
        View takePhoto = contentView.findViewById(R.id.take_photo);
        View fromPhotoGraph = contentView.findViewById(R.id.select_from_photograph);
        View popCancel = contentView.findViewById(R.id.pop_cancel);

        popBackground.setOnClickListener(this);
        takePhoto.setOnClickListener(this);
        fromPhotoGraph.setOnClickListener(this);
        popCancel.setOnClickListener(this);
    }

    //里面所有的都跟换头像有关
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CAMERA:
                if (resultCode == RESULT_OK) {
                    File photoCache = new File(mPhotoCachePath);
                    cropPhoto(Uri.fromFile(photoCache));
                }
                break;
            case REQUEST_GELLERY:
                if (data != null)
                    cropPhoto(data.getData());
                break;
            case Crop.REQUEST_CROP:
                if (resultCode == RESULT_OK) {
                    mHeadIcon.refresh();
                } else {
                    new AlertDialog.Builder(UserPreferenceActivity.this)
                            .setTitle("错误")
                            .setMessage("头像上传失败")
                            .setPositiveButton("确定", null)
                            .show();
                }
                break;
        }
    }

    private void showPopupWindow() {
        mPopupWindow.showAtLocation(rootView, Gravity.BOTTOM, 0, 0);
    }

    public void cropPhoto(Uri uri) {
        Context context = getApplicationContext();
        Uri outputUri = Uri.fromFile(new File(context.getCacheDir(), "MyHeadIcon"));
        Crop.of(uri, outputUri).asSquare().start(UserPreferenceActivity.this);
    }


}
