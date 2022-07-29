package id.rajadingin.consumer.ui.activity.presenter

import id.rajadingin.consumer.ui.activity.interfaces.SplashScreenContractInterface.*

class SplashScreenPresenter(_view: View): Presenter {

    private var view: View = _view

    init {
        view.initView()
    }



}