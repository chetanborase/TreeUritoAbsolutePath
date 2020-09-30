package com.example.treeuritoactualpath;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=findViewById(R.id.textView);
    }

    public void openPickDirectory(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT_TREE);
            intent.addCategory(Intent.CATEGORY_DEFAULT);
            this.startActivityForResult(intent, 3000);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 3000) {
                assert data != null;
                String str = data.getData().getPath();
                textView.setText("Tree Uri Path is :\n" +str + "\n");
                String actualPath=findFullPath(str);
                textView.append("New Path is :\n");
                textView.append(actualPath);
            }
        }
    }
    public static String findFullPath(String path) {
        String actualResult="";
        path=path.substring(5);
        int index=0;
        StringBuilder result = new StringBuilder("/storage");
        for (int i = 0; i < path.length(); i++) {
            if (path.charAt(i) != ':') {
                result.append(path.charAt(i));
            } else {
                index = ++i;
                result.append('/');
                break;
            }
        }
        for (int i = index; i < path.length(); i++) {
            result.append(path.charAt(i));
        }
        if (result.substring(9, 16).equalsIgnoreCase("primary")) {
            actualResult = result.substring(0, 8) + "/emulated/0/" + result.substring(17);
        } else {
            actualResult = result.toString();
        }
        return actualResult;
    }
}