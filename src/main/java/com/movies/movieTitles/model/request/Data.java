package com.movies.movieTitles.model.request;

import java.util.List;

public class Data {

	private List<String> orders;

	public List<String> getOrders() {
		return orders;
	}

	public void setOrders(List<String> orders) {
		this.orders = orders;
	}

	@Override
	public String toString() {
		return "Data{" +
				"orders=" + orders +
				'}';
	}
}
