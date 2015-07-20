package tm.davidwang.views;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import tm.davidwang.vip.R;


/**
 * Created by mac on 15/7/15.
 */
public class ShowDialog extends AlertDialog {

    private TextView dialog_text;

    public ShowDialog(Context context, int theme){
        super(context,theme);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog);
        findID();
    }

    private void findID(){
        dialog_text = (TextView)findViewById(R.id.dialog_text);

    }

    public void setContent(String content){
        dialog_text.setText(content);
    }
}
