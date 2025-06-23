package exercise;

import java.util.List;
import java.util.Optional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import exercise.model.Post;

@SpringBootApplication
@RestController
public class Application {
    // Хранилище добавленных постов
    private List<Post> posts = Data.getPosts();

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @GetMapping("/posts")
    public List<Post> pagingPosts(@RequestParam(defaultValue = "10") Long limit) {
        return posts.stream()
                .limit(limit)
                .toList();
    }

    @GetMapping("/posts/{id}")
    public Post getPost(@PathVariable String id) {
        return posts.stream()
                .filter(post -> id.equals(post.getId()))
                .findFirst()
                .orElseThrow();
    }

    @PostMapping("/posts")
    public Post createPost(@RequestBody Post post) {
        posts.add(post);
        return post;
    }

    @PutMapping("/posts/{id}")
    public Post update(@PathVariable String id, @RequestBody Post update) {
        var post = posts.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElseThrow();

        post.setId(update.getId());
        post.setBody(update.getBody());
        post.setTitle(update.getTitle());

        return post;
    }

    @DeleteMapping("/posts/{id}")
    public void delete(@PathVariable String id) {
        posts.removeIf(
                post -> post.getId().equals(id)
        );
    }
}
