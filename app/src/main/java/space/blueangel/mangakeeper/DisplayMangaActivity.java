package space.blueangel.mangakeeper;

import androidx.appcompat.app.AppCompatActivity;
import space.blueangel.mangakeeper.entities.Manga;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class DisplayMangaActivity extends AppCompatActivity {

    private TextView mEditMangaNameView;
    private ImageView mEditMangaUrlView;
    private TextView mEditMangaLastNumberView;
    private TextView mEditMangaMissingNumbersView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_manga);
        Intent intent = getIntent();
        Manga manga = new Manga(
                intent.getStringExtra(MangaListAdapter.EXTRA_REPLY_MANGA_NAME),
                intent.getStringExtra(MangaListAdapter.EXTRA_REPLY_MANGA_MISSING_NUMBER),
                intent.getStringExtra(MangaListAdapter.EXTRA_REPLY_MANGA_LAST_NUMBER) != null ? Long.parseLong(intent.getStringExtra(MangaListAdapter.EXTRA_REPLY_MANGA_LAST_NUMBER)) : 0,
                intent.getStringExtra(MangaListAdapter.EXTRA_REPLY_MANGA_URL)
        );
        mEditMangaNameView = findViewById(R.id.edit_manga_name);
        mEditMangaLastNumberView = findViewById(R.id.edit_manga_last_owned_number);
        mEditMangaMissingNumbersView = findViewById(R.id.edit_manga_missing_number);
        mEditMangaUrlView = findViewById(R.id.imageView);

        mEditMangaNameView.setText(manga.getName());
        mEditMangaLastNumberView.setText(manga.getLastNumberOwned().toString());
        mEditMangaMissingNumbersView.setText(manga.getMissingNumbers());

        Picasso.get().load(manga.getImageUrl()).into(mEditMangaUrlView);
    }
}