package com.naresh.auth.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.naresh.auth.model.ProfileImages;
import com.naresh.auth.model.User;

@Repository
public interface ProfileImagesRepository extends JpaRepository<ProfileImages, String> {

	List<ProfileImages> findByUser(User user);
}
