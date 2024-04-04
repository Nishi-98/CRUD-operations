package dev.danvega.contentcalender.repository;


import dev.danvega.contentcalender.model.Content;
import dev.danvega.contentcalender.model.Status;
import dev.danvega.contentcalender.model.Type;
import jakarta.annotation.PostConstruct;

import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ContentCollectionRepository {
    private final List<Content> contentList = new ArrayList<>();

    public ContentCollectionRepository() {
    }

    public List<Content> findAll() {
        return contentList;
    }

    public Optional<Content> findByID(Integer id) {

        return contentList.stream().filter(c -> c.id().equals(id)).findFirst();


    }



    public void save(Content content) {
        contentList.removeIf(c->c.id().equals(content.id()));
        contentList.add(content);

    }

    @PostConstruct
    private void inti() {
        Content content = new Content(1,
                "my first blog post",
                "my first blog post",
                Status.IDEA,
                Type.ARTICLE,
                LocalDateTime.now(),
                null,
                "");
        contentList.add(content);


    }
    public boolean existsById(Integer id){
        return contentList.stream().filter(c -> c.id().equals(id)).count()==1;
    }

    public void delete(Integer id) {
        contentList.removeIf(c->c.id().equals(id));
    }
}


