package com.ptrivedi.moviedbretrofit;

import android.app.LauncherActivity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.ptrivedi.moviedbretrofit.MovieList;
import java.util.List;

/**
 * Created by pooja on 7/7/2017.
 */
public class RvAdapter extends RecyclerView.Adapter<RvAdapter.RvHolder>  {


    private List<MovieList> listData;
    private LayoutInflater inflater;
    private Context mContext;
    private movieinfo info;

    public RvAdapter(List<MovieList> listData, Context c){
        this.inflater = LayoutInflater.from(c);
        mContext =c;
        this.listData = listData;
    }


    @Override
    public RvAdapter.RvHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.movie_list,parent,false);
        return new RvHolder(view);

    }

    @Override
    public void onBindViewHolder(RvAdapter.RvHolder holder, int position) {

        MovieList movieList = listData.get(position);
        holder.name.setText(movieList.getMovieName());
        String path = movieList.getMovieImagePath();
        String imageUrl = String.format("http://image.tmdb.org/t/p/w185"+ path);
        Picasso.with(mContext).load(imageUrl).resize(185,280).into(holder.image);

    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    class RvHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView name;
        private ImageView image;
        private final Context context;
        // private View container;

        public RvHolder(View itemView) {
            super(itemView);

            context = itemView.getContext();

            name = (TextView)itemView.findViewById(R.id.movie_name);
            image = (ImageView)itemView.findViewById(R.id.movie_image);

            itemView.setOnClickListener(this);
            //container = (View)itemView.findViewById(R.id.cont_item_root);
        }

        @Override
        public void onClick(View view) {

            Intent intent = new Intent(context,movieinfo.class);
            intent.putExtra(movieinfo.NAME_KEY,listData.get(getAdapterPosition()).getMovieName());
            intent.putExtra(movieinfo.REVIVE_KEY,listData.get(getAdapterPosition()).getMovieReview());
            intent.putExtra(movieinfo.IMAGE_KEY,listData.get(getAdapterPosition()).getMovieImagePath());

            context.startActivity(intent);
        }
    }

}
