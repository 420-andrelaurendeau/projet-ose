package com.sap.ose.projetose.service;

import com.sap.ose.projetose.modeles.File;
import com.sap.ose.projetose.repository.FileEntityRepository;
import org.hibernate.annotations.NaturalId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileService {

    private FileEntityRepository fileEntityRepository;

    @Autowired
    public FileService(FileEntityRepository fileEntityRepository) {
        this.fileEntityRepository = fileEntityRepository;
    }

    File findById(Long id) {
        return fileEntityRepository.findById(id).orElse(null);
    }

}
