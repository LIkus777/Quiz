package com.example.base

interface EventHandler<T> {
    fun obtainEvent(event: T)
}