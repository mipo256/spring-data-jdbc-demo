package tech.mpolivaha.jdbc_demo.known;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tech.mpolivaha.jdbc_demo.AbstractIntegrationTest;
import tech.mpolivaha.jdbc_demo.Application;
import tech.mpolivaha.jdbc_demo.known.auditingannotations.Comment;
import tech.mpolivaha.jdbc_demo.known.auditingannotations.Post;
import tech.mpolivaha.jdbc_demo.known.auditingannotations.PostRepository;

@SpringBootTest(classes = Application.class)
public class AuditingAnnotationsTest_3 extends AbstractIntegrationTest {

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
      assertThat(comments.stream().allMatch(comment -> comment.getAddedAt() != null)).isTrue();
      assertThat(comments.stream().allMatch(comment -> comment.getAddedBy() != null)).isTrue();
    });
  }
}
