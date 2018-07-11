package carvellwakeman.shoppingapp.selectuser;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import carvellwakeman.shoppingapp.R;

import java.util.HashMap;


public class SelectUserDialogFragment extends DialogFragment {

    public interface SelectUserInterfaceListener {
        void onUserSelected(DialogFragment dialog, int userId);
    }

    SelectUserInterfaceListener mListener;
    public HashMap<String, Integer> users = new HashMap<>();

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        String[] emails = users.keySet().toArray(new String[0]);

        builder.setTitle(R.string.menu_select_user).setItems(emails, (DialogInterface dialogInterface, int i) -> {
            mListener.onUserSelected(this, users.get(emails[i]));
        }).setNegativeButton(R.string.action_cancel, (DialogInterface dialogInterface, int i) ->
            this.dismiss()
        );


        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        mListener = (SelectUserInterfaceListener) context;
    }
}
