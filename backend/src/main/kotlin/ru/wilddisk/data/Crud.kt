package ru.wilddisk.data

interface Crud<T> {
    fun find(): T
    fun findAll(limit: Int = 0, offset: Int = 0): List<T>
    fun save()
    fun update()
    fun delete()
}