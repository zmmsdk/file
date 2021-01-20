package com.himalayan.sbup.dao;

import com.himalayan.sbup.entity.FileUpload;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

//定义实体OrderList和主键  Integer
public interface FileUploadDao extends JpaRepository<FileUpload, Integer>, Serializable {

}
