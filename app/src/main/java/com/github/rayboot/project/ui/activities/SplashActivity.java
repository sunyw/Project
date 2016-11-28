package com.github.rayboot.project.ui.activities;

import android.os.Bundle;
import android.util.Log;

import com.github.rayboot.project.R;
import com.github.rayboot.project.support.utils.rxutils.SchedulersCompat;
import com.github.rayboot.project.ui.activities.base.BaseAppCompatActivity;

import java.util.concurrent.TimeUnit;

import rx.Observable;

/**
 * author : YW.SUN Created on 2016/11/28
 * email : sunyw10@gmail.com
 */
public class SplashActivity extends BaseAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Observable.interval(3, TimeUnit.SECONDS)
                .take(1)
                .compose(SchedulersCompat.applyIoSchedulers())
                .subscribe(
                        aLong -> {
                            TabMainActivity.open(this);
                        },
                        throwable -> {
                            Log.e(TAG, throwable.getLocalizedMessage());
                        }
                );
    }
}
