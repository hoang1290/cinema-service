package com.example.service;

import com.example.filestorage.MyFile;

public interface MyFileService {
    MyFile save(MyFile myFile);
    MyFile finById(Integer id);
}
