package com.tugcenurdaglar.notlaruygulamasi;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class NotlarAdapter extends RecyclerView.Adapter<NotlarAdapter.CardTasarimTutucu> {
    private Context mContext;
    private List<Notlar> notlarListe;

    public NotlarAdapter(Context mContext, List<Notlar> notlarListe) {
        this.mContext = mContext;
        this.notlarListe = notlarListe;
    }

    @NonNull
    @Override
    public CardTasarimTutucu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_tasarim,parent,false);

        return new CardTasarimTutucu(view); //bu view ile card üzerindeki görsel nesnelere erişirim
    }

    @Override
    public void onBindViewHolder(@NonNull CardTasarimTutucu holder, int position) {
    //ne kadar veri varsa o kadar çalışır,
        // card tasarımmındaki verileri index numaralarına göre burada aktarırım

         Notlar not = notlarListe.get(position);

        holder.textViewDers.setText(not.getDersAdi());
        holder.textViewNot1.setText(String.valueOf(not.getNot1()));
        holder.textViewNot2.setText(String.valueOf(not.getNot2()));

        holder.not_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext, DetayActivity.class);
                intent.putExtra("nesne", not);

                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return notlarListe.size();
    }

    public class CardTasarimTutucu extends RecyclerView.ViewHolder {
        private TextView textViewDers;
        private TextView textViewNot1;
        private TextView textViewNot2;
        private CardView not_card;

        public CardTasarimTutucu(@NonNull View itemView) {
            super(itemView);

            textViewDers = itemView.findViewById(R.id.textViewDers);
            textViewNot1 = itemView.findViewById(R.id.textViewNot1);
            textViewNot2 = itemView.findViewById(R.id.textViewNot2);
            not_card = itemView.findViewById(R.id.not_card);
        }
    }
}
