package service.logic;

import java.util.List;

import domain.Music;
import service.MusicService;
import store.MusicStore;
import store.logic.MusicStoreLogic;

public class MusicServiceLogic implements MusicService {

	private MusicStore store;

	public MusicServiceLogic() {
		this.store = new MusicStoreLogic();
	}

	@Override
	public Music find(int id) {
		return this.store.read(id);
	}

	@Override
	public List<Music> findByName(String name) {
		return this.store.readByName(name);
	}

	@Override
	public List<Music> findAll() {
		return this.store.readAll();
	}

}
