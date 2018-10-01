package br.edu.ifsp.scl.sdm.listacontatossdm.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

import br.edu.ifsp.scl.sdm.listacontatossdm.R;

public class ConfiguracoesActivity extends AppCompatActivity implements View.OnClickListener {

    private RadioGroup armazenamentoRadioGroup;
    private Button cancelButton;
    private Button salvarButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracoes);

        armazenamentoRadioGroup = findViewById(R.id.armazenamentoRadioGroup);

        cancelButton = findViewById(R.id.cancelarButton);
        cancelButton.setOnClickListener(this);

        salvarButton = findViewById(R.id.salvarButton);
        salvarButton.setOnClickListener(this);

        getSupportActionBar().setSubtitle("Configurações");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
            case R.id.cancelarButton:
                finish();
                break;
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
