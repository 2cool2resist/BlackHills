package com.bhc.startstop.cis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bhc.startstop.cis.model.Premise;

public interface PremiseRepository extends JpaRepository<Premise, Long> {

	// Address Searches

	/*
	 * select PREMISE_ID, STREET_ADDR AS ADDRESS, MAIL_CITY, STATE from
	 * CISPDDEV.CIS_PREMSP_MV where COY = 1 and ADDRESS LIKE '%' and MAIL_CITY = ''
	 * and STATE = '';
	 * 
	 * 
	 * select PREMISE_ID, STREET_ADDR || ' ' || MAIL_CITY || ' ' || STATE AS
	 * ADDRESS, MAIL_CITY, STATE from CISPDDEV.CIS_PREMSP_MV where COY = 1 and
	 * STREET_ADDR LIKE '%';
	 */

	/*
	 * @Query("select distinct new Premise(premiseId as premiseId, coy, address, buildingName, city, state) "
	 * + " from Premise p where p.address like :searchTerm% order by p.address")
	 */
	List<Premise> findDistinctTop50ByCoyAndAddressContainingOrderByAddressAsc(@Param("coy") Integer coy,
			@Param("searchTerm") String address);

	/*
	 * @Query("select distinct new Premise(premiseId as premiseId, coy, address, buildingName, city, state) "
	 * +
	 * " from Premise p where p.address like :searchTerm% and p.state = :stateTerm order by p.address"
	 * )
	 */
	List<Premise> findDistinctTop50ByCoyAndAddressContainingAndStateOrderByAddressAsc(@Param("coy") Integer coy,
			@Param("searchTerm") String address, @Param("stateTerm") String state);

	/*
	 * @Query("select distinct new Premise(premiseId as premiseId, coy, address, buildingName, city, state) "
	 * + " from Premise p where p.address like :searchTerm% " +
	 * " and p.state= :stateTerm and p.city = :cityTerm order by p.address")
	 */
	List<Premise> findDistinctTop50ByCoyAndAddressContainingAndStateAndCityOrderByAddressAsc(@Param("coy") Integer coy,
			@Param("searchTerm") String address, @Param("stateTerm") String state, @Param("cityTerm") String city);

	// pull out reporting group for rate change notifications
	// @Query("select distinct reportingGroup from Premise where premise in
	// :premiseList ")
	List<Premise> findDistinctReportingGroupByPremiseIdIn(@Param("premiseList") List<Long> premiseId);

	// City Searches
	@Query("select distinct city from Premise where state = :stateValue order by city")
	List<String> findDistinctCityByStateOrderByCityAsc(@Param("stateValue") String state);

	// used in Premise debug
	List<Premise> findDistinctByPremiseIdIn(List<Long> premiseId);

}