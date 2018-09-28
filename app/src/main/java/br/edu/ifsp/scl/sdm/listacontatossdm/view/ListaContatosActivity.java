package br.edu.ifsp.scl.sdm.listacontatossdm.view;

import android.app.AppComponentFactory;
import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifsp.scl.sdm.listacontatossdm.R;
import br.edu.ifsp.scl.sdm.listacontatossdm.adapter.ListaContatosAdapter;
import br.edu.ifsp.scl.sdm.listacontatossdm.model.Contato;

public class ListaContatosActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    //Request_code para abertura de tela contatoActivity - MODO NOVO CONTATO
    private final int NOVO_CONTATO_REQUEST_CODE = 0;
    private final int EDITAR_CONTATO_REQUEST_CODE = 1;

    // constante para passar parametros para tela contato acivity - MODO DETALHES
    public static final String CONTATO_EXTRA = "CONTATO_EXTRA";
    public static final String CONTATO_EDIT = "CONTATO_EDIT";

    //Referencia para as views
    private ListView listaContatosListView;

    //Lista de contatos usada para preencer a listView
    private List<Contato> listaContatos;

    private ListaContatosAdapter listaContatosAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_contatos);

        // Referencia para o listview
        listaContatosListView = findViewById(R.id.listaContatosListView);

        listaContatos = new ArrayList<>();
        /*Usado para populara lista para teste*/
//        preencheListaContatos();
//        List<String> listaNomes = new ArrayList<>();
//        for (Contato contato : listaContatos) {
//            listaNomes.add(contato.getNome());
//        }
//        ArrayAdapter<String> listaContatosAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,listaNomes);

        listaContatosAdapter = new ListaContatosAdapter(this, listaContatos);
        listaContatosListView.setAdapter(listaContatosAdapter);

        // Necessario para funcionar o longpress
        registerForContextMenu(listaContatosListView);

        listaContatosListView.setOnItemClickListener(this);
    }

//    private void preencheListaContatos() {
//        for (int i = 0; i < 20; i++) {
//            listaContatos.add(new Contato("c" + i, "ifsp", "1234", "i@ifsp"));
//        }
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        return true;
    }

    /*Captura toque nos elementos de menu*/
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.configuracaoMenuItem:
                Intent configuracoesIntent = new Intent(this, ConfiguracoesActivity.class);
                startActivity(configuracoesIntent);
                return true;
            case R.id.novoContatoMenuItem:
                //Abrindo tela novo contato
                Intent novoContatoIntent = new Intent("NOVO_CONTATO_ACTION"); // Definido no Manifest
//                Intent novoContatoIntent = new Intent(this, ContatoActivity.class);
                startActivityForResult(novoContatoIntent,NOVO_CONTATO_REQUEST_CODE);
                return true;
            case R.id.sairMenuItem:
                finish();
                return true;
        }
        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode) {
            case NOVO_CONTATO_REQUEST_CODE:
                if (resultCode == RESULT_OK) {
                    // recupero contato da intent data
                    Contato novoContato = (Contato) data.getSerializableExtra(CONTATO_EXTRA);
                    // atualizo lista
                    if (novoContato != null) {
                        listaContatos.add(novoContato);
                        listaContatosAdapter.notifyDataSetChanged();
                        Toast.makeText(this,"Novo contato adicionado",Toast.LENGTH_SHORT).show();
                    }
                    // notifico adapter
                } else {
                    if (resultCode == RESULT_CANCELED) {
                        // nao faria nada
                        Toast.makeText(this,"Cadastro Cancelado",Toast.LENGTH_SHORT).show();
                    }
                }
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.menu_contexto,menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo infoMenu = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        Contato contato = listaContatos.get(infoMenu.position);

        switch (item.getItemId()) {
            case R.id.editarContatoMenuItem:
                Intent editContato = new Intent(this,ContatoActivity.class);
                editContato.putExtra(CONTATO_EDIT, contato);
                startActivity(editContato);
                return true;
            case R.id.ligatContatoMenuItem:
                return true;
            case R.id.enviarEmailMenuItem:
                return true;
            case R.id.verEnderecoMenuItem:
                return true;
            case R.id.removerContatoMenuItem:
                return true;
        }
        return true;
    }

    // Executado pelo listener do ListView
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Contato contato = listaContatos.get(position);

        Intent detalhesContatoIntent = new Intent(this,ContatoActivity.class);

        detalhesContatoIntent.putExtra(CONTATO_EXTRA,contato);
        startActivity(detalhesContatoIntent);
    }
}
