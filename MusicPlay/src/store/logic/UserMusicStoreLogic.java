package store.logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import domain.Music;
import store.UserMusicStore;
import store.factory.ConnectionFactory;
import store.utils.JdbcUtils;

public class UserMusicStoreLogic implements UserMusicStore {

	private ConnectionFactory factory;
	
	public UserMusicStoreLogic() {
		this.factory = ConnectionFactory.getInstance();
	}
	
	@Override
	public boolean create(String userId, int musicId) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		int count = 0;
		
		try {
			conn = this.factory.createConnection();
			
			pstmt = conn.prepareStatement(
					"insert into user_music_tb(music_id, user_id) values(?, ?)"
					);
			
			pstmt.setInt(1, musicId);
			pstmt.setString(2, userId);
			
			
			
			//
			count = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.close(conn, pstmt);
		}
		
		return count > 0;
	}

	@Override
	public boolean delete(String userId, int musicId) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		int count = 0;
		
		try {
			conn = this.factory.createConnection();
			
			pstmt = conn.prepareStatement(
					"delete from user_music_tb where music_id = ? and user_id = ?"
					);
			
			pstmt.setInt(1, musicId);
			pstmt.setString(2, userId);
			
			//
			count = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.close(conn, pstmt);
		}
		
		return count > 0;
	}

	@Override
	public boolean existUserMusic(String userId, int musicId) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = this.factory.createConnection();
			
			pstmt = conn.prepareStatement(
					"select music_id, user_id from user_music_tb where music_id = ? and user_id = ?"
					);
			
			pstmt.setInt(1, musicId);
			pstmt.setString(2, userId);
			
			//
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				return true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.close(conn, pstmt, rs);
		}
		
		return false;
	}

	@Override
	public List<Music> readMusicsByUser(String userId) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Music> list = null;
		Music m = null;
		
		try {
			conn = this.factory.createConnection();
			
			pstmt = conn.prepareStatement(
					"select M.ID, M.NAME, M.ARTIST_NAME, M.IMAGE, M.ALBUM_TITLE, M.AGENT_NAME"
					+ " from user_music_tb UM, music_tb M" 
   					+ " where M.ID = UM.MUSIC_ID and UM.USER_ID = ?"
					);
			
			pstmt.setString(1, userId);
			
			rs = pstmt.executeQuery();
			
			list = new ArrayList<>();
			while(rs.next()){
				
				m = new Music();
				
				m.setId(rs.getInt("id"));
				m.setName(rs.getString("name"));
				m.setArtist(rs.getString("artist_name"));
				m.setImage(rs.getString("image"));
				m.setAlbum(rs.getString("album_title"));
				m.setAgent(rs.getString("agent_name"));
				
				list.add(m);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.close(conn, pstmt, rs);
		}
		
		return list;
	}

}
