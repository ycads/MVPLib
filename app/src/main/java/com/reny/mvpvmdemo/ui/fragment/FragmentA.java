package com.reny.mvpvmdemo.ui.fragment;

import android.os.Bundle;

import com.reny.mvpvmdemo.R;
import com.reny.mvpvmdemo.core.MyBaseFragment;
import com.reny.mvpvmdemo.databinding.FragmentABinding;
import com.reny.mvpvmdemo.entity.event.RvScrollEvent;
import com.reny.mvpvmdemo.presenter.FAPresenter;
import com.reny.mvpvmdemo.vm.FAViewModel;
import com.reny.mvpvmlib.base.BasePresenter;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by admin on 2017/6/7.
 */

public class FragmentA extends MyBaseFragment<FragmentABinding> {

    private FAPresenter presenter;

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(RvScrollEvent event) {
        if(event.getTabIndex() == 0){
            presenter.getViewModel().layoutManager.scrollToPositionWithOffset(event.getPos(), 0);
        }
    }

    @Override
    protected boolean isEnableEventBus() {
        return true;
    }

    @Override
    protected BasePresenter getPresenter() {
        if(null == presenter){
            presenter = new FAPresenter(this, new FAViewModel());
        }
        return presenter;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        binding.setPresenter(presenter);
        binding.setViewModel(presenter.getViewModel());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_a;
    }
}
