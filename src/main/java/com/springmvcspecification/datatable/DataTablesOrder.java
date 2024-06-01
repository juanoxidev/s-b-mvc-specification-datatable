package com.springmvcspecification.datatable;

import lombok.Data;

@Data
public class DataTablesOrder {
	public enum Direction {
		asc, desc
	}

	private int column;
	private Direction dir;

}
