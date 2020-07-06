package space.blueangel.mangakeeper.viewmodels;

import android.app.Application;

import java.util.List;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import space.blueangel.mangakeeper.entities.Manga;
import space.blueangel.mangakeeper.repositories.MangaRepository;

public class MangaViewModel extends AndroidViewModel {
    private MangaRepository mangaRepository;

    private LiveData<List<Manga>> allMangas;

    public MangaViewModel(Application application) {
        super(application);
        mangaRepository = new MangaRepository(application);
        allMangas = mangaRepository.getAllMangas();
    }

    public LiveData<List<Manga>> getAllMangas() {
        return allMangas;
    }

    public void insert(Manga manga) {
        mangaRepository.insert(manga);
    }

    public void deleteAll() {
        mangaRepository.deleteAll();
    }
}
