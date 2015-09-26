package com.schneider_electric.dces.pricing.rest.dto;

import java.util.Date;

import com.schneider_electric.dces.pricing.model.Discount;

public class DiscountWithDescription extends Discount {
	
	private String description;

	public DiscountWithDescription(String familyCode,
			Double value, Date validityStart, String description) {
		super(familyCode, value, validityStart);
		this.setDescription(description);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
