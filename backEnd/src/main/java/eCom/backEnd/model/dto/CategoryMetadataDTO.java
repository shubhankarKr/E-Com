package eCom.backEnd.model.dto;

public class CategoryMetadataDTO {
	private int id;
	private String name;

	public CategoryMetadataDTO(Object id, Object name) {
		super();
		this.id = (int) id;
		this.name = (String) name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
