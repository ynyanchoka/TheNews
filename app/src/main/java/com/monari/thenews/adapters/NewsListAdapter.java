package com.monari.thenews.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.monari.thenews.R;
import com.monari.thenews.models.Article;
import com.monari.thenews.ui.NewsDetailActivity;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.NewsViewHolder> {

    private List<Article> mNews;
    private Context mContext;

    public NewsListAdapter(Context context, List<Article> articles) {
        mContext = context;
        mNews = articles;
    }


    @Override
    public NewsListAdapter.NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.general_list_item, parent, false);
        NewsViewHolder viewHolder = new NewsViewHolder(view);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(NewsListAdapter.NewsViewHolder holder, int position) {
        holder.bindNews(mNews.get(position));
    }

    @Override
    public int getItemCount() {
        return mNews.size();
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.newsImageView)
        ImageView mNewsImageView;
        @BindView(R.id.newsSource)
        TextView mNewsSource;
        @BindView(R.id.titleNews)
        TextView mTitleNews;


        private Context mContext;

        public NewsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
            itemView.setOnClickListener(this);
        }

        public void bindNews(Article article) {
            mTitleNews.setText(article.getTitle());
            mNewsSource.setText(article.getSource().getName());
            Picasso.get().load(article.getUrlToImage()).into(mNewsImageView);

        }

        @Override
        public void onClick(View v) {
            int itemPosition = getLayoutPosition();
            Intent intent = new Intent(mContext, NewsDetailActivity.class);
            intent.putExtra("position", itemPosition);
            intent.putExtra("articles", Parcels.wrap(mNews));
            mContext.startActivity(intent);
        }
    }
}
