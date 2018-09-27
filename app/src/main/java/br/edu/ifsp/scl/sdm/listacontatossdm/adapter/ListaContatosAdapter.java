package br.edu.ifsp.scl.sdm.listacontatossdm.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import br.edu.ifsp.scl.sdm.listacontatossdm.R;
import br.edu.ifsp.scl.sdm.listacontatossdm.model.Contato;

public class ListaContatosAdapter extends ArrayAdapter<Contato> {
    private LayoutInflater layoutInflator;

    public ListaContatosAdapter(Context context, List<Contato> listaContatos) {
        super(context, R.layout.layout_view_contato_adapter, listaContatos);
        layoutInflator = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        Holder holder;

        // Se ainda nao foi inflada
        if (convertView == null) {
            convertView = layoutInflator.inflate(R.layout.layout_view_contato_adapter, null);

            //Cria um novo holder para a celula nova
            holder = new Holder();
            holder.nomeContatoTextView = convertView.findViewById(R.id.nomeContatoTextView);
            holder.emailContatoTextView = convertView.findViewById(R.id.emailContatoTextView);

            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        Contato contato = getItem(position);
//        TextView nomeContatoTextView = convertView.findViewById(R.id.nomeContatoTextView);
//        TextView emailContatoTextView = convertView.findViewById(R.id.emailContatoTextView);

//        nomeContatoTextView.setText(contato.getNome());
//        emailContatoTextView.setText(contato.getEmail());

        holder.nomeContatoTextView.setText(contato.getNome());
        holder.emailContatoTextView.setText(contato.getEmail());

        return convertView;
    }

    private class Holder {
        public TextView nomeContatoTextView;
        public TextView emailContatoTextView;
    }
}
