package org.junit.contract;

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
    }

    @After
    public void tearDown() throws Exception {
        commonPostconditions();
        invaliants();
        cleanUp();
    }

    /** Create precondition builder class. */
    abstract protected PreconditionBuilder createPreconditionBuilder();

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
        builder.done();
        assertNotNull(tested);
    }

    /**
     * Builder class create preconditions.
     * Add method for each precondition parameter.
     */
    protected class PreconditionBuilder {
        protected T builded;
        protected ContractBase<T> base;

        public PreconditionBuilder(ContractBase<T> base) {
            this.base = base;
        }

        /**
         * Complete to set preconditions.
         */
        public void done() throws Exception {
            base.tested = this.builded;
            base.commonPreconditions();
            base.invaliants();
        }
    }
}
