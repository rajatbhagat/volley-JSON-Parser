package com.example.rajatbhagat.volleytest;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by rajatbhagat on 18/1/17.
 */

public class GameAdapter extends RecyclerView.Adapter<GameAdapter.GameViewHolder>{

    private List<Game> gameList;
    private int lastPosition = -1;
    private Context context;
    private LayoutInflater layoutInflater;
//    private Activity activiy;


    public GameAdapter(Context context, List<Game> gameList) {
        //Mandatory empty constructor
        this.context = context;
        this.gameList = gameList;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public GameAdapter.GameViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.card_view_game, parent, false);
        return new GameViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final GameAdapter.GameViewHolder holder, int position) {
        if(getItemCount() == 0)
            return;
        final int finalPosition = position;

        final Game current = gameList.get(finalPosition);
        holder.titleTextView.setText(current.getGameTitle());
        holder.developerTextView.setText(current.getGameDeveloper());
    }

    @Override
    public int getItemCount() {
        if(gameList == null)
            return 0;
        return gameList.size();
    }


    public class GameViewHolder extends RecyclerView.ViewHolder {

        TextView titleTextView;
        TextView developerTextView;
        CardView baseLayout;
        LinearLayout textContainer;

        public GameViewHolder(View itemView) {
            super(itemView);
            titleTextView = (TextView)itemView.findViewById(R.id.text_view_title);
            developerTextView = (TextView)itemView.findViewById(R.id.text_view_developer);
            baseLayout = (CardView)itemView.findViewById(R.id.game_card_view);
            textContainer = (LinearLayout)itemView.findViewById(R.id.game_text_container);
        }
    }
}
