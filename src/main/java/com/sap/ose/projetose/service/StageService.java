package com.sap.ose.projetose.service;

import com.sap.ose.projetose.dto.StageDto;
import com.sap.ose.projetose.repository.StageRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StageService {

    private final StageRepository stageRepository;

    public StageService(StageRepository stageRepository) {
        this.stageRepository = stageRepository;
    }

    @Transactional
    public List<StageDto> getAllStage(){
        return null;
    }
}
