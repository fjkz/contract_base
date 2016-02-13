package net.fjkz.contract;

import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Abstract base class for creating organized contract-based test cases.
 *
 * @param <T> class type under test
 */
public abstract class ContractBase<T> {
    /** Builder for set precondition */
    protected PreconditionBuilder builder;
    /** Object to be tested */
    protected T tested;

    @Before
    public void setUp() throws Exception {
        cleanUp();
        builder = createPreconditionBuilder();
        commonPreconditions();
    }

    @After
    public void tearDown() throws Exception {
        commonPostconditions();
        invaliants();
        cleanUp();
    }

    /** Create precondition builder class. */
    abstract protected PreconditionBuilder createPreconditionBuilder() throws Exception;

    /**
     * Clean up side effect of the operation.
     * Must be idempotent.
     */
    protected void cleanUp() throws Exception {
        tested = null;
    }

    /**
     * Verify invariants.
     */
    protected abstract void invaliants() throws Exception;

    /**
     * Create common preconditions among test cases.
     * Optionally verify required preconditions are gained.
     */
    protected abstract void commonPreconditions() throws Exception;

    /**
     * Verify common postconditions among test cases.
     */
    protected abstract void commonPostconditions() throws Exception;

    /** Test this class itself */
    @Test
    public void self() throws Exception {
        builder.nop().done();
        assertNotNull(tested);
    }

    /**
     * Builder class create preconditions.
     */
    protected class PreconditionBuilder {
        protected T builded;
        protected ContractBase<T> base;

        public PreconditionBuilder(ContractBase<T> base) {
            this.base = base;
        }

        /**
         * Sample method with no operation.
         * Add method like this for each precondition parameter.
         * @return this
         */
        public PreconditionBuilder nop() {
            return this;
        }

        /**
         * Set the build result to "tested" field and verify invariants.
         * Call this when complete to set preconditions.
         *
         * @return build result
         */
        public T done() throws Exception {
            base.tested = builded;
            base.invaliants();
            return builded;
        }
    }
}
