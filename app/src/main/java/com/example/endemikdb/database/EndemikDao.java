package com.example.endemikdb.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.example.endemikdb.model.Endemik;
import com.example.endemikdb.model.Favorit;
import java.util.List;

@Dao
public interface EndemikDao {

    // ── Tabel endemik ──────────────────────────────────

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Endemik> list);

    @Query("SELECT COUNT(*) FROM endemik")
    int count();

    @Query("SELECT * FROM endemik WHERE tipe = 'Hewan'")
    LiveData<List<Endemik>> getHewan();

    @Query("SELECT * FROM endemik WHERE tipe = 'Tumbuhan'")
    LiveData<List<Endemik>> getTumbuhan();

    @Query("SELECT * FROM endemik WHERE id = :id")
    LiveData<Endemik> getById(String id);

    @Query("SELECT * FROM endemik WHERE nama LIKE '%' || :keyword || '%' " +
            "OR tipe LIKE '%' || :keyword || '%' " +
            "OR asal LIKE '%' || :keyword || '%'")
    LiveData<List<Endemik>> search(String keyword);

    // ── Tabel favorit ──────────────────────────────────

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertFavorit(Favorit favorit);

    @Delete
    void deleteFavorit(Favorit favorit);

    @Query("SELECT * FROM favorit")
    LiveData<List<Favorit>> getAllFavorit();

    @Query("SELECT COUNT(*) FROM favorit WHERE id = :id")
    int isFavorit(String id);
}