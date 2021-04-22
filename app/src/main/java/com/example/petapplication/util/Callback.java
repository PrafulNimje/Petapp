package com.example.petapplication.util;

public interface Callback<T> {
    public T callback(T returningResult);
}