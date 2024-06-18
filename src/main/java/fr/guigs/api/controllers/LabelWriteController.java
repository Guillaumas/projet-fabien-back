package fr.guigs.api.controllers;

import fr.guigs.api.models.Label;
import fr.guigs.api.services.LabelService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/labels")
public class LabelWriteController {

    private final LabelService labelService;

    public LabelWriteController(LabelService labelService) {
        this.labelService = labelService;
    }

    @PostMapping("/labels")
    public Label createLabel(@RequestBody Label label) {
        return labelService.createLabel(label);
    }

    @PutMapping("/labels/{id}")
    public Label updateLabel(@PathVariable Long id, @RequestBody Label labelDetails) {
        return labelService.updateLabel(id, labelDetails);
    }

    @DeleteMapping("/labels/{id}")
    public ResponseEntity<?> deleteLabel(@PathVariable Long id) {
        labelService.deleteLabel(id);
        return ResponseEntity.ok().build();
    }
}