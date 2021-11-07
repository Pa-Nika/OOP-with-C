#ifndef CLAB1_TRITSET_H
#define CLAB1_TRITSET_H
#include <cmath>
#include <iostream>
#include <cstring>
#include <algorithm>
#include "Consts.h"
#include "Trit.h"
#include <unordered_map>

#define uint unsigned int

class TritSet {
private:
    uint size{};
    uint* set;
    uint basic_size{};    //uint amount
    uint first_install_size{}; //parameter for shrink
    uint false_count{};
    uint true_count{};
    uint last_index_trit{};

    static uint sizeUint (size_t size);
    void changeSize (size_t new_size);
    static void compareSize (TritSet& first, TritSet& second);
    void countTrit (Trit my_set, size_t index_trit);
    void copyParameter (const TritSet& new_trit);

    class Proxy {
    private:
        TritSet* set_proxy;                             //a pointer to an array of trites that called the proxy
        size_t index_proxy;                             //the index to write to
        Trit trit_proxy;                                //remembers which Trit summoned it
    public:
        Proxy(size_t index, TritSet* set);
        operator Trit () const;
        Trit operator= (Trit value);
        Proxy& operator= (const Proxy& new_trit);
    };

public:
    [[nodiscard]] size_t capacity() const;
    [[nodiscard]] uint getCountFalse () const;
    [[nodiscard]] uint getCountTrue () const;
    [[nodiscard]] size_t cardinality (Trit value) const;
    [[nodiscard]] size_t length() const;

    void trim (size_t last_index);
    void shrink();
    [[nodiscard]] std::unordered_map< Trit, int, std::hash<int> > cardinality() const;

    Proxy operator[] (size_t index);
    TritSet operator& (TritSet& new_trit);
    TritSet operator| (TritSet& new_trit);
    TritSet operator~ ();

    explicit TritSet(size_t size);
    TritSet(const TritSet& new_trit);
    TritSet (TritSet&& new_trit) noexcept;
    ~TritSet();
};

#endif //CLAB1_TRITSET_H
