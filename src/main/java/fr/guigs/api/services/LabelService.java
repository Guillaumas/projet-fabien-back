package fr.guigs.api.services;

import fr.guigs.api.exceptions.ResourceNotFoundException;
import fr.guigs.api.models.Label;
import fr.guigs.api.repositories.LabelRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class LabelService {
    private final LabelRepository labelRepository;

    public LabelService(LabelRepository labelRepository) {
        this.labelRepository = labelRepository;
    }

    public Page<Label> getAllLabels(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return labelRepository.findAll(pageable);
    }

    public Label getLabelById(Long id) {
        return labelRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Label not found"));
    }

    public Label createLabel(Label label) {
        return labelRepository.save(label);
    }

    public Label updateLabel(Long id, Label labelDetails) {
        Label label = labelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Label not found"));

        label.setName(labelDetails.getName());

        return labelRepository.save(label);
    }

    public void deleteLabel(Long id) {
        Label label = labelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Label not found"));

        labelRepository.delete(label);
    }
}
