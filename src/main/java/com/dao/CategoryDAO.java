package com.dao;

import java.util.List;

import com.modal.Category;


public interface CategoryDAO {
            public boolean insertCategory(Category category);
			public List<Category> retrieveCategory();
			public Category removeCategoryById(int categoryid);
			public Category getCategoryById(int categoryid);
}
