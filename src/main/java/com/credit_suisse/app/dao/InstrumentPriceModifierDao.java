package com.credit_suisse.app.dao;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.credit_suisse.app.model.InstrumentPriceModifier;

//@Configuration
@Repository
public interface InstrumentPriceModifierDao extends CrudRepository<InstrumentPriceModifier, Long> {
	
	InstrumentPriceModifier findById(Long id);
	
	InstrumentPriceModifier findByName(String name);
	
	InstrumentPriceModifier findByModifier(double modifier);
	
	List<InstrumentPriceModifier> findAll();
	
	@Modifying(clearAutomatically = true)
	@Query("UPDATE InstrumentPriceModifier SET multiplier = :multiplier WHERE name = :name")	
	void setMultiplier(@Param("name") String name, @Param("multiplier") double multiplier);
}
