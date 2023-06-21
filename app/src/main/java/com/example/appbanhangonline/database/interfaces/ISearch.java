package com.example.appbanhangonline.database.interfaces;

import java.util.List;

public interface ISearch<E, TypeID> {
    List<E> searchByKeyword(String query);

    List<E> searchByID(TypeID id);
}
