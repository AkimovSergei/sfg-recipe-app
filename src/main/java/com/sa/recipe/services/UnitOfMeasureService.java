package com.sa.recipe.services;

import com.sa.recipe.domain.UnitOfMeasure;
import com.sa.recipe.repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UnitOfMeasureService {
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public UnitOfMeasureService(UnitOfMeasureRepository unitOfMeasureRepository) {
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    public UnitOfMeasure findByDescriptionOrCreate(String description) {
        Optional<UnitOfMeasure> category = unitOfMeasureRepository.findByDescription("Mexican");

        return category.orElseGet(() -> unitOfMeasureRepository.save(new UnitOfMeasure(description)));
    }
}
