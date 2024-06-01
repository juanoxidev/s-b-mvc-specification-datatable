package com.springmvcspecification.datatable;

import java.util.List;

import lombok.Data;

@Data
public class DataTablesResponse<T> {
	private List<T> content;
	private long recordsTotal;
	private long recordsFiltered;

}
