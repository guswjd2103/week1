package com.example.week1_contact.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.fragment.app.DialogFragment;

import com.example.week1_contact.R;

public class PasswordDialogFragment extends DialogFragment {
    private PasswordInputListener listener;
    private EditText password;

    public static PasswordDialogFragment newInstance(PasswordInputListener listener) {
        PasswordDialogFragment fragment = new PasswordDialogFragment();
        fragment.listener = listener;
        return fragment;
    }

    public interface PasswordInputListener {
        void onPasswordInputComplete(String password);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_login, null);
        builder.setView(view)
                .setPositiveButton("확인",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                listener.onPasswordInputComplete(password.getText().toString());
                            }
                        }).setNegativeButton("취소", null);

        return builder.create();
    }
}
