package yorizori.service.logic;

import yorizori.domain.User;
import yorizori.service.UserService;
import yorizori.store.StoreFactory;
import yorizori.store.StoreFactoryBuilder;
import yorizori.store.UserStore;

public class UserServiceLogic implements UserService {

	private UserStore store;
	
	public UserServiceLogic() {
		
		StoreFactory factory = StoreFactoryBuilder.createJdbcStoreFactory();
		this.store = factory.getUserStore();
	}
	
	@Override
	public User findUser(String userId) {
		return this.store.retrieve(userId);
	}

	@Override
	public void registerUser(User user) {
		this.store.create(user);
	}

}
