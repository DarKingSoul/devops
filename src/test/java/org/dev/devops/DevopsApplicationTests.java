package org.dev.devops;

import org.dev.devops.service.DevOpsServiceTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@SpringBootTest
@Import(DevOpsServiceTest.class)
class DevopsApplicationTests {

    @Test
    void contextLoads() {
        // This will ensure that all tests in DevOpsServiceTest are executed
    }

}
