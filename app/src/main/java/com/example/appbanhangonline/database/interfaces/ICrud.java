package com.example.appbanhangonline.database.interfaces;

import com.example.appbanhangonline.database.DBHelper;

import java.util.List;

public interface ICrud<E, TypeId> {
    void add(E e);

    void update(E e);

    void transactionWithCallBack(DBHelper.TransactionCallBack callBack);

    boolean delete(TypeId id);

    E getById(TypeId id);

    List<E> getListByPage(int page, int limit);

    List<E> getAll();
}
