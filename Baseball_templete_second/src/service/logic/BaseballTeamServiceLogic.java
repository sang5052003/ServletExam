package service.logic;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import domain.BaseballTeam;
import domain.Player;
import service.BaseballTeamService;
import store.BaseballTeamStore;
import store.PlayerStore;
import store.logic.BaseballTeamStoreLogic;
import store.logic.PlayerStoreLogic;

public class BaseballTeamServiceLogic implements BaseballTeamService{

	private PlayerStore playerStore;
	private BaseballTeamStore teamStore;
	
	public BaseballTeamServiceLogic() {
		this.playerStore = new PlayerStoreLogic();
		this.teamStore = new BaseballTeamStoreLogic();
	}
	
	@Override
	public BaseballTeam findTeam(String teamId) {
		
		//
		List<Player> players = this.playerStore.retrieveByTeam(teamId);
		BaseballTeam team = this.teamStore.retrieve(teamId);
		team.setPlayers(players);
		
		return team;
	}

	@Override
	public List<BaseballTeam> findAllTeams() {
		
		//
		List<Player> players = null;
		List<BaseballTeam> teamList = this.teamStore.retrieveAll();
		for(BaseballTeam team : teamList){
			players = this.playerStore.retrieveByTeam(team.getTeamId());
			team.setPlayers(players);
		}
		
		return teamList;
	}

	//플레이어가 존재하는 팀만 리턴?
	@Override
	public List<BaseballTeam> findAllTeamsWithPlayers() {
		
		List<BaseballTeam> list = this.findAllTeams();
		
		Iterator<BaseballTeam> iter = list.iterator();
		while(iter.hasNext()){
			if(iter.next().getNumberOfPlayers() == 0){
				iter.remove();
			}
		}
		
		return list;
	}

	//인자로 주어진 팀아이디를 제외한 나머지 팀들 리턴
	@Override
	public List<BaseballTeam> findTradeTargetPlayers(String teamId) {
		
		List<BaseballTeam> teamList = this.findAllTeams();
		for(BaseballTeam team : teamList){
			if(team.getTeamId().equals(teamId)){
				teamList.remove(team); //팀아이디 일치하는 팀을 제외
				break;
			}
		}
		
		return teamList;
	}

	/////////////////////////////////////////
	
	@Override
	public Player findPlayer(String playerId) {
		return this.playerStore.retrieve(playerId);
	}

	@Override
	public void tradePlayer(String sourcePlayerId, String targetPlayerId) {
		
		Player src = this.playerStore.retrieve(sourcePlayerId);
		Player dest = this.playerStore.retrieve(targetPlayerId);
		
		String destId = dest.getPlayerId();
		String srcId = src.getPlayerId();
		
		src.setPlayerId(destId);
		dest.setPlayerId(srcId);
		
		this.playerStore.update(src);
		this.playerStore.update(dest);
	}

}
