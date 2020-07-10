package space.blueangel.mangakeeper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import space.blueangel.mangakeeper.entities.Manga;
import space.blueangel.mangakeeper.viewmodels.MangaViewModel;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

public class DisplayMangaActivity extends AppCompatActivity {

    private MangaViewModel mangaViewModel;
    public static final int EDIT_MANGA_ACTIVITY_REQUEST_CODE = 2;

    private TextView mEditMangaNameView;
    private ImageView mEditMangaUrlView;
    private TextView mEditMangaLastNumberView;
    private TextView mEditMangaMissingNumbersView;

    private Manga displayedManga;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final MangaListAdapter adapter = new MangaListAdapter(this);
        mangaViewModel = new ViewModelProvider(this).get(MangaViewModel.class);
        // Update the cached copy of the words in the adapter.
        mangaViewModel.getAllMangas().observe(this, adapter::setMangas);

        setContentView(R.layout.activity_display_manga);
        Intent intent = getIntent();
        displayedManga = new Manga(
                intent.getIntExtra(MangaListAdapter.EXTRA_MANGA_ID, 0),
                intent.getStringExtra(MangaListAdapter.EXTRA_MANGA_NAME),
                intent.getStringExtra(MangaListAdapter.EXTRA_MANGA_MISSING_NUMBER),
                intent.getStringExtra(MangaListAdapter.EXTRA_MANGA_LAST_NUMBER) != null ? Long.parseLong(intent.getStringExtra(MangaListAdapter.EXTRA_MANGA_LAST_NUMBER)) : 0,
                intent.getStringExtra(MangaListAdapter.EXTRA_MANGA_URL)
        );
        mEditMangaNameView = findViewById(R.id.edit_manga_name);
        mEditMangaLastNumberView = findViewById(R.id.edit_manga_last_owned_number);
        mEditMangaMissingNumbersView = findViewById(R.id.edit_manga_missing_number);
        mEditMangaUrlView = findViewById(R.id.imageView);

        mEditMangaNameView.setText(displayedManga.getName());
        mEditMangaLastNumberView.setText(displayedManga.getLastNumberOwned().toString());
        mEditMangaMissingNumbersView.setText(displayedManga.getMissingNumbers());
        Picasso.get().load(displayedManga.getImageUrl()).into(mEditMangaUrlView);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            Intent editIntent = new Intent(DisplayMangaActivity.this, EditMangaActivity.class);
            editIntent.putExtra(MangaListAdapter.EXTRA_MANGA_ID, displayedManga.getId());
            editIntent.putExtra(MangaListAdapter.EXTRA_MANGA_NAME, displayedManga.getName());
            editIntent.putExtra(MangaListAdapter.EXTRA_MANGA_URL, displayedManga.getImageUrl());
            editIntent.putExtra(MangaListAdapter.EXTRA_MANGA_LAST_NUMBER, displayedManga.getLastNumberOwned().toString());
            editIntent.putExtra(MangaListAdapter.EXTRA_MANGA_MISSING_NUMBER, displayedManga.getMissingNumbers());
            startActivityForResult(editIntent, EDIT_MANGA_ACTIVITY_REQUEST_CODE);
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == EDIT_MANGA_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            Manga editManga = new Manga(
                    data.getIntExtra(EditMangaActivity.EXTRA_REPLY_EDIT_MANGA_ID, 0),
                    data.getStringExtra(EditMangaActivity.EXTRA_REPLY_EDIT_MANGA_NAME),
                    data.getStringExtra(EditMangaActivity.EXTRA_REPLY_EDIT_MANGA_MISSING_NUMBER),
                    !data.getStringExtra(EditMangaActivity.EXTRA_REPLY_EDIT_MANGA_LAST_NUMBER).equals("") ? Long.parseLong(data.getStringExtra(EditMangaActivity.EXTRA_REPLY_EDIT_MANGA_LAST_NUMBER)) : 0,
                    data.getStringExtra(EditMangaActivity.EXTRA_REPLY_EDIT_MANGA_URL)
            );
            mangaViewModel.updateManga(editManga);

            mEditMangaNameView.setText(editManga.getName());
            mEditMangaLastNumberView.setText(editManga.getLastNumberOwned().toString());
            mEditMangaMissingNumbersView.setText(editManga.getMissingNumbers());
            Picasso.get().load(editManga.getImageUrl()).into(mEditMangaUrlView);
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show();
        }
    }
}