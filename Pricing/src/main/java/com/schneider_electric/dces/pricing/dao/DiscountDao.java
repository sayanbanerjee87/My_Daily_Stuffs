package com.schneider_electric.dces.pricing.dao;

import com.schneider_electric.dces.pricing.model.Discount;
import com.schneider_electric.dces.pricing.model.DiscountFamily;

import java.util.Collection;
import java.util.Date;

/**
 * User: FDU3285
 * Date: 14/11/2014
 * Time: 08:32
 */
public interface DiscountDao {

    /**
     * Load discounts for given user and country.
     * @param federatedId the user federated ID.
     * @param countryCode the country code.
     * @param discountDate the date for discount search.
     * @return discounts gor given user and country.
     */
    Collection<Discount> loadDiscounts(String federatedId, String countryCode, Date discountDate);

    /**
     * Load discounts for given user and country.
     * @param federatedId the user federated ID.
     * @param countryCode the country code.
     * @return discounts gor given user and country.
     */
    Collection<Discount> loadDiscounts(String federatedId, String countryCode);

    /**
     * Save discounts for given user and country.
     * @param discounts the discount collection for the given user and country.
     */
    void saveDiscounts(Collection<Discount> discounts);

    /**
     * Load the discount families for the given country;
     *
     * @param validityDate
     * @return the disocunt families for the given country.
     */
    Collection<DiscountFamily> loadDiscountFamilies(String priceList, Date validityDate);

}
