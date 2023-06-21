package com.example.appbanhangonline.database.interfaces;

import java.util.List;

public interface ISort<E> {
    List<E> sortAsc();

    List<E> sortDesc();
}
