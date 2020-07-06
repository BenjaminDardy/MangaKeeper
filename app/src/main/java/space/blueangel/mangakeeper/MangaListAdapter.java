package space.blueangel.mangakeeper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import space.blueangel.mangakeeper.entities.Manga;

public class MangaListAdapter extends RecyclerView.Adapter<MangaListAdapter.MangaViewHolder> {

    class MangaViewHolder extends RecyclerView.ViewHolder {
        private final TextView mangaItemView;

        private MangaViewHolder(View itemView) {
            super(itemView);
            mangaItemView = itemView.findViewById(R.id.textView);
        }
    }

    private final LayoutInflater mInflater;
    private List<Manga> mMangas;

    public MangaListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
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
