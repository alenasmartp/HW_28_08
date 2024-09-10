package de.telran.myshop.HW_28_08;


import de.telran.myshop.entity.UserDetail;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailsRepository extends CrudRepository<UserDetail, Long> {
}

