package com.example.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class CheckoutFragment extends Fragment {

    private Button placeOrderButton;
    private Callback callback;

    public static final String POSTAL_CODE_REGEX = "^[A-Za-z]\\d[A-Za-z][ -]?\\d[A-Za-z]\\d$";
    public static final String PHONE_NUMBER_REGEX = "^(\\d{3})[- ]?(\\d{3})[- ]?(\\d{4})$";
    public static final String CARD_NUMBER_REGEX = "^4[0-9]{12}(?:[0-9]{3})?$";
    public static final String CVC_REGEX = "^[0-9]{3,4}$";
    private EditText cvc;
    private EditText fullName;
    private EditText streetName;
    private EditText zipCode;
    private EditText mobileNumber;
    private EditText cardNumber;
    private EditText expiryDate;


    public CheckoutFragment() {

    }

    public static CheckoutFragment newInstance() {
        CheckoutFragment fragment = new CheckoutFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_checkout_form, container, false);
        placeOrderButton = (Button) view.findViewById(R.id.bthPlaceOrder);
        placeOrderButton.setOnClickListener(v -> {
            if (validateFields()) {
                callback.onOrderPlaced();
            }
        });


        fullName = (EditText) view.findViewById(R.id.etName);
        streetName = (EditText) view.findViewById(R.id.etStreet);
        zipCode = (EditText) view.findViewById(R.id.etZipCode);
        mobileNumber = (EditText) view.findViewById(R.id.etMobileNumber);

        cardNumber = (EditText) view.findViewById(R.id.etCardNumber);
        expiryDate = (EditText) view.findViewById(R.id.etExpiryDate);
        cvc = (EditText) view.findViewById(R.id.etCvc);


        return view;
    }

    private boolean validateFields() {
        ArrayList<EditText> requiredFields = new ArrayList<>();
        requiredFields.add(fullName);
        requiredFields.add(streetName);
        requiredFields.add(zipCode);
        requiredFields.add(mobileNumber);
        requiredFields.add(cardNumber);
        requiredFields.add(expiryDate);
        requiredFields.add(cvc);

        boolean allFieldsValid = true;

        for (EditText field : requiredFields) {
            if (field.getText().toString().trim().isEmpty()) {
                field.setError("This field is required");
                allFieldsValid = false;
            } else {
                field.setError(null);
            }
        }

        if (allFieldsValid) {
            if (!isValidInput(zipCode, POSTAL_CODE_REGEX, "Invalid postal code.") &&
                    !isValidInput(mobileNumber, PHONE_NUMBER_REGEX, "Invalid phone number.") &&
                    !isValidInput(cardNumber, CARD_NUMBER_REGEX, "Invalid card number.") &&
                    !isValidInput(cvc, CVC_REGEX, "Invalid cvc.")) {
                allFieldsValid = false;
            }
        }

        if (!allFieldsValid) {
            Toast.makeText(getContext(), "Please fill in all required fields", Toast.LENGTH_SHORT).show();
        }

        return allFieldsValid;
    }

    private boolean isValidInput(EditText field, final String REGEX, final String ERROR) {
        boolean inputIsValid = false;
        if (field.getText().toString().trim().matches(REGEX)) {
            inputIsValid = true;
        } else {
            field.setError(ERROR);
        }
        return inputIsValid;
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof CheckoutFragment.Callback) {
            callback = (CheckoutFragment.Callback) context;
        } else {
            throw new RuntimeException(context + " must implement `CheckoutFragment.Callback`");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        callback = null;
    }

    interface Callback {
        void onOrderPlaced();
    }
}
