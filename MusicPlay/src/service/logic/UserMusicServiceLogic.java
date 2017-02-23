package service.logic;

import java.util.List;

import domain.Music;
import service.UserMusicService;
import store.UserMusicStore;
import store.logic.UserMusicStoreLogic;

public class UserMusicServiceLogic implements UserMusicService {

	private UserMusicStore store;

	public UserMusicServiceLogic() {
		this.store = new UserMusicStoreLogic();
	}

	@Override
	public boolean register(String userId, int musicId) {
		// Business logic의 일을 여기서 해주는 예
		// presentation의 영역이 아니라
		if (this.store.existUserMusic(userId, musicId)) {
			return false;
		}

		return this.store.create(userId, musicId);
	}

	@Override
	public boolean remove(String userId, int musicId) {
		return this.store.delete(userId, musicId);
	}

	@Override
	public List<Music> findMusicsByUser(String userId) {
		return this.store.readMusicsByUser(userId);
	}

}
