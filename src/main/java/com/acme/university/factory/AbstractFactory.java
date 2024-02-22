package com.acme.university.factory;

import java.util.List;

public interface AbstractFactory<T> {
    List<T> produce(List<String> lines);
}
