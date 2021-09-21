package com.example.dialogos;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class Dialog_Atras extends DialogFragment
{
    private OnDialogoConfirmacionListener listener;

    public interface OnDialogoConfirmacionListener
    {
        void onPossitiveButtonClick(); //Eventos Botón Positivos
        //void onNegativeButtonClick(); //Eventos Botón Negativo
    }

    public void onAttach(Context context)
    {
        super.onAttach(context);
        try
        {
            listener = (OnDialogoConfirmacionListener) context;
        }
        catch (ClassCastException e)
        {
            throw new ClassCastException(context.toString() + " no Implemento OnDialogoConfirmacionListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setMessage(R.string.Dialog_Atras_message)
                .setTitle(R.string.Dialog_Atras_title)
                .setPositiveButton
                        (R.string.Dialog_Atras_positiveBut,
                        new DialogInterface.OnClickListener()
                        {
                            public void onClick(DialogInterface dialog, int which)
                            {
                                listener.onPossitiveButtonClick();
                            }
                        })
                .setNegativeButton
                        (R.string.Dialog_Atras_negativeBut,
                        new DialogInterface.OnClickListener()
                        {
                            public void onClick(DialogInterface dialog, int which)
                            {
                                //listener.onNegativeButtonClick();
                            }
                        });

        return builder.create();
    }
}