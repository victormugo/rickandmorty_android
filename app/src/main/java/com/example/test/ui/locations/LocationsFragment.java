package com.example.test.ui.locations;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.test.R;
import com.example.test.adapters.RecyclerLocationsListAdapter;
import com.example.test.api.APIClient;
import com.example.test.api.locationsApi;
import com.example.test.databinding.FragmentLocationsBinding;
import com.example.test.interfaces.RecyclerViewBaseClickListener;
import com.example.test.models.episodes.VoEpisodes;
import com.example.test.models.locations.ResponseLocations;
import com.example.test.models.locations.VoLocations;
import com.example.test.ui.episodes.EpisodesIdActivity;
import com.example.test.utils.Dialogs;

import java.net.HttpURLConnection;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LocationsFragment extends Fragment implements RecyclerViewBaseClickListener {

    public locationsApi locationsApi;

    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    private MaterialDialog dialog = null;

    List<VoLocations> list;

    private FragmentLocationsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LocationsViewModel locationsViewModel = new ViewModelProvider(this).get(LocationsViewModel.class);

        binding = FragmentLocationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        locationsApi = APIClient.getClient().create(locationsApi.class);

        return root;
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        // CREATED

        // Petición para obtener personajes
        this.getLocationsList();
    }

    public void getLocationsList() {

        dialog = Dialogs.showDialogProgress(getResources().getString(R.string.loading), getContext()).build();
        if (dialog != null) dialog.show();

        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executor.execute(() -> {

            Call<ResponseLocations> call = locationsApi.locations();

            call.enqueue(new Callback<ResponseLocations>() {
                @Override
                public void onResponse(@NonNull Call<ResponseLocations> call, @NonNull Response<ResponseLocations> response) {

                    handler.post(() -> {

                        switch (response.code()) {

                            case HttpURLConnection.HTTP_OK:

                                // Utilizar un linearLayout
                                layoutManager = new LinearLayoutManager(getContext());
                                binding.locationsList.setHasFixedSize(false);
                                binding.locationsList.setLayoutManager(layoutManager);

                                list = response.body().getResultsLocations();

                                // Especificar un adaptador
                                adapter = new RecyclerLocationsListAdapter(LocationsFragment.this, getContext(), list);
                                binding.emptyLocationsList.emptyText.setText(getResources().getString(R.string.message_empty_list));
                                binding.locationsList.setEmptyView(binding.emptyLocationsList.listEmpty);
                                binding.locationsList.setAdapter(adapter);

                                binding.locationsListRefreshLayout.setRefreshing(false);
                                break;

                            case HttpURLConnection.HTTP_UNAUTHORIZED:
                                break;

                            case HttpURLConnection.HTTP_FORBIDDEN:
                                break;

                            default:
                                // Mostrar mensaje de error
                        }

                        if (dialog != null) dialog.dismiss();
                    });
                }

                @Override
                public void onFailure(@NonNull Call<ResponseLocations> call, @NonNull Throwable t) {
                    call.cancel();
                    if (dialog != null) dialog.dismiss();
                }
            });
        });
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void recyclerViewItemClicked(int position) {

        VoLocations location = list.get(position);

        Intent episodeId = new Intent(getActivity().getApplicationContext(), LocationIdActivity.class);
        episodeId.putExtra("locationId", location.getId());
        getActivity().startActivity(episodeId);
    }
}