package com.phearumthann.mymovie.ui.dialogs;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.ViewGroup;
import android.view.Window;

import com.phearumthann.mymovie.R;
import com.phearumthann.mymovie.databinding.ViewProgressBarBinding;

/**
 * Created by PhearumThann on 2/21/17.
 * phearumandroid@gmail.com
 */

public class ProgressDialog extends DialogFragment {

    private static final String TAG = ProgressDialog.class.getSimpleName();

    public static void show(FragmentManager manager) {
        DialogFragment fragment = (DialogFragment) manager.findFragmentByTag(TAG);
        if (fragment != null) {
            fragment.dismiss();
        }
        fragment = new ProgressDialog();
        fragment.setCancelable(false);
        fragment.show(manager, TAG);
    }

    public static void hide(FragmentManager manager) {
        DialogFragment fragment = (DialogFragment) manager.findFragmentByTag(TAG);
        if (fragment != null && fragment.isResumed()) {
            fragment.dismiss();
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Activity activity = getActivity();
        Dialog dialog = null;
        ViewProgressBarBinding binding = ViewProgressBarBinding.inflate(activity.getLayoutInflater());
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.GINGERBREAD) {
            AlertDialog.Builder builder = new AlertDialog.Builder(activity);
            builder.setView(binding.getRoot());
            builder.setCancelable(false);
            dialog = builder.create();
        } else {
            dialog = new Dialog(activity);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(binding.getRoot());
        }
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        if (dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        return dialog;
    }

    @Override
    public void onResume() {
        super.onResume();
        // Force the width to a 100 px because the view is always set to match_parent
        // even though it is defined in the xml
        Dialog dialog = getDialog();
        if (dialog != null) {
            int width = getContext().getResources().getDimensionPixelSize(R.dimen.progress_bar_background_size);
            dialog.getWindow().setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
    }

    @Override
    public int getTheme() {
        return R.style.DialogThemeOverlay;
    }
}
