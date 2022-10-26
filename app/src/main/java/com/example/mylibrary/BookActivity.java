package com.example.mylibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class BookActivity extends AppCompatActivity {
    private TextView txtBookName, txtAuthor, txtPages, txtDescription;
    private Button btnAddToWantToRead, btnAddToAlreadyRead, btnAddToCurrentlyReading, btnAddToFavorite;
    private ImageView bookImage;
    public static final String BOOK_ID_KEY = "bookId";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        initViews();



        Intent intent = getIntent();
        if(null!=intent){
            int bookId = intent.getIntExtra(BOOK_ID_KEY, -1);
            if(bookId != -1){
                Book incomingBook = Utils.getInstance().getBookById(bookId);
                if(null!= incomingBook){
                    setData(incomingBook);

                    handleAlreadyRead(incomingBook);
                    handleWantToReadBooks(incomingBook);
                    handleCurrentlyReadingBooks(incomingBook);
                    handleFavoriteBooks(incomingBook);
                }
            }
        }
    }

    private void handleCurrentlyReadingBooks(Book book){

    }

    private void handleFavoriteBooks(Book book){

    }

    private void handleWantToReadBooks(final Book book){
        ArrayList<Book> wantToReadBooks = Utils.getInstance().getWantToReadBooks();

        boolean existInWantToReadBook = false;

        for(Book b: wantToReadBooks){
            if(b.getId() == book.getId()){
                existInWantToReadBook = true;
            }
        }

        if(existInWantToReadBook){
            btnAddToWantToRead.setEnabled(false);
        }
        else{
            btnAddToWantToRead.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(Utils.getInstance().addToWantToRead(book)){
                        Toast.makeText(BookActivity.this, "Book Added", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(BookActivity.this, AlreadyReadBookActivity.class);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(BookActivity.this, "Something wrong happened, try again", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    //ENABLE AND DISABLE BUTTON< ADD THE BOOK TO ALREADY READ BOOK ARRAYLIST
    private void handleAlreadyRead(Book book){
        ArrayList<Book> alreadyReadBooks = Utils.getInstance().getAlreadyReadBooks();

        boolean existInAlreadyReadBook = false;

        for(Book b: alreadyReadBooks){
            if(b.getId() == book.getId()){
                existInAlreadyReadBook = true;
            }
        }

        if(existInAlreadyReadBook){
            btnAddToAlreadyRead.setEnabled(false);
        }
        else{
            btnAddToAlreadyRead.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(Utils.getInstance().addToAlreadyRead(book)){
                        Toast.makeText(BookActivity.this, "Book Added", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(BookActivity.this, AlreadyReadBookActivity.class);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(BookActivity.this, "Something wrong happened, try again", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void setData(Book book){
        txtBookName.setText(book.getName());
        txtAuthor.setText(book.getAuthor());
        txtPages.setText(String.valueOf(book.getPages()));
        txtDescription.setText(book.getLongDesc());
        Glide.with(this).asBitmap().load(book.getImageUrl()).into(bookImage);
    }

    private void initViews(){
        txtAuthor = findViewById(R.id.txtAuthorName);
        txtBookName = findViewById(R.id.txtBooksName);
        txtPages = findViewById(R.id.txtPages);
        txtDescription = findViewById(R.id.txtDescription);

        btnAddToAlreadyRead = findViewById(R.id.btnAddAlreadyRead);
        btnAddToCurrentlyReading = findViewById(R.id.btnAddCurReading);
        btnAddToFavorite = findViewById(R.id.btnAddToFav);
        btnAddToWantToRead = findViewById(R.id.btnAddWantToRead);

        bookImage = findViewById(R.id.bookImg);
    }
}