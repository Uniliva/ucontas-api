package br.com.uniliva.ucontas.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.uniliva.ucontas.business.CategoryBusiness;
import br.com.uniliva.ucontas.model.Category;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("v1/categories")
@Api(value = "API of categories")
@CrossOrigin(origins = "*")
public class CategoryController {

    @Autowired
	private CategoryBusiness business;

	@GetMapping
	@ApiOperation(value = "Returns a list of all categories")
	public ResponseEntity<List<Category>> listAll() {
		return ResponseEntity.ok(business.listAll());
	}
	
	@GetMapping("/{id}")
	@ApiOperation(value = "Search for a category using the code")
	public ResponseEntity<Category> findById(@PathVariable Long id) {
		return ResponseEntity.ok().body(business.findById(id));
	}
	
	@PostMapping
	@ApiOperation(value = "Save a category")
	public ResponseEntity<Category> save(@Valid @RequestBody Category Category) {
		Category obj = business.save(Category);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "Remove a category using the code")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		business.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping
	@ApiOperation(value = "Update a category")
	public ResponseEntity<Category> update(@Valid @RequestBody Category Category) {
		return ResponseEntity.ok(business.update(Category));
	}
}