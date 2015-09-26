insert into PriceList (id, name, description, type, currencyCode, archived) values ('FR', 'FR', 'Prix France', 'PUBLIC', 'EUR', FALSE);
insert into PriceList (id, name, description, type, currencyCode, archived) values ('ES', 'ES', 'Price Spain', 'PUBLIC', 'EUR', FALSE);
insert into PriceList (id, name, description, type, currencyCode, archived) values ('3333', 'My prices', 'User1 Price 1', 'USER', 'EUR', FALSE);
insert into PriceList (id, name, description, type, currencyCode, archived) values ('4444', 'My prices 2', 'User1 Price 2', 'USER', 'EUR', FALSE);
insert into PriceList (id, name, description, type, currencyCode, archived) values ('5555', 'Other prices', 'User Prices', 'USER', 'USD', FALSE);
commit;

insert into PriceRevision (id, priceListId, fromDate, toDate) values (1, 'FR', '2014-07-01 00:00:00', '2015-01-01 00:00:00');
insert into PriceRevision (id, priceListId, fromDate, toDate) values (2, 'FR', '2015-01-01 00:00:00', null);

insert into PriceRevision (id, priceListId, fromDate, toDate) values (3, '3333', '2014-10-01 00:00:00', '2015-03-01 00:00:00');
insert into PriceRevision (id, priceListId, fromDate, toDate) values (4, '3333', '2015-03-01 00:00:00', null);

-- Price FR
-- 2014-07-01 to 2015-01-01
insert into Price (priceRevision_Id, reference, familyCode, value) values (1, '26970', '', 50);
insert into Price (priceRevision_Id, reference, familyCode, value) values (1, '16774', '', 117.96);
insert into Price (priceRevision_Id, reference, familyCode, value) values (1, '36774', '', 12.7);
insert into Price (priceRevision_Id, reference, familyCode, value) values (1, '28267', '', 16.4);
-- 2015-01-01 to 2015-07-31
insert into Price (priceRevision_Id, reference, familyCode, value) values (2, '26970', '', 45);
insert into Price (priceRevision_Id, reference, familyCode, value) values (2, '16774', '', 110.96);
insert into Price (priceRevision_Id, reference, familyCode, value) values (2, '36774', '', 13);
insert into Price (priceRevision_Id, reference, familyCode, value) values (2, '28267', '', 15);
-- Families FR
INSERT INTO DiscountFamily (revision_Id, code, description) VALUES (1, 'F', 'Main Family');
INSERT INTO DiscountFamily (revision_Id, code, description) VALUES (1, 'F1', 'Family 1');
INSERT INTO DiscountFamily (revision_Id, code, description) VALUES (1, 'F2', 'Family 2');
INSERT INTO DiscountFamily (revision_Id, code, description) VALUES (2, 'F', 'Main Family');
INSERT INTO DiscountFamily (revision_Id, code, description) VALUES (2, 'F1', 'Family 1');
INSERT INTO DiscountFamily (revision_Id, code, description) VALUES (2, 'F2', 'Family 2');
INSERT INTO DiscountFamily (revision_Id, code, description) VALUES (2, 'F3', 'Family 3');

-- Price User1
insert into Price (priceRevision_Id, reference, familyCode, value) values (3, '26970', 'F', 50);
insert into Price (priceRevision_Id, reference, familyCode, value) values (3, '16774', 'F1', 117.96);
insert into Price (priceRevision_Id, reference, familyCode, value) values (3, '36774', 'F1', 12.7);
insert into Price (priceRevision_Id, reference, familyCode, value) values (3, '28267', 'F2', 16.4);
insert into Price (priceRevision_Id, reference, familyCode, value) values (4, '26970', 'F', 45);
insert into Price (priceRevision_Id, reference, familyCode, value) values (4, '16774', 'F1', 110.96);
insert into Price (priceRevision_Id, reference, familyCode, value) values (4, '36774', 'F2', 13);
insert into Price (priceRevision_Id, reference, familyCode, value) values (4, '28267', 'F3', 15);

insert into UserAccess (id, federatedId, priceList_id) values (1, '1234', '3333');
insert into UserAccess (id, federatedId, priceList_id) values (2, '1234', '4444');
insert into UserAccess (id, federatedId, priceList_id) values (3, '4321', '5555');

insert into Discount (familyCode, federatedId, priceList, validityStart, validityStop, value) values ('F', '1234', 'FR', '2014-08-01 00:00:00', '2014-10-01 00:00:00', 0.2);
insert into Discount (familyCode, federatedId, priceList, validityStart, validityStop, value) values ('F1', '1234', 'FR', '2014-08-01 00:00:00', '2014-09-01 00:00:00', 0.1);
insert into Discount (familyCode, federatedId, priceList, validityStart, validityStop, value) values ('F2', '1234', 'FR', '2014-08-01 00:00:00', null, 0.35);
insert into Discount (familyCode, federatedId, priceList, validityStart, validityStop, value) values ('F', '1234', 'FR', '2014-10-01 00:00:00', '2015-02-01 00:00:00', 0.25);