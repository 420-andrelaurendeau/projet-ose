package com.sap.ose.projetose.service;

import com.sap.ose.projetose.dto.FileDto;
import com.sap.ose.projetose.modeles.File;
import com.sap.ose.projetose.modeles.Utilisateur;
import com.sap.ose.projetose.repository.FileEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FileService {
    private final UtilisateurService utilisateurService;
    private final FileEntityRepository fileEntityRepository;

    @Transactional
    public FileDto newFile(FileDto fileDto) {
        Utilisateur uploader = utilisateurService.getUserById(fileDto.getUploaderId());
        File file = new File(fileDto.getContent(), fileDto.getFileName(), fileDto.getIsAccepted(), uploader);
        return new FileDto(fileEntityRepository.save(file));
    }

    @Transactional
    public File getFileById(Long id) {
        return fileEntityRepository.findById(id).orElseThrow();
    }
}
