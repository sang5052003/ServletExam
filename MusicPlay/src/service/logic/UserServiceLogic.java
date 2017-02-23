package service.logic;

import domain.User;
import service.UserService;
import store.UserStore;
import store.logic.UserStoreLogic;

public class UserServiceLogic implements UserService {

	private UserStore store;
	
	public UserServiceLogic() {
		this.store = new UserStoreLogic();
	}
	
	@Override
	public User login(User user) {

		//패스워드 체크 추가
		//
		
		return this.find(user.getLoginId());
	}

	@Override
	public boolean register(User user) {
		return this.store.create(user);
	}

	@Override
	public User find(String loginId) {
		return this.store.read(loginId);
	}


}
