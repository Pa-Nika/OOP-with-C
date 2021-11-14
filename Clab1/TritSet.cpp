#include "TritSet.h"

void TritSet::copyParameter(const TritSet& new_trit) {
    this->size = new_trit.size;
    this->basic_size = new_trit.basic_size;
    this->first_install_size = new_trit.first_install_size;
    this->true_count = new_trit.true_count;
    this->false_count = new_trit.false_count;
    this->last_index_trit = new_trit.last_index_trit;
}

TritSet::TritSet(size_t countOfTrit) {
    this->size = countOfTrit;
    this->first_install_size = countOfTrit;
    this->basic_size = sizeUint(countOfTrit);
    this->false_count = this->true_count = INITIAL_COUNT;
    this->last_index_trit = INITIAL_INDEX;

    if (this->basic_size != EMPTY_MEMORY)
        this->set = new uint[this->basic_size];
    else
        this->set = nullptr;
    memset (this->set, 0u, this->basic_size);
}

TritSet::TritSet(const TritSet &new_trit) {
    this->copyParameter(new_trit);

    this->set = new uint[basic_size];
    memmove (this->set, new_trit.set, basic_size * sizeof(uint));
}

TritSet::TritSet(TritSet &&new_trit) noexcept{
    this->copyParameter(new_trit);

    this->set = new_trit.set;
    new_trit.set = nullptr;
}

TritSet::~TritSet() {
    delete [] this->set;
}

TritSet::Proxy::Proxy(size_t index, TritSet *set) {
    this->set_proxy = set;
    this->index_proxy = index;

    uint set_byte = index * COUNT_TRIT_IN_BIT / BYTE_IN_UINT;

    if (index > set->capacity())
        this->trit_proxy = Unknown;

    else {
        size_t index_bit = BYTE_IN_UINT - ((index * COUNT_TRIT_IN_BIT) % BYTE_IN_UINT) - COUNT_TRIT_IN_BIT;
        uint first_bit = set_proxy->set[set_byte] & ((uint)1 << index_bit);
        uint second_bit = set_proxy->set[set_byte] & ((uint)1 << (index_bit + 1));

        this->trit_proxy = Trit((first_bit + second_bit) >> index_bit);
    }
}

size_t TritSet::capacity() const {
    return this->size;
}

uint TritSet::sizeUint(const size_t size) {
    return ceil((double)size * COUNT_TRIT_IN_BIT / BYTE_IN_UINT);
}

void TritSet::changeSize(size_t new_size_trit) {
    this->size = new_size_trit;
    uint now_size = sizeUint(new_size_trit);
    uint* temp_set;

    if (this->basic_size != EMPTY_MEMORY)
        temp_set = new uint[now_size + ADDITIONAL_MEMORY];
    else
        temp_set = nullptr;

    memset (temp_set, 0u, now_size + ADDITIONAL_MEMORY);
    memmove (temp_set, this->set, this->basic_size * sizeof(uint));

    delete [] this->set;
    this->set = temp_set;
    this->basic_size = sizeUint(new_size_trit) + ADDITIONAL_MEMORY;
}

void TritSet::compareSize(TritSet &first, TritSet &second) {
    if (first.size > second.size)
        second.changeSize(first.size);
    else if (first.size < second.size)
        first.changeSize(second.size);
}

void TritSet::countTrit(Trit my_set, size_t index_trit) {
    if (my_set == False) {
        this->false_count++;
        this->last_index_trit = index_trit;
    }
    if (my_set == True) {
        this->true_count++;
        this->last_index_trit = index_trit;
    }
}

uint TritSet::getCountFalse() const {
    return this->false_count;
}

uint TritSet::getCountTrue() const {
    return this->true_count;
}

size_t TritSet::cardinality(Trit value) const {
    if (value == True)
        return this->true_count;
    if (value == False)
        return this->false_count;
    return this->last_index_trit + ADDITIONAL_INDEX - this->false_count - this->true_count;
}

size_t TritSet::length() const {
    return this->last_index_trit + ADDITIONAL_INDEX;
}

void TritSet::trim(size_t last_index) {
    for (size_t i = last_index; i < this->capacity(); i++)
        (*this)[i] = Unknown;
}

std::unordered_map<Trit, int, std::hash<int> > TritSet::cardinality() const {
    std::unordered_map <Trit, int, std::hash<int>> result = {
            {True, this->getCountTrue()},
            {False, this->getCountFalse()},
            {Unknown, this->cardinality(Unknown)}
    };

    return result;
}

void TritSet::shrink() {
    this->basic_size = sizeUint(this->first_install_size);
    this->size = this->first_install_size;

    uint *temp_set;

    if (this->basic_size != EMPTY_MEMORY)
        temp_set = new uint[this->basic_size];
    else {
        temp_set = nullptr;
    }
    memset(temp_set, 0u, this->basic_size);
    memmove(temp_set, this->set, this->basic_size * sizeof(uint));
    delete[] this->set;
    this->set = temp_set;
}

