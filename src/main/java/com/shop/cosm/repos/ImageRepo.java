package com.shop.cosm.repos;

import com.shop.cosm.domain.Image;
import org.apache.catalina.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ImageRepo extends CrudRepository<Image, Long> {

    List<Image> findByFilename(String filename);

}
