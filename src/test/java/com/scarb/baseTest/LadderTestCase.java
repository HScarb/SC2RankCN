package com.scarb.baseTest;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Scarb on 9/14/2016.
 */
@ContextConfiguration(locations = {"classpath:app-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public abstract class LadderTestCase extends AbstractJUnit4SpringContextTests{
    protected Logger logger = LoggerFactory.getLogger(getClass());
}
