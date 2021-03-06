package com.github.rayboot.project.ui.activities.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.github.rayboot.project.support.api.ApiFactory;
import com.github.rayboot.project.support.api.services.ApiService;
import com.github.rayboot.project.support.managers.listeners.IEventBus;
import com.umeng.analytics.MobclickAgent;

import org.greenrobot.eventbus.EventBus;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * @author rayboot
 * @from 14/11/3 14:04
 * @TODO
 */
public class BaseAppCompatActivity extends AppCompatActivity {

    public static final ApiService apiService = ApiFactory.getApi().getApiService();
    protected final String TAG = this.getClass().getSimpleName();

    private CompositeSubscription mCompositeSubscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this instanceof IEventBus) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    @Override
    protected void onDestroy() {
        if (this instanceof IEventBus) {
            EventBus.getDefault().unregister(this);
        }
        if (this.mCompositeSubscription != null) {
            this.mCompositeSubscription.unsubscribe();
        }
        super.onDestroy();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public CompositeSubscription getCompositeSubscription() {
        if (this.mCompositeSubscription == null) {
            this.mCompositeSubscription = new CompositeSubscription();
        }

        return this.mCompositeSubscription;
    }

    public void addSubscription(Subscription s) {
        getCompositeSubscription().add(s);
    }
}
