package store.logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import domain.BaseballTeam;
import store.BaseballTeamStore;
import store.factory.ConnectionFactory;
import store.utils.JdbcUtils;

public class BaseballTeamStoreLogic implements BaseballTeamStore {

	private ConnectionFactory factory;
	
	public BaseballTeamStoreLogic() {
		this.factory = ConnectionFactory.getInstance();
	}
	
	@Override
	public BaseballTeam retrieve(String teamId) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BaseballTeam bt = null;
		
		try {
			conn = this.factory.createConnection();
			
			pstmt = conn.prepareStatement(
					"select id, name, region, manager, stadium, logo from team_tb where id = ?"
					);
			pstmt.setString(1, teamId);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				bt = new BaseballTeam();
				
				bt.setTeamId(rs.getString("id"));
				bt.setName(rs.getString("name"));
				bt.setRegion(rs.getString("region"));
				bt.setManager(rs.getString("manager"));
				bt.setStadium(rs.getString("stadium"));
				bt.setLogo(rs.getString("logo"));
				
				//service logic에서 추가
				//bt.setPlayers(players);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.close(conn, pstmt, rs);
		}
		
		return bt;
	}

	@Override
	public List<BaseballTeam> retrieveAll() {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<BaseballTeam> teamList = null;
		BaseballTeam bt = null;
		
		try {
			conn = this.factory.createConnection();
			
			pstmt = conn.prepareStatement(
					"select id, name, region, manager, stadium, logo from team_tb"
					);
			
			rs = pstmt.executeQuery();
			
			teamList = new ArrayList<>();
			while(rs.next()){
				bt = new BaseballTeam();
				
				bt.setTeamId(rs.getString("id"));
				bt.setName(rs.getString("name"));
				bt.setRegion(rs.getString("region"));
				bt.setManager(rs.getString("manager"));
				bt.setStadium(rs.getString("stadium"));
				bt.setLogo(rs.getString("logo"));
				
				//service logic에서 추가
				//bt.setPlayers(players);
				
				teamList.add(bt);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.close(conn, pstmt, rs);
		}

		return teamList;
	}

}
