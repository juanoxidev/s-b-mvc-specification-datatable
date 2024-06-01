package com.springmvcspecification.datatable;

import java.util.Collections;
import java.util.List;

import lombok.Data;

@Data
public class DataTablesRequest {

	private int draw;
	private int start;
	private int length;
	private DataTablesSearch search;
	private List<DataTablesColumn> columns = Collections.emptyList();
	private List<DataTablesOrder> order = Collections.emptyList();

}
