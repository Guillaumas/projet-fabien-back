package fr.guigs.api.controllers;

import fr.guigs.api.models.Label;
import fr.guigs.api.services.LabelService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/labels")
public class LabelWriteController {

    private final LabelService labelService;

    public LabelWriteController(LabelService labelService) {
        this.labelService = labelService;
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