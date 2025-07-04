package org.aifooddelivery.app.core.di

import org.aifooddelivery.app.data.remote.api.CurrencyApiServiceImpl
import org.aifooddelivery.app.data.remote.api.UserRepositoryImpl
import org.aifooddelivery.app.domain.CurrencyApiService
import org.aifooddelivery.app.domain.repository.UserRepository
import org.aifooddelivery.app.domain.usecase.LoginUseCase
import org.aifooddelivery.app.presentation.chat.viewModel.ChatDetailViewModel
import org.aifooddelivery.app.presentation.chat.viewModel.ChatListViewModel
import org.aifooddelivery.app.presentation.home.HomeViewModel
import org.aifooddelivery.app.presentation.auth.ForgetPasswordViewModel
import org.aifooddelivery.app.presentation.auth.login.LoginViewModel
import org.aifooddelivery.app.presentation.auth.OtpVerificationViewModel
import org.aifooddelivery.app.presentation.auth.register.RegisterViewModel
import org.aifooddelivery.app.presentation.auth.ResetPasswordViewModel
import org.aifooddelivery.app.presentation.navigation.SettingViewModel
import org.aifooddelivery.app.presentation.notification.NotificationViewModel
import org.aifooddelivery.app.presentation.productDetails.ProductDetailViewModel
import org.aifooddelivery.app.utils.AppPreferences
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { AppPreferences(get()) }
    viewModel { SettingViewModel(get()) }
    viewModel { OtpVerificationViewModel() }
    viewModel { RegisterViewModel(userRepository=get<UserRepository>()) }
    viewModel { ResetPasswordViewModel() }
    viewModel { HomeViewModel() }
    viewModel { ChatDetailViewModel() }
    viewModel { NotificationViewModel() }
    viewModel { ProductDetailViewModel() }
    viewModel { ChatListViewModel() }
    viewModel { ForgetPasswordViewModel() }
    viewModel { LoginViewModel(loginUseCase = get<LoginUseCase>(),userRepository=get<UserRepository>()) }

    single<CurrencyApiService> { CurrencyApiServiceImpl() }
    single<UserRepository> { UserRepositoryImpl(get()) }
    single { LoginUseCase(get()) }

}




