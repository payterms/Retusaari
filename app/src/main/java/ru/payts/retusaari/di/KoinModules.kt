package ru.payts.retusaari.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import ru.payts.retusaari.data.NotesRepository
import ru.payts.retusaari.data.provider.FirestoreProvider
import ru.payts.retusaari.data.provider.RemoteDataProvider
import ru.payts.retusaari.ui.main.MainViewModel
import ru.payts.retusaari.ui.note.NoteViewModel
import ru.payts.retusaari.ui.splash.SplashViewModel

val appModule = module {
    single { FirebaseAuth.getInstance() }
    single { FirebaseFirestore.getInstance() }
    single { FirestoreProvider(get(), get()) } bind RemoteDataProvider::class
    single { NotesRepository(get()) }
}

val splashModule = module {
    viewModel { SplashViewModel(get()) }
}

val mainModule = module {
    viewModel { MainViewModel(get()) }
}

val noteModule = module {
    viewModel { NoteViewModel(get()) }
}