package sg.edu.rp.c346.id22013080.movielist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

public class movieDetails extends AppCompatActivity {

    ListView lv;
    ArrayList<Movies> moviesList;
    CustomAdapter adapter;
    Button back, btnFilter;
    Spinner spnFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        lv = findViewById(R.id.movieList);
        back = findViewById(R.id.buttonBack);
        //btnFilter = findViewById(R.id.buttonFilter);
        //spnFilter = findViewById(R.id.spinnerRatingFilter);

        Intent intentReceived = getIntent();
        moviesList = new ArrayList<Movies>();

        //ArrayAdapter arrayAdapter = new ArrayAdapter<>(movieDetails.this, android.R.layout.simple_list_item_1, moviesList);

        adapter = new CustomAdapter(movieDetails.this, R.layout.row, moviesList);
        lv.setAdapter(adapter);

        DBHelper db = new DBHelper(movieDetails.this);
        moviesList.clear();
        moviesList.addAll(db.getMovie());
        adapter.notifyDataSetChanged();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(movieDetails.this, MainActivity.class);
                startActivity(intent);
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Movies data = moviesList.get(position);
                Intent i = new Intent(movieDetails.this, maintainMovies.class);
                i.putExtra("data", data);
                startActivity(i);
            }
        });



    }
}