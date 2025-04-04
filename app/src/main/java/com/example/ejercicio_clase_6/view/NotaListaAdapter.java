package com.example.ejercicio_clase_6.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ejercicio_clase_6.R;
import com.example.ejercicio_clase_6.model.Nota;

import java.util.List;

public class NotaListaAdapter extends BaseAdapter {
    private Context context;
    private List<Nota> notas;

    public NotaListaAdapter(Context context, List<Nota> notas) {
        this.context = context;
        this.notas = notas;
    }

    @Override
    public int getCount() {
        return notas.size();
    }

    @Override
    public Object getItem(int i) {
        return notas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_notas, viewGroup, false);
        }
        Nota nota = notas.get(i);
        TextView numeroNota = convertView.findViewById(R.id.notaNum);
        TextView valorNota = convertView.findViewById(R.id.estudianteNota);

        numeroNota.setText(String.valueOf(getItemId(i)));
        valorNota.setText(String.valueOf(nota.getValor()));
        return convertView;
    }
}
