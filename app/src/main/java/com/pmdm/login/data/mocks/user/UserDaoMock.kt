package com.pmdm.login.data.mocks.user

import android.util.Log.d

class UserDaoMock {
    val users = mutableListOf(
        UserMock(
            email = "johndoe@example.com",
            username = "John Doe",
            password = "johndoe"
        ),
        UserMock(
            email = "janedoe@example.com",
            username = "Jane Doe",
            password = "12345678"
        ),
        UserMock(
            email = "donpepito@hola.com",
            username = "Don Pepito",
            password = "password"
        ),
        UserMock(
            email = "donjose@hola.com",
            username = "Don Jose",
            password = "johndoe"
        ),
        UserMock(
            email = "pasoustedpormicasa@hola.com",
            username = "Paso usted por mi casa",
            password = "porsucasayopase"
        ),
    )

    fun get(): List<UserMock> = users

    fun getByEmail(email: String): UserMock? = users.firstOrNull { it.email == email }

    fun getByUsername(username: String): UserMock? = users.firstOrNull { it.username == username }

    fun insert(user: UserMock) {
        if (users.contains(user)) {
            users.remove(user)
        }
        users.add(user)
    }

    fun count() = users.count()
}