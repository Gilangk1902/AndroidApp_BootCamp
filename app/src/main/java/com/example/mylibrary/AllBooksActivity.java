package com.example.mylibrary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class AllBooksActivity extends AppCompatActivity {

    private RecyclerView booksRecView;
    private BooksRecViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_books);

        adapter = new BooksRecViewAdapter(this);
        booksRecView = findViewById(R.id.booksRecyView);

        booksRecView.setAdapter(adapter);
        booksRecView.setLayoutManager(new LinearLayoutManager(this));


        //books.add(new Book(1, "1Q84", "Haruki Murakami", 1350, "https://publishingperspectives.com/wp-content/uploads/2014/09/cover-1Q84.jpg", "A work of maddening brilliance", "Long Desc"));
        adapter.setBooks(Utils.getInstance().getAllBooks());
    }
}