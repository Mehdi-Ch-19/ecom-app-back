package com.ecomapp.customer.repository;

import com.ecomapp.customer.entity.Adresse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdresseRepo extends JpaRepository<Adresse,Long> {
}
