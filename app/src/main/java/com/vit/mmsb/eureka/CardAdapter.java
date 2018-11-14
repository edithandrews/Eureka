package com.vit.mmsb.eureka;

    import android.support.annotation.NonNull;
    import android.support.v7.widget.RecyclerView;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.TextView;

    import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
    import com.firebase.ui.firestore.FirestoreRecyclerOptions;
    import com.google.firebase.firestore.DocumentSnapshot;

public class CardAdapter extends FirestoreRecyclerAdapter<Card, CardAdapter.CardHolder> {

    public CardAdapter(@NonNull FirestoreRecyclerOptions <Card> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull CardHolder holder, int position, @NonNull Card model) {
        holder.textViewTitle.setText(model.getTitle());
        holder.textViewDescription.setText(model.getDescription());
        holder.textViewPriority.setText(String.valueOf(model.getPriority()));
    }

    @NonNull
    @Override
    public CardHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.question_card,
                parent, false);
        return new CardHolder(v);
    }

    class CardHolder extends RecyclerView.ViewHolder {
        TextView textViewTitle;
        TextView textViewDescription;
        TextView textViewPriority;

        public CardHolder(View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.text_view_title);
            textViewDescription = itemView.findViewById(R.id.text_view_description);
            textViewPriority = itemView.findViewById(R.id.text_view_priority);
        }
    }
}