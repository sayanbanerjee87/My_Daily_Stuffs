package com.schneider_electric.dces.bom.neutralFile.extension;

import com.schneider_electric.electrical_distribution.exchange_format.ElectricalProject;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

@Provider
public class ElectricalNetworkJAXBContextProvider implements ContextResolver<JAXBContext> {
    private JAXBContext context = null;

    @Override
    public JAXBContext getContext(Class<?> aClass) {
        if (aClass != ElectricalProject.class) {
            return null;
        }

        if (context == null) {
            try {
                context = JAXBContext.newInstance(ElectricalProject.class, BomTagAttribute.class);
            } catch (JAXBException e) {
                e.printStackTrace();
            }
        }
        return context;
    }
}
