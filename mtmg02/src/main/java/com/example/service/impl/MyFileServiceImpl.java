package com.example.service.impl;

import com.example.filestorage.MyFile;
import com.example.repository.MyFileRepository;
import com.example.service.MyFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyFileServiceImpl implements MyFileService {
    @Autowired
    private MyFileRepository myFileRepository;
    @Override
    public MyFile save(MyFile myFile) {
        return myFileRepository.save(myFile);
    }

    @Override
    public MyFile finById(Integer id) {
        return myFileRepository.findById(id).orElse(null);
    }
}
