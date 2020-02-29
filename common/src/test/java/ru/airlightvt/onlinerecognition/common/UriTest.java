package ru.airlightvt.onlinerecognition.common;

import org.junit.Assert;
import org.junit.Test;

import java.net.URI;

public class UriTest {
    /**
     * Test extracting different parts of given URI for MQ channel URI
     */
    @Test
    public void testExtractTopicName() {
        URI uri = URI.create("amq://TEST.INPUT");
        Assert.assertEquals("schemas are not equals", "amq", uri.getScheme());
        Assert.assertEquals("paths are not equals", "", uri.getPath());
        Assert.assertEquals("hosts are not equals", "TEST.INPUT", uri.getHost());
    }
}
