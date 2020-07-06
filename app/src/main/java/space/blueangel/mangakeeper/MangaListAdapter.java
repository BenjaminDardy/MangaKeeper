package space.blueangel.mangakeeper;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import space.blueangel.mangakeeper.entities.Manga;

public class MangaListAdapter extends RecyclerView.Adapter<MangaListAdapter.MangaViewHolder> {

    public static final String EXTRA_REPLY_MANGA_NAME = "REPLY_MANGA_NAME";
    public static final String EXTRA_REPLY_MANGA_URL = "REPLY_MANGA_URL";
    public static final String EXTRA_REPLY_MANGA_LAST_NUMBER = "REPLY_MANGA_LAST_NUMBER";
    public static final String EXTRA_REPLY_MANGA_MISSING_NUMBER = "REPLY_MANGA_MISSING_NUMBER";

    class MangaViewHolder extends RecyclerView.ViewHolder {
        private final TextView mangaItemView;

        private MangaViewHolder(View itemView) {
            super(itemView);
            mangaItemView = itemView.findViewById(R.id.textView);
        }
    }

    private final LayoutInflater mInflater;
    private Context context;
    private List<Manga> mMangas;

    public MangaListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public MangaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new MangaViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MangaViewHolder holder, int position) {
        if (mMangas != null) {
            Manga current = mMangas.get(position);
            holder.mangaItemView.setText(current.getName());
        } else {
            holder.mangaItemView.setText("No Manga");
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DisplayMangaActivity.class);
                intent.putExtra(EXTRA_REPLY_MANGA_NAME, mMangas.get(position).getName());
                intent.putExtra(EXTRA_REPLY_MANGA_URL, mMangas.get(position).getImageUrl());
                intent.putExtra(EXTRA_REPLY_MANGA_LAST_NUMBER, mMangas.get(position).getLastNumberOwned());
                intent.putExtra(EXTRA_REPLY_MANGA_MISSING_NUMBER, mMangas.get(position).getMissingNumbers());
                context.startActivity(intent);
            }
        });
    }

    void setMangas(List<Manga> mangas) {
        mMangas = mangas;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mMangas != null)
            return mMangas.size();
        else return 0;
    }
}
