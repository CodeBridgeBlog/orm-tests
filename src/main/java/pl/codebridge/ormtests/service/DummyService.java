package pl.codebridge.ormtests.service;

import org.springframework.stereotype.Service;

@Service
public class DummyService {

    public DummyService() {
        throw new RuntimeException("The service cannot be created");
    }

}
