package com.schneider_electric.dces.pricing.dao;

import java.sql.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.LocalDate;

public abstract class AbstractDaoImplTest {

	protected final Log logger = LogFactory.getLog(getClass());
	private static Connection con;

	public AbstractDaoImplTest() throws ClassNotFoundException, SQLException {
		// Dirty populating method, to remove once dbunit is setup
		if (con == null) {
			Class.forName("org.h2.Driver");
			con = DriverManager.getConnection("jdbc:h2:mem:PRICING", "sa", "");

            createTables();

            insertPrices();
            insertFamily();
            insertUserDiscount();
		}
	}

    private void insertFamily() throws SQLException {
        PreparedStatement pstmt = con.prepareStatement("INSERT INTO DiscountFamily (priceList, code, description) VALUES (?, ?, ?)");
        insertFamily(pstmt, "FR", "F", "Main Family");
        insertFamily(pstmt, "FR", "F1", "Family 1");
        insertFamily(pstmt, "FR", "F2", "Family 2");
        insertFamily(pstmt, "EN", "FEN", "Family EN");
    }

    private void insertUserDiscount() throws SQLException {
        PreparedStatement pstmt = con.prepareStatement("INSERT INTO user_discount (federated_id, country_code, family_code, value, validity_start, validity_stop) VALUES (?, ?, ?, ?, ?, ?)");
        insertUserDiscount(pstmt, "001", "FR", "F", 0.2d, new LocalDate(2014, 1, 1), new LocalDate(2014, 6, 1));
        insertUserDiscount(pstmt, "001", "FR", "F2", 0.3d, new LocalDate(2014, 1, 1), new LocalDate(2014, 6, 1));
        insertUserDiscount(pstmt, "002", "FR", "F", 0.1d, new LocalDate(2014, 1, 1), new LocalDate(2014, 6, 1));

        insertUserDiscount(pstmt, "001", "FR", "F", 0.25d, new LocalDate(2014, 6, 1));
        insertUserDiscount(pstmt, "001", "FR", "F2", 0.35d, new LocalDate(2014, 6, 1));
        insertUserDiscount(pstmt, "002", "FR", "F", 0.15d, new LocalDate(2014, 6, 1));
    }

    private void insertPrices() throws SQLException {
        PreparedStatement pstmt = con.prepareStatement("INSERT INTO PRICES (reference_id, family_code, country_code, currency_code, value, validity_start, validity_stop) VALUES (?, ?, ?, ?, ?, ?, ?)");
        insertPrice(pstmt, "26970", "F", "FR", "EUR", 50, new LocalDate(2014, 1, 1), new LocalDate(2014, 6, 1));
        insertPrice(pstmt, "16774", "F", "FR", "EUR", 117.96, new LocalDate(2014, 1, 1), new LocalDate(2014, 6, 1));
        insertPrice(pstmt, "36774", "F", "FR", "DOL", 12.7, new LocalDate(2014, 1, 1), new LocalDate(2014, 6, 1));
        insertPrice(pstmt, "28267", "F", "US", "DOL", 16.4, new LocalDate(2014, 1, 1), new LocalDate(2014, 6, 1));

        insertPrice(pstmt, "26970", "F", "FR", "EUR", 60, new LocalDate(2014, 6, 1));
        insertPrice(pstmt, "16774", "F", "FR", "EUR", 127.96, new LocalDate(2014, 6, 1));
        insertPrice(pstmt, "36774", "F", "FR", "DOL", 22.7, new LocalDate(2014, 6, 1));
        insertPrice(pstmt, "28267", "F", "US", "DOL", 26.4, new LocalDate(2014, 6, 1));
    }

    private void createTables() throws SQLException {
        Statement sst = con.createStatement();

        String createTable = "CREATE TABLE IF NOT EXISTS PRICES (REFERENCE_ID VARCHAR(100), FAMILY_CODE VARCHAR(100), COUNTRY_CODE VARCHAR(100), CURRENCY_CODE VARCHAR(3), VALUE FLOAT, VALIDITY_START DATE NOT NULL DEFAULT '1970-01-01', VALIDITY_STOP DATE NULL DEFAULT NULL)";
        sst.executeUpdate(createTable);

        createTable = "CREATE TABLE IF NOT EXISTS family_discount\n" +
            "(\n" +
            "  country_code VARCHAR(255) NOT NULL,\n" +
            "  code         VARCHAR(255) NOT NULL,\n" +
            "  description  VARCHAR(1000),\n" +
            "  PRIMARY KEY (country_code, code)\n" +
            ")";
        sst.executeUpdate(createTable);

        createTable = "CREATE TABLE IF NOT EXISTS user_discount\n" +
            "(\n" +
            "  id BIGINT NOT NULL AUTO_INCREMENT,\n" +
            "  federated_id VARCHAR(255) NOT NULL,\n" +
            "  country_code VARCHAR(255) NOT NULL,\n" +
            "  family_code  VARCHAR(255) NOT NULL,\n" +
            "  validity_start DATE NOT NULL DEFAULT '1970-01-01',\n" +
            "  validity_stop DATE NULL DEFAULT NULL,\n" +
            "  value  DOUBLE UNSIGNED NOT NULL,\n" +
            "  PRIMARY KEY (id)\n" +
            ")";
        sst.executeUpdate(createTable);
    }

    private void insertFamily(PreparedStatement pstmt, String priceList, String familyCode, String description) throws SQLException {
        pstmt.setString(1, priceList);
        pstmt.setString(2, familyCode);
        pstmt.setString(3, description);
        pstmt.executeUpdate();
    }

    private void insertUserDiscount(PreparedStatement pstmt, String federatedId, String countryCode, String familyCode, double value, LocalDate from) throws SQLException {
        insertUserDiscount(pstmt, federatedId, countryCode, familyCode, value, from, null);
    }

    private void insertUserDiscount(PreparedStatement pstmt, String federatedId, String countryCode, String familyCode, double value, LocalDate from, LocalDate to) throws SQLException {
        pstmt.setString(1, federatedId);
        pstmt.setString(2, countryCode);
        pstmt.setString(3, familyCode);
        pstmt.setDouble(4, value);
        pstmt.setDate(5, new java.sql.Date(from.toDate().getTime()));
        if (to != null) {
            pstmt.setDate(6, new java.sql.Date(to.toDate().getTime()));
        } else {
            pstmt.setNull(6, java.sql.Types.DATE);
        }
        pstmt.executeUpdate();
    }

    private void insertPrice(PreparedStatement pstmt, String ref, String family, String country, String currency, double value, LocalDate from) throws SQLException {
        insertPrice(pstmt, ref, family, country, currency, value, from, null);
    }

    private void insertPrice(PreparedStatement sst, String ref, String family, String ctry, String currency, double value, LocalDate from, LocalDate to) throws SQLException {
        sst.setString(1, ref);
        sst.setString(2, family);
        sst.setString(3, ctry);
        sst.setString(4, currency);
        sst.setDouble(5, value);
        sst.setDate(6, new java.sql.Date(from.toDate().getTime()));
        if (to != null) {
            sst.setDate(7, new java.sql.Date(to.toDate().getTime()));
        } else {
            sst.setNull(7, java.sql.Types.DATE);
        }
        sst.executeUpdate();
    }
}
