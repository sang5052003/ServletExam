package yorizori.store.logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import yorizori.common.util.JdbcUtils;
import yorizori.domain.Cookbook;
import yorizori.domain.ImageFile;
import yorizori.domain.Procedure;
import yorizori.domain.Recipe;
import yorizori.domain.User;
import yorizori.store.RecipeStore;
import yorizori.store.StoreFactoryBuilder;

public class RecipeStoreLogic implements RecipeStore{

	//connection
	private DataSource dataSource;
	
	public RecipeStoreLogic(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@Override
	public int create(Recipe recipe) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int generatedId = -1;
		
		try {
			conn = this.dataSource.getConnection();
			
			//만들어지는 seq_nextVal을 받아오기 위해
			//쿼리문의 두번째 인자로 넘겨준다
			String[] cols = {"recipe_id"}; 
			
			pstmt = conn.prepareStatement(
					"INSERT INTO recipe "
					+ "(recipe_id, book_id, recipe_name, recipe_time, f_level, ingredients, "
					+ "img_cont_type, img_file_name, writer_id, writer_name) "
					+ "VALUES(recipe_seq.nextval, ?, ?, ?, ?, ?, "
					+ "?, ?, ?, ?)",
					cols
					);
			
			pstmt.setInt(1,  recipe.getCookbook().getId());
			pstmt.setString(2, recipe.getName());
			pstmt.setInt(3, recipe.getTime());
			pstmt.setInt(4, recipe.getLevel());
			pstmt.setString(5, recipe.getIngredients());
			
			ImageFile rImage = recipe.getRecipeImage();
			if(rImage != null){
				pstmt.setString(6, rImage.getContentType());
				pstmt.setString(7, rImage.getFileName());
			}
			else{ //이미지가 없는 경우
				pstmt.setString(6, "");
				pstmt.setString(7, "");
			}
			
			pstmt.setString(8, recipe.getWriter().getUserId());
			pstmt.setString(9, recipe.getWriter().getName());
			
			pstmt.executeUpdate();
			
			//위에서 설정한 String[] cols의 값들을 받아온다
			rs = pstmt.getGeneratedKeys();
			if(rs.next()){
				generatedId = (int)rs.getLong(1);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.closeQuietly(rs, pstmt, conn);
		}
		
		return generatedId;
	}

	@Override
	public List<Recipe> retrieveAll(int cookbookId) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Recipe> list = null;
		
		try {
			conn = this.dataSource.getConnection();
			
			pstmt = conn.prepareStatement(
					"SELECT recipe_id, book_id, recipe_name, recipe_time, f_level, ingredients, img_cont_type, "
					+ "img_file_name, writer_id, writer_name FROM recipe WHERE book_id = ?"
					);
			
			pstmt.setInt(1, cookbookId);
			
			rs = pstmt.executeQuery();
				
			list = new ArrayList<>();
			while(rs.next()){
				list.add(this.convertToDomain(rs));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.closeQuietly(rs, pstmt, conn);
		}
		
		return list;
	}

	@Override
	public Recipe retrieve(int id) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Recipe recipe = null;
		
		try {
			conn = this.dataSource.getConnection();
			
			pstmt = conn.prepareStatement(
					"SELECT recipe_id, book_id, recipe_name, recipe_time, f_level, ingredients, img_cont_type, "
					+ "img_file_name, writer_id, writer_name FROM recipe WHERE recipe_id = ?"
					);
			
			pstmt.setInt(1, id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				recipe = this.convertToDomain(rs);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JdbcUtils.closeQuietly(rs, pstmt, conn);
		}
		
		return recipe;
	}

	@Override
	public void createProcedure(int recipeId, Procedure procedure) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = this.dataSource.getConnection();
			
			pstmt = conn.prepareStatement(
					"INSERT INTO recipe_procedure (recipe_id, seq_num, procedure) "
					+ "VALUES(?, ?, ?)"
					);
			
			pstmt.setInt(1, recipeId);
			pstmt.setInt(2, procedure.getSequence());
			pstmt.setString(3, procedure.getProcedure());
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.closeQuietly(pstmt, conn);
		}
	}

	@Override
	public List<Procedure> retrieveProcedures(int recipeId) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Procedure> list = null;
		
		try {
			conn = this.dataSource.getConnection();
			
			pstmt = conn.prepareStatement(
					"SELECT seq_num, procedure FROM recipe_procedure where recipe_id = ? order by seq_num ASC"
					);
			
			pstmt.setInt(1, recipeId);
			
			rs = pstmt.executeQuery();
			
			list = new ArrayList<>();
			while(rs.next()){
				Procedure p = new Procedure(rs.getInt("seq_num"),
											rs.getString("procedure"));
				list.add(p);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.closeQuietly(rs, pstmt, conn);
		}
		
		return list;
	}
	
	private Recipe convertToDomain(ResultSet rs) throws SQLException{
		
		Recipe recipe = new Recipe();
		
		
//		int book_id = rs.getInt("book_id");
//		Cookbook cookbook = StoreFactoryBuilder.createJdbcStoreFactory().getCookbookStore().retrieve(book_id);;
//		recipe.setCookbook(cookbook);
		
		//여기서 아이디만 셋팅하고 나머지 cookbook의 값은 서비스에서 집어넣는다
		//CookbookServiceLogic -> findRecipeById()
		recipe.setCookbook(new Cookbook(rs.getInt("book_id")));
		
		recipe.setId(rs.getInt("recipe_id"));
		recipe.setName(rs.getString("recipe_name"));
		recipe.setTime(rs.getInt("recipe_time"));
		recipe.setLevel(rs.getInt("f_level"));
		recipe.setIngredients(rs.getString("ingredients"));
		
		String contentType = rs.getString("img_cont_type");
		String fileName = rs.getString("img_file_name");
		
		if(contentType != null && contentType.length() > 0 &&
				fileName != null && fileName.length() > 0){
			
			ImageFile rImage = new ImageFile();
			rImage.setContentType(contentType);
			rImage.setFileName(fileName);
			recipe.setRecipeImage(rImage);
		}
		
		//
		User writer = new User();
		writer.setUserId(rs.getString("writer_id"));
		writer.setName(rs.getString("writer_name"));
		recipe.setWriter(writer);
		
		return recipe;
	}

}
