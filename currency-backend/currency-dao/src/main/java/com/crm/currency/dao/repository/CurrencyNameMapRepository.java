package com.crm.currency.dao.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crm.currency.dao.entity.CurrencyNameMap;

@Repository
public interface CurrencyNameMapRepository extends JpaRepository<CurrencyNameMap, Long> {
	CurrencyNameMap findByEnglishName(String englishName);
}