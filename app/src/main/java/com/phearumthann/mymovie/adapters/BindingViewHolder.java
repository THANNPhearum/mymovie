package com.phearumthann.mymovie.adapters;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;

/**
 * Created by PhearumThann on 2/21/17.
 * phearumandroid@gmail.com
 */
public class BindingViewHolder<T extends ViewDataBinding> extends RecyclerView.ViewHolder {
    private final T mLayoutBinding;

    public BindingViewHolder(T layoutBinding) {
        super(layoutBinding.getRoot());
        mLayoutBinding = layoutBinding;
    }

    public T getBinding() {
        return mLayoutBinding;
    }
}
