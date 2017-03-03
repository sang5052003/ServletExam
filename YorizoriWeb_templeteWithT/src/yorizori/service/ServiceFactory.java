package yorizori.service;

import yorizori.service.logic.CookbookServiceLogic;
import yorizori.service.logic.UserServiceLogic;

public class ServiceFactory {
	private static ServiceFactory instance = new ServiceFactory();
	
	private UserService userService;
	private CookbookService cookbookService;
	
	
	private ServiceFactory() {
		
		this.userService = new UserServiceLogic();
		this.cookbookService = new CookbookServiceLogic();
	}
	
	public static UserService getUserService(){
		return instance.userService;
	}
	
	public static CookbookService getCookbookService(){
		return instance.cookbookService;
	}
}
