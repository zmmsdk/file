package com.himalayan.sbup.entity;




import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
public class FileUpload {
    @Id
    private Integer id;
    private String uploadFile;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUploadFile() {
        return uploadFile;
    }

    public void setUploadFile(String uploadFile) {
        this.uploadFile = uploadFile;
    }

    public FileUpload() {
    }

    public FileUpload(Integer id, String uploadFile) {
        this.id = id;
        this.uploadFile = uploadFile;
    }
}
