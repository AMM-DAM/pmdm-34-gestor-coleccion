package com.pmdm.login.data

import com.pmdm.login.data.mocks.user.UserDaoMock
import com.pmdm.login.data.mocks.user.UserMock
import com.pmdm.login.model.User

class UserRepository {
    val userDao = UserDaoMock()

    fun getUsers(): List<User> = userDao.get().map(UserMock::toUser)

    fun getByEmail(email: String): User? = userDao.getByEmail(email)?.toUser()

    fun getByUsername(username: String): User? = userDao.getByUsername(username)?.toUser()

    fun insert(user: User) = userDao.insert(user.toUserMock())

    fun countUsers() = userDao.count()
}