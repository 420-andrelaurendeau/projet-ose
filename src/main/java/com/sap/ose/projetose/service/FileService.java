package com.sap.ose.projetose.service;

import com.sap.ose.projetose.dto.FileDto;
import com.sap.ose.projetose.dto.ProgrammeDto;
import com.sap.ose.projetose.modeles.File;
import com.sap.ose.projetose.modeles.Programme;
import com.sap.ose.projetose.repository.FileEntityRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.AssertTrue;
import org.hibernate.annotations.NaturalId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
    @Transactional
    public List<FileDto> getAllStudentPendingCv() {
        Optional<List<File>> optionalFiles = fileEntityRepository.findAllStudentCvPending();
        List<File> files = optionalFiles.orElse(null);
        return files == null ? null : files.stream().map(file -> new FileDto(file.getId(),file.getContent(),file.getFileName(),file.isAccepted())).toList();
    }
}
