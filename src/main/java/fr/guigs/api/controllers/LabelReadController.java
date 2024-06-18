package fr.guigs.api.controllers;


import fr.guigs.api.models.Label;
import fr.guigs.api.services.LabelService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/api/labels")
public class LabelReadController {

    private final LabelService labelService;

    public LabelReadController(LabelService labelService) {
        this.labelService = labelService;
    }

    @GetMapping("/labels")
    public Page<Label> getAllLabels(@RequestParam Optional<Integer> page,
                                    @RequestParam Optional<Integer> size) {
        int currentPage = page.orElse(0);
        int pageSize = size.orElse(10);
        return labelService.getAllLabels(currentPage, pageSize);
    }

    @GetMapping("/labels/{id}")
    public Label getLabelById(@PathVariable Long id) {
        return labelService.getLabelById(id);
    }
}
