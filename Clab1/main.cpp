#include <iostream>
#include "TritSet.h"
#include <gtest/gtest.h>

TEST(BigTest, ConditionTest){

    TritSet set(1000);
    size_t allocLength = set.capacity();
    ASSERT_GE(allocLength, 1000 * 2 / 8 / sizeof(uint) ); //>=

    //do not allocate memory
    set[1000000000] = Unknown;
    ASSERT_EQ(allocLength, set.capacity()); //==

    //do not allocate memory
    if(set[2000000000]==True){}
    ASSERT_EQ(allocLength, set.capacity());

    //allocate memory
    set[1000000000] = True;
    ASSERT_LT(allocLength, set.capacity()); //<

    allocLength = set.capacity();
    set[1000000000] = Unknown;
    set[1000000] = False;
    ASSERT_EQ(allocLength, set.capacity());

    set.shrink();
    ASSERT_GT(allocLength, set.capacity()); //>

    TritSet setA(1000);
    TritSet setB(2000);
    TritSet setC = setA & setB;
    ASSERT_EQ(setC.capacity(), setB.capacity());
}


TEST (SecondTest, TestOfOperotor) {

    TritSet set(0);
    ASSERT_EQ (set.capacity(), 0);

    TritSet setA(100);
    TritSet setB(setA);
    ASSERT_EQ(setA.capacity(), setB.capacity());

    size_t allocLength = setB.capacity();
    setA[10] = setB[150] = True;
    ASSERT_EQ (setA[10], True);
    ASSERT_EQ (setB[150], True);
    ASSERT_LT(allocLength, setB.capacity());

    setA[20] = True;
    Trit a = setA[20];
    ASSERT_EQ (a, True);

    setB[1] = True;
    setA[1] = False;
    setB[150] = setA[1];
    ASSERT_EQ (setA[1], False);
    ASSERT_EQ (setB[150], False);
}


TEST (ThirdTest, ShrinkMethod) {

    TritSet setA(100);
    size_t allocLength = setA.capacity();

    setA.shrink();
    ASSERT_EQ (allocLength, setA.capacity());

    setA[113] = False;
    setA[15] = False;
    ASSERT_LT (allocLength, setA.capacity());

    setA.shrink();
    ASSERT_EQ (allocLength, setA.capacity());
    ASSERT_EQ (setA[15], False);

    setA[10000] = Unknown;
    ASSERT_EQ (allocLength, setA.capacity());
}

TEST (FourthTest, AdditionalMetgods) {

    TritSet setA(100);
    setA[69] = setA[70] = False;
    ASSERT_EQ (setA.cardinality(False), 2);
    ASSERT_EQ (setA.cardinality(Unknown), 69 - 1);

    setA.trim(70);
    ASSERT_EQ (setA[70], Unknown);
    ASSERT_EQ (setA[69], False);

    ASSERT_EQ (setA.length(), 69 + 1);

    setA[1000] = True;
    ASSERT_EQ (setA.length(), 1000 + 1);

    setA[10000] = Unknown;
    ASSERT_EQ (setA.length(), 1000 + 1);
}

TEST (FifthTest, SetOperations) {

    TritSet A(100);
    for (int i = 0; i < 100; i++) {
        A[i] = True;
    }

    TritSet B(200);
    for (int i = 0; i < 200; i++) {
        B[i] = False;
    }

    TritSet C = A & B;
    ASSERT_EQ (C.cardinality(False), 200);
    ASSERT_EQ (C.capacity(), B.capacity());


    for (int i = 10; i < 200; i++) {
        C[i] = True;
    }

    TritSet D = ~C;
    ASSERT_EQ (D.cardinality(False), 190);

    TritSet E = C | B;
    ASSERT_EQ (E.cardinality(False), 10);
}

TEST (SixthTest, IteratorTest) {
    TritSet setA(100);

    for(auto it1 : setA){
        it1 = True;
    }

    for (auto it1 : setA) {
        ASSERT_EQ (it1, True);
    }
    ASSERT_EQ (setA[0], True);
    ASSERT_EQ (setA[99], True);
}

TritSet func(TritSet my_trit) {

    return my_trit;
}

TEST (SeventhTest, Test) {
    TritSet setA(100);
    setA[0] = False;
    //test for Trit operator[] (...)
    const auto c = setA;
    ASSERT_EQ (c[0], False);

    //test for move constructor
    TritSet setB = func(setA);
    ASSERT_EQ (setA.capacity(), setB.capacity());
}
