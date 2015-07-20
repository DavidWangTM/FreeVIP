package tm.davidwang.views;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import tm.davidwang.baseinterface.DialogBack;
import tm.davidwang.vip.R;

/**
 * Created by mac on 15/7/15.
 */
public class TypeDialog  extends AlertDialog implements View.OnClickListener{

    private TextView dialog_text;
    private DialogBack back;
    private Button dialog_cancel,dialog_sure;

    public TypeDialog(Context context, int theme,DialogBack back){
        super(context,theme);
        this.back = back;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.type_dialog);
        findID();
    }

    private void findID(){
        dialog_text = (TextView)findViewById(R.id.dialog_text);
        dialog_cancel = (Button)findViewById(R.id.dialog_cancel);
        dialog_sure = (Button)findViewById(R.id.dialog_sure);
        dialog_sure.setOnClickListener(this);
        dialog_cancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        dismiss();
        switch (v.getId()){
            case R.id.dialog_cancel:
                back.CancelOnclick();
                break;
            case R.id.dialog_sure:
                back.SureOnclick();
                break;
        }
    }
}
