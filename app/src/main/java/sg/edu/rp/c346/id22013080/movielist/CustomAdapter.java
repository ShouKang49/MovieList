package sg.edu.rp.c346.id22013080.movielist;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {

    Context parent_context;
    int layout_id;
    ArrayList<Movies> movieList;

    public CustomAdapter(Context context, int resource, ArrayList<Movies> object) {
        super(context, resource, object);

        parent_context = context;
        layout_id = resource;
        movieList = object;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) parent_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(layout_id, parent, false);

        TextView tvTitle = rowView.findViewById(R.id.displayTitle);
        TextView tvGenre = rowView.findViewById(R.id.displayGenre);
        TextView tvYear = rowView.findViewById(R.id.displayYear);
        ImageView ivRating = rowView.findViewById(R.id.displayRating);

        Movies currentMovie = movieList.get(position);

        tvTitle.setText(currentMovie.getTitle());
        tvGenre.setText(currentMovie.getGenre());
        tvYear.setText("" + currentMovie.getYear());

        if(currentMovie.getRating().equalsIgnoreCase("g")){
            ivRating.setImageResource(R.drawable.rating_g);
        }
        else if(currentMovie.getRating().equalsIgnoreCase("pg")){
            ivRating.setImageResource(R.drawable.rating_pg);
        }
        else if (currentMovie.getRating().equalsIgnoreCase("pg-13")) {
            ivRating.setImageResource(R.drawable.rating_pg13);
        }
        else if(currentMovie.getRating().equalsIgnoreCase("nc16")){
            ivRating.setImageResource(R.drawable.rating_nc16);
        }
        else if(currentMovie.getRating().equalsIgnoreCase("m18")){
            ivRating.setImageResource(R.drawable.rating_m18);
        }
        else if(currentMovie.getRating().equalsIgnoreCase("r21")){
            ivRating.setImageResource(R.drawable.rating_r21);
        }


        return rowView;
    }

}
