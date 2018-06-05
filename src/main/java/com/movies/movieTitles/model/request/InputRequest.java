package com.movies.movieTitles.model.request;

public class InputRequest {
	
	private Data data;

	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "InputRequest [data=" + data + "]";
	}

}
