package tech.mpolivaha.jdbc_demo.known.idgeneration;

import org.springframework.data.repository.CrudRepository;

public interface ArticleRepository extends CrudRepository<Article, Long> {

}
