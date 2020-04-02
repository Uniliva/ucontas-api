package br.com.uniliva.ucontas.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import br.com.uniliva.ucontas.exception.BusinessException;
import br.com.uniliva.ucontas.exception.NotFoundException;
import br.com.uniliva.ucontas.model.Category;
import br.com.uniliva.ucontas.service.CategoryService;

@Component
public class CategoryBusiness {
	
	@Autowired
	private CategoryService service;

	public List<Category> listAll() {
		return service.listAll();
	}

	public Category findById(Long id) {
		return service.findById(id).orElseThrow(() -> new NotFoundException("Category not found"));
	}

	public Category save(Category category) {
		return service.save(category);
	}

	public void delete(Long id) {
		Category category = findById(id);
		try {
			service.deleteById(category.getId());
		} catch (DataIntegrityViolationException e) {
			throw new BusinessException("You cannot delete a category being used");
		}
		
	}

	public Category update(Category category) {
		findById(category.getId());
		return service.save(category);
	}


}      