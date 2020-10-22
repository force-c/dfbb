package com.yasso.dfbb.es.repository;

import com.yasso.dfbb.es.entity.Movies;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author guochaung
 * @version 1.0
 * @date 2020/10/21 16:09
 */
public interface MoviesRepository extends ElasticsearchRepository<Movies, String> {

    Page<Movies> findByTitle(String title, Pageable pageable);
}
