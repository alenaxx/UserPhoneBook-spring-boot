package com.example.order.dao;

import com.example.order.model.PhoneRecord;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public interface PhoneRecordDao {

    void addPhoneBook(UUID userId, UUID id, PhoneRecord phoneBook);

    default PhoneRecord addPhoneBook(UUID userId, PhoneRecord phoneBook) {
        UUID id = UUID.randomUUID();
        addPhoneBook(userId, id, phoneBook);
        return phoneBook;
    }

    List<PhoneRecord> getAllPhoneBook(UUID userId);

    Optional<PhoneRecord> getPhoneBookById(UUID userId, UUID id);

    int deletePhoneBookById(UUID userId, UUID id);

    void updatePhoneBookById(UUID userId, UUID id, PhoneRecord PhoneBook);

    Optional<PhoneRecord> getPhoneBookByNumber(UUID userId, String number);

}
