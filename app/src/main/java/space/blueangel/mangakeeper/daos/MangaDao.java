package space.blueangel.mangakeeper.daos;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import space.blueangel.mangakeeper.entities.Manga;

@Dao
public interface MangaDao {
    @Insert
    void insert(Manga manga);

    @Query("SELECT * from manga_table")
    LiveData<List<Manga>> getAllMangas();

    @Query("DELETE FROM manga_table")
    void deleteAll();
}
