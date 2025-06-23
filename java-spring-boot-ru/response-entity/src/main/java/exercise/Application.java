package exercise;

import java.util.List;
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
import org.springframework.http.ResponseEntity;

import exercise.model.Post;
import lombok.Setter;

@SpringBootApplication
@RestController
public class Application {
    // Хранилище добавленных постов
    @Setter
    private static  List<Post> posts = Data.getPosts();

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @GetMapping("/posts")
    public ResponseEntity<List<Post>> allPosts(@RequestParam(defaultValue = "10") Integer limit) {
        final int count = posts.size();
        return ResponseEntity.ok()
                .header("X-Total-Count", Integer.toString(count))
                .body(posts.stream().limit(limit).toList());
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<Post> post(@PathVariable String id) {
        final var post = posts.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst().orElse(null);

        if (post == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(post);
    }

    @PostMapping("/posts")
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        posts.add(post);
        return ResponseEntity.status(201).body(post);
    }

    @PutMapping("/posts/{id}")
    public ResponseEntity<Post> update(@PathVariable String id, @RequestBody Post update) {
        var post = posts.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (post == null) {
            return ResponseEntity.notFound().build();
        }

        post.setId(update.getId());
        post.setBody(update.getBody());
        post.setTitle(update.getTitle());
        return ResponseEntity.ok(post);
    }

    @DeleteMapping("/posts/{id}")
    public void destroy(@PathVariable String id) {
        posts.removeIf(p -> p.getId().equals(id));
    }
}
