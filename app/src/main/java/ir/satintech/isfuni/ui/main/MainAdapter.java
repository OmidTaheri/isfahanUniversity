package ir.satintech.isfuni.ui.main;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ir.satintech.isfuni.R;
import ir.satintech.isfuni.data.db.model.Category;
import ir.satintech.isfuni.ui.base.BaseViewHolder;
import ir.satintech.isfuni.utils.ViewUtils;


public class MainAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    public static final int VIEW_TYPE_EMPTY = 0;
    public static final int VIEW_TYPE_NORMAL = 1;


    private List<Category> list;
    private Context mContext;
    private Callback mCallback;
    MyGridAutofitLayoutManager Manager;

    public MainAdapter(List<Category> list, Context mContext, MyGridAutofitLayoutManager Manager) {
        this.list = list;
        this.mContext = mContext;
        this.Manager = Manager;
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

                View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_category, parent, false);

                CardView Card = layout.findViewById(R.id.parent);

                Card.setLayoutParams(new CardView.LayoutParams(
                        (int) ViewUtils.dpToPx(Manager.getmColumnWidth()), CardView.LayoutParams.WRAP_CONTENT));

                 return new ViewHolder(layout);

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


    public void addItems(List<Category> repoList) {
        list.addAll(repoList);
        notifyDataSetChanged();
    }

    public interface Callback {
        void onItemClick(Category item);
    }


    public class ViewHolder extends BaseViewHolder {
        @BindView(R.id.imageView)
        ImageView imageView;
        @BindView(R.id.textView)
        TextView textView;

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

            final Category item = list.get(position);

            textView.setText(item.getName());


            switch (item.getName()) {
                case "مراکز مذهبی و مساجد":
                    imageView.setImageResource(R.drawable.ic_mosque);
                    break;


                case "ورودی ها و خروجی ها":
                    imageView.setImageResource(R.drawable.ic_input_output);
                    break;


                case "دانشکده ها و مراکز آموزشی":
                    imageView.setImageResource(R.drawable.ic_amoozesh);
                    break;


                case "رستوران ها و غدذاخوری ها":
                    imageView.setImageResource(R.drawable.ic_food);
                    break;


                case "کافه ها":
                    imageView.setImageResource(R.drawable.ic_cafe);
                    break;


                case "مراکز اداری و خدماتی":
                    imageView.setImageResource(R.drawable.ic_service);
                    break;


                case "مراکز ورزشی":
                    imageView.setImageResource(R.drawable.ic_sport);
                    break;


                case "مراکز فرهنگی":
                    imageView.setImageResource(R.drawable.ic_talar);
                    break;


                case "تالارها و سالن های اجتماعات":
                    imageView.setImageResource(R.drawable.ic_talar);
                    break;

                case "خوابگاه ها":
                    imageView.setImageResource(R.drawable.ic_khabgah);
                    break;


                case "کتابخانه ها":
                    imageView.setImageResource(R.drawable.ic_library);
                    break;


                case "بانک ها و خودپردازها":
                    imageView.setImageResource(R.drawable.ic_bank);
                    break;


                case "مراکز بهداشتی و درمانی":
                    imageView.setImageResource(R.drawable.ic_hospital);
                    break;


                case "پارکینگ ها":
                    imageView.setImageResource(R.drawable.ic_parking);
                    break;


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
