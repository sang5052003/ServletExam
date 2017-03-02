package yorizori.service.logic;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import yorizori.domain.Cookbook;
import yorizori.domain.Procedure;
import yorizori.domain.Recipe;
import yorizori.domain.User;
import yorizori.service.CookbookService;

public class CookbookServiceLogicTest {

	private CookbookService service;
	
	@Before
	public void setUp(){
		this.service = new CookbookServiceLogic();
	}
	
//	@Test
//	public void testFindAllCookbooks() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testFindCookbook() {
//		fail("Not yet implemented");
//	}

//	@Test
//	public void testRegisterCookbook() {
//		Cookbook cookbook = new Cookbook();
//		cookbook.setName("한식 요리책");
//		cookbook.setDescription("한식 요리 바이블");
//		User user = new User();
//		user.setUserId("kimgisa");
//		user.setName("김기사");
//		cookbook.setAuthor(user);
//		
//		cookbook = this.service.registerCookbook(cookbook);
//		
//		//위에서 등록한 놈
//		cookbook = this.service.findCookbook(cookbook.getId());
//		
//		assertEquals("한식 요리책", cookbook.getName());
//		assertEquals("한식 요리 바이블", cookbook.getDescription());
//		
//		assertEquals("김기사", cookbook.getAuthor().getName());
//	}

//	@Test
//	public void testFindRecipeById() {
//		Recipe recipe = this.service.findRecipeById(1);
//		assertEquals("맛있는 피자", recipe.getName());
//		assertEquals("요리조리북", recipe.getCookbook().getName());
//	}
//
	@Test
	public void testRegisterRecipe() {
		
		Recipe recipe = new Recipe();
		recipe.setCookbook(new Cookbook(1));
		recipe.setName("떡볶이");
		recipe.setLevel(1);
		recipe.setTime(30);
		recipe.setIngredients("떡");
		User writer = new User();
		writer.setUserId("kimgisa");
		writer.setName("김기사");
		recipe.setWriter(writer);
		
		//조리절차도 생성해야 함
		List<Procedure> p = new ArrayList<>();
		p.add(new Procedure(1, "물을 끓인다"));
		p.add(new Procedure(2, "떡을 넣는다"));
		recipe.setProcedures(p);
		
		recipe = this.service.registerRecipe(recipe);
		
		recipe = this.service.findRecipeById(recipe.getId());
		assertEquals("떡볶이", recipe.getName());
		
		p = recipe.getProcedures();
		assertEquals("물을 끓인다", p.get(0).getProcedure());
		assertEquals("떡을 넣는다", p.get(1).getProcedure());
		
	}

}
