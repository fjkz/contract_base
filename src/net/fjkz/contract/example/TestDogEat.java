package net.fjkz.contract.example;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestDogEat extends DogTestBase {

    @Override
    protected void commonPreconditions() throws Exception {}

    @Override
    protected void commonPostconditions() throws Exception {};

    @Test
    public void neutralOneFood() throws Exception {
        builder().power(5).done();

        int remain = tested.eat(1);

        assertEquals(0, remain);
        assertEquals(6, tested.getPower());
        assertToString(tested, "Hachi", 6);
    }

    @Test
    public void neutralZeroFood() throws Exception {
        builder().power(5).done();

        int remain = tested.eat(0);

        assertEquals(0, remain);
        assertEquals(5, tested.getPower());
        assertToString(tested, "Hachi", 5);
    }

    @Test
    public void neutralMaxFood() throws Exception {
        builder().power(5).done();

        int remain = tested.eat(Dog.MAX_POWER);

        assertEquals(5, remain);
        assertEquals(Dog.MAX_POWER, tested.getPower());
        assertToString(tested, "Hachi", Dog.MAX_POWER);
    }

    @Test(expected=IllegalArgumentException.class)
    public void nagativeFood() throws Exception {
        builder().done();
        tested.eat(-1);
    }

    @Test
    public void emptyOneFood() throws Exception {
        builder().power(0).done();

        int remain = tested.eat(1);

        assertEquals(0, remain);
        assertEquals(1, tested.getPower());
        assertToString(tested, "Hachi", 1);
    }

    @Test
    public void emptyZeroFood() throws Exception {
        builder().power(0).done();

        int remain = tested.eat(0);

        assertEquals(0, remain);
        assertEquals(0, tested.getPower());
        assertToString(tested, "Hachi", 0);
    }

    @Test
    public void emptyMaxFood() throws Exception {
        builder().power(0).done();

        int remain = tested.eat(Dog.MAX_POWER);

        assertEquals(0, remain);
        assertEquals(Dog.MAX_POWER, tested.getPower());
        assertToString(tested, "Hachi", Dog.MAX_POWER);
    }

    @Test
    public void sutisfiedOneFood() throws Exception {
        builder().power(Dog.MAX_POWER).done();

        int remain = tested.eat(1);

        assertEquals(1, remain);
        assertEquals(Dog.MAX_POWER, tested.getPower());
        assertToString(tested, "Hachi", Dog.MAX_POWER);
    }

    @Test
    public void sutisfiedZeroFood() throws Exception {
        builder().power(Dog.MAX_POWER).done();

        int remain = tested.eat(0);

        assertEquals(0, remain);
        assertEquals(Dog.MAX_POWER, tested.getPower());
        assertToString(tested, "Hachi", Dog.MAX_POWER);
    }

    @Test
    public void sutisfiedMaxFood() throws Exception {
        builder().power(Dog.MAX_POWER).done();

        int remain = tested.eat(Dog.MAX_POWER);

        assertEquals(Dog.MAX_POWER, remain);
        assertEquals(Dog.MAX_POWER, tested.getPower());
        assertToString(tested, "Hachi", Dog.MAX_POWER);
    }
}
