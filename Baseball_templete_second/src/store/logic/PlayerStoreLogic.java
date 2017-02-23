package store.logic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import domain.BaseballTeam;
import domain.Player;
import store.PlayerStore;
import store.factory.ConnectionFactory;
import store.utils.JdbcUtils;

public class PlayerStoreLogic implements PlayerStore{

	private ConnectionFactory factory;
	
	public PlayerStoreLogic() {
		this.factory = ConnectionFactory.getInstance();
	}
	
	@Override
	public void update(Player player) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		Player p = null;
		int count = 0;
		
		try {
			conn = this.factory.createConnection();
			
			pstmt = conn.prepareStatement(
					"update player_tb set teamid = ? where id = ?"
					);
			
			pstmt.setInt(1, Integer.parseInt(player.getTeamId()));
			pstmt.setInt(2, Integer.parseInt(player.getPlayerId()));

			count = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.close(conn, pstmt);
		}
	}

	@Override
	public Player retrieve(String playerId) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Player p = null;
		
		
		try {
			conn = this.factory.createConnection();
			
			pstmt = conn.prepareStatement(
					"select id, teamid, name, backnumber, position, ascii(hitting), ascii(THROW) from player_tb where id = ?"
					);
			pstmt.setString(1, playerId);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				p = new Player();
				
				p.setPlayerId(((Integer)rs.getInt("id")).toString());
				p.setTeamId(((Integer)rs.getInt("teamid")).toString());
				p.setName(rs.getString("name"));
				p.setBackNumber(rs.getInt("backnumber"));
				p.setPosition(rs.getString("position"));
				
				//히팅, 스로우 추가
				int n = rs.getInt("ascii(hitting)");
				char c = (char)n;
				p.setHittingHand(c+"");
				
				n = rs.getInt("ascii(THROW)");
				c = (char)n;
				p.setThrowHand(c+"");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.close(conn, pstmt, rs);
		}
		
		return p;
	}

	@Override
	public List<Player> retrieveByTeam(String teamId) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Player> list = null;
		Player p = null;
		
		
		try {
			conn = this.factory.createConnection();
			
			pstmt = conn.prepareStatement(
					"select id, teamid, name, backnumber, position, ascii(hitting), ascii(THROW) from player_tb where teamid = ?"
					);
			pstmt.setString(1, teamId);
			
			rs = pstmt.executeQuery();
			
			list = new ArrayList<>();
			while(rs.next()){
				p = new Player();
				
				p.setPlayerId(((Integer)rs.getInt("id")).toString());
				p.setTeamId(((Integer)rs.getInt("teamid")).toString());
				p.setName(rs.getString("name"));
				p.setBackNumber(rs.getInt("backnumber"));
				p.setPosition(rs.getString("position"));
				
				
				//히팅, 스로우 추가
				int n = rs.getInt("ascii(hitting)");
				char c = (char)n;
				p.setHittingHand(c+"");
				
				n = rs.getInt("ascii(THROW)");
				c = (char)n;
				p.setThrowHand(c+"");
				
				list.add(p);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.close(conn, pstmt, rs);
		}
		
		return list;
	}

}
