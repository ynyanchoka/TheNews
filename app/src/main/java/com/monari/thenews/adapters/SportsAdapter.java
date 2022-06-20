package com.monari.thenews.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.monari.thenews.R;
import com.monari.thenews.models.Article;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SportsAdapter extends RecyclerView.Adapter<SportsAdapter.SportsViewHolder> {

    private List<Article> mNews;
    private Context mContext;

    public SportsAdapter(Context context, List<Article> articles) {
        mContext = context;
        mNews = articles;
    }


    @Override
    public SportsAdapter.SportsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.general_list_item, parent, false);
        SportsAdapter.SportsViewHolder viewHolder = new SportsAdapter.SportsViewHolder(view);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(SportsAdapter.SportsViewHolder holder, int position) {
        holder.bindNews(mNews.get(position));
    }

    @Override
    public int getItemCount() {
        return mNews.size();
    }

    public class SportsViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.newsImageView)
        ImageView mNewsImageView;
        @BindView(R.id.newsSource)
        TextView mNewsSource;
        @BindView(R.id.titleNews)
        TextView mTitleNews;


        private Context mContext;

        public SportsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
        }

        public void bindNews(Article article) {
            mTitleNews.setText(article.getTitle());
            mNewsSource.setText(article.getSource().getName());
            Picasso.get().load(article.getUrlToImage()).into(mNewsImageView);

        }
    }
}
