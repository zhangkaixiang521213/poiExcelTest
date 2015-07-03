package poi;

public class Bean {

	private Double account;
	private Double price;
	private Double sum;
	private String giftGoods;
	
	public Bean(Double account, Double price, Double sum, String giftGoods) {
		super();
		this.account = account;
		this.price = price;
		this.sum = sum;
		this.giftGoods = giftGoods;
	}
	public Double getAccount() {
		return account;
	}
	public void setAccount(Double account) {
		this.account = account;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Double getSum() {
		return sum;
	}
	public void setSum(Double sum) {
		this.sum = sum;
	}
	public String getGiftGoods() {
		return giftGoods;
	}
	public void setGiftGoods(String giftGoods) {
		this.giftGoods = giftGoods;
	}
	
	
	
	
}
