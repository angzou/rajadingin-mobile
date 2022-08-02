package id.rajadingin.consumer.networking


object Const {
    /* NEW ENDPOINT */ /* Rajadingin Server */
    const val BASE_URL = "http://194.233.85.129:81/rajadingin/Api/"

    const val IS_LOGGED_IN = "IS_LOGGED_IN"
    const val PREF_NAME = "RJDSRAHEDPREF"
    const val IS_LOGIN = "islogin"
    const val USER = "user"
    const val NOTIFICATION_TOKEN = "notificationtoken"
    const val USER_IMAGE = "userimgurl"
    const val P_ID = "productID"
    const val LIMIT = 10
    const val WISHLIST = "wishlist"
    const val NEXTDAY_CODE = 11
    const val REGULAR_CODE = 22
    const val NEXTDAY = "Nextday"
    const val REGULAR = "Regular"
    const val EXPET_HOLD_PENDING_ORDER_COUNT = "expetholdpending"
    const val SETTING = "setting"
    const val PRODUCT_RESULT = 512
    const val PRODUCT_DATA = "product_data"
    const val STR_CID = "cid"
    const val STR_CNAME = "cname"
    const val STRIPE_CURRENCY = "INR"
    const val COD = "COD"
    const val BANK_TRANSFER = "Bank Transfer"
    const val STRIPE = "STRIPE"
    const val NOTIF_TOPIC = "Rajadingin"
    var maxQuantity: Long = 10
    const val IDN_PHONE_CODE = "+62"
    var currency = "Rp. "

    const val PROMPT_FIELD_KOSONG = "Field tidak boleh kosong"
    const val PROMPT_SERVER_NO_RESPONSE = "Gagal terhubung ke server"
    const val PROMPT_INTERNET_IS_NOT_CONNECTED = "Internet tidak terhubung"
    const val PROMPT_BACK_TWICE_TO_EXIT = "Tekan sekali lagi untuk keluar"
    const val PROMPT_SIGNOUT_SUCCESSFULY ="Anda telah berhasil signout"
    const val PROMPT_WRONGUSERNAME_WRONGPASSWORD ="Username atau password salah"
    const val PROMPT_WELCOME ="WELCOME"
    const val PROMPT_FILL ="Mohon isi form registrasi dengan benar"

    const val CALLBACK_STATUS = "STATUS"
    const val CALLBACK_MESSAGE = "MESSAGE"
    const val CALLBACK_DESCRIPTION = "DESCRIPTION"
    const val CALLBACK_RESPONSE = "CALL RESPONSE"

    const val RESPONSE_400 = "400"
    const val RESPONSE_500 = "500"

    //HTTP Success Status
    const val RESPONSE_200 = "200"
    //User sudah terdaftar
    const val RESPONSE_201 = "201"
    //User tidak exist
    const val RESPONSE_204 = "204"
    //Salah Password
    const val RESPONSE_405 = "405"
}