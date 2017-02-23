package store.logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import domain.Music;
import store.MusicStore;
import store.factory.ConnectionFactory;
import store.utils.JdbcUtils;

public class MusicStoreLogic implements MusicStore{

	private ConnectionFactory factory;
	
	public MusicStoreLogic() {
		this.factory = ConnectionFactory.getInstance();
	}
	
	@Override
	public Music read(int id) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Music m = null;
		
		try {
			conn = this.factory.createConnection();
			
			pstmt = conn.prepareStatement(
					"select id, name, artist_name, album_title, image, agent_name from music_tb where id = ?"
					);
			
			//String str = "%" + name + "%";
			pstmt.setInt(1, id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				
				m = new Music();
				
				m.setId(rs.getInt("id"));
				m.setName(rs.getString("name"));
				m.setArtist(rs.getString("artist_name"));
				m.setAlbum(rs.getString("album_title"));
				m.setImage(rs.getString("image"));
				m.setAgent(rs.getString("agent_name"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.close(conn, pstmt, rs);
		}
		
		return m;
	}

	@Override
	public List<Music> readByName(String name) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Music> list = null;
		Music m = null;
		
		try {
			conn = this.factory.createConnection();
			
			pstmt = conn.prepareStatement(
					"select id, name, artist_name, album_title, image, agent_name from music_tb where name like '%' || ? || '%'"
					);
			
			//String str = "%" + name + "%";
			pstmt.setString(1, name);
			
			rs = pstmt.executeQuery();
			
			list = new ArrayList<>();
			while(rs.next()){
				
				m = new Music();
				
				m.setId(rs.getInt("id"));
				m.setName(rs.getString("name"));
				m.setArtist(rs.getString("artist_name"));
				m.setAlbum(rs.getString("album_title"));
				m.setImage(rs.getString("image"));
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

	@Override
	public List<Music> readAll() {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Music> list = null;
		Music m = null;
				
		try {
			conn = this.factory.createConnection();
			
			pstmt = conn.prepareStatement(
					"select id, name, artist_name, album_title, image, agent_name from music_tb"
					);
			
			rs = pstmt.executeQuery();
			
			list = new ArrayList<>();
			while(rs.next()){
				m = new Music();
				
				m.setId(rs.getInt("id"));
				m.setName(rs.getString("name"));
				m.setArtist(rs.getString("artist_name"));
				m.setAlbum(rs.getString("album_title"));
				m.setImage(rs.getString("image"));
				m.setAgent(rs.getString("agent_name"));
				
				list.add(m);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			JdbcUtils.close(pstmt, conn, rs);
		}
		
		return list;
	}

}
