package com.example.test.adapters;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Victor
 */

public class CustomRecyclerView extends RecyclerView {
    private View emptyView;
    final private AdapterDataObserver observer = new AdapterDataObserver() {
        @Override
        public void onChanged() {
            checkIfEmpty();
        }

        @Override
        public void onItemRangeInserted(int positionStart, int itemCount) {
            checkIfEmpty();
        }

        @Override
        public void onItemRangeRemoved(int positionStart, int itemCount) {
            checkIfEmpty();
        }
    };

    public CustomRecyclerView(Context context) {
        super(context);
    }

    public CustomRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    void checkIfEmpty() {
        if (emptyView != null && getAdapter() != null) {
            final boolean emptyViewVisible = getAdapter().getItemCount() == 0;
            this.setVisibility(emptyViewVisible ? GONE : VISIBLE);
            emptyView.setVisibility(emptyViewVisible ? VISIBLE : GONE);
        }
    }

    @Override
    public void setAdapter(Adapter adapter) {
        if (getAdapter() != null) getAdapter().unregisterAdapterDataObserver(observer);
        if (adapter != null) adapter.registerAdapterDataObserver(observer);
        super.setAdapter(adapter);
        checkIfEmpty();
    }

    public void setEmptyView(View emptyView) {
            this.emptyView = emptyView;
        }
}
