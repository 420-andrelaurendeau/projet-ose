package com.sap.ose.projetose.service;

import com.sap.ose.projetose.dto.FileDto;
import com.sap.ose.projetose.dto.ProgrammeDto;
import com.sap.ose.projetose.modeles.File;
import com.sap.ose.projetose.modeles.Programme;
import com.sap.ose.projetose.repository.FileEntityRepository;
import org.hibernate.annotations.NaturalId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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


    public void saveFile(File file) {
        fileEntityRepository.save(file);
    }

    public FileDto getFileById(Long id) {
        Optional<File> file = fileEntityRepository.findById(id);
        return file.map(value -> new FileDto(value.getId(),value.getContent(),value.getFileName(),value.isAccepted())).orElse(null);
    }
}
