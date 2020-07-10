package space.blueangel.mangakeeper;

import androidx.appcompat.app.AppCompatActivity;
import space.blueangel.mangakeeper.entities.Manga;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

public class EditMangaActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY_EDIT_MANGA_ID = "REPLY_EDIT_MANGA_ID";
    public static final String EXTRA_REPLY_EDIT_MANGA_NAME = "REPLY_EDIT_MANGA_NAME";
    public static final String EXTRA_REPLY_EDIT_MANGA_URL = "REPLY_EDIT_MANGA_URL";
    public static final String EXTRA_REPLY_EDIT_MANGA_LAST_NUMBER = "REPLY_EDIT_MANGA_LAST_NUMBER";
    public static final String EXTRA_REPLY_EDIT_MANGA_MISSING_NUMBER = "REPLY_EDIT_MANGA_MISSING_NUMBER";

    private EditText mEditMangaNameView;
    private EditText mEditMangaUrlView;
    private EditText mEditMangaLastNumberView;
    private EditText mEditMangaMissingNumbersView;

    private Manga editManga;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_manga);
        mEditMangaNameView = findViewById(R.id.edit_manga_name);
        mEditMangaUrlView = findViewById(R.id.edit_manga_url);
        mEditMangaLastNumberView = findViewById(R.id.edit_manga_last_owned_number);
        mEditMangaMissingNumbersView = findViewById(R.id.edit_manga_missing_number);

        Intent intent = getIntent();
        editManga = new Manga(
                intent.getIntExtra(MangaListAdapter.EXTRA_MANGA_ID, 0),
                intent.getStringExtra(MangaListAdapter.EXTRA_MANGA_NAME),
                intent.getStringExtra(MangaListAdapter.EXTRA_MANGA_MISSING_NUMBER),
                intent.getStringExtra(MangaListAdapter.EXTRA_MANGA_LAST_NUMBER) != null ? Long.parseLong(intent.getStringExtra(MangaListAdapter.EXTRA_MANGA_LAST_NUMBER)) : 0,
                intent.getStringExtra(MangaListAdapter.EXTRA_MANGA_URL)
        );

        mEditMangaNameView.setText(editManga.getName());
        mEditMangaUrlView.setText(editManga.getImageUrl());
        mEditMangaLastNumberView.setText(editManga.getLastNumberOwned().toString());
        mEditMangaMissingNumbersView.setText(editManga.getMissingNumbers());

        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(view -> {
            Intent replyIntent = new Intent();
            if (TextUtils.isEmpty(mEditMangaNameView.getText()) || TextUtils.isEmpty(mEditMangaUrlView.getText())
                    || TextUtils.isEmpty(mEditMangaLastNumberView.getText())) {
                setResult(RESULT_CANCELED, replyIntent);
            } else {
                replyIntent.putExtra(EXTRA_REPLY_EDIT_MANGA_ID, editManga.getId());
                replyIntent.putExtra(EXTRA_REPLY_EDIT_MANGA_NAME, mEditMangaNameView.getText().toString());
                replyIntent.putExtra(EXTRA_REPLY_EDIT_MANGA_URL, mEditMangaUrlView.getText().toString());
                replyIntent.putExtra(EXTRA_REPLY_EDIT_MANGA_LAST_NUMBER, mEditMangaLastNumberView.getText().toString());
                replyIntent.putExtra(EXTRA_REPLY_EDIT_MANGA_MISSING_NUMBER, mEditMangaMissingNumbersView.getText().toString());
                setResult(RESULT_OK, replyIntent);
            }
            finish();
        });
    }
}