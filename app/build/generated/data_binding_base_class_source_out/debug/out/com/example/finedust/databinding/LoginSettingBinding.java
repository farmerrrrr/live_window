// Generated by view binder compiler. Do not edit!
package com.example.finedust.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import com.example.finedust.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class LoginSettingBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final EditText etId;

  @NonNull
  public final EditText etPass;

  @NonNull
  public final Button joinEnterBtn;

  @NonNull
  public final Button loginBtn;

  private LoginSettingBinding(@NonNull LinearLayout rootView, @NonNull EditText etId,
      @NonNull EditText etPass, @NonNull Button joinEnterBtn, @NonNull Button loginBtn) {
    this.rootView = rootView;
    this.etId = etId;
    this.etPass = etPass;
    this.joinEnterBtn = joinEnterBtn;
    this.loginBtn = loginBtn;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static LoginSettingBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static LoginSettingBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.login_setting, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static LoginSettingBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.et_id;
      EditText etId = rootView.findViewById(id);
      if (etId == null) {
        break missingId;
      }

      id = R.id.et_pass;
      EditText etPass = rootView.findViewById(id);
      if (etPass == null) {
        break missingId;
      }

      id = R.id.join_enter_btn;
      Button joinEnterBtn = rootView.findViewById(id);
      if (joinEnterBtn == null) {
        break missingId;
      }

      id = R.id.login_btn;
      Button loginBtn = rootView.findViewById(id);
      if (loginBtn == null) {
        break missingId;
      }

      return new LoginSettingBinding((LinearLayout) rootView, etId, etPass, joinEnterBtn, loginBtn);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
