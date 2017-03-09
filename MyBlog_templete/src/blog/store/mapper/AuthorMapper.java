package blog.store.mapper;

import java.util.List;
import java.util.Map;

import blog.domain.Author;

public interface AuthorMapper {
	
	Author findAuthor(String id);

	List<Author> findAllAuthors();

	List<Author> findAuthorsByName(String name);

	void registAuthor(Author author);

	int updateAuthor(Author author);

	int deleteAuthor(String id);

	List<Author> findAuthorsByIds(List<String> ids);

	// id를 주면 id를 찾고, 이름을 주면 이름을 찾는다, 컨디션이 null이면
	List<Author> findAuthorByCondition(Map<String, String> conditionMap);
}
