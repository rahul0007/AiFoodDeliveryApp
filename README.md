
# 🍔 AiFoodDeliveryApp - Kotlin Multiplatform (KMP)

---

## 📱 Project Overview:

AiFoodDeliveryApp is a Kotlin Multiplatform (KMP) based Food Delivery prototype application.

### ✨ Features:

- ✅ **Local User Authentication (Login / Signup / Forgot Password / Change Password)** - Using SQLDelight
- ✅ **Clean Architecture**
- ✅ **Compose Multiplatform UI (Android + Desktop)**
- ✅ **Voyager & Jetpack Compose Navigation**
- ✅ **Koin for Dependency Injection**
- ✅ **Offline-First (Local DB)**
- ✅ **Scalable NavGraph-based Navigation**

---

## 🛠️ Tech Stack:

| Layer | Technology |
|------|-----------|
| Language | Kotlin |
| UI | Jetpack Compose Multiplatform |
| Navigation | Voyager Navigation / Jetpack Compose Navigation |
| Local DB | SQLDelight |
| DI | Koin |
| Architecture | MVVM + Clean Architecture |
| Async/State | Kotlin Coroutines / Flow |
| Platforms | Android ✅,Ios ✅, Desktop ✅ |



## 🗃️ Database - SQLDelight (UserEntity Table):

### Columns:

- **id** (Primary Key AutoIncrement)
- **username** (Text)
- **email** (Unique Text)
- **password** (Text)

---

### 🔑 Key Queries:

- `insertUser`
- `getUserByEmail`
- `validateUser`

---

## 🔐 Local Login Flow (SQLDelight + DataStore):

1. User enters credentials
2. ViewModel validates via Repository
3. Repository triggers SQLDelight queries
4. If valid → Save user session in DataStore
5. Navigate to Home Screen

---


## 🧪 Dependency Injection (Koin Example):

- UserDao
- UserRepository
- LoginViewModel
- RegisterViewModel

---



## 📋 Application Features / Screens:

### 🔐 Authentication:

- Login Screen
- Register Screen
- Forgot Password Screen
- Change Password Screen

### 🏠 Home:

- Item Listing
- Categories & Filters

### 🛒 Product & Cart:

- Product Detail Screen
- Cart Screen
- Payment Screen

### 💬 Chat Module:

- Chat List Screen
- Chat Detail Screen

### 👤 Profile:

- Profile Info Screen
- Personal Data
- Settings
- Add Card
- Extra Card

### 🔔 Notifications:

- Grouped Notification Listing

### 🔎 Search:

- Search Screen

### 📞 Support:

- Call Support Screen
- Delivery Tracking Screen
- Help Center

---

## 🚀 How to Run the App:

- ▶️ **Android** → Run using Android Studio Emulator or Device
- ▶️ **Desktop** → Run using Desktop JVM target (`DesktopLauncher` / `Main.kt`)

---

## 📸 Screenshots



