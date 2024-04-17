package com.example.modeljson.repository;

import com.example.modeljson.model.Config;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IConfigRepository extends JpaRepository<Config, Long> {


    /**
     * Find parent for the given parent identifier
     *
     * @param parentId parent identifier
     * @return a list of children for a parent
     */
    List<Config> findByParentId(Long parentId);


    /**
     * Find configs with parent root
     *
     * @return a list of root level configs
     */
    List<Config> findByParent(Config parent);

    /**
     * Config node with id and deleted false
     * 
     * @param id identifier
     */
    Optional<Config> findByIdAndDeletedFalse(Long id);

   
    /**
     * Find configs with parent root and not deleted
     *
     * @return a list of root level configs
     */
    List<Config> findByParentNullAndDeletedFalse();


    /**
     * Find valid configs with parent retrieved by id
     *
     * @param id parent identifier
     * @return a list of children of parent node
     */
    List<Config> findByParentIdAndDeletedFalse(Long id);
}
