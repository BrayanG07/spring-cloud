package hn.springcloud.msvc.products.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import hn.springcloud.msvc.libs.commons.entities.Category;
import hn.springcloud.msvc.products.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }

}
