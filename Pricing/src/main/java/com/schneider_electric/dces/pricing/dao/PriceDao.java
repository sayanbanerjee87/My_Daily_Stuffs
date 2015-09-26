package com.schneider_electric.dces.pricing.dao;

import com.schneider_electric.dces.pricing.model.Price;
import com.schneider_electric.dces.pricing.model.PriceList;
import com.schneider_electric.dces.pricing.model.PriceListType;
import com.schneider_electric.dces.pricing.model.PriceRevision;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

public interface PriceDao {

	/**
	 * Retrieves prices for a price list.
	 * @param priceList the price list you are looking to.
	 * @return prices for the given price list.
	 */
	Set<Price> getPrices(String priceList);

	/**
	 * Retrieves prices for a price list at a given date.
	 * @param priceList the price list you are looking to.
     * @param priceDate the date you want the price for.
	 * @return prices for the given price list at a given date.
	 */
	Set<Price> getPrices(String priceList, Date priceDate);

	/**
	 * Retrieves prices from a list of reference ids, a price list and a currency.
	 * @param referenceIds the ids of wanted references.
	 * @param priceList the price list you are looking to.
	 * @return prices for the given ids and price list.
	 */
	Set<Price> getPrices(Set<String> referenceIds, String priceList);

	/**
	 * Retrieves prices from a list of reference ids, a price list and a currency.
	 * @param referenceIds the ids of wanted references.
	 * @param priceList the price list you are looking to.
	 * @param priceDate (Optional) the date you want the price for. If null the current timestamp is used.
	 * @return prices for the given ids and price list.
	 */
	Set<Price> getPrices(Set<String> referenceIds, String priceList, Date priceDate);

    /**
     * Load the revision matching price list and filters.
     * @param federatedId the user federated ID.
     * @param priceListId the price list ID.
     * @param referenceIds the ids of wanted references.
     * @param priceDate (Optional) the date you want the price for. If null the current timestamp is used.
     * @return the revision matching price list and filters.
     */
    PriceRevision loadRevision(String federatedId, String priceListId, Set<String> referenceIds, Date priceDate);

    PriceRevision loadRevision(String priceList, Date priceDate);

    /**
     * Load the given revision, filtering on its refs.
     * @param federatedId the user federated ID.
     * @param revisionId the revision ID.
     * @param refs the ids of wanted references.
     * @return the wanted revision, filtering on its refs.
     */
    PriceRevision loadRevision(String federatedId, long revisionId, Collection<String> refs);

    /**
     * Load the given revision.
     * @param federatedId the user federated ID.
     * @param revisionId the revision ID.
     * @return the wanted revision.
     */
    PriceRevision loadRevision(String federatedId, long revisionId);

    /**
     * Retrieve a price list for the given price list ID, if user has access to it.
     * @param federatedId the user federated ID.
     * @param priceListId the price list ID.
     * @return the price list for the given price list ID, if user has access to it. If the user do not have access to it,
     * and AccessDeniedException is thrown.
     */
    PriceList getPriceList(String federatedId, String priceListId);

    /**
     * Find the price list matching parameters.
     * @param federatedId the user federated ID.
     * @param name the price list name.
     * @param validityDate the validity date.
     * @param archived
     * @return price lists matching parameters.
     */
    List<PriceList> findPriceList(String federatedId, String name, Date validityDate, Boolean archived);

    /**
     * Save a new price list.
     * @param priceList the price list to save.
     */
    void save(String federatedId, PriceList priceList);

    /**
     * Save a new price revision.
     * @param federatedId
     * @param revision the price revision to save.
     */
    void save(String federatedId, PriceRevision revision);

    /**
     * Delete the given price list if associated to the given user.
     * @param federatedId the user federated ID.
     * @param priceListId the price list ID.
     */
    void deletePriceList(String federatedId, String priceListId);

    /**
     * Delete the given revision  if associated to the given user.
     * @param federatedId the user federated ID.
     * @param revisionId the revision ID.
     */
    void deletePriceRevision(String federatedId, Long revisionId);
}