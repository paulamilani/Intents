package br.edu.ifsp.scl.sdm.intents;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.webkit.URLUtil;
import android.widget.Toast;

import br.edu.ifsp.scl.sdm.intents.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    //ref
    private ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());

        //toolbar
        activityMainBinding.mainTb.appTb.setTitle("Intents");
        activityMainBinding.mainTb.appTb.setSubtitle("intents");
        setSupportActionBar(activityMainBinding.mainTb.appTb);

    }

    //https://developer.android.com/guide/fragments/appbar?hl=pt-br#activity-inflate
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.viewMi:
                String url;
                url = activityMainBinding.parameterTv.getText().toString();
                boolean isURL = URLUtil.isValidUrl(url);
                if (isURL) {
                    Intent siteIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(siteIntent);
                }
                return (true);

            case R.id.callMi:
                if (requisitarPermissaoLigacao()) {
                    discarTelefone();
                } else {
                    Toast.makeText(getApplicationContext(), "Você precisa permitir para realizar ligações", Toast.LENGTH_SHORT).show();
                }
                return (true);

            case R.id.dialMi:
                String numerotel = activityMainBinding.parameterTv.getText().toString();
                Intent ligacaoIntent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", numerotel, null));
                startActivity(ligacaoIntent);
                return (true);


            case R.id.exitMi:
                finish();
                return (true);

            case R.id.actionMi:
                // Abrindo outra activity usando uma Intent Action
                Intent actionIntent = new Intent("OPEN_ACTION_ACTIVITY").putExtra(
                        Intent.EXTRA_TEXT,
                        activityMainBinding.parameterEt.getText().toString()
                );
                startActivity(actionIntent);
                return (true);
        }
        return (false);

    }

    private void discarTelefone() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel: ".concat(activityMainBinding.parameterEt.getText().toString())));
        startActivity(intent);

    }

    private boolean requisitarPermissaoLigacao() {
        ActivityResultLauncher<String> requisicaoPermissaoActivityLauncher = null;
        requisicaoPermissaoActivityLauncher.launch(Manifest.permission.CALL_PHONE);

        return false;
    }
}