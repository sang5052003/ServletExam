package blog.store.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import blog.domain.Comment;
import blog.domain.Post;
import blog.domain.Tag;

public interface PostMapper {

	Post findPost(int id);

	List<Post> findAllPost();

	@Results({
		@Result(property="id", column="id"),
		@Result(property="subject", column="subject"),
		@Result(property="contents", column="contents"),
		@Result(property="author", column="author_id", one=@One(select="AuthorStore.findAuthor")),
		@Result(property="blog", column="blog_id", one=@One(select="BlogStore.findBlog")),
		@Result(property="comments", column="id", many=@Many(select="blog.store.mapper.PostMapper.findCommentsByPostId")),
		@Result(property="tags", column="id", many=@Many(select="blog.store.mapper.PostMapper.findTagsByPostId"))
	})
	@Select("SELECT id, subject, contents FROM post_tb WHERE blog_id = #{id} ORDER BY id")
	List<Post> findPostsByBlogId(int id);

	List<Post> findPostsByAuthorName(String authorName);

	List<Post> findPostsByBlogTitle(String blogTitle);

	List<Post> findPostsBySubject(String subject);

	List<Post> findPostsByContents(String contents);

	int registPost(Post post);

	int updatePost(Post post);

	int deletePost(int id);
	
	//List<Comment>를 위해 추가
	//@Select("SELECT id, name, comment_contents, post_id FROM comment_tb WHERE post_id = #{id}")
	List<Comment> findCommentsByPostId(int id);
	List<Tag> findTagsByPostId(int id);
	
}
