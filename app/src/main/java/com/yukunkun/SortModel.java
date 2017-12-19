package com.yukunkun;

public class SortModel {

	private String name;
	private String sortLetters;

	public SortModel(String name, String sortLetters) {
		this.name = name;
		this.sortLetters = sortLetters;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSortLetters() {
		return sortLetters;
	}
	public void setSortLetters(String sortLetters) {
		this.sortLetters = sortLetters;
	}

	@Override
	public String toString() {
		return "SortModel{" +
				"name='" + name + '\'' +
				", sortLetters='" + sortLetters + '\'' +
				'}';
	}
}
