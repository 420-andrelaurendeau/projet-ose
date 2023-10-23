package com.sap.ose.projetose.services;

import com.sap.ose.projetose.dtos.NewFileTransferDto;
import com.sap.ose.projetose.models.File;
import com.sap.ose.projetose.models.User;
import com.sap.ose.projetose.repositories.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FileService {
    private final UserService userService;
    private final FileRepository fileRepository;

    @Transactional
    public NewFileTransferDto newFile(NewFileTransferDto newFileTransferDto) {
        User uploader = userService.getUserById(newFileTransferDto.getUploaderId());
        File file = new File(newFileTransferDto.getContent(), newFileTransferDto.getFileName(), newFileTransferDto.getIsAccepted(), uploader);
        return new NewFileTransferDto(fileRepository.save(file));
    }

    @Transactional
    public File getFileById(Long id) {
        return fileRepository.findById(id).orElseThrow();
    }
}
