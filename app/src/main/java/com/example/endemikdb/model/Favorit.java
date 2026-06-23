package com.example.endemikdb.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.annotation.NonNull;

@Entity(tableName = "favorit")
public class Favorit {

    @PrimaryKey
    @NonNull
    private String id;

    private String tipe;
    private String nama;
    private String namaLatin;
    private String asal;
    private String foto;
    private String status;

    public Favorit(@NonNull String id, String tipe, String nama, String namaLatin,
                   String asal, String foto, String status) {
        this.id = id;
        this.tipe = tipe;
        this.nama = nama;
        this.namaLatin = namaLatin;
        this.asal = asal;
        this.foto = foto;
        this.status = status;
    }

    @NonNull public String getId() { return id; }
    public String getTipe() { return tipe; }
    public String getNama() { return nama; }
    public String getNamaLatin() { return namaLatin; }
    public String getAsal() { return asal; }
    public String getFoto() { return foto; }
    public String getStatus() { return status; }
}