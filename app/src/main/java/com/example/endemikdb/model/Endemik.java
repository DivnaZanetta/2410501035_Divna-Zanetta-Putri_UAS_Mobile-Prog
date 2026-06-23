package com.example.endemikdb.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.annotation.NonNull;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "endemik")
public class Endemik {

    @PrimaryKey
    @NonNull
    @SerializedName("id")
    private String id;

    @SerializedName("tipe")
    private String tipe;

    @SerializedName("nama")
    private String nama;

    @SerializedName("nama_latin")
    private String namaLatin;

    @SerializedName("famili")
    private String famili;

    @SerializedName("genus")
    private String genus;

    @SerializedName("deskripsi")
    private String deskripsi;

    @SerializedName("asal")
    private String asal;

    @SerializedName("sebaran")
    private String sebaran;

    @SerializedName("foto")
    private String foto;

    @SerializedName("status")
    private String status;

    // Constructor
    public Endemik(@NonNull String id, String tipe, String nama, String namaLatin,
                   String famili, String genus, String deskripsi, String asal,
                   String sebaran, String foto, String status) {
        this.id = id;
        this.tipe = tipe;
        this.nama = nama;
        this.namaLatin = namaLatin;
        this.famili = famili;
        this.genus = genus;
        this.deskripsi = deskripsi;
        this.asal = asal;
        this.sebaran = sebaran;
        this.foto = foto;
        this.status = status;
    }

    // Getter
    @NonNull public String getId() { return id; }
    public String getTipe() { return tipe; }
    public String getNama() { return nama; }
    public String getNamaLatin() { return namaLatin; }
    public String getFamili() { return famili; }
    public String getGenus() { return genus; }
    public String getDeskripsi() { return deskripsi; }
    public String getAsal() { return asal; }
    public String getSebaran() { return sebaran; }
    public String getFoto() { return foto; }
    public String getStatus() { return status; }
}