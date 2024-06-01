package com.springmvcspecification.datatable;

import lombok.Data;

@Data

public class DataTablesRequestForm<T> extends DataTablesRequest {

	private T formBusqueda;
}

