package blog.store.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import blog.domain.Blog;
import blog.domain.Comment;

public interface BlogMapper {

	//resultmap과 같은 기능
	@Results({
		@Result(property="id", column="blog_id", id=true),
		@Result(property="title", column="title"),
		@Result(property="author", column="author_id", one=@One(select="blog.store.mapper.AuthorMapper.findAuthor")),
		@Result(property="posts", column="blog_id", many=@Many(select="blog.store.mapper.PostMapper.findPostsByBlogId"))
	})
	@Select("SELECT blog_id, title, author_id FROM blog_tb WHERE blog_id = #{id} ORDER BY blog_id")
	Blog findBlog(int id);

	//mapper.xml에 존재
	Blog findBlogByAuthorId(String authorId);

	@Results({
		@Result(property="id", column="blog_id", id=true),
		@Result(property="title", column="title"),
		@Result(property="author", column="author_id", one=@One(select="blog.store.mapper.AuthorMapper.findAuthor")),
		@Result(property="posts", column="blog_id", many=@Many(select="blog.store.mapper.PostMapper.findPostsByBlogId"))
	})
	@Select("SELECT blog_id, title, author_id FROM blog_tb ORDER BY blog_id")
	List<Blog> findAllBlogs();

	//제목 Or 저자 아이디 검색
	List<Blog> findBlogsByTitle(@Param("title") String title, @Param("authorId") String authorId);

	void registBlog(Blog blog);

	int updateBlog(Blog blog);

	int deleteBlog(int id);
	
	
}
