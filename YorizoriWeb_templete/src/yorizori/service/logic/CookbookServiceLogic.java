package yorizori.service.logic;

import java.util.List;

import yorizori.domain.Cookbook;
import yorizori.domain.Procedure;
import yorizori.domain.Recipe;
import yorizori.service.CookbookService;
import yorizori.store.CookbookStore;
import yorizori.store.RecipeStore;
import yorizori.store.StoreFactory;
import yorizori.store.StoreFactoryBuilder;

public class CookbookServiceLogic implements CookbookService {

	private CookbookStore cookbookStore;
	private RecipeStore recipeStore;
	
	public CookbookServiceLogic() {
		StoreFactory factory = StoreFactoryBuilder.createJdbcStoreFactory();
		this.cookbookStore = factory.getCookbookStore();
		this.recipeStore = factory.getRecipeStore();
	}
	
	@Override
	public List<Cookbook> findAllCookbooks() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cookbook findCookbook(int cookbookId) {
		return this.cookbookStore.retrieve(cookbookId);
	}

	//리턴 cookbook, db에 갔다오지 않고 등록 책정보를 바로 확인하기 위해서
	@Override
	public Cookbook registerCookbook(Cookbook cookbook) {
		
		//리턴값  : 발생한 시퀀스 번호(cookbook id)
		int cookbookId = this.cookbookStore.create(cookbook);
		cookbook.setId(cookbookId);
		
		return cookbook;
	}

	@Override
	public Recipe findRecipeById(int recipeId) {
		
		Recipe recipe = this.recipeStore.retrieve(recipeId);
		
		//레시피store로직에서 찾아 올때 cookbook에는 id만 들어있기 때문에
		//해당 id의 cookbook을 찾아서 레시피에 넣어줘야(set) 한다
		if(recipe != null){
			Cookbook cookbook = this.cookbookStore.retrieve(recipe.getCookbook().getId());
			recipe.setCookbook(cookbook);
			
			//조리절차 set
			List<Procedure> list = this.recipeStore.retrieveProcedures(recipe.getId());
			recipe.setProcedures(list);
		}
		
		return recipe;
	}

	@Override
	public Recipe registerRecipe(Recipe recipe) {
		
		//반환값으로 seq_nextval값을 받아온다
		int recipeId = this.recipeStore.create(recipe);
		
		//비어있는 아이디를 셋팅
		recipe.setId(recipeId);
		
		//조리 절차 생성
		List<Procedure> list = recipe.getProcedures();
		if(list != null){
			for(Procedure p : list){
				this.recipeStore.createProcedure(recipe.getId(), p);
			}
		}
		
		return recipe;
	}

}
