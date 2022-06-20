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

public class ScienceAdapter extends RecyclerView.Adapter<ScienceAdapter.ScienceViewHolder>{

    private List<Article> mNews;
    private Context mContext;

    public ScienceAdapter(Context context, List<Article> articles) {
        mContext = context;
        mNews = articles;
    }


    @Override
    public ScienceAdapter.ScienceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.science_list_item, parent, false);
        ScienceAdapter.ScienceViewHolder viewHolder = new ScienceAdapter.ScienceViewHolder(view);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(ScienceAdapter.ScienceViewHolder holder, int position) {
        holder.bindNews(mNews.get(position));
    }

    @Override
    public int getItemCount() {
        return mNews.size();
    }

    public class ScienceViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.newsImageView)
        ImageView mNewsImageView;
        @BindView(R.id.newsSource)
        TextView mNewsSource;
        @BindView(R.id.titleNews)
        TextView mTitleNews;


        private Context mContext;

        public ScienceViewHolder(View itemView) {
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