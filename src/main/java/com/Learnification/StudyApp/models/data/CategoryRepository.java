package com.Learnification.StudyApp.models.data;

import com.Learnification.StudyApp.models.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Integer>  {
}