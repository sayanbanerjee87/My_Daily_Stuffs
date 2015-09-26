
    create table Discount (
        id bigint not null auto_increment,
        familyCode varchar(255),
        federatedId varchar(255),
        priceList varchar(255),
        validityStart datetime,
        validityStop datetime,
        value double precision,
        primary key (id)
    ) ENGINE INNO_DB CHARACTER SET utf8 COLLATE utf8_general_ci;

    create table DiscountFamily (
        id bigint not null auto_increment,
        code varchar(255),
        description varchar(255),
        revision_id bigint,
        primary key (id)
    ) ENGINE INNO_DB CHARACTER SET utf8 COLLATE utf8_general_ci;

    create table Price (
        id bigint not null auto_increment,
        familyCode varchar(255),
        reference varchar(255) not null,
        value double precision not null,
        priceRevision_id bigint not null,
        primary key (id)
    ) ENGINE INNO_DB CHARACTER SET utf8 COLLATE utf8_general_ci;

    create table PriceList (
        id varchar(255) not null,
        archived bit not null,
        currencyCode varchar(255) not null,
        description varchar(255),
        name varchar(255) not null,
        type varchar(255) not null,
        primary key (id)
    ) ENGINE INNO_DB CHARACTER SET utf8 COLLATE utf8_general_ci;

    create table PriceRevision (
        id bigint not null auto_increment,
        fromDate datetime not null,
        toDate datetime,
        priceListId varchar(255) not null,
        primary key (id)
    ) ENGINE INNO_DB CHARACTER SET utf8 COLLATE utf8_general_ci;

    create table UserAccess (
        id bigint not null auto_increment,
        federatedId varchar(255) not null,
        priceList_id varchar(255) not null,
        primary key (id)
    ) ENGINE INNO_DB CHARACTER SET utf8 COLLATE utf8_general_ci;

    alter table Discount 
        add constraint discount_unique_for_federatedid_pricelist_family_validityStart  unique (federatedId, priceList, familyCode, validityStart);

    create index discount_idx on Discount (federatedId, priceList, familyCode, validityStart, validityStop);

    alter table DiscountFamily 
        add constraint discount_family_unique_for_revision_code  unique (revision_id, code);

    alter table Price 
        add constraint price_unique_for_revision_ref  unique (priceRevision_id, reference);

    create index pricelist_idx on PriceList (id, type);

    alter table PriceRevision 
        add constraint revision_unique_for_pricelist_fromDate  unique (priceListId, fromDate);

    create index revision_date_idx on PriceRevision (priceListId, fromDate, toDate);

    alter table UserAccess 
        add constraint user_access_unique_for_pricelist_userid  unique (priceList_id, federatedId);

    alter table DiscountFamily 
        add constraint FK_l8kxql4icunk3gu2eoxtaj7na 
        foreign key (revision_id) 
        references PriceRevision (id);

    alter table Price 
        add constraint FK_i40vb5953amxoqca01nh0x1gv 
        foreign key (priceRevision_id) 
        references PriceRevision (id);

    alter table PriceRevision 
        add constraint FK_dnfi1l075uaidosjx16nqvpdo 
        foreign key (priceListId) 
        references PriceList (id);

    alter table UserAccess 
        add constraint FK_onx4fiveg1843b1v30f2glyl6 
        foreign key (priceList_id) 
        references PriceList (id);
