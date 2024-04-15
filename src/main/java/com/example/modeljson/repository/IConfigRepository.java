package com.example.modeljson.repository;

import com.example.modeljson.model.Config;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IConfigRepository extends JpaRepository<Config, Long> {

    /**
     * Find parent for given parent identifier
     *
     * @param parentId parent identifier
     * @return a list of children for a parent
     */
    List<Config> findByParentId(Long parentId);

    /**
     * Find valid configs with parent root
     *
     * @return a list of root level configs
     */
    List<Config> findByParentNullAndDeletedFalse();

    List<Config> findByParent(Config parent);

    /**
     * Find valid configs with parent retrieved by id
     *
     * @param id parent identifier
     * @return a list of children of parent node
     */
    List<Config> findByParentIdAndDeletedFalse(Long id);
}
