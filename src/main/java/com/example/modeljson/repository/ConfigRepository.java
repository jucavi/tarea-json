package com.example.modeljson.repository;

import com.example.modeljson.model.Config;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ConfigRepository extends JpaRepository<Config, Long> {

    /**
     * Find parent for given parent identifier
     *
     * @param parentId parent identifier
     * @return a list of children for a parent
     */
    List<Config> findByParentId(Long parentId);
}
