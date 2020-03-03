package ir.satintech.isfuni.ui.location.map;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


public class CustomScrollListener extends RecyclerView.OnScrollListener {

    private final LinearLayoutManager layoutManager;

    int VisibleItemPosition = -1;

    private Callback mCallback;


    public CustomScrollListener(LinearLayoutManager layoutManager) {
        this.layoutManager = layoutManager;
    }


    public void setCallback(Callback callback) {
        mCallback = callback;
    }

    public interface Callback {
        void onItemSelected(int VisibleItemPosition);
    }


    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        switch (newState) {
            case RecyclerView.SCROLL_STATE_IDLE:


                int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();


                if (firstVisibleItemPosition != VisibleItemPosition) {

                    VisibleItemPosition = firstVisibleItemPosition;

                    if (mCallback != null) {
                        mCallback.onItemSelected(VisibleItemPosition);
                    }
                }

                break;
            case RecyclerView.SCROLL_STATE_DRAGGING:


                break;
            case RecyclerView.SCROLL_STATE_SETTLING:

                break;

        }

    }

}