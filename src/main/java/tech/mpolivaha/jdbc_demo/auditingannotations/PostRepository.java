package tech.mpolivaha.jdbc_demo.auditingannotations;

import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Long> {

}
