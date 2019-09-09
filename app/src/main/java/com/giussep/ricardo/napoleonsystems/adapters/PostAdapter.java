package com.giussep.ricardo.napoleonsystems.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.giussep.ricardo.napoleonsystems.R;
import com.giussep.ricardo.napoleonsystems.model.Post;
import com.giussep.ricardo.napoleonsystems.utils.ItemTouchHelperAdapter;
import com.google.android.material.behavior.SwipeDismissBehavior;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> implements ItemTouchHelperAdapter {
    private List<Post> mDatase;
    private OnPostAdapter mListener;

    public interface OnPostAdapter {

        void onPostClicked(Post post);

        void onPostDelete(Post post);

        void onAllDeleteComplete();

        void onPostFavorite(Post post);
    }

    public PostAdapter(List<Post> mDatase, OnPostAdapter mListener) {
        this.mDatase = mDatase;
        this.mListener = mListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_post, parent, false);

        SwipeDismissBehavior<View> swipeDismissBehavior = new SwipeDismissBehavior<>();
        swipeDismissBehavior.setSwipeDirection(SwipeDismissBehavior.SWIPE_DIRECTION_START_TO_END);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(mDatase.get(position), mListener);
    }

    @Override
    public int getItemCount() {
        return mDatase != null ? mDatase.size() : 0;
    }

    public void deleteAllItems() {
        mDatase.clear();
        notifyDataSetChanged();
        mListener.onAllDeleteComplete();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private View viewIndicator;
        private ImageButton imageButtonFavorite;
        private TextView textViewTitle, textViewBody;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageButtonFavorite = itemView.findViewById(R.id.imageButton_favorite);
            textViewTitle = itemView.findViewById(R.id.textView_title);
            textViewBody = itemView.findViewById(R.id.textView_body);
            viewIndicator = itemView.findViewById(R.id.view_indicator);
        }

        void bind(Post post, OnPostAdapter mListener) {
            textViewTitle.setText(post.getTitle());
            textViewBody.setText(post.getBody());

            if (post.getLeido() == 0) {
                viewIndicator.setVisibility(View.VISIBLE);
            } else {
                viewIndicator.setVisibility(View.GONE);
            }

            if (post.getFavorite() == 0) {
                imageButtonFavorite.setImageDrawable(itemView.getContext().getDrawable(R.drawable.ic_star_border_accent_24));
            } else {
                imageButtonFavorite.setImageDrawable(itemView.getContext().getDrawable(R.drawable.ic_star_accent_24));
            }

            imageButtonFavorite.setOnClickListener(v -> {
                if (post.getFavorite() == 0) {
                    post.setFavorite(1);
                } else {
                    post.setFavorite(0);
                }

                mListener.onPostFavorite(post);
            });

            itemView.setOnClickListener(v -> mListener.onPostClicked(post));
        }
    }

    @Override
    public void onItemDismiss(int position) {
        mListener.onPostDelete(mDatase.get(position));
        mDatase.remove(position);
        notifyItemRemoved(position);
    }
}
