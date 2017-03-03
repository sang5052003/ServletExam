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
import yorizori.domain.User;
import yorizori.store.CookbookStore;

public class CookbookStoreLogic implements CookbookStore {

	private DataSource dataSource;
	
	public CookbookStoreLogic(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@Override
	public int create(Cookbook cookbook) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int generatedId = -1;
		
		try {
			conn = this.dataSource.getConnection();
			

			//내가 가져올 컬럼명
			String[] cols = {"book_id"};
			
			
			pstmt = conn.prepareStatement(
					"INSERT INTO cookbook(book_id, book_name, book_desc, author_id, author_name) VALUES(cookbook_seq.nextval, ?, ?, ?, ?)",
					cols
					);
			
			pstmt.setString(1, cookbook.getName());
			pstmt.setString(2,  cookbook.getDescription());
			pstmt.setString(3,  cookbook.getAuthor().getUserId());
			pstmt.setString(4, cookbook.getAuthor().getName());
			
			pstmt.executeUpdate();
			
			rs = pstmt.getGeneratedKeys();
			if(rs.next()){
				generatedId = (int)rs.getLong(1); //오라클은 getInt("book_id")로 못 가져와서..
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			JdbcUtils.closeQuietly(rs, pstmt, conn);
		}
		
		return generatedId;
	}

	@Override
	public List<Cookbook> retrieveAll() {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Cookbook> list = null;
		
		try {
			conn = this.dataSource.getConnection();
			
			pstmt = conn.prepareStatement(
					"select book_id, book_name, book_desc, author_id, author_name from cookbook"
					);
			
			rs = pstmt.executeQuery();
			
			list = new ArrayList<>();
			while(rs.next()){
				list.add(this.convertToDomain(rs));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			JdbcUtils.closeQuietly(rs, pstmt, conn);
		}
		
		return list;
	}

	@Override
	public Cookbook retrieve(int id) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Cookbook cookbook = null;
		
		try {
			conn = this.dataSource.getConnection();
			
			pstmt = conn.prepareStatement(
					"select book_id, book_name, book_desc, author_id, author_name from cookbook where book_id = ?"
					);
			
			pstmt.setInt(1, id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				cookbook = this.convertToDomain(rs);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.closeQuietly(rs, pstmt, conn);
		}
		
		return cookbook;
	}

	private Cookbook convertToDomain(ResultSet rs) throws SQLException {
		
		Cookbook cookbook = new Cookbook();
		cookbook.setId(rs.getInt("book_id"));
		cookbook.setName(rs.getString("book_name"));
		cookbook.setDescription(rs.getString("book_desc"));
		
		User author = new User();
		author.setUserId(rs.getString("author_id"));
		author.setName(rs.getString("author_name"));
		
		cookbook.setAuthor(author);
		
		return cookbook;
	}

}
