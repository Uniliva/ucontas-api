package br.com.uniliva.ucontas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.uniliva.ucontas.model.Category;
import br.com.uniliva.ucontas.repository.CategoryRepository;

@Service
public class CategoryService {
	@Autowired
	private CategoryRepository repo;

	public List<Category> listAll() {
		return repo.findAll();
	}

	public Optional<Category> findById(long id) {
		return repo.findById(id);
	}

	public Category save(Category category) {
		return repo.save(category);		
	}

	public void deleteById(Long id) {
		repo.deleteById(id);;		
	}

}