package com.sap.ose.projetose.service;

import com.sap.ose.projetose.dto.EtudiantDto;
import com.sap.ose.projetose.dto.FileDtoAll;
import com.sap.ose.projetose.modeles.File;
import com.sap.ose.projetose.repository.FileEntityRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FileService {

    private final FileEntityRepository fileEntityRepository;

    File findById(Long id) {
        return fileEntityRepository.findById(id).orElse(null);
    }


    public void saveFile(File file) {
        fileEntityRepository.save(file);
    }


    public FileDtoAll getFileById(Long id) {
        Optional<File> file = fileEntityRepository.findById(id);
        return file.map(value -> new FileDtoAll(value.getId(),value.getContent(),value.getFileName(),value.getIsAccepted(),new EtudiantDto(value.getEtudiant()))).orElse(null);
    }
    @Transactional
    public List<FileDtoAll> getAllStudentPendingCv() {
        Optional<List<File>> optionalFiles = fileEntityRepository.findAllStudentCvPending();
        List<File> files = optionalFiles.orElse(null);
        return files == null ? null : files.stream().map(file -> new FileDtoAll(file.getId(),file.getContent(),file.getFileName(),file.getIsAccepted(), new EtudiantDto(file.getEtudiant()))).toList();
    }
}
