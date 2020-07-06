package space.blueangel.mangakeeper.repositories;

import android.app.Application;

import java.util.List;

import androidx.lifecycle.LiveData;
import space.blueangel.mangakeeper.daos.MangaDao;
import space.blueangel.mangakeeper.databases.MangaRoomDatabase;
import space.blueangel.mangakeeper.entities.Manga;

public class MangaRepository {
    private MangaDao mangaDao;
    private LiveData<List<Manga>> allMangas;

    public MangaRepository(Application application) {
        MangaRoomDatabase db = MangaRoomDatabase.getDatabase(application);
        mangaDao = db.mangaDao();
        allMangas = mangaDao.getAllMangas();
    }

    public LiveData<List<Manga>> getAllMangas() {
        return allMangas;
    }

    public void insert(final Manga manga) {
        MangaRoomDatabase.databaseWriteExecutor.execute(() -> {
            mangaDao.insert(manga);
        });
    }

    public void deleteAll() {
        MangaRoomDatabase.databaseWriteExecutor.execute(() -> {
            mangaDao.deleteAll();
        });
    }

}
