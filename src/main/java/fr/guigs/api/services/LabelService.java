package fr.guigs.api.services;

import fr.guigs.api.models.Label;
import fr.guigs.api.repositories.LabelRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LabelService {
    private final LabelRepository labelRepository;

    public LabelService(LabelRepository labelRepository) {
        this.labelRepository = labelRepository;
    }

    public void delete(Long id) {
        labelRepository.deleteById(id);
    }

    public List<Label> findAll() {
        return labelRepository.findAll();
    }

    public Label findById(Long id) {
        return labelRepository.findById(id).orElse
                (null);
    }

    public Label save(Label label) {
        return labelRepository.save(label);
    }

    public Label update(Long id, Label newLabel) {
        return labelRepository.findById(id)
                .map(label -> {
                    label.setName(newLabel.getName());
                    return labelRepository.save(label);
                })
                .orElseGet(() -> {
                    newLabel.setId(id);
                    return labelRepository.save(newLabel);
                });
    }


}
