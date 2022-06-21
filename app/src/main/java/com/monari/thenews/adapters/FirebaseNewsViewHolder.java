package com.monari.thenews.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.monari.thenews.Constants;
import com.monari.thenews.R;
import com.monari.thenews.models.Article;
import com.monari.thenews.ui.NewsDetailActivity;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

public class FirebaseNewsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener   {

    View mView;
    Context mContext;

    public FirebaseNewsViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
        itemView.setOnClickListener(this);
    }

    public void bindNews(Article article) {
        ImageView newsImageView = (ImageView) mView.findViewById(R.id.newsImageView);
        TextView sourceTextView = (TextView) mView.findViewById(R.id.newsSource);
        TextView titleTextView = (TextView) mView.findViewById(R.id.titleNews);


        Picasso.get().load(article.getUrlToImage()).into(newsImageView);


        sourceTextView.setText(article.getSource().getName());
        titleTextView.setText(article.getTitle());
        titleTextView.setText(article.getTitle());
        titleTextView.setText(article.getTitle());

    }

    @Override
    public void onClick(View view) {
        final ArrayList<Article> articles = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_ARTICLES);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    articles.add(snapshot.getValue(Article.class));
                }

                int itemPosition = getLayoutPosition();

                Intent intent = new Intent(mContext, NewsDetailActivity.class);
                intent.putExtra("position", itemPosition + "");
                intent.putExtra("articles", Parcels.wrap(articles));

                mContext.startActivity(intent);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }


}
