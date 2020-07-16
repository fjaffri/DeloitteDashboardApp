package com.deloitte.dashboard.Model;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class Result {

	private List<User> users;

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	private int totalRows;

	public int gettotalRows() {
		return totalRows;
	}

	public void settotalRows(int totalRows) {
		this.totalRows = totalRows;
	}

}
