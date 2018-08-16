package com.example.esurat.utils;

import android.support.v7.widget.RecyclerView;

public abstract class EndlessRecyclerOnScrollListenerUtils extends RecyclerView.OnScrollListener {
    public static String TAG = "EndlessScrollListener";

    private int previousTotal = 0; // The total number of items in the dataset after the last load
    private boolean loading = true; // True if we are still waiting for the last set of data to load.
    private int visibleThreshold = 10; // The minimum amount of items to have below your current scroll position before loading more.
    int firstVisibleItem, visibleItemCount, totalItemCount;

    private int currentPage = 1;

    RecyclerViewPositionUtils mRecyclerViewPositionUtils;

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        mRecyclerViewPositionUtils = RecyclerViewPositionUtils.createHelper(recyclerView);
        visibleItemCount = recyclerView.getChildCount();
        totalItemCount = mRecyclerViewPositionUtils.getItemCount();
        firstVisibleItem = mRecyclerViewPositionUtils.findFirstVisibleItemPosition();

        if (loading) {
            if (totalItemCount > previousTotal) {
                loading = false;
                previousTotal = totalItemCount;
            }
        }
        if (!loading && (totalItemCount - visibleItemCount)
                <= (firstVisibleItem + visibleThreshold)) {
            // End has been reached
            // Do something
            currentPage++;

            onLoadMore(recyclerView, currentPage);

            loading = true;
        }
    }

    //Start loading
    public abstract void onLoadMore(RecyclerView recyclerView, int currentPage);
}