package com.example.testusersapp.data.repository

import com.example.testusersapp.data.database.AppRoomDatabase
import com.example.testusersapp.data.extensions.returnMessage
import com.example.testusersapp.data.model.UserEntity
import com.example.testusersapp.data.model.UserModel
import com.example.testusersapp.data.network.api.ApiService
import com.example.testusersapp.data.preference.PreferenceRepository
import com.example.testusersapp.domain.interfaces.repositories.Repository
import com.example.testusersapp.domain.models.User
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.android.schedulers.AndroidSchedulers.*
import io.reactivex.schedulers.Schedulers
import java.util.ArrayList

class AppRepository(private val apiService: ApiService, private val database: AppRoomDatabase) :
    Repository {

    override fun getAllUsers(onSuccess: (List<User>) -> Unit, onError: (e: String) -> Unit) {
        val disposable = database.userDao()
            .getUsersSingle()
            .flatMap { it ->
                return@flatMap if (it.isEmpty()) {
                    apiService.getUsers(
                        PreferenceRepository.getInstance().getToken()
                    )
                        .flatMap {
                            recordDatabase(it)
                            Single.just(database.userDao().getUsers())
                        }
                } else database.userDao().getUsersSingle()
            }
            .subscribeOn(Schedulers.io())
            .observeOn(mainThread())
            .subscribe({
                onSuccess(convertUserList(it))
            }, {
                onError(it.returnMessage())
            })
    }

    override fun getUserById(id: Int, onSuccess: (User) -> Unit, onError: (e: String) -> Unit) {
        val dispose = database
            .userDao()
            .getUser(id)
            .subscribeOn(Schedulers.io())
            .observeOn(mainThread())
            .subscribe({
                onSuccess(convertUserModel(it))
            }, {
                onError(it.returnMessage())
            })
    }

    override fun getUserFriends(
        friendsId: List<String>, onSuccess: (List<User>) -> Unit, onError: (e: String) -> Unit
    ) {
        val d = database
            .userDao()
            .getUserFriends(convertList(friendsId))
            .subscribeOn(Schedulers.io())
            .observeOn(mainThread())
            .subscribe({
                onSuccess(convertUserList(it))
            }, {
                onError(it.returnMessage())
            })
    }

    override fun updateUsers(onSuccess: (List<User>) -> Unit, onError: (e: String) -> Unit) {
        val update = apiService
            .getUsers(PreferenceRepository.getInstance().getToken())
            .flatMap { recordDatabase(it)
                Single.just(database.userDao().getUsers())
            }
            .subscribeOn(Schedulers.io())
            .observeOn(mainThread())
            .subscribe({
                onSuccess(convertUserList(it))
            },{
                onError(it.returnMessage())
            })
    }

    override fun saveAndCheckPinCode(
        code: String,
        onSuccess: (success: Boolean) -> Unit,
        onError: (e: String) -> Unit
    ) {
        val pref = PreferenceRepository.getInstance()
        if (pref.getPinCode().isNullOrEmpty()) {
            pref.savePinCode(code)
            onSuccess.invoke(true)
        } else if (pref.getPinCode() == code) {
            onSuccess.invoke(true)
        } else
            onError.invoke("Неверный пин-код")
    }

    private fun getAllUser(): Single<List<UserEntity>> {
        return apiService.getUsers(PreferenceRepository.getInstance().getToken())
            .flatMap {
                recordDatabase(it)
                Single.just(database.userDao().getUsers())
            }
            .subscribeOn(Schedulers.io())
            .observeOn(mainThread())
    }

    private fun convertList(list: List<String>): List<Int> {
        val result = arrayListOf<Int>()
        for (item in list)
            result.add(item.toInt())
        return result
    }

    private fun recordDatabase(users: List<UserModel>) {
        val result: MutableList<UserEntity> = ArrayList()
        for (user in users)
            result.add(convertData(user))
        database.userDao().insertList(result)
    }

    private fun convertData(userModel: UserModel): UserEntity {
        val friends: MutableList<String> = ArrayList()
        for (friend in userModel.friends!!)
            friend.id?.let { friends.add(it.toString()) }

        return UserEntity(
            userModel.id,
            userModel.guid,
            userModel.isActive,
            userModel.balance,
            userModel.age,
            userModel.eyeColor,
            userModel.name,
            userModel.gender,
            userModel.company,
            userModel.email,
            userModel.phone,
            userModel.address,
            userModel.about,
            userModel.registered,
            userModel.latitude,
            userModel.longitude,
            userModel.tags,
            friends,
            userModel.favoriteFruit
        )
    }


    // convert list userModel to list user and convert object userModel to user
    private fun convertUserList(userList: List<UserEntity>): List<User> {
        val result: MutableList<User> = ArrayList()
        for (user in userList)
            result.add(convertUserModel(user))
        return result
    }

    private fun convertUserModel(userEntity: UserEntity): User {
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
            userEntity.friends,
            userEntity.favoriteFruit
        )
    }
}