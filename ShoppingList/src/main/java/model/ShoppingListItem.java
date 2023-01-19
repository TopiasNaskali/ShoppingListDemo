package model;

public class ShoppingListItem {
	private long id;
	private String title;

	public ShoppingListItem(long id, String title) {
		super();
		this.id = id;
		this.title = title;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}


	@Override
	public String toString() {
		return "ShoppingListItem [id=" + id + ", ostos=" + title + "]";
	}
}