![Screenshot__onBording_one](https://github.com/rahul0007/AiFoodDeliveryApp/blob/babfd570ab84ec08fd22ca42644616fafb7338b0/Screenshot__onBording_one.png).

![Screenshot__onBording_two](https://github.com/rahul0007/AiFoodDeliveryApp/blob/babfd570ab84ec08fd22ca42644616fafb7338b0/Screenshot__onBording_two.png).

![Screenshot__onBording_three](https://github.com/rahul0007/AiFoodDeliveryApp/blob/babfd570ab84ec08fd22ca42644616fafb7338b0/Screenshot__onBording_three.png).

 ![Login](https://github.com/rahul0007/AiFoodDeliveryApp/blob/5e0f64c6cbacfdd0a810ed7a41b863894dbd6553/Screenshot_Login.png).

 ![Sign_up](https://github.com/rahul0007/AiFoodDeliveryApp/blob/e35da298304199a97bc7a5160f19e33cc639823e/Screenshot_sign_up.png).

 ![Screenshot_forgot_password](https://github.com/rahul0007/AiFoodDeliveryApp/blob/babfd570ab84ec08fd22ca42644616fafb7338b0/Screenshot_forgot_password.png).

 ![Screenshot_forgot_password_bottomshet](https://github.com/rahul0007/AiFoodDeliveryApp/blob/babfd570ab84ec08fd22ca42644616fafb7338b0/Screenshot_forgot_password_bottomshet.png).

 ![Screenshot_otp](https://github.com/rahul0007/AiFoodDeliveryApp/blob/babfd570ab84ec08fd22ca42644616fafb7338b0/Screenshot_otp.png).

 ![Screenshot_otp_screen](https://github.com/rahul0007/AiFoodDeliveryApp/blob/babfd570ab84ec08fd22ca42644616fafb7338b0/Screenshot_otp_screen.png).

 ![reset_password_screen](https://github.com/rahul0007/AiFoodDeliveryApp/blob/babfd570ab84ec08fd22ca42644616fafb7338b0/reset_password_screen.png).
 
![home](https://github.com/rahul0007/AiFoodDeliveryApp/blob/babfd570ab84ec08fd22ca42644616fafb7338b0/Screenshot_food_home.png).

![Search](https://github.com/rahul0007/AiFoodDeliveryApp/blob/babfd570ab84ec08fd22ca42644616fafb7338b0/Screenshot_search.png).

![Screenshot_serch_plash_holder](https://github.com/rahul0007/AiFoodDeliveryApp/blob/babfd570ab84ec08fd22ca42644616fafb7338b0/Screenshot_serch_plash_holder.png).

![Screenshot_notification](https://github.com/rahul0007/AiFoodDeliveryApp/blob/babfd570ab84ec08fd22ca42644616fafb7338b0/Screenshot_notification.png).

![Screenshot_cart](https://github.com/rahul0007/AiFoodDeliveryApp/blob/babfd570ab84ec08fd22ca42644616fafb7338b0/Screenshot_cart.png).

![Screenshot_checkout](https://github.com/rahul0007/AiFoodDeliveryApp/blob/babfd570ab84ec08fd22ca42644616fafb7338b0/Screenshot_checkout.png).

![Screenshot_chat_list](https://github.com/rahul0007/AiFoodDeliveryApp/blob/babfd570ab84ec08fd22ca42644616fafb7338b0/Screenshot_chat_list.png).

![Screenshot_chat_details](https://github.com/rahul0007/AiFoodDeliveryApp/blob/babfd570ab84ec08fd22ca42644616fafb7338b0/Screenshot_chat_details.png).

![Screenshot_call](https://github.com/rahul0007/AiFoodDeliveryApp/blob/babfd570ab84ec08fd22ca42644616fafb7338b0/Screenshot_call.png).

![Screenshot_profile](https://github.com/rahul0007/AiFoodDeliveryApp/blob/babfd570ab84ec08fd22ca42644616fafb7338b0/Screenshot_profile.png).

![Screenshot_Order_details](https://github.com/rahul0007/AiFoodDeliveryApp/blob/babfd570ab84ec08fd22ca42644616fafb7338b0/Screenshot_Order_details.png).

![Screenshot_personal_data](https://github.com/rahul0007/AiFoodDeliveryApp/blob/babfd570ab84ec08fd22ca42644616fafb7338b0/Screenshot_personal_data.png).

![Screenshot_settings](https://github.com/rahul0007/AiFoodDeliveryApp/blob/babfd570ab84ec08fd22ca42644616fafb7338b0/Screenshot_settings.png).

![Screenshot_codes](https://github.com/rahul0007/AiFoodDeliveryApp/blob/babfd570ab84ec08fd22ca42644616fafb7338b0/Screenshot_codes.png).
  
![Screenshot_extra_cards](https://github.com/rahul0007/AiFoodDeliveryApp/blob/babfd570ab84ec08fd22ca42644616fafb7338b0/Screenshot_extra_cards.png).

![Screenshot_delete_card](https://github.com/rahul0007/AiFoodDeliveryApp/blob/babfd570ab84ec08fd22ca42644616fafb7338b0/Screenshot_delete_card.png).

![Screenshot_add_Card_bottom_sheet](https://github.com/rahul0007/AiFoodDeliveryApp/blob/babfd570ab84ec08fd22ca42644616fafb7338b0/Screenshot_add_Card_bottom_sheet.png).


![Screenshot_help](https://github.com/rahul0007/AiFoodDeliveryApp/blob/babfd570ab84ec08fd22ca42644616fafb7338b0/Screenshot_help.png).



Learn more about [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html)…
