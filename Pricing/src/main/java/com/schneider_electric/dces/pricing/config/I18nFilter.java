package com.schneider_electric.dces.pricing.config;

import com.schneider_electric.dces.pricing.i18n.I18nProvider;
import org.springframework.stereotype.Component;
import org.xnap.commons.i18n.I18n;
import org.xnap.commons.i18n.I18nFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

@Component
public class I18nFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        I18nProvider.set(getI18nInstanceForRequest(request));
        chain.doFilter(request, response);
	}

    public I18n getI18nInstanceForRequest(ServletRequest request) {
        List<Locale> locales = Collections.list(request.getLocales());
        for (Locale locale: locales) {
            ResourceBundle resourceBundle = ResourceBundle.getBundle("i18n.Messages", locale);
            if (resourceBundle.getLocale().getLanguage() == null || !resourceBundle.getLocale().getLanguage().equals(locale.getLanguage())) {
                continue;
            }
            return I18nFactory.getI18n(getClass(), resourceBundle.getLocale());
        }

        return I18nFactory.getI18n(getClass());
    }

	public void init(FilterConfig filterConfig) {}

	public void destroy() {}

}