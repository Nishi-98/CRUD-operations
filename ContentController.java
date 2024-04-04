package dev.danvega.contentcalender.controller;


import dev.danvega.contentcalender.model.Content;

import dev.danvega.contentcalender.repository.ContentCollectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("/api/content")
public class ContentController {
    private final ContentCollectionRepository repository;

public ContentController(ContentCollectionRepository repository){
    this.repository= repository;;
    }
    @GetMapping("")
    public List<Content> findAll(){
    return repository.findAll();
    }
    @GetMapping("/{id}")
public Content findBYId(@PathVariable Integer id ){
    return repository.findByID(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Content not Found!"));
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public void create(@RequestBody Content content){
    repository.save(content);
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
   @PutMapping("/{id}")
   public void update(@RequestBody Content content,@PathVariable Integer Id){
if(!repository.existsById(content.id() )){
    throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Content not Found!");

}
       repository.save(content);
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(Integer id){
    repository.delete(id);
    }
}

