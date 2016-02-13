package net.fjkz.contract.example;

import static org.junit.Assert.*;

import net.fjkz.contract.ContractBase;

public abstract class DogTestBase extends ContractBase<Dog> {

    // for hiding cast
    protected DogPreconditionBuilder builder() {
        return (DogPreconditionBuilder) builder;
    }

    @Override
    protected ContractBase<Dog>.PreconditionBuilder createPreconditionBuilder() throws Exception {
        return new DogPreconditionBuilder(this);
    }

    @Override
    protected void commonPreconditions() throws Exception {
        // fix the name to Hachi.
        builder().name("Hachi");
    }

    @Override
    protected void commonPostconditions() throws Exception {}

    @Override
    protected void invaliants() throws Exception {
        assertEquals("Hachi", tested.getName());
        assertEquals(10, Dog.MAX_POWER);
        assertTrue(tested.getPower() <= Dog.MAX_POWER);
        assertTrue(tested.getPower() >= 0);
    }

    class DogPreconditionBuilder extends PreconditionBuilder {

        public DogPreconditionBuilder(ContractBase<Dog> base) throws Exception {
            super(base);
        }

        public DogPreconditionBuilder name(String name) {
            builded = new Dog(name);
            return this;
        }

        public DogPreconditionBuilder power(int p) {
            int before = builded.getPower();
            if (before > p) {
                for (int i = 0; i < before - p; i++) {
                    builded.bark();
                }
            } else {
                builded.eat(p - builded.getPower());
            }
            assertEquals(p, builded.getPower());
            return this;
        }
    }

    static void assertToString(Dog tested, String name, int power) {
        assertEquals("name: " + name + ", power: " + power, tested.toString());
    }
}
