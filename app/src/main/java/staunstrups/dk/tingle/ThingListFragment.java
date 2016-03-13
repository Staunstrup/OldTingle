  package staunstrups.dk.tingle;
  import android.os.Bundle;
  import android.support.v4.app.Fragment;
  import android.support.v7.widget.LinearLayoutManager;
  import android.support.v7.widget.RecyclerView;
  import android.util.Log;
  import android.view.LayoutInflater;
  import android.view.View;
  import android.view.ViewGroup;
  import android.widget.TextView;
  import java.util.List;
  import android.widget.Toast;

  public class ThingListFragment extends Fragment {
    private static final String TAG="ThingsList";

    private RecyclerView mListRecyclerView;
    private ThingAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
      final View v = inflater.inflate(R.layout.fragment_thing_list, container, false);
      mListRecyclerView= (RecyclerView) v.findViewById(R.id.thing_recycler_view);
      mListRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
      updateUI();
      return v;
    }

    private void updateUI() {
      ThingsDB thingsDB = ThingsDB.get(getActivity());
      List<Thing> crimes = thingsDB.getThingsDB();
      mAdapter = new ThingAdapter(crimes);
      mListRecyclerView.setAdapter(mAdapter);
    }

    public void stateChange() {mAdapter.notifyDataSetChanged();}

    private class ThingHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
      private Thing mThing;
      private TextView mWhatTextView, mWhereTextView;

      public ThingHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        mWhatTextView= (TextView) itemView.findViewById(R.id.thing_what);
        mWhereTextView= (TextView) itemView.findViewById(R.id.thing_where);
      }

      public void bindThing(Thing thing) {
        mThing = thing;
        mWhatTextView.append(mThing.getWhat());
        mWhereTextView.append(mThing.getWhere());
      }
      @Override
      public void onClick(View v) {
        //Log.i(TAG, "OnClick");
        Toast.makeText(getActivity(),
            mThing.getWhat() + " clicked!", Toast.LENGTH_SHORT).show();
      }
    }

    private class ThingAdapter extends RecyclerView.Adapter<ThingHolder> {
      private List<Thing> mThings;
      public ThingAdapter(List<Thing> things) {
        mThings= things;
      }
      @Override
      public ThingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
        View view = layoutInflater.inflate(R.layout.list_item_thing, parent, false);
        return new ThingHolder(view);
      }
      @Override
      public void onBindViewHolder(ThingHolder holder, int position) {
        Thing thing = mThings.get(position);
        holder.bindThing(thing);
      }
      @Override
      public int getItemCount() {
        return mThings.size();
      }
    }
  }