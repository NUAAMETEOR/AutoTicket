package cn.edu.nuaa.autoticket.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Surface;
import android.view.Window;
import android.view.WindowManager;

import cn.edu.nuaa.autoticket.R;

/**
 * Created by Meteor on 2018/3/28.
 */

public abstract class BaseFragmentActivity extends AppCompatActivity {
    protected boolean fullScreen = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        fullScreenMode();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadFragment();
    }

    protected void loadFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager != null) {
            fragmentManager.beginTransaction().add(R.id.base_fragment_container, prepareFragment()).commit();
        }
    }

    protected void fullScreenMode() {
        if (fullScreen) {
            if (this instanceof AppCompatActivity) {
                ActionBar actionBar = getSupportActionBar();
                if (actionBar != null) {
                    actionBar.hide();
                }
            } else {
                getWindow().requestFeature(Window.FEATURE_NO_TITLE);
            }
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
    }

    public static int getScreenOrientation(Activity activity) {
        int rotation = activity.getWindowManager().getDefaultDisplay().getRotation();
        return rotation;//以逆时针来看，角度分别为0，90，180，270
    }

    protected abstract Fragment prepareFragment();
}
