package com.sap.ose.projetose.service;

import com.sap.ose.projetose.models.File;
import com.sap.ose.projetose.repository.FileEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FileService {

    private final FileEntityRepository fileEntityRepository;

    File findFileById(Long id) {
        return fileEntityRepository.findById(id).orElse(null);
    }


    public void saveFile(File file) {
        fileEntityRepository.save(file);
    }
}
