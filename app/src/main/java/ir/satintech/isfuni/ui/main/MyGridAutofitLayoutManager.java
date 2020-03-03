package ir.satintech.isfuni.ui.main;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import ir.satintech.isfuni.utils.ScreenUtils;
import ir.satintech.isfuni.utils.ViewUtils;



public class MyGridAutofitLayoutManager {
    private int mColumnWidth;

    private int mColumnNumber;

    Context context;

    RecyclerView recyclerView;


    public MyGridAutofitLayoutManager(Context context, RecyclerView recyclerView) {
        this.context = context;

        this.recyclerView = recyclerView;

        setColumnWidth();
    }

    public int getTotalPaddingItems() {

        if (getmColumnNumber() == 3) {
            return 16;
        } else if (getmColumnNumber() == 4) {
            return 24;
        }
        return 0;
    }

    public void setColumnWidth() {

        int totalSpace;
        int recyclerViewWidth = recyclerView.getWidth();

        if (recyclerViewWidth == 0) {
            recyclerViewWidth = ScreenUtils.getScreenWidth(context);
        }

        Log.i("ecyclerView.getWidth()", "" + recyclerViewWidth);

        totalSpace = recyclerViewWidth - recyclerView.getPaddingRight() - recyclerView.getPaddingLeft();


        Log.i("totalSpace1", "" + totalSpace);


        totalSpace = (int) ViewUtils.pxToDp(totalSpace);

        Log.i("totalSpace2", "" + totalSpace);


        if (totalSpace <= 320) {

            mColumnNumber = 3;


        } else if (totalSpace > 320 && totalSpace <= 360) {
            mColumnNumber = 3;


        } else if (totalSpace > 360 && totalSpace <= 600) {

            mColumnNumber = 4;


        } else if (totalSpace > 600) {

            mColumnNumber = 4;


        }

        Log.i("mColumnNumber inlayout", "" + mColumnNumber);


        totalSpace = totalSpace - getTotalPaddingItems();
        Log.i("totalSpace3", "" + totalSpace);

        if (totalSpace <= 320) {

            mColumnWidth = (int) totalSpace / mColumnNumber;

        } else if (totalSpace > 320 && totalSpace <= 360) {

            mColumnWidth = (int) totalSpace / mColumnNumber;

        } else if (totalSpace > 360 && totalSpace <= 600) {

            mColumnWidth = (int) totalSpace / mColumnNumber;

        } else if (totalSpace > 600) {

            mColumnWidth = (int) totalSpace / mColumnNumber;
        }


        Log.i("mColumnWidth inlayout", "" + mColumnWidth);


    }


    public int getmColumnWidth() {
        return mColumnWidth;
    }


    public int getmColumnNumber() {
        return mColumnNumber;
    }
}