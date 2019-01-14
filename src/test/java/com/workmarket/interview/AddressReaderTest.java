package com.workmarket.interview;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;

public class AddressReaderTest {

    @Test
    public void testRead() throws  Exception {
        File file = new File("src/test/resources/test-addresses.txt");
        AddressReader addressReader = new AddressReader(file);
        Assert.assertEquals(10, addressReader.getAddresses().size());
        Assert.assertEquals("55 Brooksby Village Way, Danvers MA 1923", addressReader.getAddresses().get(6));
    }

    @Test
    public void testReadFileNotFound() throws  Exception {
        File file = new File("src/test/resources/test-addresses-notfound.txt");
        AddressReader addressReader = new AddressReader(file);
        Assert.assertEquals(0, addressReader.getAddresses().size());
    }
}
