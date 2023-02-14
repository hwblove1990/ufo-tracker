package com.ufo.tracker;

import com.ufo.tracker.Utils.TrackerUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TrackerApplicationTests {

    @Test
    void contextLoads() {
        System.err.println(TrackerUtils.bytToLong(  TrackerUtils.shortTobytes2(3123)));

    }

}
