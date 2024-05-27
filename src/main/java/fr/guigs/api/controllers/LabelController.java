package fr.guigs.api.controllers;

import fr.guigs.api.models.Label;
import fr.guigs.api.services.LabelService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/labels")
public class LabelController {

    private final LabelService labelService;

    public LabelController(LabelService labelService) {
        this.labelService = labelService;
    }

    @GetMapping
    public List<Label> getAllLabels() {
        return labelService.findAll();
    }

    @GetMapping("/{id}")
    public Label getLabelById(@PathVariable Long id) {
        return labelService.findById(id);
    }

    @PostMapping
    public Label createLabel(@RequestBody Label label) {
        return labelService.save(label);
    }

    @PutMapping("/{id}")
    public Label updateLabel(@PathVariable Long id, @RequestBody Label label) {
        return labelService.update(id, label);
    }

    @DeleteMapping("/{id}")
    public void deleteLabel(@PathVariable Long id) {
        labelService.delete(id);
    }
}