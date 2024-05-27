package fr.guigs.api.services;

import fr.guigs.api.models.Tag;
import fr.guigs.api.repositories.TagRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {

    private final TagRepository tagRepository;

    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public List<Tag> findAll() {
        return tagRepository.findAll();
    }

    public Tag findById(Long id) {
        return tagRepository.findById(id).orElseThrow(() -> new RuntimeException("Tag not found"));
    }

    public Tag save(Tag tag) {
        return tagRepository.save(tag);
    }

    public Tag update(Long id, Tag newTag) {
        return tagRepository.findById(id)
                .map(tag -> {
                    tag.setName(newTag.getName());
                    return tagRepository.save(tag);
                })
                .orElseGet(() -> {
                    newTag.setId(id);
                    return tagRepository.save(newTag);
                });
    }

    public void delete(Long id) {
        tagRepository.deleteById(id);
    }
}