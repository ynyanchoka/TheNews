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

public class BusinessListAdapter extends RecyclerView.Adapter<BusinessListAdapter.BusinessViewHolder>{

    private List<Article> mNews;
    private Context mContext;

    public BusinessListAdapter(Context context, List<Article> articles) {
        mContext = context;
        mNews = articles;
    }


    @Override
    public BusinessListAdapter.BusinessViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.business_list_item, parent, false);
        BusinessListAdapter.BusinessViewHolder viewHolder = new BusinessListAdapter.BusinessViewHolder(view);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(BusinessListAdapter.BusinessViewHolder holder, int position) {
        holder.bindNews(mNews.get(position));
    }

    @Override
    public int getItemCount() {
        return mNews.size();
    }

    public class BusinessViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.newsImageView)
        ImageView mNewsImageView;
        @BindView(R.id.newsSource)
        TextView mNewsSource;
        @BindView(R.id.titleNews)
        TextView mTitleNews;


        private Context mContext;

        public BusinessViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
        }

        public void bindNews(Article article) {
            mTitleNews.setText(article.getTitle());
            mNewsSource.setText(article.getSource().getName());
            Picasso.get().load(article.getUrlToImage()).into(mNewsImageView);

//            mNewsImageView.setText("Rating: " + restaurant.getRating() + "/5");
        }
    }
}
