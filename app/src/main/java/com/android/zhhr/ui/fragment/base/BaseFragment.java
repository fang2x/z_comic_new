package com.android.zhhr.ui.fragment.base;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.zhhr.presenter.BasePresenter;
import com.android.zhhr.ui.activity.base.BaseFragmentActivity;
import com.android.zhhr.utils.ToastUtils;

import butterknife.ButterKnife;

/**
 * Created by 皓然 on 2017/8/6.
 */

public abstract class BaseFragment<P extends BasePresenter> extends Fragment {
    protected P mPresenter;
    //初始化Presenter
    protected abstract void initPresenter();

    protected BaseFragmentActivity mActivity;

    protected abstract void initView(View view, Bundle savedInstanceState);

    //获取布局文件ID
    protected abstract int getLayoutId();

    //获取宿主Activity
    protected BaseFragmentActivity getHoldingActivity() {
        return mActivity;
    }

    protected ToastUtils toast;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mActivity = (BaseFragmentActivity) activity;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        ButterKnife.bind(this, view);
        initPresenter();
        checkPresenterIsNull();
        toast = new ToastUtils(getActivity());
        initView(view, savedInstanceState);
        return view;
    }

    private void checkPresenterIsNull() {
        if (mPresenter == null) {
            throw new IllegalStateException("please init mPresenter in initPresenter() method ");
        }
    }

    public void showToast(String text){
        toast.showToast(text);
        //Toast.makeText(mActivity, text, Toast.LENGTH_SHORT).show();
    }
}
