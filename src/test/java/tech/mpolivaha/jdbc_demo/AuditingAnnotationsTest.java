package tech.mpolivaha.jdbc_demo;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tech.mpolivaha.jdbc_demo.auditingannotations.Comment;
import tech.mpolivaha.jdbc_demo.auditingannotations.Post;
import tech.mpolivaha.jdbc_demo.auditingannotations.PostRepository;

@Slf4j
@SpringBootTest(classes = Application.class)
public class AuditingAnnotationsTest extends AbstractIntegrationTest {

  @Autowired
  private PostRepository postRepository;

  @Test
  void whenSavingParent_thenAuditingAnnotationsShouldApply() {
    Post order = new Post()
        .setComments(
            List.of(
                new Comment()
                    .setContent("Ha-Ha")
            )
        );

    Long id = postRepository.save(order).getId();

    Optional<Post> postInDb = postRepository.findById(id);
    assertThat(postInDb).isPresent().hasValueSatisfying(post -> {
      assertThat(post.getCreatedAt()).isNotNull();
    });
    assertThat(postInDb.map(Post::getComments)).hasValueSatisfying(comments -> {
      log.info("Comments : {}", comments);
      assertThat(comments.stream().allMatch(comment -> comment.getAddedAt() != null)).isTrue();
      assertThat(comments.stream().allMatch(comment -> comment.getAddedBy() != null)).isTrue();
    });
  }
}
