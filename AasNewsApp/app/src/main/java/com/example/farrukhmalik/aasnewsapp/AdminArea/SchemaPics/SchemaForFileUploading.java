package com.example.farrukhmalik.aasnewsapp.AdminArea.SchemaPics;

/**
 * Created by Farrukh Malik on 25/09/2017.
 */

public class SchemaForFileUploading {

    String primaryKey;

    String title;

    String description;

    String downloadLink;

    int fileType;

    public SchemaForFileUploading(String primaryKey, String title, String description, String downloadLink, int fileType){

        this.primaryKey = primaryKey;
        this.title = title;
        this.description = description;
        this.downloadLink = downloadLink;
        this.fileType = fileType;

    }


    public SchemaForFileUploading(){

        //Default Costructer for read

    }


    public String getPrimaryKey() {
        return primaryKey;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getDownloadLink() {
        return downloadLink;
    }

    public int getFileType() {
        return fileType;
    }
}
