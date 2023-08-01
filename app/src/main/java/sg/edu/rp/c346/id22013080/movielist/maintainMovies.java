package sg.edu.rp.c346.id22013080.movielist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class maintainMovies extends AppCompatActivity {

    Button btnUpdate, btnDelete, btnCancel;
    EditText newTitle, newGenre, newYear, id;
    Spinner newRating;
    Movies data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintain_movies);

        btnUpdate = findViewById(R.id.buttonUpdate);
        btnDelete = findViewById(R.id.buttonDelete);
        btnCancel = findViewById(R.id.buttonCancel);
        newTitle = findViewById(R.id.editTextNewTitle);
        newGenre = findViewById(R.id.editTextNewGenre);
        newYear = findViewById(R.id.editTextNewYear);
        id = findViewById(R.id.editTextId);
        newRating = findViewById(R.id.spinnerNewRating);

        Intent i = getIntent();
        data = (Movies) i.getSerializableExtra("data");

        id.setText("" + data.get_id());
        id.setEnabled(false);

        newTitle.setText(data.getTitle());
        newGenre.setText(data.getGenre());
        newYear.setText("" + data.getYear());

        if(data.getRating().equalsIgnoreCase("G")){
            newRating.setSelection(0);
        }
        else if(data.getRating().equalsIgnoreCase("PG")){
            newRating.setSelection(1);
        }
        else if (data.getRating().equalsIgnoreCase("PG-13")) {
            newRating.setSelection(2);
        }
        else if(data.getRating().equalsIgnoreCase("NC16")){
            newRating.setSelection(3);
        }
        else if(data.getRating().equalsIgnoreCase("M18")){
            newRating.setSelection(4);
        }
        else if(data.getRating().equalsIgnoreCase("R21")){
            newRating.setSelection(5);
        }

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DBHelper db = new DBHelper(maintainMovies.this);

                data.setTitle(newTitle.getText().toString());
                data.setGenre(newGenre.getText().toString());
                data.setYear(Integer.parseInt(newYear.getText().toString()));
                data.setRating((String) newRating.getSelectedItem());

                db.updateMovie(data);

                Toast.makeText(maintainMovies.this, "Movie updated", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(maintainMovies.this, movieDetails.class);
                startActivity(intent);
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper db = new DBHelper(maintainMovies.this);
                db.deleteMovie(data.get_id());

                Toast.makeText(maintainMovies.this, "Movie deleted", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(maintainMovies.this, movieDetails.class);
                startActivity(intent);
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(maintainMovies.this, movieDetails.class);
                startActivity(intent);
            }
        });
    }
}