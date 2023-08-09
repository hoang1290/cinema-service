package com.example.controller;

import com.example.filestorage.MyFile;
import com.example.service.MyFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MyFileController {
    @Autowired
    private MyFileService myFileService;

    @GetMapping("/get-img/{id}")
    private ResponseEntity<?> getImg(@PathVariable Integer id){
        MyFile myFile = myFileService.finById(id);
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(myFile.getContentType())).body(myFile.getContent());
    }
}
