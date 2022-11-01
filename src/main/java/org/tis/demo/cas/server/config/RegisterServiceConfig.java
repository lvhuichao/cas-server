package org.tis.demo.cas.server.config;

import javax.annotation.PostConstruct;

import org.apereo.cas.services.RegexRegisteredService;
import org.apereo.cas.services.ServicesManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class RegisterServiceConfig {

    @Autowired
    private ServicesManager servicesManager;

    @PostConstruct
    public void initService() {
        RegexRegisteredService regexRegisteredService = new RegexRegisteredService();
        regexRegisteredService.setServiceId("http://localhost:8081/.*");
        servicesManager.save(regexRegisteredService);

        // local-fe
        RegexRegisteredService regexRegisteredService2 = new RegexRegisteredService();
        regexRegisteredService2.setServiceId("http://localhost:3001/user/auth/cas/redirect.*");
        servicesManager.save(regexRegisteredService2);
        servicesManager.load();
        log.info("registered service: {}", regexRegisteredService.getServiceId());
    }
}
