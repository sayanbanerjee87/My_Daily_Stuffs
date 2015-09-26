package com.schneider_electric.dces.pricing.rest;

import com.schneider_electric.dces.pricing.exception.UserNotFoundException;
import com.schneider_electric.dces.pricing.model.Discount;
import com.schneider_electric.dces.pricing.model.DiscountFamily;
import com.schneider_electric.dces.pricing.rest.dto.DiscountWithDescription;
import com.schneider_electric.dces.pricing.service.PriceService;
import com.schneider_electric.dces.pricing.service.UserService;
import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;

import static com.google.common.collect.Lists.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DiscountResourceImplUnitTest {

    private static final String FEDERATED_ID = "1234";
    private static final String TOKEN = "azerty1234";
    @Mock
    private PriceService priceService;

    @Mock
    private UserService userService;

    @InjectMocks
    private DiscountResourceImpl discountResource;

    @Before
    public void setUp() throws Exception {
        when(userService.getFederatedId(TOKEN)).thenReturn(FEDERATED_ID);
        when(userService.getFederatedId("unknown")).thenThrow(new UserNotFoundException("Unknown"));
    }

    @Test
    public void shouldSetDiscount_callPriceServiceWithRightData() {
        Collection<Discount> discounts = newArrayList(
                new Discount("F1", "FR", 0.2d, new LocalDate(2014, 1, 1).toDate()),
                new Discount("F2", "EN", 0.3d, new LocalDate(2014, 6, 1).toDate())
        );

        discountResource.setDiscounts(discounts, TOKEN);

        verify(userService).getFederatedId(TOKEN);
        verify(priceService).saveDiscounts(FEDERATED_ID, discounts);
    }

    @Test(expected = UserNotFoundException.class)
    public void shouldSetDiscount_callThrow401_whenUserCannotBeFound() {
        Collection<Discount> discounts = newArrayList(
                new Discount("F1", "FR", 0.2d, new LocalDate(2014, 1, 1).toDate()),
                new Discount("F2", "EN", 0.3d, new LocalDate(2014, 6, 1).toDate())
        );

        discountResource.setDiscounts(discounts, "unknown");
    }

    @Test
    public void shouldGetDiscounts_returnEveryFamiliesWithAssociatedDiscount_ifExist() throws IOException {
        when(priceService.loadFamilies(eq("FR"), any(Date.class))).thenReturn(newArrayList(
                new DiscountFamily("F1", "Family one"),
                new DiscountFamily("F2", "Family two")
        ));

        when(priceService.loadDiscounts(FEDERATED_ID, "FR")).thenReturn(newArrayList(
                new Discount("FR", "1234", "F2", 0.2d, new LocalDate(2014, 1, 1).toDate())
        ));

        Collection<DiscountWithDescription> discounts = discountResource.getDiscountsInJson("FR", TOKEN);
        assertThat(discounts).hasSize(2);
        assertThat(discounts).containsOnly(
                new DiscountWithDescription("F1", null, new LocalDate().toDate(), "Family one"),
                new DiscountWithDescription("F2", 0.2d, new LocalDate(2014, 1, 1).toDate(), "Family two")
        );
    }

}