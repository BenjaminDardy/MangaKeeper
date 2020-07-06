package space.blueangel.mangakeeper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewMangaActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY_MANGA_NAME = "com.example.android.wordlistsql.REPLY";
    public static final String EXTRA_REPLY_MANGA_URL = "com.example.android.wordlistsql.REPLY";
    public static final String EXTRA_REPLY_MANGA_LAST_NUMBER = "com.example.android.wordlistsql.REPLY";
    public static final String EXTRA_REPLY_MANGA_MISSING_NUMBER = "com.example.android.wordlistsql.REPLY";

    private EditText mEditMangaNameView;
    private EditText mEditMangaUrlView;
    private EditText mEditMangaLastNumberView;
    private EditText mEditMangaMissingNumbersView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_manga);
        mEditMangaNameView = findViewById(R.id.edit_manga_name);
        mEditMangaUrlView = findViewById(R.id.edit_manga_url);
        mEditMangaLastNumberView = findViewById(R.id.edit_manga_last_owned_number);
        mEditMangaMissingNumbersView = findViewById(R.id.edit_manga_missing_number);

        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(view -> {
            Intent replyIntent = new Intent();
            if (TextUtils.isEmpty(mEditMangaNameView.getText()) || TextUtils.isEmpty(mEditMangaUrlView.getText())
             || TextUtils.isEmpty(mEditMangaLastNumberView.getText()) || TextUtils.isEmpty(mEditMangaMissingNumbersView.getText())) {
                setResult(RESULT_CANCELED, replyIntent);
            } else {
                String mangaName = mEditMangaNameView.getText().toString();
                replyIntent.putExtra(EXTRA_REPLY_MANGA_NAME, mangaName);
                String mangaUrl = mEditMangaUrlView.getText().toString();
                replyIntent.putExtra(EXTRA_REPLY_MANGA_URL, mangaUrl);
                String mangaLastNumber = mEditMangaNameView.getText().toString();
                replyIntent.putExtra(EXTRA_REPLY_MANGA_LAST_NUMBER, mangaLastNumber);
                String mangaMissingNumbers = mEditMangaNameView.getText().toString();
                replyIntent.putExtra(EXTRA_REPLY_MANGA_MISSING_NUMBER, mangaMissingNumbers);
                setResult(RESULT_OK, replyIntent);
            }
            finish();
        });
    }
}