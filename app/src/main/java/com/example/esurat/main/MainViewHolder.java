package com.example.esurat.main;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.example.esurat.R;
import com.example.esurat.databinding.ItemMainBinding;
import com.example.esurat.model.Surat;
import com.github.wrdlbrnft.sortedlistadapter.SortedListAdapter;

public class MainViewHolder extends SortedListAdapter.ViewHolder<Surat> {
    private static final String TAG = "MainViewHolder";

    private ItemMainBinding mItemMainBinding;
    private Context context;

    MainViewHolder(Context context, ItemMainBinding mItemEncylopediaBinding) {
        super(mItemEncylopediaBinding.getRoot());
        this.mItemMainBinding = mItemEncylopediaBinding;
        this.context = context;
    }

    @Override
    protected void performBind(@NonNull Surat item) {
        if (item.getStatus().equals(MainConstant.NEW)) {
            mItemMainBinding.itemMainTextViewStatus.setTextColor(ContextCompat.getColor(context, R.color.error));
        }
        if (item.getStatus().equals(MainConstant.PROCESS)) {
            mItemMainBinding.itemMainTextViewStatus.setTextColor(ContextCompat.getColor(context, R.color.warning));
        }
        if (item.getStatus().equals(MainConstant.DONE)) {
            mItemMainBinding.itemMainTextViewStatus.setTextColor(ContextCompat.getColor(context, R.color.info));
        }
        mItemMainBinding.setSurat(item);
    }
}