package com.springmvcspecification.datatable;

import lombok.Data;

@Data
public class DataTablesColumn {
	private String name;
	private boolean searchable;
	private boolean orderable;
	private DataTablesSearch search;

}