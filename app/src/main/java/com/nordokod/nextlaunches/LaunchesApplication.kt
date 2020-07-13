package com.nordokod.nextlaunches

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.nordokod.nextlaunches.model.repository.ILaunchesRepository
import com.nordokod.nextlaunches.model.repository.LaunchesRepository
import com.nordokod.nextlaunches.model.source.LaunchesAPI
import com.nordokod.nextlaunches.model.source.LaunchesDAO
import com.nordokod.nextlaunches.model.source.LaunchesDB
import com.nordokod.nextlaunches.ui.launches.LaunchesViewModel
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class LaunchesApplication : Application() {
    val APIModule = module {
        fun provideLaunchesAPI(retrofit: Retrofit) : LaunchesAPI{
            return retrofit.create(LaunchesAPI::class.java)
        }
        single { provideLaunchesAPI(get()) }
    }

    val databaseModule = module {
        fun provideDatabase(application: Application) : LaunchesDB{
            return Room.databaseBuilder(application,LaunchesDB::class.java,"launches")
                .fallbackToDestructiveMigration()
                .build()
        }
        fun provideLaunchesDAO(database:LaunchesDB) : LaunchesDAO{
            return database.launchesDAO
        }
        single { provideDatabase(androidApplication())}
        single { provideLaunchesDAO(get()) }
    }
    val networkModule = module {
        fun provideHttpClient(): OkHttpClient {
            val okHttpClientBuilder = OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
            okHttpClientBuilder.build()
            return okHttpClientBuilder.build()
        }
        fun provideRetrofit(client: OkHttpClient, baseUrl: String): Retrofit {
            val gson: Gson = GsonBuilder()
                .setDateFormat("MMMMM dd, yyyy HH:mm:ss zzz")
                .create()
            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build()
        }
        single { provideHttpClient() }
        single { provideRetrofit(get(), "https://launchlibrary.net") }
    }

    val repositoryModule = module {
        fun provideCountryRepository(api: LaunchesAPI, dao : LaunchesDAO, context: Context): ILaunchesRepository {
            return LaunchesRepository(api, dao, context)
        }
        single { provideCountryRepository(get(), get(),androidContext()) }
    }


    val viewModelModule = module{
        viewModel{LaunchesViewModel(get())}
    }

    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidLogger()
            androidContext(this@LaunchesApplication)
            modules(
                APIModule,
                databaseModule,
                networkModule,
                repositoryModule,
                viewModelModule
            )
        }


    }
}