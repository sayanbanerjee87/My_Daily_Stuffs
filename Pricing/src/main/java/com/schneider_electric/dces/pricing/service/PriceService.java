package com.schneider_electric.dces.pricing.service;

import com.schneider_electric.dces.pricing.model.*;

import javax.ws.rs.NotFoundException;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

public interface PriceService {
	Set<Price> getPrices(String priceList);
	Set<Price> getPrices(String priceList, Date priceDate);
	Set<Price> getPrices(Set<String> referenceIds, String priceList, Date priceDate);
	void setPrices(PriceRevision revision);
	Set<Price> getPricesWithDiscount(Set<String> referenceIds, String priceList, String federatedId, Date validityDate);

    /**
     * Save given discounts for user.
     * @param federatedId the user federated ID.
     * @param discounts discounts to save.
     */
    void saveDiscounts(String federatedId, Collection<Discount> discounts);

    /**
     * Save given discounts for user.
     * @param federatedId the user federated ID.
     * @param priceListId the price list ID.
     * @param discounts discounts to save.
     */
    void saveDiscounts(String federatedId, String priceListId, Collection<Discount> discounts);

    /**
     * Load discount families related to the given country.
     * @param priceList the country code.
     * @param validityDate @return discount families related to the given country.
     */
    Collection<DiscountFamily> loadFamilies(String priceList, Date validityDate);

    /**
     * Load discounts related to the given user.
     * @param federatedId user id.
     * @return discounts related to the given user.
     */
    Collection<Discount> loadDiscounts(String federatedId, String countryCode);

    /**
     * Load the price lists for the given user, filtering by name, type, validity date.
     * @param federatedId (Optional) the connected user federated ID. If null, load public price lists only.
     * @param name (Optional) the price list name filter. No filter by default.
     * @param validityDate (Optional) the validity date to filter. If set return only price lists that have prices for the given date.
     * @param archived
     * @return the matching price lists. Not null.
     */
    List<PriceList> findPriceLists(String federatedId, String name, Date validityDate, Boolean archived);

    /**
     * Load the price list for the given ID.
     * @param federatedId
     * @param priceListId the price list ID.
     * @return the matching price list. Null if not found.
     */
    PriceList loadPriceList(String federatedId, String priceListId);

    /**
     * Create a new price list.
     * @param federatedId the connected user federatedId.
     * @param priceList the price list to create.
     * @return the created price list with its ID.
     */
    PriceList createPriceList(String federatedId, PriceList priceList);

    /**
     * Update the given price list.
     * @param federatedId the connected user federatedId.
     * @param priceList the price list to create.
     * @param priceListId
     */
    void updatePriceList(String federatedId, PriceList priceList, String priceListId) throws NotFoundException;

    /**
     * Delete the given price list.
     * @param federatedId the connected user federatedId.
     * @param priceListId the ID of the price list to delete.
     */
    void deletePriceList(String federatedId, String priceListId);

    /**
     * Load the price list revision matching the given ID and refs.
     * @param federatedId
     * @param revisionId the ID of the revision to load.
     * @param refs the reference IDs to load prices for.
     * @return the price list revision matching the given ID. Null if no revision can be found. Only prices matching refs are loaded.
     */
    PriceRevision loadPrices(String federatedId, long revisionId, Collection<String> refs);

    /**
     * Load the price list revision matching the given ID.
     * @param federatedId
     * @param priceListId the ID of the revision to load.
     * @param refs (Optional) the reference IDs to load prices for. If null, all references are loaded.
     * @param  validityDate (Optional) the date to use for revision search. If null, the current day is used.
     * @return the price list revision matching the given ID. Null if no revision can be found. Only prices matching refs are loaded.
     */
    PriceRevision loadPrices(String federatedId, String priceListId, Set<String> refs, Date validityDate);

    /**
     * Create a new price list revision.
     * @param federatedId the connected user federatedId.
     * @param priceListId the price list ID.
     * @param revision the price revision to create.
     * @return the created price revision with its ID.
     */
    PriceRevision createPriceListRevision(String federatedId, String priceListId, PriceRevision revision);

    /**
     * Update the given price list revision.
     * @param federatedId the connected user federatedId.
     * @param revision the price revision to update.
     */
    void updatePriceListRevision(String federatedId, PriceRevision revision);

    /**
     * Delete the given price list revision.
     * @param federatedId the connected user federatedId.
     * @param revisionId the ID of the price revision to delete.
     */
    void deletePriceListRevision(String federatedId, long revisionId);

    /**
     * Load the price list revision for the given ID.
     * @param federatedId
     * @param revisionId the price list revision id.
     * @return the mathching price list revision. Null if not found.
     */
    PriceRevision loadPriceListRevision(String federatedId, Long revisionId);
}