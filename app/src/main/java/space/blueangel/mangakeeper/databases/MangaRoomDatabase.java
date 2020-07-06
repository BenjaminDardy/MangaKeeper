package space.blueangel.mangakeeper.databases;

import android.content.Context;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import space.blueangel.mangakeeper.daos.MangaDao;
import space.blueangel.mangakeeper.entities.Manga;

@Database(entities = {Manga.class}, version = 1, exportSchema = false)
public abstract class MangaRoomDatabase extends RoomDatabase {

    public abstract MangaDao mangaDao();

    private static volatile MangaRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static MangaRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (MangaRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room
                            .databaseBuilder(context.getApplicationContext(), MangaRoomDatabase.class, "manga_database")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
