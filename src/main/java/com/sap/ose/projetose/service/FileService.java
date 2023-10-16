package com.sap.ose.projetose.service;

import com.sap.ose.projetose.dto.FileTransferDto;
import com.sap.ose.projetose.models.File;
import com.sap.ose.projetose.models.User;
import com.sap.ose.projetose.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FileService {
    private final UserService userService;
    private final FileRepository fileRepository;

    @Transactional
    public FileTransferDto newFile(FileTransferDto fileTransferDto) {
        User uploader = userService.getUserById(fileTransferDto.getUploaderId());
        File file = new File(fileTransferDto.getContent(), fileTransferDto.getFileName(), fileTransferDto.isAccepted(), uploader);
        return fileRepository.save(file);
    }
}
