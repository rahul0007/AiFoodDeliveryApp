package org.aifooddelivery.app.utils

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

object StringsManager {

    private val english = mapOf(
        "onboarding_one_title" to "We serve\nincomparable\ndelicacies",
        "onboarding_one_subtitle" to "All the best restaurants with their top menu waiting for you, they can't wait for your order!!",
        "next" to "Next",
        "email_is_required" to "Email is required",
        "password_is_required" to "Password is required",
        "password_to_short" to "Password must be at least 8 characters",
        "both_password" to "Both passwords must match",
        "password" to "Password",
        "new_password" to "New Password",
        "confirm_password" to "Confirm Password",
        "invalid_email" to "Invalid email",
        "enter_email" to "Enter email",
        "email_address" to "Email Address",
        "btn_continue" to "Continue",
        "forgot_password" to "Forgot password?",
        "enter_your_email_address_info" to "Enter your email address and we’ll send you confirmation code to reset your password",
        "select_which_contact_details" to "Select which contact details should we use to reset your password",
        "send_via_whatsApp" to "Send via WhatsApp",
        "send_via_email" to "Send via Email",
        "btn_sign_in" to "Sign In",
        "btn_register" to "Register",
        "or_sign_in_with" to "Or sign in with",
        "dont_have_an_account" to "Don’t have an account?",
        "sign_in_your_account" to "Please sign in to your account",
        "login_to_your_account" to "Login to your\naccount.",
        "btn_next" to "Next",
        "btn_Skip" to "Skip",
        "enter_user_name" to "Enter user name",
        "user_name" to "User Name",
        "user_name_is_required" to "User name is required",
        "user_name_too_short" to "User name too short",
        "create_an_account_to_start_looking" to "Create an account to start looking for the food you like",
        "create_your_account" to "Create your new\naccount",
        "verify_account" to "Verify Account",
        "home" to "Home",
        "chat" to "Chat",
        "cart" to "Cart",
        "profile" to "Profile",
        "password_changed" to "Password Changed",
        "password_changed_success" to "Password changed successfully, you can login again with a new password",
        "user_not_registered" to "User not registered",
        "invalid_password" to "Invalid password",
        "login_success" to "Login Success",
        "login_failed" to "Login Failed",
        "please_enter_email" to "Please enter email",
        "please_enter_password" to "Please enter password"
    )
    private val hindi = mapOf(
        "onboarding_one_title" to "हम परोसते हैं\nबेजोड़\nव्यंजन",
        "onboarding_one_subtitle" to "सभी बेहतरीन रेस्टोरेंट और उनका टॉप मेन्यू आपका इंतजार कर रहे हैं, वे आपके ऑर्डर का इंतजार नहीं कर सकते!!",
        "next" to "अगला",
        "email_is_required" to "ईमेल आवश्यक है",
        "password_is_required" to "पासवर्ड आवश्यक है",
        "password_to_short" to "पासवर्ड कम से कम 8 अक्षरों का होना चाहिए",
        "both_password" to "दोनों पासवर्ड मेल खाने चाहिए",
        "password" to "पासवर्ड",
        "new_password" to "नया पासवर्ड",
        "confirm_password" to "पासवर्ड की पुष्टि करें",
        "invalid_email" to "अमान्य ईमेल",
        "enter_email" to "ईमेल दर्ज करें",
        "email_address" to "ईमेल पता",
        "btn_continue" to "जारी रखें",
        "forgot_password" to "पासवर्ड भूल गए?",
        "enter_your_email_address_info" to "अपना ईमेल पता दर्ज करें और हम आपको पासवर्ड रीसेट करने के लिए पुष्टि कोड भेजेंगे",
        "select_which_contact_details" to "पासवर्ड रीसेट करने के लिए किस संपर्क विवरण का उपयोग करना है, चुनें",
        "send_via_whatsApp" to "WhatsApp के माध्यम से भेजें",
        "send_via_email" to "ईमेल के माध्यम से भेजें",
        "btn_sign_in" to "साइन इन करें",
        "btn_register" to "रजिस्टर करें",
        "or_sign_in_with" to "या इसके साथ साइन इन करें",
        "dont_have_an_account" to "क्या आपके पास खाता नहीं है?",
        "sign_in_your_account" to "कृपया अपने खाते में साइन इन करें",
        "login_to_your_account" to "अपने\nखाते में लॉगिन करें।",
        "btn_next" to "अगला",
        "btn_Skip" to "छोड़ें",
        "enter_user_name" to "यूजर नाम दर्ज करें",
        "user_name" to "यूजर नाम",
        "user_name_is_required" to "यूजर नाम आवश्यक है",
        "user_name_too_short" to "यूजर नाम बहुत छोटा है",
        "create_an_account_to_start_looking" to "वह खाना खोजने के लिए खाता बनाएं जो आपको पसंद हो",
        "create_your_account" to "अपना नया\nखाता बनाएं",
        "verify_account" to "खाता सत्यापित करें",
        "home" to "होम",
        "chat" to "चैट",
        "cart" to "कार्ट",
        "profile" to "प्रोफ़ाइल",
        "password_changed" to "पासवर्ड बदला गया",
        "password_changed_success" to "पासवर्ड सफलतापूर्वक बदल गया, आप नए पासवर्ड के साथ फिर से लॉगिन कर सकते हैं",
        "user_not_registered" to "उपयोगकर्ता पंजीकृत नहीं है",
        "invalid_password" to "अमान्य पासवर्ड",
        "login_success" to "लॉगिन सफल",
        "login_failed" to "लॉगिन विफल",
        "please_enter_email" to "कृपया ईमेल दर्ज करें",
        "please_enter_password" to "कृपया पासवर्ड दर्ज करें"
    )
    private val gujarati = mapOf(
        "onboarding_one_title" to "અમે ઉપસ્થિત કરીએ\nઅતુલનીય\nસ્વાદિષ્ટ વાનગી",
        "onboarding_one_subtitle" to "શ્રેષ્ઠ રેસ્ટોરાં અને તેમની ટોચની મેન્યુ તમારી રાહ જોઈ રહી છે, તેઓ તમારો ઓર્ડર માટે રાહ જોઈ શકતા નથી!!",
        "next" to "આગળ",
        "email_is_required" to "ઈમેઇલ જરૂરી છે",
        "password_is_required" to "પાસવર્ડ જરૂરી છે",
        "password_to_short" to "પાસવર્ડ ઓછામાં ઓછું 8 અક્ષર લાંબું હોવું જોઈએ",
        "both_password" to "બંને પાસવર્ડ મેળ ખાતા હોવા જોઈએ",
        "password" to "પાસવર્ડ",
        "new_password" to "નવો પાસવર્ડ",
        "confirm_password" to "પાસવર્ડની પુષ્ટિ કરો",
        "invalid_email" to "અમાન્ય ઈમેઇલ",
        "enter_email" to "ઈમેઇલ દાખલ કરો",
        "email_address" to "ઈમેઇલ સરનામું",
        "btn_continue" to "ચાલુ રાખો",
        "forgot_password" to "પાસવર્ડ ભૂલી ગયા છો?",
        "enter_your_email_address_info" to "તમારુ ઈમેઇલ સરનામું દાખલ કરો અને અમે તમને પિન કોಡ್ મોકલીશું જેથી તમે પાસવર્ડ રિસેટ કરી શકો",
        "select_which_contact_details" to "પાસવર્ડ રિસેટ કરવા કઈ માહિતી ઉપયોગમાં લેવી તે પસંદ કરો",
        "send_via_whatsApp" to "WhatsApp દ્વારા મોકલો",
        "send_via_email" to "ઈમેઇલ દ્વારા મોકલો",
        "btn_sign_in" to "સાઇન ઇન",
        "btn_register" to "રજિસ્ટર",
        "or_sign_in_with" to "અથવા સાથે સાઇન ઇન કરો",
        "dont_have_an_account" to "શું તમારું ખાતું નથી?",
        "sign_in_your_account" to "કૃપા કરીને તમારા ખાતામાં સાઇન ઇન કરો",
        "login_to_your_account" to "તમારા\nખાતામાં લૉગિન કરો.",
        "btn_next" to "આગળ",
        "btn_Skip" to "છોડો",
        "enter_user_name" to "યુઝર નામ દાખલ કરો",
        "user_name" to "યુઝર નામ",
        "user_name_is_required" to "યુઝર નામ જરૂરી છે",
        "user_name_too_short" to "યુઝર નામ ખૂબ નાનું છે",
        "create_an_account_to_start_looking" to "તમારા પસંદના ખોરાકને શોધવા માટે એકાઉન્ટ બનાવો",
        "create_your_account" to "તમારું નવું\nએકાઉન્ટ બનાવો",
        "verify_account" to "ખાતું તપાસો",
        "home" to "હોમ",
        "chat" to "ચેટ",
        "cart" to "કાર્ટ",
        "profile" to "પ્રોફાઇલ",
        "password_changed" to "પાસવર્ડ બદલાયો",
        "password_changed_success" to "પાસવર્ડ સફળતાપૂર્વક બદલાયો, હવે તમે નવો પાસવર્ડ સાથે ફરીથી લૉગીન કરી શકો છો",
        "user_not_registered" to "વપરાશકર્તા નોંધાયેલ નથી",
        "invalid_password" to "અમાન્ય પાસવર્ડ",
        "login_success" to "લૉગિન સફળ",
        "login_failed" to "લૉગિન નિષ્ફળ",
        "please_enter_email" to "કૃપા કરીને ઈમેઇલ દાખલ કરો",
        "please_enter_password" to "કૃપા કરીને પાસવર્ડ દાખલ કરો"
    )


    private var currentLanguageMap by mutableStateOf(english)
    fun setLanguage(languageCode: String) {
        currentLanguageMap = when (languageCode) {
            "en" -> english
            "hi" -> hindi
            "gu" -> gujarati
            else -> english // fallback
        }
    }

    fun get(key: String): String {
        return currentLanguageMap[key] ?: "**$key**"
    }
}
