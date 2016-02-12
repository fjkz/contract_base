package org.junit.contract.example;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestDogBark extends DogTestBase {

    @Override
    protected void commonPreconditions() throws Exception {}

    @Override
    protected void commonPostconditions() throws Exception {}

    @Test
    public void neutral() throws Exception {
        builder().power(5).done();

        String b = tested.bark();

        assertEquals("WOOF WOOF", b);
        assertEquals(4, tested.getPower());
    }

    @Test
    public void empty() throws Exception {
        builder().power(0).done();

        String b = tested.bark();

        assertEquals("", b);
        assertEquals(0, tested.getPower());
    }

    @Test
    public void last() throws Exception {
        builder().power(1).done();

        String b = tested.bark();

        assertEquals("WOOF WOOF", b);
        assertEquals(0, tested.getPower());
    }
}
