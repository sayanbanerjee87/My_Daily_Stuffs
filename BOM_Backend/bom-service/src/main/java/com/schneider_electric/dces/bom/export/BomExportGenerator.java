package com.schneider_electric.dces.bom.export;

import com.schneider_electric.dces.bom.domain.BomGroup;
import com.schneider_electric.dces.bom.domain.BomViewHeader;
import com.schneider_electric.dces.bom.domain.HeaderColumn;
import com.schneider_electric.dces.bom.domain.Product;
import org.apache.commons.beanutils.PropertyUtilsBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xnap.commons.i18n.I18n;
import org.xnap.commons.i18n.I18nFactory;

import java.lang.reflect.InvocationTargetException;
import java.text.MessageFormat;
import java.util.Locale;

/**
 * User: FDU3285
 * Date: 30/10/2014
 * Time: 08:27
 */
public class BomExportGenerator {

    protected static final Logger LOG = LoggerFactory.getLogger(BomExportGenerator.class);

    protected final BomViewHeader header;
    private final I18n i18n;

    public BomExportGenerator(BomViewHeader header) {
        this.header = header;
        Locale locale = Locale.forLanguageTag(header.getLanguage());
        this.i18n = I18nFactory.getI18n(getClass(), locale);
    }

    protected Object getValueForColumn(Product product, HeaderColumn column) {
        PropertyUtilsBean propertyUtilsBean = new PropertyUtilsBean();
        Object property = null;
        try {
            property = propertyUtilsBean.getProperty(product, column.id);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            LOG.debug("Undefined property " + column.id, e);
        }
        if (property == null && product.any() != null) {
            property = product.any().get(column.id);
        }
        return property;
    }

    protected String getGroupValue(BomGroup group) {
        String groupValue = group.getValue();
        if (BomGroup.BOM_EXPORT_OTHERS.equals(groupValue)) {
            return translate("Others");
        }
        return groupValue;
    }

    protected String translate(String key, Object parameter) {
        String message = header.messages.get(key);
        if (message != null) {
            return MessageFormat.format(message, parameter);
        }
        return this.i18n.tr(key, parameter);
    }

    protected String translate(String key) {
        String message = header.messages.get(key);
        if (message != null) {
            return message;
        }
        return this.i18n.tr(key);
    }
}
