package ge.com.football.footballapp.view;

import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import ge.com.football.footballapp.model.ModelMatch;

public class ContentAdapter extends RecyclerView.Adapter {
    private final static int VIEW_TYPE_HEADER = 1;
    private final static int VIEW_TYPE_ACTION = 2;

    private ModelMatch modelMatch;

    public void setModelMatch(ModelMatch modelMatch) {
        this.modelMatch = modelMatch;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return modelMatch != null ? modelMatch.getMatchSummary().getSummaries().size() + 1 : 0;
    }
}
