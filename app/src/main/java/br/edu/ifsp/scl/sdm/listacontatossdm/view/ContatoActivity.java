package br.edu.ifsp.scl.sdm.listacontatossdm.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.edu.ifsp.scl.sdm.listacontatossdm.R;
import br.edu.ifsp.scl.sdm.listacontatossdm.model.Contato;

public class ContatoActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText nomeEditText;
    private EditText enderecoEditText;
    private EditText telefoneEditText;
    private EditText emailEditText;
    private Button cancelarButton;
    private Button salvarButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contato);

        // Buscando referencias de layout
        nomeEditText = findViewById(R.id.nomeEditText);
        enderecoEditText =findViewById(R.id.enderecoEditText);
        telefoneEditText =findViewById(R.id.telefoneEditText);
        emailEditText = findViewById(R.id.emailEditText);
        cancelarButton = findViewById(R.id.cancelarButton);
        salvarButton = findViewById(R.id.salvarButton);

        // setando listeners dos botões
        cancelarButton.setOnClickListener(this);
        salvarButton.setOnClickListener(this);

        String subtitulo;
        Contato contato = (Contato) getIntent().getSerializableExtra(ListaContatosActivity.CONTATO_EXTRA);
        if (contato != null) {
            // MODO DETALHE
            subtitulo = "Detalhe do Contato";
//            modoDetalhes(contato);
            modoEdit(contato);
        } else {
            //MODO CADASTRO
            subtitulo = "Novo Contato";
        }

        // Setando subtitulo
        getSupportActionBar().setSubtitle(subtitulo);
    }

    private void modoDetalhes(Contato contato) {
        nomeEditText.setText(contato.getNome());
        nomeEditText.setEnabled(false);
        enderecoEditText.setText(contato.getEndereco());
        enderecoEditText.setEnabled(false);
        telefoneEditText.setText(contato.getTelefone());
        telefoneEditText.setEnabled(false);
        emailEditText.setText(contato.getEmail());
        emailEditText.setEnabled(false);
        salvarButton.setVisibility(View.GONE);

        cancelarButton.setText("Voltar");
    }

    private void modoEdit(Contato contato) {
        nomeEditText.setText(contato.getNome());
        nomeEditText.setEnabled(true);
        enderecoEditText.setText(contato.getEndereco());
        enderecoEditText.setEnabled(true);
        telefoneEditText.setText(contato.getTelefone());
        telefoneEditText.setEnabled(true);
        emailEditText.setText(contato.getEmail());
        emailEditText.setEnabled(true);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cancelarButton:
                setResult(RESULT_CANCELED);
                finish();
                break;
            case R.id.salvarButton:
                Contato novoContato = new Contato();
                novoContato.setNome(nomeEditText.getText().toString());
                novoContato.setEndereco(enderecoEditText.getText().toString());
                novoContato.setTelefone(telefoneEditText.getText().toString());
                novoContato.setEmail(emailEditText.getText().toString());

                Intent resultadoIntent = new Intent();
                resultadoIntent.putExtra(ListaContatosActivity.CONTATO_EXTRA,novoContato);

                setResult(RESULT_OK, resultadoIntent);
                finish();
                break;
        }
    }
}
