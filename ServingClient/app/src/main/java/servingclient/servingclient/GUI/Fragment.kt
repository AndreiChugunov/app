package servingclient.servingclient.GUI

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import servingclient.servingclient.R


class Fragment: Fragment() {
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //return super.onCreateView(inflater, container, savedInstanceState)
        val rootView = inflater?.inflate(R.layout.fragment, container, false)
        return rootView
    }
}