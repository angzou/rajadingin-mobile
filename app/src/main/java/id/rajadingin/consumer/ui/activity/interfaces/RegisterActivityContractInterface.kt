package id.rajadingin.consumer.ui.activity.interfaces

interface RegisterActivityContractInterface {

    interface View {
        fun initView()
    }

    interface Presenter {
        fun regis(
            userFullName: String,
            userEmail: String,
            userUsername: String,
            userPhone: String,
            userAddress: String,
            userProvince: String,
            userKab: String,
            userKec: String,
            userKel: String,
            userPosCode: String,
            userPwd: String,
            userLong: String,
            userLat: String
        )
    }

}