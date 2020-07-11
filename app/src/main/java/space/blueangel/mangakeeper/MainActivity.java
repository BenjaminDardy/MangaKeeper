package space.blueangel.mangakeeper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import space.blueangel.mangakeeper.entities.Manga;
import space.blueangel.mangakeeper.viewmodels.MangaViewModel;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private MangaViewModel mangaViewModel;
    public static final int NEW_MANGA_ACTIVITY_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final MangaListAdapter adapter = new MangaListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mangaViewModel = new ViewModelProvider(this).get(MangaViewModel.class);
        // Update the cached copy of the words in the adapter.
        mangaViewModel.getAllMangas().observe(this, adapter::setMangas);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, NewMangaActivity.class);
            startActivityForResult(intent, NEW_MANGA_ACTIVITY_REQUEST_CODE);
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_MANGA_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            Manga manga = new Manga(
                    data.getStringExtra(NewMangaActivity.EXTRA_REPLY_MANGA_NAME),
                    data.getStringExtra(NewMangaActivity.EXTRA_REPLY_MANGA_MISSING_NUMBER),
                    !data.getStringExtra(NewMangaActivity.EXTRA_REPLY_MANGA_LAST_NUMBER).equals("") ? Long.parseLong(data.getStringExtra(NewMangaActivity.EXTRA_REPLY_MANGA_LAST_NUMBER)) : 0,
                    data.getStringExtra(NewMangaActivity.EXTRA_REPLY_MANGA_URL)
            );
            mangaViewModel.insert(manga);
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show();
        }
    }
}