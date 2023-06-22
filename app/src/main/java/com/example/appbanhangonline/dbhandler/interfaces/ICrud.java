package com.example.appbanhangonline.dbhandler.interfaces;

import com.example.appbanhangonline.database.DBHelper;

import java.util.List;

public interface ICrud<E, TypeId> {
    void add(E e);

    void update(E e);

    boolean delete(TypeId id);

    E getById(TypeId id);

    List<E> getListByPage(int page, int limit);

    List<E> getAll();
}
