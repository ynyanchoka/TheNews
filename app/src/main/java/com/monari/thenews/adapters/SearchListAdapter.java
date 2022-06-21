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

public class SearchListAdapter extends RecyclerView.Adapter<SearchListAdapter.SearchViewHolder> {
    private List<Article> mArticles;
    private Context mContext;

    public SearchListAdapter(Context context, List<Article> articles) {
        mContext = context;
        mArticles = articles;
    }


    @Override
    public SearchListAdapter.SearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_list_item, parent, false);
        SearchViewHolder viewHolder = new SearchViewHolder(view);
        return viewHolder;


    }

    @Override
    public void onBindViewHolder(SearchListAdapter.SearchViewHolder holder, int position) {
        holder.bindNews(mArticles.get(position));



    }

    @Override
    public int getItemCount() {
        return mArticles.size();
    }

    //listner

    public class SearchViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.newsImageView)
        ImageView mNewsImageView;
        @BindView(R.id.titleNews)
        TextView mTitleNews;
        @BindView(R.id.newsSource)
        TextView mNewsSource;


        private Context mContext;


        public SearchViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
        }

        public void bindNews(Article article) {
            mTitleNews.setText(article.getTitle());
            mNewsSource.setText(article.getSource().getName());
            Picasso.get().load(article.getUrlToImage()).into(mNewsImageView);

        }

//        @Override
//        public void onClick(View v) {
//            int itemPosition = getLayoutPosition();// retrieve the position of the specific list item clicked
//            Intent intent = new Intent(mContext, MoviesDetailActivity.class);
//            intent.putExtra("position", itemPosition);
//            intent.putExtra("movies", Parcels.wrap(mMovies));
//            mContext.startActivity(intent);
//        }
    }
}