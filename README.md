ContractBase
============

Java class for creating organized constact-based test cases.

Create test-base class for each class under tests by inheliting `ContractBase` class.

```
 [ContractBase] <--+-- [AClassTestBase]
                   |
                   +-- [BClassTestBase]
                   |
                   +-- [CClassTestBase]

```

Test-base classes include

 - `PreconditionBuilder` extention that helps setting precondition parameters

and

 - utility assertion functions that helps evaluating postconsitions.

Create test classes for each operation about a class by inteliting the test-base class.

```
[AClassTestBase] <--+-- [TestAClassMethod1]
                    |
                    +-- [TestAClassMethod2]
                    |
                    +-- [TestAClassMethod3]
```

Test classes have test cases for variations about preconditions and operetion arguments.

Test cases are consisted by 4 phases.

  1. Set preconditions.
  2. Conduct an opeartion.
  3. Evaluate a returned value.
  4. Evaluate postconditions.

`PreconditionBuilder` are used for the phase 1, and utility assertion functions are used for phase 3,4.

License
-------

The MIT License (MIT)

http://opensource.org/licenses/mit-license.php
