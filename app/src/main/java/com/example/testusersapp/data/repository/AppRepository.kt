package com.example.testusersapp.data.repository

import com.example.testusersapp.data.database.AppRoomDatabase
import com.example.testusersapp.data.interfaces.AppRepositoryInterface
import com.example.testusersapp.data.model.User
import com.example.testusersapp.data.model.UserEntity
import com.example.testusersapp.data.network.api.ApiService
import com.example.testusersapp.data.preference.PreferenceRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.ArrayList

class AppRepository(private val apiService: ApiService, private val database: AppRoomDatabase) :
    AppRepositoryInterface {

    override fun getAllUsers(): Single<List<User>> {
        return apiService.getUsers(PreferenceRepository.getInstance().getToken())
            .flatMap {
                recordDatabase(it)
                Single.just(database.userDao().getUsers())
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getAllUsersWithDatabase(): Single<List<User>> {
        return database.userDao()
            .getUsersSingle()
            .flatMap { it ->
                if (it.isEmpty()) {
                    return@flatMap apiService.getUsers(
                                PreferenceRepository.getInstance().getToken())
                                .flatMap {
                                    recordDatabase(it)
                                    Single.just(database.userDao().getUsers())
                                }
                }
                return@flatMap database.userDao().getUsersSingle()
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }


    override fun getUserById(id: Int) {
        //TODO("Not yet implemented")
    }

    private fun recordDatabase(users: List<UserEntity>) {
        val result: MutableList<User> = ArrayList()
        for (user in users)
            result.add(convertData(user))
        database.userDao().insertList(result)
    }

    private fun convertData(userEntity: UserEntity): User {
        val friends: MutableList<String> = ArrayList()
        for (friend in userEntity.friends!!)
            friend.id?.let { friends.add(it.toString()) }

        return User(
            userEntity.id,
            userEntity.guid,
            userEntity.isActive,
            userEntity.balance,
            userEntity.age,
            userEntity.eyeColor,
            userEntity.name,
            userEntity.gender,
            userEntity.company,
            userEntity.email,
            userEntity.phone,
            userEntity.address,
            userEntity.about,
            userEntity.registered,
            userEntity.latitude,
            userEntity.longitude,
            userEntity.tags,
            friends,
            userEntity.favoriteFruit
        )
    }
}