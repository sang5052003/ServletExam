package shop.domain;

import java.text.NumberFormat;

/**
 * Product
 * 
 * @since 2016. 9. 28.
 * @author 진권기 (kwonkijin@nextree.co.kr)
 */
public class Product {
	//
	private int serialNo; // 제품번호
	private String name; // 제품이름
	private int price; // 제품가격
	private int like; // 선호도 (0~5)

	// private int star; //필요없는가?

	public Product() {
	}

	public Product(int serialNo, String name, int price, int like) {
		//
		this.serialNo = serialNo;
		this.name = name;
		this.price = price;
		this.like = like;
	}

	public int getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(int serialNo) {
		this.serialNo = serialNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPriceStr() {

		// string 리턴으로 바꿔주고
		// 포맷써서 세자릿수 콤마 찍기 추가

		NumberFormat f = NumberFormat.getNumberInstance();

		String s = f.format(this.price);

		return s;
	}
	
	public String sumPriceStr(int price) {
		NumberFormat f = NumberFormat.getNumberInstance();
		String s = f.format(price);
		return s;
	}

	public int getPrice() {
		// string 리턴으로 바꿔주고
		// 포맷써서 세자릿수 콤마 찍기 추가
		return price;
		// return s;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getLike() {
		return like;
	}

	public void setLike(int like) {
		this.like = like;
	}

	public int getStar() {
		int star = 0;
		for (int i = 0; i < like; i++) {
			star += 1;
		}
		return star * 20;
	}
}
