//package com.dcapp.creds_keeper.di
//
//import com.dcapp.creds_keeper.repository.CredRepository
//import com.dcapp.creds_keeper.viewmodels.BookmarksViewModel
//import com.dcapp.creds_keeper.viewmodels.HomeViewModel
//import com.dcapp.creds_keeper.viewmodels.MainViewModel
//import dagger.Module
//import dagger.Provides
//import dagger.hilt.InstallIn
//import dagger.hilt.android.components.ViewModelComponent
//import dagger.hilt.android.scopes.ViewModelScoped
//
//
//@Module
//@InstallIn(ViewModelComponent::class) // Specify the Hilt component where this module should be installed
//class ViewModelModule {
//
//    // Define your ViewModel dependencies as methods with @Provides annotation
//
//    @Provides
//    @ViewModelScoped // Specify the scope for your ViewModel
//    fun provideHomeViewModel(credRepository: CredRepository): HomeViewModel {
//        return HomeViewModel(credRepository)
//    }
//
//    @Provides
//    @ViewModelScoped // Specify the scope for your ViewModel
//    fun provideBookmarksViewModel(credRepository: CredRepository): BookmarksViewModel {
//        return BookmarksViewModel(credRepository)
//    }
//
//    @Provides
//    @ViewModelScoped
//    fun provideMainViewModel(): MainViewModel {
//        return MainViewModel()
//    }
//}