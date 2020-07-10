package space.blueangel.mangakeeper.entities;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "manga_table")
public class Manga {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "missing_numbers")
    private String missingNumbers;

    @ColumnInfo(name = "last_number_owned")
    private Long lastNumberOwned;

    @ColumnInfo(name = "image_url")
    private String imageUrl;

    public Manga(@NonNull String name, String missingNumbers, Long lastNumberOwned, String imageUrl) {
        this.name = name;
        this.missingNumbers = missingNumbers;
        this.lastNumberOwned = lastNumberOwned;
        this.imageUrl = imageUrl;
    }

    @Ignore
    public Manga(int id, @NonNull String name, String missingNumbers, Long lastNumberOwned, String imageUrl) {
        this.id = id;
        this.name = name;
        this.missingNumbers = missingNumbers;
        this.lastNumberOwned = lastNumberOwned;
        this.imageUrl = imageUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public String getMissingNumbers() {
        return missingNumbers;
    }

    public void setMissingNumbers(String missingNumbers) {
        this.missingNumbers = missingNumbers;
    }

    public Long getLastNumberOwned() {
        return lastNumberOwned;
    }

    public void setLastNumberOwned(Long lastNumberOwned) {
        this.lastNumberOwned = lastNumberOwned;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
