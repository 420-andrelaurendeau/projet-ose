package com.sap.ose.projetose.service;

import com.sap.ose.projetose.models.File;
import com.sap.ose.projetose.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FileService {
    private final FileRepository fileRepository;

    File findById(Long id) {
        return fileRepository.findById(id).orElse(null);
    }

}
