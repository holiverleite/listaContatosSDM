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

    private String MODO = null;

    private int indexEdit;

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

        String subtitulo = "";

        MODO = getIntent().getAction();

        if (MODO != null) {
            if (MODO == ListaContatosActivity.CONTATO_EXTRA) {
                Contato contato = (Contato) getIntent().getSerializableExtra(ListaContatosActivity.CONTATO_EXTRA);

                if (contato != null) {
                    // MODO DETALHE
                    subtitulo = "Detalhe do Contato";
                    modoDetalhes(contato);
                }
            }

            else if (MODO == ListaContatosActivity.CONTATO_EDIT){

                Contato contato = (Contato) getIntent().getSerializableExtra(ListaContatosActivity.CONTATO_EDIT);
                indexEdit = getIntent().getIntExtra(ListaContatosActivity.INDEX_LIST_VIEW, -1);

                if (contato != null) {
                    // MODO EDIT
                    subtitulo = "Editar Contato";
                    modoEdit(contato);
                }
            }

            else {
                //MODO CADASTRO
                MODO = ListaContatosActivity.CONTATO_EXTRA;
                subtitulo = "Novo Contato";
            }
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
        enderecoEditText.setText(contato.getEndereco());
        telefoneEditText.setText(contato.getTelefone());
        emailEditText.setText(contato.getEmail());

        salvarButton.setText("Salvar Edição");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cancelarButton:
                setResult(RESULT_CANCELED);
                finish();
                break;
            case R.id.salvarButton:

                Contato contato = new Contato();
                contato.setNome(nomeEditText.getText().toString());
                contato.setEndereco(enderecoEditText.getText().toString());
                contato.setTelefone(telefoneEditText.getText().toString());
                contato.setEmail(emailEditText.getText().toString());

                Intent resultadoIntent = new Intent();

                if (MODO == ListaContatosActivity.CONTATO_EXTRA) {
                    resultadoIntent.putExtra(ListaContatosActivity.CONTATO_EXTRA,contato);
                    resultadoIntent.setAction(ListaContatosActivity.CONTATO_EXTRA);
                    setResult(RESULT_OK, resultadoIntent);

                } else if (MODO == ListaContatosActivity.CONTATO_EDIT) {
                    resultadoIntent.putExtra(ListaContatosActivity.CONTATO_EDIT, contato);
                    resultadoIntent.setAction(ListaContatosActivity.CONTATO_EDIT);
                    resultadoIntent.putExtra(ListaContatosActivity.INDEX_LIST_VIEW, indexEdit);
                    setResult(RESULT_OK, resultadoIntent);
                }
                finish();
                break;
        }
    }
}
