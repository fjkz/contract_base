package org.junit.contract.example;

import static org.junit.Assert.*;

import org.junit.contract.ContractBase;

public abstract class DogTestBase extends ContractBase<Dog> {

    // for hiding cast
    protected DogPreconditionBuilder builder() {
        return (DogPreconditionBuilder) builder;
    };

    @Override
    protected ContractBase<Dog>.PreconditionBuilder createPreconditionBuilder() {
        return new DogPreconditionBuilder(this);
    }

    @Override
    protected void invaliants() throws Exception {
        assertEquals("Hachi", tested.getName());
        assertEquals(10, Dog.MAX_POWER);
        assertTrue(tested.getPower() <= Dog.MAX_POWER);
        assertTrue(tested.getPower() >= 0);
    }

    class DogPreconditionBuilder extends PreconditionBuilder {

        public DogPreconditionBuilder(ContractBase<Dog> base) {
            super(base);
            // fix the name to Hachi.
            builded = new Dog("Hachi");
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
