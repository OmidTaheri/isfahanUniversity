package ir.satintech.isfuni.ui.location.list;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ir.satintech.isfuni.R;
import ir.satintech.isfuni.data.db.model.Location;
import ir.satintech.isfuni.ui.base.BaseViewHolder;
import ir.satintech.isfuni.ui.detailpage.DetailLocationActivity;
import ir.satintech.isfuni.utils.GetResourceByName;


public class ListAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    public static final int VIEW_TYPE_EMPTY = 0;
    public static final int VIEW_TYPE_NORMAL = 1;


    private List<Location> list;
    private Context mContext;
    private Callback mCallback;

    public ListAdapter(List<Location> list, Context mContext) {
        this.list = list;
        this.mContext = mContext;
    }

    public void setCallback(Callback callback) {
        mCallback = callback;
    }

    private Context getContext() {
        return mContext;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        switch (viewType) {

            case VIEW_TYPE_NORMAL:
                return new ViewHolder(
                        LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_location, parent, false));


            case VIEW_TYPE_EMPTY:
            default:
                return new EmptyViewHolder(
                        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_empty_view_fullscreen, parent, false));
        }

    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.onBind(position);
    }


    @Override
    public int getItemCount() {

        if (list != null && list.size() > 0) {
            return list.size();
        } else {
            return 1;
        }

    }


    @Override
    public int getItemViewType(int position) {
        if (list != null && list.size() > 0) {
            return VIEW_TYPE_NORMAL;
        } else {
            return VIEW_TYPE_EMPTY;
        }
    }


    public void addItems(List<Location> repoList) {
        list.addAll(repoList);
        notifyDataSetChanged();
    }

    public interface Callback {
        void onItemClick(Location item);
    }


    public class ViewHolder extends BaseViewHolder {

        @BindView(R.id.item_image)
        ImageView itemImage;
        @BindView(R.id.name)
        TextView name;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        protected void clear() {

        }

        @Override
        public void onBind(int position) {
            super.onBind(position);

            final Location item = list.get(position);

            name.setText(item.getName());



            try {
                int ResId = GetResourceByName.getDrawable(mContext, item.getImage_url());


                Glide.with(mContext)
                        .load(ResId)
                        .apply(new RequestOptions().placeholder(R.drawable.placeholder))
                        .into(itemImage);
            } catch (Exception e) {
            }


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mCallback != null)
                        mCallback.onItemClick(item);


                }
            });
        }
    }


    public class EmptyViewHolder extends BaseViewHolder {
        @BindView(R.id.message)
        TextView message;

        public EmptyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        protected void clear() {

        }


        @Override
        public void onBind(int position) {
            super.onBind(position);
            message.setText("موردی وجود ندارد");
        }

    }

}
