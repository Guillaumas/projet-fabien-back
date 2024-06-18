package fr.guigs.api.controllers;


import fr.guigs.api.models.Label;
import fr.guigs.api.services.LabelService;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/labels")
public class LabelReadController {

    private final LabelService labelService;

    public LabelReadController(LabelService labelService) {
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
}
