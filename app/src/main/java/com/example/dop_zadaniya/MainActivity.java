package com.example.dop_zadaniya;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Library library;
    private EditText editTitle, editAuthor, editGenre;
    private RecyclerView recyclerViewBooks;
    private BookAdapter bookAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        library = new Library();

        editTitle = findViewById(R.id.editTitle);
        editAuthor = findViewById(R.id.editAuthor);
        editGenre = findViewById(R.id.editGenre);

        recyclerViewBooks = findViewById(R.id.recyclerViewBooks);

        recyclerViewBooks.setLayoutManager(new LinearLayoutManager(this));
        bookAdapter = new BookAdapter(library.getBooks());
        recyclerViewBooks.setAdapter(bookAdapter);

        Button buttonAdd = findViewById(R.id.buttonAdd);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = editTitle.getText().toString();
                String author = editAuthor.getText().toString();
                String genre = editGenre.getText().toString();

                if (!title.isEmpty() && !author.isEmpty() && !genre.isEmpty()) {
                    library.addBook(new Book(title, author, genre));
                    updateOutput();
                }
            }
        });
    }

    private void updateOutput() {
        bookAdapter.notifyDataSetChanged();

        StringBuilder output = new StringBuilder("\nАвторы и жанры:\n");
        for (String genre : library.getBooksByGenre().keySet()) {
            output.append(genre).append(": ")
                    .append(library.getBooksByGenre().get(genre))
                    .append("\n");
        }


    }
}

