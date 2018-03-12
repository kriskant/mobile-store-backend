package com.dao;


import java.util.List;

import com.modal.Supplier;

public interface SupplierDAO {
	public boolean insertSupplier(Supplier supplier);
	public Supplier removeSupplierById(int supplierid);
	public List<Supplier> retrieveSupplier();
	public Supplier getSupplierById(int supplierid);
}