TritSet::Proxy TritSet::operator[](size_t index) {
    return {index, this};
}

TritSet TritSet::operator&(TritSet &new_trit) {

    compareSize(new_trit, *this);

    TritSet result(new_trit.size);
    Trit first, second;

    for (uint i = 0; i < new_trit.size; i++) {
        first = (*this)[i];
        second = new_trit[i];

        result[i] = first & second;
        countTrit(result[i], i);
    }

    return result;
}

TritSet TritSet::operator|(TritSet &new_trit) {

    compareSize(new_trit, *this);

    TritSet result(new_trit.size);
    Trit first, second;

    for (uint i = 0; i < new_trit.size; i++) {
        first = (*this)[i];
        second = new_trit[i];

        result[i] = first | second;
        countTrit(result[i], i);
    }

    return result;
}

TritSet TritSet::operator~() {
    TritSet result (this->size);
    Trit first;

    for (uint i = 0; i < result.size; i++) {
        first = (*this)[i];
        result[i] = ~first;
        countTrit(result[i], i);
    }

    return result;
}

TritSet::Proxy::operator Trit() const {
    return this->trit_proxy;
}

Trit TritSet::Proxy::operator=(Trit value) {
    //need to allocate memory
    if (this->index_proxy > this->set_proxy->capacity() && value != Unknown) {
        this->set_proxy->changeSize(this->index_proxy);
    }

    //don't need to allocate memory
    if (this->index_proxy <= this->set_proxy->capacity()) {
        //the position of the desired trit in the set
        uint trit_position = BYTE_IN_UINT - (this->index_proxy * COUNT_TRIT_IN_BIT % BYTE_IN_UINT) - COUNT_TRIT_IN_BIT;
        // write the value to set
        // zero first, then assign |
        // set to zero: ~ (triple shifted to the left by the index of the trit)
        this->set_proxy->set[index_proxy * COUNT_TRIT_IN_BIT / BYTE_IN_UINT] =
                this->set_proxy->set[index_proxy * COUNT_TRIT_IN_BIT / BYTE_IN_UINT] &
                (~(3u << trit_position)) |
                ((uint)value << trit_position);
    }

    this->set_proxy->countTrit(value, index_proxy);
    return value;
}

TritSet::Proxy &TritSet::Proxy::operator=(const TritSet::Proxy &new_trit) {
    if (this == &new_trit)
        return *this;

    //need to allocate memory
    if (this->index_proxy > this->set_proxy->capacity() && new_trit.trit_proxy != Unknown) {
        this->set_proxy->changeSize(this->index_proxy);
    }

    //don't need to allocate memory
    if (this->index_proxy <= this->set_proxy->capacity()) {

        uint trit_position = BYTE_IN_UINT - (this->index_proxy * COUNT_TRIT_IN_BIT % BYTE_IN_UINT) - COUNT_TRIT_IN_BIT;

        this->set_proxy->set[index_proxy * COUNT_TRIT_IN_BIT / BYTE_IN_UINT] =
                this->set_proxy->set[index_proxy * COUNT_TRIT_IN_BIT / BYTE_IN_UINT] &
                (~(3u << trit_position)) |
                ((uint) new_trit.trit_proxy << trit_position);
    }

    this->set_proxy->countTrit(new_trit.trit_proxy, index_proxy);
    return *this;
}

Trit TritSet::operator[](size_t index) const {
    uint set_byte = index * COUNT_TRIT_IN_BIT / BYTE_IN_UINT;

    size_t index_bit = BYTE_IN_UINT - ((index * COUNT_TRIT_IN_BIT) % BYTE_IN_UINT) - COUNT_TRIT_IN_BIT;
    uint first_bit = this->set[set_byte] & ((uint)1 << index_bit);
    uint second_bit = this->set[set_byte] & ((uint)1 << (index_bit + 1));

    return Trit((first_bit + second_bit) >> index_bit);
}

TritSet::Iterator TritSet::begin() {
    return TritSet::Iterator(this, 0);
}

TritSet::Iterator TritSet::end() {
    return TritSet::Iterator(this, this->size);
}

TritSet::Iterator::Iterator(TritSet* my_set, size_t index) {
    this->set_iterator = my_set;
    this->index_iterator = index;
}

TritSet::Iterator TritSet::Iterator::operator++() {
    this->index_iterator ++;

    return TritSet::Iterator(this->set_iterator, index_iterator - 1);
}

TritSet::Iterator TritSet::Iterator::operator--() {
    this->index_iterator --;

    return TritSet::Iterator(this->set_iterator , index_iterator + 1);
}

bool TritSet::Iterator::operator==(const TritSet::Iterator &it) const {
    if (this->set_iterator == it.set_iterator && this->index_iterator == it.index_iterator)
        return true;

    return false;
}

bool TritSet::Iterator::operator!=(const TritSet::Iterator &it) const {
    if (*this == it)
        return false;

    return true;
}

TritSet::Proxy TritSet::Iterator::operator*() {
    return (*this->set_iterator)[index_iterator];
}
