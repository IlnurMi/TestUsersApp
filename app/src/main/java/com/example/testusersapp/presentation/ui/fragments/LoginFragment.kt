package com.example.testusersapp.presentation.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.testusersapp.App
import com.example.testusersapp.R
import com.example.testusersapp.presentation.listeners.FragmentListener
import com.example.testusersapp.presentation.ui.views.CustomKeyboardView
import com.example.testusersapp.presentation.viewmodels.LoginViewModel
import com.example.testusersapp.presentation.viewmodels.ViewModelFactory
import kotlinx.android.synthetic.main.login_fragment.*
import javax.inject.Inject

const val ERROR_TIME = 3

class LoginFragment : Fragment() {

    @Inject
    lateinit var factory: ViewModelFactory
    private lateinit var loginViewModel: LoginViewModel
    private lateinit var fragmentListener: FragmentListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        fragmentListener = context as FragmentListener
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.login_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity().application as App).appComponent.injectLoginFragment(this)
        init()
        setListeners()
    }

    private fun init() {
        loginViewModel = ViewModelProviders.of(activity!!, factory).get(LoginViewModel::class.java)
        loginViewModel.getCodeLiveData()?.observe(this,
            Observer {
                updatePinCode(it)
            })

        loginViewModel.getErrorLiveData().observe(this, Observer {
            if (it.first.isNotEmpty())
                Toast.makeText(requireContext(), "${it.first} Попыток осталось: ${ERROR_TIME - it.second}", Toast.LENGTH_SHORT).show()
            loginViewModel.clearText()
            if (it.second == ERROR_TIME) {
                loginViewModel.clearErrorTime()
                keyboard.setEnabled()
            }
        })

        loginViewModel.getSuccessStateLiveData().observe(this, Observer {
            fragmentListener.replaceLoginFragment()
        })
    }

    private fun setListeners() {
        keyboard.setOnKeyboardListener(object : CustomKeyboardView.OnFragmentInteractionListener {
            override fun onKeyboardClick(symbol: String) {
                loginViewModel.setPinCode(symbol)
            }
        })
    }

    private fun updatePinCode(code: String){
        etPin.setText(code)
    }
}